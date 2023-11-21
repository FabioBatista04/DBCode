package br.edu.dombosco.dbcode.requisitos.view;

import br.edu.dombosco.dbcode.requisitos.controller.ProjetoController;
import br.edu.dombosco.dbcode.requisitos.model.Projeto;
import lombok.extern.slf4j.Slf4j;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

@Slf4j
public class ProjetoView extends JPanel {

    private JButton SaveButton;
    private JButton selectButton;
    private JLabel TextoDataInicio;
    private JButton buscarButton;
    private JTextField campoBuscar;
    private JTextField campoDataFim;
    private JTextField campoDataInicio;
    private JTextArea campoDescricao;
    private JTextField campoEstimativa;
    private JTextField campoParticipantes;
    private JTextField campoTitulo;
    private JButton deleteButon;
    private JButton editarButton;
    private JScrollPane jScrollPane1;
    private JScrollPane jScrollPane2;
    private JTable tabela;
    private JLabel textoDataFim;
    private JLabel textoDescricao;
    private JLabel textoEstimativa;
    private JLabel textoParticipantes;
    private JLabel textoTitulo;
    private ProjetoController projetoController;
    private Long idProjeto;
    private Projeto projetoSelected;

    public ProjetoView(ProjetoController projetoController) {
        this.projetoController = projetoController;
        initComponents();
        setLayoutProjeto();
        setListeners();
        initTableWithData();
    }

    private void initTableWithData() {
        var projetos = projetoController.buscar10Primeiros();
        if(projetos != null && !projetos.isEmpty()){
            projetos.forEach(this::populateTable);
        }
    }

    private void setListeners() {
        editarButton.addActionListener(e -> {
            var selectedRow = tabela.getSelectedRow();
            if(selectedRow != -1){
                var id = (Long) tabela.getValueAt(selectedRow, 0);
                var projeto = projetoController.buscarPorId(id);
                if(projeto != null){
                    idProjeto = projeto.getId();
                    campoTitulo.setText(projeto.getTitulo());
                    campoDescricao.setText(projeto.getDescricao());
                    campoParticipantes.setText(projeto.getParcipantes());
                    campoDataInicio.setText(projeto.getDatainicio());
                    campoDataFim.setText(projeto.getDatafim());
                    campoEstimativa.setText(projeto.getEstimativas());
                }
            }
        });
        deleteButon.addActionListener(e -> {
            var selectedRow = tabela.getSelectedRow();
            if(selectedRow != -1){
                var id = (Long) tabela.getValueAt(selectedRow, 0);
                projetoController.deletar(id);
                ((DefaultTableModel) tabela.getModel()).removeRow(selectedRow);
            }
        });
        SaveButton.addActionListener(e -> {
            var projeto = Projeto.builder()
                    .id(idProjeto)
                    .titulo(campoTitulo.getText())
                    .descricao(campoDescricao.getText())
                    .parcipantes(campoParticipantes.getText())
                    .datainicio(campoDataInicio.getText())
                    .datafim(campoDataFim.getText())
                    .estimativas(campoEstimativa.getText())
                    .build();
            if(projeto.containsNullFields().isEmpty()) {
                var projetoSalvo = projetoController.salvar(projeto);
                if(projetoSalvo != null){
                    clearFields();
                    clearTable();
                    populateTable(projetoSalvo);
                    JOptionPane.showMessageDialog(null, "Projeto salvo com sucesso!");

                }
            }else{
                JOptionPane.showMessageDialog(null, "Os campos " + projeto.containsNullFields() + " não podem ser vazios!");
            }
        });
        selectButton.addActionListener(e -> {
            var selectedRow = tabela.getSelectedRow();
            if(selectedRow != -1){
                var id = (Long) tabela.getValueAt(selectedRow, 0);
                var projeto = projetoController.buscarPorId(id);
                if(projeto != null){
                    projetoSelected = projeto;
                    //setVisible(false);
                    //new RequisitoView(projetoSelected).setVisible(true);
                }
            }

        });
        buscarButton.addActionListener(e -> {
            if(campoBuscar.getText().isEmpty()){
                clearTable();
                initTableWithData();
                campoBuscar.setText("");
                return;
            }
            var projetos = projetoController.buscarPorTitulo(campoBuscar.getText());
            clearTable();
            if(projetos != null && !projetos.isEmpty()){
                log.info("Buscando por titulo {}", projetos);
                projetos.forEach(this::populateTable);
            }else {
                try {
                    System.out.println("Buscando por id");
                    var projeto = projetoController.buscarPorId(Long.parseLong(campoBuscar.getText()));
                    if(projeto != null){
                        populateTable(projeto);
                    }
                }catch (Exception ex){
                    JOptionPane.showMessageDialog(null, "Nenhum projeto encontrado!");
                }
            }
            campoBuscar.setText("");
        });
    }

