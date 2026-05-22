package com.example.demo.application.mapper;

import com.example.demo.application.dto.request.PedidoRequestDTO;
import com.example.demo.application.dto.response.AtualizarStatusResponseDTO;
import com.example.demo.application.dto.response.PedidoResponseDTO;
import com.example.demo.application.dto.response.PromocaoResponseDTO;
import com.example.demo.domain.model.Pedido;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PedidoMapper {


    @Mapping(source = "clientes.id", target = "clienteID")
    @Mapping(source = "unidadePedido.id", target = "unidadeID")
    @Mapping(source = "dataPedido", target = "data")



    PedidoResponseDTO toDTO(Pedido entity);
    PromocaoResponseDTO promocao(Pedido entity);

    AtualizarStatusResponseDTO atualizarStt(Pedido entity);

    List<PedidoResponseDTO> toListDTO(List<Pedido> ListEntity);

    @BeanMapping(
            nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
    )

    void update(
            PedidoRequestDTO dto,
            @MappingTarget  Pedido entity


    );
}
