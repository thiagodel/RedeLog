package com.redelog.api.controller;

import com.redelog.api.dto.EntregaRequestDTO;
import com.redelog.api.dto.EntregaResponseDTO;
import com.redelog.api.dto.HistoricoEntregaResponseDTO;
import com.redelog.api.service.EntregaService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@Validated
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
    public ResponseEntity<EntregaResponseDTO> salvar (@Valid @RequestBody EntregaRequestDTO dto) {
    	EntregaResponseDTO novaEntrega = entregaService.salvar(dto);

        URI location = URI.create("/entregas/" + novaEntrega.getId());

        return ResponseEntity
                .created(location)
                .body(novaEntrega);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntregaResponseDTO> atualizar(@PathVariable Long id,@Valid @RequestBody EntregaRequestDTO dto){
        EntregaResponseDTO entregaAtualizada = entregaService.atualizar(id, dto);
        return ResponseEntity.ok(entregaAtualizada);
    }

    // Deletar por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        entregaService.deletarPorId(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/despachar")
    public ResponseEntity<EntregaResponseDTO> despachar(@PathVariable Long id){
        return ResponseEntity.ok(entregaService.despachar(id));
    }

    @PatchMapping("/{id}/sairParaEntrega")
    public ResponseEntity<EntregaResponseDTO> sairParaEntrega(@PathVariable Long id){
        return ResponseEntity.ok(entregaService.sairParaEntrega(id));
    }

    @PatchMapping("/{id}/finalizarEntrega")
    public ResponseEntity<EntregaResponseDTO> finalizaEntrega(@PathVariable Long id){
        return ResponseEntity.ok(entregaService.finalizarEntrega(id));
    }

    @PatchMapping("/{id}/registrarFalha")
    public ResponseEntity<EntregaResponseDTO> registrarFalaha(@PathVariable Long id, @RequestParam @NotBlank String motivo){
        EntregaResponseDTO dto = entregaService.registrarFalha(id, motivo);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/{id}/historico")
    public List<HistoricoEntregaResponseDTO> listarHistorico(@PathVariable Long id) {
        return entregaService.listarHistorico(id);
    }

}
