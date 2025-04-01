package com.fiap.mecatronica.api.service;

import com.fiap.mecatronica.api.model.Cliente;
import com.fiap.mecatronica.api.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repository;

    public List<Cliente> listarClientes() {
        return repository.findAll();
    }

    public Optional<Cliente> buscarClientePorId(Long id) {
        return repository.findById(id);
    }

    public Cliente salvarCliente(Cliente cliente) {
        return repository.save(cliente);
    }

    public boolean existeCliente(Long id) {
        return repository.existsById(id);
    }

    public boolean excluirCliente(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true; // Indica que foi excluído com sucesso
        }
        return false; // Indica que o cliente não foi encontrado
    }
}