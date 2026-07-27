package com.redelog.api.controller;

import com.redelog.api.dto.ClienteRequestDTO;
import com.redelog.api.dto.ClienteResponseDTO;
import org.hibernate.query.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;

@RestController
@RequestMapping("/clientes")


public class ClienteController {
    private final ClienteController clienteController;

    public ClienteController(ClienteController clienteController) {this.clienteController = clienteController;}


    @GetMapping
    public ResponseEntity<Page<ClienteResponseDTO>>lista(Pageable pageable){
        return ResponseEntity.ok(clienteService.listarTodos(Pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteResponseDTO>listarPorId(@PathVariable long id){
        return ResponseEntity.ok(clienteService.listarPorId(long id));
    }

    @PostMapping
    public ResponseEntity<ClienteResponseDTO>Cadastrar(@RequestBody ClienteRequestDTO clienteRequestDTO){
        ClienteResponseDTO novoCadastro = clienteService.cadastro(DTO);
        return ResponseEntity.ok(novoCadastro);
    }
}
