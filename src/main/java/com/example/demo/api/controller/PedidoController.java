package com.example.demo.api.controller;

import com.example.demo.application.dto.request.PedidoRequestDTO;
import com.example.demo.application.dto.response.PedidoResponseDTO;
import com.example.demo.application.dto.response.PromocaoResponseDTO;
import com.example.demo.application.service.PedidoService;
import com.example.demo.domain.enums.CanalPedido;


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
@RequestMapping("/pedidos")
@RequiredArgsConstructor
@SecurityRequirement(name = SecurityConfig.SECURITY)
@Tag(name = "pedidos", description = "Controlador para realizar pedidos, consultar e editar pedidos!")
public class PedidoController {

    private final PedidoService pedidoService;

    @Operation(
            summary = "Realizar pedidos",
            description = "Realiza pedidos"
    )
    @ApiResponse(responseCode = "201", description = "Produto cadastrado com sucesso")
    @ApiResponse(responseCode = "400", description = "Dados inválidos")

    @PostMapping
    public ResponseEntity<PedidoResponseDTO> save(@RequestBody @Valid PedidoRequestDTO dto) {

        return ResponseEntity.status(HttpStatus.CREATED).body(pedidoService.save(dto));
    }

    @Operation(
            summary = "Listar pedidos",
            description = "Retorna todos os pedidos realizados com paginação. Utilize apenas os parâmetros 'page' e 'size'"
    )
    @ApiResponse(responseCode = "200", description = "Consulta realizada com sucesso")
    @ApiResponse(responseCode = "401", description = "Token inválido ou expirado")


    @GetMapping
    public ResponseEntity<Page<PedidoResponseDTO>> findAll(Pageable pageable) {

        return ResponseEntity.ok(pedidoService.findAll(pageable));
    }


    @Operation(
            summary = "Buscar pedido por ID",
            description = "Retorna um pedido específico"
    )
    @ApiResponse(responseCode = "200", description = "Pedido encontrado")
    @ApiResponse(responseCode = "404", description = "Pedido não encontrado")
    @ApiResponse(responseCode = "401", description = "Token inválido ou expirado")


    @GetMapping("/{id}")
    public ResponseEntity<PedidoResponseDTO> findById(@PathVariable Long id) {

        return ResponseEntity.ok(pedidoService.findById(id));
    }

    @Operation(
            summary = "Buscar pedido por canal de atendimento",
            description = "Retorna pedidos por canal"
    )
    @ApiResponse(responseCode = "200", description = "Pedidos encontrados")
    @ApiResponse(responseCode = "404", description = "Pedidos não encontrados")
    @ApiResponse(responseCode = "401", description = "Token inválido ou expirado")


    @GetMapping("/canal")
    public ResponseEntity<List<PedidoResponseDTO>> findByCanalPedido(@RequestParam CanalPedido canal){
        return ResponseEntity.ok(pedidoService.findByCanalPedido(canal));
    }




    @Operation(
            summary = "Atualizar status do pedido",
            description = "Atualiza o status de um pedido específico"
    )
    @ApiResponse(responseCode = "200", description = "Status atualizado com sucesso")
    @ApiResponse(responseCode = "404", description = "Pedido não encontrado")
    @ApiResponse(responseCode = "401", description = "Token inválido ou expirado")

    @PatchMapping("/{id}")
    public ResponseEntity<PedidoResponseDTO> atualizarStatus(@RequestBody  PedidoRequestDTO dados, @PathVariable Long id) {
        pedidoService.atualizarStatus(dados.status(), id);
        return  ResponseEntity.noContent().build();
    }



}
