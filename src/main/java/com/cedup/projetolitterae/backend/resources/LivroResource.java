package com.cedup.projetolitterae.backend.resources;

import com.cedup.projetolitterae.backend.dto.PesquisaLivroDto;
import com.cedup.projetolitterae.backend.entities.Livro;
import com.cedup.projetolitterae.backend.services.LivroService;
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
@RequestMapping(value = "/livro")
public class LivroResource {

    @Autowired
    LivroService livroService;

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public ResponseEntity<Livro> pesquisarPorId(@PathVariable int id){
        Livro livro = livroService.pesquisarPorId(id);
        return ResponseEntity.ok().body(livro);
    }

    @RequestMapping(value = "/pesquisar-por",method = RequestMethod.GET)
    public ResponseEntity<List<Livro>> pesquisaEspecifica(@RequestBody PesquisaLivroDto pesquisaLivro){
        List<Livro> livros = livroService.pesquisaLivroEspecifica(pesquisaLivro);
        return ResponseEntity.ok().body(livros);
    }

    @RequestMapping(value = "/all",method = RequestMethod.GET)
    public ResponseEntity<List<Livro>> pesquisarTodos(){
        List<Livro> livros = livroService.pesquisarTodos();
        return ResponseEntity.ok().body(livros);
    }

    @RequestMapping(value = "/cadastrar",method = RequestMethod.POST)
    public ResponseEntity<Livro> cadastrar(@Validated @RequestBody Livro livro){
        Livro livroCadastrado = livroService.cadastrarLivro(livro);
        return ResponseEntity.ok().body(livroCadastrado);
    }

    @RequestMapping(value = "/alterar",method = RequestMethod.PUT)
    public ResponseEntity<Livro> alterar(@Validated @RequestBody Livro livro){
        Livro livroAlterado = livroService.alterarLivro(livro);
        return ResponseEntity.ok().body(livroAlterado);
    }

    @RequestMapping(value = "/excluir/{id}",method = RequestMethod.DELETE)
    public ResponseEntity<Void> excluir(@PathVariable Integer id){
        livroService.excluirLivro(id);
        return ResponseEntity.noContent().build();
    }
}
