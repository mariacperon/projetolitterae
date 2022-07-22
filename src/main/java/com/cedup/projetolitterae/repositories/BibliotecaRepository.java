package com.cedup.projetolitterae.repositories;

import com.cedup.projetolitterae.entities.Biblioteca;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BibliotecaRepository extends JpaRepository<Biblioteca, Integer> {

}
