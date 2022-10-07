package com.cedup.projetolitterae.backend.services;

import com.cedup.projetolitterae.backend.dto.LivroBibliotecaDto;
import com.cedup.projetolitterae.backend.dto.LocacaoDto;
import com.cedup.projetolitterae.backend.entities.Biblioteca;
import com.cedup.projetolitterae.backend.entities.Livro;
import com.cedup.projetolitterae.backend.entities.LivroBiblioteca;
import com.cedup.projetolitterae.backend.entities.Locacao;
import com.cedup.projetolitterae.backend.entities.MensagemRetorno;
import com.cedup.projetolitterae.backend.entities.Usuario;
import com.cedup.projetolitterae.backend.exceptions.MensagemRetornoException;
import com.cedup.projetolitterae.backend.enums.StatusLocacao;
import com.cedup.projetolitterae.backend.repositories.LocacaoRepository;
import com.cedup.projetolitterae.backend.repositories.UsuarioRepository;
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
    @Autowired
    private UsuarioService usuarioService;

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
    public Locacao locarLivro(LocacaoDto locacaoDto){
        Locacao locacao = fromDto(locacaoDto);
        if(repository.qtdLivroLocado(locacao.getLivro().getId()) < locacao.getLivro().getQuantidadeEstoque()){
            locacao.setId(null);
            locacao.setDataDevolvida(null);
            locacao.setStatusLocacao(StatusLocacao.ANDAMENTO);
            validarLocacao(locacao);
            return repository.save(locacao);
        }else{
            throw new MensagemRetornoException(new MensagemRetorno("SEM ESTOQUE",
                    "Esse livro não está disponível para locação no momento."));
        }
    }

    public Locacao alterarLocacao(LocacaoDto novoLivroUsuarioDto){
        Locacao locacao = fromDto(novoLivroUsuarioDto);

        return repository.save(locacao);
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
            if(new Date().after(l.getDataDevolucao()) || l.getStatusLocacao().equals(StatusLocacao.PENDENTE)){
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

    private Locacao fromDto(LocacaoDto locacaoDto){
        LivroBiblioteca livroBiblioteca = livroBibliotecaService.pesquisarPorId(locacaoDto.getIdLivroBiblioteca());
        Usuario usuario = usuarioService.pesquisarPorId(locacaoDto.getIdUsuario());

        Locacao locacao = new Locacao();
        locacao.setId(locacaoDto.getId());
        locacao.setLivro(livroBiblioteca != null ? livroBiblioteca : (LivroBiblioteca) lancaExcecaoDto("id livro"));
        locacao.setUsuario(usuario != null ? usuario : (Usuario) lancaExcecaoDto("id usuário"));
        locacao.setDataDevolucao(locacaoDto.getDataDevolucao());
        locacao.setDataLocacao(locacaoDto.getDataLocacao());
        locacao.setDataDevolvida(locacaoDto.getDataDevolvida());
        locacao.setStatusLocacao(StatusLocacao.toEnum(locacaoDto.getStatusLocacao()));

        return locacao;
    }

    private Object lancaExcecaoDto(String campo){
        throw new MensagemRetornoException(new MensagemRetorno("ERRO", "Campo "+ campo +" não existe."));
    }

}
