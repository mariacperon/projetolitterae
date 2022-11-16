package com.cedup.projetolitterae.backend.entities;

import com.cedup.projetolitterae.backend.enums.TipoPerfil;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.io.Serializable;

@Entity
public class Biblioteca implements Serializable {

    @Id
    private Long id;

    @Column(columnDefinition ="varchar(18)")
    private String cnpj;

    private String nome;
    private String email;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "endereco_id")
    private Endereco enderecoBiblioteca;

    private Double taxaAtraso;
    private Double taxaPorDia;

    private String telefone;

    private Integer tipoPerfil;

    private String senha;

    private boolean ativo;

    public Biblioteca() {
    }

    public Biblioteca(String cnpj, String nome, String email, String telefone, TipoPerfil tipoPerfil, String senha, boolean ativo, Double taxaAtraso, Double taxaPorDia) {
        this.cnpj = cnpj;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.tipoPerfil = (tipoPerfil == null) ? null : tipoPerfil.getCod();
        this.senha = senha;
        this.ativo = ativo;
        this.taxaAtraso = taxaAtraso;
        this.taxaPorDia = taxaPorDia;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public Double getTaxaAtraso() {
        return taxaAtraso;
    }

    public void setTaxaAtraso(Double taxaAtraso) {
        this.taxaAtraso = taxaAtraso;
    }

    public Double getTaxaPorDia() {
        return taxaPorDia;
    }

    public void setTaxaPorDia(Double taxaPorDia) {
        this.taxaPorDia = taxaPorDia;
    }
}
