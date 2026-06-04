package com.example.demo.application.service;


import com.example.demo.application.dto.request.ItemPedidoRequestDTO;
import com.example.demo.application.dto.request.PedidoRequestDTO;
import com.example.demo.application.dto.response.AtualizarStatusResponseDTO;
import com.example.demo.application.dto.response.PedidoResponseDTO;
import com.example.demo.application.dto.response.PromocaoResponseDTO;
import com.example.demo.application.mapper.PedidoMapper;
import com.example.demo.application.mapper.PromocaoMapper;
import com.example.demo.domain.enums.CanalPedido;
import com.example.demo.domain.enums.StatusPedido;
import com.example.demo.domain.model.*;
import com.example.demo.infrastructure.exception.*;
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
    private final FidelidadeService fidelidadeService;
    private final EstoqueService estoqueService;

    public PedidoResponseDTO save(PedidoRequestDTO dados){

        Usuario buscaCliente = usuarioRepository.findById(dados.clienteID()).orElseThrow(() -> new UsuarioNaoEncontrado("Usuario não encontrado!"));
        Unidade buscaUnidade = unidadeRepository.findById(dados.unidadeID()).orElseThrow(() -> new UnidadeNaoEncontrada("Unidade não encontrada!"));
        Pedido pedido = new Pedido();
        pedido.setClientes(buscaCliente);
        pedido.setUnidadePedido(buscaUnidade);
        pedido.setStatus(StatusPedido.AGUARDANDO);
        pedido.setCanalPedido(dados.canalPedido());
        pedido.setDataPedido(LocalDateTime.now());


        List<ItemPedido> itens = new ArrayList<>();
        BigDecimal total = BigDecimal.ZERO;


        for(ItemPedidoRequestDTO itemDTO : dados.itens()){
            Produto produto = produtoRepository.findById(itemDTO.produtoID()).orElseThrow(() -> new ProdutoNaoEncontrado("Não foi possível localizar o produto!"));
            ItemPedido novoItem = new ItemPedido();
            novoItem.setPedido(pedido);
            novoItem.setProduto(produto);
            novoItem.setQuantidade(itemDTO.quantidade());
            novoItem.setPrecoUnitario(produto.getPreco());

            BigDecimal subTotal = produto.getPreco().multiply(BigDecimal.valueOf(itemDTO.quantidade()));



            Estoque produtoEstoque = estoqueRepository.findByProdutoId(itemDTO.produtoID());

            if(itemDTO.quantidade() <= produtoEstoque.getQuantidade()){

                estoqueService.removerQuantidade(itemDTO.produtoID(), itemDTO.quantidade());
            }

            else if(produtoEstoque.getQuantidade() == 0){

                throw new EstoqueEsgotado("Produto em falta no estoque!");
            }

            else{
                throw new QuantidadeSuperiorEstoque("Quantidade informada é maior do que temos em estoque");

            }




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

    public List<PedidoResponseDTO> findByCanalPedido(CanalPedido canal){
        List<Pedido> buscaPedido = pedidoRepository.findByCanalPedido(canal);
        return pedidoMapper.toListDTO(buscaPedido);
    }

    public PedidoResponseDTO findById(Long id){

        Pedido buscaPedido = pedidoRepository.findById(id).orElseThrow(() -> new PedidoNaoEncontrado("Produto não localizado"));

        return pedidoMapper.toDTO(buscaPedido);


    }



    public AtualizarStatusResponseDTO atualizarStatus(StatusPedido status, Long id){
        Pedido buscaPedido = pedidoRepository.findById(id).orElseThrow(() -> new PedidoNaoEncontrado("Pedido não localizado"));
        StatusPedido statusAnterior = buscaPedido.getStatus();


        buscaPedido.setStatus(status);
        if(status.equals(StatusPedido.CONCLUIDO ) && statusAnterior != StatusPedido.CONCLUIDO ){
          fidelidadeService.gerarPontos(buscaPedido);
        }

        else{
            throw new ErroResgatePontos("Não foi possível gerar seus pontos, pedido já havia sido concluido anteriormente!");
        }
        Pedido salvarAlteracao = pedidoRepository.save(buscaPedido);

        return pedidoMapper.atualizarStt(salvarAlteracao);


    }

    public PromocaoResponseDTO aplicarPromocao(Long pedidoId){

        Pedido buscaPedido = pedidoRepository.findById(pedidoId).orElseThrow(() -> new PedidoNaoEncontrado("Pedido não localizado"));
        BigDecimal valorPromocao = new BigDecimal("150.0");

        BigDecimal desconto = buscaPedido.getTotal().multiply(BigDecimal.valueOf(6)).divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP);


        if(buscaPedido.getTotal().compareTo(valorPromocao) >= 0){
            buscaPedido.setTotal(buscaPedido.getTotal().subtract(desconto));
            buscaPedido.setStatusPromocao("Promoção Aplicada");
        }

        else{

            buscaPedido.setStatusPromocao("Seu pedido não atingiu o valor mínimo para o desconto!");

        }

        Pedido salvarPedido = pedidoRepository.save(buscaPedido);

        return promocaoMapper.promocao(salvarPedido);

    }




}
