package com.example.demo.application.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Schema(description = "DTO representando um item do pedido")
public record ItemPedidoRequestDTO(

        @NotNull(message = "O ID do produto é obrigatório")
        @Positive(message = "O ID do produto deve ser maior que zero")
        @Schema(
                description = "Identificador do produto",
                example = "1"
        )
        Long produtoID,

        @NotNull(message = "A quantidade é obrigatória")
        @Positive(message = "A quantidade deve ser maior que zero")
        @Schema(
                description = "Quantidade do produto no pedido",
                example = "2"
        )
        Integer quantidade

) {}