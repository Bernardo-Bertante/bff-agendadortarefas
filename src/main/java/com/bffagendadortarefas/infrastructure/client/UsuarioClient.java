package com.bffagendadortarefas.infrastructure.client;


import com.bffagendadortarefas.dto.in.EnderecoDTORequest;
import com.bffagendadortarefas.dto.in.LoginDTORequest;
import com.bffagendadortarefas.dto.in.TelefoneDTORequest;
import com.bffagendadortarefas.dto.in.UsuarioDTORequest;
import com.bffagendadortarefas.dto.out.EnderecoDTOResponse;
import com.bffagendadortarefas.dto.out.TelefoneDTOResponse;
import com.bffagendadortarefas.dto.out.UsuarioDTOResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "usuario", url = "${usuario.url}")
public interface UsuarioClient {

    @GetMapping
    UsuarioDTOResponse buscarUsuarioPorEmail(@RequestParam("email") String email,
                                             @RequestHeader("Authorization") String token);

    @PostMapping
    UsuarioDTOResponse salvarUsuario(@RequestBody UsuarioDTORequest usuarioDTO,
                                     @RequestHeader("Authorization") String token);

    @PostMapping("/login")
    String login(@RequestBody LoginDTORequest usuarioDTO);

    @DeleteMapping("/{email}")
    void deletarUsuarioPorEmail(@PathVariable String email,
                                @RequestHeader("Authorization") String token);

    @PutMapping
    UsuarioDTOResponse atualizarDadosUsuario(@RequestBody UsuarioDTORequest usuarioDTO,
                                             @RequestHeader("Authorization") String token);

    @PutMapping("/endereco")
    EnderecoDTOResponse atualizarEndereco(@RequestBody EnderecoDTORequest enderecoDTO,
                                          @RequestParam("id") Long id,
                                          @RequestHeader("Authorization") String token);

    @PutMapping("/telefone")
    TelefoneDTOResponse atualizarTelefone(@RequestBody TelefoneDTORequest telefoneDTO,
                                          @RequestParam("id") Long id,
                                          @RequestHeader("Authorization") String token);

    @PostMapping("/endereco")
    EnderecoDTOResponse cadastrarEndereco(@RequestBody EnderecoDTORequest enderecoDTO,
                                          @RequestHeader("Authorization") String token);

    @PostMapping("/telefone")
    TelefoneDTOResponse cadastrarTelefone(@RequestBody TelefoneDTORequest telefoneDTO,
                                          @RequestHeader("Authorization") String token);
}
