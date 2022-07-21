package com.cedup.projetolitterae.entities;

import com.cedup.projetolitterae.enums.TipoPerfil;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Biblioteca {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;

    @Column(columnDefinition ="varchar(15)")
    public String cnpj;

    public String nome;
    public String email;
    public String contaBancaria;
    public Endereco endereco;
    public String telefone;
    public String livros;
    public Integer tipoPerfil;

    public String nomeUsuario;
    public String senha;

    public Biblioteca() {
    }

    public Biblioteca(String cnpj, String nome, String email, String contaBancaria, Endereco endereco, String telefone, String livros, TipoPerfil tipoPerfil, String nomeUsuario, String senha) {
        this.cnpj = cnpj;
        this.nome = nome;
        this.email = email;
        this.contaBancaria = contaBancaria;
        this.endereco = endereco;
        this.telefone = telefone;
        this.livros = livros;
        this.tipoPerfil = (tipoPerfil == null) ? null : tipoPerfil.getCod();
        this.nomeUsuario = nomeUsuario;
        this.senha = senha;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContaBancaria() {
        return contaBancaria;
    }

    public void setContaBancaria(String contaBancaria) {
        this.contaBancaria = contaBancaria;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getLivros() {
        return livros;
    }

    public void setLivros(String livros) {
        this.livros = livros;
    }

    public TipoPerfil getTipoPerfil() {
        return TipoPerfil.toEnum(tipoPerfil);
    }

    public void setTipoPerfil(TipoPerfil tipoPerfil) {
        this.tipoPerfil = tipoPerfil.getCod();
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
