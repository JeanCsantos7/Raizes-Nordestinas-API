package com.example.demo.application.service;

import com.example.demo.application.dto.request.UsuarioRequestDTO;
import com.example.demo.application.dto.response.UsuarioResponseDTO;
import com.example.demo.application.mapper.UsuarioMapper;
import com.example.demo.domain.model.Usuario;
import com.example.demo.infrastructure.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor

public class UsuarioService {

    private final UsuarioMapper usuarioMapper;
    private final UsuarioRepository repoUsuario;
    private final BCryptPasswordEncoder passwordEncoder;


    public UsuarioResponseDTO save(UsuarioRequestDTO dados) {

        Usuario toEntity = usuarioMapper.toEntity(dados);
        String senhaHash = passwordEncoder.encode(dados.senha());
        toEntity.setSenhaHash(senhaHash);
        Usuario usuarioSalvo = repoUsuario.save(toEntity);
        return usuarioMapper.toDTO(usuarioSalvo);

    }


    public List<UsuarioResponseDTO> findAll() {

        List<UsuarioResponseDTO> dto = usuarioMapper.toListDTO(repoUsuario.findAll());
        return dto;

    }

    public UsuarioResponseDTO findById(Long id) {
         Usuario usuario = repoUsuario.findById(id).orElseThrow(() -> new RuntimeException("Não foi possível localizar o usuário!"));
        return usuarioMapper.toDTO(usuario);

    }

    public UsuarioResponseDTO update(Long id, UsuarioRequestDTO dto){
        Usuario entity = repoUsuario.findById(id).orElseThrow(() -> new RuntimeException("Não foi possível localizar o usuário!"));
        usuarioMapper.update(dto, entity);
        String senhaAlterada = passwordEncoder.encode(dto.senha());
        entity.setSenhaHash(senhaAlterada);
        Usuario usuarioSalvo = repoUsuario.save(entity);
        return usuarioMapper.toDTO(usuarioSalvo);

    }

    public void delete(Long id){
        repoUsuario.deleteById(id);
    }

}
