package com.redelog.api.dto;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class EntregaResponseDTO {


    private Long id;
    private String codigoRastreio;

    private String nomeCliente;
    private String enderecoEntrega;
    private String filialOrigem;
    private String nomeEntregador;

    private String status;

    private LocalDateTime dataCriacao;
    private LocalDateTime dataEnvio;
    private LocalDateTime dataEntrega;

}
