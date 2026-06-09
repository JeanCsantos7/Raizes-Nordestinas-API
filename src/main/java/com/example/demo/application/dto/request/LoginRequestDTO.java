package com.example.demo.application.dto.request;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

@Schema(description = "DTO representando autenticação de usuário")
public record LoginRequestDTO (

        @NotNull(message = "Email obrigatório")
        @Schema(description = "Email válido e único do usuário", example = "usuario123@example.com")
        String email,
        @NotNull(message = "Senha obrigatória")
        @Schema(description = "Senha criptografada com hash de senhas", example = "senha123")
        String senha
){}
