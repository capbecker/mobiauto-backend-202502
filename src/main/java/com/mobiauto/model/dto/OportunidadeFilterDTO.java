package com.mobiauto.model.dto;


import com.mobiauto.model.enums.Status;

public class OportunidadeFilterDTO {

    private String motivoConclusao;

    private Status status;

    private String versaoVeiculo;

    private String nomeModelo;

    private String nomeMarca;

    private Integer anoVeiculo;

    private String nomeCliente;

    private String emailCliente;

    private String telefoneCliente;

    private String nomeRevenda;

    public OportunidadeFilterDTO() {
    }

    public String getMotivoConclusao() {
        return motivoConclusao;
    }

    public void setMotivoConclusao(String motivoConclusao) {
        this.motivoConclusao = motivoConclusao;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getVersaoVeiculo() {
        return versaoVeiculo;
    }

    public void setVersaoVeiculo(String versaoVeiculo) {
        this.versaoVeiculo = versaoVeiculo;
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

    public Integer getAnoVeiculo() {
        return anoVeiculo;
    }

    public void setAnoVeiculo(Integer anoVeiculo) {
        this.anoVeiculo = anoVeiculo;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public String getEmailCliente() {
        return emailCliente;
    }

    public void setEmailCliente(String emailCliente) {
        this.emailCliente = emailCliente;
    }

    public String getTelefoneCliente() {
        return telefoneCliente;
    }

    public void setTelefoneCliente(String telefoneCliente) {
        this.telefoneCliente = telefoneCliente;
    }

    public String getNomeRevenda() {
        return nomeRevenda;
    }

    public void setNomeRevenda(String nomeRevenda) {
        this.nomeRevenda = nomeRevenda;
    }
}
