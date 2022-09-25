package com.cedup.projetolitterae.backend.repositories;

import com.cedup.projetolitterae.backend.entities.Locacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocacaoRepository extends JpaRepository<Locacao, Integer> {

    List<Locacao> findLocacaoByLivroId(Integer id);
    List<Locacao> findLocacaoByUsuarioId(Long id);

}
