package com.example.demo.application.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;


@Schema(description = "DTO representando o resgate de pontos")
public record ResgatarPontosRequestDTO(
        @NotNull(message = "Valor não deve ser nulo!")
        @Schema(description = "Pontos disponíveis para resgate", example = "10(pontos)")
        Integer pontos
){}
