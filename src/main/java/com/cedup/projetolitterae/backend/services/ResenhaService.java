package com.cedup.projetolitterae.backend.services;

import com.cedup.projetolitterae.backend.entities.Resenha;
import com.cedup.projetolitterae.backend.repositories.ResenhaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ResenhaService {

    @Autowired
    private ResenhaRepository repository;

    public Resenha pesquisarPorId(Integer id){
        return (repository.findById(id)).orElse(null);
    }

    public List<Resenha> pesquisarResenhaPorIdLivro(Integer id){
        return repository.findResenhaByLivroId(id);
    }

    public List<Resenha> pesquisarResenhaPorIdUsuario(Long id){
        return repository.findResenhaByUsuarioId(id);
    }

    @Transactional
    public Resenha cadastrarResenha(Resenha resenha){
        resenha.setId(null);
        return repository.save(resenha);
    }

    public Resenha alterarResenha(Resenha novoResenha){
        repository.save(novoResenha);
        return novoResenha;
    }

    public void excluirResenha(Integer id){
        repository.deleteById(id);
    }
}
