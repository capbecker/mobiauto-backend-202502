package com.mobiauto.model.dto;

public class MarcaDTO {

    private Long idMarca;
    private String nome;
    private String cnpj;

    public MarcaDTO() {
    }

    public MarcaDTO(String nome, String cnpj) {
        this.nome = nome;
        this.cnpj = cnpj;
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
}
