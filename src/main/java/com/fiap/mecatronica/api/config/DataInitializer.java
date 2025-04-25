package com.fiap.mecatronica.api.config;

import com.fiap.mecatronica.api.model.Ambiente;
import com.fiap.mecatronica.api.model.Dispositivo;
import com.fiap.mecatronica.api.repository.AmbienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer {

    @Autowired
    private AmbienteRepository ambienteRepository;

    @Bean
    CommandLineRunner initDatabase() {
        return args -> {
            if (ambienteRepository.count() == 0) {
                // Criar ambientes
                Ambiente sala = new Ambiente("Sala", "Sala de estar principal");
                Ambiente cozinha = new Ambiente("Cozinha", "Cozinha integrada");
                Ambiente quarto = new Ambiente("Quarto Principal", "Quarto de casal");
                Ambiente banheiro = new Ambiente("Banheiro", "Banheiro social");

                // Criar dispositivos para Sala
                Dispositivo luzSala = new Dispositivo("Luz Principal", "LUZ");
                Dispositivo arSala = new Dispositivo("Ar-Condicionado", "AR_CONDICIONADO");
                sala.adicionarDispositivo(luzSala);
                sala.adicionarDispositivo(arSala);

                // Criar dispositivos para Cozinha
                Dispositivo luzCozinha = new Dispositivo("Luz Principal", "LUZ");
                Dispositivo luzPia = new Dispositivo("Luz da Pia", "LUZ");
                cozinha.adicionarDispositivo(luzCozinha);
                cozinha.adicionarDispositivo(luzPia);

                // Criar dispositivos para Quarto
                Dispositivo luzQuarto = new Dispositivo("Luz Principal", "LUZ");
                Dispositivo arQuarto = new Dispositivo("Ar-Condicionado", "AR_CONDICIONADO");
                quarto.adicionarDispositivo(luzQuarto);
                quarto.adicionarDispositivo(arQuarto);

                // Criar dispositivos para Banheiro
                Dispositivo luzBanheiro = new Dispositivo("Luz Principal", "LUZ");
                banheiro.adicionarDispositivo(luzBanheiro);

                // Salvar ambientes (e seus dispositivos em cascata)
                ambienteRepository.save(sala);
                ambienteRepository.save(cozinha);
                ambienteRepository.save(quarto);
                ambienteRepository.save(banheiro);

                System.out.println("Base de dados inicializada com ambientes e dispositivos de exemplo!");
            }
        };
    }
}
