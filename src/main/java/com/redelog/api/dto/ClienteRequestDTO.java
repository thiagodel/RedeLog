package com.redelog.api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteRequestDTO {

    @NotBlank
    private String nome;

    @NotBlank
    private String telefone;

    @Email
    private String email;

    @Pattern(
            regexp = "\\d{5}-\\d{3}",
            message = "CEP deve estar no formato 00000-000"
    )
    private String cep;

    private String endereco;


}
