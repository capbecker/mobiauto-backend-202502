package com.mobiauto.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.mobiauto.model.enums.Status;
import jakarta.persistence.*;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters.LocalDateTimeConverter;

import java.time.LocalDateTime;

@Entity
public class Oportunidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="motivo_conclusao")
    private String motivoConclusao;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Convert(converter = LocalDateTimeConverter.class)
    @Column(name="data_criacao")
    private LocalDateTime dataCriacao;

    @Convert(converter = LocalDateTimeConverter.class)
    @Column(name="data_atribuicao")
    private LocalDateTime dataAtribuicao;

    @Convert(converter = LocalDateTimeConverter.class)
    @Column(name="data_conclusao")
    private LocalDateTime dataConclusao;

    @OneToOne
    @JoinColumn(name = "id_veiculo")
    @JsonManagedReference
    private Veiculo veiculo;

    @ManyToOne
    @JoinColumn(name = "id_cliente")
    @JsonManagedReference
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "id_revenda")
    @JsonManagedReference
    private Revenda revenda;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    @JsonManagedReference
    private Usuario usuario;

    public Oportunidade() {
    }

    public Oportunidade(Long id, String motivoConclusao, Status status, Veiculo veiculo, Revenda revenda) {
        this.id = id;
        this.motivoConclusao = motivoConclusao;
        this.status = status;
        this.veiculo = veiculo;
        this.revenda = revenda;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Revenda getRevenda() {
        return revenda;
    }

    public void setRevenda(Revenda revenda) {
        this.revenda = revenda;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public LocalDateTime getDataAtribuicao() {
        return dataAtribuicao;
    }

    public void setDataAtribuicao(LocalDateTime dataAtribuicao) {
        this.dataAtribuicao = dataAtribuicao;
    }

    public LocalDateTime getDataConclusao() {
        return dataConclusao;
    }

    public void setDataConclusao(LocalDateTime dataConclusao) {
        this.dataConclusao = dataConclusao;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @PrePersist
    public void prePersist() {
        this.setDataCriacao(LocalDateTime.now());
    }
}
