package com.example.demo.domain.model;

import com.example.demo.domain.enums.MetodoPagamento;
import com.example.demo.domain.enums.StatusPagamento;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Pagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long pedidoID;

    @Enumerated(EnumType.STRING)
    private StatusPagamento status;

    @Enumerated(EnumType.STRING)
    private MetodoPagamento metodo;

    private LocalDateTime dataPagamento;
}
