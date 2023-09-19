package br.edu.dombosco.dbcode;

import br.edu.dombosco.dbcode.accessManagment.controller.EmailController;
import br.edu.dombosco.dbcode.accessManagment.controller.UserController;
import br.edu.dombosco.dbcode.accessManagment.view.LoginView;
import br.edu.dombosco.dbcode.accessManagment.view.RegisterView;
import br.edu.dombosco.dbcode.accessManagment.view.ResetPasswordView;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
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
	CommandLineRunner runner(UserController userController, EmailController emailController) {
		return args -> SwingUtilities.invokeLater(() -> new ResetPasswordView(emailController ));
	}

}
