package com.bffagendadortarefas.infrastructure.client;

import com.bffagendadortarefas.dto.in.TarefasDTORequest;
import com.bffagendadortarefas.dto.out.TarefasDTOResponse;
import com.bffagendadortarefas.infrastructure.enums.StatusNotificacaoEnum;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@FeignClient(name = "tarefas", url = "${agendador-tarefas.url}")
public interface TarefasClient {

    @PostMapping
    TarefasDTOResponse criarTarefa(@RequestBody TarefasDTORequest tarefasDTO,
                                   @RequestHeader("Authorization") String token);

    @GetMapping("/eventos")
    List<TarefasDTOResponse> buscarTarefasPorPeriodo(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataInicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataFim,
            @RequestHeader("Authorization") String token);

    @GetMapping
    List<TarefasDTOResponse> buscarTarefasPorEmail(@RequestHeader("Authorization") String token);

    @DeleteMapping
    void deletarTarefaPorId(@RequestParam("id") String id,
                            @RequestHeader("Authorization") String token);

    @PatchMapping
    TarefasDTOResponse alterarStatusTarefa(@RequestParam("id") String id,
                                           @RequestParam("status") StatusNotificacaoEnum status,
                                           @RequestHeader("Authorization") String token);

    @PutMapping
    TarefasDTOResponse alterarTarefa(@RequestParam("id") String id,
                                     @RequestBody TarefasDTORequest tarefasDTO,
                                     @RequestHeader("Authorization") String token);
}
