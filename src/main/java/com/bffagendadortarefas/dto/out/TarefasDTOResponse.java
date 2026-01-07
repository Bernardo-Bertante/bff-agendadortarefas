package com.bffagendadortarefas.dto.out;


import com.bffagendadortarefas.infrastructure.enums.StatusNotificacaoEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class TarefasDTOResponse {

    private String id;
    private String nomeTarefa;
    private String descricao;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataEvento;
    private String emailUsuario;
    private LocalDateTime dataAlteracao;
    private StatusNotificacaoEnum statusNotificacaoEnum;
}
