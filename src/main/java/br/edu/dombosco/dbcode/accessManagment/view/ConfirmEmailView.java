package br.edu.dombosco.dbcode.accessManagment.view;

import br.edu.dombosco.dbcode.accessManagment.controller.EmailController;
import br.edu.dombosco.dbcode.accessManagment.model.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class ConfirmEmailView extends JFrame {

    private JTextField textCode;
    private JLabel labelCode;
    private JButton sendCodeButton;
    private JButton verifyCodeButton;
    private final EmailController emailController;
    private final User user;

    public ConfirmEmailView(EmailController emailController, User user) {
        this.emailController = emailController;
        this.user = user;
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
        verifyCodeButton.setBounds(40,220,200,50);
        add(verifyCodeButton);
        add(new JLabel("Code:"));
        //add(codeField);
        add(verifyCodeButton);

        verifyCodeButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                String email = textCode.getText();
                if(email == null || email.isEmpty()){
                    JOptionPane.showMessageDialog(ConfirmEmailView.this, "Email vazio","Valide Campos",JOptionPane.WARNING_MESSAGE);
                }else {
                    var user = emailController.sendEmailToResetPassword(email);
                    if (user == null){
                        JOptionPane.showMessageDialog(ConfirmEmailView.this, "Email não encontrado","Valide Campos",JOptionPane.WARNING_MESSAGE);
                    } else {
                        setVisible(false);
                        //new NewSenha(emailController, user).setVisible(true);
                    }

                }


            }
        });


        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}