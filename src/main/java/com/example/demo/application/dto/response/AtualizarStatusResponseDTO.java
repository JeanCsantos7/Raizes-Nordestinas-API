package com.example.demo.application.dto.response;

import com.example.demo.domain.enums.StatusPedido;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AtualizarStatusResponseDTO {
    private Long id;
    private StatusPedido pedido;

}
