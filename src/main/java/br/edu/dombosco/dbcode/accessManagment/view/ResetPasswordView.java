package br.edu.dombosco.dbcode.accessManagment.view;

import br.edu.dombosco.dbcode.accessManagment.controller.EmailController;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ResetPasswordView extends JFrame {
    private JTextField txtEmail;
    private JLabel labelEmail;
    private JTextField codeField;
    private JButton sendCodeButton;
    private JButton verifyCodeButton;
    private final EmailController emailController;

    public ResetPasswordView(EmailController emailController) {
        this.emailController = emailController;
        setVisible(true);
        setTitle("Password Reset");
        setSize(300, 350);
        setLocationRelativeTo(null);
        setLayout(null);

        labelEmail = new JLabel("E-mail:");
        labelEmail.setBounds(10,10,60,25);
        add(labelEmail);

        txtEmail = new JTextField();
        txtEmail.setBounds(60, 10, 200, 25);
        add(txtEmail);



        codeField = new JTextField(20);
//        sendCodeButton = new JButton("Enviar");
//        verifyCodeButton = new JButton("Verificar Código");
//        add(txtEmail);
//        add(sendCodeButton);
//        add(new JLabel("Code:"));
//        add(codeField);
//        add(verifyCodeButton);
//
//        sendCodeButton.addActionListener(new SendCodeAction());
//        verifyCodeButton.addActionListener(new VerifyCodeAction());

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private class SendCodeAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
           // String email = emailField.getText();
           // emailController.sendEmailToResetPassword(email);
        }
    }

    private class VerifyCodeAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
          //  String email = emailField.getText();
            String code = codeField.getText();
            // ... Lógica para verificar o código no backend
        }
    }

}