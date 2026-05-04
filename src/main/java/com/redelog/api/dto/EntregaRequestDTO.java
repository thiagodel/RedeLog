package com.redelog.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class EntregaRequestDTO {

    @NotBlank
    private String codigoRastreio;

    @NotNull
    private Long clienteId;

    @NotNull
    private Long enderecoEntregaId;

    @NotNull
    private Long filialOrigemId;

    @NotNull
    private Long entregadorId;

}
