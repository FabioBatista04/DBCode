package br.edu.dombosco.dbcode.test.view;

import br.edu.dombosco.dbcode.accessManagment.model.Profile;
import br.edu.dombosco.dbcode.accessManagment.view.HomeView;
import br.edu.dombosco.dbcode.test.controller.CenarioController;
import br.edu.dombosco.dbcode.test.model.Cenario;
import br.edu.dombosco.dbcode.test.model.Plano;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.Objects;

public class CenarioTesteView extends JPanel {

    private JLabel StatusText;
    private JButton changeButton;
    private JButton deleteButton;
    private JButton findButton;
    private JTextField findField;
    private JScrollPane jScrollPane1;
    private JButton listButton;
    private JTextField nameField;
    private JLabel nameText;
    private JButton saveButton;
    private JButton selectButton;
    private JComboBox<String> statusField;
    private JTable table;
    private JTextField timeField;
    private JLabel timeText;
    private CenarioController cenarioController;
    private Long idCenario;
    private HomeView homeView;
    private Plano plano;

    public CenarioTesteView(HomeView homeView) {
        this.homeView = homeView;
        this.plano = homeView.getPlano();
        this.cenarioController = homeView.getCenarioController();
        initComponents();
        initTable();
    }

    private void initTable() {
        clearFields();
        clearTable();
        var cenariosList = cenarioController.findFirst10(homeView.getPlano().getId());
        if(cenariosList != null && !cenariosList.isEmpty()){
            cenariosList.forEach(this::addToTable);
        }
    }

