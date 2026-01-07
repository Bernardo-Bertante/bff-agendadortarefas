package com.bffagendadortarefas.dto.in;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EnderecoDTORequest {

    private String rua;
    private Long numero;
    private String complemento;
    private String cidade;
    private String estado;
    private String cep;
}
