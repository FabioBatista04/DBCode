package br.edu.dombosco.dbcode.accessManagment.view;

import br.edu.dombosco.dbcode.bugs.view.BugQueryView;
import br.edu.dombosco.dbcode.bugs.view.BugRegisterView;
import br.edu.dombosco.dbcode.bugs.view.BugRelatoryView;
import br.edu.dombosco.dbcode.requisitos.controller.ProjetoController;
import br.edu.dombosco.dbcode.requisitos.controller.RequisitosController;
import br.edu.dombosco.dbcode.requisitos.view.ProjetoView;
import br.edu.dombosco.dbcode.requisitos.view.RequisitoView;
import lombok.extern.slf4j.Slf4j;

import br.edu.dombosco.dbcode.bugs.controller.BugController;
import br.edu.dombosco.dbcode.accessManagment.model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@Slf4j
public class HomeView extends JFrame {


    private JMenuBar menu;

    private JMenu home;

    private JMenu requisitos;
    private JMenuItem subMenuRequisitos;
    private JMenuItem subMenuRequisitosProjeto;

    private JMenu bugs;
    private JMenuItem subMenuBugCadastro;
    private JMenuItem subMenuBugConsulta;
    private JMenuItem subMenuBugRelatorio;


    private JMenu test;
    private JMenuItem subMenuTest;
    private JMenuItem subMenutest2;

    private JMenu config;
    private JMenuItem subMenuConfig;
    private JMenuItem subMenuConfig2;

    private JMenu exit;

    private ImageIcon imageIcon;
    private JLabel image;


    private RequisitosController requisitosController;
    private BugController bugController;
    private ProjetoController projetoController;
    private User user;

    public HomeView(LoginView loginView){
        this.requisitosController = loginView.getRequisitosController();
        this.bugController = loginView.getBugController();
        this.user = loginView.getUser();
        this.projetoController = loginView.getProjetoController();

        setLayoutHome();
        initComponents();
        configComponents();

        setListenersBugs();
        setListenersRequisitos();
        setListenersHome();
        setListenerPojetos();
    }

    private void setListenerPojetos() {
        subMenuRequisitosProjeto.addActionListener(e -> {
            log.info("Projeto Menu Item clicked");
            addPanel(new ProjetoView(projetoController));
        });
    }

    private void setListenersHome() {
        home.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                log.info("Home Menu Item clicked");
                getContentPane().removeAll();
                getContentPane().revalidate();
                getContentPane().repaint();
                add(image);
                image.setVisible(true);
            }
        });
        exit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                log.info("Exit Menu Item clicked");
                System.exit(0);
            }
        });
    }

    private void setListenersRequisitos() {
        subMenuRequisitos.addActionListener(e -> {
            log.info("Requisitos Menu Item clicked");
            addPanel(new RequisitoView(requisitosController));
        });
    }

    private void setListenersBugs() {
        subMenuBugCadastro.addActionListener(e -> {
            log.info("Cadastro Menu Item clicked");
            addPanel(new BugRegisterView(bugController));
        });

        subMenuBugConsulta.addActionListener(e -> {
            log.info("Consulta Menu Item clicked");
            addPanel(new BugQueryView(bugController, user));
        });

        subMenuBugRelatorio.addActionListener(e -> {
            log.info("Relatorio Menu Item clicked");
            addPanel(new BugRelatoryView(bugController));
        });
    }

    private void addPanel(JPanel panel) {
        getContentPane().removeAll();
        getContentPane().add(panel);
        getContentPane().revalidate();
        getContentPane().repaint();
        panel.add(image);
        for( Component compo : panel.getComponents() ){
            if( compo instanceof JLabel ){

                compo.setForeground(new Color(245,245,245));
            }
        }
        for( Component compo : panel.getComponents() ){
            if( compo instanceof JRadioButton ){
                ((JRadioButton) compo).setOpaque(false);
                compo.setBackground(null);
                compo.setForeground(new Color(245,245,245));
            }
        }

        panel.setVisible(true);
        //image.setVisible(false);
    }

    private void setLayoutHome() {
        setTitle("DBCode");
        setSize(800, 650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);

    }

    private void configComponents() {
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);


        menu.add(home);
        menu.add(requisitos);
        menu.add(bugs);
        menu.add(test);
        //menu.add(config);
        menu.add(Box.createHorizontalGlue());
        menu.add(exit);

        bugs.add(subMenuBugCadastro);
        bugs.add(subMenuBugConsulta);
        bugs.add(subMenuBugRelatorio);


        test.add(subMenuTest);
        test.add(subMenutest2);

        //config.add(subMenuConfig);
        //config.add(subMenuConfig2);

        requisitos.add(subMenuRequisitos);
        requisitos.add(subMenuRequisitosProjeto);

        setJMenuBar(menu);
        image.setIcon(imageIcon);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(image, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(image, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }

    private void initComponents() {

        menu = new JMenuBar();
        home = new JMenu("Home");
        requisitos = new JMenu("Requisitos");
        bugs = new JMenu("Bugs");
        test = new JMenu("Test");
        //config = new JMenu("Configurações");
        exit = new JMenu("Sair");

        subMenuRequisitos = new JMenuItem("Requisito");
        subMenuRequisitosProjeto = new JMenuItem("Projeto");


        subMenuBugCadastro = new JMenuItem("Cadastro");
        subMenuBugConsulta = new JMenuItem("Consulta");
        subMenuBugRelatorio = new JMenuItem("Relatorio");

        subMenuTest = new JMenuItem("Plano");
        subMenutest2 = new JMenuItem("Caso");

//        subMenuConfig = new JMenuItem("Cadastrar");
//        subMenuConfig2 = new JMenuItem("Editar");

        imageIcon = new ImageIcon("src/main/resources/images/backend_opaco.png");
        image = new JLabel();

    }
}
