package com.cedup.projetolitterae.services;

import com.cedup.projetolitterae.entities.Livro;
import com.cedup.projetolitterae.entities.Usuario;
import com.cedup.projetolitterae.repositories.EnderecoRepository;
import com.cedup.projetolitterae.repositories.LivroRepository;
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

    public List<Livro> pesquisarLivrosBiblioteca(Integer id){
        return repository.findLivroByBibliotecaId(id);
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
