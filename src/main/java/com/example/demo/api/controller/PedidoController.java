package com.example.demo.api.controller;

import com.example.demo.application.dto.request.PedidoRequestDTO;

import com.example.demo.application.dto.response.PedidoResponseDTO;

import com.example.demo.application.dto.response.PromocaoResponseDTO;
import com.example.demo.application.service.PedidoService;
import com.example.demo.domain.enums.StatusPedido;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/pedidos")
@RequiredArgsConstructor
public class PedidoController {

    private final PedidoService pedidoService;

    @PostMapping
    public ResponseEntity<PedidoResponseDTO> save(@RequestBody @Valid PedidoRequestDTO dto) {

        return ResponseEntity.status(HttpStatus.CREATED).body(pedidoService.save(dto));
    }

    @GetMapping
    public ResponseEntity<Page<PedidoResponseDTO>> findAll(Pageable pageable) {

        return ResponseEntity.ok(pedidoService.findAll(pageable));
    }


    @GetMapping("/{id}")
    public ResponseEntity<PedidoResponseDTO> findById(@PathVariable Long id) {

        return ResponseEntity.ok(pedidoService.findById(id));
    }

    @GetMapping("/aplicarPromocao/{id}")
    public ResponseEntity<PromocaoResponseDTO> aplicarPromocao(@PathVariable Long id){

        return ResponseEntity.ok(pedidoService.aplicarPromocao(id));
    }


    @PatchMapping("/{id}")
    public ResponseEntity<PedidoResponseDTO> atualizarStatus(@RequestBody @Valid StatusPedido statusPedido, @PathVariable Long id) {
        pedidoService.atualizarStatus(statusPedido, id);
        return  ResponseEntity.noContent().build();
    }



}
