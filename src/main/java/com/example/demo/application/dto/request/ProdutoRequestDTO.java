package com.example.demo.application.dto.request;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;


public record ProdutoRequestDTO (
        @NotBlank(message = "É necessário informar o nome do produto!")
        String nome,

        @PositiveOrZero(message = "O valor não pode ser menor que zero!")
        BigDecimal preco


){}





