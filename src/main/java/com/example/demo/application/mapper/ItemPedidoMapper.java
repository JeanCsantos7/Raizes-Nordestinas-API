package com.example.demo.application.mapper;

import com.example.demo.application.dto.request.ItemPedidoRequestDTO;
import com.example.demo.application.dto.request.PedidoRequestDTO;
import com.example.demo.application.dto.response.ItemPedidoResponseDTO;
import com.example.demo.application.dto.response.PedidoResponseDTO;
import com.example.demo.domain.model.ItemPedido;
import com.example.demo.domain.model.Pedido;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ItemPedidoMapper {

    @Mapping(source = "subTotal", target = "subTotal")





    ItemPedidoResponseDTO toDTO(ItemPedido entity);
    List<ItemPedidoResponseDTO> toListDTO(List<ItemPedido> ListEntity);
    @BeanMapping(
            nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
    )

    void update(
            ItemPedidoRequestDTO dto,
            @MappingTarget ItemPedido entity


    );


}
