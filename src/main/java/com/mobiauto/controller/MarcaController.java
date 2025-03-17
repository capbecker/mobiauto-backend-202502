package com.mobiauto.controller;

import com.mobiauto.model.Marca;
import com.mobiauto.model.dto.MarcaDTO;
import com.mobiauto.service.MarcaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("marca")
public class MarcaController {

    @Autowired
    private MarcaService marcaService;

    @PreAuthorize("hasRole('PROPRIETARIO')")
    @PostMapping("/salvar")
    public ResponseEntity<Marca> salvar(
            @RequestBody MarcaDTO marcaDTO)  {
        try {
            Marca marca = marcaService.salvar(marcaDTO);
            return new ResponseEntity<>(marca, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<List<Marca>> buscar() {
        try {
            List<Marca> marca = marcaService.buscar();
            return new ResponseEntity<>(marca, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
}
