package com.example.demo.application.dto.response;

import com.example.demo.domain.enums.CanalPedido;
import com.example.demo.domain.enums.StatusPedido;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PedidoResponseDTO {

    private Long id;
    private Long clienteID;
    private Long unidadeID;
    private StatusPedido status;
    private CanalPedido canalPedido;
    private BigDecimal total;
    private LocalDateTime data;
    private List<ItemPedidoResponseDTO> itens;



}
