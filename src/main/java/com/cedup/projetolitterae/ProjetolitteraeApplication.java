package com.cedup.projetolitterae;

import com.cedup.projetolitterae.backend.entities.Autor;
import com.cedup.projetolitterae.backend.entities.Biblioteca;
import com.cedup.projetolitterae.backend.entities.Endereco;
import com.cedup.projetolitterae.backend.entities.Livro;
import com.cedup.projetolitterae.backend.entities.Resenha;
import com.cedup.projetolitterae.backend.entities.Usuario;
import com.cedup.projetolitterae.backend.enums.GeneroLivro;
import com.cedup.projetolitterae.backend.enums.TipoPerfil;
import com.cedup.projetolitterae.backend.repositories.AutorRepository;
import com.cedup.projetolitterae.backend.repositories.BibliotecaRepository;
import com.cedup.projetolitterae.backend.repositories.EnderecoRepository;
import com.cedup.projetolitterae.backend.repositories.LivroRepository;
import com.cedup.projetolitterae.backend.repositories.ResenhaRepository;
import com.cedup.projetolitterae.backend.repositories.UsuarioRepository;
import com.cedup.projetolitterae.backend.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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
				"45987412365",  TipoPerfil.ADMIN, "livrosCheiro", "1234");
		b1.setEnderecoBiblioteca(e1);

		Biblioteca b2 = new Biblioteca("645657", "Saraiva", "sad@", "321321",
				"852221", TipoPerfil.ADMIN, "livros3", "1234");
		b2.setEnderecoBiblioteca(e2);

		Biblioteca b3 = new Biblioteca("789", "Catarinense", "fghdfgh@", "7889",
				"5546", TipoPerfil.ADMIN, "livro5", "1234");
		b3.setEnderecoBiblioteca(e3);

		Biblioteca b4 = new Biblioteca("678", "BluLivros", "AaE@", "654",
				"5154", TipoPerfil.ADMIN, "livrso2", "1234");
		b4.setEnderecoBiblioteca(e4);

		Biblioteca b5 = new Biblioteca("5645", "BookCenter", "hryty@", " 7858",
				"548454", TipoPerfil.ADMIN, "cheiro", "1234");
		b5.setEnderecoBiblioteca(e5);

		//CADASTRO DE AUTORES
		Autor a1 = new Autor("Victoria", "Aveyard", "Estadunidense e milennial, escreveu vários livros de " +
				"sucesso como a saga da Rainha Vermelha e Destruidor de Mundos.");
		Autor a2 = new Autor("Carlos Luis", "Zafon", "Autor espanhol e não sei mais oq.");
		Autor a3 = new Autor("Suzanne", "Collins", "Escritora da saga jogos vorazes.");
		Autor a4 = new Autor("Taylor", "Jenkins Reid", "Autora de sucesos gays.");

		//CADASTRO DE LIVROS
		Livro l1 = new Livro("Rainha Vermelha", "ansdjbf",
				"portugues", 3, "asdfasdfa");
		l1.setBiblioteca(b1);
		l1.setAutor(a1);
		l1.setGeneros(List.of(GeneroLivro.FICCAO, GeneroLivro.AVENTURA));

		Livro l2 = new Livro("Espada de Vidro","ansdjbf",
				"portugues", 2, "asdfasdfa");
		l2.setGeneros(List.of(GeneroLivro.FICCAO, GeneroLivro.AVENTURA));
		l2.setBiblioteca(b1);
		l2.setAutor(a1);

		Livro l3 = new Livro("A Sombra do Vento","ansdjbf",
				"portugues", 4, "asdfasdfa");
		l3.setBiblioteca(b2);
		l3.setAutor(a2);
		l3.setGeneros(List.of(GeneroLivro.DRAMA, GeneroLivro.FICCAO));

		Livro l4 = new Livro("Jogos Vorazes", "ansdjbf",
				"portugues", 5, "asdfasdfa");
		l4.setBiblioteca(b3);
		l4.setAutor(a3);
		l4.setGeneros(List.of(GeneroLivro.FICCAO, GeneroLivro.AVENTURA));

		Livro l5 = new Livro("Os Sete Maridos de Evelyn Hugo", "ansdjbf",
				"portugues", 3, "asdfasdfa");
		l5.setBiblioteca(b4);
		l5.setAutor(a4);
		l5.setGeneros(List.of(GeneroLivro.ROMANCE));

		Livro l6 = new Livro("Daisy Jones & The Six", "ansdjbf",
				"portugues", 1, "asdfasdfa");
		l6.setBiblioteca(b5);
		l6.setAutor(a4);
		l6.setGeneros(List.of(GeneroLivro.DRAMA, GeneroLivro.ROMANCE));

		//CADASTRO DE USUARIOS
		Usuario u1 = new Usuario("12345678945", "Maria Clara", "Peron Gonçalves",
				"skjdfa", "13231", TipoPerfil.LEITOR,
				"mariaclara", "1234");
		u1.setEnderecoUsuario(e6);

		Usuario u2 = new Usuario("65432198745", "Joca Luis", "Peron Gonçalves",
				"fgd", "dfasd", TipoPerfil.LEITOR,
				"jocaluis", "1234");
		u2.setEnderecoUsuario(e7);

		Usuario u3 = new Usuario("12345678945", "Lucas", "Testoni",
				"dfghfgh", "13231", TipoPerfil.LEITOR,
				"lucastestoni", "1234");
		u3.setEnderecoUsuario(e8);

		Usuario u4 = new Usuario("12345678945", "Veri", "Berti",
				"dfghfgh", "13231", TipoPerfil.LEITOR,
				"lucastestoni", "1234");
		u4.setEnderecoUsuario(e9);

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
		autorRepository.saveAll(List.of(a1, a2, a3, a4));
		livroRepository.saveAll(List.of(l1, l2, l3, l4, l5, l6));
		usuarioRepository.saveAll(List.of(u1, u2, u3, u4));
		resenhaRepository.saveAll(List.of(r1, r2, r3, r4, r5));
	}

}
