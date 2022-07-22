package com.cedup.projetolitterae.repositories;

import com.cedup.projetolitterae.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    List<Usuario> findUsuarioByBibliotecaId(Integer id);
}
