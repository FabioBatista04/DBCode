package br.edu.dombosco.dbcode.accessManagment.view;

import br.edu.dombosco.dbcode.requisitos.controller.RequisitosController;
import br.edu.dombosco.dbcode.requisitos.view.RequisitoView;
import lombok.extern.slf4j.Slf4j;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@Slf4j
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

    private RequisitosController requisitosController;
    public HomeView(RequisitosController requisitosController){
        this.requisitosController = requisitosController;
        setLayoutHome();
        initComponents();
        configComponents();
        setListeners();
    }

    private void setListeners() {
        subMenuRequisitos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                log.info("Requisitos Menu Item clicked");
                setVisible(false);
                new RequisitoView(requisitosController).setVisible(true);
            }
        });
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


        requisitos.add(subMenuRequisitos);
        requisitos.add(subMenuRequisitos2);
        menu.add(requisitos);
        bugs.add(subMenuBugs);
        bugs.add(subMenuBugs2);
        menu.add(bugs);
        test.add(subMenuTest);
        test.add(subMenutest2);
        menu.add(test);
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
        requisitos = new JMenu("Requisitos");
        subMenuRequisitos = new JMenuItem("Cadastrar");
        subMenuRequisitos2 = new JMenuItem("Editar");
        bugs = new JMenu("Bugs");
        subMenuBugs = new JMenuItem("Cadastrar");
        subMenuBugs2 = new JMenuItem("Editar");
        test = new JMenu("Test");
        subMenuTest = new JMenuItem("Cadastrar");
        subMenutest2 = new JMenuItem("Editar");
        config = new JMenu("Configurações");
        subMenuConfig = new JMenuItem("Cadastrar");
        subMenuConfig2 = new JMenuItem("Editar");
        imageIcon = new ImageIcon("src/main/resources/images/backend_opaco.png");
        image = new JLabel();

    }
}
