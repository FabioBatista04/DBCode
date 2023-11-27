
package br.edu.dombosco.dbcode.test.view;

import br.edu.dombosco.dbcode.accessManagment.model.Profile;
import br.edu.dombosco.dbcode.accessManagment.view.HomeView;
import br.edu.dombosco.dbcode.test.controller.PlanoController;
import br.edu.dombosco.dbcode.test.model.Plano;
import lombok.extern.slf4j.Slf4j;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.Objects;

@Slf4j
public class PlanoTesteView extends JPanel {
    private JButton changeButton;
    private JTextField dateField;
    private JLabel dateText;
    private JButton deleteButton;
    private JButton findButton;
    private JTextField findField;
    private JScrollPane jScrollPane1;
    private JButton listButton;
    private JTextField nameField;
    private JLabel nameText;
    private JTextField responsabilityField;
    private JLabel responsbilityText;
    private JButton saveButton;
    private JButton selectButton;
    private JTable table;
    private PlanoController planoController;
    private Long idPlano;
    private HomeView homeView;

    public PlanoTesteView(HomeView homeView) {
        this.homeView = homeView;
        this.planoController = homeView.getPlanoController();
        initComponents();
        preencherTabela();
    }

    private void preencherTabela() {
        var planos = planoController.findFirst10(homeView.getProjeto().getId());
        clearTable();
        if(planos != null && !planos.isEmpty()){
            planos.forEach(this::addToTable);
        }
    }

