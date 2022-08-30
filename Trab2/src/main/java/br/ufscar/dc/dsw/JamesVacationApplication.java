package br.ufscar.dc.dsw;

import java.math.BigDecimal;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.ufscar.dc.dsw.dao.IAgenciaDAO;
import br.ufscar.dc.dsw.dao.IPacoteDAO;
import br.ufscar.dc.dsw.dao.IUsuarioDAO;
import br.ufscar.dc.dsw.domain.Agencia;
import br.ufscar.dc.dsw.domain.PacoteTuristico;
import br.ufscar.dc.dsw.domain.Usuario;

@SpringBootApplication
public class JamesVacationApplication {

	public static void main(String[] args) {
		SpringApplication.run(JamesVacationApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(IUsuarioDAO usuarioDAO, BCryptPasswordEncoder encoder, IAgenciaDAO agenciaDAO, IPacoteDAO pacoteDAO) {
		return (args) -> {
			
			Usuario u1 = new Usuario();
			u1.setUsername("admin");
			u1.setPassword(encoder.encode("admin"));
			u1.setCPF("012.345.678-90");
			u1.setName("Administrador");
            u1.setDataNascimento("2001");
			u1.setRole("ROLE_ADMIN");
			u1.setEnabled(true);
			usuarioDAO.save(u1);
			
			Usuario u2 = new Usuario();
			u2.setUsername("beltrano");
			u2.setPassword(encoder.encode("123"));
			u2.setCPF("985.849.614-10");
			u2.setName("Beltrano Andrade");
            u2.setDataNascimento("2001");
			u2.setRole("ROLE_USER");
			u2.setEnabled(true);
			usuarioDAO.save(u2);
			
			Usuario u3 = new Usuario();
			u3.setUsername("fulano");
			u3.setPassword(encoder.encode("123"));
			u3.setCPF("367.318.380-04");
			u3.setName("Fulano Silva");
            u3.setDataNascimento("2001");
			u3.setRole("ROLE_USER");
			u3.setEnabled(true);
			usuarioDAO.save(u3);
			
			Agencia e1 = new Agencia();
			e1.setCNPJ("55.789.390/0008-99");
            e1.setEmail("teste1@teste.com");
			e1.setNome("Golaço");
            e1.setPassword(encoder.encode("123"));
			agenciaDAO.save(e1);
            Usuario temp = new Usuario();
            temp.setUsername(e1.getEmail());
            temp.setPassword(e1.getPassword());
            temp.setCPF(e1.getCNPJ());
            temp.setName(e1.getNome());
            temp.setRole("ROLE_AGENCIA");
            usuarioDAO.save(temp);
            
			
			Agencia e2 = new Agencia();
			e2.setCNPJ("71.150.470/0001-40");
            e2.setEmail("teste2@teste.com");
			e2.setNome("LATAMANHO");
            e2.setPassword("123");
			agenciaDAO.save(e2);
			
			Agencia e3 = new Agencia();
			e3.setCNPJ("32.106.536/0001-82");
            e3.setEmail("teste3@teste.com");
			e3.setNome("Passamedo");
            e3.setPassword("123");
			agenciaDAO.save(e3);
			
			PacoteTuristico l1 = new PacoteTuristico();
			l1.setDestino("São paulo");
			l1.setDataPartida("2021-12-10");
			l1.setPreco(BigDecimal.valueOf(540.9));
			l1.setAtivo(true);
			l1.setAgencia(e1);
			pacoteDAO.save(l1);
			
			PacoteTuristico l2 = new PacoteTuristico();
			l2.setDestino("Campinas");
			l2.setDataPartida("2022-10-22");
			l2.setPreco(BigDecimal.valueOf(300.99));
			l2.setAgencia(e2);
			l2.setAtivo(true);
			pacoteDAO.save(l2);
			
			PacoteTuristico l3 = new PacoteTuristico();
			l3.setDestino("Santos");
			l3.setDataPartida("2024-05-15");
			l3.setPreco(BigDecimal.valueOf(999.99));
			l3.setAgencia(e3);
			l3.setAtivo(true);
			pacoteDAO.save(l3);
		};
	}
}
