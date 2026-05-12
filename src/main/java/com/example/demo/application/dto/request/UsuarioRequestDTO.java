package com.example.demo.application.dto.request;


import com.example.demo.domain.enums.PerfilUsuario;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


public record UsuarioRequestDTO(

        @NotBlank(message = "É necessário informar o nome do usuário!")
        String nome,
        @Email(message = "E-mail inválido")
        @NotBlank(message = "É necessário informar o e-mail")
        String email,
        @NotBlank(message = "É necessário informar uma senha!")
        String senha


){}





