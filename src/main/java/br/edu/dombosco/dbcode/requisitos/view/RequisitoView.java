/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package br.edu.dombosco.dbcode.requisitos.view;

import br.edu.dombosco.dbcode.requisitos.controller.RequisitosController;
import br.edu.dombosco.dbcode.requisitos.model.Requisito;
import lombok.extern.slf4j.Slf4j;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.File;

@Slf4j
public class RequisitoView extends JFrame {
    private JMenu Requisitos, jMenu2;
    private JTextField especificationField, fieldTittle, findFiend, ucField,JTextField;
    private JRadioButton functional;
    private ButtonGroup funcionalGroup;
    private JButton findButton,editButton, fileButton,fileButtonUC, printButton, removeButton,saveButon;
    private JLabel jLabel1,jLabel2, jLabel3, jLabel4, jLabel5, jLabel6;
    private JMenuBar jMenuBar1;
    private JPanel jPanel1;
    private JScrollPane jScrollPane1, jScrollPane2, jScrollPane3;
    private JTable jTable1;
    private JTextArea jTextArea2;
    private JRadioButton notFunctionl;
    private JTextArea textAreaDescription;
    private DefaultTableModel model;
    private RequisitosController requisitosController;
    private Long id;

    public RequisitoView(RequisitosController requisitosController) {
        this.requisitosController = requisitosController;
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initComponents();
        setListeners();
    }

