package com.cedup.projetolitterae.backend.enums;

public enum TipoPerfil {

    ADMIN(0, "Admin"),
    LEITOR(1, "Leitor");

    private final int cod;
    private final String descricao;

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
