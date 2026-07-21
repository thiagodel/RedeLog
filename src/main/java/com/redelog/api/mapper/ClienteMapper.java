package com.redelog.api.mapper;

import com.redelog.api.dto.ClienteRequestDTO;
import com.redelog.api.dto.ClienteResponseDTO;
import com.redelog.api.model.entities.Cliente;

public class ClienteMapper {
    private static ClienteMapper instance;
    private ClienteMapper() {}
    private static synchronized void createInstance() {
        if (instance == null) {
            instance = new ClienteMapper();
        }
    }
}

public static ClienteResponseDTO toDTO(Cliente cliente) {
    ClienteResponseDTO dto = new ClienteResponseDTO();

    dto.setNome(cliente.getNome());
    dto.setTelefone(cliente.getTelefone());
    dto.setEmail(cliente.getEmail());
    dto.setCep(cliente.getCep());
    dto.setEndereco(cliente.getEndereco());

    return dto;
}

public static Cliente toEntity(ClienteRequestDTO dto) {
    Cliente cliente = new Cliente();

    cliente.setNome(dto.getNome());
    cliente.setTelefone(dto.getTelefone());
    cliente.setEmail(dto.getEmail());
    cliente.setCep(dto.getCep());
    cliente.setEndereco(dto.getEndereco());

    return cliente;
}
