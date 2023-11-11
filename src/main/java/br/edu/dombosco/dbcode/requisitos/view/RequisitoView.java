package br.edu.dombosco.dbcode.requisitos.view;

import br.edu.dombosco.dbcode.requisitos.controller.RequisitosController;
import br.edu.dombosco.dbcode.requisitos.model.Requisito;
import lombok.extern.slf4j.Slf4j;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.File;

@Slf4j
public class RequisitoView extends JPanel {
    private JMenu requisitos, jMenu2;
    private JTextField especificationField, fieldTittle, findFiend, ucField;
    private JRadioButton functional;
    private ButtonGroup funcionalGroup;
    private JButton findButton,editButton, fileButton,fileButtonUC, printButton, removeButton,saveButon;
    private JLabel jLabel2, jLabel3, jLabel4, jLabel5, jLabel6;
    private JMenuBar jMenuBar1;
    private JScrollPane jScrollPaneTableInternal, jScrollPaneTable, jScrollPane3;
    private JTable jTable1;
    private JTextArea jTextArea2;
    private JRadioButton notFunctionl;
    private JTextArea textAreaDescription;
    private DefaultTableModel model;
    private RequisitosController requisitosController;
    private Long id;

    public RequisitoView(RequisitosController requisitosController) {
        this.requisitosController = requisitosController;
        initComponents();
        setPosition();
        addComponents();
        setListeners();
        initpopulateTable();
        updateUI();
    }

    private void initpopulateTable() {
        var requisitos = requisitosController.buscar10Primeiros();
        if(requisitos != null && !requisitos.isEmpty()){
            requisitos.forEach(this::populateTable);
        }
    }

    private void populateTable(Requisito requisito) {
        model.addRow( new Object[]{
                        requisito.getId(),
                        requisito.getNome(),
                        requisito.getQualificacao(),
                        requisito.getDescricao(),
                        requisito.getFile_especificacao(),
                        requisito.getFile_desenho()
                }
        );
    }

    public void updateUI() {
        validate();
        repaint();
    }
    private void addComponents(){
        funcionalGroup.add(functional);
        funcionalGroup.add(notFunctionl);
        add(jLabel2);
        add(fieldTittle);
        add(jLabel3);
        add(jLabel4);


        add(fileButton);
        add(fileButtonUC);
        add(especificationField);
        add(ucField);
        add(findFiend);
        add(findButton);
        add(saveButon);
        add(functional);
        add(notFunctionl);
        add(editButton);
        add(removeButton);
        add(printButton);
        add(jScrollPaneTableInternal);
        //add(textAreaDescription);
        add(jScrollPane3);
    }


    private void setPosition() {
        jScrollPaneTableInternal.setBounds(20, 290, 740, 250);
        jLabel2.setBounds(10, 20, 90, 18);
        jLabel3.setBounds(10, 70, 90, 18);
        fieldTittle.setBounds(110, 20, 650, 24);
        jScrollPane3.setBounds(110, 70, 274, 96);
        textAreaDescription.setBounds(110, 70, 274, 96);
        fileButton.setBounds(400, 70, 100, 24);
        fileButtonUC.setBounds(400, 140, 100, 24);
        especificationField.setBounds(520, 70, 240, 24);
        ucField.setBounds(520, 140, 240, 24);
        findFiend.setBounds(20, 250, 630, 24);
        findButton.setBounds(670, 250, 90, 24);
        jScrollPaneTable.setBounds(10, 240, 760, 310);
        jLabel4.setBounds(10, 200, 90, 20);
        saveButon.setBounds(670, 200, 90, 24);
        functional.setBounds(100, 200, 110, 22);
        notFunctionl.setBounds(260, 200, 140, 22);
        editButton.setBounds(230, 560, 80, 24);
        removeButton.setBounds(370, 560, 90, 24);
        printButton.setBounds(520, 560, 100, 24);
        jLabel5.setBounds(410, 120, 140, 18);
        jLabel6.setBounds(400, 50, 180, 18);

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
            if(findFiend.getText().isEmpty()){
                initpopulateTable();
                findFiend.setText("");
                return;
            }
            var requisitos = requisitosController.buscarPorTitulo(findFiend.getText());
            if(requisitos != null && !requisitos.isEmpty()){
                clearTable();
                requisitos.forEach(this::populateTable);
            }else {
                try {
                    var requisito = requisitosController.findRequisitoModelById(Long.parseLong(findFiend.getText()));
                    if(requisito != null){
                        clearTable();
                        populateTable(requisito);
                    }

                }catch (Exception ex){
                    JOptionPane.showMessageDialog(null, "Nenhum requisito encontrado!");
                }
            }
            findFiend.setText("");
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
        setSize(850, 650);
        setLayout(null);

        jLabel2 = new JLabel();
        jLabel3 = new JLabel();
        fieldTittle = new JTextField();
        textAreaDescription = new JTextArea();
        jScrollPane3 = new JScrollPane(textAreaDescription);
        fileButton = new JButton();
        fileButtonUC = new JButton();
        especificationField = new JTextField();
        ucField = new JTextField();
        findFiend = new JTextField();
        findButton = new JButton();
        jScrollPaneTable = new JScrollPane();
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
        jMenuBar1 = new JMenuBar();
        requisitos = new JMenu();
        jMenu2 = new JMenu();
        model = new DefaultTableModel(new Object [][] {},
                new String [] {"id", "Nome Requisito", "Qualificação","Descrição", "Anexos Especificação","Anexo Desenho UC"});
        funcionalGroup = new ButtonGroup();



        jTable1 = new JTable();

        jTable1.setModel(model);

        jScrollPaneTableInternal = new JScrollPane(jTable1);

        jLabel2.setText("Nome/Titulo:");
        jLabel3.setText("Descrição:");

        textAreaDescription.setLineWrap(true);
        textAreaDescription.setWrapStyleWord(true);

        jMenuBar1.add(jMenu2);



        fileButton.setText("Arquivo");
        fileButtonUC.setText("Arquivo");
        findButton.setText("Buscar");
        jTextArea2.setColumns(20);
        jTextArea2.setRows(5);

        requisitos.setText("File");
        jMenuBar1.add(requisitos);
        jLabel4.setText("Qualificação:");
        saveButon.setText("Salvar");
        functional.setText("Funcional");
        notFunctionl.setText("Não Funcional");
        editButton.setText("Editar");
        removeButton.setText("Excluir");
        printButton.setText("Imprimir");
        jLabel5.setText("Anexar Desenho UC");
        jLabel6.setText("Anexar Especificação");
        jMenu2.setText("Edit");

    }

}
