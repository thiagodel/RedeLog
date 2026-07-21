package com.redelog.api.controller;

import com.redelog.api.dto.EntregadorRequestDTO;
import com.redelog.api.dto.EntregadorResponseDTO;
import com.redelog.api.service.EntregadorService;
import jakarta.validation.Valid;
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

    @GetMapping ("/{id}")
    public ResponseEntity<EntregadorResponseDTO> listarPorId(@PathVariable Long id){
        return ResponseEntity.ok(entregadorService.listarPorId(id));
    }

    @PostMapping
    public ResponseEntity<EntregadorResponseDTO> salvar(@Valid @RequestBody EntregadorRequestDTO dto){
        EntregadorResponseDTO novoEntregador = entregadorService.salvar(dto);

        return ResponseEntity.ok(novoEntregador);
    }

    @PutMapping ("/{id}")
    public ResponseEntity<EntregadorResponseDTO> atualizar(@PathVariable Long id, @Valid @RequestBody EntregadorRequestDTO dto){
        EntregadorResponseDTO entregadorAtualizado = entregadorService.atualizar(id, dto);

        return ResponseEntity.ok(entregadorAtualizado);
    }

    @DeleteMapping ("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id){
        entregadorService.deletarPorId(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/ativar")
    public ResponseEntity<EntregadorResponseDTO> ativar(@PathVariable Long id){
        return ResponseEntity.ok(entregadorService.ativar(id));
    }

    @PatchMapping("/{id}/desativar")
    public ResponseEntity<EntregadorResponseDTO> desativar(@PathVariable Long id){
        return ResponseEntity.ok(entregadorService.desativar(id));
    }

}
