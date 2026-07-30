package com.redelog.api.mapper;

import com.redelog.api.dto.EnderecoResponseDTO;
import com.redelog.api.dto.FilialRequestDTO;
import com.redelog.api.dto.FilialResponseDTO;
import com.redelog.api.model.entities.Endereco;
import com.redelog.api.model.entities.Filial;

public class FilialMapper {

    public static FilialResponseDTO toDTO(Filial filial) {
        FilialResponseDTO dto = new FilialResponseDTO();

        dto.setNumeroFilial(filial.getNumeroFilial());
        dto.setId(filial.getId());
        dto.setNome(filial.getNome());
        dto.setCnpj(filial.getCnpj());

        if (filial.getEndereco() != null) {
            Endereco endereco = filial.getEndereco();

            EnderecoResponseDTO enderecoDTO = new EnderecoResponseDTO();
            enderecoDTO.setRua(endereco.getRua());
            enderecoDTO.setNumero(endereco.getNumero());
            enderecoDTO.setBairro(endereco.getBairro());
            enderecoDTO.setCidade(endereco.getCidade());
            enderecoDTO.setEstado(endereco.getEstado());
            enderecoDTO.setCep(endereco.getCep());
            enderecoDTO.setComplemento(endereco.getComplemento());

            dto.setEndereco(enderecoDTO);
        }

        return dto;
    }

    public static Filial toEntity(FilialRequestDTO dto) {

        Filial filial = new Filial();

        filial.setNumeroFilial(dto.getNumeroFilial());
        filial.setNome(dto.getNome());
        filial.setCnpj(dto.getCnpj());

        if (dto.getEndereco() != null) {

            Endereco endereco = new Endereco();
            endereco.setRua(dto.getEndereco().getRua());
            endereco.setNumero(dto.getEndereco().getNumero());
            endereco.setCidade(dto.getEndereco().getCidade());
            endereco.setBairro(dto.getEndereco().getBairro());
            endereco.setEstado(dto.getEndereco().getEstado());
            endereco.setCep(dto.getEndereco().getCep());
            endereco.setComplemento(dto.getEndereco().getComplemento());

            filial.setEndereco(endereco);
        }
        return filial;
    }
}
