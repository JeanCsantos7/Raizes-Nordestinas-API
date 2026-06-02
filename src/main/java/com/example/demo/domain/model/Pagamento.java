package com.example.demo.domain.model;

import com.example.demo.domain.enums.MetodoPagamento;
import com.example.demo.domain.enums.StatusPagamento;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;



@Entity
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class Pagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

  @ManyToOne
  @JoinColumn(name= "pedido_id")
  private Pedido pedido;

    @Enumerated(EnumType.STRING)
    private StatusPagamento status;

    @Enumerated(EnumType.STRING)
    private MetodoPagamento metodo;


    @Transient
    private String payload;

    private LocalDateTime dataPagamento;
}
