package com.cedup.projetolitterae.entities;

import com.cedup.projetolitterae.enums.GeneroLivro;

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
    private String autor;

    @ElementCollection
    @CollectionTable(name = "genero")
    @Column(name = "genero")
    private List<GeneroLivro> generos = new ArrayList<>();

    private String resumo;
    private String idioma;
    private int quantidadeEstoque;
    private String resenhas;

    @ManyToOne
    @JoinColumn(name = "id_biblioteca")
    private Biblioteca biblioteca;

    public Livro() {
    }

    public Livro(String nome, String autor, String resumo, String idioma, int quantidadeEstoque, String resenhas) {
        this.nome = nome;
        this.autor = autor;
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

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public List<GeneroLivro> getGeneros() {
        return generos;
    }

    public void setGeneros(List<GeneroLivro> generos) {
        this.generos = generos;
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
