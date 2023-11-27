/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package br.edu.dombosco.dbcode.test.view;

import br.edu.dombosco.dbcode.accessManagment.model.Profile;
import br.edu.dombosco.dbcode.accessManagment.view.HomeView;
import br.edu.dombosco.dbcode.test.model.CasoDetalhado;




import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.Objects;

/**
 *
 * @author fabio
 */
public class CasoDetalhadoView extends JPanel {
    private JTextArea actionField;
    private JLabel actionText;
    private JButton changeButton;
    private JButton deleteButton;
    private JButton findButton;
    private JTextField findField;
    private JScrollPane jScrollPane1;
    private JScrollPane jScrollPane2;
    private JScrollPane jScrollPane4;
    private JTextField passoField;
    private JButton listButton;
    private JComboBox<String> passField;
    private JLabel passoText;
    private JLabel passouText;
    private JLabel resultText;
    private JTextArea resultaField;
    private JButton saveButton;
    private JTable table;
    private HomeView homeView;
    private Long idCasoDetalhado;

    public CasoDetalhadoView(HomeView homeView) {
        this.homeView = homeView;
        initComponents();
        preencherTabela();
    }

    private void preencherTabela() {
        var planos = homeView.getCasoDetalhadoController().findFirst10(homeView.getCaso().getId());
        if(planos != null && !planos.isEmpty()){
            clearTable();
            planos.forEach(this::addToTable);
        }
    }

