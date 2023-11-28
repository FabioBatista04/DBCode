package br.edu.dombosco.dbcode;

import br.edu.dombosco.dbcode.accessManagment.controller.EmailController;
import br.edu.dombosco.dbcode.accessManagment.controller.UserController;
import br.edu.dombosco.dbcode.accessManagment.view.LoginView;
import br.edu.dombosco.dbcode.requisitos.controller.ProjetoController;
import br.edu.dombosco.dbcode.requisitos.controller.RequisitosController;
import br.edu.dombosco.dbcode.bugs.controller.BugController;
import br.edu.dombosco.dbcode.test.controller.CasoController;
import br.edu.dombosco.dbcode.test.controller.CasoDetalhadoController;
import br.edu.dombosco.dbcode.test.controller.CenarioController;
import br.edu.dombosco.dbcode.test.controller.PlanoController;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;

import javax.swing.*;

@SpringBootApplication
public class DBCodeApplication {
	public static void main(String[] args) {
		new SpringApplicationBuilder(DBCodeApplication.class).headless(false).run(args);
	}
	@Bean
	CommandLineRunner runner(UserController userController, EmailController emailController, BugController bugController,
							 RequisitosController requisitosController, ProjetoController projetoController,
							 PlanoController planoController, CenarioController cenarioController,
							 CasoController casoController, CasoDetalhadoController casoDetalhadoController
	) {
		return args -> SwingUtilities.invokeLater(() ->
				new LoginView(userController, emailController, requisitosController, bugController, projetoController,
				planoController, cenarioController, casoController, casoDetalhadoController));
	}

}
