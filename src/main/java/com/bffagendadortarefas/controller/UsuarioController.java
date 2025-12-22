package com.bffagendadortarefas.controller;


import com.bffagendadortarefas.business.UsuarioService;
import com.bffagendadortarefas.dto.EnderecoDTO;
import com.bffagendadortarefas.dto.TelefoneDTO;
import com.bffagendadortarefas.dto.UsuarioDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
@RequiredArgsConstructor
@Tag(name = "Usuários", description = "Cadastro, Login e Administração de Usuário")
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
    public ResponseEntity<UsuarioDTO> salvarUsuario(
            @RequestBody UsuarioDTO usuarioDTO,
            @RequestHeader("Authorization") String token) {

        return ResponseEntity.ok(usuarioService.salvarUsuario(usuarioDTO));
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
            @RequestBody UsuarioDTO usuarioDTO,
            @RequestHeader("Authorization") String token) {

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
    public ResponseEntity<UsuarioDTO> buscarUsuarioPorEmail(
            @RequestParam("email") String email,
            @RequestHeader("Authorization") String token) {

        return ResponseEntity.ok(usuarioService.buscarUsuarioPorEmail(email));
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
            @RequestHeader("Authorization") String token) {

        usuarioService.deletarUsuarioPorEmail(email);
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
    public ResponseEntity<UsuarioDTO> atualizarDadosUsuario(
            @RequestBody UsuarioDTO usuarioDTO,
            @RequestHeader("Authorization") String token) {

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
    public ResponseEntity<EnderecoDTO> atualizarEndereco(
            @RequestBody EnderecoDTO enderecoDTO,
            @RequestParam("id") Long id,
            @RequestHeader("Authorization") String token) {

        return ResponseEntity.ok(usuarioService.atualizarEndereco(id, enderecoDTO));
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
    public ResponseEntity<TelefoneDTO> atualizarTelefone(
            @RequestBody TelefoneDTO telefoneDTO,
            @RequestParam("id") Long id,
            @RequestHeader("Authorization") String token) {

        return ResponseEntity.ok(usuarioService.atualizarTelefone(id, telefoneDTO));
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
    public ResponseEntity<EnderecoDTO> cadastrarEndereco(
            @RequestBody EnderecoDTO enderecoDTO,
            @RequestHeader("Authorization") String token) {

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
    public ResponseEntity<TelefoneDTO> cadastrarTelefone(
            @RequestBody TelefoneDTO telefoneDTO,
            @RequestHeader("Authorization") String token) {

        return ResponseEntity.ok(usuarioService.cadastrarTelefone(token, telefoneDTO));
    }
}