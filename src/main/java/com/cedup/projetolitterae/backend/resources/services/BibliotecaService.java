package com.cedup.projetolitterae.backend.resources.services;

import com.cedup.projetolitterae.backend.dto.SenhaDto;
import com.cedup.projetolitterae.backend.entities.Biblioteca;
import com.cedup.projetolitterae.backend.entities.MensagemRetorno;
import com.cedup.projetolitterae.backend.exceptions.MensagemRetornoException;
import com.cedup.projetolitterae.backend.repositories.BibliotecaRepository;
import com.cedup.projetolitterae.backend.repositories.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Service
public class BibliotecaService {

    @Autowired
    private BibliotecaRepository repository;
    @Autowired
    private EnderecoRepository enderecoRepository;

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

        biblioteca.setId(random.nextLong(10000, 100000000));
        biblioteca.setSenha(senha);

        biblioteca.getEnderecoBiblioteca().setId(null);
        enderecoRepository.save(biblioteca.getEnderecoBiblioteca());

        return repository.save(biblioteca);
    }

    public Biblioteca alterarBiblioteca(Biblioteca novaBiblioteca){
        Biblioteca oldBiblioteca = pesquisarPorId(novaBiblioteca.getId());
        novaBiblioteca.getEnderecoBiblioteca().setId(oldBiblioteca.getEnderecoBiblioteca().getId());
        enderecoRepository.save(novaBiblioteca.getEnderecoBiblioteca());
        repository.save(novaBiblioteca);
        return novaBiblioteca;
    }

    public void excluirBiblioteca(Long id){
        Integer idEndereco = repository.findById(id).get().getEnderecoBiblioteca().getId();
        repository.deleteById(id);
        enderecoRepository.deleteById(idEndereco);
    }

    public boolean alterarSenha(SenhaDto senhaDto){
        Biblioteca biblioteca = pesquisarPorId(senhaDto.getId());

        if(biblioteca == null)
            throw new MensagemRetornoException(new MensagemRetorno("ERRO",
                    "Não foi encontrado o usuário"));

        biblioteca.setSenha(senhaDto.getNovaSenha());
        repository.save(biblioteca);
        return true;
    }

}
