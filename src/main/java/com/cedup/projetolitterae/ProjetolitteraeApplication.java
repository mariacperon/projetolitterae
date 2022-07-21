package com.cedup.projetolitterae;

import com.cedup.projetolitterae.entities.Usuario;
import com.cedup.projetolitterae.enums.TipoPerfil;
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

	public static void main(String[] args) {
		SpringApplication.run(ProjetolitteraeApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Usuario u1 = new Usuario("12345678945", "maria@","Maria Clara", "Peron Gonçalves",
				"edereco", "skjdfa", "13231", TipoPerfil.LEITOR,
				"mariaclara", "1234");
		Usuario u2 = new Usuario("65432198745", "joca@", "Joca Luis", "Peron Gonçalves",
				"sdfgsd", "fgd", "dfasd", TipoPerfil.LEITOR,
				"jocaluis", "1234");
		Usuario u3 = new Usuario("12345678945", "testoni@", "Lucas", "Testoni",
				"adfasdf", "dfghfgh", "13231", TipoPerfil.ADMIN,
				"lucastestoni", "1234");
		Usuario u4 = new Usuario("12345678945", "veri@", "Veri", "Berti",
				"adfasdf", "dfghfgh", "13231", TipoPerfil.ADMIN,
				"lucastestoni", "1234");
		usuarioRepository.saveAll(List.of(u1, u2, u3));
	}

}
