package com.cedup.projetolitterae.repositories;

import com.cedup.projetolitterae.entities.LivroUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LivroUsuarioRepository extends JpaRepository<LivroUsuario, Integer> {

}
