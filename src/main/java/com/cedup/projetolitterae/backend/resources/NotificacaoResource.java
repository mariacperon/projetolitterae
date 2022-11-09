package com.cedup.projetolitterae.backend.resources;

import com.cedup.projetolitterae.backend.dto.ResenhaDto;
import com.cedup.projetolitterae.backend.entities.Notificacao;
import com.cedup.projetolitterae.backend.entities.Resenha;
import com.cedup.projetolitterae.backend.services.NotificacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/notificacao")
public class NotificacaoResource {

    @Autowired
    NotificacaoService notificacaoService;

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public ResponseEntity<List<Notificacao>> pesquisarPorIdBiblioteca(@PathVariable Long id){
        List<Notificacao> notificacao = notificacaoService.pesquisarPorIdBiblioteca(id);
        return ResponseEntity.ok().body(notificacao);
    }
}
