package com.example.demo.api.controller;


import com.example.demo.application.dto.request.AlterarPrecoRequestDTO;
import com.example.demo.application.dto.request.ProdutoRequestDTO;
import com.example.demo.application.dto.response.ProdutoResponseDTO;
import com.example.demo.application.service.ProdutoService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/produtos")
public class ProdutoController {

    private final ProdutoService produtoService;


    @PostMapping
    public ResponseEntity<ProdutoResponseDTO> save(@RequestBody @Valid ProdutoRequestDTO dto){

        return ResponseEntity.status(HttpStatus.CREATED).body(produtoService.save(dto));
    }

    @GetMapping
    public ResponseEntity<List<ProdutoResponseDTO>> findAll(){

        return ResponseEntity.ok(produtoService.findAll());
    }


    @GetMapping("/{id}")
    public ResponseEntity<ProdutoResponseDTO> findById(@PathVariable Long id){

        return ResponseEntity.ok(produtoService.findById(id));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ProdutoResponseDTO> update(@RequestBody @Valid ProdutoRequestDTO dto, @PathVariable Long id){
        return ResponseEntity.ok(produtoService.update(dto, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable  Long id){
        produtoService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PatchMapping ("/{id}/preco")
    public ResponseEntity<ProdutoResponseDTO> alterarPreco(@PathVariable  Long id, @RequestBody @Valid AlterarPrecoRequestDTO preco){
        produtoService.alterarPreco(id, preco.preco() );
        return ResponseEntity.noContent().build();
    }

}
