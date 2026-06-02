package com.example.demo.application.mapper;

import com.example.demo.application.dto.response.EstornoResponseDTO;
import com.example.demo.application.dto.response.PagamentoResponseDTO;
import com.example.demo.domain.model.Pagamento;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PagamentoMapper {

    @Mapping(source = "pedido.id", target = "pedidoID")
    @Mapping(source = "dataPagamento", target = "data")
    PagamentoResponseDTO toDTO(Pagamento entity);

    @Mapping(source = "pedido.id", target = "pedidoID")
    @Mapping(source = "status", target = "statusPagamento")
    @Mapping(source = "dataPagamento", target = "data")
    EstornoResponseDTO estornoDTO(Pagamento entity);

    List<PagamentoResponseDTO> toListDTO(List<Pagamento> listEntity);

}
