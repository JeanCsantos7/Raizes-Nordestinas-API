package com.example.demo.application.dto.request;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UnidadeRequestDTO(

        @NotBlank(message = "É obrigatório o preenchimento do endereço!")

    String nome,

    @NotBlank(message = "É obrigatório o preenchimento do endereço!")

    String endereco

){}
