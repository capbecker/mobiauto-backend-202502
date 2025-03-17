package com.mobiauto.controller;

import com.mobiauto.model.Marca;
import com.mobiauto.model.dto.MarcaDTO;
import com.mobiauto.service.MarcaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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
    @Operation(summary = "Salva marca", description =
            "Método destinado em salvar uma marca. " +
            "Necessário estar logado no sistema com perfil nível \"PROPRIETARIO\". ")
    @ApiResponse(responseCode = "201", description = "Marca salva com sucesso")
    @ApiResponse(responseCode = "400", description = "Erro ao salvar a marca")
    @ApiResponse(responseCode = "401", description = "Erro ao autenticar o token")
    public ResponseEntity<Marca> salvar(
            @RequestBody MarcaDTO marcaDTO)  {
        try {
            Marca marca = marcaService.salvar(marcaDTO);
            return new ResponseEntity<>(marca, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    @Operation(summary = "Busca as marcas", description =
        "Método destinado em salvar a marca. " +
        "Necessário estar logado no sistema com perfil nível \"ASSISTENTE\". ")
    @ApiResponse(responseCode = "200", description = "Marcas buscadas com sucesso")
    @ApiResponse(responseCode = "400", description = "Erro ao buscar a marca")
    @ApiResponse(responseCode = "401", description = "Erro ao autenticar o token")
    public ResponseEntity<List<Marca>> buscar() {
        try {
            List<Marca> marca = marcaService.buscar();
            return new ResponseEntity<>(marca, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
}
