package com.example.demo.api.controller;

import com.example.demo.application.dto.request.UsuarioRequestDTO;
import com.example.demo.application.dto.response.UsuarioResponseDTO;
import com.example.demo.application.service.UsuarioService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

   private final UsuarioService usuarioService;


    @PostMapping
    public ResponseEntity<UsuarioResponseDTO> save( @RequestBody @Valid UsuarioRequestDTO dto){

        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.save(dto));
    }

    @GetMapping
    public ResponseEntity<List<UsuarioResponseDTO>> findAll(){

        return ResponseEntity.ok(usuarioService.findAll());
    }


    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> findById(@PathVariable Long id){

        return ResponseEntity.ok(usuarioService.findById(id));
    }



    @PatchMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> update(@RequestBody  UsuarioRequestDTO dto, @PathVariable Long id){
    return ResponseEntity.ok(usuarioService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable  Long id){
        usuarioService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }



}
