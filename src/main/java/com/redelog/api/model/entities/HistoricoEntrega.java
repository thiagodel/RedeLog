package com.redelog.api.model.entities;

import com.redelog.api.model.enums.StatusEntrega;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "historico_entrega")
@Getter
@NoArgsConstructor
public class HistoricoEntrega {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private StatusEntrega status;

    private String observacao;

    private LocalDateTime data;

    @ManyToOne
    @JoinColumn(name = "entrega_id", nullable = false)
    private Entrega entrega;

    public HistoricoEntrega(StatusEntrega status, String observacao, Entrega entrega) {
        this.status = status;
        this.observacao = observacao;
        this.entrega = entrega;
        this.data = LocalDateTime.now();
    }
}


