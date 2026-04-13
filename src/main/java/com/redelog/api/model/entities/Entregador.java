package com.redelog.api.model.entities;

import com.redelog.api.model.enums.StatusEntregador;

public class Entregador {

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
        this. email = email;
        this.placaVeiculo = placaVeiculo;
        this.status = status;
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

    public void atualizarDados(){};


    public void atualizarTelefone(){};
}

    

