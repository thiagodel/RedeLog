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
    @Column(nullable = false)
    private String nome;

    @Embedded
    private Endereco endereco;
    @Column(unique = true, nullable = false)
    private String numeroFilial;
    @Column(unique = true, nullable = false, length = 14)
    private String cnpj;
    public Filial() {
    }
}

    
