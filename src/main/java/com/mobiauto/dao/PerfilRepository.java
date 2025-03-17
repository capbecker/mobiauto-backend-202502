package com.mobiauto.dao;

import com.mobiauto.model.Perfil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PerfilRepository extends JpaRepository<Perfil, Long>, JpaSpecificationExecutor<Perfil> {

    Optional<Perfil> findByRole(String roleAssistente);

    List<Perfil> findByNivelLessThanEqual(Integer nivel_perfil);
}