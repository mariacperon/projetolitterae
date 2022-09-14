package com.cedup.projetolitterae.backend.entities;

import java.io.Serializable;

public class MensagemRetorno implements Serializable {

    public String status;
    public String mensagem;

    public MensagemRetorno(String status, String mensagem) {
        this.mensagem = mensagem;
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
}
