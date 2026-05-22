package com.example.demo.application.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class PromocaoResponseDTO {

    private Long pedidoID;
    private String statusPromocao;
    private BigDecimal totalAtualizado;
}
