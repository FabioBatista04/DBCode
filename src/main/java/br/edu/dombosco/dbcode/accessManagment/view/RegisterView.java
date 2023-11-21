package br.edu.dombosco.dbcode.accessManagment.view;

import br.edu.dombosco.dbcode.accessManagment.adapter.GenericFocusAdapter;
import br.edu.dombosco.dbcode.accessManagment.controller.EmailController;
import br.edu.dombosco.dbcode.accessManagment.model.Profile;
import br.edu.dombosco.dbcode.accessManagment.model.User;
import br.edu.dombosco.dbcode.accessManagment.controller.UserController;
import br.edu.dombosco.dbcode.bugs.controller.BugController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class RegisterView extends JPanel {
    private final JTextField user = new JTextField("Digite seu nome de usuário");
    private final JTextField email = new JTextField("Digite seu email");
    private final JPasswordField password = new JPasswordField("Digite sua senha");
    private final JPasswordField replyPassword = new JPasswordField("Digite novamente sua senha");
    private final JLabel labelProfile = new JLabel("Selecione o perfil:");
    private final JComboBox<Profile> profileBox = new JComboBox<>(Profile.values());
    private GenericFocusAdapter focus;
    private JButton register = new JButton("Cadastrar");
    private JButton cancel = new JButton("Cancelar");
    private final ImageIcon imageIcon = new ImageIcon("src/main/resources/images/image.png");
    private final JLabel image = new JLabel();
    private UserController userController;
    private LoginView loginView;
    private EmailController emailController;
    private BugController bugController;

    public RegisterView(LoginView loginView) {
        this.loginView = loginView;
        this.userController = loginView.getUserController();
        this.bugController = loginView.getBugController();
        //setLocationRelativeTo(null);
        initializeComponents();
        addComponents();
        setupListeners();
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void addComponents() {


        add(email);
        add(password);
        add(replyPassword);
        add(labelProfile);
        add(profileBox);
        add(register);
        add(cancel);
        add(user);
        add(image);
    }

    private void setupListeners() {

        image.setIcon(imageIcon);
        image.setBounds(0, 0, 600, 650);


        register.setBounds(430, 490, 110, 30);
        register.setBackground(new Color(8, 138, 179));
        register.setForeground(new Color(255, 255, 255));
        register.setBorderPainted(false);



        user.putClientProperty("defaultText","Digite seu nome de usuário");
        user.setForeground(new Color(153, 153, 153));
        user.setBounds(320, 140, 210, 30);
        user.addFocusListener(focus);



        email.putClientProperty("defaultText","Digite seu email");
        email.setForeground(new java.awt.Color(153, 153, 153));
        email.setBounds(320, 210, 210, 30);
        email.addFocusListener(focus);



        password.putClientProperty("defaultText","Digite sua senha");
        password.setForeground(new Color(153, 153, 153));
        password.setBounds(320, 280, 210, 30);
        password.addFocusListener(focus);
        password.setEchoChar((char) 0);



        replyPassword.putClientProperty("defaultText","Digite novamente sua senha");
        replyPassword.setForeground(new Color(153, 153, 153));
        replyPassword.setBounds(320, 350, 210, 30);
        replyPassword.addFocusListener(focus);
        replyPassword.setEchoChar((char) 0);



        labelProfile.setForeground(new Color(255, 255, 255));
        labelProfile.setBounds(320, 410, 140, 16);



        profileBox.setBounds(320, 430, 140, 22);



        cancel.setBounds(320, 490, 100, 30);



        register.addActionListener(e -> {
            String username = user.getText();
            String emailAddress = email.getText();
            String userPassword = new String(password.getPassword());
            String userReplyPassword = new String(replyPassword.getPassword());
            if(userPassword.isEmpty() || userReplyPassword.isEmpty() || !userPassword.equals(userReplyPassword)){
                JOptionPane.showMessageDialog(RegisterView.this, "Senhas não conferem","Valide Campos",JOptionPane.WARNING_MESSAGE);
            }

            var userCreated = userController.create(User.builder()
                    .username(username)
                    .email(emailAddress)
                    .password(userPassword)
                    .profile((Profile) profileBox.getSelectedItem())
                    .fields(new ArrayList<>())
                    .build());
            if(userCreated.getFields().isEmpty()){
                JOptionPane.showMessageDialog(this, "Registro efetuado com sucesso!");

                this.loginView.addPanel(this.loginView.getPanel());
            }else {
                String mensagem = "Por favor, verifique os seguintes campos: " + String.join(", ", userCreated.getFields());
                JOptionPane.showMessageDialog(RegisterView.this, mensagem,"Valide Campos",JOptionPane.WARNING_MESSAGE);

            }
        });

        cancel.addActionListener(e -> {
                loginView.addPanel(loginView.getPanel());
        });
    }

    private void initializeComponents() {

        setSize(600, 650);
        //setLocationRelativeTo(null);
        setLayout(null);


        focus = new GenericFocusAdapter();
//        this.addWindowListener(new WindowAdapter() {
//            @Override
//            public void windowOpened(WindowEvent e) {
//                image.requestFocusInWindow();
//            }
//        });

    }

}