    private void initComponents() {
        setSize(800,650);
        setLayout(null);


        jScrollPane1 = new JScrollPane();
        table = new JTable();
        StatusText = new JLabel();
        findField = new JTextField();
        nameField = new JTextField();
        timeField = new JTextField();
        nameText = new JLabel();
        timeText = new JLabel();
        changeButton = new JButton();
        saveButton = new JButton();
        selectButton = new JButton();
        deleteButton = new JButton();
        findButton = new JButton();
        listButton = new JButton();
        statusField = new JComboBox<>();
        idCenario = null;

        table.setModel(new DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Id", "nome", "Tempo", "Status"
            }
        ));
        jScrollPane1.setViewportView(table);

        add(jScrollPane1);
        jScrollPane1.setBounds(40, 330, 720, 180);

        StatusText.setText("Status do Cenario de Teste:");
        add(StatusText);
        StatusText.setBounds(40, 220, 200, 20);
        add(findField);
        findField.setBounds(160, 290, 600, 22);


        add(nameField);
        nameField.setBounds(250, 50, 510, 22);
        add(timeField);
        timeField.setBounds(250, 130, 510, 22);

        nameText.setText("Nome do Cenario de Teste:");
        add(nameText);
        nameText.setBounds(40, 50, 200, 16);

        timeText.setText("Tempo do Cenario de Teste:");
        add(timeText);
        timeText.setBounds(40, 130, 200, 16);

        changeButton.setText("Alterar");
        add(changeButton);
        changeButton.setBounds(210, 540, 110, 23);
        changeButton.addActionListener(e -> {
            if(isNotAutorized(homeView.getUser().getProfile())){
                JOptionPane.showMessageDialog(null, "Você não tem permissão para alterar um cenário de teste", "Sem permissão", JOptionPane.ERROR_MESSAGE);
                return;
            }
            var selectedRow = table.getSelectedRow();
            if(selectedRow != -1){
               var id = (Long) table.getValueAt(selectedRow, 0);
               var cenario = cenarioController.buscarPorId(id, homeView.getPlano().getId());
               idCenario = cenario.getId();
               nameField.setText(cenario.getNome());
               timeField.setText(cenario.getTempo());
               statusField.setSelectedItem(cenario.getStatus());
            }
        });

        saveButton.setText("Salvar");
        saveButton.setActionCommand("salvar");
        saveButton.addActionListener(e -> {
            if(isNotAutorized(homeView.getUser().getProfile())){
                JOptionPane.showMessageDialog(null, "Você não tem permissão para salvar um cenário de teste", "Sem permissão", JOptionPane.ERROR_MESSAGE);
                return;
            }
            var cenario = Cenario.builder()
                    .id(idCenario)
                    .nome(nameField.getText())
                    .tempo(timeField.getText())
                    .status(Objects.requireNonNull(statusField.getSelectedItem()).toString())
                    .plano(homeView.getPlano())
                    .build();
            if(cenario.containsNullFields().isEmpty()){
               var cenarioSalvo = cenarioController.save(cenario);
               if(cenarioSalvo != null){
                   clearFields();
                   clearTable();
                   addToTable(cenarioSalvo);
               }
            } else {
                JOptionPane.showMessageDialog(null, "Preencha os campos: " + cenario.containsNullFields(), "Preencha Todos os Campos", JOptionPane.ERROR_MESSAGE);
            }
        });
        add(saveButton);
        saveButton.setBounds(510, 540, 80, 23);

        selectButton.setText("Selecionar");
        selectButton.addActionListener(evt -> {
            var selectedRow = table.getSelectedRow();
            if(selectedRow != -1){
                var id = (Long) table.getValueAt(selectedRow, 0);
                var cenario = cenarioController.buscarPorId(id, homeView.getPlano().getId());
                homeView.setCenario(cenario);
                homeView.setCenario(cenario);
                homeView.addPanel(new CasoTesteView(homeView));
            }
        });
        add(selectButton);
        selectButton.setBounds(650, 540, 110, 23);

        deleteButton.setText("Excluir");
        add(deleteButton);
        deleteButton.setBounds(360, 540, 80, 23);
        deleteButton.addActionListener(e -> {
            if(isNotAutorized(homeView.getUser().getProfile())){
                JOptionPane.showMessageDialog(null, "Você não tem permissão para excluir um cenário de teste", "Sem permissão", JOptionPane.ERROR_MESSAGE);
                return;
            }
            var selectedRow = table.getSelectedRow();
            if(selectedRow != -1){
                var id = (Long) table.getValueAt(selectedRow, 0);
                if( homeView.getCenario() != null && Objects.equals(id, homeView.getCenario().getId())){
                    homeView.setCenario(null);
                    homeView.setCaso(null);
                }
                cenarioController.delete(id);
                removeElementFromTable(selectedRow);
            }
        });


        findButton.setText("Pesquisar");
        add(findButton);
        findButton.setBounds(40, 290, 110, 23);
        findButton.addActionListener(e -> {
            if(findField.getText().isEmpty()){
                return;
            }
            var nome = findField.getText();
            var cenarios = cenarioController.findByNome(nome,homeView.getPlano().getId());
            if(cenarios != null && !cenarios.isEmpty()){
                clearTable();
                cenarios.forEach(this::addToTable);
            } else {
                try {
                    var id = Long.parseLong(nome);
                    var cenario = cenarioController.buscarPorId(id, homeView.getPlano().getId());
                    if(cenario != null){
                        clearTable();
                        addToTable(cenario);
                    }
                } catch (Exception ex){
                    JOptionPane.showMessageDialog(null, "Nenhum cenário encontrado", "Nenhum cenário encontrado", JOptionPane.ERROR_MESSAGE);
                }
            }
            findField.setText("");
        });

        listButton.setText("Listar");
        add(listButton);
        listButton.setBounds(40, 540, 90, 23);
        listButton.addActionListener(e -> {
            var cenarios = cenarioController.findFirst10(homeView.getPlano().getId());
            if(cenarios != null && !cenarios.isEmpty()){
                clearTable();
                cenarios.forEach(this::addToTable);
            }
        });

        statusField.setModel(new DefaultComboBoxModel<>(new String[] {"Aguardando Início", "Em execução", "Finalizado", "Cancelado", "Finalizado com ERRO"}));
        add(statusField);
        statusField.setBounds(250, 220, 200, 22);
    }

    private boolean isNotAutorized(Profile profile) {
        return !profile.equals(Profile.ADMIN) && !profile.equals(Profile.TEST);
    }

    private void removeElementFromTable(int selectedRow) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.removeRow(selectedRow);
    }

    private void addToTable(Cenario cenarioSalvo) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.addRow(new Object[]{
                cenarioSalvo.getId(),
                cenarioSalvo.getNome(),
                cenarioSalvo.getTempo(),
                cenarioSalvo.getStatus()
        });
    }

    private void clearTable() {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
    }

    private void clearFields() {
        idCenario = null;
        nameField.setText("");
        timeField.setText("");
        statusField.setSelectedIndex(0);
    }
}
