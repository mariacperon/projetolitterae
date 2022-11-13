package com.cedup.projetolitterae.backend.entities;

import com.cedup.projetolitterae.backend.enums.GeneroLivro;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nome;
    private String autor;

    @ElementCollection
    @CollectionTable(name = "livro_genero")
    @Column(name = "genero")
    private List<Integer> generos = new ArrayList<>();

    @Column(length = 500)
    private String sinopse;

    private String idioma;
    private String classificacaoEtaria;
    private String editora;
    private String ISDB;
    private String edicao;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date dataLancamento;

    private String imagemPath;

    public Livro() {
    }

    public Livro(String nome, String autor, String sinopse, String idioma, String classificacaoEtaria, String editora, String ISDB, String edicao, Date dataLancamento) {
        this.nome = nome;
        this.autor = autor;
        this.sinopse = sinopse;
        this.idioma = idioma;
        this.classificacaoEtaria = classificacaoEtaria;
        this.editora = editora;
        this.ISDB = ISDB;
        this.edicao = edicao;
        this.dataLancamento = dataLancamento;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public List<Integer> getGeneros() {
        return generos;
    }

    public void setGeneros(List<GeneroLivro> generos) {
        List<Integer> codGeneros = new ArrayList<>();
        for(GeneroLivro g : generos) {
            codGeneros.add(g.getCod());
        }
        this.generos = codGeneros;
    }

    public String getSinopse() {
        return sinopse;
    }

    public void setSinopse(String sinopse) {
        this.sinopse = sinopse;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public String getImagem() {
        return imagemPath;
    }

    public void setImagem(String imagemPath) {
        this.imagemPath = imagemPath;
    }

    public String getClassificacaoEtaria() {
        return classificacaoEtaria;
    }

    public void setClassificacaoEtaria(String classificacaoEtaria) {
        this.classificacaoEtaria = classificacaoEtaria;
    }

    public String getEditora() {
        return editora;
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }

    public String getISDB() {
        return ISDB;
    }

    public void setISDB(String ISDB) {
        this.ISDB = ISDB;
    }

    public String getEdicao() {
        return edicao;
    }

    public void setEdicao(String edicao) {
        this.edicao = edicao;
    }

    public Date getDataLancamento() {
        return dataLancamento;
    }

    public void setDataLancamento(Date dataLancamento) {
        this.dataLancamento = dataLancamento;
    }
}
