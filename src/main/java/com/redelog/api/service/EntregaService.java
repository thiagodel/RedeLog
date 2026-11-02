package com.redelog.api.service;

import com.redelog.api.dto.EntregaRequestDTO;
import com.redelog.api.dto.EntregaResponseDTO;
import com.redelog.api.dto.HistoricoEntregaResponseDTO;
import com.redelog.api.mapper.ClienteMapper;
import com.redelog.api.mapper.EnderecoMapper;
import com.redelog.api.mapper.EntregaMapper;
import com.redelog.api.mapper.HistoricoEntregaMapper;
import com.redelog.api.model.entities.*;
import com.redelog.api.model.enums.StatusEntrega;
import com.redelog.api.repository.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@Service
public class EntregaService {

    private final EntregaRepository entregaRepository;
    private final EntregadorRepository entregadorRepository;
    private final ClienteRepository clienteRepository;
    private final FilialRepository filialRepository;
    private final EnderecoMapper enderecoMapper;
    private final EntregaMapper entregaMapper;

    public EntregaService(EntregaRepository entregaRepository, EntregadorRepository entregadorRepository, ClienteRepository clienteRepository, FilialRepository filialRepository, EnderecoMapper enderecoMapper, EntregaMapper entregaMapper) {
        this.entregaRepository = entregaRepository;
        this.entregadorRepository = entregadorRepository;
        this.clienteRepository = clienteRepository;
        this.filialRepository = filialRepository;
        this.enderecoMapper = enderecoMapper;
        this.entregaMapper = entregaMapper;
    }

    public Page<EntregaResponseDTO> listarTodos(Pageable pageable) {

        return entregaRepository.findAll(pageable)
                .map(EntregaMapper::toDTO);

    }


    public EntregaResponseDTO listarPorId(Long id) {

        Entrega entrega = entregaRepository.findByIdOrThrow(id, "Entrega");

        return EntregaMapper.toDTO(entrega);
    }

    public EntregaResponseDTO cancelar(Long id) {
        Entrega entrega = entregaRepository.findByIdOrThrow(id, "Entrega");

        entrega.setStatus(StatusEntrega.CANCELADA);

        Entrega entregaSalva = entregaRepository.save(entrega);

        return entregaMapper.toDTO(entregaSalva);
    }

    @Transactional
    public EntregaResponseDTO salvar(EntregaRequestDTO dto) {

        Cliente cliente = resolverCliente(dto);

        Entregador entregador = entregadorRepository.findByIdOrThrow(dto.getEntregadorId(), "Entregador");

        if (!entregador.estaAtivo()) {
            throw new ResponseStatusException(
                    CONFLICT,
                    "Não é possível atribuir uma entrega a um entregador inativo");
        }

        Filial filial = filialRepository.findByIdOrThrow(dto.getFilialId(), "Filial");

        Endereco endereco = enderecoMapper.toEntity(dto.getEnderecoEntrega());

        if (endereco == null) {
            throw new ResponseStatusException(BAD_REQUEST, "Endereço não informado");
        }

        Entrega entrega = new Entrega(
                null,
                cliente,
                endereco,
                filial
        );

        entrega.setEntregador(entregador);
        entrega.gerarCodigoRastreio();


        Entrega salva = entregaRepository.save(entrega);

        return EntregaMapper.toDTO(salva);

    }

    @Transactional
    public EntregaResponseDTO atualizar(Long id, EntregaRequestDTO dto) {

        Entrega atual = entregaRepository.findByIdOrThrow(id, "Entrega");

        if (atual.getStatus() != StatusEntrega.CRIADA) {
            throw new ResponseStatusException(
                    CONFLICT,
                    "Não é possível alterar uma entrega após ela ser despachada."
            );
        }

        Cliente cliente = resolverCliente(dto);

        Entregador entregador = entregadorRepository.findByIdOrThrow(id, "Entregador");

        if (!entregador.estaAtivo()) {
            throw new ResponseStatusException(
                    CONFLICT,
                    "Não é possível atribuir uma entrega a um entregador inativo");
        }

        Filial filial = filialRepository.findByIdOrThrow(id, "Fiilial");

        Endereco endereco = enderecoMapper.toEntity(dto.getEnderecoEntrega());

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
        return entregaRepository.findByIdOrThrow(id, "Entrega");
    }

    private Cliente resolverCliente(EntregaRequestDTO dto) {
        boolean temClienteCadastrado = dto.getClienteId() != null;
        boolean temNovoCliente = dto.getCliente() != null;

        if (temClienteCadastrado == temNovoCliente) {
            throw new ResponseStatusException(
                    BAD_REQUEST,
                    "Informe exatamente um entre clienteId e cliente");
        }

        if (temClienteCadastrado) {
            return clienteRepository.findByIdOrThrow(dto.getClienteId(), "Cliente");
        }

        return clienteRepository.save(ClienteMapper.toEntity(dto.getCliente()));
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

    @Transactional(readOnly = true)
    public List<HistoricoEntregaResponseDTO> listarHistorico(Long id) {

        Entrega entrega = entregaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        NOT_FOUND,
                        "Entrega não encontrada"
                ));

        return entrega.getHistorico().stream()
                .map(HistoricoEntregaMapper::toDTO)
                .toList();
    }



}

