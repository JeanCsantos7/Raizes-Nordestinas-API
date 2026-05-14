package com.example.demo.infrastructure.config;


import com.example.demo.application.dto.request.UsuarioRequestDTO;
import com.example.demo.application.dto.response.UsuarioResponseDTO;
import com.example.demo.application.service.UsuarioService;
import com.example.demo.domain.model.Usuario;
import com.example.demo.infrastructure.exception.UsuarioNaoEncontrado;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class JwtEndpoint {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UsuarioService usuarioService;

    @PostMapping("/login")
    public String login(@RequestBody UsuarioRequestDTO usuario){

        Usuario user = usuarioService.findByEmail(usuario.email());


   try{


       authenticationManager.authenticate(
               new UsernamePasswordAuthenticationToken(usuario.email(), usuario.senha()));




   }

   catch (BadCredentialsException e) {
       throw new BadCredentialsException("Email ou senha inválidos");
   }

        return jwtService.gerarToken(user.getEmail(), user.getPerfil().name());


    }

}
