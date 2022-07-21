package com.cedup.projetolitterae.resources;

import com.cedup.projetolitterae.entities.Endereco;
import com.cedup.projetolitterae.entities.Usuario;
import com.cedup.projetolitterae.services.EnderecoService;
import com.cedup.projetolitterae.services.UsuarioService;
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
@RequestMapping(value = "/endereco")
public class EnderecoResource {

    @Autowired
    EnderecoService enderecoService;

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public ResponseEntity<Endereco> pesquisarPorId(@PathVariable int id){
        Endereco endereco = enderecoService.pesquisarPorId(id);
        return ResponseEntity.ok().body(endereco);
    }

}
