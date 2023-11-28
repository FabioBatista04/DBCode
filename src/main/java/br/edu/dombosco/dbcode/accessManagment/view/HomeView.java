package br.edu.dombosco.dbcode.accessManagment.view;

import br.edu.dombosco.dbcode.bugs.view.BugQueryView;
import br.edu.dombosco.dbcode.bugs.view.BugRegisterView;
import br.edu.dombosco.dbcode.bugs.view.BugRelatoryView;
import br.edu.dombosco.dbcode.requisitos.controller.ProjetoController;
import br.edu.dombosco.dbcode.requisitos.controller.RequisitosController;
import br.edu.dombosco.dbcode.requisitos.model.Projeto;
import br.edu.dombosco.dbcode.requisitos.view.ProjetoView;
import br.edu.dombosco.dbcode.requisitos.view.RequisitoView;
import br.edu.dombosco.dbcode.test.controller.CasoController;
import br.edu.dombosco.dbcode.test.controller.CasoDetalhadoController;
import br.edu.dombosco.dbcode.test.controller.CenarioController;
import br.edu.dombosco.dbcode.test.controller.PlanoController;
import br.edu.dombosco.dbcode.test.model.Caso;
import br.edu.dombosco.dbcode.test.model.Cenario;
import br.edu.dombosco.dbcode.test.model.Plano;
import br.edu.dombosco.dbcode.test.view.CasoDetalhadoView;
import br.edu.dombosco.dbcode.test.view.CasoTesteView;
import br.edu.dombosco.dbcode.test.view.CenarioTesteView;
import br.edu.dombosco.dbcode.test.view.PlanoTesteView;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import br.edu.dombosco.dbcode.bugs.controller.BugController;
import br.edu.dombosco.dbcode.accessManagment.model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@Slf4j
@Getter
@Setter
public class HomeView extends JFrame {


    private JMenuBar menu;

    private JMenu home;

    private JMenu projetos;
    private JMenu requisitos;

    private JMenu bugs;
    private JMenuItem subMenuBugCadastro;
    private JMenuItem subMenuBugConsulta;
    private JMenuItem subMenuBugRelatorio;


    private JMenu test;
    private JMenuItem subMenuPlanoTest;
    private JMenuItem subMenuCenarioTest;
    private JMenuItem subMenuCasoTest;
    private JMenuItem subMenuCasoDetalhadoTest;


    private JMenu exit;

    private ImageIcon imageIcon;
    private JLabel image;


    private RequisitosController requisitosController;
    private BugController bugController;
    private ProjetoController projetoController;
    private PlanoController planoController;
    private CenarioController cenarioController;
    private CasoController casoController;
    private CasoDetalhadoController casoDetalhadoController;
    private User user;
    private Projeto projeto;
    private Plano plano;
    private Cenario cenario;
    private Caso caso;


    public HomeView(LoginView loginView){
        this.requisitosController = loginView.getRequisitosController();
        this.bugController = loginView.getBugController();
        this.user = loginView.getUser();
        this.projetoController = loginView.getProjetoController();
        this.planoController = loginView.getPlanoController();
        this.cenarioController = loginView.getCenarioController();
        this.casoController = loginView.getCasoController();
        this.casoDetalhadoController = loginView.getCasoDetalhadoController();

        setLayoutHome();
        initComponents();
        configComponents();

        setListenersTest();
        setListenersBugs();
        setListenersRequisitos();
        setListenersHome();
        setListenerPojetos();

    }

    private void setListenersTest() {
        subMenuPlanoTest.addActionListener(e -> {
            if(projeto == null){
                JOptionPane.showMessageDialog(HomeView.this, "É necessário selecionar um projeto","Projeto não selecionado",JOptionPane.WARNING_MESSAGE);
                return;
            }
            log.info("Plano Menu Item clicked");
            addPanel(new PlanoTesteView(HomeView.this));
        });
        subMenuCenarioTest.addActionListener(e -> {
            if(projeto == null){
                JOptionPane.showMessageDialog(HomeView.this, "É necessário selecionar um projeto","Projeto não selecionado",JOptionPane.WARNING_MESSAGE);
                return;
            }
            if(plano == null){
                JOptionPane.showMessageDialog(HomeView.this, "É necessário selecionar um Plano de Teste","Plano de Teste não selecionado",JOptionPane.WARNING_MESSAGE);
                return;
            }
            log.info("Cenario Menu Item clicked");
            addPanel(new CenarioTesteView(HomeView.this));
        });
        subMenuCasoTest.addActionListener(e -> {
            if(projeto == null){
                JOptionPane.showMessageDialog(HomeView.this, "É necessário selecionar um projeto","Projeto não selecionado",JOptionPane.WARNING_MESSAGE);
                return;
            }
            if(cenario == null){
                JOptionPane.showMessageDialog(HomeView.this, "É necessário selecionar um Cenário de Teste","Cenário de Teste não selecionado",JOptionPane.WARNING_MESSAGE);
                return;
            }
            log.info("Caso Menu Item clicked");
            addPanel(new CasoTesteView(HomeView.this));
        });
        subMenuCasoDetalhadoTest.addActionListener(e -> {
            if(projeto == null){
                JOptionPane.showMessageDialog(HomeView.this, "É necessário selecionar um projeto","Projeto não selecionado",JOptionPane.WARNING_MESSAGE);
                return;
            }
            if(cenario == null){
                JOptionPane.showMessageDialog(HomeView.this, "É necessário selecionar um Cenário de Teste","Cenário de Teste não selecionado",JOptionPane.WARNING_MESSAGE);
                return;
            }
            if(caso == null){
                JOptionPane.showMessageDialog(HomeView.this, "É necessário selecionar um Caso de Teste","Caso de Teste não selecionado",JOptionPane.WARNING_MESSAGE);
                return;
            }
            log.info("Caso Detalhado Menu Item clicked");
            addPanel(new CasoDetalhadoView(HomeView.this));
        });
    }

