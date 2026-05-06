package com.example.demo.domain.model;


import com.example.demo.domain.enums.PerfilUsuario;
import jakarta.persistence.*;

@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String email;


    private String senhaHash;

    @Enumerated(EnumType.STRING)
    private PerfilUsuario perfil;




}
