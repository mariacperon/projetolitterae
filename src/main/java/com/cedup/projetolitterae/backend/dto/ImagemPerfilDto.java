package com.cedup.projetolitterae.backend.dto;

import org.springframework.web.multipart.MultipartFile;

public class ImagemPerfilDto {

    private Long idUsuario;
    private MultipartFile imagem;

    public ImagemPerfilDto(Long idUsuario, MultipartFile imagem) {
        this.idUsuario = idUsuario;
        this.imagem = imagem;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public MultipartFile getImagem() {
        return imagem;
    }

    public void setImagem(MultipartFile imagem) {
        this.imagem = imagem;
    }
}
