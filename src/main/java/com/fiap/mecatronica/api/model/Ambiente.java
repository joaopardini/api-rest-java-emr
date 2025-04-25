package com.fiap.mecatronica.api.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ambientes")
public class Ambiente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String descricao;

    @OneToMany(mappedBy = "ambiente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Dispositivo> dispositivos = new ArrayList<>();

    // Construtores
    public Ambiente() {
        // Construtor vazio necessário para JPA
    }

    public Ambiente(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public List<Dispositivo> getDispositivos() {
        return dispositivos;
    }

    public void setDispositivos(List<Dispositivo> dispositivos) {
        this.dispositivos = dispositivos;
    }

    // Método helper para adicionar dispositivo
    public void adicionarDispositivo(Dispositivo dispositivo) {
        dispositivos.add(dispositivo);
        dispositivo.setAmbiente(this);
    }

    // Método helper para remover dispositivo
    public void removerDispositivo(Dispositivo dispositivo) {
        dispositivos.remove(dispositivo);
        dispositivo.setAmbiente(null);
    }
}