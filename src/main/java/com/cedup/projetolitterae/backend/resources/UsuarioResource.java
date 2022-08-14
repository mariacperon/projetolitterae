package com.cedup.projetolitterae.backend.resources;

import com.cedup.projetolitterae.backend.entities.Usuario;
import com.cedup.projetolitterae.backend.services.UsuarioService;
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
@RequestMapping(value = "/usuario")
public class UsuarioResource {

    @Autowired
    UsuarioService usuarioService;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ResponseEntity<List<Usuario>> pesquisarTodos(){
        List<Usuario> usuarios = usuarioService.pesquisarTodos();
        return ResponseEntity.ok().body(usuarios);
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public ResponseEntity<Usuario> pesquisarPorId(@PathVariable int id){
        Usuario usuario = usuarioService.pesquisarPorId(id);
        return ResponseEntity.ok().body(usuario);
    }

    @RequestMapping(value = "/cadastrar",method = RequestMethod.POST)
    public ResponseEntity<Usuario> cadastrar(@Validated @RequestBody Usuario usuario){
        Usuario usuarioCadastrado = usuarioService.cadastrarUsuario(usuario);
        return ResponseEntity.ok().body(usuarioCadastrado);
    }

    @RequestMapping(value = "/alterar",method = RequestMethod.PUT)
    public ResponseEntity<Usuario> alterar(@Validated @RequestBody Usuario usuario){
        Usuario usuarioAlterado = usuarioService.alterarUsuario(usuario);
        return ResponseEntity.ok().body(usuarioAlterado);
    }

    @RequestMapping(value = "/excluir/{id}",method = RequestMethod.DELETE)
    public ResponseEntity<Void> excluir(@PathVariable Integer id){
        usuarioService.excluirUsuario(id);
        return ResponseEntity.noContent().build();
    }
}
