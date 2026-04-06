package com.redelog.api.controller;

import com.redelog.api.model.entities.Entrega;
import com.redelog.api.service.EntregaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class EntregaController {

    private final EntregaService entregaService;

    public EntregaController(EntregaService entregaService){
        this.entregaService = entregaService;
    }

    @GetMapping
    public List<Entrega> listar() {
        return entregaService.listarTodos();
    }

    @PostMapping
    public Entrega salvar(@RequestBody Entrega entrega){
        return entregaService.salvar(entrega);
    }

    @PutMapping("/{id}")
    public Entrega atualizar(@PathVariable Integer id, @RequestBody Entrega entrega){
        return entregaService.atualiza(id, entrega);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Integer id) {
        entregaService.deletar(id);
    }

}
