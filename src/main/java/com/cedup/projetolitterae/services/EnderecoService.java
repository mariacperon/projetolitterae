package com.cedup.projetolitterae.services;

import com.cedup.projetolitterae.entities.Endereco;
import com.cedup.projetolitterae.entities.Endereco;
import com.cedup.projetolitterae.repositories.EnderecoRepository;
import com.cedup.projetolitterae.repositories.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class EnderecoService {

    @Autowired
    private EnderecoRepository repository;

    public Endereco pesquisarPorId(Integer id){
        return (repository.findById(id)).get();
    }

    @Transactional
    public Endereco cadastrarEndereco(Endereco endereco){
        return repository.save(endereco);
    }

    public Endereco alterarEndereco(Endereco novoEndereco){
        Endereco oldEndereco = pesquisarPorId(novoEndereco.getId());
        alteraDados(novoEndereco, oldEndereco);
        repository.save(oldEndereco);
        return oldEndereco;
    }

    public void excluirEndereco(Integer id){
        repository.deleteById(id);
    }

    private void alteraDados(Endereco novoEndereco, Endereco oldEndereco){
        oldEndereco.setCep(novoEndereco.getCep());
        oldEndereco.setEstado(novoEndereco.getEstado());
        oldEndereco.setCidade(novoEndereco.getCidade());
        oldEndereco.setBairro(novoEndereco.getBairro());
        oldEndereco.setRua(novoEndereco.getRua());
        oldEndereco.setNumero(novoEndereco.getNumero());
        oldEndereco.setComplemento(novoEndereco.getComplemento());
    }
}
