package com.cedup.projetolitterae.backend.entities;

import com.cedup.projetolitterae.backend.dto.QuantidadesLocadosBibliotecaDto;

import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQuery;
import javax.persistence.SqlResultSetMapping;

@Entity
@NamedNativeQuery(
        name = "qtd_locacoes_livros",
        query ="select lb.id_livro as idLivro, count(*) as qtdLocacoes from livro_biblioteca lb " +
                "inner join locacao l on l.id_livro = lb.id " +
                "where lb.id_biblioteca = :idbiblioteca " +
                "group by lb.id",
        resultSetMapping = "qtd_locacoes_livros_dto"
)
@SqlResultSetMapping(
        name = "qtd_locacoes_livros_dto",
        classes = @ConstructorResult(
                targetClass = QuantidadesLocadosBibliotecaDto.class,
                columns = {
                        @ColumnResult(name = "idLivro", type = Integer.class),
                        @ColumnResult(name = "qtdLocacoes", type = Integer.class)
                }
        )
)
public class LivroBiblioteca {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_livro")
    private Livro livro;

    @ManyToOne
    @JoinColumn(name = "id_biblioteca")
    private Biblioteca biblioteca;

    private Integer quantidadeEstoque;

    public LivroBiblioteca() {
    }

    public LivroBiblioteca(Livro livro, Biblioteca biblioteca, Integer quantidadeEstoque) {
        this.livro = livro;
        this.biblioteca = biblioteca;
        this.quantidadeEstoque = quantidadeEstoque;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }

    public Biblioteca getBiblioteca() {
        return biblioteca;
    }

    public void setBiblioteca(Biblioteca biblioteca) {
        this.biblioteca = biblioteca;
    }

    public Integer getQuantidadeEstoque() {
        return quantidadeEstoque;
    }

    public void setQuantidadeEstoque(Integer quantidadeEstoque) {
        this.quantidadeEstoque = quantidadeEstoque;
    }
}
