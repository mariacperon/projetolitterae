package com.cedup.projetolitterae.backend.entities;

import com.cedup.projetolitterae.backend.enums.StatusLocacao;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.sql.Date;

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

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date dataLocacao;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date dataDevolucao;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date dataDevolvida;

    private Integer statusLocacao;

    public Locacao() {
    }

    public Locacao(Date dataLocacao, Date dataDevolucao, StatusLocacao statusLocacao) {
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

    public StatusLocacao getStatusLocacao() {
        return StatusLocacao.toEnum(statusLocacao);
    }

    public void setStatusLocacao(StatusLocacao statusLocacao) {
        this.statusLocacao = statusLocacao.getCod();
    }
}
