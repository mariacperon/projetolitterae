package com.cedup.projetolitterae.backend.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class UltimoId {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String perfil;
    private Long ultimoId;

    public UltimoId() {
    }

    public UltimoId(Integer id, String perfil, Long ultimoId) {
        this.id = id;
        this.perfil = perfil;
        this.ultimoId = ultimoId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }

    public Long getUltimoId() {
        return ultimoId;
    }

    public void setUltimoId(Long ultimoId) {
        this.ultimoId = ultimoId;
    }
}
