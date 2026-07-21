package com.redelog.api.dto;

import com.redelog.api.model.entities.Endereco;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class EntregaRequestDTO {


    @NotNull
    private Long clienteId;

    @NotNull
    private EnderecoRequestDTO enderecoEntrega;

    @NotNull
    private Long filialOrigemId;

    @NotNull
    private Long entregadorId;

}
