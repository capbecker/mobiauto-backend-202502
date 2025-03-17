package com.mobiauto.dao;

import com.mobiauto.model.Modelo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ModeloRepository extends JpaRepository<Modelo, Long> {

    Optional<Modelo> findByNome(String modelo);
}