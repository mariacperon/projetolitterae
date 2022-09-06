package com.cedup.projetolitterae.backend.entities;

public class MensagemRetorno {

    public String mensagem;
    public String status;

    public MensagemRetorno(String mensagem, String status) {
        this.mensagem = mensagem;
        this.status = status;
    }
}
