package com.mobiauto.controller;

import com.mobiauto.model.Cliente;
import com.mobiauto.model.dto.ClienteDTO;
import com.mobiauto.service.ClienteService;
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
    public ResponseEntity<Cliente> salvar(
            @RequestBody ClienteDTO clienteDTO)  {
        try {
            Cliente cliente = clienteService.salvar(clienteDTO);
            return new ResponseEntity<>(cliente, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping()
    public ResponseEntity<List<Cliente>> buscar() {
        try {
            List<Cliente> cliente = clienteService.buscar();
            return new ResponseEntity<>(cliente, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> buscarUm(@PathVariable("id") Long id) {
        try {
            Cliente cliente = clienteService.buscarUm(id);
            return new ResponseEntity<>(cliente, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
}
