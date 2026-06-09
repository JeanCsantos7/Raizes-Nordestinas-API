package com.example.demo.api.controller;

import com.example.demo.application.dto.request.PagamentoRequestDTO;
import com.example.demo.application.dto.response.EstornoResponseDTO;
import com.example.demo.application.dto.response.PagamentoResponseDTO;
import com.example.demo.application.service.PagamentoService;
import com.example.demo.infrastructure.config.SecurityConfig;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/pagamentos")
@SecurityRequirement(name = SecurityConfig.SECURITY)
@Tag(name = "pagamentos", description = "Controlador para processar pagamento, listar  e estornar pagamentos!")
public class PagamentoController {

    private final PagamentoService pagamentoService;


    @Operation(
            summary = "Processar pagamento",
            description = """
                Realiza uma simulação de pagamento através de um serviço externo (mock).
                
                - Recebe os dados do pagamento e do pedido.
                - Simula a comunicação com um gateway de pagamento.
                - Retorna o status da transação (APROVADO ou RECUSADO).
                - Caso o pagamento seja aprovado e o valor do pedido seja igual ou superior a R$ 150,00,
                  uma promoção de 6% é aplicada automaticamente ao pedido.
                - O resultado da operação é registrado e retornado ao cliente.
                """

    )
    @ApiResponse(responseCode = "201", description = "Produto cadastrado com sucesso")
    @ApiResponse(responseCode = "400", description = "Dados inválidos")



    @PostMapping("/processarPagamento")
    public ResponseEntity<PagamentoResponseDTO> processarPagamento(@Valid @RequestBody PagamentoRequestDTO dto){
      return ResponseEntity.ok(pagamentoService.processarPagamento(dto));
    }


    @Operation(
            summary = "Listar pagamentos realizados, recusados ou pendentes",
            description = "Retorna todos os registros de pagamentos"
    )
    @ApiResponse(responseCode = "200", description = "Consulta realizada com sucesso")
    @ApiResponse(responseCode = "401", description = "Token inválido ou expirado")


    @GetMapping
    public ResponseEntity<List<PagamentoResponseDTO>> findAll(){
        return ResponseEntity.ok(pagamentoService.findAll());
    }


    @Operation(
            summary = "Estornar pagamento por ID",
            description = """
        Realiza o estorno de um pagamento específico.
        Apenas pagamentos com status APROVADO podem ser estornados.
        Após o estorno, o pagamento retorna para PENDENTE e o pedido é cancelado.
        """
    )
    @ApiResponse(responseCode = "204", description = "Valor estornado com sucesso")
    @ApiResponse(responseCode = "404", description = "Transação não encontrada")
    @ApiResponse(responseCode = "409", description = "Pagamento não pode ser estornado")
    @ApiResponse(responseCode = "401", description = "Token inválido ou expirado")

    @PatchMapping ("/estornar/{id}")
    public ResponseEntity<EstornoResponseDTO> estornar(@PathVariable  Long id){
        return ResponseEntity.ok(pagamentoService.estornar(id));
    }
}
