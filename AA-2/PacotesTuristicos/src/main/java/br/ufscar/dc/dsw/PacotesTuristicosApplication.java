package br.ufscar.dc.dsw;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.ufscar.dc.dsw.dao.IAgenciaDAO;
import br.ufscar.dc.dsw.dao.IPacoteDAO;
import br.ufscar.dc.dsw.dao.IUsuarioDAO;
import br.ufscar.dc.dsw.domain.Agencia;
import br.ufscar.dc.dsw.domain.Cliente;
import br.ufscar.dc.dsw.domain.Pacote;
import br.ufscar.dc.dsw.domain.Usuario;

@SpringBootApplication
public class PacotesTuristicosApplication {

	private static final Logger log = LoggerFactory.getLogger(PacotesTuristicosApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(PacotesTuristicosApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(IUsuarioDAO usuarioDAO, BCryptPasswordEncoder encoder, IPacoteDAO pacoteDAO, IAgenciaDAO agenciaDAO) {
		return (args) -> {

			// Clientes

			try {
				log.info("Salvando Cliente 1");
				Cliente a1 = new Cliente();
				a1.setEmail("alan");
				a1.setSenha(encoder.encode("alan"));
				a1.setFuncao("ROLE_ADMIN");
				a1.setAtivo(true);
				a1.setCpf("111.111.111-11");
				a1.setNome("Alan");
				a1.setSexo("Masculino");
				a1.setDataNasc("28/10/2003");
				a1.setTelefone("9999-9999");
				usuarioDAO.save(a1);
				log.info("Cliente 1 salvo");
			} catch (Exception e) {
				log.info("Falha ao salvar Agencia 1: " + e.getLocalizedMessage());
			}
			// Agências
			try {
				log.info("Salvando Agencia 1");
				Agencia a1 = new Agencia();
				a1.setEmail("EuroAgencia@gmail.com");
				a1.setSenha("EuroAgencia");
				a1.setFuncao("Agencia");
				a1.setAtivo(true);
				a1.setCnpj("12.341.234/0001-88");
				a1.setNome("EuroAgência");
				a1.setDescricao("Agência destinada a venda de pacotes para a Europa.");
				usuarioDAO.save(a1);
				log.info("Agencia 1 salva");
			} catch (Exception e) {
				log.info("Falha ao salvar Agencia 1: " + e.getLocalizedMessage());
			}

			try {
				log.info("Salvando Agencia 2");
				Agencia a2 = new Agencia();
				a2.setEmail("Maritima@gmail.com");
				a2.setSenha("Maritima");
				a2.setFuncao("Agencia");
				a2.setAtivo(true);
				a2.setCnpj("56.534.546/0003-45");
				a2.setNome("Marítima");
				a2.setDescricao("Melhor Agência de Turismo com ênfase em Pacotes Marítmos.");
				usuarioDAO.save(a2);
				log.info("Agencia 2 salva");
			} catch (Exception e) {
				log.info("Falha ao salvar Agencia 2: " + e.getLocalizedMessage());
			}

			try {
				log.info("Salvando Agencia 3");
				Agencia a3 = new Agencia();
				a3.setEmail("Popular@gmail.com");
				a3.setSenha("Popular");
				a3.setFuncao("Agencia");
				a3.setAtivo(true);
				a3.setCnpj("76.234.554/0008-99");
				a3.setNome("Agência Popular");
				a3.setDescricao("Agência com os melhores preços");
				usuarioDAO.save(a3);
				log.info("Agencia 3 salva");
			} catch (Exception e) {
				log.info("Falha ao salvar Agencia 3: " + e.getLocalizedMessage());
			}

			// Pacotes
			try {
				log.info("Salvando Pacote 1");
				Pacote p1 = new Pacote();
				p1.setNome("Viagem para Praia Grande");
				p1.setAgencia(agenciaDAO.findByNome("Agência Popular"));
				p1.setData("2021/11/21");
				p1.setDuracao(10);
				p1.setPreco(BigDecimal.valueOf(1000.00));
				p1.setDescricao("Viagem para a Praia Grande, 10 dias de muito praia");
				p1.setDestinos("Praia Grande - SP");
				p1.setFotos("foto1");
				pacoteDAO.save(p1);
				log.info("Pacote 1 salvo");
			} catch (Exception e) {
				log.info("Falha ao salvar Pacote 1: " + e.getLocalizedMessage());
			}

			try {
				log.info("Salvando Pacote 2");
				Pacote p2 = new Pacote();
				p2.setNome("Paris: a capital francesa");
				p2.setAgencia(agenciaDAO.findByNome("EuroAgência"));
				p2.setData("2022/05/10");
				p2.setDuracao(15);
				p2.setPreco(BigDecimal.valueOf(1000.00));
				p2.setDescricao("15 dias conhecendo Paris e as cidades ao seu redor");
				p2.setDestinos("Paris, Chartres, Crell");
				p2.setFotos("foto de Paris");
				pacoteDAO.save(p2);
				log.info("Pacote 2 salvo");
			} catch (Exception e) {
				log.info("Falha ao salvar Pacote 2: " + e.getLocalizedMessage());
			}
		};
	}
}
