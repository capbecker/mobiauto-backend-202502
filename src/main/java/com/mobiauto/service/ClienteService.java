package com.mobiauto.service;

import com.mobiauto.dao.ClienteRepository;
import com.mobiauto.model.Cliente;
import com.mobiauto.model.dto.ClienteDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente salvar(ClienteDTO clienteDTO) throws Exception {

        Cliente modelo = new Cliente(clienteDTO.getId(), clienteDTO.getNome(), clienteDTO.getEmail(), clienteDTO.getTelefone());

        return clienteRepository.save(modelo);
    }

    public List<Cliente> buscar() {
        return clienteRepository.findAll();
    }

    public Cliente buscarUm(Long id) {
        return clienteRepository.findById(id).orElseThrow();
    }
}
