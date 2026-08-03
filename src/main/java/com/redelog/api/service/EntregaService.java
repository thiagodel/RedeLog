package com.redelog.api.service;

import com.redelog.api.dto.EntregaRequestDTO;
import com.redelog.api.dto.EntregaResponseDTO;
import com.redelog.api.dto.EnderecoRequestDTO;
import com.redelog.api.mapper.EntregaMapper;
import com.redelog.api.model.entities.*;
import com.redelog.api.model.enums.StatusEntrega;
import com.redelog.api.repository.*;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.*;

@Service
public class EntregaService {

    private final EntregaRepository entregaRepository;
    private final EntregadorRepository entregadorRepository;
    private final ClienteRepository clienteRepository;
    private final FilialRepository filialRepository;

    public EntregaService(EntregaRepository entregaRepository, EntregadorRepository entregadorRepository, ClienteRepository clienteRepository, FilialRepository filialRepository) {
        this.entregaRepository = entregaRepository;
        this.entregadorRepository = entregadorRepository;
        this.clienteRepository = clienteRepository;
        this.filialRepository = filialRepository;
    }

    public Page<EntregaResponseDTO> listarTodos(Pageable pageable) {

        return entregaRepository.findAll(pageable)
                .map(EntregaMapper::toDTO);

    }


    public EntregaResponseDTO listarPorId(Long id) {

        Entrega entrega = entregaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Entrega não encontrada"));

        return EntregaMapper.toDTO(entrega);
    }

    public void deletarPorId(Long id) {
        Entrega entrega = entregaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Entrega não encontrada com ID: " + id));
        entregaRepository.delete(entrega);
    }

    public EntregaResponseDTO salvar(EntregaRequestDTO dto) {

        Cliente cliente = clienteRepository.findById(dto.getClienteId())
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Cliente não encontrado"));

        Entregador entregador = entregadorRepository.findById(dto.getEntregadorId())
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Entregador não encontrado"));

        if (!entregador.estaAtivo()) {
            throw new ResponseStatusException(
                    CONFLICT,
                    "Não é possível atribuir uma entrega a um entregador inativo");
        }

        Filial filial = filialRepository.findById(dto.getFilialOrigemId())
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Filial não encontrada"));

        Endereco endereco = converterEndereco(dto.getEnderecoEntrega());

        if (endereco == null) {
            throw new ResponseStatusException(BAD_REQUEST, "Endereço não informado");
        }

        Entrega entrega = new Entrega();
        entrega.setCliente(cliente);
        entrega.setEnderecoEntrega(endereco);
        entrega.setFilialOrigem(filial);
        entrega.setEntregador(entregador);
        entrega.setStatus(StatusEntrega.CRIADA);
        entrega.setDataCriacao(java.time.LocalDateTime.now());
        entrega.gerarCodigoRastreio();


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

        if (!entregador.estaAtivo()) {
            throw new ResponseStatusException(
                    CONFLICT,
                    "Não é possível atribuir uma entrega a um entregador inativo");
        }

        Filial filial = filialRepository.findById(dto.getFilialOrigemId())
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Filial não encontrada"));

        Endereco endereco = converterEndereco(dto.getEnderecoEntrega());

        if (endereco == null) {
            throw new ResponseStatusException(BAD_REQUEST, "Endereço não informado");
        }


        atual.setCliente(cliente);
        atual.setEntregador(entregador);
        atual.setFilialOrigem(filial);
        atual.setEnderecoEntrega(endereco);

        Entrega atualizada = entregaRepository.save(atual);

        return EntregaMapper.toDTO(atualizada);


    }

    private Entrega buscarEntrega(Long id) {
        return entregaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        NOT_FOUND, "Entrega não encontrada"));
    }

    private Endereco converterEndereco(EnderecoRequestDTO enderecoDto) {
        if (enderecoDto == null) {
            return null;
        }

        Endereco endereco = new Endereco();
        endereco.setRua(enderecoDto.getRua());
        endereco.setNumero(enderecoDto.getNumero());
        endereco.setBairro(enderecoDto.getBairro());
        endereco.setCidade(enderecoDto.getCidade());
        endereco.setEstado(enderecoDto.getEstado());
        endereco.setCep(enderecoDto.getCep());
        endereco.setComplemento(enderecoDto.getComplemento());

        return endereco;
    }

    public EntregaResponseDTO despachar(Long id){
        Entrega entrega = buscarEntrega(id);
        entrega.despachar();

        Entrega entregaAtualizada = entregaRepository.save(entrega);

        return EntregaMapper.toDTO(entregaAtualizada);
    }

    public EntregaResponseDTO sairParaEntrega(Long id){
        Entrega entrega = buscarEntrega(id);
        entrega.sairParaEntrega();

        Entrega entregaAtualizada = entregaRepository.save(entrega);

        return EntregaMapper.toDTO(entregaAtualizada);
    }

    public EntregaResponseDTO finalizarEntrega(Long id){
        Entrega entrega = buscarEntrega(id);
        entrega.finalizarEntrega();

        Entrega entregaAtualizada = entregaRepository.save(entrega);

        return EntregaMapper.toDTO(entregaAtualizada);
    }

    public EntregaResponseDTO registrarFalha(Long id, String motivo){
        Entrega entrega = buscarEntrega(id);
        entrega.registrarFalha(motivo);

        Entrega entregaAtualizada = entregaRepository.save(entrega);

        return EntregaMapper.toDTO(entregaAtualizada);

    }



}

