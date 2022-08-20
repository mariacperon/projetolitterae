package com.cedup.projetolitterae.backend.entities;

import com.cedup.projetolitterae.backend.enums.StatusLocacao;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDate;
import java.util.Date;

@Entity
public class Locacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_livro")
    private LivroBiblioteca livro;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataLocacao;

    private LocalDate dataDevolucao;
    private LocalDate dataDevolvida;

    private Integer statusLocacao;

    public Locacao() {
    }

    public Locacao(LocalDate dataLocacao, LocalDate dataDevolucao, StatusLocacao statusLocacao) {
        this.dataLocacao = dataLocacao;
        this.dataDevolucao = dataDevolucao;
        this.statusLocacao = (statusLocacao == null) ? null : statusLocacao.getCod();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LivroBiblioteca getLivro() {
        return livro;
    }

    public void setLivro(LivroBiblioteca livro) {
        this.livro = livro;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public LocalDate getDataLocacao() {
        return dataLocacao;
    }

    public void setDataLocacao(LocalDate dataLocacao) {
        this.dataLocacao = dataLocacao;
    }

    public LocalDate getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(LocalDate dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    public LocalDate getDataDevolvida() {
        return dataDevolvida;
    }

    public void setDataDevolvida(LocalDate dataDevolvida) {
        this.dataDevolvida = dataDevolvida;
    }

    public StatusLocacao getStatusLocacao() {
        return StatusLocacao.toEnum(statusLocacao);
    }

    public void setStatusLocacao(StatusLocacao statusLocacao) {
        this.statusLocacao = statusLocacao.getCod();
    }
}
