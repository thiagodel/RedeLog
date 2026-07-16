package com.redelog.api.service;

import com.redelog.api.dto.EntregadorRequestDTO;
import com.redelog.api.dto.EntregadorResponseDTO;
import com.redelog.api.mapper.EntregadorMapper;
import com.redelog.api.model.entities.Entregador;
import com.redelog.api.repository.EntregadorRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public class EntregadorService {

    private final EntregadorRepository entregadorRepository;



    public EntregadorService(EntregadorRepository entregadorRepository){
        this.entregadorRepository = entregadorRepository;

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

    public EntregadorResponseDTO atualizar(Long id, EntregadorRequestDTO dto) {

        Entregador entregador = entregadorRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        NOT_FOUND, "Entregador não encontrado"));

        if(!entregador.estaAtivo()){
        throw new ResponseStatusException(CONFLICT, "Não é possível atualizar um entregador inativo");

        }
        entregador.atualizarDados(
                dto.getNome(),
                dto.getTelefone(),
                dto.getEmail(),
                dto.getPlacaVeiculo()
        );

        return EntregadorMapper.toDto(entregadorRepository.save(entregador));
    }

    public void deletarPorId(Long id){

        Entregador entregador = entregadorRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Entregador não encontrado com ID" + id));

        entregadorRepository.delete(entregador);
    }




}