    private void setListenerPojetos() {
        projetos.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
            log.info("Projeto Menu Item clicked");
            addPanel(new ProjetoView(HomeView.this));
        }});
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
        requisitos.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if(projeto == null){
                    JOptionPane.showMessageDialog(HomeView.this, "É necessário selecionar um projeto","Projeto não selecionado",JOptionPane.WARNING_MESSAGE);
                    return;
                }
            log.info("Requisitos Menu Item clicked");
            addPanel(new RequisitoView(HomeView.this));
        }});
    }

    private void setListenersBugs() {
        subMenuBugCadastro.addActionListener(e -> {
            if(projeto == null){
                JOptionPane.showMessageDialog(HomeView.this, "É necessário selecionar um projeto","Projeto não selecionado",JOptionPane.WARNING_MESSAGE);
                return;
            }
            log.info("Cadastro Menu Item clicked");
            addPanel(new BugRegisterView(HomeView.this));
        });

        subMenuBugConsulta.addActionListener(e -> {
            if(projeto == null){
                JOptionPane.showMessageDialog(HomeView.this, "É necessário selecionar um projeto","Projeto não selecionado",JOptionPane.WARNING_MESSAGE);
                return;
            }
            log.info("Consulta Menu Item clicked");
            addPanel(new BugQueryView(HomeView.this));
        });

        subMenuBugRelatorio.addActionListener(e -> {
            if(projeto == null){
                JOptionPane.showMessageDialog(HomeView.this, "É necessário selecionar um projeto","Projeto não selecionado",JOptionPane.WARNING_MESSAGE);
                return;
            }
            log.info("Relatorio Menu Item clicked");
            addPanel(new BugRelatoryView(bugController));
        });
    }

    public void addPanel(JPanel panel) {
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
        menu.add(projetos);
        menu.add(requisitos);
        menu.add(bugs);
        menu.add(test);
        //menu.add(config);
        menu.add(Box.createHorizontalGlue());
        menu.add(exit);

        bugs.add(subMenuBugCadastro);
        bugs.add(subMenuBugConsulta);
        bugs.add(subMenuBugRelatorio);


        test.add(subMenuPlanoTest);
        test.add(subMenuCenarioTest);
        test.add(subMenuCasoTest);
        test.add(subMenuCasoDetalhadoTest);

        //config.add(subMenuConfig);
        //config.add(subMenuConfig2);

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
    public void goToHome(){
        log.info("Home Menu Item clicked");
        getContentPane().removeAll();
        getContentPane().revalidate();
        getContentPane().repaint();
        add(image);
        image.setVisible(true);
    }

    private void initComponents() {

        menu = new JMenuBar();
        home = new JMenu("Home");
        projetos = new JMenu("Projetos");
        requisitos = new JMenu("Requisitos");
        bugs = new JMenu("Bugs");
        test = new JMenu("Test");
        //config = new JMenu("Configurações");
        exit = new JMenu("Sair");


        subMenuBugCadastro = new JMenuItem("Cadastro");
        subMenuBugConsulta = new JMenuItem("Consulta");
        subMenuBugRelatorio = new JMenuItem("Relatorio");

        subMenuPlanoTest = new JMenuItem("Plano");
        subMenuCenarioTest = new JMenuItem("Cenario");
        subMenuCasoTest = new JMenuItem("Caso");
        subMenuCasoDetalhadoTest = new JMenuItem("Caso Detalhado");

//        subMenuConfig = new JMenuItem("Cadastrar");
//        subMenuConfig2 = new JMenuItem("Editar");

        imageIcon = new ImageIcon("src/main/resources/images/backend_opaco.png");
        image = new JLabel();

    }
}
