package com.algaworks.algalog.controller;

import com.algaworks.algalog.domain.model.Entrega;
import com.algaworks.algalog.domain.repository.ClienteRepository;
import com.algaworks.algalog.domain.repository.EntregaRepository;
import com.algaworks.algalog.domain.service.SolicitacaoEntregaService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/entregas")

public class EntregaController {

    private EntregaRepository entregaRepository;
    private SolicitacaoEntregaService solicitacaoEntregaService;

    @GetMapping
    public List<Entrega> listar(){
        return entregaRepository.findAll();
    }

    @GetMapping("/{entregaId}")
    public ResponseEntity<Entrega> buscar (@PathVariable Long entregaId){
        return entregaRepository.findAllById(entregaId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Entrega adicioar(@Valid @RequestBody Entrega entrega){
        return solicitacaoEntregaService.solicitarEntrega(entrega);
    }

    @PutMapping("/{entregaId")
    public ResponseEntity<Entrega> atualizar(@Valid @PathVariable Long entregaId, @RequestBody Entrega entrega){
        if (!entregaRepository.existsById(entregaId)){
            return ResponseEntity.notFound().build();
        }

        entrega.setId((entregaId));
        entrega = solicitacaoEntregaService.solicitarEntrega(entrega);
        return ResponseEntity.ok(entrega);
    }

    @DeleteMapping("/{entregaId")
    public ResponseEntity<Void> remover(@PathVariable Long entregaId){
        if (!entregaRepository.existsById(entregaId)){
            return ResponseEntity.notFound().build();
        }

        solicitacaoEntregaService.excluir(entregaId);
        return ResponseEntity.noContent().build();

    }


}
