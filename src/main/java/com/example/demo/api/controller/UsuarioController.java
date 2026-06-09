package com.example.demo.api.controller;

import com.example.demo.application.dto.request.LoginRequestDTO;
import com.example.demo.application.dto.request.UsuarioRequestDTO;
import com.example.demo.application.dto.response.UsuarioResponseDTO;
import com.example.demo.application.service.UsuarioService;

import com.example.demo.domain.model.Usuario;
import com.example.demo.infrastructure.config.JwtService;
import com.example.demo.infrastructure.config.SecurityConfig;
import com.example.demo.infrastructure.exception.UsuarioNaoEncontrado;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
@SecurityRequirement(name = SecurityConfig.SECURITY)
@Tag(name = "usuarios", description = "Controlador para salvar, consultar e editar usuários!")

public class UsuarioController {

   private final UsuarioService usuarioService;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;




    @Operation(
            summary = "Cadastrar usuário",
            description = "Realiza o cadastro de um novo usuário"
    )
    @ApiResponse(responseCode = "201", description = "Usuário cadastrado com sucesso")
    @ApiResponse(responseCode = "409", description = "Email já cadastrado")
    @ApiResponse(responseCode = "400", description = "Dados inválidos")



    @PostMapping
    public ResponseEntity<UsuarioResponseDTO> save( @RequestBody @Valid UsuarioRequestDTO dto){

        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.save(dto));
    }

    @Operation(
            summary = "Login",
            description = "Realiza autenticação e gera token JWT"
    )
    @ApiResponse(responseCode = "200", description = "Login realizado com sucesso")
    @ApiResponse(responseCode = "401", description = "Email ou senha inválidos")


    @PostMapping("/login")
    public String login(@RequestBody @Valid LoginRequestDTO usuario){

        Usuario user = usuarioService.findByEmail(usuario.email());


        try{


            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(usuario.email(), usuario.senha()));




        }

        catch (UsuarioNaoEncontrado e) {
            throw new UsuarioNaoEncontrado("Email ou senha inválidos");
        }

        return jwtService.gerarToken(user.getEmail(), user.getPerfil().name());


    }

    @Operation(
            summary = "Listar usuários",
            description = "Retorna todos os usuários cadastrados com paginação. Utilize apenas os parâmetros 'page' e 'size'"
    )
    @ApiResponse(responseCode = "200", description = "Consulta realizada com sucesso")
    @ApiResponse(responseCode = "401", description = "Token inválido ou expirado")

    @GetMapping
    public ResponseEntity<Page<UsuarioResponseDTO>> findAll( @ParameterObject Pageable pageable){

        return ResponseEntity.ok(usuarioService.findAll(pageable));
    }

    @Operation(
            summary = "Buscar usuário por ID",
            description = "Retorna um usuário específico"
    )
    @ApiResponse(responseCode = "200", description = "Usuário encontrado")
    @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    @ApiResponse(responseCode = "401", description = "Token inválido ou expirado")

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> findById(@PathVariable Long id){

        return ResponseEntity.ok(usuarioService.findById(id));
    }

    @Operation(
            summary = "Atualizar usuário",
            description = "Atualiza os dados de um usuário"
    )
    @ApiResponse(responseCode = "200", description = "Usuário atualizado com sucesso")
    @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    @ApiResponse(responseCode = "401", description = "Token inválido ou expirado")

    @PatchMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> update(@RequestBody  UsuarioRequestDTO dto, @PathVariable Long id){
    return ResponseEntity.ok(usuarioService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
      usuarioService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }






}
