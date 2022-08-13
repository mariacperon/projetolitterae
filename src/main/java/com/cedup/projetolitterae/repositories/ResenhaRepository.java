package com.cedup.projetolitterae.repositories;

import com.cedup.projetolitterae.entities.Resenha;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResenhaRepository extends JpaRepository<Resenha, Integer> {

    List<Resenha> findResenhaByLivroId(Integer id);
    List<Resenha> findResenhaByUsuarioId(Integer id);

}
