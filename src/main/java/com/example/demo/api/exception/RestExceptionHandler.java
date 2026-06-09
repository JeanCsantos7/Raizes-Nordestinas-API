package com.example.demo.api.exception;

import com.example.demo.application.dto.erros.*;
import com.example.demo.infrastructure.exception.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(UsuarioNaoEncontrado.class)
    public ResponseEntity<NaoEncontradoDTO> usuarioNaoEncontradoException(UsuarioNaoEncontrado ex){

        NaoEncontradoDTO erro = new NaoEncontradoDTO(
                ex.getMessage(),
                404,
                LocalDateTime.now()
        );

        return ResponseEntity.status(404).body(erro);
    }

    public ResponseEntity<UsuarioNaoCadastradoDTO> usuarioNaoCadastradoException(UsuarioNaoCadastrado ex){

        UsuarioNaoCadastradoDTO erro = new UsuarioNaoCadastradoDTO(
                ex.getMessage(),
                401,
                LocalDateTime.now()
        );

        return ResponseEntity.status(401).body(erro);
    }

    @ExceptionHandler(PedidoNaoEncontrado.class)
    public ResponseEntity<NaoEncontradoDTO> PedidoNaoEncontradoException(PedidoNaoEncontrado ex){
        NaoEncontradoDTO erro = new NaoEncontradoDTO(
                ex.getMessage(),
                404,
                LocalDateTime.now()
        );

        return ResponseEntity.status(404).body(erro);
    }

    @ExceptionHandler(UnidadeNaoEncontrada.class)
    public ResponseEntity<NaoEncontradoDTO> UnidadeNaoEncontradoException(UnidadeNaoEncontrada ex){
        NaoEncontradoDTO erro = new NaoEncontradoDTO(
                ex.getMessage(),
                404,
                LocalDateTime.now()
        );

        return ResponseEntity.status(404).body(erro);
    }

    @ExceptionHandler(ProdutoNaoEncontrado.class)
    public ResponseEntity<NaoEncontradoDTO> ProdutoNaoEncontradoException(ProdutoNaoEncontrado ex){
        NaoEncontradoDTO erro = new NaoEncontradoDTO(
                ex.getMessage(),
                404,
                LocalDateTime.now()
        );

        return ResponseEntity.status(404).body(erro);
    }

    @ExceptionHandler(PagamentoNaoEncontrado.class)
    public ResponseEntity<NaoEncontradoDTO> PagamentoNaoEncontradoException(PagamentoNaoEncontrado ex){
        NaoEncontradoDTO erro = new NaoEncontradoDTO(
                ex.getMessage(),
                404,
                LocalDateTime.now()
        );

        return ResponseEntity.status(404).body(erro);
    }

    @ExceptionHandler(ErroResgatePontos.class)
    public ResponseEntity<ErroPontosDTO> ErroPontosException(ErroResgatePontos ex){
        ErroPontosDTO erro = new ErroPontosDTO(
                ex.getMessage(),
                409,
                LocalDateTime.now()
        );

        return ResponseEntity.status(409).body(erro);
    }

    @ExceptionHandler(PagamentoRecusado.class)
    public ResponseEntity<PagamentoRecusadoDTO> PagamentoRecusadoException(PagamentoRecusado ex){
        PagamentoRecusadoDTO erro = new PagamentoRecusadoDTO(
                ex.getMessage(),
                409,
                LocalDateTime.now()
        );

        return ResponseEntity.status(409).body(erro);
    }

    @ExceptionHandler(ErroEstorno.class)
    public ResponseEntity<EstornoErroDTO> ErroEstornoException(ErroEstorno ex){
        EstornoErroDTO erro = new EstornoErroDTO(
                ex.getMessage(),
                409,
                LocalDateTime.now()
        );

        return ResponseEntity.status(409).body(erro);
    }

    @ExceptionHandler(ErroPedido.class)
    public ResponseEntity<ErroPedidoDTO> ErroPedidoException(ErroPedido ex){
        ErroPedidoDTO erro = new ErroPedidoDTO(
                ex.getMessage(),
                409,
                LocalDateTime.now()
        );

        return ResponseEntity.status(409).body(erro);
    }

    @ExceptionHandler(EstoqueEsgotado.class)
    public ResponseEntity<EstoqueEsgotadoDTO> EstoqueEsgotadoException(EstoqueEsgotado ex){
        EstoqueEsgotadoDTO erro = new EstoqueEsgotadoDTO(
                ex.getMessage(),
                409,
                LocalDateTime.now()
        );

        return ResponseEntity.status(409).body(erro);
    }

    @ExceptionHandler(QuantidadeSuperiorEstoque.class)
    public ResponseEntity<QuantidadeSuperiorEstoqueDTO> QuantidadeSuperiorEstoqueException(QuantidadeSuperiorEstoque ex){
        QuantidadeSuperiorEstoqueDTO erro = new QuantidadeSuperiorEstoqueDTO(
                ex.getMessage(),
                409,
                LocalDateTime.now()
        );

        return ResponseEntity.status(409).body(erro);
    }

    @ExceptionHandler(EmailExistente.class)
    public ResponseEntity<EmailExistenteDTO> EmailExistenteException(EmailExistente ex){
        EmailExistenteDTO erro = new EmailExistenteDTO(
                ex.getMessage(),
                409,
                LocalDateTime.now()
        );

        return ResponseEntity.status(409).body(erro);
    }



}
