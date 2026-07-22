package com.redelog.api.mapper;

import com.redelog.api.dto.EnderecoResponseDTO;
import com.redelog.api.dto.EntregaResponseDTO;
import com.redelog.api.model.entities.Endereco;
import com.redelog.api.model.entities.Entrega;

public class EntregaMapper {

    public static EntregaResponseDTO toDTO(Entrega entrega) {

        EntregaResponseDTO dto = new EntregaResponseDTO();
        dto.setId(entrega.getId());
        dto.setCodigoRastreio(entrega.getCodigoRastreio());
        dto.setNomeCliente(entrega.getCliente() != null ? entrega.getCliente().getNome() : null);
        dto.setNomeEntregador(entrega.getEntregador() != null ? entrega.getEntregador().getNome() : null);
        dto.setFilialOrigem(entrega.getFilialOrigem() != null ? entrega.getFilialOrigem().getNome() : null);
        dto.setStatus(entrega.getStatus() != null ? entrega.getStatus().name() : null);
        dto.setDataCriacao(entrega.getDataCriacao());
        dto.setDataEnvio(entrega.getDataEnvio());
        dto.setDataEntrega(entrega.getDataEntrega());

        if (entrega.getEnderecoEntrega() != null) {
            Endereco endereco = entrega.getEnderecoEntrega();

            EnderecoResponseDTO enderecoDTO = new EnderecoResponseDTO();
            enderecoDTO.setRua(endereco.getRua());
            enderecoDTO.setNumero(endereco.getNumero());
            enderecoDTO.setBairro(endereco.getBairro());
            enderecoDTO.setCidade(endereco.getCidade());
            enderecoDTO.setEstado(endereco.getEstado());
            enderecoDTO.setCep(endereco.getCep());
            enderecoDTO.setComplemento(endereco.getComplemento());

            dto.setEnderecoEntrega(enderecoDTO);
        }

        return dto;
    }

}
