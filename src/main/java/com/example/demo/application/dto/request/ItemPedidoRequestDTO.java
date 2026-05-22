package com.example.demo.application.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record ItemPedidoRequestDTO(

        @NotNull
        Long produtoID,
        @Positive
        Integer quantidade

){}



