package com.mobiauto.model.dto;

public class ModeloDTO {

    private String nome;
    private Long idMarca;

    public ModeloDTO() {
    }

    public ModeloDTO(String nome, Long idMarca) {
        this.nome = nome;
        this.idMarca = idMarca;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getIdMarca() {
        return idMarca;
    }

    public void setIdMarca(Long idMarca) {
        this.idMarca = idMarca;
    }
}
