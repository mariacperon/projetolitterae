package com.cedup.projetolitterae.backend.services;

import com.cedup.projetolitterae.backend.dto.LoginDto;
import com.cedup.projetolitterae.backend.entities.MensagemRetorno;
import com.cedup.projetolitterae.backend.entities.Usuario;
import com.cedup.projetolitterae.backend.exceptions.MensagemRetornoException;
import com.cedup.projetolitterae.backend.repositories.EnderecoRepository;
import com.cedup.projetolitterae.backend.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;
    @Autowired
    private EnderecoRepository enderecoRepository;

    public Usuario pesquisarPorId(Long id){
        return repository.findById(id).orElse(null);
    }

    public List<Usuario> pesquisarUsuariosPorBiblioteca(Long id){
        return repository.findUsuarioByBibliotecaId(id);
    }

    public List<Usuario> pesquisarTodos(){
        return repository.findAll();
    }

    public Usuario login(LoginDto login){
        login.getDataNascimento().setDate(login.getDataNascimento().getDate() + 1);
        Usuario usuario = pesquisarPorId(login.getId());

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        if(usuario == null){
            throw new MensagemRetornoException(new MensagemRetorno("ERRO",
                    "O número de cadastro não foi encontrado em nossos registros."));
        }

        if(!sdf.format(usuario.getDataNascimento()).equals(sdf.format(login.getDataNascimento()))){
            throw new MensagemRetornoException(new MensagemRetorno("ERRO",
                    "Data de nascimento inválida."));
        }

        return usuario;
    }

    @Transactional
    public Usuario cadastrarUsuario(Usuario usuario){
        Random random = new Random();
        usuario.setId(random.nextLong(1000L, 100000L));
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

    public void excluirUsuario(Long id){
        Integer idEndereco = repository.findById(id).get().getEnderecoUsuario().getId();
        repository.deleteById(id);
        enderecoRepository.deleteById(idEndereco);
    }
}
