package com.mobiauto.model.dto;


public class VeiculoFilterDTO {

    private String versao;

    private Integer ano;

    private String nomeModelo;

    private String nomeMarca;

    public VeiculoFilterDTO(String versao, Integer ano, String nomeModelo, String nomeMarca) {
        this.versao = versao;
        this.ano = ano;
        this.nomeModelo = nomeModelo;
        this.nomeMarca = nomeMarca;
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

    public String getNomeModelo() {
        return nomeModelo;
    }

    public void setNomeModelo(String nomeModelo) {
        this.nomeModelo = nomeModelo;
    }

    public String getNomeMarca() {
        return nomeMarca;
    }

    public void setNomeMarca(String nomeMarca) {
        this.nomeMarca = nomeMarca;
    }
}
