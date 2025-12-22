package com.bffagendadortarefas.controller;

import com.bffagendadortarefas.business.TarefasService;
import com.bffagendadortarefas.dto.TarefasDTO;
import com.bffagendadortarefas.infrastructure.enums.StatusNotificacaoEnum;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/tarefas")
@AllArgsConstructor
@Tag(name = "Tarefas", description = "Gerenciamento de tarefas do usuário")
public class TarefasController {

    private final TarefasService tarefasService;

    @Operation(
            summary = "Criar uma nova tarefa",
            description = "Cria uma tarefa associada ao usuário autenticado"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Tarefa criada com sucesso"),
            @ApiResponse(responseCode = "401", description = "Não autorizado"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    @PostMapping
    public ResponseEntity<TarefasDTO> criarTarefa(
            @RequestBody
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Dados da tarefa",
                    required = true
            )
            TarefasDTO tarefasDTO,

            @RequestHeader("Authorization")
            @Parameter(description = "Token JWT no formato Bearer {token}", required = true)
            String token
    ) {
        return ResponseEntity.ok(tarefasService.criarTarefa(tarefasDTO, token));
    }

    @Operation(
            summary = "Buscar tarefas por período",
            description = "Retorna as tarefas do usuário dentro de um intervalo de datas"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista de tarefas retornada"),
            @ApiResponse(responseCode = "401", description = "Não autorizado")
    })
    @GetMapping("/eventos")
    public ResponseEntity<List<TarefasDTO>> buscarTarefasPorPeriodo(

            @Parameter(description = "Data inicial no formato ISO-8601", example = "2025-01-01T00:00:00")
            @RequestParam
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
            LocalDateTime dataInicio,

            @Parameter(description = "Data final no formato ISO-8601", example = "2025-01-31T23:59:59")
            @RequestParam
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
            LocalDateTime dataFim,

            @RequestHeader("Authorization")
            @Parameter(description = "Token JWT", required = true)
            String token
    ) {
        return ResponseEntity.ok(
                tarefasService.buscarTarefasPorPeriodo(dataInicio, dataFim, token)
        );
    }

    @Operation(
            summary = "Buscar todas as tarefas do usuário",
            description = "Retorna todas as tarefas associadas ao usuário autenticado"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista de tarefas retornada"),
            @ApiResponse(responseCode = "401", description = "Não autorizado")
    })
    @GetMapping
    public ResponseEntity<List<TarefasDTO>> buscarTarefasPorEmail(

            @RequestHeader("Authorization")
            @Parameter(description = "Token JWT", required = true)
            String token
    ) {
        return ResponseEntity.ok(tarefasService.buscarTarefasPorEmail(token));
    }

    @Operation(
            summary = "Excluir tarefa",
            description = "Remove uma tarefa pelo ID"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Tarefa removida"),
            @ApiResponse(responseCode = "401", description = "Não autorizado"),
            @ApiResponse(responseCode = "404", description = "Tarefa não encontrada")
    })
    @DeleteMapping
    public ResponseEntity<Void> deletarTarefaPorId(

            @Parameter(description = "ID da tarefa", required = true)
            @RequestParam("id")
            String id,

            @RequestHeader("Authorization")
            @Parameter(description = "Token JWT", required = true)
            String token
    ) {
        tarefasService.deletarTarefaPorId(id, token);
        return ResponseEntity.ok().build();
    }

    @Operation(
            summary = "Alterar status da tarefa",
            description = "Atualiza o status de uma tarefa"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Status atualizado"),
            @ApiResponse(responseCode = "400", description = "Status inválido"),
            @ApiResponse(responseCode = "401", description = "Não autorizado")
    })
    @PatchMapping
    public ResponseEntity<TarefasDTO> alterarStatusTarefa(

            @Parameter(description = "ID da tarefa", required = true)
            @RequestParam("id")
            String id,

            @Parameter(
                    description = "Novo status da tarefa",
                    required = true,
                    example = "CONCLUIDA"
            )
            @RequestParam("status")
            StatusNotificacaoEnum status,

            @RequestHeader("Authorization")
            @Parameter(description = "Token JWT", required = true)
            String token
    ) {
        return ResponseEntity.ok(
                tarefasService.alterarStatusTarefa(id, status, token)
        );
    }

    @Operation(
            summary = "Atualizar dados da tarefa",
            description = "Atualiza as informações de uma tarefa existente"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Tarefa atualizada"),
            @ApiResponse(responseCode = "401", description = "Não autorizado"),
            @ApiResponse(responseCode = "404", description = "Tarefa não encontrada")
    })
    @PutMapping
    public ResponseEntity<TarefasDTO> alterarTarefa(

            @Parameter(description = "ID da tarefa", required = true)
            @RequestParam("id")
            String id,

            @RequestBody
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Novos dados da tarefa",
                    required = true
            )
            TarefasDTO tarefasDTO,

            @RequestHeader("Authorization")
            @Parameter(description = "Token JWT", required = true)
            String token
    ) {
        return ResponseEntity.ok(
                tarefasService.alterarTarefa(id, tarefasDTO, token)
        );
    }
}