package com.fiap.mecatronica.api.controller;

import com.fiap.mecatronica.api.model.Ambiente;
import com.fiap.mecatronica.api.service.AmbienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/ambientes")
public class AmbienteController {

    @Autowired
    private AmbienteService ambienteService;

    @GetMapping
    public List<Ambiente> listarAmbientes() {
        return ambienteService.listarAmbientes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ambiente> buscarAmbientePorId(@PathVariable Long id) {
        Optional<Ambiente> ambiente = ambienteService.buscarAmbientePorId(id);
        return ambiente.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Ambiente criarAmbiente(@RequestBody Ambiente ambiente) {
        return ambienteService.salvarAmbiente(ambiente);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Ambiente> atualizarAmbiente(@PathVariable Long id, @RequestBody Ambiente ambienteAtualizado) {
        if (!ambienteService.existeAmbiente(id)) {
            return ResponseEntity.notFound().build();
        }
        ambienteAtualizado.setId(id);
        return ResponseEntity.ok(ambienteService.salvarAmbiente(ambienteAtualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> removerAmbiente(@PathVariable Long id) {
        if (!ambienteService.existeAmbiente(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ambiente não encontrado.");
        }
        ambienteService.excluirAmbiente(id);
        return ResponseEntity.status(HttpStatus.OK).body("Ambiente excluído com sucesso!");
    }
}