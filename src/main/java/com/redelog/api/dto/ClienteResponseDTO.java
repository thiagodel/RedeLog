package com.redelog.api.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteResponseDTO {

    private int id;
    private String nome;
    private String telefone;
    private String endereco;
    private String cep;
    private String email;

}
