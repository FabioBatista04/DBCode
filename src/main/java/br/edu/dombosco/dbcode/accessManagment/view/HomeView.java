package br.edu.dombosco.dbcode.accessManagment.view;

import javax.swing.*;
import javax.swing.plaf.PanelUI;
import java.awt.*;

public class HomeView extends JFrame {

    public HomeView(){
        JFrame frame = new JFrame("DBCode");
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JTabbedPane tabbedPane = new JTabbedPane();

        JPanel panel1 = new JPanel(new BorderLayout());
        panel1.add(new JLabel("Conteudo Requisitos"));
        tabbedPane.addTab("Requisitos", panel1);

        JPanel panel2 = new JPanel(new BorderLayout());
        panel2.add(new JLabel("Conteudo Testes"));
        tabbedPane.addTab("Testes", panel2);

        JPanel panel3 = new JPanel(new BorderLayout());
        panel3.add(new JLabel("Conteudo Bugs"));
        tabbedPane.addTab("Bugs", panel3);

        JPanel panel4 = new JPanel(new BorderLayout());
        panel4.add(new JLabel("Conteudo Configurações"));
        tabbedPane.addTab("Configurações", panel4);

        frame.add(tabbedPane, BorderLayout.CENTER);

        JPanel footerPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton exitButton = new JButton("Sair");
        exitButton.addActionListener(e -> System.exit(0)); // Sai do programa ao clicar
        footerPanel.add(exitButton);

        frame.add(footerPanel, BorderLayout.PAGE_END);

        frame.setVisible(true);
    }
}
