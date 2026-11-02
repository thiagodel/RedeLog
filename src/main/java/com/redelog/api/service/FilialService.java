package com.redelog.api.service;

import com.redelog.api.dto.FilialRequestDTO;
import com.redelog.api.dto.FilialResponseDTO;
import com.redelog.api.mapper.EnderecoMapper;
import com.redelog.api.mapper.FilialMapper;
import com.redelog.api.model.entities.Filial;
import com.redelog.api.repository.FilialRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class FilialService {

    private final FilialRepository filialRepository;
    private final EnderecoMapper enderecoMapper;
    private final FilialMapper filialMapper;

    public FilialService(FilialRepository filialRepository, EnderecoMapper enderecoMapper, FilialMapper filialMapper) {

        this.filialRepository = filialRepository;
        this.enderecoMapper = enderecoMapper;
        this.filialMapper = filialMapper;
    }


    public Page<FilialResponseDTO> listarTodos(Pageable pageable) {

        return filialRepository.findAll(pageable)
                .map(filialMapper::toDTO);

    }

    public FilialResponseDTO listarPorId(Long id) {

        Filial filial = filialRepository.findByIdOrThrow(id, "Filial");

        return filialMapper.toDTO(filial);
    }

    public void deletarPorId(Long id) {
        Filial filial = filialRepository.findByIdOrThrow(id, "Filial");
        filialRepository.delete(filial);
    }

    public FilialResponseDTO cadastrar(FilialRequestDTO dto) {

        if (filialRepository.existsByCnpj(dto.getCnpj())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Já existe uma filial com este CNPJ."
            );
        }

        if (filialRepository.existsByNumeroFilial(dto.getNumeroFilial())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Número da filial já cadastrado."
            );
        }

            Filial filial = filialMapper.toEntity(dto);

            filial = filialRepository.save(filial);

            return filialMapper.toDTO(filial);
        }

    public FilialResponseDTO atualizar(Long id, FilialRequestDTO dto) {

        Filial filial = filialRepository.findByIdOrThrow(id, "Filial");

        if (!filial.getCnpj().equals(dto.getCnpj())
                && filialRepository.existsByCnpj(dto.getCnpj())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Já existe uma filial com este CNPJ."
            );
        }

        if (filialRepository.existsByNumeroFilialAndIdNot(dto.getNumeroFilial(), id)) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Número da filial já cadastrado."
            );
        }

        filialMapper.atualizarEntidade(filial, dto);

        Filial atualizada = filialRepository.save(filial);

        return filialMapper.toDTO(atualizada);

    }
}


