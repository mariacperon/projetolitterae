package com.cedup.projetolitterae.backend.services;

import com.cedup.projetolitterae.backend.dto.ResenhaDto;
import com.cedup.projetolitterae.backend.entities.Livro;
import com.cedup.projetolitterae.backend.entities.MensagemRetorno;
import com.cedup.projetolitterae.backend.entities.Resenha;
import com.cedup.projetolitterae.backend.entities.Usuario;
import com.cedup.projetolitterae.backend.exceptions.MensagemRetornoException;
import com.cedup.projetolitterae.backend.repositories.ResenhaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ResenhaService {

    @Autowired
    private ResenhaRepository repository;
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private LivroService livroService;

    public Resenha pesquisarPorId(Integer id){
        return (repository.findById(id)).orElse(null);
    }

    public List<Resenha> pesquisarResenhaPorIdLivro(Integer id){
        return repository.findResenhaByLivroId(id);
    }

    public List<Resenha> pesquisarResenhaPorIdUsuario(Long id){
        return repository.findResenhaByUsuarioId(id);
    }

    @Transactional
    public Resenha cadastrarResenha(ResenhaDto resenhaDto){
        Resenha resenha = fromDto(resenhaDto);
        resenha.setId(null);
        return repository.save(resenha);
    }

    public Resenha alterarResenha(ResenhaDto novoResenhaDto){
        Resenha novoResenha = fromDto(novoResenhaDto);

        return repository.save(novoResenha);
    }

    public void excluirResenha(Integer id){
        repository.deleteById(id);
    }

    private Resenha fromDto(ResenhaDto resenhaDto){
        Usuario usuario = usuarioService.pesquisarPorId(resenhaDto.getIdUsuario());
        Livro livro = livroService.pesquisarPorId(resenhaDto.getIdLivro());

        Resenha resenha = new Resenha();
        resenha.setId(resenhaDto.getId());
        resenha.setLivro(livro != null ? livro : (Livro) lancaExcecaoDto("id livro"));
        resenha.setUsuario(usuario != null ? usuario : (Usuario)  lancaExcecaoDto("id usuário"));
        resenha.setResenha(resenhaDto.getResenha());

        return resenha;
    }

    private Object lancaExcecaoDto(String campo){
        throw new MensagemRetornoException(new MensagemRetorno("ERRO", "Campo "+ campo +" não existe."));
    }
}
