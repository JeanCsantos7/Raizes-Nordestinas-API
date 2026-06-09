package com.example.demo.application.mapper;


import com.example.demo.application.dto.request.ResgatarPontosRequestDTO;
import com.example.demo.application.dto.request.UsuarioRequestDTO;
import com.example.demo.application.dto.response.FidelidadeResponseDTO;
import com.example.demo.application.dto.response.UsuarioResponseDTO;
import com.example.demo.domain.model.Usuario;
import java.util.List;

import org.mapstruct.*;


@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    Usuario toEntity(UsuarioRequestDTO dto);
    UsuarioResponseDTO toDTO(Usuario entity);
    @Mapping(source = "pontos", target = "pontosRestantes")
    FidelidadeResponseDTO fidelidadeDTO(Usuario entity);
    List<UsuarioResponseDTO> toListDTO(List<Usuario> listEntity);
    @BeanMapping(
            nullValuePropertyMappingStrategy
                    = NullValuePropertyMappingStrategy.IGNORE
    )


    void update(

            UsuarioRequestDTO dto,
            @MappingTarget Usuario entity
    );




}
