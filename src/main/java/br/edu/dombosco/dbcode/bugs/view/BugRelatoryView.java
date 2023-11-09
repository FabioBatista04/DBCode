package br.edu.dombosco.dbcode.bugs.view;

import br.edu.dombosco.dbcode.bugs.controller.BugController;
import br.edu.dombosco.dbcode.bugs.model.Bug;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class BugRelatoryView extends JFrame {
    private JLabel lbl_status;
    private JLabel lbl_titulo;
    private JComboBox<String> txt_status;
    private JButton btn_mostrar_relatorio;
    private JButton btn_relatorio_csv;

    private BugController bugController;

    private FileWriter fileWriter;
    private BufferedWriter buffWrite;

    public BugRelatoryView(BugController bugController) {
        this.bugController = bugController;
        setLocationRelativeTo(null);
        initComponents();
        setupListeners();

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    private void setupListeners() {
        lbl_titulo.setBounds(330, 60, 140, 25);
        lbl_status.setBounds(235, 220, 80, 25);
        txt_status.setBounds(320, 220, 200, 25);
        btn_relatorio_csv.setBounds(430, 330, 150, 25);
        btn_mostrar_relatorio.setBounds(270, 330, 150, 25);

        add(lbl_titulo);
        add(lbl_status);
        add(txt_status);
        add(btn_mostrar_relatorio);
        add(btn_relatorio_csv);

        // Mostar o relatório em tela
        btn_mostrar_relatorio.addActionListener(e -> {
            String status = (String) txt_status.getSelectedItem();
            assert status != null;

            var bugsQuery = bugController.listBugs(status);

            // TODO: implementar método de mostrar o relatório na tela
            DefaultTableModel model = new DefaultTableModel();
            JTable table = new JTable(model);

            model.addColumn("id");
            model.addColumn("titulo");
            model.addColumn("status");
            model.addColumn("descricao");
            model.addColumn("reproducao");
            model.addColumn("file");
            model.addColumn("classificacao");
            model.addColumn("prioridade");
            model.addColumn("data_cadastro");

            for (Bug bug : bugsQuery){
                System.out.println(bug);
                model.addRow(new Object[]{
                        bug.getId(),
                        bug.getTitulo(),
                        bug.getStatus(),
                        bug.getDescricao(),
                        bug.getReproducao(),
                        bug.getFile(),
                        bug.getClassificacao(),
                        bug.getPrioridade(),
                        bug.getData_cadastro()
                });
            }

            JFrame frame = new JFrame("Lista de bugs");
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

            JScrollPane scrollPane = new JScrollPane(table);
            frame.add(scrollPane);

            frame.pack();
            frame.setVisible(true);
        });

        // Gerar relatório em csv
        btn_relatorio_csv.addActionListener(e -> {
            String status = (String) txt_status.getSelectedItem();
            assert status != null;

            var bugsQuery = bugController.listBugs(status);

            String filepath = "bugs.csv";
            File file = new File(filepath);

            if (file.exists())
            {
                file.delete();
            }

            try {
                this.fileWriter = new FileWriter(filepath, true);
                this.buffWrite = new BufferedWriter(fileWriter);

                for (Bug bug : bugsQuery){

                    String row = bug.getId() + ";" + bug.getTitulo() + ";"
                            + bug.getStatus() + ";" + bug.getDescricao() + ";"
                            + bug.getReproducao() + ";" + bug.getFile() + ";"
                            + bug.getClassificacao() + ";" + bug.getPrioridade() + ";"
                            + bug.getData_cadastro() + ";";

                    buffWrite.write(row);
                    buffWrite.newLine();
                }
                buffWrite.close();

            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }

            JOptionPane.showMessageDialog(this, "Relatório gerado em: " + filepath);
        });
    }

    private void initComponents() {
        setTitle("Relatório de Bugs");
        setSize(700, 600);
        setLocationRelativeTo(null);
        setLayout(null);

        lbl_titulo = new JLabel("Relatório de Bugs");

        lbl_status = new JLabel("Filtrar por:");

        txt_status = new JComboBox<>(new String[]{
                "Todos", "Aberto", "Em andamento", "Concluído", "Em teste", "Fechado", "Reaberto"
        });

        btn_mostrar_relatorio = new JButton("Mostrar relatório");

        btn_relatorio_csv = new JButton("Relatório em CSV");

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }
}
