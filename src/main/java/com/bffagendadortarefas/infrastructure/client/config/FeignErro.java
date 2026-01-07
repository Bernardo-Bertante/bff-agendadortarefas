package com.bffagendadortarefas.infrastructure.client.config;

import com.bffagendadortarefas.infrastructure.exceptions.BusinessException;
import com.bffagendadortarefas.infrastructure.exceptions.ConflictException;
import com.bffagendadortarefas.infrastructure.exceptions.ResourceNotFoundException;
import com.bffagendadortarefas.infrastructure.exceptions.UnauthorizedException;
import feign.Response;
import feign.codec.ErrorDecoder;

public class FeignErro implements ErrorDecoder {

    @Override
    public Exception decode(String s, Response response) {
        switch (response.status()) {
            case 401 -> {
                return new UnauthorizedException("Usuário não autorizado");
            }
            case 403 -> {
                return new ResourceNotFoundException("Recurso não encontrado");
            }
            case 409 -> {
                return new ConflictException("Atributo já existente");
            }
            default -> {
                return new BusinessException("Erro de servidor");
            }
        }
    }
}
