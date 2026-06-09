package com.example.demo.application.dto.request;

import com.example.demo.domain.enums.MetodoPagamento;
import com.example.demo.domain.enums.StatusPagamento;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;


@Schema(description = "DTO representando processamento de pagamentos")
public record PagamentoRequestDTO(

        @NotNull(message = "O ID do pedido é obrigatório")
        @Schema(description = "ID do pedido", example = "1")
        Long pedidoID,

        @NotNull(message = "Método de pagamento é obrigatório")
        @Schema(description = "Método de pagamento", example = "PIX")
        MetodoPagamento metodo

        ){}
