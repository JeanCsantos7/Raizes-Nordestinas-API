package com.example.demo.application.dto.request;

import jakarta.validation.constraints.NotNull;

public record ResgatarPontosRequestDTO(
        @NotNull
        Integer pontos
){}
