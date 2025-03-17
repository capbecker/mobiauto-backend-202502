package com.mobiauto.dao.specs;

import com.mobiauto.model.*;
import com.mobiauto.model.dto.OportunidadeFilterDTO;
import com.mobiauto.model.enums.Status;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class OportunidadeSpecification {

    public Specification<Oportunidade> filtrar(OportunidadeFilterDTO oportunidadeFilterDTO) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            Join<Oportunidade, Veiculo> joinVeiculo = root.join("veiculo");
            Join<Veiculo, Modelo> joinModelo = joinVeiculo.join("modelo");
            Join<Modelo, Marca> joinMarca = joinModelo.join("marca");
            Join<Oportunidade, Cliente> joinCliente = root.join("cliente", JoinType.LEFT);

            if (Objects.nonNull(oportunidadeFilterDTO.getMotivoConclusao())) {
                predicates.add(criteriaBuilder.like(root.get("motivoConclusao"), "%"+oportunidadeFilterDTO.getMotivoConclusao()+"%"));
            }
            if (Objects.nonNull(oportunidadeFilterDTO.getStatus())) {
                predicates.add(criteriaBuilder.equal(root.get("status"), oportunidadeFilterDTO.getStatus()));
            }
            if (Objects.nonNull(oportunidadeFilterDTO.getVersaoVeiculo())) {
                predicates.add(criteriaBuilder.like(joinVeiculo.get("versao"), "%"+oportunidadeFilterDTO.getVersaoVeiculo()+"%"));
            }
            if (Objects.nonNull(oportunidadeFilterDTO.getAnoVeiculo())) {
                predicates.add(criteriaBuilder.equal(joinVeiculo.get("ano"), oportunidadeFilterDTO.getAnoVeiculo()));
            }
            if (Objects.nonNull(oportunidadeFilterDTO.getNomeModelo())) {
                predicates.add(criteriaBuilder.like(joinModelo.get("nome"), "%"+oportunidadeFilterDTO.getNomeModelo()+"%"));
            }
            if (Objects.nonNull(oportunidadeFilterDTO.getNomeMarca())) {
                predicates.add(criteriaBuilder.like(joinMarca.get("nome"), "%"+oportunidadeFilterDTO.getNomeMarca()+"%"));
            }
            if (Objects.nonNull(oportunidadeFilterDTO.getNomeCliente())) {
                predicates.add(criteriaBuilder.like(joinCliente.get("nome"), "%"+oportunidadeFilterDTO.getNomeCliente()+"%"));
            }
            if (Objects.nonNull(oportunidadeFilterDTO.getEmailCliente())) {
                predicates.add(criteriaBuilder.like(joinCliente.get("email"), "%"+oportunidadeFilterDTO.getEmailCliente()+"%"));
            }
            if (Objects.nonNull(oportunidadeFilterDTO.getTelefoneCliente())) {
                predicates.add(criteriaBuilder.like(joinCliente.get("telefone"), "%"+oportunidadeFilterDTO.getTelefoneCliente()+"%"));
            }

            return criteriaBuilder.and(predicates.toArray(predicates.toArray(new Predicate[0])));
        };
    }

    public Specification<Oportunidade> buscarOportunidadesSemResponsavel() {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            Join<Oportunidade, Usuario> joinUsuario = root.join("usuario", JoinType.LEFT);
            Join<Oportunidade, Revenda> joinRevenda = root.join("revenda");
            predicates.add(criteriaBuilder.isNull(joinUsuario.get("id")));
            predicates.add(criteriaBuilder.equal(root.get("status"), Status.NOVO));

            query.orderBy(criteriaBuilder.asc(joinRevenda.get("id")),criteriaBuilder.asc(root.get("dataCriacao")));

            return criteriaBuilder.and(predicates.toArray(predicates.toArray(new Predicate[0])));
        };
    }
}
