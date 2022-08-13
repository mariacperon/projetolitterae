package com.cedup.projetolitterae.entities;

import com.cedup.projetolitterae.enums.StatusLocacao;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;

@Entity
public class LivroUsuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_livro")
    private Livro livro;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    private Date dataLocacao;
    private Date dataDevolucao;
    private Date dataDevolvida;

    private Integer statusLocacao;

    public LivroUsuario() {
    }

    public LivroUsuario(Date dataLocacao, StatusLocacao statusLocacao) {
        this.dataLocacao = dataLocacao;
        this.statusLocacao = (statusLocacao == null) ? null : statusLocacao.getCod();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
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
