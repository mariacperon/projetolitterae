package com.cedup.projetolitterae.backend.services;

import com.cedup.projetolitterae.backend.entities.LivroBiblioteca;
import com.cedup.projetolitterae.backend.repositories.AutorRepository;
import com.cedup.projetolitterae.backend.repositories.LivroBibliotecaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class LivroBibliotecaService {

    @Autowired
    private LivroBibliotecaRepository repository;

    public LivroBiblioteca pesquisarPorId(Integer id){
        return (repository.findById(id)).get();
    }

    public List<LivroBiblioteca> pesquisarLivroBibliotecasBiblioteca(Integer id){
        return repository.findLivroBibliotecaByBibliotecaId(id);
    }

    @Transactional
    public LivroBiblioteca cadastrarLivroBiblioteca(LivroBiblioteca livroBiblioteca){
        livroBiblioteca.setId(null);
        return repository.save(livroBiblioteca);
    }

    public LivroBiblioteca alterarLivroBiblioteca(LivroBiblioteca novoLivroBiblioteca){
        repository.save(novoLivroBiblioteca);
        return novoLivroBiblioteca;
    }

    public void excluirLivroBiblioteca(Integer id){
        repository.deleteById(id);
    }
}
