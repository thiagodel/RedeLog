package com.redelog.api.controller;

import com.redelog.api.dto.ClienteRequestDTO;
import com.redelog.api.dto.ClienteResponseDTO;
import com.redelog.api.service.ClienteService;
import jakarta.validation.Valid;
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
    public ResponseEntity<Page<ClienteResponseDTO>>listarTodos(Pageable pageable){
        return ResponseEntity.ok(clienteService.listarTodos(Pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteResponseDTO>buscarPorId(@PathVariable long id){
        return ResponseEntity.ok(clienteService.buscarPorId(long id));
    }

    @PostMapping
    public ResponseEntity<ClienteResponseDTO>novoCadastro(@RequestBody ClienteRequestDTO clienteRequestDTO){
        ClienteResponseDTO novoCadastro = clienteService.novoCadastro(DTO);
        return ResponseEntity.ok(novoCadastro);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteResponseDTO>atualizar(@PathVariable long id, @Valid @RequestBody ClienteRequestDTO dto){
        ClienteRequestDTO novoCliente = ClienteService.atualizar(dto, id)
        return ResponseEntity.ok(clienteAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void>Deletar(@PathVariable long id){
        ClienteService.deletarPorId(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/recebido")
    public ResponseEntity<ClienteResponseDTO>clienteRecebeuEncomenda(@PathVariable long id, @RequestBody ClienteRequestDTO dto){
        return ResponseEntity.ok(clienteRecebeuEncomenda);
    }

    @PatchMapping"/{id}/naoRecebido")
    public ResponseEntity<ClienteResponseDTO>clienteNaoRecebeuEncomenda(@PathVariable long id, @RequestBody ClienteRequestDTO dto){
        return ResponseEntity.ok(clienteNaoRecebeuEncomenda);
    }
}
