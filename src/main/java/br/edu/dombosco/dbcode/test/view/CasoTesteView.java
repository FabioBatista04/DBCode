
package br.edu.dombosco.dbcode.test.view;

import br.edu.dombosco.dbcode.accessManagment.model.Profile;
import br.edu.dombosco.dbcode.accessManagment.view.HomeView;
import br.edu.dombosco.dbcode.test.model.Caso;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.Objects;

public class CasoTesteView extends JPanel {

    private JButton changeButton;
    private JLabel complexText;
    private JButton deleteButton;
    private JTextArea descriptionField;
    private JLabel descriptionText;
    private JComboBox<String> dificultField;
    private JButton findButton;
    private JTextField findField;
    private JScrollPane jScrollPane3;
    private JScrollPane jScrollPane5;
    private JButton listButton;
    private JTextField nameField;
    private JLabel nameText;
    private JTextField preRequisitoField;
    private JLabel preRequisitosText;
    private JButton saveButton;
    private JButton selectButton;
    private JTable table;
    private HomeView homeView;
    private Long idCaso;

    public CasoTesteView(HomeView homeView) {
        this.homeView = homeView;
        initComponents();
        preencheTable();
    }

    private void preencheTable() {
        var casos = homeView.getCasoController().findFirst10(homeView.getCenario().getId());
        clearTable();
        if(casos != null && !casos.isEmpty()){
            casos.forEach(this::addToTable);
        }
    }

