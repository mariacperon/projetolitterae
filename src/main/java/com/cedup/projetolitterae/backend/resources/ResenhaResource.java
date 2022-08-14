package com.cedup.projetolitterae.backend.resources;

import com.cedup.projetolitterae.backend.entities.Resenha;
import com.cedup.projetolitterae.backend.services.ResenhaService;
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
@RequestMapping(value = "/resenha")
public class ResenhaResource {

    @Autowired
    ResenhaService resenhaService;

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public ResponseEntity<Resenha> pesquisarPorId(@PathVariable int id){
        Resenha resenha = resenhaService.pesquisarPorId(id);
        return ResponseEntity.ok().body(resenha);
    }

    @RequestMapping(value = "/livro/{id}",method = RequestMethod.GET)
    public ResponseEntity<List<Resenha>> pesquisarResenhasPorIdLivro(@PathVariable int id){
        List<Resenha> resenha = resenhaService.pesquisarResenhaPorIdLivro(id);
        return ResponseEntity.ok().body(resenha);
    }

    @RequestMapping(value = "/usuario/{id}",method = RequestMethod.GET)
    public ResponseEntity<List<Resenha>> pesquisarResenhasPorIdUsuario(@PathVariable int id){
        List<Resenha> resenha = resenhaService.pesquisarResenhaPorIdUsuario(id);
        return ResponseEntity.ok().body(resenha);
    }

    @RequestMapping(value = "/cadastrar",method = RequestMethod.POST)
    public ResponseEntity<Resenha> cadastrar(@Validated @RequestBody Resenha resenha){
        Resenha resenhaCadastrado = resenhaService.cadastrarResenha(resenha);
        return ResponseEntity.ok().body(resenhaCadastrado);
    }

    @RequestMapping(value = "/alterar",method = RequestMethod.PUT)
    public ResponseEntity<Resenha> alterar(@Validated @RequestBody Resenha resenha){
        Resenha resenhaAlterado = resenhaService.alterarResenha(resenha);
        return ResponseEntity.ok().body(resenhaAlterado);
    }

    @RequestMapping(value = "/excluir/{id}",method = RequestMethod.DELETE)
    public ResponseEntity<Void> excluir(@PathVariable Integer id){
        resenhaService.excluirResenha(id);
        return ResponseEntity.noContent().build();
    }
}
