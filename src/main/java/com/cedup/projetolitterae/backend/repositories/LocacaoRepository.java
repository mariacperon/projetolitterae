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

    @Query(value = "select * from locacao l " +
            "inner join livro_biblioteca lb " +
            "on lb.id = l.id_livro " +
            "and lb.id_biblioteca = :id " +
            "where l.status_locacao = 2", nativeQuery = true)
    List<Locacao> locacoesPendentes(@Param("id") Long id);

    @Query(value = "select * from locacao l " +
            "inner join livro_biblioteca lb " +
            "on lb.id = l.id_livro " +
            "and lb.id_biblioteca = :id ", nativeQuery = true)
    List<Locacao> locacoesPorBiblioteca(@Param("id") Long id);

    @Query(value = "select * from locacao l " +
            "inner join livro_biblioteca lb " +
            "on lb.id = l.id_livro " +
            "and lb.id_biblioteca = :id " +
            "inner join usuario u " +
            "on u.id = l.id_usuario " +
            "and u.nome like %:nome% ", nativeQuery = true)
    List<Locacao> locacoesPorNomeUsuario(@Param("id") Long idBiblioteca, @Param("nome") String nome);

    @Query(value = "select * from locacao l " +
            "inner join livro_biblioteca lb " +
            "on lb.id = l.id_livro " +
            "and lb.id_biblioteca = :id " +
            "inner join usuario u " +
            "on u.id = l.id_usuario " +
            "and u.sobrenome like %:sobrenome%", nativeQuery = true)
    List<Locacao> locacoesPorSobrenomeUsuario(@Param("id") Long idBiblioteca, @Param("sobrenome") String sobrenome);

    @Query(value = "select * from locacao l " +
            "inner join livro_biblioteca lb " +
            "on lb.id = l.id_livro " +
            "and lb.id_biblioteca = :id " +
            "inner join livro l2 " +
            "on lb.id_livro = l2.id " +
            "and l2.nome like %:nome%", nativeQuery = true)
    List<Locacao> locacoesPorNomeLivro(@Param("id") Long idBiblioteca, @Param("nome") String sobrenome);

    @Query(value = "select * from locacao l " +
            "inner join livro_biblioteca lb " +
            "on lb.id = l.id_livro " +
            "and lb.id_biblioteca = :id " +
            "inner join usuario u " +
            "on u.id = l.id_usuario " +
            "and u.nome like %:nome% " +
            "where l.status_locacao = 2", nativeQuery = true)
    List<Locacao> locacoesPorNomeUsuarioPendentes(@Param("id") Long idBiblioteca, @Param("nome") String nome);

    @Query(value = "select * from locacao l " +
            "inner join livro_biblioteca lb " +
            "on lb.id = l.id_livro " +
            "and lb.id_biblioteca = :id " +
            "inner join usuario u " +
            "on u.id = l.id_usuario " +
            "and u.sobrenome like %:sobrenome% " +
            "where l.status_locacao = 2", nativeQuery = true)
    List<Locacao> locacoesPorSobrenomeUsuarioPendentes(@Param("id") Long idBiblioteca, @Param("sobrenome") String sobrenome);

    @Query(value = "select * from locacao l " +
            "inner join livro_biblioteca lb " +
            "on lb.id = l.id_livro " +
            "and lb.id_biblioteca = :id " +
            "inner join livro l2 " +
            "on lb.id_livro = l2.id " +
            "and l2.nome like %:nome% " +
            "where l.status_locacao = 2", nativeQuery = true)
    List<Locacao> locacoesPorNomeLivroPendentes(@Param("id") Long idBiblioteca, @Param("nome") String sobrenome);

}