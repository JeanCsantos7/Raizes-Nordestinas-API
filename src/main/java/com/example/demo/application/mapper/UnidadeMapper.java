package com.example.demo.application.mapper;

import com.example.demo.application.dto.request.UnidadeRequestDTO;
import com.example.demo.application.dto.response.UnidadeResponseDTO;
import com.example.demo.domain.model.Unidade;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;


import java.util.List;

@Mapper(componentModel = "spring")
public interface UnidadeMapper {

    Unidade toEntity(UnidadeRequestDTO dto);

    UnidadeResponseDTO toDTO(Unidade entity);

    List<UnidadeResponseDTO> listDTO(List<Unidade> listEntity);

    @BeanMapping(
            nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
    )

    void atualizarDados(
            UnidadeRequestDTO dto,
            @MappingTarget Unidade entity
    );

}
