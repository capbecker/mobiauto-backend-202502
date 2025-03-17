package com.mobiauto.dao.specs;

import com.mobiauto.model.Marca;
import com.mobiauto.model.Modelo;
import com.mobiauto.model.Veiculo;
import com.mobiauto.model.dto.VeiculoFilterDTO;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class VeiculoSpecification {

    public Specification<Veiculo> filtrar(VeiculoFilterDTO veiculoFilterDTO) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            Join<Veiculo, Modelo> joinModelo = root.join("modelo");
            Join<Modelo, Marca> joinMarca = joinModelo.join("marca");
            if (Objects.nonNull(veiculoFilterDTO.getAno())) {
                predicates.add(criteriaBuilder.equal(root.get("ano"), veiculoFilterDTO.getAno()));
            }
            if (Objects.nonNull(veiculoFilterDTO.getVersao())) {
                predicates.add(criteriaBuilder.like(root.get("versao"), "%"+veiculoFilterDTO.getVersao()+"%"));
            }
            if (Objects.nonNull(veiculoFilterDTO.getNomeModelo())) {
                predicates.add(criteriaBuilder.like(joinModelo.get("nome"), "%"+veiculoFilterDTO.getNomeModelo()+"%"));
            }
            if (Objects.nonNull(veiculoFilterDTO.getNomeMarca())) {
                predicates.add(criteriaBuilder.like(joinMarca.get("nome"), "%"+veiculoFilterDTO.getNomeMarca()+"%"));
            }

            return criteriaBuilder.and(predicates.toArray(predicates.toArray(new Predicate[0])));
        };
    }
}
