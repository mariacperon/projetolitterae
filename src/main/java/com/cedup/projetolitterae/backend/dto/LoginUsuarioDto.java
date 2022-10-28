package com.cedup.projetolitterae.backend.dto;


import java.sql.Date;

public class LoginUsuarioDto {

    private Long id;
    private Date dataNascimento;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDataNascimento() {
        dataNascimento.setDate(dataNascimento.getDate() + 1);
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }
}
