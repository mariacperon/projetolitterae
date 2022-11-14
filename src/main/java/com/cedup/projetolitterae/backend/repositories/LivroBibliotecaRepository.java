package com.cedup.projetolitterae.backend.repositories;

import com.cedup.projetolitterae.backend.dto.QuantidadesLocadosBibliotecaDto;
import com.cedup.projetolitterae.backend.entities.LivroBiblioteca;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LivroBibliotecaRepository extends JpaRepository<LivroBiblioteca, Integer> {

    List<LivroBiblioteca> findLivroBibliotecaByBibliotecaId(Long id);
    List<LivroBiblioteca> findLivroBibliotecaByLivroId(Integer id);

    @Query(name = "qtd_locacoes_livros", nativeQuery = true)
    List<QuantidadesLocadosBibliotecaDto> quantidadeLocacoesLivrosPorBibliotecaId(@Param("idbiblioteca") Long idBiblioteca);
}
