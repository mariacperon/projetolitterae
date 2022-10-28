package com.cedup.projetolitterae.backend.services;

import com.cedup.projetolitterae.backend.dto.ImagemPerfilDto;
import com.cedup.projetolitterae.backend.dto.PesquisaLivroDto;
import com.cedup.projetolitterae.backend.dto.QuantidadesLocadosBibliotecaDto;
import com.cedup.projetolitterae.backend.entities.Livro;
import com.cedup.projetolitterae.backend.entities.Locacao;
import com.cedup.projetolitterae.backend.entities.MensagemRetorno;
import com.cedup.projetolitterae.backend.exceptions.MensagemRetornoException;
import com.cedup.projetolitterae.backend.repositories.LivroRepository;
import com.cedup.projetolitterae.imagens.FileUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LivroService {

    @Autowired
    private LivroRepository repository;
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private LivroBibliotecaService livroBibliotecaService;

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
        qtdLocacoesLivros.stream().sorted(Comparator.comparing(QuantidadesLocadosBibliotecaDto::getQtdLocacoes)).collect(Collectors.toList());

        qtdLocacoesLivros.forEach(x -> livrosMaisLocados.add(pesquisarPorId(x.getIdLivro())));

        return livrosMaisLocados;
    }


    @Transactional
    public Livro cadastrarLivro(Livro livro){
        livro.setId(null);
        return repository.save(livro);
    }

    public String salvaImagem(ImagemPerfilDto imagem) {
        String path = null;
        try {
            Livro livro = pesquisarPorId(imagem.getId().intValue());
            if (livro != null) {
                String pasta = "src/main/java/com/cedup/projetolitterae/imagens/livros";
                String nomeArquivo = livro.getId() + ".jpeg";

                Path diretorio = Paths.get(pasta + "/" + nomeArquivo);

                if (!Files.exists(diretorio)) {
                    Files.copy(imagem.getImagem().getInputStream(), diretorio);

                    livro.setImagem(diretorio.toString());
                    repository.save(livro);
                }

                FileUploadUtil.saveFile(pasta, nomeArquivo, imagem.getImagem());

                path = diretorio.toString();
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
        return novoLivro;
    }

    public void excluirLivro(Integer id){
        Livro livro = pesquisarPorId(id);
        repository.deleteById(id);
        File imagem = new File(livro.getImagem());
        imagem.delete();
    }
}
