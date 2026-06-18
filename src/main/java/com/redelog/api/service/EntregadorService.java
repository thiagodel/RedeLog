package com.redelog.api.service;

import com.redelog.api.dto.EntregadorRequestDTO;
import com.redelog.api.dto.EntregadorResponseDTO;
import com.redelog.api.mapper.EntregadorMapper;
import com.redelog.api.model.entities.Entregador;
import com.redelog.api.repository.ClienteRepository;
import com.redelog.api.repository.EntregaRepository;
import com.redelog.api.repository.EntregadorRepository;
import com.redelog.api.repository.FilialRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public class EntregadorService {

    private final EntregadorRepository entregadorRepository;
    private final EntregaRepository entregaRepository;
    private final FilialRepository filialRepository;
    private final ClienteRepository clienteRepository;


    public EntregadorService(EntregadorRepository entregadorRepository, EntregaRepository entregaRepository, FilialRepository filialRepository, ClienteRepository clienteRepository){
        this.entregadorRepository = entregadorRepository;
        this.entregaRepository = entregaRepository;
        this.filialRepository = filialRepository;
        this.clienteRepository = clienteRepository;
    }

    public Page<EntregadorResponseDTO> listarTodos(Pageable pageable){

        return entregadorRepository.findAll(pageable)
                .map(EntregadorMapper::toDto);
    }

    public EntregadorResponseDTO listarPorId(Long id){

        Entregador entregador = entregadorRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Entregador não encontrado"));

        return EntregadorMapper.toDto(entregador);
    }

    public EntregadorResponseDTO salvar(EntregadorRequestDTO dto){

        Entregador entregador = EntregadorMapper.toEntity(dto);

        Entregador salvo = entregadorRepository.save(entregador);

        return EntregadorMapper.toDto(salvo);
    }



}
