package com.redelog.api.model.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "filial")
@Getter
@Setter
public class Filial {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;

    @Embedded
    private Endereco endereco;
    @Column(unique = true)
    private String numeroFilial;
    @Column(unique = true)
    private String cnpj;
    public Filial() {
    }
}

    
