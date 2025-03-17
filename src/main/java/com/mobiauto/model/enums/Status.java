package com.mobiauto.model.enums;

public enum Status {

    NOVO("novo"), EM_ANDAMENTO("Em Andamento"), CONCLUIDO("Concluído");

    private String descricao;

    Status(String descricao) {
        this.descricao = descricao;
    }
}
