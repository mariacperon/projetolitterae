package com.cedup.projetolitterae.backend.repositories;

import com.cedup.projetolitterae.backend.entities.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AutorRepository extends JpaRepository<Autor, Integer> {
}
