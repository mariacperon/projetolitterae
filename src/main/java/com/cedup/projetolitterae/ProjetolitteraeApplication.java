package com.cedup.projetolitterae;

import com.cedup.projetolitterae.entities.Biblioteca;
import com.cedup.projetolitterae.entities.Endereco;
import com.cedup.projetolitterae.entities.Usuario;
import com.cedup.projetolitterae.enums.TipoPerfil;
import com.cedup.projetolitterae.repositories.BibliotecaRepository;
import com.cedup.projetolitterae.repositories.EnderecoRepository;
import com.cedup.projetolitterae.repositories.UsuarioRepository;
import com.cedup.projetolitterae.services.UsuarioService;
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

	public static void main(String[] args) {
		SpringApplication.run(ProjetolitteraeApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

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

		Biblioteca b1 = new Biblioteca("554", "Cheiro de livro", "asdfad@", "8521",
				"45987412365", "ghdfg", TipoPerfil.ADMIN, "livrosCheiro", "1234");
		b1.setEnderecoBiblioteca(e1);
		Biblioteca b2 = new Biblioteca("645657", "Saraiva", "sad@", "321321",
				"852221", "jhbjh", TipoPerfil.ADMIN, "livros3", "1234");
		b2.setEnderecoBiblioteca(e2);
		Biblioteca b3 = new Biblioteca("789", "Catarinense", "fghdfgh@", "7889",
				"5546", "sfg", TipoPerfil.ADMIN, "livro5", "1234");
		b3.setEnderecoBiblioteca(e3);
		Biblioteca b4 = new Biblioteca("678", "BluLivros", "AaE@", "654",
				"5154", "jhsdgsdfbjh", TipoPerfil.ADMIN, "livrso2", "1234");
		b4.setEnderecoBiblioteca(e4);
		Biblioteca b5 = new Biblioteca("5645", "BookCenter", "hryty@", " 7858",
				"548454", "sdf", TipoPerfil.ADMIN, "cheiro", "1234");
		b5.setEnderecoBiblioteca(e5);

		Usuario u1 = new Usuario("12345678945", "Maria Clara", "Peron Gonçalves",
				"skjdfa", "13231", TipoPerfil.LEITOR,
				"mariaclara", "1234", b1);
		u1.setEnderecoUsuario(e6);
		Usuario u2 = new Usuario("65432198745", "Joca Luis", "Peron Gonçalves",
				"fgd", "dfasd", TipoPerfil.LEITOR,
				"jocaluis", "1234", b2);
		u2.setEnderecoUsuario(e7);
		Usuario u3 = new Usuario("12345678945", "Lucas", "Testoni",
				"dfghfgh", "13231", TipoPerfil.LEITOR,
				"lucastestoni", "1234", b1);
		u3.setEnderecoUsuario(e8);
		Usuario u4 = new Usuario("12345678945", "Veri", "Berti",
				"dfghfgh", "13231", TipoPerfil.LEITOR,
				"lucastestoni", "1234", b3);
		u4.setEnderecoUsuario(e9);

		enderecoRepository.saveAll(List.of(e1, e2, e3, e4, e5, e6, e7, e8, e9, e10));
		bibliotecaRepository.saveAll(List.of(b1, b2, b3, b4, b5));
		usuarioRepository.saveAll(List.of(u1, u2, u3, u4));

	}

}
