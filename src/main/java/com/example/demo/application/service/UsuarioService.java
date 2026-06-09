package com.example.demo.application.service;

import com.example.demo.application.dto.request.UsuarioRequestDTO;
import com.example.demo.application.dto.response.UsuarioResponseDTO;
import com.example.demo.application.mapper.UsuarioMapper;
import com.example.demo.domain.enums.PerfilUsuario;
import com.example.demo.domain.model.Usuario;
import com.example.demo.infrastructure.exception.EmailExistente;
import com.example.demo.infrastructure.exception.UsuarioNaoEncontrado;
import com.example.demo.infrastructure.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;



@Service
@RequiredArgsConstructor

public class UsuarioService {

    private final UsuarioMapper usuarioMapper;
    private final UsuarioRepository usuarioRepository;
    private final BCryptPasswordEncoder passwordEncoder;


    public UsuarioResponseDTO save(UsuarioRequestDTO dados) {

        Usuario toEntity = usuarioMapper.toEntity(dados);
        String senhaHash = passwordEncoder.encode(dados.senha());
        toEntity.setSenhaHash(senhaHash);
        EmailExistente(dados.email());
        toEntity.setPerfil(PerfilUsuario.CLIENTE);
        Usuario usuarioSalvo = usuarioRepository.save(toEntity);
        return usuarioMapper.toDTO(usuarioSalvo);

    }

    public void EmailExistente(String email){

        try{

            Boolean existente = verificarEmailExistente(email);
            if(existente){
                throw new EmailExistente("Email já cadastrado! ");


            }
        }

        catch (EmailExistente ex){

            throw new EmailExistente("Email já cadastrado!" );

        }
    }


    public Page<UsuarioResponseDTO> findAll(Pageable pageable) {

        Page<Usuario> usuario = usuarioRepository.findAll(pageable);
        return usuario.map(usuarioMapper::toDTO);

    }

    public UsuarioResponseDTO findById(Long id) {



            Usuario usuario = usuarioRepository.findById(id).orElseThrow(() -> new UsuarioNaoEncontrado("Usuário não encontrado, verifique os dados informados!"));
            return usuarioMapper.toDTO(usuario);



    }

    public Usuario findByEmail(String email){



        Usuario usuarioBuscado = usuarioRepository.findByEmail(email);

      if(usuarioBuscado == null){
          throw new UsuarioNaoEncontrado("Usuário não encontrado!");
      }
            return usuarioRepository.findByEmail(email);



    }

    public Boolean verificarEmailExistente(String email){
        return usuarioRepository.existsByEmail(email);
    }



    public UsuarioResponseDTO update(Long id, UsuarioRequestDTO dto){
        Usuario buscaUsuario = usuarioRepository.findById(id).orElseThrow(() -> new UsuarioNaoEncontrado("Usuário não encontrado, verifique os dados informados!"));
        usuarioMapper.update(dto, buscaUsuario);
        Usuario usuarioSalvo = usuarioRepository.save(buscaUsuario);
        return usuarioMapper.toDTO(usuarioSalvo);

    }

    public void delete(Long id){
        usuarioRepository.deleteById(id);

    }



}
