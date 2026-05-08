package com.example.demo.application.dto.response;

import com.example.demo.domain.enums.PerfilUsuario;
import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class UsuarioResponseDTO {

    private String nome;
    private String email;
    private PerfilUsuario perfil;
}
