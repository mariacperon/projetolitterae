package com.cedup.projetolitterae.backend.services;

import com.cedup.projetolitterae.backend.entities.Locacao;
import com.cedup.projetolitterae.backend.entities.MensagemRetorno;
import com.cedup.projetolitterae.backend.exceptions.MensagemRetornoException;
import com.cedup.projetolitterae.backend.enums.StatusLocacao;
import com.cedup.projetolitterae.backend.repositories.LocacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class LocacaoService {

    @Autowired
    private LocacaoRepository repository;
    @Autowired
    private LivroBibliotecaService livroBibliotecaService;

    public Locacao pesquisarPorId(Integer id){
        return (repository.findById(id)).orElse(null);
    }

    public List<Locacao> pesquisarPorLivro(Integer id){
        return repository.findLocacaoByLivroId(id);
    }

    public List<Locacao> pesquisarPorUsuario(Long id){
        return repository.findLocacaoByUsuarioId(id);
    }

    @Transactional
    public Locacao locarLivro(Locacao locacao){
        if(repository.qtdLivroLocado(locacao.getLivro().getId()) < locacao.getLivro().getQuantidadeEstoque()){
            locacao.setId(null);
            locacao.setLivro(livroBibliotecaService.pesquisarPorId(locacao.getLivro().getId()));
            validarLocacao(locacao);
            return repository.save(locacao);
        }else{
            throw new MensagemRetornoException(new MensagemRetorno("SEM ESTOQUE",
                    "Esse livro não está disponível para locação no momento."));
        }
    }

    public Locacao alterarLocacao(Locacao novoLivroUsuario){
        Locacao oldLivroUsuario = pesquisarPorId(novoLivroUsuario.getId());
        repository.save(oldLivroUsuario);

        return oldLivroUsuario;
    }

    public void devolver(Integer id) {
        Locacao locacao = pesquisarPorId(id);
        if(StatusLocacao.ANDAMENTO.equals(locacao.getStatusLocacao())){
            Date dataDdevolvida = new Date();
            Integer dia = dataDdevolvida.getDate();
            Integer mes = dataDdevolvida.getMonth();
            Integer ano = dataDdevolvida.getYear();
            locacao.setDataDevolvida(new java.sql.Date(ano, mes, dia));

            Boolean pendencia = validaDevolucao(locacao);
            repository.save(locacao);

            if(!pendencia){
                throw new MensagemRetornoException(new MensagemRetorno("PENDÊNCIA ABERTA",
                        "O livro foi devolvido, mas uma pendência foi aberta pelo atraso na devolução. Este usuário deve pagar R$"+
                                calculaMulta(locacao)+" de multa."));
            }
        }else{
            throw new MensagemRetornoException(new MensagemRetorno("ERRO",
                    "Esta locação não pode ser devolvida, pois seu status é "+ locacao.getStatusLocacao()));
        }
    }

    public void excluirLocacao(Integer id){
        repository.deleteById(id);
    }

    public Double pesquisaMulta(Integer id){
        Locacao locacao = pesquisarPorId(id);

        if(StatusLocacao.PENDENTE.equals(locacao.getStatusLocacao())){
            throw new MensagemRetornoException(new MensagemRetorno("ERRO",
                    "Esta locação não está pendente."));
        }

        return calculaMulta(locacao);
    }

    private void validarLocacao(Locacao locacao) {
        List<Locacao> locacoes = pesquisarPorUsuario(locacao.getUsuario().getId());
        for (Locacao l : locacoes) {
            if(new Date().after(l.getDataDevolucao()) || l.getStatusLocacao().equals(StatusLocacao.ANDAMENTO)){
                throw new MensagemRetornoException(new MensagemRetorno("ERRO",
                        "Erro ao locar livro, este usuário possui pendências."));
            }
        }
    }

    private Boolean validaDevolucao(Locacao locacao){
        if(locacao.getDataDevolvida().after(locacao.getDataDevolucao())){
            locacao.setStatusLocacao(StatusLocacao.PENDENTE);
            return false;
        }
        locacao.setStatusLocacao(StatusLocacao.DEVOLVIDO);
        return true;
    }

    private Double calculaMulta(Locacao locacao){
        Double taxaAtraso = locacao.getLivro().getBiblioteca().getTaxaAtraso();
        Double taxaPorDia = locacao.getLivro().getBiblioteca().getTaxaPorDia();

        long diasEmAtraso = TimeUnit.MILLISECONDS.toDays(Math.abs(locacao.getDataDevolvida().getTime() - locacao.getDataDevolucao().getTime()));

        return (diasEmAtraso * taxaPorDia) + taxaAtraso;
    }

}
