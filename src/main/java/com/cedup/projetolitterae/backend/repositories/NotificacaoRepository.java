package com.cedup.projetolitterae.backend.repositories;

import com.cedup.projetolitterae.backend.entities.Notificacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificacaoRepository extends JpaRepository<Notificacao, Integer> {

    List<Notificacao> findNotificacaoByIdBiblioteca(Long id);
}
