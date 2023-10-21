package br.edu.dombosco.dbcode.accessManagment.view;

import br.edu.dombosco.dbcode.accessManagment.controller.EmailController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ValidateEmailCodeView extends JFrame {
    private JLabel labelCode;
    private JTextField textCode;
    private JButton verifyCodeButton;
    private final EmailController emailController;

    public ValidateEmailCodeView(EmailController emailController) {
        this.emailController = emailController;
        setVisible(true);
        setTitle("Valid Code");
        setSize(300, 350);
        setLocationRelativeTo(null);
        setLayout(null);

        labelCode = new JLabel("Código:");
        labelCode.setBounds(10,40,60,25);
        add(labelCode);

        textCode = new JTextField();
        textCode.setBounds(60, 40, 200, 25);
        add(textCode);



//        codeField = new JTextField(20);
//        sendCodeButton = new JButton("Enviar");
//        sendCodeButton.setBounds(90,80,80,40);
        verifyCodeButton = new JButton("Verificar Código");
        verifyCodeButton.setBounds(90,80,80,40);
//        add(txtEmail);
//        add(sendCodeButton);
//        add(new JLabel("Code:"));
//        add(codeField);
        add(verifyCodeButton);
//
//        sendCodeButton.addActionListener(new SendCodeAction());
//        verifyCodeButton.addActionListener(new VerifyCodeAction());

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private class VerifyCodeAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String code = textCode.getText();


        }
    }

}