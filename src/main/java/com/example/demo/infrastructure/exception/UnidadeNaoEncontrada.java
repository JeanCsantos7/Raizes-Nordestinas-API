package com.example.demo.infrastructure.exception;

public class UnidadeNaoEncontrada extends RuntimeException {
    public UnidadeNaoEncontrada(String message) {
        super(message);
    }
}
