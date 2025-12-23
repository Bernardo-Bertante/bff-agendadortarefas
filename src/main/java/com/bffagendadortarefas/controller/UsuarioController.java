package com.bffagendadortarefas.controller;


import com.bffagendadortarefas.business.UsuarioService;
import com.bffagendadortarefas.dto.in.*;
import com.bffagendadortarefas.dto.out.EnderecoDTOResponse;
import com.bffagendadortarefas.dto.out.TelefoneDTOResponse;
import com.bffagendadortarefas.dto.out.UsuarioDTOResponse;
import com.bffagendadortarefas.infrastructure.security.SecurityConfig;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
@RequiredArgsConstructor
@Tag(name = "Usuários", description = "Cadastro, Login e Administração de Usuário")
@SecurityRequirement(name = SecurityConfig.SECURITY_SCHEME)
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping
    @Operation(
            summary = "Cadastrar usuário",
            description = "Cadastra um novo usuário no sistema"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Usuário cadastrado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos"),
            @ApiResponse(responseCode = "409", description = "Email já cadastrado"),
            @ApiResponse(responseCode = "401", description = "Não autorizado")
    })
    public ResponseEntity<UsuarioDTOResponse> salvarUsuario(
            @RequestBody UsuarioDTORequest usuarioDTO,
            @RequestHeader(name = "Authorization", required = false) String token) {

        return ResponseEntity.ok(usuarioService.salvarUsuario(usuarioDTO, token));
    }

    @PostMapping("/login")
    @Operation(
            summary = "Login do usuário",
            description = "Autentica o usuário e retorna o token JWT"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Login realizado com sucesso"),
            @ApiResponse(responseCode = "401", description = "Credenciais inválidas"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    public String login(
            @RequestBody LoginDTORequest usuarioDTO) {

        return usuarioService.login(usuarioDTO);
    }

    @GetMapping
    @Operation(
            summary = "Buscar usuário por email",
            description = "Retorna os dados de um usuário a partir do email"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Usuário encontrado"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado"),
            @ApiResponse(responseCode = "401", description = "Não autorizado")
    })
    public ResponseEntity<UsuarioDTOResponse> buscarUsuarioPorEmail(
            @RequestParam("email") String email,
            @RequestHeader(name = "Authorization", required = false)String token) {

        return ResponseEntity.ok(usuarioService.buscarUsuarioPorEmail(email, token));
    }

    @DeleteMapping("/{email}")
    @Operation(
            summary = "Deletar usuário",
            description = "Remove um usuário do sistema pelo email"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Usuário removido com sucesso"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado"),
            @ApiResponse(responseCode = "401", description = "Não autorizado")
    })
    public ResponseEntity<Void> deletarUsuarioPorEmail(
            @PathVariable String email,
            @RequestHeader(name = "Authorization", required = false)String token) {

        usuarioService.deletarUsuarioPorEmail(email, token);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    @Operation(
            summary = "Atualizar dados do usuário",
            description = "Atualiza os dados do usuário autenticado"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Dados atualizados com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos"),
            @ApiResponse(responseCode = "401", description = "Não autorizado")
    })
    public ResponseEntity<UsuarioDTOResponse> atualizarDadosUsuario(
            @RequestBody UsuarioDTORequest usuarioDTO,
            @RequestHeader(name = "Authorization", required = false)String token) {

        return ResponseEntity.ok(usuarioService.atualizarDadosUsuario(token, usuarioDTO));
    }

    @PutMapping("/endereco")
    @Operation(
            summary = "Atualizar endereço",
            description = "Atualiza um endereço existente do usuário"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Endereço atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Endereço não encontrado"),
            @ApiResponse(responseCode = "401", description = "Não autorizado")
    })
    public ResponseEntity<EnderecoDTOResponse> atualizarEndereco(
            @RequestBody EnderecoDTORequest enderecoDTO,
            @RequestParam("id") Long id,
            @RequestHeader(name = "Authorization", required = false)String token) {

        return ResponseEntity.ok(usuarioService.atualizarEndereco(id, enderecoDTO, token));
    }

    @PutMapping("/telefone")
    @Operation(
            summary = "Atualizar telefone",
            description = "Atualiza um telefone existente do usuário"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Telefone atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Telefone não encontrado"),
            @ApiResponse(responseCode = "401", description = "Não autorizado")
    })
    public ResponseEntity<TelefoneDTOResponse> atualizarTelefone(
            @RequestBody TelefoneDTORequest telefoneDTO,
            @RequestParam("id") Long id,
            @RequestHeader(name = "Authorization", required = false)String token) {

        return ResponseEntity.ok(usuarioService.atualizarTelefone(id, telefoneDTO, token));
    }

    @PostMapping("/endereco")
    @Operation(
            summary = "Cadastrar endereço",
            description = "Cadastra um novo endereço para o usuário autenticado"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Endereço cadastrado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos"),
            @ApiResponse(responseCode = "401", description = "Não autorizado")
    })
    public ResponseEntity<EnderecoDTOResponse> cadastrarEndereco(
            @RequestBody EnderecoDTORequest enderecoDTO,
            @RequestHeader(name = "Authorization", required = false)String token) {

        return ResponseEntity.ok(usuarioService.cadastrarEndereco(token, enderecoDTO));
    }

    @PostMapping("/telefone")
    @Operation(
            summary = "Cadastrar telefone",
            description = "Cadastra um novo telefone para o usuário autenticado"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Telefone cadastrado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos"),
            @ApiResponse(responseCode = "401", description = "Não autorizado")
    })
    public ResponseEntity<TelefoneDTOResponse> cadastrarTelefone(
            @RequestBody TelefoneDTORequest telefoneDTO,
            @RequestHeader(name = "Authorization", required = false)String token) {

        return ResponseEntity.ok(usuarioService.cadastrarTelefone(token, telefoneDTO));
    }
}