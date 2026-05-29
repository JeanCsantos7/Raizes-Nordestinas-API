package com.example.demo.application.dto.request;


import com.example.demo.domain.enums.CanalPedido;

import com.example.demo.domain.enums.StatusPedido;
import jakarta.validation.constraints.NotNull;

import java.util.List;


public record PedidoRequestDTO(
        @NotNull
        Long clienteID,
        @NotNull
        Long unidadeID,

        CanalPedido canalPedido,
        StatusPedido status,

        @NotNull
        List<ItemPedidoRequestDTO> itens

) {}
