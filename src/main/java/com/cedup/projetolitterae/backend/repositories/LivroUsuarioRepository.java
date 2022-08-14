package com.cedup.projetolitterae.backend.repositories;

import com.cedup.projetolitterae.backend.entities.LivroUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LivroUsuarioRepository extends JpaRepository<LivroUsuario, Integer> {

}