    private void addToTable(Caso caso) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.addRow(new Object[]{
                caso.getId(),
                caso.getNome(),
                caso.getPreRequisitos(),
                caso.getComplexidade()
        });

    }

    private void clearTable() {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
    }

    private void initComponents() {
        setSize(800,650);
        setLayout(null);
        complexText = new JLabel();
        findField = new JTextField();
        nameField = new JTextField();
        nameText = new JLabel();
        descriptionText = new JLabel();
        changeButton = new JButton();
        deleteButton = new JButton();
        findButton = new JButton();
        listButton = new JButton();
        jScrollPane3 = new JScrollPane();
        descriptionField = new JTextArea();
        dificultField = new JComboBox<>();
        preRequisitosText = new JLabel();
        preRequisitoField = new JTextField();
        jScrollPane5 = new JScrollPane();
        table = new JTable();
        selectButton = new JButton();
        saveButton = new JButton();
        idCaso = null;



        complexText.setText("Complexidade:");
        add(complexText);
        complexText.setBounds(490, 110, 110, 16);
        add(findField);
        findField.setBounds(160, 360, 600, 22);

        add(nameField);
        nameField.setBounds(40, 50, 720, 22);

        nameText.setText("Nome do Caso de Teste:");
        add(nameText);
        nameText.setBounds(40, 30, 160, 16);

        descriptionText.setText("Descricao: ");
        add(descriptionText);
        descriptionText.setBounds(40, 200, 130, 16);

        changeButton.setText("Alterar");
        add(changeButton);
        changeButton.setBounds(200, 540, 80, 23);
        changeButton.addActionListener(e -> {
            if(isNotAutorized(homeView.getUser().getProfile())){
                JOptionPane.showMessageDialog(this, "Você não tem permissão para alterar um caso de teste");
                return;
            }
            var selectedRow = table.getSelectedRow();
            if(selectedRow != -1){
                var id = (Long) table.getModel().getValueAt(selectedRow, 0);
                var caso = homeView.getCasoController().buscarPorId(id, homeView.getCenario().getId());
                if(caso != null){
                    idCaso = caso.getId();
                    nameField.setText(caso.getNome());
                    descriptionField.setText(caso.getDescricao());
                    preRequisitoField.setText(caso.getPreRequisitos());
                    dificultField.setSelectedItem(caso.getComplexidade());
                }
            }
        });

        deleteButton.setText("Excluir");
        add(deleteButton);
        deleteButton.setBounds(350, 540, 80, 23);
        deleteButton.addActionListener(e -> {
            if(isNotAutorized(homeView.getUser().getProfile())){
                JOptionPane.showMessageDialog(this, "Você não tem permissão para excluir um caso de teste");
                return;
            }
            var selectedRow = table.getSelectedRow();
            if(selectedRow != -1){
                var id = (Long) table.getModel().getValueAt(selectedRow, 0);
                if(homeView.getCaso() != null && homeView.getCaso().getId().equals(id)){
                    homeView.setCaso(null);
                }
                homeView.getCasoController().delete(id);
                removeFromTable(selectedRow);
            }
        });

        findButton.setText("Pesquisar");
        add(findButton);
        findButton.setBounds(40, 360, 100, 23);
        findButton.addActionListener(e -> {
            if(findField.getText().isEmpty()){
                return;
            }
            var nome = findField.getText();
            var casos = homeView.getCasoController().findByNome(nome, homeView.getCenario().getId());
            if(casos != null && !casos.isEmpty()){
                clearTable();
                casos.forEach(this::addToTable);
            }else {
                try {
                    var id = Long.parseLong(nome);
                    var plano = homeView.getCasoController().buscarPorId(id, homeView.getCenario().getId());
                    if(plano != null){
                        clearTable();
                        addToTable(plano);
                    }
                }catch (Exception ex){
                    JOptionPane.showMessageDialog(this, "Nenhum Caso de Teste encontrado", "Nenhum Caso de Teste encontrado", JOptionPane.ERROR_MESSAGE);
                }
            }
            findField.setText("");
        });

        listButton.setText("Listar");
        add(listButton);
        listButton.setBounds(40, 540, 90, 23);
        listButton.addActionListener(e -> {
            var planos = homeView.getCasoController().findFirst10(homeView.getCenario().getId());
            if(planos != null && !planos.isEmpty()){
                clearTable();
                planos.forEach(this::addToTable);
            }
        });

        descriptionField.setColumns(20);
        descriptionField.setRows(5);
        jScrollPane3.setViewportView(descriptionField);

        add(jScrollPane3);
        jScrollPane3.setBounds(40, 220, 720, 100);

        dificultField.setModel(new DefaultComboBoxModel<>(new String[] {"Simples", "Moderada", "Difícil"}));
        add(dificultField);
        dificultField.setBounds(490, 130, 170, 22);

        preRequisitosText.setText("Pré-requisitos");
        add(preRequisitosText);
        preRequisitosText.setBounds(40, 110, 170, 16);
        add(preRequisitoField);
        preRequisitoField.setBounds(40, 130, 280, 22);

        table.setModel(new DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Id", "Nome", "Pré-Requisito", "Complexidade"
            }
        ));
        jScrollPane5.setViewportView(table);

        add(jScrollPane5);
        jScrollPane5.setBounds(40, 390, 720, 130);

        selectButton.setText("Selecionar");
        selectButton.setActionCommand("salvar");
        selectButton.addActionListener(evt -> {
            var selectedRow = table.getSelectedRow();
            if(selectedRow != -1){
                var id = (Long) table.getModel().getValueAt(selectedRow, 0);
                var caso = homeView.getCasoController().buscarPorId(id, homeView.getCenario().getId());
                if(caso != null){
                    homeView.setCaso(caso);
                    homeView.addPanel(new CasoDetalhadoView(homeView));
                }
            }
        });
        add(selectButton);
        selectButton.setBounds(650, 540, 110, 23);

        saveButton.setText("Salvar");
        saveButton.setActionCommand("salvar");
        saveButton.addActionListener(e -> {
            if(isNotAutorized(homeView.getUser().getProfile())){
                JOptionPane.showMessageDialog(this, "Você não tem permissão para salvar um caso de teste");
                return;
            }
            var caso = Caso.builder()
                    .id(idCaso)
                    .nome(nameField.getText())
                    .descricao(descriptionField.getText())
                    .preRequisitos(preRequisitoField.getText())
                    .complexidade(Objects.requireNonNull(dificultField.getSelectedItem()).toString())
                    .cenario(homeView.getCenario())
                    .build();

            if(caso.containsNullFields().isEmpty()){
                try {
                    var casoSalvo = homeView.getCasoController().save(caso);
                    if(casoSalvo != null){
                        clearTable();
                        clearFields();
                        addToTable(casoSalvo);
                    }
                }catch (Exception ex){
                    JOptionPane.showMessageDialog(this, "Erro ao salvar caso de teste");
                }
            }else{
                JOptionPane.showMessageDialog(this, "Preencha os campos: " + caso.containsNullFields(), "Campos obrigatórios", JOptionPane.WARNING_MESSAGE);
            }
        });
        add(saveButton);
        saveButton.setBounds(510, 540, 80, 23);
    }

    private boolean isNotAutorized(Profile profile) {
        return !profile.equals(Profile.ADMIN) && !profile.equals(Profile.TEST);
    }

    private void removeFromTable(int selectedRow) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.removeRow(selectedRow);
    }

    private void clearFields() {
        idCaso = null;
        nameField.setText("");
        descriptionField.setText("");
        preRequisitoField.setText("");
        dificultField.setSelectedIndex(0);
    }

}
