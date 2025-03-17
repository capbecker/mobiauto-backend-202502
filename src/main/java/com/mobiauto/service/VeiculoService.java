package com.mobiauto.service;

import com.mobiauto.dao.ModeloRepository;
import com.mobiauto.dao.VeiculoRepository;
import com.mobiauto.dao.specs.VeiculoSpecification;
import com.mobiauto.model.Modelo;
import com.mobiauto.model.Veiculo;
import com.mobiauto.model.dto.VeiculoCreateDTO;
import com.mobiauto.model.dto.VeiculoFilterDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VeiculoService {

    @Autowired
    private ModeloRepository modeloRepository;

    @Autowired
    private VeiculoRepository veiculoRepository;

    @Autowired
    private VeiculoSpecification veiculoSpecification;

    public Veiculo salvar(VeiculoCreateDTO veiculoDTO) throws Exception {
        Modelo modelo = modeloRepository.findById(veiculoDTO.getIdModelo()).orElseThrow();

        Veiculo veiculo = new Veiculo(veiculoDTO.getVersao(), veiculoDTO.getAno(), modelo);

        return veiculoRepository.save(veiculo);
    }

    public List<Veiculo> filtrar(VeiculoFilterDTO veiculoFilterDTO) {
        Specification<Veiculo> spec = veiculoSpecification.filtrar(veiculoFilterDTO);
        return veiculoRepository.findAll(spec);
    }
}
