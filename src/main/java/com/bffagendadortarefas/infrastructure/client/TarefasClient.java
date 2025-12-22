package com.bffagendadortarefas.infrastructure.client;

import com.bffagendadortarefas.dto.TarefasDTO;
import com.bffagendadortarefas.infrastructure.enums.StatusNotificacaoEnum;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@FeignClient(name = "tarefas", url = "${agendador-tarefas.url}")
public interface TarefasClient {

    @PostMapping
    TarefasDTO criarTarefa(@RequestBody TarefasDTO tarefasDTO,
                           @RequestHeader("Authorization") String token);

    @GetMapping("/eventos")
    List<TarefasDTO> buscarTarefasPorPeriodo(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataInicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataFim,
            @RequestHeader("Authorization") String token);

    @GetMapping
    List<TarefasDTO> buscarTarefasPorEmail(@RequestHeader("Authorization") String token);

    @DeleteMapping
    void deletarTarefaPorId(@RequestParam("id") String id,
                            @RequestHeader("Authorization") String token);

    @PatchMapping
    TarefasDTO alterarStatusTarefa(@RequestParam("id") String id,
                                   @RequestParam("status") StatusNotificacaoEnum status,
                                   @RequestHeader("Authorization") String token);

    @PutMapping
    TarefasDTO alterarTarefa(@RequestParam("id") String id,
                             @RequestBody TarefasDTO tarefasDTO,
                             @RequestHeader("Authorization") String token);
}
