package com.cedup.projetolitterae.resources;

import com.cedup.projetolitterae.entities.Usuario;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/usuarios")
public class UsuarioResource {

    public ResponseEntity<String> pesuisarTodos(){

        return ResponseEntity.ok().body("maria linda testando o projetinho lindo");
    }
}
