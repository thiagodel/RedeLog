package com.redelog.api.service;

import com.redelog.api.dto.FilialRequestDTO;
import com.redelog.api.dto.FilialResponseDTO;
import com.redelog.api.mapper.FilialMapper;
import com.redelog.api.model.entities.Filial;
import com.redelog.api.repository.FilialRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public class FilialService {

    private final FilialRepository filialRepository;

    public FilialService(FilialRepository filialRepository) {

        this.filialRepository = filialRepository;
    }


    public Page<FilialResponseDTO> listarTodos(Pageable pageable) {

        return filialRepository.findAll(pageable)
                .map(FilialMapper::toDTO);

    }

    public FilialResponseDTO listarPorId(Long id) {

        Filial filial = filialRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Filial não encontrada"));

        return FilialMapper.toDTO(filial);
    }

    public void deletarPorId(Long id) {
        Filial filial = filialRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Filial não encontrada com ID: " + id));
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

            Filial filial = FilialMapper.toEntity(dto);

            filial = filialRepository.save(filial);

            return FilialMapper.toDTO(filial);
        }

    public FilialResponseDTO atualizar(Long id, FilialRequestDTO dto) {

        Filial filial = filialRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Filial não encontrada!"));

        if (!filial.getCnpj().equals(dto.getCnpj())
                && filialRepository.existsByCnpj(dto.getCnpj())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Já existe uma filial com este CNPJ."
            );
        }

        Filial dadosAtualizados = FilialMapper.toEntity(dto);

        filial.setNome(dadosAtualizados.getNome());
        filial.setEndereco(dadosAtualizados.getEndereco());
        filial.setNumeroFilial(dadosAtualizados.getNumeroFilial());

        Filial atualizada = filialRepository.save(filial);

        return FilialMapper.toDTO(atualizada);
    }
}


