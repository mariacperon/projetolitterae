package com.cedup.projetolitterae.backend.services;

import com.cedup.projetolitterae.backend.dto.ImagemPerfilDto;
import com.cedup.projetolitterae.backend.dto.LoginUsuarioDto;
import com.cedup.projetolitterae.backend.entities.MensagemRetorno;
import com.cedup.projetolitterae.backend.entities.Usuario;
import com.cedup.projetolitterae.backend.exceptions.MensagemRetornoException;
import com.cedup.projetolitterae.backend.repositories.EnderecoRepository;
import com.cedup.projetolitterae.backend.repositories.UsuarioRepository;
import com.cedup.projetolitterae.imagens.FileUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Random;

import org.apache.commons.io.FilenameUtils;

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

    public Usuario login(LoginUsuarioDto login){
        login.getDataNascimento().setDate(login.getDataNascimento().getDate() + 1);
        Usuario usuario = pesquisarPorId(login.getId());

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        if(usuario == null){
            throw new MensagemRetornoException(new MensagemRetorno("ERRO",
                    "Erro ao fazder login."));
        }

        return usuario;
    }

    public String salvaImagem(ImagemPerfilDto imagem) {
        try {
            Usuario usuario = pesquisarPorId(imagem.getIdUsuario());
            if(usuario != null){
                String pasta = "src/main/java/com/cedup/projetolitterae/imagens/perfil";
                String nomeArquivo = usuario.getId() + ".jpeg";

                Path diretorio = Paths.get(pasta + "/" + nomeArquivo);

                if (!Files.exists(diretorio)) {
                    Files.copy(imagem.getImagem().getInputStream(), diretorio);

                    usuario.setImagem(diretorio.toString());
                    repository.save(usuario);
                }

                FileUploadUtil.saveFile(pasta, nomeArquivo, imagem.getImagem());

                return diretorio.toString();
            }else{
                throw new MensagemRetornoException(new MensagemRetorno("ERRO", "Houve um erro ao tentar salvar a imagem de perfil."));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
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
