package com.cedup.projetolitterae.backend.services;

import com.cedup.projetolitterae.backend.entities.Biblioteca;
import com.cedup.projetolitterae.backend.entities.MensagemRetorno;
import com.cedup.projetolitterae.backend.entities.UltimoId;
import com.cedup.projetolitterae.backend.entities.Usuario;
import com.cedup.projetolitterae.backend.exceptions.MensagemRetornoException;
import com.cedup.projetolitterae.backend.repositories.BibliotecaRepository;
import com.cedup.projetolitterae.backend.repositories.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class BibliotecaService {

    @Autowired
    private BibliotecaRepository repository;
    @Autowired
    private EnderecoRepository enderecoRepository;
    @Autowired
    private UltimoIdService ultimoIdService;

    Random random = new Random();
    UUID randomUUID = UUID.randomUUID();

    public Biblioteca pesquisarPorId(Long id){
        return (repository.findById(id)).orElse(null);
    }

    public List<Biblioteca> pesquisarTodas() {
        return repository.findAll();
    }

    @Transactional
    public Biblioteca cadastrarBiblioteca(Biblioteca biblioteca){
        String senha = (randomUUID.toString().replaceAll("_", "").
                replaceAll("-","")).substring(0, 10);

        Long novoId = gerarIdBiblioteca();
        biblioteca.setId(novoId);
        biblioteca.setSenha(senha);

        biblioteca.getEnderecoBiblioteca().setId(null);
        enderecoRepository.save(biblioteca.getEnderecoBiblioteca());

        biblioteca = repository.save(biblioteca);
        ultimoIdService.salvarUltimoId(new UltimoId(1, "biblioteca", novoId));

        return biblioteca;
    }

    public Biblioteca alterarBiblioteca(Biblioteca novaBiblioteca){
        Biblioteca oldBiblioteca = pesquisarPorId(novaBiblioteca.getId());
        novaBiblioteca.getEnderecoBiblioteca().setId(oldBiblioteca.getEnderecoBiblioteca().getId());
        enderecoRepository.save(novaBiblioteca.getEnderecoBiblioteca());
        repository.save(novaBiblioteca);
        return novaBiblioteca;
    }

    public void excluirBiblioteca(Long id){
        Integer idEndereco = pesquisarPorId(id).getEnderecoBiblioteca().getId();
        repository.deleteById(id);
        enderecoRepository.deleteById(idEndereco);
    }

    public boolean alterarSenha(Long id, String novaSenha){
        Biblioteca biblioteca = pesquisarPorId(id);

        if(biblioteca == null)
            throw new MensagemRetornoException(new MensagemRetorno("ERRO",
                    "Não foi encontrado o usuário"));

        biblioteca.setSenha(novaSenha);
        repository.save(biblioteca);
        return true;
    }

    public Biblioteca login(Long id, String senha){
        Biblioteca biblioteca = pesquisarPorId(id);

        if(biblioteca == null && !Objects.equals(biblioteca.getSenha(), senha)){
            throw new MensagemRetornoException(new MensagemRetorno("ERRO",
                    "Não foi possível realizar o login."));
        }

        return biblioteca;
    }

    private Long gerarIdBiblioteca(){
        return ultimoIdService.verificarUltimoId("biblioteca") + 1;
    }

}
