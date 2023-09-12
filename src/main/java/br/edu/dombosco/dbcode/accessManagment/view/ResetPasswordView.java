package br.edu.dombosco.dbcode.accessManagment.view;

import javax.swing.*;
import java.awt.*;

public class ResetPasswordView extends JFrame {

        public ResetPasswordView() {
            setTitle("Redefinição de Senha");
            setSize(400, 200);
            setLocationRelativeTo(null);
            setLayout(new BorderLayout());

            JLabel instructionLabel = new JLabel("Digite seu e-mail para redefinir sua senha:");
            JTextField emailField = new JTextField();
            JButton sendButton = new JButton("Enviar");

            sendButton.addActionListener(e -> {
                String email = emailField.getText();
                // Implemente a lógica para enviar o email aqui
                // ...
                JOptionPane.showMessageDialog(this, "E-mail enviado!");
            });

            add(instructionLabel, BorderLayout.NORTH);
            add(emailField, BorderLayout.CENTER);
            add(sendButton, BorderLayout.SOUTH);

            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        }
    }

