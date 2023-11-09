package br.edu.dombosco.dbcode.accessManagment.view;

import javax.swing.*;
import javax.swing.plaf.PanelUI;
import java.awt.*;

public class HomeView extends JFrame {


    private JMenuBar menu;
    private JMenu requisitos;
    private JMenuItem subMenuRequisitos;
    private JMenuItem subMenuRequisitos2;
    private JMenu bugs;
    private JMenuItem subMenuBugs;
    private JMenuItem subMenuBugs2;
    private JMenu test;
    private JMenuItem subMenuTest;
    private JMenuItem subMenutest2;
    private JMenu config;
    private JMenuItem subMenuConfig;
    private JMenuItem subMenuConfig2;

    private ImageIcon imageIcon;
    private JLabel image;

    public HomeView(){

        setLayoutHome();

        initComponents();

        configComponents();


//        JTabbedPane tabbedPane = new JTabbedPane();
//
//        JPanel panel1 = new JPanel(new BorderLayout());
//        panel1.add(new JLabel("Conteudo Requisitos"));
//        tabbedPane.addTab("Requisitos", panel1);
//
//        JPanel panel2 = new JPanel(new BorderLayout());
//        panel2.add(new JLabel("Conteudo Testes"));
//        tabbedPane.addTab("Testes", panel2);
//
//        JPanel panel3 = new JPanel(new BorderLayout());
//        panel3.add(new JLabel("Conteudo Bugs"));
//        tabbedPane.addTab("Bugs", panel3);
//
//        JPanel panel4 = new JPanel(new BorderLayout());
//        panel4.add(new JLabel("Conteudo Configurações"));
//        tabbedPane.addTab("Configurações", panel4);
//
//        frame.add(tabbedPane, BorderLayout.CENTER);
//
//        JPanel footerPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
//        JButton exitButton = new JButton("Sair");
//        exitButton.addActionListener(e -> System.exit(0)); // Sai do programa ao clicar
//        footerPanel.add(exitButton);
//
//        frame.add(footerPanel, BorderLayout.PAGE_END);
//
//        frame.setVisible(true);
    }

    private void setLayoutHome() {
        setTitle("DBCode");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void configComponents() {
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        requisitos.setText("Requisitos");

        requisitos.add(subMenuRequisitos);

        requisitos.add(subMenuRequisitos2);

        menu.add(requisitos);

        bugs.setText("Bugs");

        bugs.add(subMenuBugs);

        bugs.add(subMenuBugs2);

        menu.add(bugs);

        test.setText("Test");

        test.add(subMenuTest);

        test.add(subMenutest2);

        menu.add(test);

        config.setText("Configurações");

        config.add(subMenuConfig);

        config.add(subMenuConfig2);

        menu.add(config);

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
        requisitos = new JMenu();
        subMenuRequisitos = new JMenuItem("Cadastrar");
        subMenuRequisitos2 = new JMenuItem("Editar");
        bugs = new JMenu();
        subMenuBugs = new JMenuItem("Cadastrar");
        subMenuBugs2 = new JMenuItem("Editar");
        test = new JMenu();
        subMenuTest = new JMenuItem("Cadastrar");
        subMenutest2 = new JMenuItem("Editar");
        config = new JMenu();
        subMenuConfig = new JMenuItem("Cadastrar");
        subMenuConfig2 = new JMenuItem("Editar");
        imageIcon = new ImageIcon("src/main/java/br/edu/dombosco/dbcode/accessManagment/view/backend_opaco.png");
        image = new JLabel();

    }
}
