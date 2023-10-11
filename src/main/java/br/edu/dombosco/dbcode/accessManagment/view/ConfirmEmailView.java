package br.edu.dombosco.dbcode.accessManagment.view;

import br.edu.dombosco.dbcode.accessManagment.controller.EmailController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ConfirmEmailView extends JFrame {

    private JTextField textCode;
    private JLabel labelCode;
    private JButton sendCodeButton;
    private JButton verifyCodeButton;
    private final EmailController emailController;

    public ConfirmEmailView(EmailController emailController) {
        this.emailController = emailController;
        setVisible(true);
        setTitle("Password Reset");
        setSize(300, 350);
        setLocationRelativeTo(null);
        setLayout(null);

        labelCode = new JLabel("Código de Verificação:");
        labelCode.setBounds(10,60,180,25);
        add(labelCode);

        textCode = new JTextField();
        textCode.setBounds(60, 80, 100, 60);
        textCode.setColumns(10);
        add(textCode);




        verifyCodeButton = new JButton("Verificar Código");
        verifyCodeButton.setBounds(40,220,50,50);
        add(verifyCodeButton);
        add(new JLabel("Code:"));
        //add(codeField);
        add(verifyCodeButton);

        sendCodeButton.addActionListener(new SendCodeAction());
        verifyCodeButton.addActionListener(new VerifyCodeAction());

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
            //String code = codeField.getText();
            // ... Lógica para verificar o código no backend
        }
    }

}