package com.redelog.api.controller;

import com.redelog.api.model.entities.Entregador;
import com.redelog.api.service.EntregadorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class EntregadorController {

    private final EntregadorService entregadorService;

    public EntregadorController(EntregadorService entregadorService){
        this.entregadorService = entregadorService;
    }

    @GetMapping
    public List<Entregador> listar(){
        return entregadorService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Entregador> buscarPorId(@PathVariable Long id){
        return ResponseEntity.ok(entregadorService.listarPorId(id));
    }

    @PostMapping
    public ResponseEntity<Entregador> salvar(@RequestBody Entregador entregador){
        Entregador novoEntregador = entregadorService.salvar(entregador);
        return ResponseEntity.ok(novoEntregador);
    }


}
