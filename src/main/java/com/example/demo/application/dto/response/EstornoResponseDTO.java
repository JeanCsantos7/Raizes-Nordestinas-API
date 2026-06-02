package com.example.demo.application.dto.response;

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
public class EstornoResponseDTO {


    private Long pedidoID;
    private StatusPagamento statusPagamento;
    private LocalDateTime data;

}
