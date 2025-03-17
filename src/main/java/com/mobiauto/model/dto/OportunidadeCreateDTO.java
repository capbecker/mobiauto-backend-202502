package com.mobiauto.model.dto;


public class OportunidadeCreateDTO {

    private Long idVeiculo;

    public OportunidadeCreateDTO(Long idVeiculo, Long idRevenda) {
        this.idVeiculo = idVeiculo;
    }

    public Long getIdVeiculo() {
        return idVeiculo;
    }

    public void setIdVeiculo(Long idVeiculo) {
        this.idVeiculo = idVeiculo;
    }

}
