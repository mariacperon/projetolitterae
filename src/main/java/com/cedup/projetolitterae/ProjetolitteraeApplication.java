package com.cedup.projetolitterae;

import com.cedup.projetolitterae.backend.entities.Biblioteca;
import com.cedup.projetolitterae.backend.entities.Endereco;
import com.cedup.projetolitterae.backend.entities.Livro;
import com.cedup.projetolitterae.backend.entities.LivroBiblioteca;
import com.cedup.projetolitterae.backend.entities.Locacao;
import com.cedup.projetolitterae.backend.entities.Resenha;
import com.cedup.projetolitterae.backend.entities.Usuario;
import com.cedup.projetolitterae.backend.enums.GeneroLivro;
import com.cedup.projetolitterae.backend.enums.StatusLocacao;
import com.cedup.projetolitterae.backend.enums.TipoPerfil;
import com.cedup.projetolitterae.backend.repositories.AutorRepository;
import com.cedup.projetolitterae.backend.repositories.BibliotecaRepository;
import com.cedup.projetolitterae.backend.repositories.EnderecoRepository;
import com.cedup.projetolitterae.backend.repositories.LivroBibliotecaRepository;
import com.cedup.projetolitterae.backend.repositories.LivroRepository;
import com.cedup.projetolitterae.backend.repositories.LocacaoRepository;
import com.cedup.projetolitterae.backend.repositories.ResenhaRepository;
import com.cedup.projetolitterae.backend.repositories.UsuarioRepository;
import com.cedup.projetolitterae.backend.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Date;
import java.util.List;

@SpringBootApplication
public class ProjetolitteraeApplication implements CommandLineRunner {

	@Autowired
	UsuarioService usuarioService;
	@Autowired
	UsuarioRepository usuarioRepository;
	@Autowired
	EnderecoRepository enderecoRepository;
	@Autowired
	BibliotecaRepository bibliotecaRepository;
	@Autowired
	LivroRepository livroRepository;
	@Autowired
	AutorRepository autorRepository;
	@Autowired
	ResenhaRepository resenhaRepository;
	@Autowired
	LivroBibliotecaRepository livroBibliotecaRepository;
	@Autowired
	LocacaoRepository locacaoRepository;

