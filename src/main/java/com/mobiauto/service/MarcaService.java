package com.mobiauto.service;

import com.mobiauto.dao.MarcaRepository;
import com.mobiauto.model.Marca;
import com.mobiauto.model.dto.MarcaDTO;
import com.mobiauto.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MarcaService {

    @Autowired
    private MarcaRepository marcaRepository;

    public Marca salvar(MarcaDTO marcaDTO) throws Exception {
        if (!Utils.validaCnpj(marcaDTO.getCnpj())) {
            throw new Exception("CNPJ Invalido");
        }
        return marcaRepository.save(new Marca(marcaDTO.getNome(), marcaDTO.getCnpj()));
    }

    public List<Marca> buscar() {
        return marcaRepository.findAll();
    }
}
