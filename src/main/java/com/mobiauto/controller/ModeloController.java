package com.mobiauto.controller;

import com.mobiauto.dao.PerfilRepository;
import com.mobiauto.model.Marca;
import com.mobiauto.model.Modelo;
import com.mobiauto.model.dto.MarcaDTO;
import com.mobiauto.model.dto.ModeloDTO;
import com.mobiauto.service.ModeloService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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
    @PostMapping("/salvar")
    @Operation(summary = "Salva modelo", description =
            "Método destinado em salvar um modelo. " +
            "Necessário estar logado no sistema com perfil nível \"ADMINISTRADOR\". ")
    @ApiResponse(responseCode = "201", description = "Modelo salvo com sucesso")
    @ApiResponse(responseCode = "400", description = "Erro ao salvar a marca")
    @ApiResponse(responseCode = "401", description = "Erro ao autenticar o token")
    public ResponseEntity<Modelo> salvar(
            @RequestBody ModeloDTO modeloDTO)  {
        try {
            Modelo modelo = modeloService.salvar(modeloDTO);
            return new ResponseEntity<>(modelo, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping()
    @Operation(summary = "Busca os modelos", description =
            "Método destinado em salvar o modelo. " +
            "Necessário estar logado no sistema com perfil nível \"ASSISTENTE\". ")
    @ApiResponse(responseCode = "200", description = "Modelos buscados com sucesso")
    @ApiResponse(responseCode = "400", description = "Erro ao buscar o modelo")
    @ApiResponse(responseCode = "401", description = "Erro ao autenticar o token")
    public ResponseEntity<List<Modelo>> buscar()  {
        try {
            List<Modelo> modelo = modeloService.buscar();
            return new ResponseEntity<>(modelo, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }


}
