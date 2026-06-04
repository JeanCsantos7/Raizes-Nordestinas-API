package com.example.demo.infrastructure.exception;

public class ErroEstorno extends RuntimeException {
    public ErroEstorno(String message) {
        super(message);
    }
}
