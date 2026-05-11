package com.redelog.api.controller;

import com.redelog.api.dto.EntregaRequestDTO;
import com.redelog.api.dto.EntregaResponseDTO;
import com.redelog.api.model.entities.Entrega;
import com.redelog.api.service.EntregaService;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class EntregaController {

    private final EntregaService entregaService;

    public EntregaController(EntregaService entregaService){
        this.entregaService = entregaService;
    }

    // Listar Todos
    @GetMapping
    public ResponseEntity<List<EntregaResponseDTO>> listar() {
        return ResponseEntity.ok(entregaService.listarTodos());
    }
    
    // Listar por ID
    @GetMapping("/{id}")
    public ResponseEntity<EntregaResponseDTO> buscarPorId(@PathVariable Long id) {
    	return ResponseEntity.ok(entregaService.listarPorId(id));
    }

    // Salvar
    @PostMapping
    public ResponseEntity<EntregaResponseDTO> salvar (@RequestBody EntregaRequestDTO dto) {
    	EntregaResponseDTO novaEntrega = entregaService.salvar(dto);

        return ResponseEntity.ok(novaEntrega);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntregaResponseDTO> atualizar(@PathVariable Long id, @RequestBody EntregaRequestDTO dto){
        EntregaResponseDTO entregaAtualizada = entregaService.atualizar(id, dto);
        return ResponseEntity.ok(entregaAtualizada);
    }

    // Deletar por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        entregaService.deletarPorId(id);
        return ResponseEntity.noContent().build();
    }

}
