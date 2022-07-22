package com.cedup.projetolitterae.resources;

import com.cedup.projetolitterae.entities.Biblioteca;
import com.cedup.projetolitterae.entities.Usuario;
import com.cedup.projetolitterae.services.BibliotecaService;
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
@RequestMapping(value = "/biblioteca")
public class BibliotecaResource {

    @Autowired
    BibliotecaService bibliotecaService;

    @RequestMapping(value="/all", method = RequestMethod.GET)
    public ResponseEntity<List<Biblioteca>> pesquisarTodas(){
        List<Biblioteca> bibliotecas = bibliotecaService.pesquisarTodas();
        return ResponseEntity.ok().body(bibliotecas);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Biblioteca> pesquisarPorId(@PathVariable int id){
        Biblioteca biblioteca = bibliotecaService.pesquisarPorId(id);
        return ResponseEntity.ok().body(biblioteca);
    }

    @RequestMapping(value = "/usuarios/{id}", method = RequestMethod.GET)
    public ResponseEntity<List<Usuario>> pesquisarUsuarios(@PathVariable int id){
        List<Usuario> usuarios = bibliotecaService.pesquisarUsuarios(id);
        return ResponseEntity.ok().body(usuarios);
    }

    @RequestMapping(value = "/cadastrar",method = RequestMethod.POST)
    public ResponseEntity<Biblioteca> cadastrar(@Validated @RequestBody Biblioteca biblioteca){
        Biblioteca bibliotecaCadastrado = bibliotecaService.cadastrarBiblioteca(biblioteca);
        return ResponseEntity.ok().body(bibliotecaCadastrado);
    }

    @RequestMapping(value = "/alterar",method = RequestMethod.PUT)
    public ResponseEntity<Biblioteca> alterar(@Validated @RequestBody Biblioteca biblioteca){
        Biblioteca bibliotecaAlterado = bibliotecaService.alterarBiblioteca(biblioteca);
        return ResponseEntity.ok().body(bibliotecaAlterado);
    }

    @RequestMapping(value = "/excluir/{id}",method = RequestMethod.DELETE)
    public ResponseEntity<Void> excluir(@PathVariable Integer id){
        bibliotecaService.excluirBiblioteca(id);
        return ResponseEntity.noContent().build();
    }
}
