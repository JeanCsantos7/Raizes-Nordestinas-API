package com.example.demo.application.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;

@Schema(description = "DTO representando edição/alteração de preço do produto.")
public record AlterarPrecoRequestDTO (

        @NotNull(message = "O preço é obrigatório")
        @DecimalMin(value = "0.0", inclusive = false)
        @Schema(description = "Preço do produto", example = "100.0")
        BigDecimal preco

) {}



