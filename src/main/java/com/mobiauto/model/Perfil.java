package com.mobiauto.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Perfil {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer nivel;
    private String nome;
    private String role;

    @ManyToMany(mappedBy = "perfis")
    private List<Usuario> usuarios;

    public Perfil() {
    }

    public Perfil(Integer nivel, String nome, String role) {
        this.nivel = nivel;
        this.nome = nome;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNivel() {
        return nivel;
    }

    public void setNivel(Integer nivel) {
        this.nivel = nivel;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
