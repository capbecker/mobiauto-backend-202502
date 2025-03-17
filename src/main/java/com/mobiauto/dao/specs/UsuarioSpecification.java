package com.mobiauto.dao.specs;

import com.mobiauto.model.*;
import com.mobiauto.model.dto.OportunidadeFilterDTO;
import com.mobiauto.model.enums.Status;
import jakarta.persistence.criteria.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class UsuarioSpecification {

    public Specification<Usuario> buscarUsuariosOrdenadoPorQuantidadeOportunidade() {
        return (root, query, criteriaBuilder) -> {

            Subquery<Long> subQueryPerfil = query.subquery(Long.class);
            Root<Perfil> rootPerfilMax = subQueryPerfil.from(Perfil.class);
            Join<Perfil, Usuario> joinUsuarioNoPerfil = rootPerfilMax.join("usuarios", JoinType.LEFT);

            subQueryPerfil.select(criteriaBuilder.max(rootPerfilMax.get("nivel")))
                    .where(criteriaBuilder.equal(joinUsuarioNoPerfil.get("id"), root.get("id")))
                    .groupBy(joinUsuarioNoPerfil.get("id"));

            Subquery<Long> subQueryQtd = query.subquery(Long.class);
            Root<Oportunidade> rootOportunidadeQtd = subQueryQtd.from(Oportunidade.class);
            subQueryQtd.select(
                    criteriaBuilder.count(rootOportunidadeQtd.get("usuario"))
                )
                .where(criteriaBuilder.equal(rootOportunidadeQtd.get("status"), Status.EM_ANDAMENTO))
                .groupBy(rootOportunidadeQtd.get("usuario"));


            Subquery<Long> subQueryMax = query.subquery(Long.class);
            Root<Oportunidade> rootOportunidadeMax = subQueryMax.from(Oportunidade.class);
            subQueryMax.select(
                    criteriaBuilder.max(rootOportunidadeMax.get("dataAtribuicao"))
                )
                .groupBy(rootOportunidadeMax.get("usuario"));


            Predicate subqueryPredicateCount = criteriaBuilder.equal(root.get("id"), subQueryQtd);
            Predicate subqueryPredicateMax = criteriaBuilder.equal(root.get("id"), subQueryMax);

            query.where(criteriaBuilder.and(subqueryPredicateCount, subqueryPredicateMax));
            query.orderBy(criteriaBuilder.desc(subQueryPerfil),criteriaBuilder.asc(subQueryQtd), criteriaBuilder.desc(subqueryPredicateMax));

            return query.getRestriction();
        };
    }
}
