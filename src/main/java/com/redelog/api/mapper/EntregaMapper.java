package com.redelog.api.mapper;

import com.redelog.api.dto.EntregaResponseDTO;
import com.redelog.api.model.entities.Entrega;

public class EntregaMapper {

    public static EntregaResponseDTO toDTO(Entrega entrega) {

        EntregaResponseDTO dto = new EntregaResponseDTO();
        dto.setId(entrega.getId());
        dto.setCodigoRastreio(entrega.getCodigoRastreio());
        dto.setNomeCliente(entrega.getCliente() != null ? entrega.getCliente().getNome() : null);
        dto.setNomeEntregador(entrega.getEntregador() != null ? entrega.getEntregador().getNome() : null);
        dto.setFilialOrigem(entrega.getFilialOrigem() != null ? entrega.getFilialOrigem().getNome() : null);
        dto.setEnderecoEntrega(entrega.getEnderecoEntrega());
        dto.setStatus(entrega.getStatus() != null ? entrega.getStatus().name() : null);
        dto.setDataCriacao(entrega.getDataCriacao());
        dto.setDataEnvio(entrega.getDataEnvio());
        dto.setDataEntrega(entrega.getDataEntrega());
        return dto;
    }

}
