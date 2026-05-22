package com.example.demo.application.dto.request;

import jakarta.validation.constraints.Positive;

public record AdicionarQuantidadeRequestDTO(

        @Positive
        Integer quantidade
){}

