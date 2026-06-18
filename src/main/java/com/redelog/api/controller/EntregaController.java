package com.redelog.api.controller;

import com.redelog.api.dto.EntregaRequestDTO;
import com.redelog.api.dto.EntregaResponseDTO;
import com.redelog.api.service.EntregaService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/entregas")
public class EntregaController {

    private final EntregaService entregaService;

    public EntregaController(EntregaService entregaService){
        this.entregaService = entregaService;
    }

    // Listar Todos
    @GetMapping
    public ResponseEntity<Page<EntregaResponseDTO>> listar(Pageable pageable) {
        return ResponseEntity.ok(entregaService.listarTodos(pageable));
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