    private void clearTable() {
        var tableModel = (DefaultTableModel) tabela.getModel();
        tableModel.setRowCount(0);
    }

    private void populateTable(Projeto projetoSalvo) {
        var tableModel = (DefaultTableModel) tabela.getModel();
        tableModel.addRow(new Object[]{
                projetoSalvo.getId(),
                projetoSalvo.getTitulo(),
                projetoSalvo.getDescricao(),
                projetoSalvo.getParcipantes(),
                projetoSalvo.getDatainicio(),
                projetoSalvo.getDatafim(),
                projetoSalvo.getEstimativas()
        });
    }

    private void clearFields() {
        idProjeto = null;
        campoTitulo.setText("");
        campoDescricao.setText("");
        campoParticipantes.setText("");
        campoDataInicio.setText("");
        campoDataFim.setText("");
        campoEstimativa.setText("");
    }

    private void setLayoutProjeto() {
        setSize(800,650);
        setLayout(null);
    }

    private void initComponents() {

        textoTitulo = new javax.swing.JLabel();
        textoDescricao = new javax.swing.JLabel();
        TextoDataInicio = new javax.swing.JLabel();
        textoParticipantes = new javax.swing.JLabel();
        textoDataFim = new javax.swing.JLabel();
        textoEstimativa = new javax.swing.JLabel();
        campoTitulo = new javax.swing.JTextField();
        campoParticipantes = new javax.swing.JTextField();
        campoDataInicio = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        campoDescricao = new javax.swing.JTextArea();
        campoDataFim = new javax.swing.JTextField();
        campoEstimativa = new javax.swing.JTextField();
        editarButton = new javax.swing.JButton();
        deleteButon = new javax.swing.JButton();
        SaveButton = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabela = new javax.swing.JTable();
        selectButton = new javax.swing.JButton();
        campoBuscar = new javax.swing.JTextField();
        buscarButton = new javax.swing.JButton();


        textoTitulo.setText("Nome/Titulo:");
        add(textoTitulo);
        textoTitulo.setBounds(40, 20, 80, 16);

        textoDescricao.setText("Descrição:");
        add(textoDescricao);
        textoDescricao.setBounds(40, 60, 80, 16);

        TextoDataInicio.setText("Data Inicio:");
        add(TextoDataInicio);
        TextoDataInicio.setBounds(40, 270, 80, 16);

        textoParticipantes.setText("Participantes:");
        add(textoParticipantes);
        textoParticipantes.setBounds(40, 220, 100, 16);

        textoDataFim.setText("Data Fim:");
        add(textoDataFim);
        textoDataFim.setBounds(300, 270, 80, 16);

        textoEstimativa.setText("Estimativa em Horas:");
        add(textoEstimativa);
        textoEstimativa.setBounds(490, 270, 140, 16);
        add(campoTitulo);
        campoTitulo.setBounds(160, 20, 580, 22);
        add(campoParticipantes);
        campoParticipantes.setBounds(160, 220, 580, 22);
        add(campoDataInicio);
        campoDataInicio.setBounds(160, 270, 110, 22);

        campoDescricao.setColumns(20);
        campoDescricao.setRows(5);
        jScrollPane1.setViewportView(campoDescricao);

        add(jScrollPane1);
        jScrollPane1.setBounds(160, 60, 580, 135);
        add(campoDataFim);
        campoDataFim.setBounds(360, 270, 110, 22);
        add(campoEstimativa);
        campoEstimativa.setBounds(630, 270, 110, 22);

        editarButton.setText("Editar");
        editarButton.setActionCommand("");
        editarButton.addActionListener(e -> {

        });
        add(editarButton);
        editarButton.setBounds(80, 550, 80, 23);

        deleteButon.setText("Excluir");
        add(deleteButon);
        deleteButon.setBounds(230, 550, 80, 23);

        SaveButton.setText("Salvar");
        add(SaveButton);
        SaveButton.setBounds(420, 550, 80, 23);

        tabela.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {},
            new String [] {
                "Id","Nome/Titulo", "Descrição","Participantes", "Data Inicio", "Data Fim", "Estimativa em Horas"
            }
        ));
        jScrollPane2.setViewportView(tabela);

        add(jScrollPane2);
        jScrollPane2.setBounds(41, 375, 700, 150);

        selectButton.setText("Selecionar");
        add(selectButton);
        selectButton.setBounds(630, 550, 120, 23);
        add(campoBuscar);
        campoBuscar.setBounds(160, 320, 580, 22);

        buscarButton.setText("Buscar");
        add(buscarButton);
        buscarButton.setBounds(40, 320, 80, 23);
    }

}
