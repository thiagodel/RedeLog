package com.redelog.api.dto;


import com.redelog.api.model.entities.Endereco;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FilialRequestDTO {

    @NotNull
    private String nome;

    @Valid
    @NotNull(message = "Endereço obrigatório!")
    private EnderecoRequestDTO endereco;
    private String numeroFilial;

    @NotNull(message = "CNPJ Obrigatório!")
    @Pattern(regexp = "\\d{14}", message = "CNPJ deve ter 14 dígitos")
    private String cnpj;

}
