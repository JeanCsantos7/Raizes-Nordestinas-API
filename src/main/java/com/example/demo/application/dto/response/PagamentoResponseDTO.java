package com.example.demo.application.dto.response;

import com.example.demo.domain.enums.MetodoPagamento;
import com.example.demo.domain.enums.StatusPagamento;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PagamentoResponseDTO {

    private Long pedidoID;
    private String payload;
    private MetodoPagamento metodo;
    private LocalDateTime data;
}
