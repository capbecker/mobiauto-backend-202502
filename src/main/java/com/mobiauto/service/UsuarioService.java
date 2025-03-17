package com.mobiauto.service;

//import com.mobiauto.config.AutenticadorService;
import com.mobiauto.dao.PerfilRepository;
import com.mobiauto.dao.UsuarioRepository;
import com.mobiauto.dao.specs.UsuarioSpecification;
import com.mobiauto.model.Oportunidade;
import com.mobiauto.model.Perfil;
import com.mobiauto.model.Usuario;
import com.mobiauto.model.dto.UsuarioFormDTO;
import com.mobiauto.model.enums.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    PerfilRepository perfilRepository;

    @Autowired
    UsuarioSpecification usuarioSpecification;

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


    public List<Usuario> buscarUsuariosOrdenadoPorQuantidadeOportunidade() {
        //Specification<Usuario> spec = usuarioSpecification.buscarUsuariosOrdenadoPorQuantidadeOportunidade();
        List<Usuario> usuarios = usuarioRepository.findAll();
        return usuarios.stream().sorted(getComparing()).collect(Collectors.toList());
    }

    private static Comparator<Usuario> getComparing() {
        return
            Comparator.comparing((Usuario usuario) -> usuario.getPerfis().size())
                .thenComparing((Usuario usuario) ->
                    usuario.getOportunidade().stream().filter(oportunidade -> oportunidade.getStatus().equals(Status.EM_ANDAMENTO)).count())
                ;
    }
}
