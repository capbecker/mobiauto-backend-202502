package com.mobiauto.controller;

import com.mobiauto.model.Oportunidade;
import com.mobiauto.model.dto.OportunidadeCreateDTO;
import com.mobiauto.model.dto.OportunidadeFilterDTO;
import com.mobiauto.model.dto.OportunidadeUpdateDTO;
import com.mobiauto.service.OportunidadeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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
    @Operation(summary = "Salva oportunidade", description =
            "Método destinado em salvar uma oportunidade. " +
            "Necessário estar logado no sistema com perfil nível \"GERENTE\". ")
    @ApiResponse(responseCode = "201", description = "Oportunidade salvo com sucesso")
    @ApiResponse(responseCode = "400", description = "Erro ao salvar a oportunidade")
    @ApiResponse(responseCode = "401", description = "Erro ao autenticar o token")
    public ResponseEntity<Oportunidade> salvar(
            @RequestBody OportunidadeCreateDTO oportunidadeFormDTO)  {
        try {
            Oportunidade oportunidade = oportunidadeService.salvar(oportunidadeFormDTO);
            return new ResponseEntity<>(oportunidade, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("hasRole('ASSISTENTE')")
    @PostMapping("/atualizar")
    @Operation(summary = "Atualiza oportunidade", description =
            "Método destinado em atualizar uma oportunidade. " +
            "Necessário estar logado no sistema com perfil nível \"GERENTE\". " +
            "Diferentes niveis de permissão concede diferentes permissões ao modificar a oportunidade.")
    @ApiResponse(responseCode = "200", description = "Oportunidade atualizado com sucesso")
    @ApiResponse(responseCode = "400", description = "Erro ao atualuizar a oportunidade")
    @ApiResponse(responseCode = "401", description = "Erro ao autenticar o token")
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
    @Operation(summary = "Busca todas as oportunidade",
            description = "Método destinado em buscar as oportunidades. " +
                    "Necessário estar logado no sistema com perfil nível \"ASSISTENTE\". ")
    @ApiResponse(responseCode = "200", description = "Oportunidade encontrada com sucesso")
    @ApiResponse(responseCode = "400", description = "Erro ao buscar a oportunidade")
    @ApiResponse(responseCode = "401", description = "Erro ao autenticar o token")
    public ResponseEntity<List<Oportunidade>> buscar() {
        try {
            List<Oportunidade> oportunidade = oportunidadeService.buscar();
            return new ResponseEntity<>(oportunidade, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    @Operation(summary = "Busca uma oportunidade",
            description = "Método destinado em buscar uma oportunidade com o id especificado. " +
                    "Necessário estar logado no sistema com perfil nível \"ASSISTENTE\".  ")
    @ApiResponse(responseCode = "200", description = "Oportunidade encontrada com sucesso")
    @ApiResponse(responseCode = "400", description = "Erro ao buscar a oportunidade")
    @ApiResponse(responseCode = "401", description = "Erro ao autenticar o token")
    public ResponseEntity<Oportunidade> buscarUm(@PathVariable("id") Long id) {
        try {
            Oportunidade oportunidade = oportunidadeService.buscarUm(id);
            return new ResponseEntity<>(oportunidade, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/filtrar")
    @Operation(summary = "Filtra oportunidades",
            description = "Método destinado em buscar as oportunidade com o filtro dinamico. " +
                    "Necessário estar logado no sistema com perfil nível \"ASSISTENTE\".  ")
    @ApiResponse(responseCode = "200", description = "Oportunidades filtradas com sucesso")
    @ApiResponse(responseCode = "400", description = "Erro ao filtrar as oportunidades")
    @ApiResponse(responseCode = "401", description = "Erro ao autenticar o token")
    public ResponseEntity<List<Oportunidade>> filtrar(@RequestBody OportunidadeFilterDTO oportunidadeFilterDTO) {
        try {
            List<Oportunidade> oportunidades = oportunidadeService.filtrar(oportunidadeFilterDTO);
            return new ResponseEntity<>(oportunidades, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

}
