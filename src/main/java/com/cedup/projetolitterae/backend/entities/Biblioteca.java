package com.cedup.projetolitterae.backend.entities;

import com.cedup.projetolitterae.backend.enums.TipoPerfil;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Biblioteca implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(columnDefinition ="varchar(16)")
    private String cnpj;

    private String nome;
    private String email;
    private String contaBancaria;

    @OneToOne
    @JoinColumn(name = "endereco_id")
    private Endereco enderecoBiblioteca;

    private String telefone;

    private Integer tipoPerfil;

    @Column(columnDefinition ="varchar(25)")
    private String nomeUsuario;
    private String senha;

    private boolean ativo;

    public Biblioteca() {
    }

    public Biblioteca(String cnpj, String nome, String email, String contaBancaria, String telefone, TipoPerfil tipoPerfil, String nomeUsuario, String senha, boolean ativo) {
        this.cnpj = cnpj;
        this.nome = nome;
        this.email = email;
        this.contaBancaria = contaBancaria;
        this.telefone = telefone;
        this.tipoPerfil = (tipoPerfil == null) ? null : tipoPerfil.getCod();
        this.nomeUsuario = nomeUsuario;
        this.senha = senha;
        this.ativo = ativo;
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

    public Endereco getEnderecoBiblioteca() {
        return enderecoBiblioteca;
    }

    public void setEnderecoBiblioteca(Endereco enderecoBiblioteca) {
        this.enderecoBiblioteca = enderecoBiblioteca;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
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

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
}
