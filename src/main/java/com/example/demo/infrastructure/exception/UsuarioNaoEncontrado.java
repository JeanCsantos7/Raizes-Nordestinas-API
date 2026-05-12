package com.example.demo.infrastructure.exception;

public class UsuarioNaoEncontrado extends RuntimeException {
    public UsuarioNaoEncontrado(String message) {
        super(message);
    }
}
