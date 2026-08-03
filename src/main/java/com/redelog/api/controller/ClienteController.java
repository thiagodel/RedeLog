package com.redelog.api.controller;

import com.redelog.api.dto.ClienteRequestDTO;
import com.redelog.api.dto.ClienteResponseDTO;
import com.redelog.api.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;


@RestController
@RequestMapping("/clientes")


public class ClienteController {
    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService){this.clienteService = clienteService;}


    @GetMapping
    public ResponseEntity<Page<ClienteResponseDTO>> listarTodos(Pageable pageable){
        return ResponseEntity.ok(clienteService.listarTodos(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteResponseDTO> buscarPorId(@PathVariable long id){
        return ResponseEntity.ok(clienteService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<ClienteResponseDTO> salvar(@Valid @RequestBody ClienteRequestDTO dto){
        ClienteResponseDTO novoCliente = clienteService.salvar(dto);

        URI location = URI.create("/filiais/" + novoCliente.getId());

        return ResponseEntity
                .created(location)
                .body(novoCliente);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteResponseDTO> atualizar(@PathVariable Long id, @Valid @RequestBody ClienteRequestDTO dto){
        ClienteResponseDTO clienteAtualizado = clienteService.atualizar(id, dto);
        return ResponseEntity.ok(clienteAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPorId(@PathVariable Long id){
       clienteService.deletarPorId(id);
       return ResponseEntity.noContent().build();
    }








}
