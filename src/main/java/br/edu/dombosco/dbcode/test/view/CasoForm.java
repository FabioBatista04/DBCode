package br.edu.dombosco.dbcode.test.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class CasoForm {
    private JFrame frame;
    private JTextField idCNField;
    private JTextField idCTField;
    private JTextField nomeCasoTesteField;
    private JTextField preRequisitosField;
    private JComboBox<String> complexidadeField;
    private JTextField acaoField;
    private JTextField resultadoEsperadoField;
    private JComboBox<String> passouField;
    private List<CasoTeste> casos = new ArrayList<>();
    private JTextArea resultadoArea;
    private JLabel mensagemLabel;
    private CenarioForm cenarioForm;

    public CasoForm() {
        frame = new JFrame("Caso de Teste");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));

        idCNField = new JTextField(20);
        idCTField = new JTextField(20);
        nomeCasoTesteField = new JTextField(20);
        preRequisitosField = new JTextField(20);

        // ComboBox para Complexidade
        String[] complexidadeOptions = {"Simples", "Moderada", "Difícil"};
        complexidadeField = new JComboBox<>(complexidadeOptions);

        acaoField = new JTextField(20);
        resultadoEsperadoField = new JTextField(20);

        // ComboBox para Passou
        String[] passouOptions = {"Sim", "Não"};
        passouField = new JComboBox<>(passouOptions);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        JButton salvarButton = new JButton("Salvar");
        salvarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                salvarCaso();
            }
        });

        JButton excluirButton = new JButton("Excluir");
        excluirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                excluirCaso();
            }
        });

        JButton listarButton = new JButton("Listar");
        listarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listarCasos();
            }
        });

        JButton pesquisarButton = new JButton("Pesquisar");
        pesquisarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pesquisarCaso();
            }
        });

        buttonPanel.add(salvarButton);
        buttonPanel.add(excluirButton);
        buttonPanel.add(listarButton);
        buttonPanel.add(pesquisarButton);

        resultadoArea = new JTextArea(10, 30);
        resultadoArea.setEditable(false);

        mensagemLabel = new JLabel("");

        mainPanel.add(createLabelAndField("ID do CN:", idCNField));
        mainPanel.add(createLabelAndField("ID do CT:", idCTField));
        mainPanel.add(createLabelAndField("Descrição do Caso de Teste:", nomeCasoTesteField));
        mainPanel.add(createLabelAndField("Pré-requisitos:", preRequisitosField));
        mainPanel.add(createLabelAndField("Complexidade:", complexidadeField));
        mainPanel.add(createLabelAndField("Ação:", acaoField));
        mainPanel.add(createLabelAndField("Resultado Esperado:", resultadoEsperadoField));
        mainPanel.add(createLabelAndField("Passou?", passouField));
        mainPanel.add(buttonPanel);

        frame.add(mainPanel, BorderLayout.NORTH);
        frame.add(new JScrollPane(resultadoArea), BorderLayout.CENTER);
        frame.add(mensagemLabel, BorderLayout.SOUTH);
        frame.pack();
        frame.setVisible(true);
    }

   private void salvarCaso() {
        String idCN = idCNField.getText();
        String idCT = idCTField.getText();
        String nomeCasoTeste = nomeCasoTesteField.getText();
        String preRequisitos = preRequisitosField.getText();
        String complexidade = complexidadeField.getSelectedItem().toString();
        String acao = acaoField.getText();
        String resultadoEsperado = resultadoEsperadoField.getText();
        String passou = passouField.getSelectedItem().toString();

        CasoTeste novoCaso = new CasoTeste(idCN, idCT, nomeCasoTeste, preRequisitos, complexidade, acao, resultadoEsperado, passou);
        casos.add(novoCaso);

        idCNField.setText("");
        idCTField.setText("");
        nomeCasoTesteField.setText("");
        preRequisitosField.setText("");
        complexidadeField.setSelectedIndex(0);
        acaoField.setText("");
        resultadoEsperadoField.setText("");
        passouField.setSelectedIndex(0);

        mensagemLabel.setText("Caso de Teste ID CN " + idCN + " incluído com sucesso");
    }

    /*private void salvarCaso() {
        String idCN = idCNField.getText();
        String idCT = idCTField.getText();
        String nomeCasoTeste = nomeCasoTesteField.getText();
        String preRequisitos = preRequisitosField.getText();
        String complexidade = complexidadeField.getSelectedItem().toString();
        String acao = acaoField.getText();
        String resultadoEsperado = resultadoEsperadoField.getText();
        String passou = passouField.getSelectedItem().toString();

        List<String> idsCenarioValidos = cenarioForm.getIdsCenarioValidos();

        if (!idsCenarioValidos.contains(idCN)) {
            mensagemLabel.setText("ID CN " + idCN + " não é um ID de Cenário válido.");
        } else {
            CasoTeste novoCaso = new CasoTeste(idCN, idCT, nomeCasoTeste, preRequisitos, complexidade, acao, resultadoEsperado, passou);
            casos.add(novoCaso);

            idCNField.setText("");
            idCTField.setText("");
            nomeCasoTesteField.setText("");
            preRequisitosField.setText("");
            complexidadeField.setSelectedIndex(0);
            acaoField.setText("");
            resultadoEsperadoField.setText("");
            passouField.setSelectedIndex(0);

            mensagemLabel.setText("Caso de Teste ID CN " + idCN + " incluído com sucesso");
        }
    }*/

    private void excluirCaso() {
        String idCN = idCNField.getText();

        casos.removeIf(caso -> caso.getIdCN().equals(idCN));

        mensagemLabel.setText("Caso de Teste excluído com sucesso");
    }

    private void listarCasos() {
        resultadoArea.setText("");
        for (CasoTeste caso : casos) {
            resultadoArea.append(caso.toString() + "\n");
        }
        mensagemLabel.setText("");
    }

    private void pesquisarCaso() {
        String idCN = idCNField.getText();
        resultadoArea.setText("");

        for (CasoTeste caso : casos) {
            if (caso.getIdCN().equals(idCN)) {
                resultadoArea.append(caso.toString() + "\n");
            }
        }

        if (resultadoArea.getText().isEmpty()) {
            mensagemLabel.setText("Nenhum Caso de Teste com o ID CN " + idCN + " encontrado.");
        } else {
            mensagemLabel.setText("");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new CasoForm();
            }
        });
    }

    private JPanel createLabelAndField(String label, JComponent field) {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel lbl = new JLabel(label);
        panel.add(lbl);
        panel.add(field);
        return panel;
    }

    public class CasoTeste {
        private String idCN;
        private String idCT;
        private String nomeCasoTeste;
        private String preRequisitos;
        private String complexidade;
        private String acao;
        private String resultadoEsperado;
        private String passou;

        public CasoTeste(String idCN, String idCT, String nomeCasoTeste, String preRequisitos, String complexidade, String acao, String resultadoEsperado, String passou) {
            this.idCN = idCN;
            this.idCT = idCT;
            this.nomeCasoTeste = nomeCasoTeste;
            this.preRequisitos = preRequisitos;
            this.complexidade = complexidade;
            this.acao = acao;
            this.resultadoEsperado = resultadoEsperado;
            this.passou = passou;
        }

        public String getIdCN() {
            return idCN;
        }

        public String getIdCT() {
            return idCT;
        }

        public String getNomeCasoTeste() {
            return nomeCasoTeste;
        }

        public String getPreRequisitos() {
            return preRequisitos;
        }

        public String getComplexidade() {
            return complexidade;
        }

        public String getAcao() {
            return acao;
        }

        public String getResultadoEsperado() {
            return resultadoEsperado;
        }

        public String getPassou() {
            return passou;
        }

        @Override
        public String toString() {
            return "ID CN: " + idCN + ", ID CT: " + idCT + ", Nome: " + nomeCasoTeste + ", Pré-requisitos: " + preRequisitos + ", Complexidade: " + complexidade + ", Ação: " + acao + ", Resultado Esperado: " + resultadoEsperado + ", Passou: " + passou;
        }
    }
}
