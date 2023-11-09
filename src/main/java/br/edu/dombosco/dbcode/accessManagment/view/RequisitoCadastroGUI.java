package br.edu.dombosco.dbcode.accessManagment.view;

import javax.swing.*;
import java.awt.*;

public class RequisitoCadastroGUI {

    RequisitoCadastroGUI() {
        // Create the frame.
        JFrame frame = new JFrame("Cadastro de Requisito");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLayout(null);

        // Create components.
        JLabel titleLabel = new JLabel("CADASTRO DE REQUISITO");
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));

        JTextField nomeField = new JTextField();
        JTextArea descricaoArea = new JTextArea();

        ButtonGroup qualificacaoGroup = new ButtonGroup();
        JRadioButton funcionalButton = new JRadioButton("Funcional");
        JRadioButton naoFuncionalButton = new JRadioButton("Não funcional");

        qualificacaoGroup.add(funcionalButton);
        qualificacaoGroup.add(naoFuncionalButton);

        JButton anexarEspecificacaoButton = new JButton("ARQUIVO");
        JButton anexarDesenhoUCButton = new JButton("ARQUIVO");

        JButton salvarButton = new JButton("SALVAR");
        JButton editarButton = new JButton("EDITAR");
        JButton excluirButton = new JButton("EXCLUIR");
        JButton imprimirButton = new JButton("IMPRIMIR");

        String[] columnNames = {"Num", "Nome do Requisito", "Qualificação", "Anexos Especificação", "Anexo desenho UC"};
        Object[][] data = {};
        JTable table = new JTable(data, columnNames);

        JScrollPane scrollPane = new JScrollPane(table);
        table.setPreferredScrollableViewportSize(new Dimension(500, 70));
        table.setFillsViewportHeight(true);

        // Position components.
        titleLabel.setBounds(200, 10, 400, 30);
        nomeField.setBounds(50, 50, 200, 30);
        descricaoArea.setBounds(50, 90, 300, 100);

        funcionalButton.setBounds(50, 200, 100, 30);
        naoFuncionalButton.setBounds(160, 200, 100, 30);

        anexarEspecificacaoButton.setBounds(600, 50, 100, 30);
        anexarDesenhoUCButton.setBounds(600, 90, 100, 30);

        salvarButton.setBounds(600, 150, 100, 30);

        scrollPane.setBounds(50, 250, 700, 200);

        editarButton.setBounds(50, 460, 100, 30);
        excluirButton.setBounds(160, 460, 100, 30);
        imprimirButton.setBounds(270, 460, 100, 30);

        // Add components to the frame.
        frame.add(titleLabel);
        frame.add(nomeField);
        frame.add(descricaoArea);
        frame.add(funcionalButton);
        frame.add(naoFuncionalButton);
        frame.add(anexarEspecificacaoButton);
        frame.add(anexarDesenhoUCButton);
        frame.add(salvarButton);
        frame.add(scrollPane);
        frame.add(editarButton);
        frame.add(excluirButton);
        frame.add(imprimirButton);

        // Display the frame.
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}