    private void setListeners() {
        saveButon.addActionListener(e -> {
            var requisito = Requisito.builder()
                    .id(id)
                    .nome(fieldTittle.getText())
                    .descricao(textAreaDescription.getText())
                    .file_desenho(ucField.getText())
                    .file_especificacao(especificationField.getText())
                    .qualificacao(functional.isSelected() ? "Funcional" : "Não Funcional")
                    .build();

            var retorno = requisitosController.salvar(requisito);
            if(retorno != null){
                clearTable();
                model.addRow( new Object[]{
                        retorno.getId(),
                        retorno.getNome(),
                        retorno.getQualificacao(),
                        retorno.getDescricao(),
                        retorno.getFile_especificacao(),
                        retorno.getFile_desenho()
                        }
                );
            }
            clearFields();
        });

        findButton.addActionListener(e -> {
            log.info(findFiend.getText());
            try{

                Long id = Long.valueOf(findFiend.getText());
                log.info(String.valueOf(id));
                var retorno = requisitosController.findRequisitoModelById(id);
                log.info("retorno {}", retorno);
                for (int i = model.getRowCount() - 1; i >= 0; i--) {
                    model.removeRow(i);
                }
                if(retorno != null){
                    model.addRow( new Object[]{
                                    retorno.getId(),
                                    retorno.getNome(),
                                    retorno.getQualificacao(),
                                    retorno.getDescricao(),
                                    retorno.getFile_especificacao(),
                                    retorno.getFile_desenho()
                            }
                    );
                }
            }catch (Exception ex){
                log.error(ex.getMessage());
            }


        });
        editButton.addActionListener(e -> {
            int selectedRow = jTable1.getSelectedRow();
            if(selectedRow >= 0){
                Long objectId = (Long) jTable1.getModel().getValueAt(selectedRow, 0);
                var retorno = requisitosController.findRequisitoModelById(objectId);
                if(retorno != null){
                    clearTable();
                    model.addRow( new Object[]{
                                    retorno.getId(),
                                    retorno.getNome(),
                                    retorno.getQualificacao(),
                                    retorno.getDescricao(),
                                    retorno.getFile_especificacao(),
                                    retorno.getFile_desenho()
                            }
                    );
                    id = retorno.getId();
                    fieldTittle.setText(retorno.getNome());
                    textAreaDescription.setText(retorno.getDescricao());
                    ucField.setText(retorno.getFile_desenho());
                    especificationField.setText(retorno.getFile_especificacao());
                    if (retorno.getQualificacao().equals("Funcional")) {
                        functional.setSelected(true);
                    } else {
                        notFunctionl.setSelected(true);
                    }

                }
            }



        });
        removeButton.addActionListener(e -> {
            int selectedRow = jTable1.getSelectedRow();
            if(selectedRow >= 0){
                Long objectId = (Long) jTable1.getModel().getValueAt(selectedRow, 0);
                requisitosController.delete(objectId);
                clearTable();
            }
        });

        fileButton.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY); // Para selecionar apenas arquivos
            int result = fileChooser.showOpenDialog(RequisitoView.this);

            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                especificationField.setText(selectedFile.getAbsolutePath());
            }
        });

        fileButtonUC.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY); // Para selecionar apenas arquivos
            int result = fileChooser.showOpenDialog(RequisitoView.this);

            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                ucField.setText(selectedFile.getAbsolutePath());
            }
        });
    }

    private void clearTable() {
        for (int i = model.getRowCount() - 1; i >= 0; i--) {
            model.removeRow(i);
        }
    }

    private void clearFields() {
        id = null;
        fieldTittle.setText("");
        textAreaDescription.setText("");
        ucField.setText("");
        especificationField.setText("");
        funcionalGroup.clearSelection();
    }

    private void initComponents() {

        jPanel1 = new JPanel();
        jScrollPane1 = new JScrollPane();
        jTable1 = new JTable();
        jLabel2 = new JLabel();
        jLabel3 = new JLabel();
        fieldTittle = new JTextField();
        jScrollPane3 = new JScrollPane();
        textAreaDescription = new JTextArea();
        fileButton = new JButton();
        fileButtonUC = new JButton();
        especificationField = new JTextField();
        ucField = new JTextField();
        findFiend = new JTextField();
        findButton = new JButton();
        jScrollPane2 = new JScrollPane();
        jTextArea2 = new JTextArea();
        jLabel4 = new JLabel();
        saveButon = new JButton();
        functional = new JRadioButton();
        notFunctionl = new JRadioButton();
        editButton = new JButton();
        removeButton = new JButton();
        printButton = new JButton();
        jLabel5 = new JLabel();
        jLabel6 = new JLabel();
        jLabel1 = new JLabel();
        jMenuBar1 = new JMenuBar();
        Requisitos = new JMenu();
        jMenu2 = new JMenu();
        model = new DefaultTableModel(new Object [][] {},
                new String [] {"id", "Nome Requisito", "Qualificação","Descrição", "Anexos Especificação","Anexo Desenho UC"});
        funcionalGroup = new ButtonGroup();
        funcionalGroup.add(functional);
        funcionalGroup.add(notFunctionl);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(null);

        jTable1.setModel(model);

        jScrollPane1.setViewportView(jTable1);

        jPanel1.add(jScrollPane1);
        jScrollPane1.setBounds(20, 290, 760, 250);

        jLabel2.setText("Nome/Titulo");
        jPanel1.add(jLabel2);
        jLabel2.setBounds(10, 20, 90, 18);

        jLabel3.setText("Descrição");
        jPanel1.add(jLabel3);
        jLabel3.setBounds(10, 70, 90, 18);

        jPanel1.add(fieldTittle);
        fieldTittle.setBounds(110, 20, 650, 24);

        textAreaDescription.setColumns(20);
        textAreaDescription.setRows(5);
        jScrollPane3.setViewportView(textAreaDescription);

        jPanel1.add(jScrollPane3);
        jScrollPane3.setBounds(110, 70, 274, 96);

        fileButton.setText("Arquivo");

        jPanel1.add(fileButton);
        fileButton.setBounds(400, 70, 100, 24);

        fileButtonUC.setText("Arquivo");
        jPanel1.add(fileButtonUC);
        fileButtonUC.setBounds(400, 140, 100, 24);
        jPanel1.add(especificationField);
        especificationField.setBounds(520, 70, 240, 24);
        jPanel1.add(ucField);
        ucField.setBounds(520, 140, 240, 24);

        jPanel1.add(findFiend);
        findFiend.setBounds(20, 250, 650, 24);

        findButton.setText("Buscar");
        jPanel1.add(findButton);
        findButton.setBounds(680, 250, 90, 24);

        jTextArea2.setColumns(20);
        jTextArea2.setRows(5);
        jScrollPane2.setViewportView(jTextArea2);

        jPanel1.add(jScrollPane2);
        jScrollPane2.setBounds(10, 240, 780, 310);

        jLabel4.setText("Qualificação");
        jPanel1.add(jLabel4);
        jLabel4.setBounds(10, 200, 90, 20);

        saveButon.setText("Salvar");
        jPanel1.add(saveButon);
        saveButon.setBounds(670, 200, 90, 24);

        functional.setText("Funcional");
        jPanel1.add(functional);
        functional.setBounds(100, 200, 110, 22);

        notFunctionl.setText("Não Funcional");
        jPanel1.add(notFunctionl);
        notFunctionl.setBounds(260, 200, 140, 22);

        editButton.setText("Editar");
        jPanel1.add(editButton);
        editButton.setBounds(230, 560, 80, 24);

        removeButton.setText("Excluir");
        jPanel1.add(removeButton);
        removeButton.setBounds(370, 560, 90, 24);

        printButton.setText("Imprimir");
        jPanel1.add(printButton);
        printButton.setBounds(520, 560, 100, 24);

        jLabel5.setText("Anexar Desenho UC");
        jPanel1.add(jLabel5);
        jLabel5.setBounds(410, 120, 140, 18);

        jLabel6.setText("Anexar Especificação");
        jPanel1.add(jLabel6);
        jLabel6.setBounds(400, 50, 180, 18);

        jLabel1.setIcon(new ImageIcon(getClass().getResource("/images/backend_opaco.png"))); // NOI18N
        jPanel1.add(jLabel1);
        jLabel1.setBounds(0, 0, 800, 600);

        Requisitos.setText("File");
        jMenuBar1.add(Requisitos);
        Requisitos.getAccessibleContext().setAccessibleName("Requisitos");

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);
        jMenu2.getAccessibleContext().setAccessibleName("Test");

        functional.setOpaque(false);
        notFunctionl.setOpaque(false);

        setJMenuBar(jMenuBar1);

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, GroupLayout.DEFAULT_SIZE, 800, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, GroupLayout.DEFAULT_SIZE, 597, Short.MAX_VALUE)
        );

        pack();
    }

}
