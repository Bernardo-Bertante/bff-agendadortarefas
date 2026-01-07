package com.bffagendadortarefas.infrastructure.exceptions;

public class BusinessException extends RuntimeException{
    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(String message, Throwable erro) {super(message, erro);}
}
