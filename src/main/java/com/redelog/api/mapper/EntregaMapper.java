package com.redelog.api.mapper;

import com.redelog.api.dto.EntregaRequestDTO;
import com.redelog.api.dto.EntregaResponseDTO;
import com.redelog.api.model.entities.Entrega;

public class EntregaMapper {

    public static EntregaResponseDTO toDTO(Entrega entrega) {

        EntregaResponseDTO dto = new EntregaResponseDTO();
        dto.setId(entrega.getId());
        dto.setCodigoRastreio(entrega.getCodigoRastreio());
        dto.setNomeCliente(entrega.getCliente().getNome());
        dto.setNomeEntregador(entrega.getEntregador().getNome());
        dto.setFilialOrigem(entrega.getFilialOrigem().getNome());
        dto.setStatus(entrega.getStatus().name());
                return dto;
    }

    public static Entrega toEntity(EntregaRequestDTO dto) {

        Entrega entrega = new Entrega();

            return new Entrega();
    }

}
