package com.mobiauto.model.dto;


public class VeiculoCreateDTO {

    private String versao;

    private Integer ano;

    private Long idModelo;

    public VeiculoCreateDTO(String versao, Integer ano, Long idModelo) {
        this.versao = versao;
        this.ano = ano;
        this.idModelo = idModelo;
    }

    public String getVersao() {
        return versao;
    }

    public void setVersao(String versao) {
        this.versao = versao;
    }

    public Integer getAno() {
        return ano;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

    public Long getIdModelo() {
        return idModelo;
    }

    public void setIdModelo(Long idModelo) {
        this.idModelo = idModelo;
    }
}
