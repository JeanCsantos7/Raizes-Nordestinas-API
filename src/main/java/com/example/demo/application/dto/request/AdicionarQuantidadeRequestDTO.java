package com.example.demo.application.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Positive;


@Schema(description = "DTO representando Adição de quantidade de itens.")
public record AdicionarQuantidadeRequestDTO(

        @Positive
        @Schema(description = "Quantidade do item", example = "40")
        Integer quantidade
){}

