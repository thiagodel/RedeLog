package com.redelog.api.model.entities;

import com.redelog.api.model.enums.StatusEntregador;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "entregador")
public class Entregador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String telefone;
    private String email;
    private String placaVeiculo;
    private StatusEntregador status;
    public Entregador(){}

    public Entregador (Long id, String nome, String telefone, String email, String placaVeiculo) {
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.placaVeiculo = placaVeiculo;
        this.status = StatusEntregador.ATIVO;
    } 

    public void desativar(){
       if (this.status == StatusEntregador.INATIVO){
           throw new IllegalStateException("Entregador já está inativo!");
       }
        this.status = StatusEntregador.INATIVO;
    }

    public void ativar(){
        if (this.status == StatusEntregador.ATIVO){
            throw new IllegalStateException("Entregador já está ativo!");

        }
        this.status = StatusEntregador.ATIVO;
    }

    public void atualizarDados(String nome, String telefone, String email, String placaVeiculo){
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.placaVeiculo = placaVeiculo;
    }


    public void atualizarTelefone(String telefone){
        if(telefone == null || telefone.isBlank()){
            throw new IllegalArgumentException("Telefone inválido!");
        }
        this.telefone = telefone;
    }
    public boolean estaAtivo() {
        return this.status == StatusEntregador.ATIVO;
    }

}

    

