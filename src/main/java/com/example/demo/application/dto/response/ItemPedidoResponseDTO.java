package com.example.demo.application.dto.response;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ItemPedidoResponseDTO {





    private Integer quantidade;

    private BigDecimal precoUnitario;

    private BigDecimal subTotal;

}
