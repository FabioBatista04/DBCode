package br.edu.dombosco.dbcode.accessManagment.view;

import br.edu.dombosco.dbcode.accessManagment.controller.EmailController;
import br.edu.dombosco.dbcode.accessManagment.controller.UserController;
import br.edu.dombosco.dbcode.accessManagment.model.User;

import javax.swing.*;


public class NewPasswordView extends JFrame {
//    private JLabel usernameLabel;
//    private JTextField user;

//    private JLabel emailLabel;
//    private JTextField email;

    private JLabel passwordLabel;
    private JPasswordField password;

    private JLabel replyPasswordLabel;
    private JPasswordField replyPassword;
    private User user;

//    private JLabel profileLabel;
//    private ButtonGroup profileGroup;
//    private Profile profileSelected;

    private JButton register;
    int cantoEsquerdoTexto = 10;
    int comprimentoTexto = 170;
    int distanciaTexto = comprimentoTexto - 15;
    private UserController userController;
    private EmailController emailController;

    public NewPasswordView(UserController userController,EmailController emailController, User user) {
        this.user = user;
        this.userController = userController;
        this.emailController = emailController;
        setLocationRelativeTo(null);
        initializeComponents();
        setupListeners();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void setupListeners() {

//        usernameLabel.setBounds(cantoEsquerdoTexto, 10, comprimentoTexto, 25);
//        user.setBounds(distanciaTexto, 10, 150, 25);
//        emailLabel.setBounds(cantoEsquerdoTexto, 50, comprimentoTexto, 25);
//        email.setBounds(distanciaTexto, 50, 150, 25);
        passwordLabel.setBounds(cantoEsquerdoTexto, 90, comprimentoTexto, 25);
        password.setBounds(distanciaTexto, 90, 150, 25);
        replyPasswordLabel.setBounds(cantoEsquerdoTexto, 130, comprimentoTexto, 25);
        replyPassword.setBounds(distanciaTexto, 130, 150, 25);
//        profileLabel.setBounds(cantoEsquerdoTexto, 170, comprimentoTexto, 25);
//
//
//        add(usernameLabel);
//        add(user);
//        add(emailLabel);
//        add(email);
        add(passwordLabel);
        add(password);
        add(replyPasswordLabel);
        add(replyPassword);
//        add(profileLabel);
        add(register);

//        for (Profile profile : Profile.values()) {
//            JRadioButton radioButton = new JRadioButton(profile.getProfileName());
//            radioButton.setBounds(profile.getPadding(), 195, profile.getWidth(), 25);
//            radioButton.addActionListener(e -> profileSelected = profile);
//            profileGroup.add(radioButton);
//            add(radioButton);
//
//        }

        register.setBounds(130, 230, 150, 25);
        register.addActionListener(e -> {
//            String username = user.getText();
//            String emailAddress = email.getText();
            String userPassword = new String(password.getPassword());
            String userReplyPassword = new String(replyPassword.getPassword());
            if(userPassword.isEmpty() || userReplyPassword.isEmpty() || !userPassword.equals(userReplyPassword)){
                JOptionPane.showMessageDialog(NewPasswordView.this, "Senhas não conferem","Valide Campos",JOptionPane.WARNING_MESSAGE);
            }
            user.setPassword(userPassword);
            var userCreated = userController.update(user);

            if(userCreated != null){
                JOptionPane.showMessageDialog(this, "Registro efetuado com sucesso!");
                setVisible(false);
                new LoginView(userController, emailController).setVisible(true);
            }else {
                String mensagem = "Erro ao atualizar senha";
                JOptionPane.showMessageDialog(NewPasswordView.this, mensagem,"Erro",JOptionPane.ERROR_MESSAGE);

            }
        });




    }

    private void initializeComponents() {
        setTitle("Cadastro");
        setSize(800, 600);
        setLocationRelativeTo(null);
        //setLayout(new GridLayout(3, 2));
        setLayout(null);

//        usernameLabel = new JLabel("Nome de Usuário:");
//        user = new JTextField();
//
//        emailLabel = new JLabel("Email:");
//        email = new JTextField();

        password = new JPasswordField();
        passwordLabel = new JLabel("Nova Senha:");

        replyPassword = new JPasswordField();
        replyPasswordLabel = new JLabel("Repita Nova Senha:");

//        profileLabel = new JLabel("Perfil:");
//        profileGroup = new ButtonGroup();

        register = new JButton("Registrar");

    }

}
