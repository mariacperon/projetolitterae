package com.cedup.projetolitterae.backend.resources;

import com.cedup.projetolitterae.backend.dto.LoginBibliotecaDto;
import com.cedup.projetolitterae.backend.entities.Biblioteca;
import com.cedup.projetolitterae.backend.services.BibliotecaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
    public ResponseEntity<Biblioteca> pesquisarPorId(@PathVariable Long id){
        Biblioteca biblioteca = bibliotecaService.pesquisarPorId(id);
        return ResponseEntity.ok().body(biblioteca);
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
    public ResponseEntity<Void> excluir(@PathVariable Long id){
        bibliotecaService.excluirBiblioteca(id);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/alterar-senha",method = RequestMethod.PUT)
    public ResponseEntity<Boolean> alterarSenha(@RequestParam(value = "id") Long id, @RequestParam(value = "novaSenha") String senha){
        return ResponseEntity.ok().body(bibliotecaService.alterarSenha(id, senha));
    }

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public ResponseEntity<Biblioteca> login(@RequestBody LoginBibliotecaDto login){
        Biblioteca biblioteca = bibliotecaService.login(login.getId(), login.getSenha());
        return ResponseEntity.ok().body(biblioteca);
    }
}