    private void initComponents() {
        setSize(800, 650);
        setLayout(null);

        jScrollPane1 = new JScrollPane();
        table = new JTable();
        findField = new JTextField();
        passoField = new JTextField();
        changeButton = new JButton();
        deleteButton = new JButton();
        findButton = new JButton();
        listButton = new JButton();
        resultText = new JLabel();
        jScrollPane2 = new JScrollPane();
        resultaField = new JTextArea();
        actionText = new JLabel();
        passouText = new JLabel();
        jScrollPane4 = new JScrollPane();
        actionField = new JTextArea();
        passoText = new JLabel();
        passField = new JComboBox<>();
        saveButton = new JButton();
        idCasoDetalhado = null;


        table.setModel(new DefaultTableModel(
            new Object [][] {},
            new String [] {
                "Id",
                "Passo",
                "Ação",
                "Resultado Esperado",
                "Passou"
            }
        ));
        jScrollPane1.setViewportView(table);

        add(jScrollPane1);
        jScrollPane1.setBounds(40, 360, 720, 170);
        add(findField);
        findField.setBounds(160, 310, 600, 22);
        add(passoField);
        passoField.setBounds(40, 230, 330, 22);

        changeButton.setText("Alterar");
        add(changeButton);
        changeButton.setBounds(280, 560, 80, 23);
        changeButton.addActionListener(e -> {
            if(isNotAutorized(homeView.getUser().getProfile())){
                JOptionPane.showMessageDialog(this, "Você não tem permissão para alterar", "Sem permissão", JOptionPane.ERROR_MESSAGE);
                return;
            }
            var selectedRow = table.getSelectedRow();
            if(selectedRow != -1){
                var id = (Long) table.getModel().getValueAt(selectedRow, 0);
                var casoDetalhado = homeView.getCasoDetalhadoController().buscarPorId(id, homeView.getCaso().getId());
                if(casoDetalhado != null){
                    idCasoDetalhado = casoDetalhado.getId();
                    passoField.setText(casoDetalhado.getPasso());
                    actionField.setText(casoDetalhado.getAcao());
                    resultaField.setText(casoDetalhado.getResultadoEsperado());
                    passField.setSelectedItem(casoDetalhado.getPassou());
                }
            }
        });

        deleteButton.setText("Excluir");
        add(deleteButton);
        deleteButton.setBounds(490, 560, 80, 23);
        deleteButton.addActionListener(e -> {
            if(isNotAutorized(homeView.getUser().getProfile())){
                JOptionPane.showMessageDialog(this, "Você não tem permissão para excluir", "Sem permissão", JOptionPane.ERROR_MESSAGE);
                return;
            }
            var selectedRow = table.getSelectedRow();
            if(selectedRow != -1){
                var id = (Long) table.getValueAt(selectedRow, 0);
                homeView.getCasoDetalhadoController().delete(id);
                removeElementFromTable(selectedRow);
            }
        });

        findButton.setText("Pesquisar");
        add(findButton);
        findButton.setBounds(40, 310, 100, 23);
        findButton.addActionListener(e -> {
            if(findField.getText().isEmpty()){
                return;
            }
            var passo = findField.getText();
            var casos = homeView.getCasoDetalhadoController().findByPasso(passo, homeView.getCaso().getId());
            if(casos != null && !casos.isEmpty()){
                clearTable();
                casos.forEach(this::addToTable);
            }else {
                try {
                    var id = Long.parseLong(passo);
                    var casoDetalhado = homeView.getCasoDetalhadoController().buscarPorId(id, homeView.getCaso().getId());
                    if(casoDetalhado != null){
                        clearTable();
                        addToTable(casoDetalhado);
                    }
                }catch (Exception ex){
                    JOptionPane.showMessageDialog(this, "Nenhum Caso Detalhado encontrado", "Nenhum CasoDetalhado encontrado", JOptionPane.ERROR_MESSAGE);
                }
            }
            findField.setText("");
        });

        listButton.setText("Listar");
        add(listButton);
        listButton.setBounds(40, 560, 90, 23);
        listButton.addActionListener(e -> {
            var planos = homeView.getCasoDetalhadoController().findFirst10(homeView.getCaso().getId());
            if(planos != null && !planos.isEmpty()){
                clearTable();
                planos.forEach(this::addToTable);
            }
        });

        resultText.setText("Resultado Esperado");
        add(resultText);
        resultText.setBounds(450, 30, 170, 16);

        resultaField.setColumns(20);
        resultaField.setRows(5);
        jScrollPane2.setViewportView(resultaField);

        add(jScrollPane2);
        jScrollPane2.setBounds(450, 50, 310, 90);

        actionText.setText("Ação:");
        add(actionText);
        actionText.setBounds(40, 30, 170, 16);

        passouText.setText("Passou:");
        add(passouText);
        passouText.setBounds(450, 210, 70, 16);

        actionField.setColumns(20);
        actionField.setRows(5);
        jScrollPane4.setViewportView(actionField);

        add(jScrollPane4);
        jScrollPane4.setBounds(40, 50, 330, 90);

        passoText.setText("Passo:");
        add(passoText);
        passoText.setBounds(40, 210, 170, 16);

        passField.setModel(new DefaultComboBoxModel<>(new String[] { "Sim", "Não" }));

        add(passField);
        passField.setBounds(450, 230, 150, 22);

        saveButton.setText("Salvar");
        saveButton.setActionCommand("salvar");
        add(saveButton);
        saveButton.setBounds(680, 560, 80, 23);
        saveButton.addActionListener(e -> {
            if(isNotAutorized(homeView.getUser().getProfile())){
                JOptionPane.showMessageDialog(this, "Você não tem permissão para salvar", "Sem permissão", JOptionPane.ERROR_MESSAGE);
                return;
            }
            var casoDetalhado = CasoDetalhado.builder()
                    .id(idCasoDetalhado)
                    .passo(passoField.getText())
                    .acao(actionField.getText())
                    .resultadoEsperado(resultaField.getText())
                    .passou(Objects.requireNonNull(passField.getSelectedItem()).toString())
                    .caso(homeView.getCaso())
                    .build();

            if(casoDetalhado.containsNullFields().isEmpty()){
                try {
                    var casoDetalhadoSalvo = homeView.getCasoDetalhadoController().save(casoDetalhado);
                    if(casoDetalhadoSalvo != null){
                        clearTable();
                        clearFields();
                        addToTable(casoDetalhadoSalvo);
                    }
                }catch (Exception ex){
                    JOptionPane.showMessageDialog(this, "Erro ao salvar caso Detalhado");
                }
            }else{
                JOptionPane.showMessageDialog(this, "Preencha os campos: " + casoDetalhado.containsNullFields(), "Campos obrigatórios", JOptionPane.WARNING_MESSAGE);
            }
        });
    }

    private boolean isNotAutorized(Profile profile) {
        return !profile.equals(Profile.ADMIN) && !profile.equals(Profile.TEST);
    }

    private void clearFields() {
        idCasoDetalhado = null;
        passoField.setText("");
        actionField.setText("");
        resultaField.setText("");
        passField.setSelectedIndex(0);
    }

    private void addToTable(CasoDetalhado casoDetalhado) {
        var model = (DefaultTableModel) table.getModel();
        model.addRow(new Object[]{
                casoDetalhado.getId(),
                casoDetalhado.getPasso(),
                casoDetalhado.getAcao(),
                casoDetalhado.getResultadoEsperado(),
                casoDetalhado.getPassou()
        });
    }

    private void clearTable() {
        var model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
    }

    private void removeElementFromTable(int selectedRow) {
        var model = (DefaultTableModel) table.getModel();
        model.removeRow(selectedRow);
    }
}
