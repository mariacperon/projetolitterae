package com.cedup.projetolitterae.services;

import com.cedup.projetolitterae.entities.Biblioteca;
import com.cedup.projetolitterae.repositories.BibliotecaRepository;
import com.cedup.projetolitterae.repositories.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class BibliotecaService {

    @Autowired
    private BibliotecaRepository repository;
    @Autowired
    private EnderecoRepository enderecoRepository;

    public Biblioteca pesquisarPorId(Integer id){
        return (repository.findById(id)).get();
    }

    public List<Biblioteca> pesquisarTodas() {
        return repository.findAll();
    }

    @Transactional
    public Biblioteca cadastrarBiblioteca(Biblioteca biblioteca){
        biblioteca.setId(null);
        biblioteca.getEnderecoBiblioteca().setId(null);
        Biblioteca bibliotecaCadastrada = repository.save(biblioteca);
        enderecoRepository.save(biblioteca.getEnderecoBiblioteca());
        return bibliotecaCadastrada;
    }

    public Biblioteca alterarBiblioteca(Biblioteca novaBiblioteca){
        Biblioteca oldBiblioteca = pesquisarPorId(novaBiblioteca.getId());
        novaBiblioteca.getEnderecoBiblioteca().setId(oldBiblioteca.getEnderecoBiblioteca().getId());
        enderecoRepository.save(novaBiblioteca.getEnderecoBiblioteca());
        repository.save(novaBiblioteca);
        return novaBiblioteca;
    }

    public void excluirBiblioteca(Integer id){
        Integer idEndereco = repository.findById(id).get().getEnderecoBiblioteca().getId();
        repository.deleteById(id);
        enderecoRepository.deleteById(idEndereco);
    }

}
