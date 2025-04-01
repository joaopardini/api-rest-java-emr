package com.fiap.mecatronica.api.service;

import com.fiap.mecatronica.api.model.Dispositivo;
import com.fiap.mecatronica.api.repository.DispositivoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DispositivoService {

    @Autowired
    private DispositivoRepository repository;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    public List<Dispositivo> listarDispositivos() {
        return repository.findAll();
    }

    public List<Dispositivo> listarDispositivosPorAmbiente(Long ambienteId) {
        return repository.findByAmbienteId(ambienteId);
    }

    public Optional<Dispositivo> buscarDispositivoPorId(Long id) {
        return repository.findById(id);
    }

    public Dispositivo salvarDispositivo(Dispositivo dispositivo) {
        Dispositivo dispositivoSalvo = repository.save(dispositivo);
        // Enviar atualização pelo WebSocket
        messagingTemplate.convertAndSend("/topic/atualizacoes", dispositivoSalvo);
        return dispositivoSalvo;
    }

    public boolean existeDispositivo(Long id) {
        return repository.existsById(id);
    }

    public boolean excluirDispositivo(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }

    public Optional<Dispositivo> alterarEstado(Long id, boolean ligado) {
        Optional<Dispositivo> dispositivo = repository.findById(id);
        if (dispositivo.isPresent()) {
            Dispositivo d = dispositivo.get();
            d.setLigado(ligado);
            Dispositivo salvo = repository.save(d);
            // Enviar atualização pelo WebSocket
            messagingTemplate.convertAndSend("/topic/atualizacoes", salvo);
            return Optional.of(salvo);
        }
        return Optional.empty();
    }

    public Optional<Dispositivo> ajustarTemperatura(Long id, Integer temperatura) {
        Optional<Dispositivo> dispositivo = repository.findById(id);
        if (dispositivo.isPresent() && "AR_CONDICIONADO".equals(dispositivo.get().getTipo())) {
            Dispositivo d = dispositivo.get();
            d.setTemperatura(temperatura);
            Dispositivo salvo = repository.save(d);
            // Enviar atualização pelo WebSocket
            messagingTemplate.convertAndSend("/topic/atualizacoes", salvo);
            return Optional.of(salvo);
        }
        return Optional.empty();
    }

    public Optional<Dispositivo> ajustarIntensidade(Long id, Integer intensidade) {
        Optional<Dispositivo> dispositivo = repository.findById(id);
        if (dispositivo.isPresent() && "LUZ".equals(dispositivo.get().getTipo())) {
            Dispositivo d = dispositivo.get();
            d.setIntensidade(intensidade);
            Dispositivo salvo = repository.save(d);
            // Enviar atualização pelo WebSocket
            messagingTemplate.convertAndSend("/topic/atualizacoes", salvo);
            return Optional.of(salvo);
        }
        return Optional.empty();
    }
}