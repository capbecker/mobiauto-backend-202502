package com.mobiauto.controller;

import com.mobiauto.config.security.JWTUtils;
import com.mobiauto.dao.UsuarioRepository;
import com.mobiauto.model.Perfil;
import com.mobiauto.model.Usuario;
import com.mobiauto.model.dto.LoginRequestDTO;
import com.mobiauto.model.dto.LoginResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class LoginController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JWTUtils jwtUtils;

    @Autowired
    UsuarioRepository usuarioRepository;

    @PostMapping("/login")
    @Operation(summary = "Efetua o login", description =
            "Método destinado em logar o usuario e retornar o token. " +
            "Não é necessário estar logado no sistema. ")
    @ApiResponse(responseCode = "200", description = "Logado com sucesso e retornado o token")
    @ApiResponse(responseCode = "401", description = "Erro ao autenticar o token")
    @ApiResponse(responseCode = "404", description = "Erro ao gerar a autenticação")
    public ResponseEntity<?> login(@RequestBody LoginRequestDTO loginRequestDTO) {
        Authentication authentication;
        try {
            authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequestDTO.getUsername(),
                        loginRequestDTO.getPassword()));
        } catch (AuthenticationException e) {
            Map<String, Object> map = new HashMap<>();
            map.put("message", "Falha nas credenciais");
            map.put("status", false);
            return new ResponseEntity<Object>(map, HttpStatus.NOT_FOUND);
        }

        SecurityContextHolder.getContext().setAuthentication(authentication);

        Usuario usuario = usuarioRepository.findByLogin(authentication.getPrincipal().toString()).orElseThrow();

        UserDetails userDetails =  User
                .withUsername(usuario.getLogin())
                .password(usuario.getSenha())
                .roles(usuario.getPerfis()
                    .stream().map(Perfil::getRole).toArray(String[]::new))
                .build();

        String jwtToken = jwtUtils.generateToken(userDetails);

        List<String> roles = userDetails.getAuthorities().stream()
            .map(GrantedAuthority::getAuthority).collect(Collectors.toList());

        LoginResponseDTO loginResponseDTO = new LoginResponseDTO(
                jwtToken, userDetails.getUsername(), roles);

        return ResponseEntity.ok(loginResponseDTO);
    }
}
