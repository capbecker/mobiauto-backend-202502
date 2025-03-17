package com.mobiauto.controller;

import com.mobiauto.dao.PerfilRepository;
import com.mobiauto.model.Marca;
import com.mobiauto.model.Modelo;
import com.mobiauto.model.dto.MarcaDTO;
import com.mobiauto.model.dto.ModeloDTO;
import com.mobiauto.service.ModeloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("modelo")
public class ModeloController {

    @Autowired
    private ModeloService modeloService;

    @PreAuthorize("hasRole('ADMINISTRADOR')")
    @PostMapping()
    public ResponseEntity<Modelo> salvar(
            @RequestBody ModeloDTO modeloDTO)  {
        try {
            Modelo modelo = modeloService.salvar(modeloDTO);
            return new ResponseEntity<>(modelo, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping()
    public ResponseEntity<List<Modelo>> buscar()  {
        try {
            List<Modelo> modelo = modeloService.buscar();
            return new ResponseEntity<>(modelo, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }


}
