package com.example.demo.infrastructure.exception;

public class QuantidadeSuperiorEstoque extends RuntimeException {
    public QuantidadeSuperiorEstoque(String message) {
        super(message);
    }
}
