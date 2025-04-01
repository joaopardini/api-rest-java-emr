package com.fiap.mecatronica.api.model;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "dispositivos")
public class Dispositivo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String tipo; // LUZ, AR_CONDICIONADO
    private boolean ligado;
    private Integer intensidade; // para luzes (0-100%)
    private Integer temperatura; // para ar-condicionado

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ambiente_id")
    @JsonIgnore
    private Ambiente ambiente;

    // Construtores
    public Dispositivo() {
        // Construtor vazio necessário para JPA
    }

    public Dispositivo(String nome, String tipo) {
        this.nome = nome;
        this.tipo = tipo;
        this.ligado = false;
        this.intensidade = tipo.equals("LUZ") ? 100 : null;
        this.temperatura = tipo.equals("AR_CONDICIONADO") ? 22 : null;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public boolean isLigado() {
        return ligado;
    }

    public void setLigado(boolean ligado) {
        this.ligado = ligado;
    }

    public Integer getIntensidade() {
        return intensidade;
    }

    public void setIntensidade(Integer intensidade) {
        this.intensidade = intensidade;
    }

    public Integer getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(Integer temperatura) {
        this.temperatura = temperatura;
    }

    public Ambiente getAmbiente() {
        return ambiente;
    }

    public void setAmbiente(Ambiente ambiente) {
        this.ambiente = ambiente;
    }
}