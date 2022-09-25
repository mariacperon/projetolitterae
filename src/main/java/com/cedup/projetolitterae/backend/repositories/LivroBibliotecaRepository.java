package com.cedup.projetolitterae.backend.repositories;

import com.cedup.projetolitterae.backend.entities.LivroBiblioteca;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LivroBibliotecaRepository extends JpaRepository<LivroBiblioteca, Integer> {

    List<LivroBiblioteca> findLivroBibliotecaByBibliotecaId(Long id);
}
