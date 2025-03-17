package com.mobiauto.controller;

import com.mobiauto.model.Usuario;
import com.mobiauto.model.dto.UsuarioFormDTO;
import com.mobiauto.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping()
    public ResponseEntity<Usuario> salvar(
            @RequestBody UsuarioFormDTO usuarioDTO
    )  {
        try {
            Usuario usuario = usuarioService.save(usuarioDTO);
            return new ResponseEntity<>(usuario, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping()
    public ResponseEntity<List<Usuario>> obtemUsuarios() {
        return new ResponseEntity<>(usuarioService.findAllUsuarios(), HttpStatus.OK);
    }
}
