package com.mobiauto.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Revenda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="razao_social")
    private String razaoSocial;

    private String cnpj;

    @OneToMany(mappedBy="revenda")
    @JsonBackReference
    private List<Oportunidade> oportunidade;

    @OneToMany(mappedBy="revenda")
    @JsonBackReference
    private List<Usuario> usuario;

    public Revenda() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public List<Oportunidade> getOportunidade() {
        return oportunidade;
    }

    public void setOportunidade(List<Oportunidade> oportunidade) {
        this.oportunidade = oportunidade;
    }

    public List<Usuario> getUsuario() {
        return usuario;
    }

    public void setUsuario(List<Usuario> usuario) {
        this.usuario = usuario;
    }
}
