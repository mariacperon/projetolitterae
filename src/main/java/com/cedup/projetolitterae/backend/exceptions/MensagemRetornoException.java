package com.cedup.projetolitterae.backend.exceptions;

import com.cedup.projetolitterae.backend.entities.MensagemRetorno;

import java.util.Optional;

public class MensagemRetornoException extends RuntimeException{

    private MensagemRetorno mensagemRetorno;

    public MensagemRetornoException(MensagemRetorno mensagemRetorno){
        this.mensagemRetorno = mensagemRetorno;
    }

    @Override
    public String getMessage() {
        return Optional.ofNullable(getMensagemRetorno()).map(MensagemRetorno::getMensagem).orElse(null);
    }

    public MensagemRetorno getMensagemRetorno() {
        return mensagemRetorno;
    }

    public void setMensagemRetorno(MensagemRetorno mensagemRetorno) {
        this.mensagemRetorno = mensagemRetorno;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("MensagemRetornoException{");
        sb.append("mensagemRetorno=").append(mensagemRetorno);
        sb.append('}');
        return sb.toString();
    }
}
