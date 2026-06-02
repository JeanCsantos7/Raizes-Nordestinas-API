package com.example.demo.application.dto.request;

import com.example.demo.domain.enums.MetodoPagamento;
import com.example.demo.domain.enums.StatusPagamento;

public record PagamentoRequestDTO(
        Long pedidoID,
        MetodoPagamento metodo

        ){}
