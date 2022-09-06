package com.cedup.projetolitterae.backend.services;

import com.cedup.projetolitterae.backend.entities.Locacao;
import com.cedup.projetolitterae.backend.enums.StatusLocacao;
import com.cedup.projetolitterae.backend.repositories.LocacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Period;
import java.util.Date;
import java.util.List;

@Service
public class LocacaoService {

    @Autowired
    private LocacaoRepository repository;
    @Autowired
    private LivroBibliotecaService livroBibliotecaService;

    public Locacao pesquisarPorId(Integer id){
        return (repository.findById(id)).get();
    }

    public List<Locacao> pesquisarPorLivro(Integer id){
        return repository.findLocacaoByLivroId(id);
    }

    public List<Locacao> pesquisarPorUsuario(Integer id){
        return repository.findLocacaoByUsuarioId(id);
    }

    @Transactional
    public Locacao locarLivro(Locacao locacao){
        locacao.setId(null);
        locacao.setLivro(livroBibliotecaService.pesquisarPorId(locacao.getLivro().getId()));
        validarLocacao(locacao);
        return repository.save(locacao);
    }

    public Locacao alterarLocacao(Locacao novoLivroUsuario){
        Locacao oldLivroUsuario = pesquisarPorId(novoLivroUsuario.getId());
        repository.save(oldLivroUsuario);

        return oldLivroUsuario;
    }

    public void devolver(Integer id){
        Locacao locacao = pesquisarPorId(id);
        locacao.setDataDevolvida((java.sql.Date) new Date());
        validaDevolucao(locacao);
    }

    public void excluirLocacao(Integer id){
        repository.deleteById(id);
    }

    private void validarLocacao(Locacao locacao) {
        List<Locacao> locacoes = pesquisarPorUsuario(locacao.getUsuario().getId());
        for (Locacao l : locacoes) {
            if(new Date().after(l.getDataDevolucao()) || l.getStatusLocacao().equals(StatusLocacao.ANDAMENTO)){
                throw new RuntimeException("Teste");
            }
        }
    }

    private void validaDevolucao(Locacao locacao){
        if(locacao.getDataDevolvida().after(locacao.getDataDevolucao())){
            Double taxaAtraso = locacao.getLivro().getBiblioteca().getTaxaAtraso();
            Double taxaPorDia = locacao.getLivro().getBiblioteca().getTaxaPorDia();

            long diasEmAtraso = Math.abs(locacao.getDataDevolvida().getTime() - locacao.getDataDevolucao().getTime());

            Double multa = (diasEmAtraso * taxaPorDia) + taxaAtraso;
        }
    }

}
