package com.bffagendadortarefas.business;

import com.bffagendadortarefas.dto.in.LoginDTORequest;
import com.bffagendadortarefas.dto.out.TarefasDTOResponse;
import com.bffagendadortarefas.infrastructure.enums.StatusNotificacaoEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j //para criar logs
public class CronService {

    private final TarefasService tarefasService;
    private final EmailService emailService;
    private final UsuarioService usuarioService;

    @Value(value = "${usuario.email}")
    private String email;

    @Value(value = "${usuario.senha}")
    private String senha;

    @Scheduled(cron = "${cron.horario}")
    public void buscarENotificarTarefa() {
        String token = login(converterParaLoginDTO());

        LocalDateTime periodoInicial = LocalDateTime.now();
        LocalDateTime periodoFinal = LocalDateTime.now().plusHours(1);

        List<TarefasDTOResponse> listaTarefas = tarefasService.buscarTarefasPorPeriodo(periodoInicial,
                periodoFinal,
                token);

        listaTarefas.forEach(tarefa -> {
            emailService.enviarEmail(tarefa);
            tarefasService.alterarStatusTarefa(tarefa.getId(), StatusNotificacaoEnum.NOTIFICADO, token);
        });
    }

    private String login(LoginDTORequest dto) {
        return usuarioService.login(dto);
    }

    private LoginDTORequest converterParaLoginDTO() {
        return LoginDTORequest.builder()
                .email(email)
                .senha(senha)
                .build();
    }

}
