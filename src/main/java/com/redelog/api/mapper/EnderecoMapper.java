package com.redelog.api.mapper;

import com.redelog.api.dto.EnderecoRequestDTO;
import com.redelog.api.dto.EnderecoResponseDTO;
import com.redelog.api.model.entities.Endereco;

public class EnderecoMapper {

    public EnderecoResponseDTO toResponseDTO(Endereco endereco) {
        if (endereco == null) return null;

        EnderecoResponseDTO dto = new EnderecoResponseDTO();
        dto.setRua(endereco.getRua());
        dto.setNumero(endereco.getNumero());
        dto.setBairro(endereco.getBairro());
        dto.setCidade(endereco.getCidade());
        dto.setEstado(endereco.getEstado());
        dto.setCep(endereco.getCep());
        dto.setComplemento(endereco.getComplemento());

        return dto;
    }

    public static Endereco toEntity(EnderecoRequestDTO enderecoDto) {
        if (enderecoDto == null) {
            return null;
        }

        Endereco endereco = new Endereco();
        endereco.setRua(enderecoDto.getRua());
        endereco.setNumero(enderecoDto.getNumero());
        endereco.setBairro(enderecoDto.getBairro());
        endereco.setCidade(enderecoDto.getCidade());
        endereco.setEstado(enderecoDto.getEstado());
        endereco.setCep(enderecoDto.getCep());
        endereco.setComplemento(enderecoDto.getComplemento());

        return endereco;
    }

}
