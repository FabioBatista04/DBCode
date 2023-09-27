package br.edu.dombosco.dbcode.accessManagment.view;

import br.edu.dombosco.dbcode.accessManagment.controller.EmailController;
import br.edu.dombosco.dbcode.accessManagment.controller.UserController;
import br.edu.dombosco.dbcode.accessManagment.model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class LoginView extends JFrame {
    private JTextField txtUsuario;
    private JPasswordField txtSenha;
    private JLabel lblEsqueciSenha;
    private JLabel lblCadastreSe;
    private JButton btnEntrar;

    private UserController userController;
    private EmailController emailController;


    public LoginView(UserController userController, EmailController emailController) {
        this.userController = userController;
        this.emailController = emailController;

        setTitle("Tela de Login");
        setSize(300, 350);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel lblUsuario = new JLabel("Usuario:");
        lblUsuario.setBounds(10, 10, 60, 25);
        add(lblUsuario);

        txtUsuario = new JTextField();
        txtUsuario.setBounds(100, 10, 150, 25);
        add(txtUsuario);

        JLabel lblSenha = new JLabel("Senha:");
        lblSenha.setBounds(10, 70, 60, 25);
        add(lblSenha);

        txtSenha = new JPasswordField();
        txtSenha.setBounds(100, 70, 150, 25);
        add(txtSenha);


        lblEsqueciSenha = new JLabel("Esqueci minha senha");
        lblEsqueciSenha.setBounds(10, 140, 150, 25);
        lblEsqueciSenha.setForeground(Color.BLUE);
        lblEsqueciSenha.setCursor(new Cursor(Cursor.HAND_CURSOR));
        add(lblEsqueciSenha);

        lblCadastreSe = new JLabel("Cadastre-se");
        lblCadastreSe.setBounds(10, 170, 150, 25);
        lblCadastreSe.setForeground(Color.BLUE);
        lblCadastreSe.setCursor(new Cursor(Cursor.HAND_CURSOR));
        add(lblCadastreSe);



        btnEntrar = new JButton("Entrar");
        btnEntrar.setBounds(100, 110, 150, 25);
        add(btnEntrar);

        btnEntrar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                String usuario = txtUsuario.getText();
                String senha = new String(txtSenha.getPassword());

                var login = userController.login(User.builder().username(usuario).password(senha).build());
                if(login){
                    setVisible(false);
                    new HomeView().setVisible(true);
                }else {
                    JOptionPane.showMessageDialog(LoginView.this, "Confirme seus dados.");

                }
            }
        });

        lblEsqueciSenha.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                setVisible(false);
                new SendCodeEmailValidateView(emailController).setVisible(true);

            }
        });

        lblCadastreSe.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                setVisible(false);
                new RegisterView(userController, emailController).setVisible(true);
            }
        });

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
