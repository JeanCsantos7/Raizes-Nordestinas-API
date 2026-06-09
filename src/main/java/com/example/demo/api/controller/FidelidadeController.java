package com.example.demo.api.controller;



import com.example.demo.application.dto.request.ResgatarPontosRequestDTO;
import com.example.demo.application.dto.response.FidelidadeResponseDTO;
import com.example.demo.application.service.FidelidadeService;
import com.example.demo.infrastructure.config.SecurityConfig;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/fidelidade")
@RequiredArgsConstructor
@SecurityRequirement(name = SecurityConfig.SECURITY)
@Tag(name = "fidelidade", description = "Controlador para consultar e resgatar pontos!")
public class FidelidadeController {

    private final FidelidadeService fidelidadeService;

    @Operation(
            summary = "Consultar saldo de pontos",
            description = """
                    Retorna a quantidade de pontos acumulados por um usuário específico.

                    Regras:
                    - O usuário deve existir.
                    - O saldo retornado corresponde aos pontos atualmente disponíveis.
                    """
    )
    @ApiResponse(
            responseCode = "200",
            description = "Consulta realizada com sucesso"
    )
    @ApiResponse(
            responseCode = "404",
            description = "Usuário não encontrado"
    )
    @ApiResponse(
            responseCode = "401",
            description = "Token inválido ou expirado")


    @GetMapping("/{id}")
    public ResponseEntity<FidelidadeResponseDTO> consultarPontos(@PathVariable Long id){

        return ResponseEntity.ok(fidelidadeService.consultarPontos(id));

    }

    @Operation(
            summary = "Resgatar pontos",
            description = """
                    Realiza o resgate de pontos acumulados pelo usuário.

                    Regras:
                    - O usuário deve existir.
                    - A quantidade solicitada deve ser menor ou igual ao saldo disponível.
                    - Após o resgate, o saldo é atualizado automaticamente.
                    """
    )
    @ApiResponse(
            responseCode = "200",
            description = "Pontos resgatados com sucesso"
    )
    @ApiResponse(
            responseCode = "404",
            description = "Usuário não encontrado"
    )
    @ApiResponse(
            responseCode = "409",
            description = "Quantidade de pontos solicitada é maior que o saldo disponível"
    )
    @ApiResponse(
            responseCode = "401",
            description = "Token inválido ou expirado"
    )

    @PatchMapping("/resgatar/{id}")
    public ResponseEntity<FidelidadeResponseDTO> resgatarPontos(@PathVariable Long id, @RequestBody ResgatarPontosRequestDTO dto){

        return ResponseEntity.ok(fidelidadeService.resgatarPontos(id, dto));



    }

}
