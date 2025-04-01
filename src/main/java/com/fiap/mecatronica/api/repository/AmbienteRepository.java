package com.fiap.mecatronica.api.repository;

import com.fiap.mecatronica.api.model.Ambiente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AmbienteRepository extends JpaRepository<Ambiente, Long> {
}