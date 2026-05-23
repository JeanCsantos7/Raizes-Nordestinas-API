package com.example.demo.application.service;

import com.example.demo.application.dto.request.ProdutoRequestDTO;
import com.example.demo.application.dto.response.ProdutoResponseDTO;
import com.example.demo.application.mapper.ProdutoMapper;
import com.example.demo.application.projection.ProdutoProjection;
import com.example.demo.domain.model.Produto;
import com.example.demo.domain.model.Unidade;
import com.example.demo.infrastructure.repository.ProdutoRepository;
import com.example.demo.infrastructure.repository.UnidadeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProdutoService {

    private final ProdutoMapper produtoMapper;
    private final ProdutoRepository produtoRepository;


    public ProdutoResponseDTO save(ProdutoRequestDTO dados){
       Produto toEntity = produtoMapper.toEntity(dados);

      Produto salvarProduto = produtoRepository.save(toEntity);

      return produtoMapper.toDTO(salvarProduto);




    }

    public List<ProdutoResponseDTO> findAll(){


       List<ProdutoResponseDTO>  dto = produtoMapper.listDTO(produtoRepository.findAll());
       return dto;

    }

    public ProdutoResponseDTO findById(Long id){

        Produto buscaProduto = produtoRepository.findById(id).orElseThrow(() -> new RuntimeException("Produto não localizado"));

        return produtoMapper.toDTO(buscaProduto);


    }

    public List<ProdutoResponseDTO> findByUnidade(Long id){
      List<ProdutoProjection> buscaUnidade = produtoRepository.findByUnidade(id);

        return buscaUnidade.stream()
                .map(produtoMapper::projectionToDTO)
                .toList();
    }


    public ProdutoResponseDTO update(ProdutoRequestDTO dados, Long id){

        Produto buscaProduto = produtoRepository.findById(id).orElseThrow(() -> new RuntimeException("Produto não localizado"));
      produtoMapper.update(dados, buscaProduto);
      Produto produtoSalvo = produtoRepository.save(buscaProduto);

      return produtoMapper.toDTO(produtoSalvo);

    }

    public void delete(Long id){
        produtoRepository.deleteById(id);
    }

    public ProdutoResponseDTO alterarPreco(Long id, BigDecimal preco) {

        Produto buscaProduto = produtoRepository.findById(id).orElseThrow(() -> new RuntimeException("Produto não localizado"));
        buscaProduto.setPreco(preco);
        Produto produtoSalvo = produtoRepository.save(buscaProduto);

        return produtoMapper.toDTO(produtoSalvo);


        }
}




