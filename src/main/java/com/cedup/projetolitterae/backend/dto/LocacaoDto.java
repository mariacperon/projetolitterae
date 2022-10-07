package com.cedup.projetolitterae.backend.dto;

import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;

public class LocacaoDto {

    private Integer id;
    private Integer idLivroBiblioteca;
    private Long idUsuario;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date dataLocacao;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date dataDevolucao;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date dataDevolvida;

    private Integer statusLocacao;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdLivroBiblioteca() {
        return idLivroBiblioteca;
    }

    public void setIdLivroBiblioteca(Integer idLivroBiblioteca) {
        this.idLivroBiblioteca = idLivroBiblioteca;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Date getDataLocacao() {
        return dataLocacao;
    }

    public void setDataLocacao(Date dataLocacao) {
        this.dataLocacao = dataLocacao;
    }

    public Date getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(Date dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    public Date getDataDevolvida() {
        return dataDevolvida;
    }

    public void setDataDevolvida(Date dataDevolvida) {
        this.dataDevolvida = dataDevolvida;
    }

    public Integer getStatusLocacao() {
        return statusLocacao;
    }

    public void setStatusLocacao(Integer statusLocacao) {
        this.statusLocacao = statusLocacao;
    }
}
