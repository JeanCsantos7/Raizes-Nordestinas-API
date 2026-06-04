package com.example.demo.api.controller;

import com.example.demo.application.dto.request.AdicionarQuantidadeRequestDTO;
import com.example.demo.application.dto.request.EstoqueRequestDTO;
import com.example.demo.application.dto.response.EstoqueResponseDTO;

import com.example.demo.application.service.EstoqueService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/estoque")
@RequiredArgsConstructor
public class EstoqueController {

    private final EstoqueService estoqueService;

    @PostMapping
    public ResponseEntity<EstoqueResponseDTO> save(@RequestBody @Valid  EstoqueRequestDTO dto){

        return ResponseEntity.status(HttpStatus.CREATED).body(estoqueService.save(dto));
    }

    @GetMapping
    public ResponseEntity<Page<EstoqueResponseDTO>> findAll(Pageable pageable){

        return ResponseEntity.ok(estoqueService.findAll(pageable));
    }

    @PatchMapping("/adicionarQuantidade/{id}")
    public ResponseEntity<EstoqueResponseDTO> adicionarQuantidade(@PathVariable  Long id, @RequestBody AdicionarQuantidadeRequestDTO qtd){

        return ResponseEntity.ok(estoqueService.adicionarQuantidade(id, qtd));
    }


}
