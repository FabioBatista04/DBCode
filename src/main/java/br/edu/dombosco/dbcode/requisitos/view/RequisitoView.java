package br.edu.dombosco.dbcode.requisitos.view;

import br.edu.dombosco.dbcode.accessManagment.model.Profile;
import br.edu.dombosco.dbcode.accessManagment.model.User;
import br.edu.dombosco.dbcode.accessManagment.view.HomeView;
import br.edu.dombosco.dbcode.requisitos.controller.RequisitosController;
import br.edu.dombosco.dbcode.requisitos.model.Requisito;
import lombok.extern.slf4j.Slf4j;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.File;
import java.io.FileWriter;

@Slf4j
public class RequisitoView extends JPanel {
    private JMenu requisitos, jMenu2;
    private JTextField especificationField, fieldTittle, findFiend, ucField;
    private JRadioButton functional;
    private ButtonGroup funcionalGroup;
    private JButton findButton,editButton, fileButton,fileButtonUC, genereteCSVFileButton, removeButton,saveButon;
    private JLabel titleLabel, descriptionLabel, QualificationLabel, ucLabel, specificationLabel;
    private JMenuBar jMenuBar1;
    private JScrollPane jScrollPaneTableInternal, jScrollPaneTable, jScrollPane3;
    private JTable jTable1;
    private JTextArea jTextArea2;
    private JRadioButton notFunctionl;
    private JTextArea textAreaDescription;
    private DefaultTableModel model;
    private RequisitosController requisitosController;
    private Long id;
    private HomeView homeView;

    public RequisitoView(HomeView homeView) {
        this.homeView = homeView;
        this.requisitosController = homeView.getRequisitosController();
        initComponents();
        setPosition();
        addComponents();
        setListeners();
        initpopulateTable();
        updateUI();
    }

    private void initpopulateTable() {
        var requisitos = requisitosController.buscar10Primeiros(homeView.getProjeto());
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
        add(titleLabel);
        add(fieldTittle);
        add(descriptionLabel);
        add(QualificationLabel);
        add(ucLabel);
        add(specificationLabel);


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
        add(genereteCSVFileButton);
        add(jScrollPaneTableInternal);
        //add(textAreaDescription);
        add(jScrollPane3);
    }


    private void setPosition() {
        jScrollPaneTableInternal.setBounds(20, 290, 740, 250);
        titleLabel.setBounds(10, 20, 90, 18);
        descriptionLabel.setBounds(10, 70, 90, 18);
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
        QualificationLabel.setBounds(10, 200, 110, 20);
        saveButon.setBounds(670, 200, 90, 24);
        functional.setBounds(100, 200, 130, 22);
        notFunctionl.setBounds(330, 200, 160, 22);
        editButton.setBounds(230, 560, 80, 24);
        removeButton.setBounds(370, 560, 90, 24);
        genereteCSVFileButton.setBounds(520, 560, 100, 24);
        ucLabel.setBounds(400, 110, 140, 18);
        specificationLabel.setBounds(400, 50, 180, 18);

    }

