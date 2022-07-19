package com.cedup.projetolitterae.entities;

import com.cedup.projetolitterae.enums.TipoPerfil;
import java.io.Serializable;

public class Usuario {

    private int id;
    private String cpf;
    private String nome;
    private String sobrenome;
    private String endereco;
    private String telefone;
    private String metodoPagto;
    private TipoPerfil tipoPerfil;
    private String nomeUsuario;
    private String senha;

    public Usuario() {
    }

    public Usuario(int id, String cpf, String nome, String sobrenome, String endereco, String telefone, String metodoPagto, TipoPerfil tipoPerfil, String nomeUsuario, String senha) {
        this.id = id;
        this.cpf = cpf;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.endereco = endereco;
        this.telefone = telefone;
        this.metodoPagto = metodoPagto;
        this.tipoPerfil = tipoPerfil;
        this.nomeUsuario = nomeUsuario;
        this.senha = senha;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
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
        return tipoPerfil;
    }

    public void setTipoPerfil(TipoPerfil tipoPerfil) {
        this.tipoPerfil = tipoPerfil;
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
