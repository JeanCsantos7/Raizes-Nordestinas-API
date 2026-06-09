package com.example.demo.api.controller;


import com.example.demo.application.dto.request.AlterarPrecoRequestDTO;
import com.example.demo.application.dto.request.ProdutoRequestDTO;
import com.example.demo.application.dto.response.ProdutoResponseDTO;
import com.example.demo.application.service.ProdutoService;

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
@RequiredArgsConstructor
@RequestMapping("/produtos")
@SecurityRequirement(name = SecurityConfig.SECURITY)
@Tag(name = "produtos", description = "Controlador para salvar, consultar e editar produtos!")
public class ProdutoController {

    private final ProdutoService produtoService;

    @Operation(
            summary = "Cadastrar produtos",
            description = "Realiza o cadastro de um novo produto"
    )
    @ApiResponse(responseCode = "201", description = "Produto cadastrado com sucesso")
    @ApiResponse(responseCode = "400", description = "Dados inválidos")

    @PostMapping
    public ResponseEntity<ProdutoResponseDTO> save(@RequestBody @Valid ProdutoRequestDTO dto){

        return ResponseEntity.status(HttpStatus.CREATED).body(produtoService.save(dto));
    }


    @Operation(
            summary = "Listar produtos",
            description = "Retorna todos os produtos cadastradas com paginação. Utilize apenas os parâmetros 'page' e 'size'"
    )
    @ApiResponse(responseCode = "200", description = "Consulta realizada com sucesso")
    @ApiResponse(responseCode = "401", description = "Token inválido ou expirado")

    @GetMapping
    public ResponseEntity<Page<ProdutoResponseDTO>> findAll(Pageable pageable){

        return ResponseEntity.ok(produtoService.findAll(pageable));
    }


    @Operation(
            summary = "Buscar produto por ID",
            description = "Retorna um produto específico"
    )
    @ApiResponse(responseCode = "200", description = "Produto encontrado")
    @ApiResponse(responseCode = "404", description = "Produto não encontrado")
    @ApiResponse(responseCode = "401", description = "Token inválido ou expirado")


    @GetMapping("/{id}")
    public ResponseEntity<ProdutoResponseDTO> findById(@PathVariable Long id){

        return ResponseEntity.ok(produtoService.findById(id));
    }


    @Operation(
            summary = "Buscar produto por unidade",
            description = "Retorna produtos por unidade"
    )
    @ApiResponse(responseCode = "200", description = "Produtos encontrados")
    @ApiResponse(responseCode = "404", description = "Produtos não encontrados")
    @ApiResponse(responseCode = "401", description = "Token inválido ou expirado")

    @GetMapping("/unidade/{id}")
    public ResponseEntity<List<ProdutoResponseDTO>> findByUnidade(@PathVariable Long id){
        return ResponseEntity.ok(produtoService.findByUnidade(id));
    }

    @Operation(
            summary = "Atualizar produto",
            description = "Atualiza os dados de um produto"
    )
    @ApiResponse(responseCode = "200", description = "Produto atualizado com sucesso")
    @ApiResponse(responseCode = "404", description = "Produto não encontrado")
    @ApiResponse(responseCode = "401", description = "Token inválido ou expirado")


    @PatchMapping("/{id}")
    public ResponseEntity<ProdutoResponseDTO> update(@RequestBody @Valid ProdutoRequestDTO dto, @PathVariable Long id){
        return ResponseEntity.ok(produtoService.update(dto, id));
    }

    @Operation(
            summary = "Atualizar preço",
            description = "Atualiza o preço de um produto"
    )
    @ApiResponse(responseCode = "200", description = "Preço atualizado com sucesso")
    @ApiResponse(responseCode = "404", description = "Produto não encontrado")
    @ApiResponse(responseCode = "401", description = "Token inválido ou expirado")

    @PatchMapping ("/{id}/preco")
    public ResponseEntity<ProdutoResponseDTO> alterarPreco(@PathVariable  Long id, @RequestBody @Valid AlterarPrecoRequestDTO preco){
        produtoService.alterarPreco(id, preco.preco() );
        return ResponseEntity.noContent().build();
    }

    @Operation(
            summary = "Deletar produto",
            description = "Deleta o registro de um produto específico"
    )
    @ApiResponse(responseCode = "204", description = "Produto deletado com sucesso")
    @ApiResponse(responseCode = "404", description = "produto não encontrado")
    @ApiResponse(responseCode = "401", description = "Token inválido ou expirado")

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable  Long id){
        produtoService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }



}
