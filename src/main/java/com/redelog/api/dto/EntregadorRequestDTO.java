package com.redelog.api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class EntregadorRequestDTO {

    @NotBlank
    private String nome;

    @NotBlank
    private String telefone;

    @Email
    private String email;

    @NotBlank
    private String placaVeiculo;

}
