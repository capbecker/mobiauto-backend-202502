package com.mobiauto.service;

//import com.mobiauto.config.AutenticadorService;
import com.mobiauto.config.JWTUtils;
import com.mobiauto.dao.PerfilRepository;
import com.mobiauto.dao.UsuarioRepository;
import com.mobiauto.model.Perfil;
import com.mobiauto.model.Revenda;
import com.mobiauto.model.Usuario;
import com.mobiauto.model.dto.UsuarioFormDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    PerfilRepository perfilRepository;

    public Usuario getUsuarioLogado() {
        UserDetails principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return usuarioRepository.findByLogin(principal.getUsername()).orElse(null);
    }

    private String ROLE_ASSISTENTE = "ASSISTENTE";

    public Usuario save(UsuarioFormDTO usuarioFormDTO) {
        try {
            List<Perfil> perfis = new ArrayList<>();
            Usuario usuarioLogado = getUsuarioLogado();
            if (Objects.nonNull(usuarioFormDTO.getNivel())) {
                perfis = perfilRepository.findByNivelLessThanEqual(usuarioFormDTO.getNivel());
            } else {
                perfis.add(perfilRepository.findByRole(ROLE_ASSISTENTE).get());
            }

            Usuario usuario = new Usuario(usuarioFormDTO.getNome(), usuarioFormDTO.getEmail(), usuarioFormDTO.getSenha(), true, perfis, usuarioLogado.getRevenda());
            usuarioRepository.save(usuario);
            return usuario;
        } catch (Exception e) {
            return null;
        }
    }


    public List<Usuario> findAllUsuarios() {
        return usuarioRepository.findAll();
    }
}
