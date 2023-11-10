package br.edu.dombosco.dbcode.accessManagment.view;

import br.edu.dombosco.dbcode.accessManagment.adapter.GenericFocusAdapter;
import br.edu.dombosco.dbcode.accessManagment.controller.EmailController;
import br.edu.dombosco.dbcode.accessManagment.model.Profile;
import br.edu.dombosco.dbcode.accessManagment.model.User;
import br.edu.dombosco.dbcode.accessManagment.controller.UserController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class RegisterView extends JFrame {
    private final JPanel panel = new JPanel();
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

    public RegisterView(LoginView loginView) {
        this.loginView = loginView;
        this.userController = loginView.getUserController();
        setLocationRelativeTo(null);
        initializeComponents();
        setupListeners();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void setupListeners() {

        image.setIcon(imageIcon);
        image.setBounds(0, 0, 600, 650);
        panel.add(image);

        register.setBounds(430, 490, 110, 30);
        register.setBackground(new Color(8, 138, 179));
        register.setForeground(new Color(255, 255, 255));
        register.setBorderPainted(false);
        panel.add(register);
        add(register);

        user.putClientProperty("defaultText","Digite seu nome de usuário");
        user.setForeground(new Color(153, 153, 153));
        user.setBounds(320, 140, 210, 30);
        user.addFocusListener(focus);
        panel.add(user);
        add(user);

        email.putClientProperty("defaultText","Digite seu email");
        email.setForeground(new java.awt.Color(153, 153, 153));
        email.setBounds(320, 210, 210, 30);
        email.addFocusListener(focus);
        panel.add(email);
        add(email);

        password.putClientProperty("defaultText","Digite sua senha");
        password.setForeground(new Color(153, 153, 153));
        password.setBounds(320, 280, 210, 30);
        password.addFocusListener(focus);
        password.setEchoChar((char) 0);
        panel.add(password);
        add(password);

        replyPassword.putClientProperty("defaultText","Digite novamente sua senha");
        replyPassword.setForeground(new Color(153, 153, 153));
        replyPassword.setBounds(320, 350, 210, 30);
        replyPassword.addFocusListener(focus);
        replyPassword.setEchoChar((char) 0);
        panel.add(replyPassword);
        add(replyPassword);

        labelProfile.setForeground(new Color(255, 255, 255));
        labelProfile.setBounds(320, 410, 140, 16);
        panel.add(labelProfile);
        add(labelProfile);

        profileBox.setBounds(320, 430, 140, 22);
        panel.add(profileBox);
        add(profileBox);

        cancel.setBounds(320, 490, 100, 30);
        panel.add(cancel);
        add(cancel);

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(panel, GroupLayout.DEFAULT_SIZE, 600, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(panel, GroupLayout.DEFAULT_SIZE, 650, Short.MAX_VALUE)
        );


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
                setVisible(false);
                this.loginView.setVisible(true);
            }else {
                String mensagem = "Por favor, verifique os seguintes campos: " + String.join(", ", userCreated.getFields());
                JOptionPane.showMessageDialog(RegisterView.this, mensagem,"Valide Campos",JOptionPane.WARNING_MESSAGE);

            }
        });

        cancel.addActionListener(e -> {
                setVisible(false);
                loginView.setVisible(true);
        });
    }

    private void initializeComponents() {
        setTitle("Cadastro");
        setSize(600, 650);
        setLocationRelativeTo(null);
        setLayout(null);
        panel.setLayout(null);

        focus = new GenericFocusAdapter();
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                image.requestFocusInWindow();
            }
        });

    }

}

