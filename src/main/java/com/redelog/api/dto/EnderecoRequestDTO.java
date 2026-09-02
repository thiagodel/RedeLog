package com.redelog.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
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

    @Pattern(
            regexp = "\\d{5}-\\d{3}",
            message = "CEP deve estar no formato 00000-000"
    )
    private String cep;

    private String complemento;
}
