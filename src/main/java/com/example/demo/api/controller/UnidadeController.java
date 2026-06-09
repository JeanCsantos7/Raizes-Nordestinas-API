package com.example.demo.api.controller;

import com.example.demo.application.dto.request.UnidadeRequestDTO;
import com.example.demo.application.dto.response.UnidadeResponseDTO;
import com.example.demo.application.service.UnidadeService;
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
@RequestMapping("/unidades")
@RequiredArgsConstructor
@SecurityRequirement(name = SecurityConfig.SECURITY)
@Tag(name = "unidades", description = "Controlador para salvar, consultar e editar unidades!")
public class UnidadeController {

    private final UnidadeService unidadeService;

    @Operation(
            summary = "Cadastrar unidade",
            description = "Realiza o cadastro de uma nova unidade"
    )
    @ApiResponse(responseCode = "201", description = "Unidade cadastrada com sucesso")
    @ApiResponse(responseCode = "400", description = "Dados inválidos")

    @PostMapping

    public ResponseEntity<UnidadeResponseDTO> save(@RequestBody @Valid UnidadeRequestDTO dto) {

        return ResponseEntity.status(HttpStatus.CREATED).body(unidadeService.save(dto));
    }

    @Operation(
            summary = "Listar unidades",
            description = "Retorna todas as unidades cadastradas com paginação. Utilize apenas os parâmetros 'page' e 'size'"
    )
    @ApiResponse(responseCode = "200", description = "Consulta realizada com sucesso")
    @ApiResponse(responseCode = "401", description = "Token inválido ou expirado")

    @GetMapping
    public ResponseEntity<Page<UnidadeResponseDTO>> findAll(Pageable pageable) {

        return ResponseEntity.ok(unidadeService.findAll(pageable));
    }
    @Operation(
            summary = "Buscar unidade por ID",
            description = "Retorna uma unidade específica"
    )
    @ApiResponse(responseCode = "200", description = "Unidade encontrada")
    @ApiResponse(responseCode = "404", description = "Unidade não encontrada")
    @ApiResponse(responseCode = "401", description = "Token inválido ou expirado")

    @GetMapping("/{id}")
    public ResponseEntity<UnidadeResponseDTO> findById(@PathVariable Long id) {

        return ResponseEntity.ok(unidadeService.findById(id));
    }



    @Operation(
            summary = "Atualizar unidade",
            description = "Atualiza os dados de uma unidade"
    )
    @ApiResponse(responseCode = "200", description = "Unidade atualizada com sucesso")
    @ApiResponse(responseCode = "404", description = "Unidade não encontrada")
    @ApiResponse(responseCode = "401", description = "Token inválido ou expirado")

    @PatchMapping("/{id}")
    public ResponseEntity<UnidadeResponseDTO> update(@RequestBody @Valid UnidadeRequestDTO dto, @PathVariable Long id) {
        return ResponseEntity.ok(unidadeService.atualizarDados(id, dto));
    }
    @Operation(
            summary = "Deletar unidade",
            description = "Deleta o registro de uma unidade específica"
    )
    @ApiResponse(responseCode = "204", description = "Unidade Deletada com sucesso")
    @ApiResponse(responseCode = "404", description = "Unidade não encontrada")
    @ApiResponse(responseCode = "401", description = "Token inválido ou expirado")

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        unidadeService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }

}
