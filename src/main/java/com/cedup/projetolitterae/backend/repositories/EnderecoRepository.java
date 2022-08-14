package com.cedup.projetolitterae.backend.repositories;

import com.cedup.projetolitterae.backend.entities.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Integer> {

}
