package com.mobiauto.controller;


import com.mobiauto.model.Veiculo;
import com.mobiauto.model.dto.VeiculoCreateDTO;
import com.mobiauto.model.dto.VeiculoFilterDTO;
import com.mobiauto.service.VeiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("veiculo")
public class VeiculoController {

    @Autowired
    private VeiculoService veiculoService;

    @PreAuthorize("hasRole('GERENTE')")
    @PostMapping()
    public ResponseEntity<Veiculo> salvar(
            @RequestBody VeiculoCreateDTO veiculoDTO)  {
        try {
            Veiculo veiculo = veiculoService.salvar(veiculoDTO);
            return new ResponseEntity<>(veiculo, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/filtrar")
    public ResponseEntity<List<Veiculo>> filtrar(@RequestBody VeiculoFilterDTO veiculoFilterDTO) {
        try {
            List<Veiculo> veiculo = veiculoService.filtrar(veiculoFilterDTO);
            return new ResponseEntity<>(veiculo, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
}
