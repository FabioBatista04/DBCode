package br.edu.dombosco.dbcode.accessManagment.view;

import br.edu.dombosco.dbcode.accessManagment.controller.EmailController;
import br.edu.dombosco.dbcode.accessManagment.controller.UserController;
import br.edu.dombosco.dbcode.accessManagment.model.User;
import lombok.extern.slf4j.Slf4j;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
@Slf4j
public class ConfirmEmailView extends JFrame {

    private JTextField textCode;
    private JLabel labelCode;
    private JButton sendCodeButton;
    private JButton verifyCodeButton;
    private final EmailController emailController;
    private final UserController userController;
    private final User user;

    public ConfirmEmailView(UserController userController,EmailController emailController, User user) {
        log.info("contrutor :: {}", user);
        this.emailController = emailController;
        this.userController = userController;
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
                String code = textCode.getText();
                if (code == null || code.isEmpty()) {
                    JOptionPane.showMessageDialog(ConfirmEmailView.this, "Código vazio", "Valide Campos", JOptionPane.WARNING_MESSAGE);
                } else {
                    var user = userController.findByEmail(ConfirmEmailView.this.user.getEmail());
                    log.info("User:: {}", user);
                    if (user == null) {
                        JOptionPane.showMessageDialog(ConfirmEmailView.this, "Email não encontrado", "Valide Campos", JOptionPane.WARNING_MESSAGE);
                    } else if (!code.equals(user.getResetCode())) {
                        JOptionPane.showMessageDialog(ConfirmEmailView.this, "Código não é válido", "Valide Campos", JOptionPane.WARNING_MESSAGE);
                    } else {
                        setVisible(false);
                        new NewPasswordView(userController, emailController, user).setVisible(true);
                    }
                }
            }
        });

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}