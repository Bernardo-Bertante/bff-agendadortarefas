package com.bffagendadortarefas.controller;


import com.bffagendadortarefas.business.TarefasService;
import com.bffagendadortarefas.dto.TarefasDTO;
import com.bffagendadortarefas.infrastructure.enums.StatusNotificacaoEnum;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/tarefas")
@AllArgsConstructor
public class TarefasController {

    private final TarefasService tarefasService;

    @PostMapping
    public ResponseEntity<TarefasDTO> criarTarefa(@RequestBody TarefasDTO tarefasDTO,
                                                  @RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(tarefasService.criarTarefa(tarefasDTO, token));
    }

    @GetMapping("/eventos")
    public ResponseEntity<List<TarefasDTO>> buscarTarefasPorPeriodo(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataInicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataFim,
            @RequestHeader("Authorization") String token) {

        return ResponseEntity.ok(tarefasService.buscarTarefasPorPeriodo(dataInicio, dataFim, token));
    }

    @GetMapping
    public ResponseEntity<List<TarefasDTO>> buscarTarefasPorEmail(@RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(tarefasService.buscarTarefasPorEmail(token));
    }

    @DeleteMapping
    public ResponseEntity<Void> deletarTarefaPorId(@RequestParam("id") String id,
                                                   @RequestHeader("Authorization") String token) {
        tarefasService.deletarTarefaPorId(id, token);
        return ResponseEntity.ok().build();
    }

    @PatchMapping
    public ResponseEntity<TarefasDTO> alterarStatusTarefa(@RequestParam("id") String id,
                                                          @RequestParam("status") StatusNotificacaoEnum status,
                                                          @RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(tarefasService.alterarStatusTarefa(id, status, token));
    }

    @PutMapping
    public ResponseEntity<TarefasDTO> alterarTarefa(@RequestParam("id") String id,
                                                    @RequestBody TarefasDTO tarefasDTO,
                                                    @RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(tarefasService.alterarTarefa(id, tarefasDTO, token));
    }




}
