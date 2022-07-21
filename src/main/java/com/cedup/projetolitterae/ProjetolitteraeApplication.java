package com.cedup.projetolitterae;

import com.cedup.projetolitterae.entities.Endereco;
import com.cedup.projetolitterae.entities.Usuario;
import com.cedup.projetolitterae.enums.TipoPerfil;
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

	public static void main(String[] args) {
		SpringApplication.run(ProjetolitteraeApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Endereco e1 = new Endereco("12345789", "sc", "blumenau", "velha", "rua pipipororo",
				"87", "perto da casa do caralho");
		Endereco e2 = new Endereco("98765432", "sp", "ouro preto", "sul", "rua abububle",
				"87", "perto da casa do caralho");
		Endereco e3 = new Endereco("12345789", "sc", "blumenau", "velha", "rua pipipororo",
				"87", "perto da casa do caralho");
		Endereco e4 = new Endereco("98765432", "pernanbuco", "recife", "nordeeeste", "rua abububle",
				"87", "perto da casa do caralho");


		Usuario u1 = new Usuario("12345678945", "Maria Clara", "Peron Gonçalves",
				"skjdfa", "13231", TipoPerfil.LEITOR,
				"mariaclara", "1234");
		u1.setEndereco(e1);
		Usuario u2 = new Usuario("65432198745", "Joca Luis", "Peron Gonçalves",
				"fgd", "dfasd", TipoPerfil.LEITOR,
				"jocaluis", "1234");
		u2.setEndereco(e2);
		Usuario u3 = new Usuario("12345678945", "Lucas", "Testoni",
				"dfghfgh", "13231", TipoPerfil.ADMIN,
				"lucastestoni", "1234");
		u3.setEndereco(e3);
		Usuario u4 = new Usuario("12345678945", "Veri", "Berti",
				"dfghfgh", "13231", TipoPerfil.ADMIN,
				"lucastestoni", "1234");
		u4.setEndereco(e4);

		enderecoRepository.saveAll(List.of(e1, e2, e3, e4));
		usuarioRepository.saveAll(List.of(u1, u2, u3, u4));

	}

}
