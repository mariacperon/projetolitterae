package com.cedup.projetolitterae.services;

import com.cedup.projetolitterae.entities.Usuario;
import com.cedup.projetolitterae.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    public Usuario pesquisarPorId(Integer id){
        return (repository.findById(id)).get();
    }

    public List<Usuario> pesquisarTodos(){
        return repository.findAll() ;
    }

    @Transactional
    public Usuario cadastrarUsuario(Usuario usuario){
        return repository.save(usuario);
    }

    public Usuario alterarUsuario(Usuario novoUsuario){
        Usuario oldUsuario = pesquisarPorId(novoUsuario.getId());
        alteraDados(novoUsuario, oldUsuario);
        repository.save(oldUsuario);
        return oldUsuario;
    }

    public void excluirUsuario(Integer id){
        repository.deleteById(id);
    }

    private void alteraDados(Usuario novoUsuario, Usuario oldUsuario){
        oldUsuario.setCpf(novoUsuario.getCpf());
        oldUsuario.setNome(novoUsuario.getNome());
        oldUsuario.setSobrenome(novoUsuario.getSobrenome());
        oldUsuario.setEndereco(novoUsuario.getEndereco());
        oldUsuario.setTelefone(novoUsuario.getTelefone());
        oldUsuario.setMetodoPagto(novoUsuario.getMetodoPagto());
        oldUsuario.setTipoPerfil(novoUsuario.getTipoPerfil());
        oldUsuario.setNomeUsuario(novoUsuario.getNomeUsuario());
        oldUsuario.setSenha(novoUsuario.getSenha());
    }
}
