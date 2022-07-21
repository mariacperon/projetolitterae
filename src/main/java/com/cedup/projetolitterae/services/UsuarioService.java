package com.cedup.projetolitterae.services;

import com.cedup.projetolitterae.entities.Usuario;
import com.cedup.projetolitterae.repositories.EnderecoRepository;
import com.cedup.projetolitterae.repositories.UsuarioRepository;
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
        Usuario usuarioCadastrado = repository.save(usuario);
        usuario.getEndereco().setUsuario(usuario);
        enderecoRepository.save(usuario.getEndereco());
        return usuarioCadastrado;
    }

    public Usuario alterarUsuario(Usuario novoUsuario){
        Usuario oldUsuario = pesquisarPorId(novoUsuario.getId());
        //alteraDados(novoUsuario, oldUsuario);
        novoUsuario.getEndereco().setId(oldUsuario.getEndereco().getId());
        enderecoRepository.save(novoUsuario.getEndereco());
        repository.save(novoUsuario);
        return novoUsuario;
    }

    public void excluirUsuario(Integer id){
        enderecoRepository.deleteById((repository.findById(id).get().getEndereco().getId()));
        repository.deleteById(id);
    }

    private void alteraDados(Usuario novoUsuario, Usuario oldUsuario){
        novoUsuario.setCpf(oldUsuario.getCpf());
        novoUsuario.setNome(oldUsuario.getNome());
        novoUsuario.setSobrenome(oldUsuario.getSobrenome());
        novoUsuario.getEndereco().setId(oldUsuario.getEndereco().getId());
        novoUsuario.setTelefone(oldUsuario.getTelefone());
        novoUsuario.setMetodoPagto(oldUsuario.getMetodoPagto());
        novoUsuario.setTipoPerfil(oldUsuario.getTipoPerfil());
        novoUsuario.setNomeUsuario(oldUsuario.getNomeUsuario());
        novoUsuario.setSenha(oldUsuario.getSenha());
    }
}
