package com.cedup.projetolitterae.backend.resources;

import com.cedup.projetolitterae.backend.services.AutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/autor")
public class AutorResource {

    @Autowired
    AutorService autorService;

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public ResponseEntity<Autor> pesquisarPorId(@PathVariable int id){
        Autor autor = autorService.pesquisarPorId(id);
        return ResponseEntity.ok().body(autor);
    }

    @RequestMapping(value = "/cadastrar",method = RequestMethod.POST)
    public ResponseEntity<Autor> cadastrar(@Validated @RequestBody Autor autor){
        Autor autorCadastrado = autorService.cadastrarAutor(autor);
        return ResponseEntity.ok().body(autorCadastrado);
    }

    @RequestMapping(value = "/alterar",method = RequestMethod.PUT)
    public ResponseEntity<Autor> alterar(@Validated @RequestBody Autor autor){
        Autor autorAlterado = autorService.alterarAutor(autor);
        return ResponseEntity.ok().body(autorAlterado);
    }

    @RequestMapping(value = "/excluir/{id}",method = RequestMethod.DELETE)
    public ResponseEntity<Void> excluir(@PathVariable Integer id){
        autorService.excluirAutor(id);
        return ResponseEntity.noContent().build();
    }
}