	public static void main(String[] args) {
		SpringApplication.run(ProjetolitteraeApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		initializeDatabase();
	}

	private void initializeDatabase(){
		//CADASTRO DE ENDEREÇOS
		Endereco e1 = new Endereco("123489", "sc", "blumenau", "velha", "rua pipipororo",
				"87", "perto da casa do caralho");

		Endereco e2 = new Endereco("98765432", "sp", "ouro preto", "sul", "rua abububle",
				"87", "perto da casa do caralho");

		Endereco e3 = new Endereco("12345789", "sc", "blumenau", "velha", "rua pipipororo",
				"87", "perto da casa do caralho");

		Endereco e4 = new Endereco("98765432", "ba", "salvador", "nordeeeste", "rua abububle",
				"87", "perto da casa do caralho");

		Endereco e5 = new Endereco("98765432", "parana", "curitiba", "sul", "rua abububle",
				"87", "perto da casa do caralho");

		Endereco e6 = new Endereco("98765432", "tocantins", "palmas", "nordeeeste", "rua abububle",
				"87", "perto da casa do caralho");

		Endereco e7 = new Endereco("98765432", "rio de janeiro", "rio de janeiro", "centroeste", "rua abububle",
				"87", "perto da casa do caralho");

		Endereco e8 = new Endereco("98765432", "bh", "espirito santo", "nordeeeste", "rua abububle",
				"87", "perto da casa do caralho");

		Endereco e9 = new Endereco("98765432", "amazonas", "manaus", "nordeeeste", "rua abububle",
				"87", "perto da casa do caralho");

		Endereco e10 = new Endereco("98765432", "paraiba", "joao pessoa", "nordeeeste", "rua abububle",
				"87", "perto da casa do caralho");

		//CADASTRO DE BIBLIOTECAS
		Biblioteca b1 = new Biblioteca("554", "Cheiro de livro", "asdfad@", "8521",
				"45987412365",  TipoPerfil.ADMIN, "livrosCheiro", "1234", true, 10.00,2.00);
		b1.setEnderecoBiblioteca(e1);

		Biblioteca b2 = new Biblioteca("645657", "Saraiva", "sad@", "321321",
				"852221", TipoPerfil.ADMIN, "livros3", "1234", true, 10.00,2.00);
		b2.setEnderecoBiblioteca(e2);

		Biblioteca b3 = new Biblioteca("789", "Catarinense", "fghdfgh@", "7889",
				"5546", TipoPerfil.ADMIN, "livro5", "1234", true, 10.00,2.00);
		b3.setEnderecoBiblioteca(e3);

		Biblioteca b4 = new Biblioteca("678", "BluLivros", "AaE@", "654",
				"5154", TipoPerfil.ADMIN, "livrso2", "1234", true, 10.00,2.00);
		b4.setEnderecoBiblioteca(e4);

		Biblioteca b5 = new Biblioteca("5645", "BookCenter", "hryty@", " 7858",
				"548454", TipoPerfil.ADMIN, "cheiro", "1234", true, 10.00,2.00);
		b5.setEnderecoBiblioteca(e5);

		//CADASTRO DE LIVROS
		Livro l1 = new Livro("Rainha Vermelha","victoria aveyard", "ansdjbf",
				"portugues", "livre", "seila", "sdfasdf", "1º", new Date(1999-1900,2-1,19));
		l1.setGeneros(List.of(GeneroLivro.FICCAO, GeneroLivro.AVENTURA));

		Livro l2 = new Livro("Espada de Vidro","victoria aveyard","ansdjbf",
				"portugues", "livre", "seila", "sdfasdf", "1º", new Date(1999-1900,2-1,19));
		l2.setGeneros(List.of(GeneroLivro.FICCAO, GeneroLivro.AVENTURA));

		Livro l3 = new Livro("A Sombra do Vento","luis carlos zafon","ansdjbf",
				"portugues", "livre", "seila", "sdfasdf", "1º", new Date(1999-1900,2-1,19));
		l3.setGeneros(List.of(GeneroLivro.DRAMA, GeneroLivro.FICCAO));

		Livro l4 = new Livro("Jogos Vorazes","suzanne collins", "ansdjbf",
				"portugues", "livre", "seila", "sdfasdf", "1º", new Date(1999-1900,2-1,19));
		l4.setGeneros(List.of(GeneroLivro.FICCAO, GeneroLivro.AVENTURA));

		Livro l5 = new Livro("Os Sete Maridos de Evelyn Hugo","tyalor jenkins", "ansdjbf",
				"portugues", "livre", "seila", "sdfasdf", "1º", new Date(1999-1900,2-1,19));
		l5.setGeneros(List.of(GeneroLivro.ROMANCE));

		Livro l6 = new Livro("Daisy Jones & The Six","taylor jenkis", "ansdjbf",
				"portugues", "livre", "seila", "sdfasdf", "1º", new Date(1999-1900,2-1,19));
		l6.setGeneros(List.of(GeneroLivro.DRAMA, GeneroLivro.ROMANCE));

		//CADASTRO DE USUARIOS
		Usuario u1 = new Usuario("12345678945", "Maria Clara", "Peron Gonçalves",
				"1474147", "13231", TipoPerfil.LEITOR, true, new Date(1999-1900,2-1,19));
		u1.setEnderecoUsuario(e6);
		u1.setBiblioteca(b1);

		Usuario u2 = new Usuario("65432198745", "Joca Luis", "Peron Gonçalves",
				"4561234", "dfasd", TipoPerfil.LEITOR,true, new Date(1999-1900,2-1,19));
		u2.setEnderecoUsuario(e7);
		u2.setBiblioteca(b2);

		Usuario u3 = new Usuario("12345678945", "Lucas", "Testoni",
				"4564897", "13231", TipoPerfil.LEITOR, true, new Date(1999-1900,2-1,19));
		u3.setEnderecoUsuario(e8);
		u3.setBiblioteca(b3);

		Usuario u4 = new Usuario("12345678945", "Veri", "Berti",
				"4715457", "13231", TipoPerfil.LEITOR, true, new Date(1999-1900,2-1,19));
		u4.setEnderecoUsuario(e9);
		u4.setBiblioteca(b4);

		//CADASTRO DE LIVROS DA BIBLIOTECA
		LivroBiblioteca lb1 = new LivroBiblioteca(l1, b1, 6);
		LivroBiblioteca lb2 = new LivroBiblioteca(l2, b1, 6);
		LivroBiblioteca lb3 = new LivroBiblioteca(l3, b1, 6);
		LivroBiblioteca lb4 = new LivroBiblioteca(l4, b1, 6);
		LivroBiblioteca lb5 = new LivroBiblioteca(l5, b1, 6);
		LivroBiblioteca lb6 = new LivroBiblioteca(l6, b1, 6);

		LivroBiblioteca lb7 = new LivroBiblioteca(l1, b2, 6);
		LivroBiblioteca lb8 = new LivroBiblioteca(l2, b2, 6);
		LivroBiblioteca lb9 = new LivroBiblioteca(l3, b2, 6);
		LivroBiblioteca lb10 = new LivroBiblioteca(l4, b2, 6);

		LivroBiblioteca lb11 = new LivroBiblioteca(l1, b4, 6);
		LivroBiblioteca lb12 = new LivroBiblioteca(l2, b4, 6);
		LivroBiblioteca lb13 = new LivroBiblioteca(l3, b4, 6);
		LivroBiblioteca lb14 = new LivroBiblioteca(l4, b4, 6);
		LivroBiblioteca lb15 = new LivroBiblioteca(l5, b4, 6);

		//CDASTRO DE LOCACOES
		Locacao loc1 = new Locacao(new Date(2022-1900,2-1,19), new Date(2022-1900,4-1,19),
				StatusLocacao.PENDENTE);
		loc1.setLivro(lb1);
		loc1.setUsuario(u2);

		Locacao loc2 = new Locacao(new Date(2022-1900,2-1,19), new Date(2022-1900,5-1,19),
				StatusLocacao.ANDAMENTO);
		loc2.setLivro(lb3);
		loc2.setUsuario(u1);

		Locacao loc3 = new Locacao(new Date(2023-1900,2-1,19), new Date(2023-1900,2-1,19),
				StatusLocacao.PENDENTE);
		loc3.setLivro(lb5);
		loc3.setUsuario(u3);

		Locacao loc4 = new Locacao(new Date(2023-1900,2-1,19), new Date(2023-1900,2-1,19),
				StatusLocacao.ANDAMENTO);
		loc4.setLivro(lb6);
		loc4.setUsuario(u4);

		//CADASTRO DE RESENHAS
		Resenha r1 = new Resenha("muito bom");
		r1.setLivro(l1);
		r1.setUsuario(u1);

		Resenha r2 = new Resenha("maravilhosoo");
		r2.setLivro(l1);
		r2.setUsuario(u2);

		Resenha r3 = new Resenha("odiei");
		r3.setLivro(l1);
		r3.setUsuario(u4);

		Resenha r4 = new Resenha("ótimo");
		r4.setLivro(l2);
		r4.setUsuario(u2);

		Resenha r5 = new Resenha("mais ou menos");
		r5.setLivro(l4);
		r5.setUsuario(u3);

		//SALVANDO NO BANCO
		enderecoRepository.saveAll(List.of(e1, e2, e3, e4, e5, e6, e7, e8, e9, e10));
		bibliotecaRepository.saveAll(List.of(b1, b2, b3, b4, b5));
		livroRepository.saveAll(List.of(l1, l2, l3, l4, l5, l6));
		usuarioRepository.saveAll(List.of(u1, u2, u3, u4));
		resenhaRepository.saveAll(List.of(r1, r2, r3, r4, r5));
		livroBibliotecaRepository.saveAll(List.of(lb1, lb2, lb3, lb4, lb5, lb6, lb7, lb8, lb9, lb10, lb11, lb12, lb13, lb14, lb15));
		locacaoRepository.saveAll(List.of(loc1, loc2, loc3, loc4));
	}

}
