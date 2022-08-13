package com.cedup.projetolitterae.enums;

public enum StatusLocacao {

    ANDAMENTO(0, "Em andamento"),
    ENCERRADO(1, "Encerrado");

    private final int cod;
    private final String descricao;

    StatusLocacao(int cod, String descricao) {
        this.cod = cod;
        this.descricao = descricao;
    }

    public int getCod() {
        return cod;
    }

    public String getDescricao() {
        return descricao;
    }

    public static StatusLocacao toEnum(Integer cod){
        if(cod == null){
            return null;
        }
        for(StatusLocacao tp : StatusLocacao.values()){
            if(cod.equals(tp.getCod())){
                return tp;
            }
        }

        throw new IllegalArgumentException("Id inválido: "+ cod);
    }
}
