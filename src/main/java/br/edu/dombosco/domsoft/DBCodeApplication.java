package br.edu.dombosco.domsoft;

import br.edu.dombosco.domsoft.accessManagment.view.LoginView;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.swing.*;

@SpringBootApplication
public class DBCodeApplication {

	public static void main(String[] args) {
		System.setProperty("java.awt.headless", "false");
		SwingUtilities.invokeLater(() -> {
			var context = SpringApplication.run(DBCodeApplication.class, args);
			LoginView loginView = context.getBean(LoginView.class);
			loginView.setVisible(true);
		});

	}

}
