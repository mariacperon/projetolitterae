package com.cedup.projetolitterae.backend.resources;

import com.cedup.projetolitterae.backend.entities.Endereco;
import com.cedup.projetolitterae.backend.services.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
