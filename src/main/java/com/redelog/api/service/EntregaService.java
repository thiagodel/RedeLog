package com.redelog.api.service;

import com.redelog.api.dto.EntregaRequestDTO;
import com.redelog.api.dto.EntregaResponseDTO;
import com.redelog.api.mapper.EntregaMapper;
import com.redelog.api.model.entities.*;
import com.redelog.api.model.enums.StatusEntrega;
import com.redelog.api.repository.*;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public class EntregaService {

    private final EntregaRepository entregaRepository;
    private final EntregadorRepository entregadorRepository;
    private final ClienteRepository clienteRepository;
    private final FilialRepository filialRepository;
    private final EnderecoRepository enderecoRepository;

    public EntregaService(EntregaRepository entregaRepository, EntregadorRepository entregadorRepository, ClienteRepository clienteRepository, FilialRepository filialRepository, EnderecoRepository enderecoRepository) {
        this.entregaRepository = entregaRepository;
        this.entregadorRepository = entregadorRepository;
        this.clienteRepository = clienteRepository;
        this.filialRepository = filialRepository;
        this.enderecoRepository = enderecoRepository;
    }

    public List<EntregaResponseDTO> listarTodos() {

        return entregaRepository.findAll()
                .stream()
                .map(EntregaMapper::toDTO)
                .toList();

    }


    public EntregaResponseDTO listarPorId(Long id) {

        Entrega entrega = entregaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Entrega não encontrada"));

        return EntregaMapper.toDTO(entrega);
    }

    public void deletarPorId(Long id) {
        Entrega entrega = entregaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Entrega não encontrada com ID: " + id));
        entregaRepository.delete(entrega);
    }

    public EntregaResponseDTO salvar(EntregaRequestDTO dto) {

        Cliente cliente = clienteRepository.findById(dto.getClienteId())
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Cliente não encontrado"));

        Entregador entregador = entregadorRepository.findById(dto.getEntregadorId())
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Entregador não encontrado"));

        Filial filial = filialRepository.findById(dto.getFilialOrigemId())
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Filial não encontrada"));

        Endereco endereco = enderecoRepository.findById(dto.getEnderecoEntregaId())
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Endereço não encontrado"));

        Entrega entrega = EntregaMapper.toEntity(dto);

        entrega.gerarCodigoRastreio();
        entrega.setCliente(cliente);
        entrega.setEntregador(entregador);
        entrega.setFilialOrigem(filial);
        entrega.setEnderecoEntrega(endereco);
        entrega.setStatus(StatusEntrega.CRIADA);


        Entrega salva = entregaRepository.save(entrega);

        return EntregaMapper.toDTO(salva);

    }

    public EntregaResponseDTO atualizar(Long id, EntregaRequestDTO dto) {

        Entrega atual = entregaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Entrega não encontrada"));

        Cliente cliente = clienteRepository.findById(dto.getClienteId())
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Cliente não encontrado"));

        Entregador entregador = entregadorRepository.findById(dto.getEntregadorId())
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Entregador não encontrado"));

        Filial filial = filialRepository.findById(dto.getFilialOrigemId())
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Filial não encontrada"));

        Endereco endereco = enderecoRepository.findById(dto.getEnderecoEntregaId())
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Endereço não encontrado"));


        atual.setCliente(cliente);
        atual.setEntregador(entregador);
        atual.setFilialOrigem(filial);
        atual.setEnderecoEntrega(endereco);

        Entrega atualizada = entregaRepository.save(atual);

        return EntregaMapper.toDTO(atualizada);


    }
}
