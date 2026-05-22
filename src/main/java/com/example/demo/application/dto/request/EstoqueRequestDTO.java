package com.example.demo.application.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record EstoqueRequestDTO (

    @NotNull
    @Positive
    Long produtoId,
    @NotNull
    @Positive
    Long unidadeId,

    @Positive
    Integer quantidade

){}
