package br.edu.dombosco.dbcode.accessManagment.view;

import br.edu.dombosco.dbcode.accessManagment.adapter.GenericFocusAdapter;
import br.edu.dombosco.dbcode.accessManagment.controller.EmailController;
import br.edu.dombosco.dbcode.accessManagment.controller.UserController;
import br.edu.dombosco.dbcode.accessManagment.model.User;
import lombok.extern.slf4j.Slf4j;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

@Slf4j

public class ResetPassword extends JPanel {

    private JTextField email = new JTextField("Digite seu email cadastrado");
    private JTextField code = new JTextField("Digite o código recebido");
    private JPasswordField password = new JPasswordField("Digite a nova senha");
    private JPasswordField replyPassword = new JPasswordField("Repita a nova senha");
    private JButton sendCode = new JButton("Enviar código");
    private JButton savePassword = new JButton("Salvar");
    private JButton cancel = new JButton("Cancelar");
    private ImageIcon imageIcon = new ImageIcon("src/main/resources/images/image.png");
    private JLabel image = new JLabel(imageIcon);

    private GenericFocusAdapter focus = new GenericFocusAdapter();
    private final EmailController emailController;
    private final UserController userController;

    private User user = User.builder().build();
    private LoginView loginView;

    public ResetPassword(LoginView loginView) {
        this.loginView = loginView;
        this.emailController = loginView.getEmailController();
        this.userController = loginView.getUserController();

        addComponents();
        setSize(600, 650);
        setLayout(null);

        email.setBounds(330, 140, 210, 30);
        email.setForeground(new Color(153, 153, 153));
        email.putClientProperty("defaultText","Digite seu email cadastrado");
        email.addFocusListener(focus);

        code.setBounds(330, 230, 210, 30);
        code.setForeground(new Color(153, 153, 153));
        code.putClientProperty("defaultText","Digite o código recebido");
        code.addFocusListener(focus);

        password.setBounds(330, 300, 210, 30);
        password.setForeground(new Color(153, 153, 153));
        password.putClientProperty("defaultText","Digite a nova senha");
        password.addFocusListener(focus);
        password.setEchoChar((char) 0);

        replyPassword.setBounds(330, 350, 210, 30);
        replyPassword.setForeground(new Color(153, 153, 153));
        replyPassword.putClientProperty("defaultText","Repita a nova senha");
        replyPassword.addFocusListener(focus);
        replyPassword.setEchoChar((char) 0);

        sendCode.setBounds(370, 410, 140, 23);

        savePassword.setBounds(440, 470, 100, 23);

        cancel.setBounds(330, 470, 100, 23);

        image.setBounds(0, 0, 600, 650);



        sendCode.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                String email = ResetPassword.this.email.getText();
                if(email == null || email.isEmpty() || email.equals("Digite seu email cadastrado")){
                    JOptionPane.showMessageDialog(ResetPassword.this, "Email vazio","Valide Campos",JOptionPane.WARNING_MESSAGE);
                }else {
                    var user = emailController.sendEmailToResetPassword(email);
                    if (user == null){
                        JOptionPane.showMessageDialog(ResetPassword.this, "Email não encontrado","Valide Campos",JOptionPane.WARNING_MESSAGE);
                    } else {
                        ResetPassword.this.user = user;
                        JOptionPane.showMessageDialog(ResetPassword.this, "Email Enviado","Sucesso", JOptionPane.INFORMATION_MESSAGE);
                    }

                }


            }
        });

        savePassword.addActionListener(e -> {
            String code = ResetPassword.this.code.getText();

            if (code == null || code.isEmpty() || code.equals("Digite o código recebido")) {
                JOptionPane.showMessageDialog(ResetPassword.this, "Código vazio", "Valide Campos", JOptionPane.WARNING_MESSAGE);
            } else {
                var user = userController.findByEmail(ResetPassword.this.user.getEmail());
                if (user != null && !code.equals(user.getResetCode())) {
                    JOptionPane.showMessageDialog(ResetPassword.this, "Código não é válido", "Valide Campos", JOptionPane.WARNING_MESSAGE);
                }else {
                    String userPassword = new String(ResetPassword.this.password.getPassword());
                    String userReplyPassword = new String(ResetPassword.this.replyPassword.getPassword());
                    if(userPassword.isEmpty() || userReplyPassword.isEmpty() || !userPassword.equals(userReplyPassword)){
                        JOptionPane.showMessageDialog(ResetPassword.this, "Senhas não conferem","Valide Campos",JOptionPane.WARNING_MESSAGE);
                    }else{
                        user.setPassword(userPassword);
                        var userCreated = userController.update(user);
                        if(userCreated != null){
                            JOptionPane.showMessageDialog(this, "Registro efetuado com sucesso!");
                            loginView.addPanel(loginView.getPanel());
                        }else {
                            String mensagem = "Erro ao atualizar senha";
                            JOptionPane.showMessageDialog(ResetPassword.this, mensagem,"Erro",JOptionPane.ERROR_MESSAGE);

                        }
                    }
                }
            }
        });

        cancel.addActionListener(e -> {
            loginView.addPanel(loginView.getPanel());
        });

    }

    private void addComponents() {
        add(email);
        add(code);
        add(password);
        add(replyPassword);
        add(sendCode);
        add(savePassword);
        add(cancel);
        add(image);
    }

}