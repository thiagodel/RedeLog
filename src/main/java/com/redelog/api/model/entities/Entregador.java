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

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getEmail() {
        return email;
    }

    public String getPlacaVeiculo() {
        return placaVeiculo;
    }

    public StatusEntregador getStatus() {
        return status;
    }

}

    

