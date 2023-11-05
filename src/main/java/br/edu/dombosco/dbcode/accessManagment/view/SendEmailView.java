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

public class SendEmailView extends JFrame {

    private JPanel panel = new JPanel();
    private JTextField email = new JTextField("Digite seu email cadastrado");
    private JTextField code = new JTextField("Digite o código recebido");
    private JPasswordField password = new JPasswordField("Digite a nova senha");
    private JPasswordField replyPassword = new JPasswordField("Repita a nova senha");
    private JButton sendCode = new JButton("Enviar código");
    private JButton savePassword = new JButton("Salvar");
    private JButton cancel = new JButton("Cancelar");
    private ImageIcon imageIcon = new ImageIcon("src/main/java/br/edu/dombosco/dbcode/accessManagment/view/image.png");
    private JLabel image = new JLabel(imageIcon);

    private GenericFocusAdapter focus = new GenericFocusAdapter();
    private final EmailController emailController;
    private final UserController userController;

    private User user;

    public SendEmailView(UserController userController, EmailController emailController) {
        this.emailController = emailController;
        this.userController = userController;
        setVisible(true);
        setTitle("Password Reset");
        setSize(600, 650);
        setLocationRelativeTo(null);
        setLayout(null);
        panel.setLayout(null);


        email.setBounds(330, 140, 210, 22);
        email.setForeground(new Color(153, 153, 153));
        email.putClientProperty("defaultText","Digite seu email cadastrado");
        email.addFocusListener(focus);
        panel.add(email);
        add(email);

        code.setBounds(330, 230, 210, 22);
        code.setForeground(new Color(153, 153, 153));
        code.putClientProperty("defaultText","Digite o código recebido");
        code.addFocusListener(focus);
        panel.add(code);
        add(code);

        password.setBounds(330, 300, 210, 22);
        password.setForeground(new Color(153, 153, 153));
        password.putClientProperty("defaultText","Digite a nova senha");
        password.addFocusListener(focus);
        password.setEchoChar((char) 0);
        panel.add(password);
        add(password);

        replyPassword.setBounds(330, 350, 210, 22);
        replyPassword.setForeground(new Color(153, 153, 153));
        replyPassword.putClientProperty("defaultText","Repita a nova senha");
        replyPassword.addFocusListener(focus);
        replyPassword.setEchoChar((char) 0);
        panel.add(replyPassword);
        add(replyPassword);

        sendCode.setBounds(370, 410, 120, 23);
        panel.add(sendCode);
        add(sendCode);

        savePassword.setBounds(440, 470, 100, 23);
        panel.add(savePassword);
        add(savePassword);

        cancel.setBounds(330, 470, 100, 23);
        panel.add(cancel);
        add(cancel);


        image.setBounds(0, 0, 600, 650);
        panel.add(image);
        add(image);

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

        sendCode.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                String email = SendEmailView.this.email.getText();
                if(email == null || email.isEmpty() || email.equals("Digite seu email cadastrado")){
                    JOptionPane.showMessageDialog(SendEmailView.this, "Email vazio","Valide Campos",JOptionPane.WARNING_MESSAGE);
                }else {
                    var user = emailController.sendEmailToResetPassword(email);
                    if (user == null){
                        JOptionPane.showMessageDialog(SendEmailView.this, "Email não encontrado","Valide Campos",JOptionPane.WARNING_MESSAGE);
                    } else {
                        SendEmailView.this.user = user;
                        JOptionPane.showMessageDialog(SendEmailView.this, "Email Enviado","Sucesso", JOptionPane.INFORMATION_MESSAGE);
                    }

                }


            }
        });

        savePassword.addActionListener(e -> {
            String code = SendEmailView.this.code.getText();
            log.info("sssssss {}",SendEmailView.this.code.getText());

            if (code == null || code.isEmpty() || code.equals("Digite o código recebido")) {
                JOptionPane.showMessageDialog(SendEmailView.this, "Código vazio", "Valide Campos", JOptionPane.WARNING_MESSAGE);
            } else {
                var user = userController.findByEmail(SendEmailView.this.user.getEmail());
                if (user == null) {
                    JOptionPane.showMessageDialog(SendEmailView.this, "Email não encontrado", "Valide Campos", JOptionPane.WARNING_MESSAGE);
                } else if (!code.equals(user.getResetCode())) {
                    JOptionPane.showMessageDialog(SendEmailView.this, "Código não é válido", "Valide Campos", JOptionPane.WARNING_MESSAGE);
                }
            }

            String userPassword = new String(SendEmailView.this.password.getPassword());
            String userReplyPassword = new String(SendEmailView.this.replyPassword.getPassword());
            if(userPassword.isEmpty() || userReplyPassword.isEmpty() || !userPassword.equals(userReplyPassword)){
                JOptionPane.showMessageDialog(SendEmailView.this, "Senhas não conferem","Valide Campos",JOptionPane.WARNING_MESSAGE);
            }
            user.setPassword(userPassword);
            var userCreated = userController.update(user);

            if(userCreated != null){
                JOptionPane.showMessageDialog(this, "Registro efetuado com sucesso!");
                setVisible(false);
                new LoginView(userController, emailController).setVisible(true);
            }else {
                String mensagem = "Erro ao atualizar senha";
                JOptionPane.showMessageDialog(SendEmailView.this, mensagem,"Erro",JOptionPane.ERROR_MESSAGE);

            }
        });

        cancel.addActionListener(e -> {
            setVisible(false);
            new LoginView(userController, emailController).setVisible(true);
        });

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                image.requestFocusInWindow();
            }
        });


        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}