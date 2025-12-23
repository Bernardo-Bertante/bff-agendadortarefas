package com.bffagendadortarefas.dto.in;


import com.bffagendadortarefas.infrastructure.enums.StatusNotificacaoEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class TarefasDTORequest {

    private String nomeTarefa;
    private String descricao;
    private LocalDateTime dataEvento;
}
