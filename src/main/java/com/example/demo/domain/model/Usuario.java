package com.example.demo.domain.model;


import com.example.demo.domain.enums.PerfilUsuario;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Getter
@Setter
public class Usuario {




    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    private String nome;

    private String email;

    @OneToMany(mappedBy = "clientes", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Pedido> clientes = new ArrayList<>();


    private String senhaHash;

    @Enumerated(EnumType.STRING)
    private PerfilUsuario perfil;



}
