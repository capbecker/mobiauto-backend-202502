package com.mobiauto.controller;

import com.mobiauto.model.Oportunidade;
import com.mobiauto.model.dto.OportunidadeCreateDTO;
import com.mobiauto.model.dto.OportunidadeFilterDTO;
import com.mobiauto.model.dto.OportunidadeUpdateDTO;
import com.mobiauto.service.OportunidadeService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("oportunidade")
public class OportunidadeController {


    @Autowired
    private OportunidadeService oportunidadeService;

    @PreAuthorize("hasRole('GERENTE')")
    @PostMapping("/salvar")
    public ResponseEntity<Oportunidade> salvar(
            @RequestBody OportunidadeCreateDTO oportunidadeFormDTO)  {
        try {
            Oportunidade oportunidade = oportunidadeService.salvar(oportunidadeFormDTO);
            return new ResponseEntity<>(oportunidade, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("hasRole('ASSISTENTE')")
    @PostMapping("/atualizar")
    public ResponseEntity<Oportunidade> atualizar(
            @RequestBody OportunidadeUpdateDTO oportunidadeFormDTO)  {
        try {
            Oportunidade oportunidade = oportunidadeService.atualizar(oportunidadeFormDTO);
            return new ResponseEntity<>(oportunidade, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping()
    public ResponseEntity<List<Oportunidade>> buscar() {
        try {
            List<Oportunidade> oportunidade = oportunidadeService.buscar();
            return new ResponseEntity<>(oportunidade, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("hasRole('GERENTE')")
    @GetMapping("/{id}")
    public ResponseEntity<Oportunidade> buscarUm(@PathVariable("id") Long id) {
        try {
            Oportunidade oportunidade = oportunidadeService.buscarUm(id);
            return new ResponseEntity<>(oportunidade, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/filtrar")
    public ResponseEntity<List<Oportunidade>> filtrar(@RequestBody OportunidadeFilterDTO oportunidadeFilterDTO) {
        try {
            List<Oportunidade> oportunidades = oportunidadeService.filtrar(oportunidadeFilterDTO);
            return new ResponseEntity<>(oportunidades, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }



}
