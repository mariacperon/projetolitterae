package com.cedup.projetolitterae.backend.repositories;

import com.cedup.projetolitterae.backend.entities.UltimoId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UltimoIdRepository extends JpaRepository<UltimoId, Integer> {

    @Query(value = "select ui.ultimo_id from ultimo_id ui where ui.perfil = \"usuario\"", nativeQuery = true)
    Long buscarUltimoIdUsuario();

    @Query(value = "select ui.ultimo_id from ultimo_id ui where ui.perfil = \"biblioteca\"", nativeQuery = true)
    Long buscarUltimoIdBiblioteca();
}
