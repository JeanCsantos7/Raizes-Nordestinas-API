package com.example.demo.application.mapper;

import com.example.demo.application.dto.request.ProdutoRequestDTO;
import com.example.demo.application.dto.response.ProdutoResponseDTO;
import com.example.demo.domain.model.Produto;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProdutoMapper {

     Produto toEntity(ProdutoRequestDTO dto);
     ProdutoResponseDTO toDTO(Produto entity);
     List<ProdutoResponseDTO> listDTO(List<Produto> listEntity);

     @BeanMapping(
             nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
     )

     void update(
             ProdutoRequestDTO dto,
             @MappingTarget Produto entity
     );

}
