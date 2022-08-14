package com.cedup.projetolitterae.backend.services;

import com.cedup.projetolitterae.backend.entities.LivroUsuario;
import com.cedup.projetolitterae.backend.repositories.LivroUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class LivroUsuarioService {

    @Autowired
    private LivroUsuarioRepository repository;

    public LivroUsuario pesquisarPorId(Integer id){
        return (repository.findById(id)).get();
    }

    @Transactional
    public LivroUsuario locarLivro(LivroUsuario livroUsuario){
        return repository.save(livroUsuario);
    }

    public LivroUsuario alterarLocacao(LivroUsuario novoLivroUsuario){
        LivroUsuario oldLivroUsuario = pesquisarPorId(novoLivroUsuario.getId());
        repository.save(oldLivroUsuario);

        return oldLivroUsuario;
    }

    public void excluirLivroUsuario(Integer id){
        repository.deleteById(id);
    }
}
