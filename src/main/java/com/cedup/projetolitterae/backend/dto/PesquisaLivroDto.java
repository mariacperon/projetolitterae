package com.cedup.projetolitterae.backend.dto;

public class PesquisaLivroDto {

    private Long idUsuario;

    /*QUAL CAMPO DO LIVRO É PARA SER FEITA A PESUISA:
    **colocar exatamente como abaixo:
        nome
        autor
        isdb
        editora
        classificacao_etaria
        genero
    */
    private String campo;

    private String value;

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getCampo() {
        return campo;
    }

    public void setCampo(String campo) {
        this.campo = campo;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
