package com.cedup.projetolitterae;

import com.cedup.projetolitterae.backend.dto.LivroBibliotecaDto;
import com.cedup.projetolitterae.backend.entities.Biblioteca;
import com.cedup.projetolitterae.backend.entities.Endereco;
import com.cedup.projetolitterae.backend.entities.Livro;
import com.cedup.projetolitterae.backend.entities.LivroBiblioteca;
import com.cedup.projetolitterae.backend.entities.Locacao;
import com.cedup.projetolitterae.backend.entities.Notificacao;
import com.cedup.projetolitterae.backend.entities.Resenha;
import com.cedup.projetolitterae.backend.entities.UltimoId;
import com.cedup.projetolitterae.backend.entities.Usuario;
import com.cedup.projetolitterae.backend.enums.GeneroLivro;
import com.cedup.projetolitterae.backend.enums.StatusLocacao;
import com.cedup.projetolitterae.backend.enums.TipoPerfil;
import com.cedup.projetolitterae.backend.repositories.BibliotecaRepository;
import com.cedup.projetolitterae.backend.repositories.EnderecoRepository;
import com.cedup.projetolitterae.backend.repositories.LivroBibliotecaRepository;
import com.cedup.projetolitterae.backend.repositories.LivroRepository;
import com.cedup.projetolitterae.backend.repositories.LocacaoRepository;
import com.cedup.projetolitterae.backend.repositories.NotificacaoRepository;
import com.cedup.projetolitterae.backend.repositories.ResenhaRepository;
import com.cedup.projetolitterae.backend.repositories.UltimoIdRepository;
import com.cedup.projetolitterae.backend.repositories.UsuarioRepository;
import com.cedup.projetolitterae.backend.services.LivroBibliotecaService;
import com.cedup.projetolitterae.backend.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.util.CollectionUtils;

import java.io.File;
import java.sql.Date;
import java.util.Collections;
import java.util.List;
import java.util.Random;

@SpringBootApplication
public class ProjetolitteraeApplication implements CommandLineRunner {

	@Autowired
	UsuarioRepository usuarioRepository;
	@Autowired
	EnderecoRepository enderecoRepository;
	@Autowired
	BibliotecaRepository bibliotecaRepository;
	@Autowired
	LivroRepository livroRepository;
	@Autowired
	ResenhaRepository resenhaRepository;
	@Autowired
	LivroBibliotecaRepository livroBibliotecaRepository;
	@Autowired
	LocacaoRepository locacaoRepository;
	@Autowired
	UltimoIdRepository ultimoIdRepository;
	@Autowired
	NotificacaoRepository notificacaoRepository;

	@Bean
	public String getDirBaseArquivo(@Value( "${spring.web.resources.static-locations:/litterae/files}" )
										List<String> diratorioStatic){
		if(CollectionUtils.isEmpty(diratorioStatic)){
			throw new RuntimeException("Pasta de arquivos não definida");
		}
		File diratorioPadraoArquivos = new File(diratorioStatic.get(0).replace("file:",""));
		if(!diratorioPadraoArquivos.exists()){
			diratorioPadraoArquivos.mkdirs();
		}
		return diratorioPadraoArquivos.getAbsolutePath();
	}

