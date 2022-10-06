package com.cedup.projetolitterae.backend.resources;

import com.cedup.projetolitterae.backend.dto.LivroBibliotecaDto;
import com.cedup.projetolitterae.backend.entities.LivroBiblioteca;
import com.cedup.projetolitterae.backend.services.LivroBibliotecaService;
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
@RequestMapping(value = "/livro-biblioteca")
public class LivroBibliotecaResource {

    @Autowired
    LivroBibliotecaService livroBibliotecaService;

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public ResponseEntity<LivroBiblioteca> pesquisarPorId(@PathVariable int id){
        LivroBiblioteca livroBiblioteca = livroBibliotecaService.pesquisarPorId(id);
        return ResponseEntity.ok().body(livroBiblioteca);
    }

    @RequestMapping(value = "/cadastrar",method = RequestMethod.POST)
    public ResponseEntity<LivroBiblioteca> cadastrar(@Validated @RequestBody LivroBibliotecaDto livroBiblioteca){
        LivroBiblioteca livroBibliotecaCadastrado = livroBibliotecaService.cadastrarLivroBiblioteca(livroBiblioteca);
        return ResponseEntity.ok().body(livroBibliotecaCadastrado);
    }

    @RequestMapping(value = "/alterar",method = RequestMethod.PUT)
    public ResponseEntity<LivroBiblioteca> alterar(@Validated @RequestBody LivroBibliotecaDto livroBiblioteca){
        LivroBiblioteca livroBibliotecaAlterado = livroBibliotecaService.alterarLivroBiblioteca(livroBiblioteca);
        return ResponseEntity.ok().body(livroBibliotecaAlterado);
    }

    @RequestMapping(value = "/excluir/{id}",method = RequestMethod.DELETE)
    public ResponseEntity<Void> excluir(@PathVariable Integer id){
        livroBibliotecaService.excluirLivroBiblioteca(id);
        return ResponseEntity.noContent().build();
    }
}
