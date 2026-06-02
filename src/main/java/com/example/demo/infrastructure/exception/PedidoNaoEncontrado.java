package com.example.demo.infrastructure.exception;

public class PedidoNaoEncontrado extends RuntimeException {
    public PedidoNaoEncontrado(String message) {
        super(message);
    }
}