    private void initComponents() {
        setSize(800,650);
        jScrollPane1 = new JScrollPane();
        table = new JTable();
        responsbilityText = new JLabel();
        findField = new JTextField();
        nameField = new JTextField();
        dateField = new JTextField();
        responsabilityField = new JTextField();
        nameText = new JLabel();
        dateText = new JLabel();
        changeButton = new JButton();
        saveButton = new JButton();
        selectButton = new JButton();
        deleteButton = new JButton();
        findButton = new JButton();
        listButton = new JButton();
        idPlano = null;

        setLayout(null);

        table.setModel(new DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Id", "Nome", "Data de Inclusão", "Responsavel de Inclusão"
            }
        ));

        jScrollPane1.setViewportView(table);

        add(jScrollPane1);
        jScrollPane1.setBounds(40, 330, 720, 180);

        responsbilityText.setText("Responsavel de Inclusão");
        add(responsbilityText);
        responsbilityText.setBounds(40, 220, 170, 16);
        add(findField);
        findField.setBounds(160, 290, 600, 22);

        nameField.addActionListener(evt -> {

        });
        add(nameField);
        nameField.setBounds(250, 50, 510, 22);

        dateField.addActionListener(evt -> {});
        add(dateField);
        dateField.setBounds(250, 130, 510, 22);
        add(responsabilityField);
        responsabilityField.setBounds(250, 220, 510, 22);

        nameText.setText("Nome do Plano de Teste");
        add(nameText);
        nameText.setBounds(40, 50, 160, 16);

        dateText.setText("Data da Inclusão");
        add(dateText);
        dateText.setBounds(40, 130, 130, 16);

        changeButton.setText("Alterar");
        add(changeButton);
        changeButton.setBounds(210, 540, 80, 23);
        changeButton.addActionListener(e -> {
            if(isNotAuthorized(homeView.getUser().getProfile())){
                JOptionPane.showMessageDialog(this, "Você não tem permissão para alterar", "Sem permissão", JOptionPane.ERROR_MESSAGE);
                return;
            }
            var selectedRow = table.getSelectedRow();
            if(selectedRow != -1){
                var id = (Long) table.getValueAt(selectedRow, 0);
                var plano = planoController.buscarPorId(id, homeView.getProjeto().getId());
                if(plano != null){
                    idPlano = plano.getId();
                    nameField.setText(plano.getNome());
                    dateField.setText(plano.getData_inclusao());
                    responsabilityField.setText(plano.getResponsavel_inclusao());
                }
            }
        });

        saveButton.setText("Salvar");
        saveButton.setActionCommand("salvar");
        saveButton.addActionListener(evt -> {
            if (isNotAuthorized(homeView.getUser().getProfile())) {
                JOptionPane.showMessageDialog(this, "Você não tem permissão para salvar", "Sem permissão", JOptionPane.ERROR_MESSAGE);
                return;
            }
            String nome = nameField.getText();
            String data = dateField.getText();
            String responsavel = responsabilityField.getText();
            var plano = Plano.builder()
                    .id(idPlano)
                    .nome(nome)
                    .data_inclusao(data)
                    .responsavel_inclusao(responsavel)
                    .projeto(homeView.getProjeto())
                    .build();
            if(plano.containsNullFields().isEmpty()){
                var planoSalvo = planoController.save(plano);
                if(planoSalvo != null){
                    clearFields();
                    clearTable();
                    addToTable(planoSalvo);
                }
            }else {
                JOptionPane.showMessageDialog(this, "Preencha os campos: " + plano.containsNullFields(), "Preencha todos os campos", JOptionPane.ERROR_MESSAGE);
            }
        });
        add(saveButton);
        saveButton.setBounds(510, 540, 80, 23);

        selectButton.setText("Selecionar");
        selectButton.addActionListener(evt -> {
            var selectedRow = table.getSelectedRow();
            if(selectedRow != -1){
                var id = (Long) table.getValueAt(selectedRow, 0);
                var plano = planoController.buscarPorId(id, homeView.getProjeto().getId());
                if(plano != null){
                    log.info("Plano selecionado: {}", plano.getId());
                    homeView.setPlano(plano);
                    homeView.setCenario(null);

                    homeView.addPanel(new CenarioTesteView(homeView));
                }
            }
        });
        add(selectButton);
        selectButton.setBounds(660, 540, 100, 23);

        deleteButton.setText("Excluir");
        add(deleteButton);
        deleteButton.setBounds(360, 540, 80, 23);
        deleteButton.addActionListener(e -> {
            if(isNotAuthorized(homeView.getUser().getProfile())){
                JOptionPane.showMessageDialog(this, "Você não tem permissão para excluir", "Sem permissão", JOptionPane.ERROR_MESSAGE);
                return;
            }
            var selectedRow = table.getSelectedRow();
            if(selectedRow != -1){
                var id = (Long) table.getValueAt(selectedRow, 0);
                if( homeView.getPlano() != null && Objects.equals(id, homeView.getPlano().getId())){
                    homeView.setPlano(null);
                    homeView.setCenario(null);
                    homeView.setCaso(null);
                }
                planoController.delete(id);
                removeElementFromTable(selectedRow);
            }
        });

        findButton.setText("Pesquisar");
        add(findButton);
        findButton.setBounds(40, 290, 100, 23);
        findButton.addActionListener(e -> {
            if(findField.getText().isEmpty()){
                return;
            }
            var nome = findField.getText();
            var planos = planoController.findByNome(nome, homeView.getProjeto().getId());
            if(planos != null && !planos.isEmpty()){
                clearTable();
                planos.forEach(this::addToTable);
            }else {
                try {
                    var id = Long.parseLong(nome);
                    var plano = planoController.buscarPorId(id, homeView.getProjeto().getId());
                    if(plano != null){
                        clearTable();
                        addToTable(plano);
                    }
                }catch (Exception ex){
                    JOptionPane.showMessageDialog(this, "Nenhum plano encontrado", "Nenum Plano encontrado", JOptionPane.ERROR_MESSAGE);
                }
            }
            findField.setText("");
        });

        listButton.setText("Listar");
        add(listButton);
        listButton.setBounds(40, 540, 90, 23);
        listButton.addActionListener(e -> {
            var planos = planoController.findFirst10(homeView.getProjeto().getId());
            if(planos != null && !planos.isEmpty()){
                clearTable();
                planos.forEach(this::addToTable);
            }
        });
    }

    private boolean isNotAuthorized(Profile profile) {
        return !profile.equals(Profile.ADMIN) && !profile.equals(Profile.TEST);
    }

    private void removeElementFromTable(int selectedRow) {
        var tableModel = (DefaultTableModel) table.getModel();
        tableModel.removeRow(selectedRow);
    }

    private void addToTable(Plano planoSalvo) {
        var tableModel = (DefaultTableModel) table.getModel();
        tableModel.addRow(new Object[]{
                planoSalvo.getId(),
                planoSalvo.getNome(),
                planoSalvo.getData_inclusao(),
                planoSalvo.getResponsavel_inclusao()
        });
    }

    private void clearTable() {
        var tableModel = (DefaultTableModel) table.getModel();
        tableModel.setRowCount(0);
    }

    private void clearFields() {
        idPlano = null;
        nameField.setText("");
        dateField.setText("");
        responsabilityField.setText("");
    }

}
