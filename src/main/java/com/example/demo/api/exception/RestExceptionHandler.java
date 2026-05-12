package com.example.demo.api.exception;

import com.example.demo.api.exception.dtoExceptions.UsuarioNaoEncontradoDTO;
import com.example.demo.infrastructure.exception.UsuarioNaoEncontrado;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(UsuarioNaoEncontrado.class)
    public ResponseEntity<UsuarioNaoEncontradoDTO> usuarioNaoEncontrado(UsuarioNaoEncontrado ex){

        UsuarioNaoEncontradoDTO erro = new UsuarioNaoEncontradoDTO(
                ex.getMessage(),
                404,
                LocalDateTime.now()
        );

        return ResponseEntity.status(404).body(erro);
    }

}
