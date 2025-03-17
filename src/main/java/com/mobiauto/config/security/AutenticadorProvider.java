package com.mobiauto.config.security;

import com.mobiauto.dao.UsuarioRepository;
import com.mobiauto.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

@Service
public class AutenticadorProvider implements AuthenticationProvider {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        Usuario usuario = usuarioRepository.findByLogin(authentication.getName()).orElseThrow();
        String senha = authentication.getCredentials().toString();
        return checarSenha(usuario, senha);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }

    private Authentication checarSenha(Usuario usuario, String senha) {
        if (Objects.equals(senha, usuario.getSenha())) {
            Collection<GrantedAuthority> roles = new ArrayList<>();
            usuario.getPerfis().forEach(perfil->roles.add(new SimpleGrantedAuthority("ROLE_" + perfil.getRole())));
            return new UsernamePasswordAuthenticationToken(usuario.getLogin(), usuario.getSenha(), roles);
        } else {
            throw new BadCredentialsException("Bad credentials");
        }
    }
}
