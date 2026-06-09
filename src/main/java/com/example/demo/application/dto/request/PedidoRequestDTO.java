package com.example.demo.application.dto.request;


import com.example.demo.domain.enums.CanalPedido;

import com.example.demo.domain.enums.StatusPedido;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

import java.util.List;


@Schema(description = "DTO representando novos pedidos")
public record PedidoRequestDTO(
        @NotNull(message = "O ID do cliente é obrigatório")
        @Schema(description = "ID do cliente", example = "1")
        Long clienteID,

        @NotNull(message = "O ID da unidade é obrigatório")
        @Schema(description = "ID da unidade", example = "4")
        Long unidadeID,

        @NotNull(message = "O canal do pedido é obrigatório")
        @Schema(description = "Canal de atendimento(APP, WEB, TOTEM, BALCAO)", example = "APP")
        CanalPedido canalPedido,


        @Schema(description = "Status do pedido", example = "PRONTO")
        StatusPedido status,

        @NotNull(message = "A lista de itens é obrigatória")
        List<ItemPedidoRequestDTO> itens


) {}
