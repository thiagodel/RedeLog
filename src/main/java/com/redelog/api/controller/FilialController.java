package com.redelog.api.controller;

import com.redelog.api.dto.FilialRequestDTO;
import com.redelog.api.dto.FilialResponseDTO;
import com.redelog.api.service.FilialService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/filiais")
public class FilialController {

    private final FilialService filialService;

    public FilialController(FilialService filialService){ this.filialService = filialService;}

    @GetMapping
    public ResponseEntity<Page<FilialResponseDTO>> listarTodos(Pageable pageable){
        return ResponseEntity.ok(filialService.listarTodos(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<FilialResponseDTO> listarPorId(@PathVariable Long id){
        return ResponseEntity.ok(filialService.listarPorId(id));
    }

    @PostMapping
    public ResponseEntity<FilialResponseDTO> cadastrar(@Valid @RequestBody FilialRequestDTO dto){
        FilialResponseDTO novaFilial = filialService.cadastrar(dto);
        return ResponseEntity.ok(novaFilial);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FilialResponseDTO> atualizar(@PathVariable Long id, @Valid @RequestBody FilialRequestDTO dto){
        FilialResponseDTO filialAtualizada = filialService.atualizar(id, dto);
        return ResponseEntity.ok(filialAtualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPorId(@PathVariable Long id){
        filialService.deletarPorId(id);
        return ResponseEntity.noContent().build();
    }

}
