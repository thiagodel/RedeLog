package com.redelog.api.service;

import com.redelog.api.dto.EntregadorRequestDTO;
import com.redelog.api.dto.EntregadorResponseDTO;
import com.redelog.api.mapper.EntregadorMapper;
import com.redelog.api.model.entities.Entregador;
import com.redelog.api.model.enums.StatusEntrega;
import com.redelog.api.repository.EntregaRepository;
import com.redelog.api.repository.EntregadorRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


import java.util.List;

import static org.springframework.http.HttpStatus.CONFLICT;


@Service
public class EntregadorService {

    private final EntregadorRepository entregadorRepository;
    private final EntregaRepository entregaRepository;



    public EntregadorService(EntregadorRepository entregadorRepository, EntregaRepository entregaRepository){
        this.entregadorRepository = entregadorRepository;
        this.entregaRepository = entregaRepository;

    }

    public Page<EntregadorResponseDTO> listarTodos(Pageable pageable){
        return entregadorRepository.findAll(pageable)
                .map(EntregadorMapper::toDto);
    }

    public EntregadorResponseDTO listarPorId(Long id){

        Entregador entregador = entregadorRepository.findByIdOrThrow(id, "Entregador");

        return EntregadorMapper.toDto(entregador);
    }

    public EntregadorResponseDTO salvar(EntregadorRequestDTO dto){

        Entregador entregador = EntregadorMapper.toEntity(dto);

        Entregador salvo = entregadorRepository.save(entregador);

        return EntregadorMapper.toDto(salvo);
    }

    public EntregadorResponseDTO atualizar(Long id, EntregadorRequestDTO dto) {

        Entregador entregador = entregadorRepository.findByIdOrThrow(id, "Entregador");

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

    public EntregadorResponseDTO ativar(Long id) {
        Entregador entregador = entregadorRepository.findByIdOrThrow(id, "Entregador");

        entregador.ativar();

        return EntregadorMapper.toDto(entregadorRepository.save(entregador));
    }

    public EntregadorResponseDTO desativar(Long id) {

       Entregador entregador = entregadorRepository.findByIdOrThrow(id, "Entregador");

        boolean possuiEntregaAtiva = entregaRepository.existsByEntregadorIdAndStatusIn(id, List.of(
                                StatusEntrega.ENVIADA,
                                StatusEntrega.EM_ROTA
                        )
                );

        if (possuiEntregaAtiva) {
            throw new ResponseStatusException(
                    CONFLICT,
                    "Não é possível desativar o entregador enquanto ele possui uma entrega ativa."
            );
        }


        entregador.desativar();

        return EntregadorMapper.toDto(entregadorRepository.save(entregador));
    }



}
