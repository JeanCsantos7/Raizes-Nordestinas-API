package com.example.demo.infrastructure.config;

import com.example.demo.application.service.UsuarioService;

import com.example.demo.domain.model.Usuario;
import com.example.demo.infrastructure.exception.UsuarioNaoEncontrado;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class UserDetail implements UserDetailsService {

    private final UsuarioService repoUsuario;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

      Usuario usuario = repoUsuario.findByEmail(email);

      if(email == null){
           throw new UsuarioNaoEncontrado("Usuário não encontrado");
       }


      return new User(usuario.getEmail(), usuario.getSenhaHash(), List.of(new SimpleGrantedAuthority("ROLE_" + usuario.getPerfil().name())) );


    }
}
