package com.bffagendadortarefas.business;

import com.bffagendadortarefas.dto.out.TarefasDTOResponse;
import com.bffagendadortarefas.infrastructure.client.EmailClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class EmailService {

    private final EmailClient emailClient;

    public void enviarEmail(TarefasDTOResponse dto) {
        emailClient.enviarEmail(dto);
    }

}
