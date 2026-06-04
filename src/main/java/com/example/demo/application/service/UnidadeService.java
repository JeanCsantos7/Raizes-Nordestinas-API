package com.example.demo.application.service;

import com.example.demo.application.dto.request.UnidadeRequestDTO;
import com.example.demo.application.dto.response.UnidadeResponseDTO;
import com.example.demo.application.mapper.UnidadeMapper;
import com.example.demo.domain.model.Unidade;

import com.example.demo.infrastructure.exception.UnidadeNaoEncontrada;
import com.example.demo.infrastructure.repository.UnidadeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;



@Service
@RequiredArgsConstructor
public class UnidadeService {

     private final UnidadeMapper unidadeMapper;
     private final UnidadeRepository unidadeRepository;


     public UnidadeResponseDTO save(UnidadeRequestDTO dados){
         Unidade toEntity = unidadeMapper.toEntity(dados);
         Unidade unidadeSalva = unidadeRepository.save(toEntity);

         return unidadeMapper.toDTO(unidadeSalva);

     }

     public Page<UnidadeResponseDTO> findAll(Pageable pageable){
         Page<Unidade> unidade = unidadeRepository.findAll(pageable);
         return unidade.map(unidadeMapper::toDTO);

     }



    public UnidadeResponseDTO findById(Long id){

         Unidade buscaUnidade = unidadeRepository.findById(id).orElseThrow(() -> new UnidadeNaoEncontrada("Unidade não encontrada!"));
         return unidadeMapper.toDTO(buscaUnidade);

    }

    public UnidadeResponseDTO atualizarDados(Long id, UnidadeRequestDTO dados){
      Unidade buscaUsuario = unidadeRepository.findById(id).orElseThrow(() -> new UnidadeNaoEncontrada("Unidade não encontrada!"));
      unidadeMapper.atualizarDados(dados, buscaUsuario);
      Unidade unidadeSalva = unidadeRepository.save(buscaUsuario);

      return unidadeMapper.toDTO(unidadeSalva);
    }

    public void delete(Long id){

          unidadeRepository.deleteById(id);

    }
}
