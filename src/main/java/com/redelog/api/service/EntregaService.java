package com.redelog.api.service;

import com.redelog.api.model.entities.Entrega;
import com.redelog.api.model.entities.Entregador;
import com.redelog.api.repository.EntregaRepository;
import com.redelog.api.repository.EntregadorRepository;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class EntregaService {

    private final EntregaRepository entregaRepository;
    private final EntregadorRepository entregadorRepository;

    public EntregaService(EntregaRepository entregaRepository, EntregadorRepository entregadorRepository){
        this.entregaRepository = entregaRepository;
        this.entregadorRepository = entregadorRepository;
    }

    public List<Entrega> listarTodos(){
        return entregaRepository.findAll();
    }
    
    public Entrega listarPorId(Long id) {
    	return entregaRepository.findById(id)
    			.orElseThrow(() -> new RuntimeException("Entrega não encontrada com ID: " + id));
    }
    
    public void deletarPorId(Long id) {
    	 Entrega entrega = entregaRepository.findById(id)
    			.orElseThrow(() -> new RuntimeException("Entrega não encontrada com ID: " + id));
    	 entregaRepository.delete(entrega);
    }
    
    public Entrega salvar (Entrega entrega) {
    	return entregaRepository.save(entrega);
    }
    
    public Entrega atualizar(Long id, Entrega payload) {
        Entrega atual = entregaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Entrega não encontrada" ));

        Entregador entregador = entregadorRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Entregador não encontrado"));


        atual.atribuirEntregador(payload.getEntregador());
        return entregaRepository.save(atual);
    }



}
