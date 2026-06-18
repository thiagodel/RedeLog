package com.redelog.api.dto;

import com.redelog.api.model.enums.StatusEntregador;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class EntregadorResponseDTO {

    private Long id;
    private String nome;
    private String telefone;
    private String email;
    private String placaVeiculo;
    private StatusEntregador status;

}
