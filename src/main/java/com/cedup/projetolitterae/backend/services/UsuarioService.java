package com.cedup.projetolitterae.backend.services;

import com.cedup.projetolitterae.backend.dto.ImagemPerfilDto;
import com.cedup.projetolitterae.backend.dto.LoginUsuarioDto;
import com.cedup.projetolitterae.backend.dto.UsuarioDto;
import com.cedup.projetolitterae.backend.entities.Biblioteca;
import com.cedup.projetolitterae.backend.entities.Locacao;
import com.cedup.projetolitterae.backend.entities.MensagemRetorno;
import com.cedup.projetolitterae.backend.entities.Resenha;
import com.cedup.projetolitterae.backend.entities.Usuario;
import com.cedup.projetolitterae.backend.exceptions.MensagemRetornoException;
import com.cedup.projetolitterae.backend.repositories.UsuarioRepository;
import com.cedup.projetolitterae.imagens.FileUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Service
public class UsuarioService{

    @Autowired
    private UsuarioRepository repository;
    @Autowired
    private EnderecoService enderecoService;
    @Autowired
    private ResenhaService resenhaService;
    @Autowired
    private LocacaoService locacaoService;
    @Autowired
    private BibliotecaService bibliotecaService;

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
        Usuario usuario = pesquisarPorId(login.getId());

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        if(usuario == null){
            throw new MensagemRetornoException(new MensagemRetorno("ERRO",
                    "Erro ao fazder login."));
        }

        return usuario;
    }

    public String salvaImagem(ImagemPerfilDto imagem) {
        String path = null;
        try {
            Usuario usuario = pesquisarPorId(imagem.getId());
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

                path = diretorio.toString();
            }else{
                throw new MensagemRetornoException(new MensagemRetorno("ERRO", "Houve um erro ao tentar salvar a imagem de perfil."));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return path;
    }

    @Transactional
    public Usuario cadastrarUsuario(UsuarioDto usuarioDto){
        Usuario usuario = fromDto(usuarioDto);
        usuario.setId(gerarIdUsuario());
        enderecoService.cadastrarEndereco(usuario.getEnderecoUsuario());
        return repository.save(usuario);
    }

    public Usuario alterarUsuario(Usuario novoUsuario){
        Usuario oldUsuario = pesquisarPorId(novoUsuario.getId());
        novoUsuario.getEnderecoUsuario().setId(oldUsuario.getEnderecoUsuario().getId());
        enderecoService.alterarEndereco(novoUsuario.getEnderecoUsuario());
        repository.save(novoUsuario);
        return novoUsuario;
    }

    public void excluirUsuario(Long id){
        Usuario usuario = pesquisarPorId(id);
        if(usuario != null){
            List<Resenha> resenhas = resenhaService.pesquisarResenhaPorIdUsuario(id);
            resenhas.forEach(x -> resenhaService.excluirResenha(x.getId()));

            List<Locacao> locacoes = locacaoService.pesquisarPorUsuario(id);
            locacoes.forEach(x -> locacaoService.excluirLocacao(x.getId()));

            repository.deleteById(id);
            enderecoService.excluirEndereco(usuario.getEnderecoUsuario().getId());

            File imagem = new File(usuario.getImagem());
            imagem.delete();
        }
    }

    private Long gerarIdUsuario(){
        Random random = new Random();
        final Long[] idUsuario = {null};
        List<Usuario> todosUsuarios = pesquisarTodos();

        todosUsuarios.stream().map(x -> {
            long id = random.nextLong(1000L, 100000L);

            if(Objects.equals(x.getId(), id)){
                id = random.nextLong(1000L, 100000L);
                idUsuario[0] = id;
            }

            return idUsuario[0];
        }).collect(Collectors.toList());

        return idUsuario[0];
    }

    private Usuario fromDto(UsuarioDto usuarioDto){
        Usuario usuario = new Usuario();
        Biblioteca biblioteca = bibliotecaService.pesquisarPorId(usuarioDto.getIdBiblioteca());

        usuario.setId(usuarioDto.getId());
        usuario.setCpf(usuarioDto.getCpf());
        usuario.setNome(usuarioDto.getNome());
        usuario.setSobrenome(usuarioDto.getSobrenome());
        usuario.setEnderecoUsuario(usuarioDto.getEnderecoUsuario());
        usuario.setBiblioteca(biblioteca != null ? biblioteca : (Biblioteca) lancaExcecaoDto("id biblioteca"));
        usuario.setEmail(usuarioDto.getEmail());
        usuario.setDataNascimento(usuarioDto.getDataNascimento());
        usuario.setTelefone1(usuarioDto.getTelefone1());
        usuario.setTelefone2(usuarioDto.getTelefone2());
        usuario.setImagem(usuarioDto.getImagem());
        usuario.setTipoPerfil(usuario.getTipoPerfil());
        usuario.setAtivo(usuarioDto.isAtivo());

        return usuario;
    }

    private Object lancaExcecaoDto(String campo){
        throw new MensagemRetornoException(new MensagemRetorno("ERRO", "Campo "+ campo +" não existe."));
    }
}
