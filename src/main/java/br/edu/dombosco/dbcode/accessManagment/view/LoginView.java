package br.edu.dombosco.dbcode.accessManagment.view;

import br.edu.dombosco.dbcode.accessManagment.adapter.GenericFocusAdapter;
import br.edu.dombosco.dbcode.accessManagment.controller.EmailController;
import br.edu.dombosco.dbcode.accessManagment.controller.UserController;
import br.edu.dombosco.dbcode.accessManagment.model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;


public class LoginView extends JFrame {

    private JPanel panel;

    private JTextField username;
    private JPasswordField password;
    private JLabel forgetPassword;
    private JLabel singUp;
    private JButton login;

    private Color color;

    private GenericFocusAdapter focus;

    private ImageIcon imageIcon;

    private JLabel image;

    private UserController userController;
    private EmailController emailController;


    public LoginView(UserController userController, EmailController emailController) {
        this.userController = userController;
        this.emailController = emailController;

        initComponents();
        setLayoutLogin();
        setUpListeners();

        username.setBounds(340, 130, 200, 30);
        username.setForeground(color);
        username.addFocusListener(focus);
        username.putClientProperty("defaultText","Digite seu usuário");
        panel.add(username);

        password.setForeground(color);
        password.setEchoChar((char) 0);
        password.putClientProperty("defaultText","Digite sua senha");
        password.addFocusListener(focus);
        panel.add(password);
        password.setBounds(340, 210, 200, 30);

        forgetPassword.setForeground(new java.awt.Color(204, 204, 204));
        forgetPassword.setText("Esqueci minha senha");
        panel.add(forgetPassword);
        forgetPassword.setBounds(340, 480, 180, 16);
        forgetPassword.setCursor(new Cursor(Cursor.HAND_CURSOR));

        singUp.setForeground(new java.awt.Color(204, 204, 204));
        singUp.setText("Cadastre-se");
        panel.add(singUp);
        singUp.setBounds(340, 440, 180, 16);
        singUp.setCursor(new Cursor(Cursor.HAND_CURSOR));

        image.setIcon(imageIcon);
        panel.add(image);
        image.setBounds(0, 0, 600, 650);

        login.setBounds(380, 340, 100, 23);
        panel.add(login);

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(panel, GroupLayout.DEFAULT_SIZE, 600, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(panel, GroupLayout.DEFAULT_SIZE, 650, Short.MAX_VALUE)
        );




        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void setUpListeners() {

        login.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                String usuario = username.getText();
                String senha = new String(password.getPassword());

                var user = userController.login(User.builder().username(usuario).password(senha).fields(new ArrayList<>()).build());
                if(user.getFields().isEmpty()){
                    setVisible(false);
                    new HomeView().setVisible(true);
                }else {
                    String mensagem = "Por favor, verifique os seguintes campos: " + String.join(", ", user.getFields());
                    JOptionPane.showMessageDialog(LoginView.this, mensagem,"Valide Campos",JOptionPane.WARNING_MESSAGE);

                }
            }
        });

        forgetPassword.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                setVisible(false);
                new ResetPassword(userController, emailController).setVisible(true);

            }
        });

        singUp.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                setVisible(false);
                new RegisterView(userController, emailController).setVisible(true);
            }
        });
    }

    private void initComponents() {
        panel = new JPanel();
        username = new JTextField("Digite seu usuário");
        login = new JButton("Entrar");
        password = new JPasswordField("Digite sua senha");
        singUp = new JLabel("Cadastre-se");
        forgetPassword = new JLabel("Esqueci minha senha");
        color = new Color(153, 153, 153);
        imageIcon = new ImageIcon("src/main/resources/images/image.png");
        image = new JLabel();
        focus = new GenericFocusAdapter();

                this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                login.requestFocusInWindow();
            }
        });

    }

    private void setLayoutLogin() {
        setTitle("Tela de Login");
        setSize(600, 650);
        setLocationRelativeTo(null);
        setLayout(null);
        panel.setLayout(null);
        add(panel);
    }
}
