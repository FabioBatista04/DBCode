package br.edu.dombosco.dbcode.accessManagment.view;

import br.edu.dombosco.dbcode.accessManagment.controller.EmailController;
import br.edu.dombosco.dbcode.accessManagment.model.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class SendEmailView extends JFrame {
    private JTextField txtEmail;
    private JLabel labelEmail;
    private JButton sendCodeButton;
    private final EmailController emailController;

    public SendEmailView(EmailController emailController) {
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

        sendCodeButton = new JButton("Enviar");
        sendCodeButton.setBounds(80,60,90,60);
        sendCodeButton.addActionListener(new SendCodeAction());
        add(sendCodeButton);

        sendCodeButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                String email = txtEmail.getText();
                //emailController.sendEmailToResetPassword(email);
                setVisible(false);
                new ConfirmEmailView(emailController).setVisible(true);
            }
        });


        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private class SendCodeAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
           String email = txtEmail.getText();
           emailController.sendEmailToResetPassword(email);
        }
    }

}