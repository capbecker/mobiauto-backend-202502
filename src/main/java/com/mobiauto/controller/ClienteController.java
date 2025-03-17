package com.mobiauto.controller;

import com.mobiauto.model.Cliente;
import com.mobiauto.model.dto.ClienteDTO;
import com.mobiauto.service.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("cliente")
public class ClienteController {

    @Autowired
    ClienteService clienteService;

    @PostMapping("/salvar")
    @Operation(summary = "Salva num novo cliente", description = "Método destinado em salvar novos clientes")
    @ApiResponse(responseCode = "201", description = "Cliente salvo com sucesso")
    @ApiResponse(responseCode = "400", description = "Erro ao salvar o cliente")
    @ApiResponse(responseCode = "401", description = "Erro ao autenticar o token")
    public ResponseEntity<Cliente> salvar(
            @RequestBody ClienteDTO clienteDTO)  {
        try {
            Cliente cliente = clienteService.salvar(clienteDTO);
            return new ResponseEntity<>(cliente, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping()
    @Operation(summary = "Busca todos os clientes",
            description = "Método destinado em buscar os clientes. " +
                    "Necessário estar logado no sistema com perfil nível \"ASSISTENTE\". ")
    @ApiResponse(responseCode = "200", description = "Cliente encontrado com sucesso")
    @ApiResponse(responseCode = "400", description = "Erro ao buscar o cliente")
    @ApiResponse(responseCode = "401", description = "Erro ao autenticar o token")
    public ResponseEntity<List<Cliente>> buscar() {
        try {
            List<Cliente> cliente = clienteService.buscar();
            return new ResponseEntity<>(cliente, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    @Operation(summary = "Busca um cliente",
            description = "Método destinado em buscar um cliente com o id especificado. " +
                    "Necessário estar logado no sistema com perfil nível \"ASSISTENTE\".  ")
    @ApiResponse(responseCode = "200", description = "Cliente encontrado com sucesso")
    @ApiResponse(responseCode = "400", description = "Erro ao buscar o cliente")
    @ApiResponse(responseCode = "401", description = "Erro ao autenticar o token")

    public ResponseEntity<Cliente> buscarUm(@PathVariable("id") Long id) {
        try {
            Cliente cliente = clienteService.buscarUm(id);
            return new ResponseEntity<>(cliente, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
}
