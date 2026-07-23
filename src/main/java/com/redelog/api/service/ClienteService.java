package com.redelog.api.service;

import com.redelog.api.dto.ClienteRequestDTO;
import com.redelog.api.dto.ClienteResponseDTO;
import com.redelog.api.repository.ClienteRepository;
import org.springframework.data.domain.Page;
import org.springframework.web.server.ResponseStatusException;

import java.awt.print.Pageable;

@service

public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;

    }

    public Page<ClienteResponseDTO> listarTodos(Pageable pageable){
        return clienteRepository.findAll(pageable);
            .map(ClienteMapper::toDTO);

    }

    public ClienteResponseDTO buscarPorId(long id)

        Cliente cliente = clienteRepository.findByID(id)
                .orElseThrow(() new ResponseStatusException
                        NOT_FOUND, "Cliente não encontrado"));
        return clienteMapper.toDto(cliente);

    }

    public ClienteResponseDTO salvar(ClienteRequestDTO dto){

        Cliente cliente = clienteMapper.toEntity (dto);
        Cliente salvo = clienteRepository.save(cliente);
        return clienteMapper.toDto(salvo);
    }

    public ClienteResponseDTO atualizar(long id, ClienteRequestDTO dto){

        Cliente cliente = clienteRepository.findByID(id)
                .orElseThrow(()  new ResponseStatusException
                        NOT_FOUND, "Cliente não encontrado"));

        Cliente atualizado = clienteRepository.save(cliente)
        return clienteMapper.toDto(atualizado);

    }

    public void deletarPorId(long id){
        Cliente cliente = clienteRepository.findByID(id)
                .orElseThrom(() new ResponseStatusException
                        NOT_FOUND, "Cliente não encontrado"));

        clienteRepository.delete(cliente);
    }
}
