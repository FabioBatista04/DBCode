package br.edu.dombosco.dbcode.accessManagment.view;

import br.edu.dombosco.dbcode.requisitos.controller.RequisitosController;
import br.edu.dombosco.dbcode.requisitos.model.RequisitoModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RequisitoView  extends JFrame {

    // Constructor for the form
    public RequisitoView() {
        // Set the title of the window
        setTitle("Cadastro de Requisito");

        // Set the layout
        setLayout(new BorderLayout());

        // Create components
        JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel formPanel = new JPanel(new GridLayout(0, 1));
        JPanel tablePanel = new JPanel(new BorderLayout());
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        // Form fields
        JTextField nameField = new JTextField(20);
        JTextArea descriptionArea = new JTextArea(5, 20);
        JRadioButton functionalRadio = new JRadioButton("Funcional");
        JRadioButton nonFunctionalRadio = new JRadioButton("Não Funcional");
        ButtonGroup qualificationGroup = new ButtonGroup();
        qualificationGroup.add(functionalRadio);
        qualificationGroup.add(nonFunctionalRadio);

        // Table
        String[] columnNames = {"Id", "Nome do Requisito", "Qualificação", "Descrição"};
        Object[][] data = {};
        JTable requirementTable = new JTable(data, columnNames);
        JScrollPane tableScrollPane = new JScrollPane(requirementTable);

        // Buttons
        JButton editButton = new JButton("EDITAR");
        JButton deleteButton = new JButton("EXCLUIR");
        JButton printButton = new JButton("IMPRIMIR");
        JButton saveButton = new JButton("SALVAR");

        // Add components to the form panel
        formPanel.add(new JLabel("NOME/TITULO"));
        formPanel.add(nameField);
        formPanel.add(new JLabel("DESCRIÇÃO"));
        formPanel.add(descriptionArea);
        formPanel.add(functionalRadio);
        formPanel.add(nonFunctionalRadio);

        // Add buttons to button panel
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(printButton);

        // Add table to table panel
        tablePanel.add(tableScrollPane, BorderLayout.CENTER);

        // Add form and buttons to the main panel
        mainPanel.add(formPanel, BorderLayout.NORTH);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Add all panels to the frame
        add(mainPanel, BorderLayout.NORTH);
        add(tablePanel, BorderLayout.CENTER);
        add(saveButton, BorderLayout.SOUTH);

        // Set the default close operation and size
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);



        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                RequisitosController controller = new RequisitosController();
                controller.salvar(RequisitoModel.builder()
                                .nome(nameField.getText())
                                .descricao(descriptionArea.getText())
                                .qualificacao(functionalRadio.isSelected() ?  "Funcional" : "Não funcional")
                                .file_desenho(null)
                        .build());
            }
        });

//        JButton editButton = new JButton("EDITAR");
//        editButton.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                editRequisito();
//            }
//        });
//
//        JButton deleteButton = new JButton("EXCLUIR");
//        deleteButton.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                deleteRequisito();
//            }
//        });
//
//        JButton searchButton = new JButton("BUSCAR");
//        searchButton.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                searchRequisito();
//            }
//        });
    }
}

