package com.example.demo.infrastructure.exception;

public class UsuarioNaoCadastrado extends RuntimeException {
    public UsuarioNaoCadastrado(String message) {
        super(message);
    }
}
