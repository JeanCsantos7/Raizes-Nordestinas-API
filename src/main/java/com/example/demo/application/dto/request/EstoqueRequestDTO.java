package com.example.demo.application.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Schema(description = "DTO representando gerenciamento de estoque")
public record EstoqueRequestDTO (

    @NotNull
    @Positive
    @Schema(description = "ID do produto", example = "1")
    Long produtoId,
    @NotNull
    @Schema(description = "ID da unidade", example = "4")
    Long unidadeId,
    @Positive(message = "Quantidade deve ser maior que zero")
    @Schema(description = "Quantidade disponível", example = "10")
    Integer quantidade

){}
