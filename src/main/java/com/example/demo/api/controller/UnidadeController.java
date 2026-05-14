package com.example.demo.api.controller;

import com.example.demo.application.dto.request.UnidadeRequestDTO;
import com.example.demo.application.dto.response.UnidadeResponseDTO;
import com.example.demo.application.service.UnidadeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/unidades")
@RequiredArgsConstructor

public class UnidadeController {

    private final UnidadeService unidadeService;

    @PostMapping
    public ResponseEntity<UnidadeResponseDTO> save(@RequestBody @Valid UnidadeRequestDTO dto) {

        return ResponseEntity.status(HttpStatus.CREATED).body(unidadeService.save(dto));
    }

    @GetMapping
    public ResponseEntity<List<UnidadeResponseDTO>> findAll() {

        return ResponseEntity.ok(unidadeService.findAll());
    }


    @GetMapping("/{id}")
    public ResponseEntity<UnidadeResponseDTO> findById(@PathVariable Long id) {

        return ResponseEntity.ok(unidadeService.findById(id));
    }


    @PatchMapping("/{id}")
    public ResponseEntity<UnidadeResponseDTO> update(@RequestBody @Valid UnidadeRequestDTO dto, @PathVariable Long id) {
        return ResponseEntity.ok(unidadeService.atualizarDados(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        unidadeService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }

}
