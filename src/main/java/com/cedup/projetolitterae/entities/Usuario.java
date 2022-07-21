package com.cedup.projetolitterae.entities;

import com.cedup.projetolitterae.enums.TipoPerfil;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
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
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(columnDefinition ="varchar(11)")
    private String cpf;

    @Column(columnDefinition ="varchar(20)")
    private String nome;
    @Column(columnDefinition ="varchar(50)")
    private String sobrenome;

    @OneToOne
    @JoinColumn(name = "endereco_id")
    private Endereco endereco;

    private String telefone;
    private String metodoPagto;

    private int tipoPerfil;

    @Column(columnDefinition ="varchar(25)")
    private String nomeUsuario;
    private String senha;

    public Usuario() {
    }

    public Usuario(String cpf, String nome, String sobrenome, String telefone, String metodoPagto, TipoPerfil tipoPerfil, String nomeUsuario, String senha) {
        this.cpf = cpf;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.telefone = telefone;
        this.metodoPagto = metodoPagto;
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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
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

    public String getMetodoPagto() {
        return metodoPagto;
    }

    public void setMetodoPagto(String metodoPagto) {
        this.metodoPagto = metodoPagto;
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
