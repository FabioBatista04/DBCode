package br.edu.dombosco.dbcode.test.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class CenarioForm {
    private JFrame frame;
    private JFormattedTextField idCenarioTesteField;
    private JTextField nomeCenarioTesteField;
    private JFormattedTextField tempoCenarioTesteField;
    private JComboBox<String> statusCenarioTesteField;
    private List<CenarioTeste> cenarios = new ArrayList<>();
    private JTextArea resultadoArea;
    private JLabel mensagemLabel;

    public CenarioForm() {
        frame = new JFrame("Cenário de Teste");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(6, 2));

        MaskFormatter idMask = null;
        MaskFormatter tempoMask = null;

        try {
            idMask = new MaskFormatter("####");
            idMask.setPlaceholderCharacter('0');
            tempoMask = new MaskFormatter("##:##");
            tempoMask.setPlaceholderCharacter('0');
        } catch (ParseException e) {
            e.printStackTrace();
        }

        idCenarioTesteField = new JFormattedTextField(idMask);
        idCenarioTesteField.setColumns(4);
        idCenarioTesteField.setFocusLostBehavior(JFormattedTextField.COMMIT);

        tempoCenarioTesteField = new JFormattedTextField(tempoMask);
        tempoCenarioTesteField.setColumns(5);
        tempoCenarioTesteField.setFocusLostBehavior(JFormattedTextField.COMMIT);

        nomeCenarioTesteField = new JTextField(20);

        // Lista de valores para o status
        String[] statusOptions = {"Aguardando Início", "Em execução", "Finalizado", "Cancelado", "Finalizado com ERRO"};
        statusCenarioTesteField = new JComboBox<>(statusOptions);

        JButton salvarButton = new JButton("Salvar");
        salvarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                salvarCenario();
            }
        });

        JButton excluirButton = new JButton("Excluir");
        excluirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                excluirCenario();
            }
        });

        JButton listarButton = new JButton("Listar");
        listarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listarCenarios();
            }
        });

        JButton pesquisarButton = new JButton("Pesquisar");
        pesquisarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pesquisarCenario();
            }
        });

        resultadoArea = new JTextArea(10, 30);
        resultadoArea.setEditable(false);

        mensagemLabel = new JLabel("");

        panel.add(new JLabel("ID do Cenário de Teste:"));
        panel.add(idCenarioTesteField);
        panel.add(new JLabel("Nome do Cenário de Teste:"));
        panel.add(nomeCenarioTesteField);
        panel.add(new JLabel("Tempo do Cenário de Teste:"));
        panel.add(tempoCenarioTesteField);
        panel.add(new JLabel("Status do Cenário de Teste:"));
        panel.add(statusCenarioTesteField);
        panel.add(salvarButton);
        panel.add(excluirButton);
        panel.add(listarButton);
        panel.add(pesquisarButton);

        frame.add(panel, BorderLayout.NORTH);
        frame.add(new JScrollPane(resultadoArea), BorderLayout.CENTER);
        frame.add(mensagemLabel, BorderLayout.SOUTH);
        frame.pack();
        frame.setVisible(true);
    }

    private void salvarCenario() {
        String idCenarioTeste = idCenarioTesteField.getText().replaceAll("[^0-9]", "");
        String nomeCenarioTeste = nomeCenarioTesteField.getText();
        String tempoCenarioTeste = tempoCenarioTesteField.getText();
        String statusCenarioTeste = statusCenarioTesteField.getSelectedItem().toString();

        CenarioTeste novoCenario = new CenarioTeste(idCenarioTeste, nomeCenarioTeste, tempoCenarioTeste, statusCenarioTeste);
        cenarios.add(novoCenario);

        idCenarioTesteField.setValue("");
        nomeCenarioTesteField.setText("");
        tempoCenarioTesteField.setValue("");
        statusCenarioTesteField.setSelectedIndex(0);

        mensagemLabel.setText("Cenário de Teste id " + idCenarioTeste + " incluído com sucesso");
    }

    private void excluirCenario() {
        String idCenarioTeste = idCenarioTesteField.getText().replaceAll("[^0-9]", "");

        cenarios.removeIf(cenario -> cenario.getId().equals(idCenarioTeste));

        mensagemLabel.setText("Cenário de Teste excluído com sucesso");
    }

    private void listarCenarios() {
        resultadoArea.setText("");
        for (CenarioTeste cenario : cenarios) {
            resultadoArea.append(cenario.toString() + "\n");
        }
        mensagemLabel.setText("");
    }

    private void pesquisarCenario() {
        String idCenarioTeste = idCenarioTesteField.getText().replaceAll("[^0-9]", "");
        resultadoArea.setText("");

        for (CenarioTeste cenario : cenarios) {
            if (cenario.getId().equals(idCenarioTeste)) {
                resultadoArea.append(cenario.toString() + "\n");
                return;
            }
        }

        mensagemLabel.setText("Cenário de Teste com o ID " + idCenarioTeste + " não encontrado.");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new CenarioForm();
            }
        });
    }

    public List<String> getIdsCenarioValidos() {
        List<String> idsCenarioValidos = new ArrayList<>();
        for (CenarioTeste cenario : cenarios) {
            idsCenarioValidos.add(cenario.getId());
        }
        return idsCenarioValidos;
    }

    public class CenarioTeste {
        private String id;
        private String nome;
        private String tempo;
        private String status;

        public CenarioTeste(String id, String nome, String tempo, String status) {
            this.id = id;
            this.nome = nome;
            this.tempo = tempo;
            this.status = status;
        }

        public String getId() {
            return id;
        }

        public String getNome() {
            return nome;
        }

        public String getTempo() {
            return tempo;
        }

        public String getStatus() {
            return status;
        }

        @Override
        public String toString() {
            return "ID: " + id + ", Nome: " + nome + ", Tempo: " + tempo + ", Status: " + status;
        }
    }
}
