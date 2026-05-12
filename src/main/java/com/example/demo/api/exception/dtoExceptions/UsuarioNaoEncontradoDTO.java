package com.example.demo.api.exception.dtoExceptions;

import java.time.LocalDateTime;


public record UsuarioNaoEncontradoDTO(

        String message,
        Integer status,
        LocalDateTime data

){}


