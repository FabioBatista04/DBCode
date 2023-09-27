package br.edu.dombosco.dbcode.accessManagment.view;

import br.edu.dombosco.dbcode.accessManagment.controller.EmailController;
import br.edu.dombosco.dbcode.accessManagment.controller.UserController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class SendCodeEmailValidateView extends JFrame {
    private JTextField txtEmail;
    private JLabel labelEmail;
    private JButton sendCodeButton;
    private final UserController emailController;

    public SendCodeEmailValidateView(UserController emailController) {
        this.emailController = emailController;
        setVisible(true);
        setTitle("Password Reset");
        setSize(300, 350);
        setLocationRelativeTo(null);
        setLayout(null);

        labelEmail = new JLabel("E-mail:");
        labelEmail.setBounds(10,40,60,25);
        add(labelEmail);

        txtEmail = new JTextField();
        txtEmail.setBounds(60, 40, 200, 25);
        add(txtEmail);




        sendCodeButton = new JButton("Enviar");
        sendCodeButton.setBounds(90,80,80,40);
        add(sendCodeButton);

        sendCodeButton.addActionListener(new SendCodeAction());

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    private class SendCodeAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String email = txtEmail.getText();
            var user = emailController.validateResetCode(email);
            if( user  ){
                setVisible(false);
                new SendCodeEmailValidateView(emailController).setVisible(true);
            }
        }
    }

}