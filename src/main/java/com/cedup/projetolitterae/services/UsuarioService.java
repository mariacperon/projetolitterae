package com.cedup.projetolitterae.services;

import com.cedup.projetolitterae.entities.Usuario;
import com.cedup.projetolitterae.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    public Optional<Usuario> pesquisar(Integer id){
        Optional<Usuario> obj = repository.findById(id);
        return obj;
    }

    @Transactional
    public Usuario cadastrarUsuario(Usuario usuario){
        return repository.save(usuario);
    }

    public Usuario alterarUsuario(Usuario novoUsuario){
        Usuario oldUsuario = (pesquisar(novoUsuario.getId())).get();
        alteraDados(oldUsuario, novoUsuario);
        return novoUsuario;
    }

    public void excluirUsuario(Integer id){
        repository.deleteById(id);
    }

    private Usuario alteraDados(Usuario oldUsuario, Usuario novoUsuario){
        List<Field> fieldsOld = List.of(oldUsuario.getClass().getDeclaredFields());
        List<Field> fieldsNovo = List.of(novoUsuario.getClass().getDeclaredFields());

        return novoUsuario;
    }
}
