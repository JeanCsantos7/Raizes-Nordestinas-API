package com.example.demo.infrastructure.exception;

public class ErroPedido extends RuntimeException {
    public ErroPedido(String message) {
        super(message);
    }
}
