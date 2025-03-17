package com.mobiauto.controller;

import com.mobiauto.model.Revenda;
import com.mobiauto.model.dto.RevendaDTO;
import com.mobiauto.service.RevendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("revenda")
public class RevendaController {

    @Autowired
    private RevendaService revendaService;

    @PreAuthorize("hasRole('PROPRIETARIO')")
    @PostMapping()
    public ResponseEntity<Revenda> salvar(
            @RequestBody RevendaDTO revendaDTO)  {
        try {
            Revenda revenda = revendaService.salvar(revendaDTO);
            return new ResponseEntity<>(revenda, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping()
    public ResponseEntity<List<Revenda>> buscar()  {
        try {
            List<Revenda> revendas = revendaService.buscar();
            return new ResponseEntity<>(revendas, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
}
