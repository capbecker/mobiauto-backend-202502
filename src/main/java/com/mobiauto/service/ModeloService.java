package com.mobiauto.service;

import com.mobiauto.dao.MarcaRepository;
import com.mobiauto.dao.ModeloRepository;
import com.mobiauto.model.Marca;
import com.mobiauto.model.Modelo;
import com.mobiauto.model.dto.MarcaDTO;
import com.mobiauto.model.dto.ModeloDTO;
import com.mobiauto.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ModeloService {

    @Autowired
    private MarcaRepository marcaRepository;

    @Autowired
    private ModeloRepository modeloRepository;

    public Modelo salvar(ModeloDTO modeloDTO) throws Exception {
        Marca marca = marcaRepository.findById(modeloDTO.getIdMarca()).orElseThrow();

        Modelo modelo = new Modelo(modeloDTO.getNome(), marca);

        return modeloRepository.save(modelo);
    }

    public List<Modelo> buscar() {
        return modeloRepository.findAll();
    }
}
