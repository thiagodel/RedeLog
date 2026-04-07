package com.redelog.api.controller;

import com.redelog.api.model.entities.Entrega;
import com.redelog.api.service.EntregaService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class EntregaController {

    private final EntregaService entregaService;

    public EntregaController(EntregaService entregaService){
        this.entregaService = entregaService;
    }

    // Listar Todos
    @GetMapping
    public List<Entrega> listar() {
        return entregaService.listarTodos();
    }
    
    // Listar por ID
    @GetMapping("/{id}")
    public ResponseEntity<Entrega> buscarPorId(@PathVariable Long id) {
    	return ResponseEntity.ok(entregaService.listarPorId(id));
    }

    // Salvar
    @PostMapping
    public ResponseEntity<Entrega> salvar (@RequestBody Entrega entrega) {
    	Entrega novaEntrega = entregaService.salvar(entrega);
    	return ResponseEntity.ok(novaEntrega);
    }

    @PutMapping("/{id}")
    public Entrega atualizar(@PathVariable Integer id, @RequestBody Entrega entrega){
        return entregaService.atualiza(id, entrega);
    }

    // Deletar por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletar(@PathVariable Long id) {
        entregaService.deletarPorId(id);
		return ResponseEntity.ok("Entrega deletada com sucesso");
    }

}
