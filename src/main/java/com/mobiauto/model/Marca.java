package com.mobiauto.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Marca {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String cnpj;

    @OneToMany(mappedBy = "marca")
    @JsonBackReference
    private List<Modelo> modelos;

    public Marca() {
    }

    public Marca(String nome, String cnpj) {
        this.nome = nome;
        this.cnpj = cnpj;
    }

    public Marca(String nome, String cnpj, List<Modelo> modelos) {
        this.id = id;
        this.nome = nome;
        this.cnpj = cnpj;
        this.modelos = modelos;
    }

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

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public List<Modelo> getModelos() {
        return modelos;
    }

    public void setModelos(List<Modelo> modelos) {
        this.modelos = modelos;
    }
}
