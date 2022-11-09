package com.cedup.projetolitterae.backend.repositories;

import com.cedup.projetolitterae.backend.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    List<Usuario> findUsuarioByBibliotecaId(Long id);

    @Query(value = "select * from usuario u where u.nome like %:nome%", nativeQuery = true)
    List<Usuario> buscarUsuariosPorNome(@Param("nome") String nome);

    @Query(value = "select * from usuario u where u.sobrenome like %:nome%", nativeQuery = true)
    List<Usuario> buscarUsuariosPorSobrenome(@Param("nome") String nome);
}
