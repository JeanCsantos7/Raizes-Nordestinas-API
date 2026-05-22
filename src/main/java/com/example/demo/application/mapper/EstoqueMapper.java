package com.example.demo.application.mapper;

import com.example.demo.application.dto.request.EstoqueRequestDTO;

import com.example.demo.application.dto.response.EstoqueResponseDTO;

import com.example.demo.domain.model.Estoque;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EstoqueMapper {


    EstoqueResponseDTO toDTO(Estoque entity);
    List<EstoqueResponseDTO> listDTO(List<Estoque> listEntity);

    @BeanMapping(
            nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
    )

    void update(
            EstoqueRequestDTO qtd,
            @MappingTarget Estoque entity
    );

}
