package com.example.demo.application.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;

@Schema(description = "DTO representando o cadastro de novos produtos")
public record ProdutoRequestDTO (
        @NotBlank(message = "É necessário informar o nome do produto!")
        @Schema(description = "Nome do produto", example = "X-Tudo")
        String nome,

        @PositiveOrZero(message = "O valor não pode ser menor que zero!")
        @Schema(description = "Valor do produto, o valor deve ser maior que zero!", example = "12.50")
        BigDecimal preco




){}





