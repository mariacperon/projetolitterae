package com.cedup.projetolitterae.backend.dto;

import org.springframework.web.multipart.MultipartFile;

public class ImagemPerfilDto {

    private Long id;
    private MultipartFile imagem;

    public ImagemPerfilDto(Long idUsuario, MultipartFile imagem) {
        this.id = idUsuario;
        this.imagem = imagem;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MultipartFile getImagem() {
        return imagem;
    }

    public void setImagem(MultipartFile imagem) {
        this.imagem = imagem;
    }
}
