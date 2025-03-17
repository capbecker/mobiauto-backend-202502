package com.mobiauto.model.dto;

public class OportunidadeUpdateDTO {

    private Long idOportunidade;

    private Long idVeiculo;

    private Long idRevenda;

    private String motivoConclusao;

    private Long idCliente;

    private Long idUsuario;

    public Long getIdOportunidade() {
        return idOportunidade;
    }

    public void setIdOportunidade(Long idOportunidade) {
        this.idOportunidade = idOportunidade;
    }

    public Long getIdVeiculo() {
        return idVeiculo;
    }

    public void setIdVeiculo(Long idVeiculo) {
        this.idVeiculo = idVeiculo;
    }

    public Long getIdRevenda() {
        return idRevenda;
    }

    public void setIdRevenda(Long idRevenda) {
        this.idRevenda = idRevenda;
    }

    public String getMotivoConclusao() {
        return motivoConclusao;
    }

    public void setMotivoConclusao(String motivoConclusao) {
        this.motivoConclusao = motivoConclusao;
    }

    public Long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }
}
