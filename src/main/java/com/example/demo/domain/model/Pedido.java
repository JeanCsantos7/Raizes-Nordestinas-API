package com.example.demo.domain.model;


import com.example.demo.domain.enums.CanalPedido;
import com.example.demo.domain.enums.StatusPedido;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long clienteID;

    private Long unidadeID;

    @Enumerated(EnumType.STRING)
    private StatusPedido status;

    @Enumerated(EnumType.STRING)
    private CanalPedido canalPedido;

    private BigDecimal total;

    private LocalDateTime dataPedido;


}
