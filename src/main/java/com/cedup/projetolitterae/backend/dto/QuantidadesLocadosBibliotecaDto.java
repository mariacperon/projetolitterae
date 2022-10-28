package com.cedup.projetolitterae.backend.dto;

public class QuantidadesLocadosBibliotecaDto {

    private Integer idLivro;
    private Integer qtdLocacoes;

    public QuantidadesLocadosBibliotecaDto() {
    }

    public QuantidadesLocadosBibliotecaDto(Integer idLivro, Integer qtdLocacoes) {
        this.idLivro = idLivro;
        this.qtdLocacoes = qtdLocacoes;
    }

    public Integer getIdLivro() {
        return idLivro;
    }

    public void setIdLivro(Integer idLivro) {
        this.idLivro = idLivro;
    }

    public Integer getQtdLocacoes() {
        return qtdLocacoes;
    }

    public void setQtdLocacoes(Integer qtdLocacoes) {
        this.qtdLocacoes = qtdLocacoes;
    }
}
