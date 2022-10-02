package com.cedup.projetolitterae.backend.services;

import com.cedup.projetolitterae.backend.dto.PesquisaLivroDto;
import com.cedup.projetolitterae.backend.entities.Livro;
import com.cedup.projetolitterae.backend.repositories.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class LivroService {

    @Autowired
    private LivroRepository repository;
    @Autowired
    private UsuarioService usuarioService;

    public Livro pesquisarPorId(Integer id){
        return (repository.findById(id)).orElse(null);
    }

    public List<Livro> pesquisarTodos(){
        return repository.findAll() ;
    }

    public List<Livro> pesquisaLivroEspecifica(PesquisaLivroDto pesquisaLivro){
        Long idBiblioteca = usuarioService.pesquisarPorId(pesquisaLivro.getIdUsuario()).getBiblioteca().getId();

        pesquisaLivro.setValue("%"+pesquisaLivro.getValue()+"%");

        return switch (pesquisaLivro.getCampo()) {
            case "nome" -> repository.findLivroNome(pesquisaLivro.getValue(), idBiblioteca);
            case "autor" -> repository.findLivroAutor(pesquisaLivro.getValue(), idBiblioteca);
            case "isdb" -> repository.findLivroIsdb(pesquisaLivro.getValue(), idBiblioteca);
            case "editora" -> repository.findLivroEditora(pesquisaLivro.getValue(), idBiblioteca);
            case "classificacao_etaria" -> repository.findLivroClassificacaoEtaria(pesquisaLivro.getValue(), idBiblioteca);
            default -> null;
        };
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
