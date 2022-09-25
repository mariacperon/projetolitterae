package com.cedup.projetolitterae.backend.services;

import com.cedup.projetolitterae.backend.repositories.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class AutorService {

    @Autowired
    private AutorRepository repository;

    public Autor pesquisarPorId(Integer id){
        return (repository.findById(id)).get();
    }

    @Transactional
    public Autor cadastrarAutor(Autor autor){
        autor.setId(null);
        return repository.save(autor);
    }

    public Autor alterarAutor(Autor novoAutor){
        repository.save(novoAutor);
        return novoAutor;
    }

    public void excluirAutor(Integer id){
        repository.deleteById(id);
    }
}
