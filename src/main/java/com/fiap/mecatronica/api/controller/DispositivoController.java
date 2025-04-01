package com.fiap.mecatronica.api.controller;

import com.fiap.mecatronica.api.model.Dispositivo;
import com.fiap.mecatronica.api.service.DispositivoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/dispositivos")
public class DispositivoController {

    @Autowired
    private DispositivoService dispositivoService;

    @GetMapping
    public List<Dispositivo> listarDispositivos() {
        return dispositivoService.listarDispositivos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Dispositivo> buscarDispositivoPorId(@PathVariable Long id) {
        Optional<Dispositivo> dispositivo = dispositivoService.buscarDispositivoPorId(id);
        return dispositivo.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/ambiente/{ambienteId}")
    public List<Dispositivo> listarDispositivosPorAmbiente(@PathVariable Long ambienteId) {
        return dispositivoService.listarDispositivosPorAmbiente(ambienteId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Dispositivo criarDispositivo(@RequestBody Dispositivo dispositivo) {
        return dispositivoService.salvarDispositivo(dispositivo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Dispositivo> atualizarDispositivo(@PathVariable Long id, @RequestBody Dispositivo dispositivoAtualizado) {
        if (!dispositivoService.existeDispositivo(id)) {
            return ResponseEntity.notFound().build();
        }
        dispositivoAtualizado.setId(id);
        return ResponseEntity.ok(dispositivoService.salvarDispositivo(dispositivoAtualizado));
    }

    @PatchMapping("/{id}/ligar")
    public ResponseEntity<Dispositivo> ligarDispositivo(@PathVariable Long id) {
        return dispositivoService.alterarEstado(id, true)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PatchMapping("/{id}/desligar")
    public ResponseEntity<Dispositivo> desligarDispositivo(@PathVariable Long id) {
        return dispositivoService.alterarEstado(id, false)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PatchMapping("/{id}/temperatura")
    public ResponseEntity<Dispositivo> ajustarTemperatura(@PathVariable Long id, @RequestBody Map<String, Integer> payload) {
        Integer temperatura = payload.get("temperatura");
        if (temperatura == null) {
            return ResponseEntity.badRequest().build();
        }

        return dispositivoService.ajustarTemperatura(id, temperatura)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PatchMapping("/{id}/intensidade")
    public ResponseEntity<Dispositivo> ajustarIntensidade(@PathVariable Long id, @RequestBody Map<String, Integer> payload) {
        Integer intensidade = payload.get("intensidade");
        if (intensidade == null || intensidade < 0 || intensidade > 100) {
            return ResponseEntity.badRequest().build();
        }

        return dispositivoService.ajustarIntensidade(id, intensidade)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> removerDispositivo(@PathVariable Long id) {
        if (!dispositivoService.existeDispositivo(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Dispositivo não encontrado.");
        }
        dispositivoService.excluirDispositivo(id);
        return ResponseEntity.status(HttpStatus.OK).body("Dispositivo excluído com sucesso!");
    }
}