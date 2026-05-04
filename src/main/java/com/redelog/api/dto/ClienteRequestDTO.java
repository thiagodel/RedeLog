package com.redelog.api.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteRequestDTO {

    @NotBlank
    private String nome;

    @NotBlank
    private String telefone;

    private String email;
    private String cep;
    private String endereco;


}
