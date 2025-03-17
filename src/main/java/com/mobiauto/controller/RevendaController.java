package com.mobiauto.controller;

import com.mobiauto.model.Revenda;
import com.mobiauto.model.dto.RevendaDTO;
import com.mobiauto.service.RevendaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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
    @PostMapping("/salvar")
    @Operation(summary = "Salva revenda", description =
            "Método destinado em salvar uma revendedora. " +
                    "Necessário estar logado no sistema com perfil nível \"PROPRIETARIO\". ")
    @ApiResponse(responseCode = "201", description = "Revendedora salvo com sucesso")
    @ApiResponse(responseCode = "400", description = "Erro ao salvar a revendedora")
    @ApiResponse(responseCode = "401", description = "Erro ao autenticar o token")
    public ResponseEntity<Revenda> salvar(
            @RequestBody RevendaDTO revendaDTO)  {
        try {
            Revenda revenda = revendaService.salvar(revendaDTO);
            return new ResponseEntity<>(revenda, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping()
    @Operation(summary = "Busca todas as revendedora",
            description = "Método destinado em buscar as revendedoras. " +
                    "Necessário estar logado no sistema com perfil nível \"ASSISTENTE\". ")
    @ApiResponse(responseCode = "200", description = "Revendedora encontrado com sucesso")
    @ApiResponse(responseCode = "400", description = "Erro ao buscar a revendedora")
    @ApiResponse(responseCode = "401", description = "Erro ao autenticar o token")
    public ResponseEntity<List<Revenda>> buscar()  {
        try {
            List<Revenda> revendas = revendaService.buscar();
            return new ResponseEntity<>(revendas, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
}
