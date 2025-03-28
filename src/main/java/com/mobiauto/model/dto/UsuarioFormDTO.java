package com.mobiauto.model.dto;

public class UsuarioFormDTO {
    private String nome;
    private String email;
    private String senha;
    private Integer nivel;

    public UsuarioFormDTO() {
    }

    public UsuarioFormDTO(String nome, String email, String senha, Integer nivel) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.nivel = nivel;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Integer getNivel() {
        return nivel;
    }

    public void setNivel(Integer nivel) {
        this.nivel = nivel;
    }
}
