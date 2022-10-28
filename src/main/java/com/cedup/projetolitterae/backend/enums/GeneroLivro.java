package com.cedup.projetolitterae.backend.enums;

public enum GeneroLivro {

    FICCAO_CIENTIFICA(0, "Ficção Científica"),
    FANTASIA(1, "Fantasia"),
    SUSPENSE(2, "Suspense"),
    HORROR(3, "Horror"),
    POESIA(4, "Poesia"),
    ROMANCE(5, "Romance"),
    DISTOPIA(6, "Distopia"),
    INFANTIL(7, "Infantil"),
    BIOGRAFIA(8, "Biografia"),
    ACAO_E_AVENTURA(9, "Ação e Aventura"),
    HISTORIA(10, "História"),
    VIAGEM(11, "Viagem"),
    AUTO_AJUDA(12, "Auto Ajuda"),
    JOVEM_ADULTO(13, "Jovem Adulto"),
    NOVO_ADULTO(14, "Novo Aulto"),
    TECNOLOGIA_E_CIENCIA(15, "Tecnologia e Ciência"),
    RELIGIAO(16, "Religião"),
    NAO_FICCAO(17, "Não Ficção");
    
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
