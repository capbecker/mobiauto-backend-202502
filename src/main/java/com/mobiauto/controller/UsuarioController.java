package com.mobiauto.controller;

import com.mobiauto.model.Usuario;
import com.mobiauto.model.dto.UsuarioFormDTO;
import com.mobiauto.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PreAuthorize("hasRole('PROPRIETARIO')")
    @PostMapping("/salvar")
    @Operation(summary = "Salva usuario", description =
            "Método destinado em salvar um usuario. " +
                    "Necessário estar logado no sistema com perfil nível \"PROPRIETARIO\". ")
    @ApiResponse(responseCode = "201", description = "Usuario salvo com sucesso")
    @ApiResponse(responseCode = "400", description = "Erro ao salvar a usuario")
    @ApiResponse(responseCode = "401", description = "Erro ao autenticar o token")
    public ResponseEntity<Usuario> salvar(
            @RequestBody UsuarioFormDTO usuarioDTO
    )  {
        try {
            Usuario usuario = usuarioService.save(usuarioDTO);
            return new ResponseEntity<>(usuario, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping()
    @Operation(summary = "Busca todas as revendedora",
            description = "Método destinado em buscar os usuarios. " +
                    "Necessário estar logado no sistema com perfil nível \"ASSISTENTE\". ")
    @ApiResponse(responseCode = "200", description = "Usuario encontrado com sucesso")
    @ApiResponse(responseCode = "400", description = "Erro ao buscar o usuario")
    @ApiResponse(responseCode = "401", description = "Erro ao autenticar o token")
    public ResponseEntity<List<Usuario>> obtemUsuarios() {
        return new ResponseEntity<>(usuarioService.findAllUsuarios(), HttpStatus.OK);
    }
}
