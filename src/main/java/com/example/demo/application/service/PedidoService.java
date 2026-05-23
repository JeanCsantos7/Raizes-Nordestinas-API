package com.example.demo.application.service;


import com.example.demo.application.dto.request.ItemPedidoRequestDTO;
import com.example.demo.application.dto.request.PedidoRequestDTO;
import com.example.demo.application.dto.response.AtualizarStatusResponseDTO;
import com.example.demo.application.dto.response.PedidoResponseDTO;
import com.example.demo.application.dto.response.PromocaoResponseDTO;
import com.example.demo.application.mapper.PedidoMapper;
import com.example.demo.application.mapper.PromocaoMapper;
import com.example.demo.domain.enums.StatusPedido;
import com.example.demo.domain.model.*;
import com.example.demo.infrastructure.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PedidoService {

    private final UsuarioRepository usuarioRepository;
    private final UnidadeRepository unidadeRepository;
    private final PedidoRepository pedidoRepository;
    private final ProdutoRepository produtoRepository;
    private final EstoqueRepository estoqueRepository;
    private final PedidoMapper pedidoMapper;
    private final PromocaoMapper promocaoMapper;

    public PedidoResponseDTO save(PedidoRequestDTO dados){

        Usuario buscaCliente = usuarioRepository.findById(dados.clienteID()).orElseThrow(() -> new RuntimeException("Usuario não encontrado!"));
        Unidade buscaUnidade = unidadeRepository.findById(dados.unidadeID()).orElseThrow(() -> new RuntimeException("Unidade não encontrada!"));
        Pedido pedido = new Pedido();
        pedido.setClientes(buscaCliente);
        pedido.setUnidadePedido(buscaUnidade);
        pedido.setStatus(StatusPedido.AGUARDANDO);
        pedido.setCanalPedido(dados.canalPedido());
        pedido.setDataPedido(LocalDateTime.now());


        List<ItemPedido> itens = new ArrayList<>();
        BigDecimal total = BigDecimal.ZERO;


        for(ItemPedidoRequestDTO itemDTO : dados.itens()){
            Produto produto = produtoRepository.findById(itemDTO.produtoID()).orElseThrow(() -> new RuntimeException("Não foi possível localizar o produto!"));
            ItemPedido novoItem = new ItemPedido();
            novoItem.setPedido(pedido);
            novoItem.setProduto(produto);
            novoItem.setQuantidade(itemDTO.quantidade());
            novoItem.setPrecoUnitario(produto.getPreco());

            BigDecimal subTotal = produto.getPreco().multiply(BigDecimal.valueOf(itemDTO.quantidade()));



            Estoque produtoEstoque = estoqueRepository.findByProdutoId(itemDTO.produtoID());

           produtoEstoque.setQuantidade(produtoEstoque.getQuantidade() - itemDTO.quantidade());

            estoqueRepository.save(produtoEstoque);





           total = total.add(subTotal);


           itens.add(novoItem);



        }






       pedido.setItens(itens);
        pedido.setTotal(total);




        Pedido salvarPedido = pedidoRepository.save(pedido);

        return pedidoMapper.toDTO(salvarPedido);


    }

    public Page<PedidoResponseDTO> findAll(Pageable pageable){

        Page<Pedido> pedido = pedidoRepository.findAll(pageable);
       return pedido.map(pedidoMapper::toDTO);
    }

    public PedidoResponseDTO findById(Long id){

        Pedido buscaProduto = pedidoRepository.findById(id).orElseThrow(() -> new RuntimeException("Produto não localizado"));

        return pedidoMapper.toDTO(buscaProduto);


    }



    public AtualizarStatusResponseDTO atualizarStatus(StatusPedido status, Long id){
        Pedido buscaProduto = pedidoRepository.findById(id).orElseThrow(() -> new RuntimeException("Pedido não localizado"));
        buscaProduto.setStatus(status);
        Pedido salvarAlteracao = pedidoRepository.save(buscaProduto);

        return pedidoMapper.atualizarStt(salvarAlteracao);


    }

    public PromocaoResponseDTO aplicarPromocao(Long pedidoId){

        Pedido buscaProduto = pedidoRepository.findById(pedidoId).orElseThrow(() -> new RuntimeException("Pedido não localizado"));
        BigDecimal valorPromocao = new BigDecimal("150.0");

        BigDecimal desconto = buscaProduto.getTotal().multiply(BigDecimal.valueOf(6)).divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP);


        if(buscaProduto.getTotal().compareTo(valorPromocao) >= 0){
         buscaProduto.setTotal(buscaProduto.getTotal().subtract(desconto));
         buscaProduto.setStatusPromocao("Promoção Aplicada");
        }

        else{

            buscaProduto.setStatusPromocao("Seu pedido não atingiu o valor mínimo para o desconto!");
        }

        Pedido salvarPedido = pedidoRepository.save(buscaProduto);

        return promocaoMapper.promocao(salvarPedido);

    }




}
