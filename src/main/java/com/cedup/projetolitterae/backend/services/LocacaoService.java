package com.cedup.projetolitterae.backend.services;

import com.cedup.projetolitterae.backend.entities.Locacao;
import com.cedup.projetolitterae.backend.repositories.LocacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class LocacaoService {

    @Autowired
    private LocacaoRepository repository;

    public Locacao pesquisarPorId(Integer id){
        return (repository.findById(id)).get();
    }

    public List<Locacao> pesquisarPorLivro(Integer id){
        return repository.findLocacaoByLivroId(id);
    }

    public List<Locacao> pesquisarPorUsuario(Integer id){
        return repository.findLocacaoByUsuarioId(id);
    }

    @Transactional
    public Locacao locarLivro(Locacao livroUsuario){
        return repository.save(livroUsuario);
    }

    public Locacao alterarLocacao(Locacao novoLivroUsuario){
        Locacao oldLivroUsuario = pesquisarPorId(novoLivroUsuario.getId());
        repository.save(oldLivroUsuario);

        return oldLivroUsuario;
    }

    public void excluirLocacao(Integer id){
        repository.deleteById(id);
    }

    public boolean validarLocacao(){


        return true;
    }

}
