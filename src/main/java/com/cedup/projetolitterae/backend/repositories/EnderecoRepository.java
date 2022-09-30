package com.cedup.projetolitterae.backend.repositories;

import com.cedup.projetolitterae.backend.entities.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Integer> {

    class LoginDto {

        private Long id;
        private Date dataNascimento;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public Date getDataNascimento() {
            return dataNascimento;
        }

        public void setDataNascimento(Date dataNascimento) {
            this.dataNascimento = dataNascimento;
        }
    }
}
