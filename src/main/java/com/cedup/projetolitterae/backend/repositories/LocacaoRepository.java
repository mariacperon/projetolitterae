package com.cedup.projetolitterae.backend.repositories;

import com.cedup.projetolitterae.backend.entities.Locacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocacaoRepository extends JpaRepository<Locacao, Integer> {

    List<Locacao> findLocacaoByLivroId(Integer id);
    List<Locacao> findLocacaoByUsuarioId(Long id);

    @Query(value = "select count(id) from locacao where id_livro = :id and status_locacao != 1", nativeQuery = true)
    Integer qtdLivroLocado(@Param("id") Integer idLivro);

}
