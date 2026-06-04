package com.example.demo.application.service;

import com.example.demo.application.dto.request.PagamentoRequestDTO;
import com.example.demo.application.dto.response.EstornoResponseDTO;
import com.example.demo.application.dto.response.PagamentoResponseDTO;
import com.example.demo.application.mapper.PagamentoMapper;
import com.example.demo.domain.enums.StatusPagamento;
import com.example.demo.domain.enums.StatusPedido;
import com.example.demo.domain.model.Pagamento;
import com.example.demo.domain.model.Pedido;
import com.example.demo.infrastructure.exception.ErroEstorno;
import com.example.demo.infrastructure.exception.PagamentoNaoEncontrado;
import com.example.demo.infrastructure.exception.PagamentoRecusado;
import com.example.demo.infrastructure.exception.PedidoNaoEncontrado;
import com.example.demo.infrastructure.repository.PagamentoRepository;
import com.example.demo.infrastructure.repository.PedidoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class PagamentoService {

    private final PagamentoRepository pagamentoRepository;
    private final PagamentoMapper pagamentoMapper;
    private final PedidoRepository pedidoRepository;
    private final PedidoService pedidoService;

    public List<PagamentoResponseDTO> findAll(){
        return pagamentoMapper.toListDTO(pagamentoRepository.findAll());
    }

    public PagamentoResponseDTO processarPagamento(PagamentoRequestDTO dto){
        Pedido pedido = pedidoRepository.findById(dto.pedidoID()).orElseThrow(() -> new PedidoNaoEncontrado("Pedido não localizado"));
        Pagamento pagamento = new Pagamento();
        pagamento.setDataPagamento(LocalDateTime.now());
        pagamento.setPedido(pedido);
        pagamento.setMetodo(dto.metodo());



        boolean aprovado = new Random().nextBoolean();


        if(aprovado){
            pagamento.setStatus(StatusPagamento.APROVADO);
            pedido.setStatus(StatusPedido.CONCLUIDO);
            pagamento.setPayload("Pagamento realizado com sucesso");
            pedidoService.aplicarPromocao(dto.pedidoID());

        }

        else{
            pagamento.setStatus(StatusPagamento.NEGADO);
            pedido.setStatus(StatusPedido.PAGAMENTO_NEGADO);
            pagamento.setPayload("Pagamento negado!!");
            throw new PagamentoRecusado("O pagamento foi recusado, verifique as informações de pagamento!!");


        }

        Pagamento pedidoSalvo = pagamentoRepository.save(pagamento);

        return pagamentoMapper.toDTO(pedidoSalvo);


    }

    public EstornoResponseDTO estornar(Long id){
        Pagamento pagamento = pagamentoRepository.findById(id).orElseThrow(() -> new PagamentoNaoEncontrado("Não foi possível localizar o registro de pagamento"));




            Pedido pedido = pagamento.getPedido();
            if(pagamento.getStatus().equals(StatusPagamento.APROVADO)){
                pagamento.setStatus(StatusPagamento.PENDENTE);

                pedido.setStatus(StatusPedido.CANCELADO);
                pedido.setTotal(BigDecimal.valueOf(0));
                Pagamento pagamentoSalvo = pagamentoRepository.save(pagamento);

                return pagamentoMapper.estornoDTO(pagamentoSalvo);

            }



        else{


          throw new ErroEstorno("Só é possível estornar pedidos que já foram pagos!");
        }







    }
}
