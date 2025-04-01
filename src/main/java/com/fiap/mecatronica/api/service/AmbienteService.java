package com.fiap.mecatronica.api.service;

import com.fiap.mecatronica.api.model.Ambiente;
import com.fiap.mecatronica.api.repository.AmbienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AmbienteService {

    @Autowired
    private AmbienteRepository repository;

    public List<Ambiente> listarAmbientes() {
        return repository.findAll();
    }

    public Optional<Ambiente> buscarAmbientePorId(Long id) {
        return repository.findById(id);
    }

    public Ambiente salvarAmbiente(Ambiente ambiente) {
        return repository.save(ambiente);
    }

    public boolean existeAmbiente(Long id) {
        return repository.existsById(id);
    }

    public boolean excluirAmbiente(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }
}