    private void setListeners() {
        saveButon.addActionListener(e -> {
           if(isAutorizado(homeView.getUser())) {
               var requisito = Requisito.builder()
                       .id(id)
                       .nome(fieldTittle.getText())
                       .descricao(textAreaDescription.getText())
                       .file_desenho(ucField.getText())
                       .file_especificacao(especificationField.getText())
                       .qualificacao(functional.isSelected() ? "Funcional" : "Não Funcional")
                       .projeto(homeView.getProjeto())
                       .build();
               if (requisito.containsNullFields().isEmpty()) {
                   try {
                       var retorno = requisitosController.salvar(requisito);
                       if (retorno != null) {
                           clearTable();
                           clearFields();
                           addToTable(retorno);
                       }

                   } catch (Exception ex) {
                       JOptionPane.showMessageDialog(null, "Titulo já cadastrado!");
                   }

               } else {
                   JOptionPane.showMessageDialog(null, "Preencha os campos:" + requisito.containsNullFields(), "Campos Obrigatórios", JOptionPane.WARNING_MESSAGE);
               }
           }else {
               JOptionPane.showMessageDialog(null, "Você não tem permissão para salvar!");
           }
        });

        findButton.addActionListener(e -> {
            if(findFiend.getText().isEmpty()){
                clearTable();
                initpopulateTable();
                findFiend.setText("");
                return;
            }
            var requisitos = requisitosController.buscarPorTitulo(findFiend.getText(), homeView.getProjeto().getId());
            if(requisitos != null && !requisitos.isEmpty()){
                clearTable();
                requisitos.forEach(this::populateTable);
            }else {
                try {
                    var requisito = requisitosController.findRequisitoModelById(Long.parseLong(findFiend.getText()), homeView.getProjeto().getId());
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
            if(isAutorizado(homeView.getUser())) {

                int selectedRow = jTable1.getSelectedRow();
                if (selectedRow >= 0) {
                    Long objectId = (Long) jTable1.getModel().getValueAt(selectedRow, 0);
                    var retorno = requisitosController.findRequisitoModelById(objectId, homeView.getProjeto().getId());
                    if (retorno != null) {
                        clearTable();
                        model.addRow(new Object[]{
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
            }else {
                JOptionPane.showMessageDialog(null, "Você não tem permissão para editar!");
            }
        });

        removeButton.addActionListener(e -> {
            if(!isAutorizado(homeView.getUser())){
                JOptionPane.showMessageDialog(null, "Você não tem permissão para excluir!");
                return;
            }
            int selectedRow = jTable1.getSelectedRow();
            if(selectedRow >= 0){
                Long objectId = (Long) jTable1.getModel().getValueAt(selectedRow, 0);
                requisitosController.delete(objectId);
                removeElementFromTable(selectedRow);

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

        genereteCSVFileButton.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY); // Para selecionar apenas arquivos
            int result = fileChooser.showOpenDialog(RequisitoView.this);

            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                generateCSVFile(selectedFile.getAbsolutePath());
            }
        });
    }

    private void addToTable(Requisito retorno) {
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

    private void removeElementFromTable(int selectedRow) {
        model.removeRow(selectedRow);
    }

    private void generateCSVFile(String absolutePath) {
        try {
            FileWriter writer = new FileWriter(absolutePath + "/requisitos.csv");
            for (int i = 0; i < jTable1.getRowCount(); i++) {
                // Escreve cada linha no arquivo CSV
                for (int j = 0; j < jTable1.getColumnCount(); j++) {
                    writer.write(jTable1.getValueAt(i, j).toString() + ",");
                }
                writer.write("\n");
            }

            // Fecha o fluxo de caracteres
            writer.close();
        }catch (Exception e){
            log.error("Erro ao gerar arquivo CSV", e);
        }
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

        titleLabel = new JLabel();
        descriptionLabel = new JLabel();
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
        QualificationLabel = new JLabel();
        saveButon = new JButton();
        functional = new JRadioButton();
        notFunctionl = new JRadioButton();
        editButton = new JButton();
        removeButton = new JButton();
        genereteCSVFileButton = new JButton();
        ucLabel = new JLabel();
        specificationLabel = new JLabel();
        jMenuBar1 = new JMenuBar();
        requisitos = new JMenu();
        jMenu2 = new JMenu();
        model = new DefaultTableModel(new Object [][] {},
                new String [] {"id", "Nome Requisito", "Qualificação","Descrição", "Anexos Especificação","Anexo Desenho UC"});
        funcionalGroup = new ButtonGroup();



        jTable1 = new JTable();

        jTable1.setModel(model);

        jScrollPaneTableInternal = new JScrollPane(jTable1);

        titleLabel.setText("Nome/Titulo:");
        descriptionLabel.setText("Descrição:");

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
        QualificationLabel.setText("Qualificação:");
        saveButon.setText("Salvar");
        functional.setText("Funcional");
        notFunctionl.setText("Não Funcional");
        editButton.setText("Editar");
        removeButton.setText("Excluir");
        genereteCSVFileButton.setText("CSV");
        ucLabel.setText("Anexar Desenho UC");
        specificationLabel.setText("Anexar Especificação");
        jMenu2.setText("Edit");

    }
    private boolean isAutorizado(User user) {
       return user.getProfile().equals(Profile.ADMIN) || user.getProfile().equals(Profile.ANALYST);
    }
}
