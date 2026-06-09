package com.example.demo.application.dto.request;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


@Schema(description = "DTO representando os dados para criação de uma nova unidade")
public record UnidadeRequestDTO(

        @NotBlank(message = "É obrigatório o preenchimento do endereço!")
        @Schema(description = "Nome da unidade", example = "Unidade Moema")
    String nome,

    @NotBlank(message = "É obrigatório o preenchimento do endereço!")
    @Schema(description = "Endereço da unidade cadastrada", example = "Rua exemplo, 42 - Bairro Exemplo - São Paulo")
    String endereco

){}
