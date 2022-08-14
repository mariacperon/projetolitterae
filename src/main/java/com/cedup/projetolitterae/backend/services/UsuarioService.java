package com.cedup.projetolitterae.backend.services;

import com.cedup.projetolitterae.backend.entities.Usuario;
import com.cedup.projetolitterae.backend.repositories.EnderecoRepository;
import com.cedup.projetolitterae.backend.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;
    @Autowired
    private EnderecoRepository enderecoRepository;

    public Usuario pesquisarPorId(Integer id){
        return (repository.findById(id)).get();
    }

    public List<Usuario> pesquisarTodos(){
        return repository.findAll() ;
    }

    @Transactional
    public Usuario cadastrarUsuario(Usuario usuario){
        usuario.setId(null);
        enderecoRepository.save(usuario.getEnderecoUsuario());
        return repository.save(usuario);
    }

    public Usuario alterarUsuario(Usuario novoUsuario){
        Usuario oldUsuario = pesquisarPorId(novoUsuario.getId());
        novoUsuario.getEnderecoUsuario().setId(oldUsuario.getEnderecoUsuario().getId());
        enderecoRepository.save(novoUsuario.getEnderecoUsuario());
        repository.save(novoUsuario);
        return novoUsuario;
    }

    public void excluirUsuario(Integer id){
        Integer idEndereco = repository.findById(id).get().getEnderecoUsuario().getId();
        repository.deleteById(id);
        enderecoRepository.deleteById(idEndereco);
    }
}
