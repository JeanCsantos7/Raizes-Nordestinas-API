package com.example.demo.infrastructure.exception;

public class PagamentoRecusado extends RuntimeException {
    public PagamentoRecusado(String message) {
        super(message);
    }
}
