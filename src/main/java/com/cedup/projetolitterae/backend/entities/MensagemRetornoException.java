package com.cedup.projetolitterae.backend.entities;

public class MensagemRetornoException extends RuntimeException{

    private MensagemRetorno mensagemRetorno;

    public MensagemRetornoException(MensagemRetorno mensagemRetorno){
        this.mensagemRetorno = mensagemRetorno;
    }
}
