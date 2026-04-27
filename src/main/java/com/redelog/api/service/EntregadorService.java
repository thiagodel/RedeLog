package com.redelog.api.service;

import com.redelog.api.model.entities.Entregador;
import com.redelog.api.repository.EntregadorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EntregadorService {

    private final EntregadorRepository entregadorRepository;

    public EntregadorService(EntregadorRepository entregadorRepository){
        this.entregadorRepository = entregadorRepository;
    }

    public List<Entregador> listarTodos(){
        return entregadorRepository.findAll();
    }

    public Entregador listarPorId(Long id){
        return entregadorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Entregador não encontrado com id " + id));
    }

    public void desativarEntregador(Long id){
        Entregador entregador = entregadorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Não encontrado!"));

        entregador.desativar();
        entregadorRepository.save(entregador);
    }

    public void ativarEntregador(Long id){
        Entregador entregador = entregadorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Não encontrado"));

        entregador.ativar();
        entregadorRepository.save(entregador);
}

    public Entregador salvar(Entregador entregador) {
        return entregadorRepository.save(entregador);
    }



}
