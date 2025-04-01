package com.fiap.mecatronica.api.repository;

import com.fiap.mecatronica.api.model.Dispositivo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DispositivoRepository extends JpaRepository<Dispositivo, Long> {
    List<Dispositivo> findByAmbienteId(Long ambienteId);
}