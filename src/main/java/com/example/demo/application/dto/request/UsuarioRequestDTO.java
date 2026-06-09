package com.example.demo.application.dto.request;



import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;



@Schema(description = "DTO representando os dados para criação de um novo usuário")
public record UsuarioRequestDTO(


        @NotBlank(message = "É necessário informar o nome do usuário!")
        @Schema(description = "Nome do usuário", example = "Usuario 123")
        String nome,
        @NotNull(message = "É necessário informar o email do usuário!")
        @Schema(description = "Email válido e único do usuário", example = "usuario123@example.com")
        @Email(message = "E-mail inválido")
        @NotBlank(message = "É necessário informar o e-mail")
        String email,
        @Schema(description = "Senha criptografada com hash de senhas", example = "senha123")
        @NotBlank(message = "É necessário informar uma senha!")
        String senha


){}





