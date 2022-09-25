package com.cedup.projetolitterae.backend.services;

import com.cedup.projetolitterae.backend.entities.Livro;
import com.cedup.projetolitterae.backend.repositories.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class LivroService {

    @Autowired
    private LivroRepository repository;

    public Livro pesquisarPorId(Integer id){
        return (repository.findById(id)).get();
    }

    public List<Livro> pesquisarTodos(){
        return repository.findAll() ;
    }

    @Transactional
    public Livro cadastrarLivro(Livro livro){
        livro.setId(null);
        return repository.save(livro);
    }

    public Livro alterarLivro(Livro novoLivro){
        repository.save(novoLivro);
        return novoLivro;
    }

    public void excluirLivro(Integer id){
        repository.deleteById(id);
    }
}
