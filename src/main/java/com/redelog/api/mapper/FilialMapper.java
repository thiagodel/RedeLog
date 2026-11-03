package com.redelog.api.mapper;

import com.redelog.api.dto.EnderecoResponseDTO;
import com.redelog.api.dto.FilialRequestDTO;
import com.redelog.api.dto.FilialResponseDTO;
import com.redelog.api.model.entities.Endereco;
import com.redelog.api.model.entities.Filial;
import org.springframework.stereotype.Component;

@Component
public class FilialMapper {

    private final EnderecoMapper enderecoMapper;

    public FilialMapper(EnderecoMapper enderecoMapper) {
        this.enderecoMapper = enderecoMapper;
    }

    public FilialResponseDTO toDTO(Filial filial) {
        FilialResponseDTO dto = new FilialResponseDTO();

        dto.setNumeroFilial(filial.getNumeroFilial());
        dto.setId(filial.getId());
        dto.setNome(filial.getNome());
        dto.setCnpj(filial.getCnpj());

        if (filial.getEndereco() != null) {
            dto.setEndereco(enderecoMapper.toResponseDTO(filial.getEndereco()));
        }

        return dto;
    }

    public Filial toEntity(FilialRequestDTO dto) {

        Filial filial = new Filial();

        filial.setNumeroFilial(dto.getNumeroFilial());
        filial.setNome(dto.getNome());
        filial.setCnpj(dto.getCnpj());

        if (dto.getEndereco() != null) {
            filial.setEndereco(enderecoMapper.toEntity(dto.getEndereco()));
        }

        return filial;
    }

    public void atualizarEntidade(Filial filial, FilialRequestDTO dto) {

        filial.setNome(dto.getNome());
        filial.setEndereco(enderecoMapper.toEntity(dto.getEndereco()));
        filial.setNumeroFilial(dto.getNumeroFilial());
        filial.setCnpj(dto.getCnpj());
    }
}
