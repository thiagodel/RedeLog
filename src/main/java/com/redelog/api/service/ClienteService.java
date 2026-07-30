package com.redelog.api.service;

import com.redelog.api.dto.ClienteRequestDTO;
import com.redelog.api.dto.ClienteResponseDTO;
import com.redelog.api.mapper.ClienteMapper;
import com.redelog.api.model.entities.Cliente;
import com.redelog.api.repository.ClienteRepository;
import jakarta.persistence.Id;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.awt.print.Pageable;

@Service

public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;

    }

    public Page<ClienteResponseDTO> listarTodos(Pageable pageable){
        return clienteRepository.findAll(pageable);
            .map(ClienteMapper::toDTO);

    }

    public ClienteResponseDTO buscarPorId(long Id){

    Cliente cliente = clienteRepository.findById(Id)
            .orElseThrow(( new ResponseStatusException
                    NOT_FOUND, "Cliente não encontrado"));
        return ClienteMapper.toDto(cliente);

    }

    public ClienteResponseDTO salvar(ClienteRequestDTO dto){

    Cliente cliente = ClienteMapper.toEntity (dto);
        Cliente salvo = ClienteRepository.save(cliente);
    return ClienteMapper.toDto(salvo);
    }

    public ClienteResponseDTO atualizar(long id, ClienteRequestDTO dto){

    Cliente cliente = ClienteRepository.findById(id)
            .orElseThrow(()  new ResponseStatusException
    NOT_FOUND, "Cliente não encontrado"));

    Cliente atualizado = ClienteRepository.save(cliente)
    return ClienteMapper.toDto(atualizado);

    }

    public void deletarPorId(long id){
    Cliente cliente = ClienteRepository.findByID(id)
            .orElseThrom(() new ResponseStatusException
    NOT_FOUND, "Cliente não encontrado"));

    ClienteRepository.delete(cliente);
    }
}
