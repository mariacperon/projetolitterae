package com.cedup.projetolitterae.backend.entities;

import com.cedup.projetolitterae.backend.enums.TipoPerfil;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.io.Serializable;
import java.sql.Date;
import java.util.Random;

@Entity
public class Usuario implements Serializable{

    @Id
    private Long id;

    @Column(columnDefinition ="varchar(11)")
    private String cpf;

    @Column(columnDefinition ="varchar(20)")
    private String nome;
    @Column(columnDefinition ="varchar(50)")
    private String sobrenome;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "id_endereco")
    private Endereco enderecoUsuario;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_biblioteca")
    private Biblioteca biblioteca;

    private String email;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date dataNascimento;

    private String telefone1;
    private String telefone2;

    private String imagem;

    private int tipoPerfil;

    private boolean ativo;

    public Usuario() {
    }

    public Usuario(String cpf, String nome, String sobrenome, String email,String telefone1, String telefone2, TipoPerfil tipoPerfil, boolean ativo, Date dataNascimento) {
        this.cpf = cpf;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.email = email;
        this.telefone1 = telefone1;
        this.telefone2 = telefone2;
        this.tipoPerfil = (tipoPerfil == null) ? null : tipoPerfil.getCod();
        this.ativo = ativo;
        this.dataNascimento = dataNascimento;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Endereco getEnderecoUsuario() {
        return enderecoUsuario;
    }

    public void setEnderecoUsuario(Endereco enderecoUsuario) {
        this.enderecoUsuario = enderecoUsuario;
    }

    public String getTelefone1() {
        return telefone1;
    }

    public void setTelefone1(String telefone1) {
        this.telefone1 = telefone1;
    }

    public String getTelefone2() {
        return telefone2;
    }

    public void setTelefone2(String telefone2) {
        this.telefone2 = telefone2;
    }

    public TipoPerfil getTipoPerfil() {
        return TipoPerfil.toEnum(tipoPerfil);
    }

    public void setTipoPerfil(TipoPerfil tipoPerfil) {
        this.tipoPerfil = tipoPerfil.getCod();
    }

    public Biblioteca getBiblioteca() {
        return biblioteca;
    }

    public void setBiblioteca(Biblioteca bilioteca) {
        this.biblioteca = bilioteca;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }
}
