package com.cedup.projetolitterae.enums;

public enum GeneroLivro {

    ROMANCE(0, "Romance"),
    AVENTURA(1, "Aventura"),
    AÇÃO(2, "Ação"),
    FICCAO(3, "Ficção"),
    FANTASIA(4, "Fantasia"),
    TERROR(5, "Terror"),
    SUSPENSE(6, "Suspense"),
    COMEDIA(7, "Comédia"),
    AUTOAJUDA(8, "Autoajuda"),
    AUTOBIOGRAFIA(9, "Autobiografia"),
    BIOGRAFIA(10, "Biografia"),
    INFANTIL(11, "Infantil"),
    INFANTOJUVENIL(11, "InfantoJuvenil"),
    HISTORIA(11, "História"),
    HQ(11, "HQ"),
    GASTRONOMIA(11, "Gatronomia"),
    DRAMA(11, "Drama");

    private final int cod;
    private final String descricao;

    GeneroLivro(int cod, String descricao) {
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
