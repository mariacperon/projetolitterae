package com.cedup.projetolitterae.backend.entities;

import com.cedup.projetolitterae.backend.enums.GeneroLivro;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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

    private String resumo;
    private String idioma;
    private int quantidadeEstoque;
    private String resenhas;

    @ManyToOne
    @JoinColumn(name = "id_biblioteca")
    private Biblioteca biblioteca;

    public Livro() {
    }

    public Livro(String nome, String resumo, String idioma, int quantidadeEstoque, String resenhas) {
        this.nome = nome;
        this.resumo = resumo;
        this.idioma = idioma;
        this.quantidadeEstoque = quantidadeEstoque;
        this.resenhas = resenhas;
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

    public String getResumo() {
        return resumo;
    }

    public void setResumo(String resumo) {
        this.resumo = resumo;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public int getQuantidadeEstoque() {
        return quantidadeEstoque;
    }

    public void setQuantidadeEstoque(int quantidadeEstoque) {
        this.quantidadeEstoque = quantidadeEstoque;
    }

    public String getResenhas() {
        return resenhas;
    }

    public void setResenhas(String resenhas) {
        this.resenhas = resenhas;
    }

    public Biblioteca getBiblioteca() {
        return biblioteca;
    }

    public void setBiblioteca(Biblioteca biblioteca) {
        this.biblioteca = biblioteca;
    }
}
