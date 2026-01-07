package com.bffagendadortarefas.business;


import com.bffagendadortarefas.dto.in.TarefasDTORequest;
import com.bffagendadortarefas.dto.out.TarefasDTOResponse;
import com.bffagendadortarefas.infrastructure.client.TarefasClient;
import com.bffagendadortarefas.infrastructure.enums.StatusNotificacaoEnum;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class TarefasService {

    private final TarefasClient tarefasClient;

    public TarefasDTOResponse criarTarefa(TarefasDTORequest dto, String token) {
        return tarefasClient.criarTarefa(dto, token);

    }

    public List<TarefasDTOResponse> buscarTarefasPorPeriodo(LocalDateTime dataInicio, LocalDateTime dataFim, String token) {
        return tarefasClient.buscarTarefasPorPeriodo(dataInicio, dataFim, token);
    }

    public List<TarefasDTOResponse> buscarTarefasPorEmail(String token) {
        return tarefasClient.buscarTarefasPorEmail(token);
    }

    public void deletarTarefaPorId(String id, String token) {
        tarefasClient.deletarTarefaPorId(id, token);
    }

    public TarefasDTOResponse alterarStatusTarefa(String id, StatusNotificacaoEnum status, String token) {
        return tarefasClient.alterarStatusTarefa(id, status, token);
    }

    public TarefasDTOResponse alterarTarefa(String id, TarefasDTORequest dto, String token) {
        return tarefasClient.alterarTarefa(id, dto, token);
    }
}
