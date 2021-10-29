package br.ufscar.dc.dsw;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import br.ufscar.dc.dsw.dao.IUsuarioDAO;
import br.ufscar.dc.dsw.domain.Agencia;

@SpringBootApplication
public class PacotesTuristicosApplication {

	private static final Logger log = LoggerFactory.getLogger(PacotesTuristicosApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(PacotesTuristicosApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(IUsuarioDAO usuarioDAO) {
		return (args) -> {
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
				log.info("Falha ao salvar Agencia 1");
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
				log.info("Falha ao salvar Agencia 2");
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
				log.info("Falha ao salvar Agencia 3");
			}
		};
	}
}
