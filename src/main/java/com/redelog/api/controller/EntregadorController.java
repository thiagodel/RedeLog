package com.redelog.api.controller;

import com.redelog.api.dto.EntregadorResponseDTO;
import com.redelog.api.model.entities.Entregador;
import com.redelog.api.service.EntregadorService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/entregadores")
public class EntregadorController {

    private final EntregadorService entregadorService;

    public EntregadorController(EntregadorService entregadorService){
        this.entregadorService = entregadorService;
    }

    @GetMapping
    public ResponseEntity<Page<EntregadorResponseDTO>> listartodos(Pageable pageable){
        return ResponseEntity.ok(entregadorService.listarTodos(pageable));
    }

    @GetMapping
    public ResponseEntity<EntregadorResponseDTO> listarPorId(@PathVariable Long id){
        return ResponseEntity.ok(entregadorService.listarPorId(id));
    }

    @PostMapping
    public ResponseEntity<EntregadorResponseDTO> salvar(@RequestBody )

}
