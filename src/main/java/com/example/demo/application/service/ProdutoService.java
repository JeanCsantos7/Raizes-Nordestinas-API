package com.example.demo.application.service;

import com.example.demo.application.dto.request.ProdutoRequestDTO;
import com.example.demo.application.dto.response.ProdutoResponseDTO;
import com.example.demo.application.mapper.ProdutoMapper;
import com.example.demo.application.projection.ProdutoProjection;
import com.example.demo.domain.model.Produto;

import com.example.demo.infrastructure.exception.ProdutoNaoEncontrado;
import com.example.demo.infrastructure.repository.ProdutoRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    public Page<ProdutoResponseDTO> findAll(Pageable pageable){


       Page<Produto> produtos = produtoRepository.findAll(pageable);
       return produtos.map(produtoMapper::toDTO);



    }

    public ProdutoResponseDTO findById(Long id){

        Produto buscaProduto = produtoRepository.findById(id).orElseThrow(() -> new ProdutoNaoEncontrado("Produto não localizado"));

        return produtoMapper.toDTO(buscaProduto);


    }

    public List<ProdutoResponseDTO> findByUnidade(Long id){
      List<ProdutoProjection> buscaUnidade = produtoRepository.findByUnidade(id);

        return buscaUnidade.stream()
                .map(produtoMapper::projectionToDTO)
                .toList();
    }


    public ProdutoResponseDTO update(ProdutoRequestDTO dados, Long id){

        Produto buscaProduto = produtoRepository.findById(id).orElseThrow(() -> new ProdutoNaoEncontrado("Produto não localizado"));
      produtoMapper.update(dados, buscaProduto);
      Produto produtoSalvo = produtoRepository.save(buscaProduto);

      return produtoMapper.toDTO(produtoSalvo);

    }

    public void delete(Long id){
        produtoRepository.deleteById(id);
    }

    public ProdutoResponseDTO alterarPreco(Long id, BigDecimal preco) {

        Produto buscaProduto = produtoRepository.findById(id).orElseThrow(() -> new ProdutoNaoEncontrado("Produto não localizado"));
        buscaProduto.setPreco(preco);
        Produto produtoSalvo = produtoRepository.save(buscaProduto);

        return produtoMapper.toDTO(produtoSalvo);


        }
}




