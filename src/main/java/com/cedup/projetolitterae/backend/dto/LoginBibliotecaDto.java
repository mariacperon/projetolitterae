package com.cedup.projetolitterae.backend.dto;

public class LoginBibliotecaDto {

    private Long id;
    private String senha;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
