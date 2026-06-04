package com.example.demo.infrastructure.exception;

public class PagamentoNaoEncontrado extends RuntimeException {
    public PagamentoNaoEncontrado(String message) {
        super(message);
    }
}
