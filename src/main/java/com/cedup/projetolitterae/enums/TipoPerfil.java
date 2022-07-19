package com.cedup.projetolitterae.enums;

public enum TipoPerfil {

    ADMIN(1, "Admin"),
    LEITOR(2, "Leitor");

    private int cod;
    private String descricao;

    TipoPerfil(int cod, String descricao) {
        this.cod = cod;
        this.descricao = descricao;
    }

    public int getCod() {
        return cod;
    }

    public String getDescricao() {
        return descricao;
    }

    public static TipoPerfil toEnum(Integer cod){
        if(cod == null){
            return null;
        }
        for(TipoPerfil tp : TipoPerfil.values()){
            if(cod.equals(tp.getCod())){
                return tp;
            }
        }

        throw new IllegalArgumentException("Id inválido: "+ cod);
    }
}
