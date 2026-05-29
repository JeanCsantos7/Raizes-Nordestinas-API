package com.example.demo.api.controller;



import com.example.demo.application.dto.request.ResgatarPontosRequestDTO;
import com.example.demo.application.dto.response.FidelidadeResponseDTO;
import com.example.demo.application.service.FidelidadeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/fidelidade")
@RequiredArgsConstructor
public class FidelidadeController {

    private final FidelidadeService fidelidadeService;




    @GetMapping("/{id}")
    public ResponseEntity<FidelidadeResponseDTO> consultarPontos(@PathVariable Long id){

        return ResponseEntity.ok(fidelidadeService.consultarPontos(id));

    }

    @PatchMapping("/resgatar/{id}")
    public ResponseEntity<FidelidadeResponseDTO> resgatarPontos(@PathVariable Long id, @RequestBody ResgatarPontosRequestDTO dto){

        return ResponseEntity.ok(fidelidadeService.resgatarPontos(id, dto));



    }

}
