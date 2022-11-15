package com.cedup.projetolitterae.backend.services;

import com.cedup.projetolitterae.backend.dto.ImagemPerfilDto;
import com.cedup.projetolitterae.backend.dto.PesquisaLivroDto;
import com.cedup.projetolitterae.backend.dto.QuantidadesLocadosBibliotecaDto;
import com.cedup.projetolitterae.backend.entities.Livro;
import com.cedup.projetolitterae.backend.entities.LivroBiblioteca;
import com.cedup.projetolitterae.backend.entities.MensagemRetorno;
import com.cedup.projetolitterae.backend.entities.Resenha;
import com.cedup.projetolitterae.backend.enums.GeneroLivro;
import com.cedup.projetolitterae.backend.exceptions.MensagemRetornoException;
import com.cedup.projetolitterae.backend.repositories.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class LivroService {

    @Autowired
    private LivroRepository repository;
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private LivroBibliotecaService livroBibliotecaService;
    @Autowired
    private ResenhaService resenhaService;

    @Autowired
    private FileService fileService;

    public Livro pesquisarPorId(Integer id){
        return (repository.findById(id)).orElse(null);
    }

    public List<Livro> pesquisarTodos(){
        return repository.findAll() ;
    }

    public List<Livro> pesquisaLivroEspecifica(PesquisaLivroDto pesquisaLivro){
        Long idBiblioteca = usuarioService.pesquisarPorId(pesquisaLivro.getIdUsuario()).getBiblioteca().getId();

        return switch (pesquisaLivro.getCampo()) {
            case "nome" -> repository.findLivroNome("%"+pesquisaLivro.getValue()+"%", idBiblioteca);
            case "autor" -> repository.findLivroAutor("%"+pesquisaLivro.getValue()+"%", idBiblioteca);
            case "isdb" -> repository.findLivroIsdb("%"+pesquisaLivro.getValue()+"%", idBiblioteca);
            case "editora" -> repository.findLivroEditora("%"+pesquisaLivro.getValue()+"%", idBiblioteca);
            case "classificacao_etaria" -> repository.findLivroClassificacaoEtaria("%"+pesquisaLivro.getValue()+"%", idBiblioteca);
            case "genero" -> repository.findLivroGenero(Integer.parseInt(pesquisaLivro.getValue()), idBiblioteca);
            default -> null;
        };
    }

    public List<Livro> maisLocados(Long idBiblioteca){
        List<Livro> livrosMaisLocados = new ArrayList<>();

        List<QuantidadesLocadosBibliotecaDto> qtdLocacoesLivros = livroBibliotecaService.quantidadeLocacoesLivro(idBiblioteca);
        List<QuantidadesLocadosBibliotecaDto> maisLocados = qtdLocacoesLivros.stream().sorted(Comparator.comparing(QuantidadesLocadosBibliotecaDto::getQtdLocacoes).reversed()).collect(Collectors.toList());

        maisLocados.forEach(x -> livrosMaisLocados.add(pesquisarPorId(x.getIdLivro())));

        return livrosMaisLocados;
    }

    public List<Livro> pesquisarLivrosPorBiblioteca(Long idBiblioteca){
        List<Livro> livros = new ArrayList<>();
        List<LivroBiblioteca> livroBibliotecas = livroBibliotecaService.pesquisarPorBibliotecaId(idBiblioteca);

        livroBibliotecas.forEach(x -> livros.add(pesquisarPorId(x.getLivro().getId())));

        return livros;
    }

    @Transactional
    public Livro cadastrarLivro(Livro livro){
        livro.setId(null);
        validaGeneros(livro);
        return repository.save(livro);
    }

    public String salvaImagem(ImagemPerfilDto imagem) {
        String path = null;
        try {
            Livro livro = pesquisarPorId(imagem.getId().intValue());
            if (livro != null && imagem.getImagem() != null) {
                String diretorioArquivo = fileService.salvaArquivo("/livro/"+livro.getId()+"/", imagem.getImagem());
                livro.setImagem(diretorioArquivo);
                repository.save(livro);
                return diretorioArquivo;
            } else {
                throw new MensagemRetornoException(new MensagemRetorno("ERRO", "Houve um erro ao tentar salvar a imagem de capa."));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return path;
    }

    public Livro alterarLivro(Livro novoLivro){
        repository.save(novoLivro);
        validaGeneros(novoLivro);
        return novoLivro;
    }

    public void excluirLivro(Integer id){
        List<Resenha> resenhas = resenhaService.pesquisarResenhaPorIdLivro(id);
        resenhas.forEach(x -> resenhaService.excluirResenha(x.getId()));
        List<LivroBiblioteca> livroBibliotecas = livroBibliotecaService.pesquisarPorLivroId(id);
        //livroBibliotecaService.excluirLivroBiblioteca(livroBibliotecas.get(0).getId());
        livroBibliotecas.forEach(x ->livroBibliotecaService.excluirLivroBiblioteca(x.getId()));
        Livro livro = pesquisarPorId(id);
        repository.deleteById(id);
        if(livro.getImagem() != null){
            File imagem = new File(livro.getImagem());
            imagem.delete();
        }
    }

    private void validaGeneros(Livro livro){
        Set<Integer> generos = new HashSet<>();

        for(Integer genero : livro.getGeneros()){
            if(!generos.add(genero)){
                throw new MensagemRetornoException(new MensagemRetorno("ERRO", "Um gênero foi selecionado mais de uma vez."));
            }
        }

    }
}
