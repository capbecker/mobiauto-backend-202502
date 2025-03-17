package com.mobiauto.service;

import com.mobiauto.dao.RevendaRepository;
import com.mobiauto.model.Revenda;
import com.mobiauto.model.dto.RevendaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RevendaService {

    @Autowired
    private RevendaRepository revendaRepository;

    public Revenda salvar(RevendaDTO revendaDTO) {
        //return revendaRepository.save(new Revenda(revendaDTO.getNome()));
        return null;
    }

    public List<Revenda> buscar() {
        return revendaRepository.findAll();
    }
}
