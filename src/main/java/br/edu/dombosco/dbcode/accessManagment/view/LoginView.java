package br.edu.dombosco.dbcode.accessManagment.view;

import br.edu.dombosco.dbcode.accessManagment.adapter.GenericFocusAdapter;
import br.edu.dombosco.dbcode.accessManagment.controller.EmailController;
import br.edu.dombosco.dbcode.accessManagment.controller.UserController;
import br.edu.dombosco.dbcode.accessManagment.model.User;
import br.edu.dombosco.dbcode.requisitos.controller.ProjetoController;
import br.edu.dombosco.dbcode.requisitos.controller.RequisitosController;
import br.edu.dombosco.dbcode.test.controller.CasoController;
import br.edu.dombosco.dbcode.test.controller.CasoDetalhadoController;
import br.edu.dombosco.dbcode.test.controller.CenarioController;
import br.edu.dombosco.dbcode.test.controller.PlanoController;
import lombok.Getter;
import br.edu.dombosco.dbcode.bugs.controller.BugController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

@Getter
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
    private RequisitosController requisitosController;
    private BugController bugController;
    private ProjetoController projetoController;
    private PlanoController planoController;
    private CenarioController cenarioController;
    private CasoController casoController;
    private CasoDetalhadoController casoDetalhadoController;
    private User user;


    public LoginView(UserController userController, EmailController emailController,
                     RequisitosController requisitosController, BugController bugController,
                     ProjetoController projetoController, PlanoController planoController,
                     CenarioController cenarioController, CasoController casoController,
                     CasoDetalhadoController casoDetalhadoController
    ) {
        this.requisitosController = requisitosController;
        this.userController = userController;
        this.emailController = emailController;
        this.bugController = bugController;
        this.projetoController = projetoController;
        this.planoController = planoController;
        this.cenarioController = cenarioController;
        this.casoController = casoController;
        this.casoDetalhadoController = casoDetalhadoController;

        initComponents();
        setLayoutLogin();
        setUpListeners();
        addComponentsToPanel();

        username.setBounds(340, 130, 200, 30);
        username.setForeground(color);
        username.addFocusListener(focus);
        username.putClientProperty("defaultText","Digite seu usuário");


        password.setForeground(color);
        password.setEchoChar((char) 0);
        password.putClientProperty("defaultText","Digite sua senha");
        password.addFocusListener(focus);

        password.setBounds(340, 210, 200, 30);

        forgetPassword.setForeground(new java.awt.Color(204, 204, 204));
        forgetPassword.setText("Esqueci minha senha");

        forgetPassword.setBounds(340, 480, 180, 16);
        forgetPassword.setCursor(new Cursor(Cursor.HAND_CURSOR));

        singUp.setForeground(new java.awt.Color(204, 204, 204));
        singUp.setText("Cadastre-se");

        singUp.setBounds(340, 440, 180, 16);
        singUp.setCursor(new Cursor(Cursor.HAND_CURSOR));

        image.setIcon(imageIcon);

        image.setBounds(0, 0, 600, 650);

        login.setBounds(380, 340, 100, 23);


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

    private void addComponentsToPanel() {
        panel.add(username);
        panel.add(password);
        panel.add(forgetPassword);
        panel.add(singUp);
        panel.add(login);
        panel.add(image);
    }

    private void setUpListeners() {

        login.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                String usuario = username.getText();
                String senha = new String(password.getPassword());

                var userDb = userController.login(User.builder().username(usuario).password(senha).fields(new ArrayList<>()).build());
                if(userDb.getFields().isEmpty()){
                    user = userDb;
                    setVisible(false);
                    new HomeView(LoginView.this).setVisible(true);
                }else {
                    String mensagem = "Por favor, verifique os seguintes campos: " + String.join(", ", userDb.getFields());
                    JOptionPane.showMessageDialog(LoginView.this, mensagem,"Valide Campos",JOptionPane.WARNING_MESSAGE);

                }
            }
        });

        forgetPassword.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                addPanel(new ResetPassword(LoginView.this));
            }
        });

        singUp.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                addPanel(new RegisterView(LoginView.this));
            }
        });
    }



    public void addPanel(JPanel panel) {
        getContentPane().removeAll();
        getContentPane().add(panel);
        getContentPane().revalidate();
        getContentPane().repaint();
        panel.setVisible(true);
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
        setResizable(false);
        panel.setLayout(null);
        add(panel);
    }
}
