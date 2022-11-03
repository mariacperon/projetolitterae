package com.cedup.projetolitterae.backend.services;

import com.cedup.projetolitterae.backend.entities.UltimoId;
import com.cedup.projetolitterae.backend.repositories.UltimoIdRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UltimoIdService {

    @Autowired
    private UltimoIdRepository repository;

    public Long verificarUltimoId(String perfil){
        Long ultimoId = null;

        if(Objects.equals(perfil, "biblioteca")){
            ultimoId = repository.buscarUltimoIdBiblioteca();
        }else{
            ultimoId = repository.buscarUltimoIdUsuario();
        }

        return ultimoId;
    }

    public void salvarUltimoId(UltimoId ultimoId){
        repository.save(ultimoId);
    }
}
