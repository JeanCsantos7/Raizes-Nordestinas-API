package com.example.demo.api.controller;

import com.example.demo.application.dto.request.PagamentoRequestDTO;
import com.example.demo.application.dto.response.EstornoResponseDTO;
import com.example.demo.application.dto.response.PagamentoResponseDTO;
import com.example.demo.application.service.PagamentoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/pagamentos")
public class PagamentoController {

    private final PagamentoService pagamentoService;

    @PostMapping("/processarPagamento")
    public ResponseEntity<PagamentoResponseDTO> processarPagamento(@Valid @RequestBody PagamentoRequestDTO dto){
      return ResponseEntity.ok(pagamentoService.processarPagamento(dto));
    }



    @GetMapping
    public ResponseEntity<List<PagamentoResponseDTO>> findAll(){
        return ResponseEntity.ok(pagamentoService.findAll());
    }

    @PatchMapping ("/estornar/{id}")
    public ResponseEntity<EstornoResponseDTO> estornar(@PathVariable  Long id){
        return ResponseEntity.ok(pagamentoService.estornar(id));
    }
}
