package com.example.demo.domain.model;


import com.example.demo.domain.enums.CanalPedido;
import com.example.demo.domain.enums.StatusPedido;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name= "cliente_id")
    private Usuario clientes;

    @ManyToOne
    @JoinColumn(name = "unidade_id")
    private Unidade unidadePedido;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ItemPedido> itens = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private StatusPedido status;

    @Enumerated(EnumType.STRING)
    private CanalPedido canalPedido;



    private BigDecimal total;

    @Transient
    private String statusPromocao;



    private LocalDateTime dataPedido;


}
