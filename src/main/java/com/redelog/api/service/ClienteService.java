package com.redelog.api.service;

import com.redelog.api.dto.ClienteRequestDTO;
import com.redelog.api.dto.ClienteResponseDTO;
import com.redelog.api.mapper.ClienteMapper;
import com.redelog.api.model.entities.Cliente;
import com.redelog.api.repository.ClienteRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service

public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;

    }

    public Page<ClienteResponseDTO> listarTodos(Pageable pageable){
        return clienteRepository.findAll(pageable)
            .map(ClienteMapper::toDTO);

    }

    public ClienteResponseDTO buscarPorId(long id){

    Cliente cliente = clienteRepository.findByIdOrThrow(id, "Cliente");

    return ClienteMapper.toDTO(cliente);
    }

    public ClienteResponseDTO salvar(ClienteRequestDTO dto){

    Cliente cliente = ClienteMapper.toEntity (dto);
        Cliente salvo = clienteRepository.save(cliente);
    return ClienteMapper.toDTO(salvo);
    }

    public ClienteResponseDTO atualizar(long id, ClienteRequestDTO dto) {
        Cliente cliente = clienteRepository.findByIdOrThrow(id, "Cliente");

        cliente.atualizarDados(
                dto.getNome(),
                dto.getTelefone(),
                dto.getEmail(),
                dto.getCep(),
                dto.getEndereco()
        );

        Cliente atualizado = clienteRepository.save(cliente);

        return ClienteMapper.toDTO(atualizado);
    }

    public void deletarPorId(long id){
    Cliente cliente = clienteRepository.findByIdOrThrow(id, "Cliente");

    clienteRepository.delete(cliente);
    }
}
