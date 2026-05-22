package com.example.demo.application.mapper;

import com.example.demo.application.dto.request.PedidoRequestDTO;
import com.example.demo.application.dto.response.AtualizarStatusResponseDTO;
import com.example.demo.application.dto.response.PedidoResponseDTO;
import com.example.demo.application.dto.response.PromocaoResponseDTO;
import com.example.demo.domain.model.Pedido;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PromocaoMapper {



    @Mapping(source = "total", target = "totalAtualizado")
    @Mapping(source = "id", target = "pedidoID")
    @Mapping(source = "statusPromocao", target = "statusPromocao")



    PromocaoResponseDTO promocao(Pedido entity);


    @BeanMapping(
            nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
    )

    void update(
            PedidoRequestDTO dto,
            @MappingTarget  Pedido entity


    );
}
