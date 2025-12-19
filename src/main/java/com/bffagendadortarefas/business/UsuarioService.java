package com.bffagendadortarefas.business;


import com.bffagendadortarefas.dto.EnderecoDTO;
import com.bffagendadortarefas.dto.TelefoneDTO;
import com.bffagendadortarefas.dto.UsuarioDTO;
import com.bffagendadortarefas.infrastructure.client.UsuarioClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioClient client;

    public UsuarioDTO salvarUsuario(UsuarioDTO usuarioDTO) {
        return client.salvarUsuario(usuarioDTO);
    }

    public void deletarUsuarioPorEmail(String email) {
        client.deletarUsuarioPorEmail(email);
    }

    public UsuarioDTO atualizarDadosUsuario(String token, UsuarioDTO usuarioDTO) {
        return client.atualizarDadosUsuario(usuarioDTO, token);
    }

    public UsuarioDTO buscarUsuarioPorEmail(String email) {
        return client.buscarUsuarioPorEmail(email);
    }

    public String login(UsuarioDTO usuarioDTO) {
        return client.login(usuarioDTO);
    }

    public EnderecoDTO atualizarEndereco(Long idEndereco, EnderecoDTO enderecoDTO) {
        return client.atualizarEndereco(enderecoDTO, idEndereco);
    }

    public TelefoneDTO atualizarTelefone(Long idTelefone, TelefoneDTO telefoneDTO) {
        return client.atualizarTelefone(telefoneDTO, idTelefone);
    }

    public EnderecoDTO cadastrarEndereco(String token, EnderecoDTO enderecoDTO) {
        return client.cadastrarEndereco(enderecoDTO, token);

    }

    public TelefoneDTO cadastrarTelefone(String token, TelefoneDTO telefoneDTO) {
        return client.cadastrarTelefone(telefoneDTO, token);
    }

}
