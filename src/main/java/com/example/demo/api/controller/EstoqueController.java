package com.example.demo.api.controller;

import com.example.demo.application.dto.request.AdicionarQuantidadeRequestDTO;
import com.example.demo.application.dto.request.EstoqueRequestDTO;
import com.example.demo.application.dto.response.EstoqueResponseDTO;

import com.example.demo.application.service.EstoqueService;
import com.example.demo.infrastructure.config.SecurityConfig;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/estoque")
@RequiredArgsConstructor
@SecurityRequirement(name = SecurityConfig.SECURITY)
@Tag(name = "estoque", description = "Controlador para salvar, consultar e adicionar itens ao estoque !")
public class EstoqueController {

    private final EstoqueService estoqueService;


    @Operation(
            summary = "Cadastrar item no estoque",
            description = """
                    Realiza o cadastro de um novo item no estoque.

                    Regras:
                    - O produto deve existir.
                    - A unidade deve existir.
                    - A quantidade inicial será registrada para controle de disponibilidade.
                    """
    )
    @ApiResponse(
            responseCode = "201",
            description = "Item cadastrado no estoque com sucesso"
    )
    @ApiResponse(
            responseCode = "400",
            description = "Dados inválidos"
    )
    @ApiResponse(
            responseCode = "404",
            description = "Produto ou unidade não encontrados"
    )
    @ApiResponse(
            responseCode = "401",
            description = "Token inválido ou expirado"
    )

    @PostMapping
    public ResponseEntity<EstoqueResponseDTO> save(@RequestBody @Valid  EstoqueRequestDTO dto){

        return ResponseEntity.status(HttpStatus.CREATED).body(estoqueService.save(dto));
    }


    @Operation(
            summary = "Listar itens do estoque",
            description = """
                    Retorna todos os registros de estoque utilizando paginação.
                    Utilize apenas os parâmetros 'page' e 'size'

                    Informações retornadas:
                    - Produto vinculado.
                    - Quantidade disponível.
                    - Unidade responsável pelo estoque.
                    """
    )
    @ApiResponse(
            responseCode = "200",
            description = "Consulta realizada com sucesso"
    )
    @ApiResponse(
            responseCode = "401",
            description = "Token inválido ou expirado"
    )
    @GetMapping
    public ResponseEntity<Page<EstoqueResponseDTO>> findAll(Pageable pageable){

        return ResponseEntity.ok(estoqueService.findAll(pageable));
    }

    @Operation(
            summary = "Adicionar quantidade ao estoque",
            description = """
                    Incrementa a quantidade disponível de um item já cadastrado.

                    Regras:
                    - O registro de estoque deve existir.
                    - Apenas valores positivos são aceitos.
                    - A quantidade informada será somada ao saldo atual.
                    """
    )
    @ApiResponse(
            responseCode = "200",
            description = "Quantidade adicionada com sucesso"
    )
    @ApiResponse(
            responseCode = "404",
            description = "Registro de estoque não encontrado"
    )
    @ApiResponse(
            responseCode = "400",
            description = "Quantidade inválida"
    )
    @ApiResponse(
            responseCode = "401",
            description = "Token inválido ou expirado"
    )

    @PatchMapping("/adicionarQuantidade/{id}")
    public ResponseEntity<EstoqueResponseDTO> adicionarQuantidade(@PathVariable  Long id, @RequestBody AdicionarQuantidadeRequestDTO qtd){

        return ResponseEntity.ok(estoqueService.adicionarQuantidade(id, qtd));
    }


}
