package com.bffagendadortarefas.business;


import com.bffagendadortarefas.dto.in.EnderecoDTORequest;
import com.bffagendadortarefas.dto.in.LoginDTORequest;
import com.bffagendadortarefas.dto.in.TelefoneDTORequest;
import com.bffagendadortarefas.dto.in.UsuarioDTORequest;
import com.bffagendadortarefas.dto.out.EnderecoDTOResponse;
import com.bffagendadortarefas.dto.out.TelefoneDTOResponse;
import com.bffagendadortarefas.dto.out.UsuarioDTOResponse;
import com.bffagendadortarefas.infrastructure.client.UsuarioClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioClient client;

    public UsuarioDTOResponse salvarUsuario(UsuarioDTORequest usuarioDTO, String token) {
        return client.salvarUsuario(usuarioDTO, token);
    }

    public void deletarUsuarioPorEmail(String email, String token) {
        client.deletarUsuarioPorEmail(email, token);
    }

    public UsuarioDTOResponse atualizarDadosUsuario(String token, UsuarioDTORequest usuarioDTO) {
        return client.atualizarDadosUsuario(usuarioDTO, token);
    }

    public UsuarioDTOResponse buscarUsuarioPorEmail(String email, String token) {
        return client.buscarUsuarioPorEmail(email, token);
    }

    public String login(LoginDTORequest usuarioDTO) {
        return client.login(usuarioDTO);
    }

    public EnderecoDTOResponse atualizarEndereco(Long idEndereco, EnderecoDTORequest enderecoDTO, String token) {
        return client.atualizarEndereco(enderecoDTO, idEndereco, token);
    }

    public TelefoneDTOResponse atualizarTelefone(Long idTelefone, TelefoneDTORequest telefoneDTO, String token) {
        return client.atualizarTelefone(telefoneDTO, idTelefone, token);
    }

    public EnderecoDTOResponse cadastrarEndereco(String token, EnderecoDTORequest enderecoDTO) {
        return client.cadastrarEndereco(enderecoDTO, token);

    }

    public TelefoneDTOResponse cadastrarTelefone(String token, TelefoneDTORequest telefoneDTO) {
        return client.cadastrarTelefone(telefoneDTO, token);
    }

}
