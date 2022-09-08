package com.cedup.projetolitterae.backend.entities;

public class MensagemRetorno {

    public String status;
    public String mensagem;

    public MensagemRetorno(String status, String mensagem) {
        this.mensagem = mensagem;
        this.status = status;
    }
}
