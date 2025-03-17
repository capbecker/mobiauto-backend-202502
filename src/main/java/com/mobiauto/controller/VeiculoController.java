package com.mobiauto.controller;


import com.mobiauto.model.Veiculo;
import com.mobiauto.model.dto.VeiculoCreateDTO;
import com.mobiauto.model.dto.VeiculoFilterDTO;
import com.mobiauto.service.VeiculoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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
    @PostMapping("/salvar")
    @Operation(summary = "Salva Veiculo", description =
            "Método destinado em salvar um veiculo. " +
                    "Necessário estar logado no sistema com perfil nível \"GERENTE\". ")
    @ApiResponse(responseCode = "201", description = "Veiculo salvo com sucesso")
    @ApiResponse(responseCode = "400", description = "Erro ao salvar o veiculo")
    @ApiResponse(responseCode = "401", description = "Erro ao autenticar o token")
    public ResponseEntity<Veiculo> salvar(
            @RequestBody VeiculoCreateDTO veiculoDTO)  {
        try {
            Veiculo veiculo = veiculoService.salvar(veiculoDTO);
            return new ResponseEntity<>(veiculo, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/filtrar")
    @Operation(summary = "Filtra Veiculos",
            description = "Método destinado em buscar os veiculo com o filtro dinamico. " +
                    "Necessário estar logado no sistema com perfil nível \"ASSISTENTE\".  ")
    @ApiResponse(responseCode = "200", description = "Veiculos filtrados com sucesso")
    @ApiResponse(responseCode = "400", description = "Erro ao filtrar os veiculos")
    @ApiResponse(responseCode = "401", description = "Erro ao autenticar o token")
    public ResponseEntity<List<Veiculo>> filtrar(@RequestBody VeiculoFilterDTO veiculoFilterDTO) {
        try {
            List<Veiculo> veiculo = veiculoService.filtrar(veiculoFilterDTO);
            return new ResponseEntity<>(veiculo, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
}
