package com.redelog.api.mapper;

import com.redelog.api.dto.EntregaRequestDTO;
import com.redelog.api.dto.EntregadorRequestDTO;
import com.redelog.api.dto.EntregadorResponseDTO;
import com.redelog.api.model.entities.Entregador;

public class EntregadorMapper {

    public static EntregadorResponseDTO toDto(Entregador entregador) {

        EntregadorResponseDTO dto = new EntregadorResponseDTO();
        dto.setId(entregador.getId());
        dto.setNome(entregador.getNome());
        dto.setTelefone(entregador.getTelefone());
        dto.setEmail(entregador.getEmail());
        dto.setPlacaVeiculo(entregador.getPlacaVeiculo());
        dto.setStatus(entregador.getStatus());
        return dto;
    }

    public static Entregador toEntity(EntregadorRequestDTO dto) {

        Entregador entregador = new Entregador();
        entregador.setNome(dto.getNome());
        entregador.setTelefone(dto.getTelefone());
        entregador.setEmail(dto.getEmail());
        entregador.setPlacaVeiculo(dto.getPlacaVeiculo());

        return entregador;


    }
}