package com.example.demo.application.service;


import com.example.demo.application.dto.request.AdicionarQuantidadeRequestDTO;
import com.example.demo.application.dto.request.EstoqueRequestDTO;
import com.example.demo.application.dto.response.EstoqueResponseDTO;
import com.example.demo.application.mapper.EstoqueMapper;
import com.example.demo.domain.model.Estoque;
import com.example.demo.domain.model.Produto;
import com.example.demo.domain.model.Unidade;
import com.example.demo.infrastructure.repository.EstoqueRepository;
import com.example.demo.infrastructure.repository.ProdutoRepository;
import com.example.demo.infrastructure.repository.UnidadeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
@RequiredArgsConstructor
public class EstoqueService {

  private final EstoqueRepository estoqueRepository;
  private final EstoqueMapper estoqueMapper;
  private final ProdutoRepository produtoRepository;
  private final UnidadeRepository unidadeRepository;


   public EstoqueResponseDTO save(EstoqueRequestDTO dados){

     Produto buscaProduto = produtoRepository.findById(dados.produtoId()).orElseThrow(() -> new RuntimeException("Não foi possível localizar o produto") );
     Unidade buscaUnidade = unidadeRepository.findById(dados.unidadeId()).orElseThrow(() -> new RuntimeException("Não foi possível localizar a unidade") );
     Estoque estoque = new Estoque();

     estoque.setProduto(buscaProduto);
     estoque.setUnidade(buscaUnidade);
     estoque.setQuantidade(dados.quantidade());

     Estoque salvarItem = estoqueRepository.save(estoque);

     return estoqueMapper.toDTO(salvarItem);

   }

   public List<EstoqueResponseDTO> findAll(){
     return  estoqueMapper.listDTO(estoqueRepository.findAll());

   }

    public EstoqueResponseDTO adicionarQuantidade(Long id, AdicionarQuantidadeRequestDTO qtd ){


      Estoque buscaEstoque = estoqueRepository.findById(id).orElseThrow(() -> new RuntimeException("Não foi possível localizar o produto em estoque"));

      buscaEstoque.setQuantidade(buscaEstoque.getQuantidade() + qtd.quantidade());
       Estoque salvarItem = estoqueRepository.save(buscaEstoque);


       return estoqueMapper.toDTO(salvarItem);




    }

        public EstoqueResponseDTO removerQuantidade(Long id, AdicionarQuantidadeRequestDTO qtd){

      Estoque buscaEstoque = estoqueRepository.findById(id).orElseThrow(() -> new RuntimeException("Não foi possível localizar o produto em estoque"));

      buscaEstoque.setQuantidade(buscaEstoque.getQuantidade() - qtd.quantidade());
      Estoque salvarItem = estoqueRepository.save(buscaEstoque);


      return estoqueMapper.toDTO(salvarItem);

    }
}
