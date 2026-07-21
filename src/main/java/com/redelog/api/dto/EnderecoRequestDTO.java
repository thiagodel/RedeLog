package com.redelog.api.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EnderecoRequestDTO {

    @NotBlank
    private String rua;

    @NotBlank
    private String numero;
    @NotBlank
    private String bairro;

    @NotBlank
    private String cidade;

    @NotBlank
    private String estado;

    private String cep;

    private String complemento;
}
