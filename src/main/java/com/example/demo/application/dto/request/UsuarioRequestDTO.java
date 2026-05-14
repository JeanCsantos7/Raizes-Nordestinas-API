package com.example.demo.application.dto.request;



import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;



public record UsuarioRequestDTO(

        @NotBlank(message = "É necessário informar o nome do usuário!")
        String nome,
        @Email(message = "E-mail inválido")
        @NotBlank(message = "É necessário informar o e-mail")
        String email,
        @NotBlank(message = "É necessário informar uma senha!")
        String senha


){}





