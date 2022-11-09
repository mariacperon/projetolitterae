package com.cedup.projetolitterae.backend.services;

import com.cedup.projetolitterae.backend.dto.NotificacaoDto;
import com.cedup.projetolitterae.backend.entities.Biblioteca;
import com.cedup.projetolitterae.backend.entities.MensagemRetorno;
import com.cedup.projetolitterae.backend.entities.Notificacao;
import com.cedup.projetolitterae.backend.exceptions.MensagemRetornoException;
import com.cedup.projetolitterae.backend.repositories.NotificacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificacaoService {

    @Autowired
    BibliotecaService bibliotecaService;
    @Autowired
    NotificacaoRepository notificacaoRepository;

    public List<Notificacao> pesquisarPorIdBiblioteca(Long idBiblioteca){
        return notificacaoRepository.findNotificacaoByIdBiblioteca(idBiblioteca);
    }

    public Notificacao cadastrarNotificacao(NotificacaoDto notificacaoDto){
        Notificacao notificacao = fromDto(notificacaoDto);
        notificacao.setId(null);
        return notificacaoRepository.save(notificacao);
    }

    private Notificacao fromDto(NotificacaoDto notificacaoDto){
        Notificacao notificacao = new Notificacao();
        Biblioteca biblioteca = bibliotecaService.pesquisarPorId(notificacaoDto.getIdBiblioteca());

        notificacao.setId(notificacaoDto.getId());
        notificacao.setBiblioteca(biblioteca != null ? biblioteca : (Biblioteca) lancaExcecaoDto("id biblioteca"));
        notificacao.setTitulo(notificacaoDto.getTitulo());
        notificacao.setMensagem(notificacaoDto.getMensagem());

        return notificacao;
    }

    private Object lancaExcecaoDto(String campo){
        throw new MensagemRetornoException(new MensagemRetorno("ERRO", "Campo "+ campo +" não existe."));
    }
}
