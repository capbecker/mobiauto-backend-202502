package com.mobiauto.dao;

import com.mobiauto.model.Revenda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface RevendaRepository extends JpaRepository<Revenda, Long>, JpaSpecificationExecutor<Revenda> {

}