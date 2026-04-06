package com.redelog.api.service;

import com.redelog.api.model.entities.Entrega;
import com.redelog.api.model.entities.Entregador;
import com.redelog.api.repository.EntregaRepository;
import com.redelog.api.repository.EntregadorRepository;

import java.util.List;

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


}