	public static void main(String[] args) {
		SpringApplication.run(ProjetolitteraeApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		initializeDatabase();
	}

	private void initializeDatabase(){
		/*
		//CADASTRO DE ENDEREÇOS
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
		Biblioteca b2 = new Biblioteca("645657", "Saraiva", "sad@",
				"852221", TipoPerfil.ADMIN, "1234", true, 10.00,2.00);
		b2.setEnderecoBiblioteca(e2);
		b2.setId(100002L);

		Biblioteca b3 = new Biblioteca("789", "Catarinense", "fghdfgh@",
				"5546", TipoPerfil.ADMIN, "1234", true, 10.00,2.00);
		b3.setEnderecoBiblioteca(e3);
		b3.setId(100003L);

		Biblioteca b4 = new Biblioteca("678", "BluLivros", "AaE@",
				"5154", TipoPerfil.ADMIN, "1234", true, 10.00,2.00);
		b4.setEnderecoBiblioteca(e4);
		b4.setId(100004L);

		Biblioteca b5 = new Biblioteca("5645", "BookCenter", "hryty@",
				"548454", TipoPerfil.ADMIN, "1234", true, 10.00,2.00);
		b5.setEnderecoBiblioteca(e5);
		b5.setId(100005L);

		Biblioteca litterae = new Biblioteca("", "Litteae", "",
				"", TipoPerfil.MASTER, "1234", true, null,null);
		litterae.setEnderecoBiblioteca(null);
		litterae.setId(1L);

		//CADASTRO DE LIVROS
		Livro l1 = new Livro("Rainha Vermelha","victoria aveyard", "ansdjbf",
				"portugues", "livre", "seila", "sdfasdf", "1º", new Date(1999-1900,2-1,19));
		l1.setGeneros(List.of(GeneroLivro.FICCAO_CIENTIFICA, GeneroLivro.ACAO_E_AVENTURA));

		Livro l2 = new Livro("Espada de Vidro","victoria aveyard","ansdjbf",
				"portugues", "livre", "seila", "sdfasdf", "1º", new Date(1999-1900,2-1,19));
		l2.setGeneros(List.of(GeneroLivro.FICCAO_CIENTIFICA, GeneroLivro.ACAO_E_AVENTURA));

		Livro l3 = new Livro("A Sombra do Vento","luis carlos zafon","ansdjbf",
				"portugues", "livre", "seila", "sdfasdf", "1º", new Date(1999-1900,2-1,19));
		l3.setGeneros(List.of(GeneroLivro.ROMANCE, GeneroLivro.FICCAO_CIENTIFICA));

		Livro l4 = new Livro("Jogos Vorazes","suzanne collins", "ansdjbf",
				"portugues", "livre", "seila", "sdfasdf", "1º", new Date(1999-1900,2-1,19));
		l4.setGeneros(List.of(GeneroLivro.FICCAO_CIENTIFICA, GeneroLivro.ACAO_E_AVENTURA));

		Livro l5 = new Livro("Os Sete Maridos de Evelyn Hugo","tyalor jenkins", "ansdjbf",
				"portugues", "livre", "seila", "sdfasdf", "1º", new Date(1999-1900,2-1,19));
		l5.setGeneros(List.of(GeneroLivro.ROMANCE));

		Livro l6 = new Livro("Daisy Jones & The Six","taylor jenkis", "ansdjbf",
				"portugues", "livre", "seila", "sdfasdf", "1º", new Date(1999-1900,2-1,19));
		l6.setGeneros(List.of(GeneroLivro.ROMANCE, GeneroLivro.ROMANCE));

		//CADASTRO DE USUARIOS
		Usuario u1 = new Usuario("12345678945", "Maria Clara", "Peron Gonçalves", "mariaclaraperon@gmail.com",
				"1474147", "13231", TipoPerfil.LEITOR, true, new Date(1999-1900,2-1,19));
		u1.setEnderecoUsuario(e6);
		u1.setBiblioteca(b1);
		u1.setId(100001L);

		Usuario u2 = new Usuario("65432198745", "Joca Luis", "Peron Gonçalves", "teste",
				"4561234", "dfasd", TipoPerfil.LEITOR,true, new Date(1999-1900,2-1,19));
		u2.setEnderecoUsuario(e7);
		u2.setBiblioteca(b2);
		u2.setId(100002L);

		Usuario u3 = new Usuario("12345678945", "Lucas", "Testoni", "teste",
				"4564897", "13231", TipoPerfil.LEITOR, true, new Date(1999-1900,2-1,19));
		u3.setEnderecoUsuario(e8);
		u3.setBiblioteca(b3);
		u3.setId(100003L);

		Usuario u4 = new Usuario("12345678945", "Veri", "Berti", "teste",
				"4715457", "13231", TipoPerfil.LEITOR, true, new Date(1999-1900,2-1,19));
		u4.setEnderecoUsuario(e9);
		u4.setBiblioteca(b4);
		u4.setId(100004L);

		//CADASTRO DE LIVROS DA BIBLIOTECA

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
		Locacao loc1 = new Locacao(new Date(2022-1900,2-1,19), new Date(2022-1900,8-1,19),
				StatusLocacao.ANDAMENTO);
		loc1.setLivro(lb1);
		loc1.setUsuario(u2);

		Locacao loc2 = new Locacao(new Date(2022-1900,2-1,19), new Date(2022-1900,11-1,19),
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

		Locacao loc5 = new Locacao(new Date(2023-1900,2-1,19), new Date(2023-1900,2-1,19),
				StatusLocacao.ANDAMENTO);
		loc5.setLivro(lb3);
		loc5.setUsuario(u1);

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
		bibliotecaRepository.saveAll(List.of(b1, b2, b3, b4, b5, litterae));
		livroRepository.saveAll(List.of(l1, l2, l3, l4, l5, l6));
		usuarioRepository.saveAll(List.of(u1, u2, u3, u4));
		resenhaRepository.saveAll(List.of(r1, r2, r3, r4, r5));
		livroBibliotecaRepository.saveAll(List.of(lb1, lb2, lb3, lb4, lb5, lb6, lb7, lb8, lb9, lb10, lb11, lb12, lb13, lb14, lb15));
		locacaoRepository.saveAll(List.of(loc1, loc2, loc3, loc4, loc5));
		 */
		gerarBase1();
		UltimoId ultimoIdBiblioteca = new UltimoId(1, "biblioteca", 100001L);
		UltimoId ultimoIdUsuario = new UltimoId(2, "usuario", 100004L);
		ultimoIdRepository.saveAll(List.of(ultimoIdBiblioteca, ultimoIdUsuario));
	}

	private void gerarBase1(){
		//ENDERECO BIBLIOTECA
		Endereco e1 = new Endereco("123489", "SC", "Blumenau", "Velha Central", "Rua Rio Branco",
				"87", "Ao lado do posto.");

		//CADASTRO DE BIBLIOTECAS
		Biblioteca b1 = new Biblioteca("12365478965412", "Cheiro de livro", "cheirodelivro@email.com",
				"45987412365",  TipoPerfil.ADMIN, "1234", true, 10.00,2.00);
		b1.setEnderecoBiblioteca(e1);
		b1.setId(100001L);

		//CADASTRO DE LIVROS
		Livro l1 = new Livro("Rainha Vermelha","Victoria aveyard", "Mare e sua família são vermelhos: " +
				"plebeus, humildes, destinados a servir uma elite prateada cujos poderes sobrenaturais os tornam quase deuses. " +
				"Mare rouba o que pode para ajudar sua família a sobreviver e não tem esperanças de escapar do vilarejo " +
				"miserável onde mora.",
				"PT", "1", "Seguinte", "8565765695", "Padrão", new Date(2015-1900,6-1,9));
		l1.setGeneros(List.of(GeneroLivro.FICCAO_CIENTIFICA, GeneroLivro.ACAO_E_AVENTURA));
		l1.setImagem("/livro/1/a3a3b0c312ea2497220fbc12f53b945b.jpg");

		Livro l2 = new Livro("Espada de Vidro","Victoria aveyard","O sangue de Mare Barrow é vermelho, " +
				"da mesma cor da população comum, mas sua habilidade de controlar a eletricidade a torna tão poderosa quanto " +
				"os membros da elite de sangue prateado. Depois que essa revelação foi feita em rede nacional, Mare se " +
				"transformou numa arma perigosa que a corte real quer esconder e controlar.",
				"PT", "1", "Seguinte", "8565765946", "Padrão", new Date(2016-1900,2-1,4));
		l2.setGeneros(List.of(GeneroLivro.FICCAO_CIENTIFICA, GeneroLivro.ACAO_E_AVENTURA));
		l2.setImagem("/livro/2/41PHjMF81IL.jpg");

		Livro l3 = new Livro("A Sombra do Vento","Luís Carlos Zafón","Junto com seu amigo Fermín, Daniel " +
				"percorre a cidade, adentrando as ruelas e os segredos mais obscuros de Barcelona. Anos se passam e sua " +
				"investigação inocente se transforma em uma trama de mistério, magia, loucura e assassinato. E o destino " +
				"de seu autor favorito de repente parece intimamente conectado ao dele.",
				"PT", "1", "Suma", "9788556510341", "Padrão", new Date(2017-1900,5-1,26));
		l3.setGeneros(List.of(GeneroLivro.ROMANCE, GeneroLivro.FICCAO_CIENTIFICA));
		l3.setImagem("/livro/3/91xOzA3VHtL.jpg");

		Livro l4 = new Livro("Jogos Vorazes","Suzanne Collins", "Quando Katniss Everdeen, de 16 anos, " +
				"decide participar dos Jogos Vorazes para poupar a irmã mais nova, causando grande comoção no país, ela " +
				"sabe que essa pode ser a sua sentença de morte. Mas a jovem usa toda a sua habilidade de caça e sobrevivência" +
				" ao ar livre para se manter viva.",
				"PT", "14", "Rocco", "978-65-5532-073-2", "Padrão", new Date(2008-1900,2-1,19));
		l4.setGeneros(List.of(GeneroLivro.FICCAO_CIENTIFICA, GeneroLivro.ACAO_E_AVENTURA));
		l4.setImagem("/livro/4/jogosvorazes.jpg");

		Livro l5 = new Livro("Os Sete Maridos de Evelyn Hugo","Taylor Jenkins", "Lendária estrela de Hollywood, " +
				"Evelyn Hugo sempre esteve sob os holofotes ― seja estrelando uma produção vencedora do Oscar, protagonizando " +
				"algum escândalo ou aparecendo com um novo marido… pela sétima vez. Agora, prestes a completar oitenta anos " +
				"e reclusa em seu apartamento no Upper East Side, a famigerada atriz decide contar a própria história ― " +
				"ou sua “verdadeira história” ―, mas com uma condição: que Monique Grant, jornalista iniciante e até então " +
				"desconhecida, seja a entrevistadora. Ao embarcar nessa misteriosa empreitada, a jovem repórter começa a " +
				"se dar conta de que nada é por acaso ― e que suas trajetórias podem estar profunda e irreversivelmente conectadas.",
				"PT", "14", "Paralela", "8584391509", "Padrão", new Date(2019-1900,10-1,21));
		l5.setGeneros(List.of(GeneroLivro.ROMANCE));
		l5.setImagem("/livro/5/7maridosdeevelynhugo.jpg");

		Livro l6 = new Livro("Daisy Jones & The Six","Taylor Jenkins Reid", "Todo mundo conhece Daisy Jones " +
				"& The Six. Nos anos setenta, dominavam as paradas de sucesso, faziam shows para plateias lotadas e " +
				"conquistavam milhões de fãs. Eram a voz de uma geração, e Daisy, a inspiração de toda garota descolada. " +
				"Mas no dia 12 de julho de 1979, no último show da turnê Aurora, eles se separaram. E ninguém nunca soube " +
				"por quê. Até agora. Esta é história de uma menina de Los Angeles que sonhava em ser uma estrela do rock " +
				"e de uma banda que também almejava seu lugar ao sol. E de tudo o que aconteceu ― o sexo, as drogas, os " +
				"conflitos e os dramas ― quando um produtor apostou (certo!) que juntos poderiam se tornar lendas da música.",
				"PT", "14", "Paralela", "8584391401", "Padrão", new Date(2019-1900,6-1,10));
		l6.setGeneros(List.of(GeneroLivro.ROMANCE, GeneroLivro.ROMANCE));
		l6.setImagem("/livro/6/91zeDTttzKL.jpg");

		//ENDERECOS USUARIOS
		Endereco e2 = new Endereco("98765432", "SC", "Blumenau", "Itoupavazinha", "Rua Araxá",
				"542", "Última casa da rua.");

		Endereco e3 = new Endereco("12345789", "SC", "Blumenau", "Jardim Blumenau", "Rua Arboria",
				"14", "Ao lado da farmácia");

		Endereco e4 = new Endereco("98765432", "SC", "Blumenau", "Velha Central", "Rua Caçadores",
				"87", "Apto 201");

		Endereco e5 = new Endereco("98765432", "SC", "Blumenau", "Garcia", "Rua Amazonas",
				"412", "Em frente ao batalhão.");

		//CADASTRO DE USUARIOS
		Usuario u1 = new Usuario("12345678945", "Maria Clara", "Peron", "mariaclara@email.com",
				"47521452145", null, TipoPerfil.LEITOR, true, new Date(2005-1900,2-1,19));
		u1.setEnderecoUsuario(e2);
		u1.setBiblioteca(b1);
		u1.setImagem("/usuario/100001/images(1).jpeg");
		u1.setId(100001L);

		Usuario u2 = new Usuario("65432198745", "Veridiana", "Berti", "veriberti@email.com",
				"47569852145", null, TipoPerfil.LEITOR,true, new Date(2005-1900,5-1,2));
		u2.setEnderecoUsuario(e3);
		u2.setBiblioteca(b1);
		u2.setImagem("/usuario/100002/images.png");
		u2.setId(100002L);

		Usuario u3 = new Usuario("12345678945", "Lucas", "Testoni", "testoni@email.com",
				"47854124785", "4763215896", TipoPerfil.LEITOR, true, new Date(2003-1900, 0,1));
		u3.setEnderecoUsuario(e4);
		u3.setBiblioteca(b1);
		u3.setImagem("/usuario/100003/download.jpg");
		u3.setId(100003L);

		Usuario u4 = new Usuario("12345678945", "Jeniffer", "Cristina", "jeniffer@email.com",
				"47552362145", "47258712587", TipoPerfil.LEITOR, true, new Date(2004-1900,8-1,15));
		u4.setEnderecoUsuario(e5);
		u4.setBiblioteca(b1);
		u4.setImagem("/usuario/100004/images(1).jpeg");
		u4.setId(100004L);

		//CADASTRO DE LIVROS DA BIBLIOTECA
		LivroBiblioteca lb1 = new LivroBiblioteca(l1, b1, 5);
		LivroBiblioteca lb2 = new LivroBiblioteca(l2, b1, 5);
		LivroBiblioteca lb3 = new LivroBiblioteca(l3, b1, 5);
		LivroBiblioteca lb4 = new LivroBiblioteca(l4, b1, 5);
		LivroBiblioteca lb5 = new LivroBiblioteca(l5, b1, 5);
		LivroBiblioteca lb6 = new LivroBiblioteca(l6, b1, 5);

		//CDASTRO DE LOCACOES
		Locacao loc1 = new Locacao(new Date(2022-1900,5-1,20), new Date(2022-1900,8-1,19),
				StatusLocacao.DEVOLVIDO);
		loc1.setLivro(lb1);
		loc1.setDataDevolvida(new Date(2022-1900,8-1,19));
		loc1.setUsuario(u2);

		Locacao loc2 = new Locacao(new Date(2022-1900,10-1,29), new Date(2022-1900,11-1,29),
				StatusLocacao.ANDAMENTO);
		loc2.setLivro(lb3);
		loc2.setUsuario(u1);

		Locacao loc3 = new Locacao(new Date(2022-1900,10-1,30), new Date(2022-1900,11-1,30),
				StatusLocacao.ANDAMENTO);
		loc3.setLivro(lb5);
		loc3.setUsuario(u2);

		Locacao loc4 = new Locacao(new Date(2022-1900,9-1,14), new Date(2022-1900,10-1,14),
				StatusLocacao.PENDENTE);
		loc4.setLivro(lb6);
		loc4.setUsuario(u3);

		Locacao loc5 = new Locacao(new Date(2022-1900,10-1,20), new Date(2022-1900,11-1,20),
				StatusLocacao.ANDAMENTO);
		loc5.setLivro(lb3);
		loc5.setUsuario(u4);

		Locacao loc6 = new Locacao(new Date(2022-1900,7-1,20), new Date(2022-1900,8-1,20),
				StatusLocacao.DEVOLVIDO);
		loc6.setLivro(lb3);
		loc6.setDataDevolvida(new Date(2022-1900,8-1,19));
		loc6.setUsuario(u4);

		//CADASTRO DE RESENHAS
		Resenha r1 = new Resenha("Muito bom");
		r1.setLivro(l1);
		r1.setUsuario(u1);

		Resenha r2 = new Resenha("Maravilhosoo");
		r2.setLivro(l1);
		r2.setUsuario(u2);

		Resenha r3 = new Resenha("Odiei");
		r3.setLivro(l1);
		r3.setUsuario(u4);

		Resenha r4 = new Resenha("Ótimo");
		r4.setLivro(l2);
		r4.setUsuario(u2);

		Resenha r5 = new Resenha("Não curti");
		r5.setLivro(l2);
		r5.setUsuario(u1);

		Resenha r6 = new Resenha("Gostei da escrita");
		r6.setLivro(l2);
		r6.setUsuario(u3);

		Resenha r7 = new Resenha("Achei mais ou menos");
		r7.setLivro(l3);
		r7.setUsuario(u3);

		Resenha r8 = new Resenha("Muuuito bom");
		r8.setLivro(l3);
		r8.setUsuario(u2);

		Resenha r9 = new Resenha("Perfeito!");
		r9.setLivro(l3);
		r9.setUsuario(u1);

		Resenha r10 = new Resenha("Sem opiniões");
		r10.setLivro(l4);
		r10.setUsuario(u4);

		Resenha r11 = new Resenha("Chorei muito");
		r11.setLivro(l5);
		r11.setUsuario(u2);

		Resenha r12 = new Resenha("Chorei muito");
		r12.setLivro(l5);
		r12.setUsuario(u1);

		Resenha r13 = new Resenha("Esperava mais");
		r13.setLivro(l6);
		r13.setUsuario(u3);

		Notificacao n1 = new Notificacao(null, b1, "Livro devolvido",
				"Livro "+ loc1.getLivro().getLivro().getNome() +" locacado pelo usuário "+
				loc1.getUsuario().getNome() +" "+ loc1.getUsuario().getSobrenome() +" devolvido.");

		Notificacao n2 = new Notificacao(null, b1, "Livro devolvido",
				"Livro "+ loc6.getLivro().getLivro().getNome() +" locacado pelo usuário "+
						loc6.getUsuario().getNome() +" "+ loc6.getUsuario().getSobrenome() +" devolvido.");

		Notificacao n3 = new Notificacao(null, b1, "Pendência",
				"Usuário "+ loc4.getUsuario().getId() +" - "+ loc4.getUsuario().getNome() +" "+
						loc4.getUsuario().getSobrenome()+ " possui uma pêndencia na locação "+ loc4.getId());

		Notificacao n4 = new Notificacao(null, b1, "Livro locado",
				"Usuário "+ loc5.getUsuario().getId() +" - "+ loc5.getUsuario().getNome() +" "+
						loc5.getUsuario().getSobrenome()+ " locou o livro "+ loc5.getLivro().getLivro().getNome()
						+" no dia "+ loc5.getDataLocacao());

		Notificacao n5 = new Notificacao(null, b1, "Livro locado",
				"Usuário "+ loc2.getUsuario().getId() +" - "+ loc2.getUsuario().getNome() +" "+
						loc2.getUsuario().getSobrenome()+ " locou o livro "+ loc2.getLivro().getLivro().getNome()
						+" no dia "+ loc2.getDataLocacao());

		Notificacao n6 = new Notificacao(null, b1, "Livro locado",
				"Usuário "+ loc3.getUsuario().getId() +" - "+ loc3.getUsuario().getNome() +" "+
						loc3.getUsuario().getSobrenome()+ " locou o livro "+ loc3.getLivro().getLivro().getNome()
						+" no dia "+ loc3.getDataLocacao());

		//SALVANDO NO BANCO
		enderecoRepository.saveAll(List.of(e1, e2, e3, e4, e5));
		bibliotecaRepository.save(b1);
		livroRepository.saveAll(List.of(l1, l2, l3, l4, l5, l6));
		usuarioRepository.saveAll(List.of(u1, u2, u3, u4));
		resenhaRepository.saveAll(List.of(r1, r2, r3, r4, r5, r6, r7 ,r8, r9, r10, r11, r12, r13));
		livroBibliotecaRepository.saveAll(List.of(lb1, lb2, lb3, lb4, lb5, lb6));
		locacaoRepository.saveAll(List.of(loc1, loc2, loc3, loc4, loc5, loc6));
		notificacaoRepository.saveAll(List.of(n1, n2, n3, n4, n5, n6));
	}

}
