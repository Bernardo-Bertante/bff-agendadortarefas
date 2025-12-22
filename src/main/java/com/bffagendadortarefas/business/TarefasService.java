package com.bffagendadortarefas.business;


import com.bffagendadortarefas.dto.TarefasDTO;
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

    public TarefasDTO criarTarefa(TarefasDTO dto, String token) {
        return tarefasClient.criarTarefa(dto, token);

    }

    public List<TarefasDTO> buscarTarefasPorPeriodo(LocalDateTime dataInicio, LocalDateTime dataFim, String token) {
        return tarefasClient.buscarTarefasPorPeriodo(dataInicio, dataFim, token);
    }

    public List<TarefasDTO> buscarTarefasPorEmail(String token) {
        return tarefasClient.buscarTarefasPorEmail(token);
    }

    public void deletarTarefaPorId(String id, String token) {
        tarefasClient.deletarTarefaPorId(id, token);
    }

    public TarefasDTO alterarStatusTarefa(String id, StatusNotificacaoEnum status, String token) {
        return tarefasClient.alterarStatusTarefa(id, status, token);
    }

    public TarefasDTO alterarTarefa(String id, TarefasDTO dto, String token) {
        return tarefasClient.alterarTarefa(id, dto, token);
    }
}
