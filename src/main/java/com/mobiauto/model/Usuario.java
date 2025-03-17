package com.mobiauto.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String login;
    private String email;
    private String senha;

    private Boolean ativo;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name="permissao",
        joinColumns =
            @JoinColumn(name="id_usuario"),
        inverseJoinColumns =
            @JoinColumn(name="id_perfil")
    )
    private List<Perfil> perfis;

    @ManyToOne
    @JoinColumn(name = "id_revenda")
    @JsonManagedReference
    private Revenda revenda;


    @OneToMany(mappedBy="usuario")
    @JsonBackReference
    private List<Oportunidade> oportunidade;

    public Usuario() {
    }

    public Usuario(String login, String email, String senha, Boolean ativo, List<Perfil> perfis, Revenda revenda) {
        this.login = login;
        this.email = email;
        this.senha = senha;
        this.ativo = ativo;
        this.perfis = perfis;
        this.revenda = revenda;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
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

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public List<Perfil> getPerfis() {
        return perfis;
    }

    public void setPerfis(List<Perfil> perfis) {
        this.perfis = perfis;
    }

    public Revenda getRevenda() {
        return revenda;
    }

    public void setRevenda(Revenda revenda) {
        this.revenda = revenda;
    }

    public List<Oportunidade> getOportunidade() {
        return oportunidade;
    }

    public void setOportunidade(List<Oportunidade> oportunidade) {
        this.oportunidade = oportunidade;
    }
}
