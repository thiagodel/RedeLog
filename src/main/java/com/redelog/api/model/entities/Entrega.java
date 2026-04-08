package com.redelog.api.model.entities;

import com.redelog.api.model.enums.StatusEntrega;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "entrega")
@Getter
public class Entrega {

    protected Entrega() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String codigoRastreio;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @Embedded
    private Endereco enderecoEntrega;

    @ManyToOne
    @JoinColumn(name = "filial_id")
    private Filial filialOrigem;

    @ManyToOne
    @JoinColumn(name = "entregador_id")
    private Entregador entregador;

    @Enumerated(EnumType.STRING)
    private StatusEntrega status;

    private LocalDateTime dataCriacao;
    private LocalDateTime dataEnvio;
    private LocalDateTime dataEntrega;

    @OneToMany(mappedBy = "entrega", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<HistoricoEntrega> historico = new ArrayList<>();

    // 🔹 Construtor
    public Entrega(String codigoRastreio, Cliente cliente, Endereco enderecoEntrega, Filial filialOrigem) {
        this.codigoRastreio = codigoRastreio;
        this.cliente = cliente;
        this.enderecoEntrega = enderecoEntrega;
        this.filialOrigem = filialOrigem;
        this.status = StatusEntrega.CRIADA;
        this.dataCriacao = LocalDateTime.now();

        adicionarHistorico("Entrega criada", StatusEntrega.CRIADA);
    }

    // 🔥 método central
    private void adicionarHistorico(String observacao, StatusEntrega status) {
        HistoricoEntrega h = new HistoricoEntrega(status, observacao, this);
        this.historico.add(h);
    }

    // 🔥 regras de negócio

    public void despachar() {
        if (status != StatusEntrega.CRIADA) {
            throw new IllegalStateException("Entrega não pode ser despachada");
        }

        this.status = StatusEntrega.ENVIADA;
        this.dataEnvio = LocalDateTime.now();

        adicionarHistorico("Entrega despachada", StatusEntrega.ENVIADA);
    }

    public void sairParaEntrega() {
        if (status != StatusEntrega.ENVIADA) {
            throw new IllegalStateException("Entrega não está pronta para rota");
        }

        this.status = StatusEntrega.EM_ROTA;

        adicionarHistorico("Saiu para entrega", StatusEntrega.EM_ROTA);
    }

    public void finalizarEntrega() {
        if (status != StatusEntrega.EM_ROTA) {
            throw new IllegalStateException("Entrega não está em rota");
        }

        this.status = StatusEntrega.ENTREGUE;
        this.dataEntrega = LocalDateTime.now();

        adicionarHistorico("Entrega realizada", StatusEntrega.ENTREGUE);
    }

    public void registrarFalha(String motivo) {
        if (status != StatusEntrega.EM_ROTA) {
            throw new IllegalStateException("Falha só pode ocorrer em rota");
        }

        this.status = StatusEntrega.FALHA;

        adicionarHistorico("Falha na entrega: " + motivo, StatusEntrega.FALHA);
    }

    public void atribuirEntregador(Entregador entregador) {
        this.entregador = entregador;
    }

}

