package com.cedup.projetolitterae.resources;

import com.cedup.projetolitterae.entities.Usuario;
import com.cedup.projetolitterae.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/usuario")
public class UsuarioResource {

    @Autowired
    UsuarioService usuarioService;

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

    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public ResponseEntity<Void> excluir(@PathVariable Integer id){
        usuarioService.excluirUsuario(id);
        return ResponseEntity.noContent().build();
    }
}
