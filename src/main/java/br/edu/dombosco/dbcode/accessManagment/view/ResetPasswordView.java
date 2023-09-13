package br.edu.dombosco.dbcode.accessManagment.view;

import br.edu.dombosco.dbcode.accessManagment.controller.EmailController;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@Lazy
@Component
@Scope("prototype")
public class ResetPasswordView extends JFrame {
    private JTextField emailField;
    private JTextField codeField;
    private JButton sendCodeButton;
    private JButton verifyCodeButton;
    private final EmailController emailController;

    public ResetPasswordView(EmailController emailController) {
        this.emailController = emailController;

        setTitle("Password Reset");
        setSize(300, 200);
        setLayout(new FlowLayout());

        emailField = new JTextField(20);
        codeField = new JTextField(20);
        sendCodeButton = new JButton("Enviar");
        verifyCodeButton = new JButton("Verificar Código");

        add(new JLabel("Email:"));
        add(emailField);
        add(sendCodeButton);
        add(new JLabel("Code:"));
        add(codeField);
        add(verifyCodeButton);

        sendCodeButton.addActionListener(new SendCodeAction());
        verifyCodeButton.addActionListener(new VerifyCodeAction());

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private class SendCodeAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String email = emailField.getText();
            emailController.sendEmailToResetPassword(email);
        }
    }

    private class VerifyCodeAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String email = emailField.getText();
            String code = codeField.getText();
            // ... Lógica para verificar o código no backend
        }
    }

}