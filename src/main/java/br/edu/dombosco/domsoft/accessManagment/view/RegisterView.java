package br.edu.dombosco.domsoft.accessManagment.view;

import br.edu.dombosco.domsoft.accessManagment.model.Profile;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class RegisterView extends JFrame {
    private JTextField user;
    private JTextField email;
    private JPasswordField password;
    private JPasswordField replyPassword;
    private ButtonGroup genderGroup;
    private Profile selectedGender;
    private JButton register;
    int cantoEsquerdoTexto = 10;
    int comprimentoTexto = 170;
    int distanciaTexto = comprimentoTexto - 15;

    public RegisterView() {
        setTitle("Cadastro");
        setSize(500, 300);
        setLocationRelativeTo(null);
        //setLayout(new GridLayout(3, 2));
        setLayout(null);

        JLabel usernameLabel = new JLabel("Nome de Usuário:");
        usernameLabel.setBounds(cantoEsquerdoTexto, 10, comprimentoTexto, 25);
        add(usernameLabel);

        user = new JTextField();
        user.setBounds(distanciaTexto, 10, 150, 25);
        add(user);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(cantoEsquerdoTexto, 50, comprimentoTexto, 25);
        add(emailLabel);

        email = new JTextField();
        email.setBounds(distanciaTexto, 50, 150, 25);
        add(email);

        JLabel passwordLabel = new JLabel("Nova Senha:");
        passwordLabel.setBounds(cantoEsquerdoTexto, 90, comprimentoTexto, 25);
        add(passwordLabel);

        password = new JPasswordField();
        password.setBounds(distanciaTexto, 90, 150, 25);
        add(password);

        JLabel replyPasswordLabel = new JLabel("Repita Nova Senha:");
        replyPasswordLabel.setBounds(cantoEsquerdoTexto, 130, comprimentoTexto, 25);
        add(replyPasswordLabel);

        replyPassword = new JPasswordField();
        replyPassword.setBounds(distanciaTexto, 130, 150, 25);
        add(replyPassword);

        // Adicionando a seleção de gênero
        JLabel genderLabel = new JLabel("Perfil:");
        genderLabel.setBounds(cantoEsquerdoTexto, 170, comprimentoTexto, 25);
        add(genderLabel);

        genderGroup = new ButtonGroup();


        genderGroup = new ButtonGroup();
        int offset = 0;
        for (Profile profile : Profile.values()) {
            JRadioButton radioButton = new JRadioButton(profile.getProfileName());
            radioButton.setBounds(profile.getPadding(), 195, profile.getWidth(), 25);
            radioButton.addActionListener(e -> selectedGender = profile);
            genderGroup.add(radioButton);
            add(radioButton);
            offset += 120;  // ajuste conforme necessário
        }


        register = new JButton("Registrar");
        register.setBounds(130, 230, 150, 25);
        register.addActionListener(e -> {
            String username = user.getText();
            String emailAddress = email.getText();
            String userPassword = new String(password.getPassword());
            String userReplyPassword = new String(replyPassword.getPassword());

            // Aqui, adicione a lógica para verificar se as duas senhas são iguais e outros
            // tratamentos de registro necessários
            // ...

            JOptionPane.showMessageDialog(this, "Registro efetuado com sucesso!");
        });

        add(register);  // Adicionando o botão ao painel

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
}

