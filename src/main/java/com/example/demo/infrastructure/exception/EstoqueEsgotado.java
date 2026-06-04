package com.example.demo.infrastructure.exception;

public class EstoqueEsgotado extends RuntimeException {
    public EstoqueEsgotado(String message) {
        super(message);
    }
}
