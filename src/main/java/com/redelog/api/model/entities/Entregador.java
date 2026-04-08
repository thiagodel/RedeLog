package com.redelog.api.model.entities;

public class Entregador {

    private string nome;
    private string telefone;
    private string email;
    private string placaVeiculo;
    private boolean ativo;
    public entregador(){}

    public id (String nome, string telefone, string email, string placaVeiculo) {
        this.nome = nome;
        this.telefone = telefone;
        this. email = email;
        this.placaVeiculo = placaVeiculo;
        this.ativo = true;
    } 

    public void desativar(){
        this.ativo = false;
    }

    public void ativar(){
        this.ativo = true;
    }

    public void atualizarDados(string nome, string telefone, string email, string placaVeiculo){
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.placaVeiculo = placaVeiculo;
    
    }

    public void atualizarTelefone(String novoTelefone){
        this.telefone = novoTelefone;
         system.out.println("Telefone atualizado para: " + novoTelefone);
        
    }
}

    

