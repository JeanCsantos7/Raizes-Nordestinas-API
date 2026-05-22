package com.example.demo.application.dto.request;


import com.example.demo.domain.enums.CanalPedido;

import jakarta.validation.constraints.NotNull;

import java.util.List;


public record PedidoRequestDTO(
        @NotNull
        Long clienteID,
        @NotNull
        Long unidadeID,

        CanalPedido canalPedido,

        @NotNull
        List<ItemPedidoRequestDTO> itens

) {}
