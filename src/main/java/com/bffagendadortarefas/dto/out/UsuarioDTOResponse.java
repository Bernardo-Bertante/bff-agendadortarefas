package com.bffagendadortarefas.dto.out;

import com.bffagendadortarefas.dto.in.EnderecoDTORequest;
import com.bffagendadortarefas.dto.in.TelefoneDTORequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDTOResponse {

    private String nome;
    private String email;
    private String senha;
    private List<EnderecoDTORequest> enderecos;
    private List<TelefoneDTORequest> telefones;

}
