package com.example.demo.application.service;

import com.example.demo.application.dto.request.ResgatarPontosRequestDTO;
import com.example.demo.application.dto.response.FidelidadeResponseDTO;
import com.example.demo.application.mapper.UsuarioMapper;
import com.example.demo.domain.model.Pedido;
import com.example.demo.domain.model.Usuario;
import com.example.demo.infrastructure.exception.ErroResgatePontos;
import com.example.demo.infrastructure.exception.UsuarioNaoEncontrado;
import com.example.demo.infrastructure.repository.UsuarioRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;


@Service
@RequiredArgsConstructor
public class FidelidadeService {

   private final UsuarioRepository usuarioRepository;
   private final UsuarioMapper usuarioMapper;


    public FidelidadeResponseDTO gerarPontos(Pedido pedido){

    Usuario usuario = pedido.getClientes();


       BigDecimal total = pedido.getTotal();
       BigDecimal divisao = total.divide(BigDecimal.TEN);
       Integer gerarPts = divisao.intValue();

       usuario.setPontos(usuario.getPontos() + gerarPts);

       Usuario salvarUsuario = usuarioRepository.save(usuario);

       return usuarioMapper.fidelidadeDTO(salvarUsuario);

    }


    public FidelidadeResponseDTO consultarPontos(Long id){
       Usuario buscaUsuario = usuarioRepository.findById(id).orElseThrow(() -> new UsuarioNaoEncontrado("Não foi possível localizar o usuário"));
       return usuarioMapper.fidelidadeDTO(buscaUsuario);

    }


    public  FidelidadeResponseDTO resgatarPontos(Long id,  ResgatarPontosRequestDTO dto){
        Usuario buscaUsuario = usuarioRepository.findById(id).orElseThrow(() -> new UsuarioNaoEncontrado("Não foi possível localizar o usuário"));
        if (dto.pontos() > buscaUsuario.getPontos()){
            throw new ErroResgatePontos("o valor deve ser menor ou igual a: " + buscaUsuario.getPontos());
        }


        buscaUsuario.setPontos(buscaUsuario.getPontos() - dto.pontos());

        Usuario salvar = usuarioRepository.save(buscaUsuario);

        return usuarioMapper.fidelidadeDTO(salvar);
    }



}
