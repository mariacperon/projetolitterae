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

    @ManyToOne
    @JoinColumn(name = "id_autor")
    private Autor autor;

    @ElementCollection
    @CollectionTable(name = "livro_genero")
    @Column(name = "genero")
    private List<Integer> generos = new ArrayList<>();

    private String sinopse;
    private String idioma;
    private String classificacaoEtaria;
    private String editora;
    private String ISDB;
    private String edicao;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date dataLancamento;

    @Lob
    @Column(name="imagem", columnDefinition="mediumblob")
    private byte[] imagem;

    public Livro() {
    }

    public Livro(String nome, String sinopse, String idioma, String classificacaoEtaria, String editora, String ISDB, String edicao, Date dataLancamento) {
        this.nome = nome;
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

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
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

    public byte[] getImagem() {
        return imagem;
    }

    public void setImagem(byte[] imagem) {
        this.imagem = imagem;
    }
}
