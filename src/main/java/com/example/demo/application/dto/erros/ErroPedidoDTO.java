package com.example.demo.application.dto.erros;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class ErroPedidoDTO {

    private String message;
    private Integer status;
    private LocalDateTime dateTime;

}
