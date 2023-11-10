package br.edu.dombosco.dbcode.test.view;

import br.edu.dombosco.dbcode.test.controller.PlanoController;
import br.edu.dombosco.dbcode.test.model.Plano;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.text.MaskFormatter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PlanoForm {
    private JFrame frame;
    private JFormattedTextField idPlanoField;
    private JTextField nomePlanoField;
    private JFormattedTextField dataInclusaoPlanoField;
    private JTextField respInclusaoPlanoField;
    private List<Plano> planos = new ArrayList<>();
    private JTextArea resultadoArea;
    private JLabel mensagemLabel;

    private PlanoController planoController;

    public PlanoForm(PlanoController planoController) {
        this.planoController = planoController;
        frame = new JFrame("Plano de Teste");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(6, 2));

        MaskFormatter idMask = null;
        MaskFormatter dataMask = null;

        try {
            idMask = new MaskFormatter("####");
            idMask.setPlaceholderCharacter('0');
            dataMask = new MaskFormatter("##/##/####");
            dataMask.setPlaceholderCharacter('0');
        } catch (ParseException e) {
            e.printStackTrace();
        }

        idPlanoField = new JFormattedTextField(idMask);
        idPlanoField.setColumns(4);
        idPlanoField.setFocusLostBehavior(JFormattedTextField.COMMIT);

        dataInclusaoPlanoField = new JFormattedTextField(dataMask);
        dataInclusaoPlanoField.setColumns(10);
        dataInclusaoPlanoField.setFocusLostBehavior(JFormattedTextField.COMMIT);

        JLabel nomePlanoLabel = new JLabel("Nome do Plano:");
        nomePlanoField = new JTextField(20);

        JLabel respInclusaoPlanoLabel = new JLabel("Responsável de Inclusão:");
        respInclusaoPlanoField = new JTextField(20);

        JButton salvarButton = new JButton("Salvar");
        salvarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                salvarPlano();
            }
        });

        JButton excluirButton = new JButton("Excluir");
        excluirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                excluirPlano();
            }
        });

        JButton listarButton = new JButton("Listar");
        listarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listarPlanos();
            }
        });

        JButton pesquisarButton = new JButton("Pesquisar");
        pesquisarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pesquisarPlano();
            }
        });

        resultadoArea = new JTextArea(10, 30);
        resultadoArea.setEditable(false);

        mensagemLabel = new JLabel("");

        panel.add(new JLabel("ID do Plano:"));
        panel.add(idPlanoField);
        panel.add(nomePlanoLabel);
        panel.add(nomePlanoField);
        panel.add(new JLabel("Data de Inclusão:"));
        panel.add(dataInclusaoPlanoField);
        panel.add(respInclusaoPlanoLabel);
        panel.add(respInclusaoPlanoField);
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

    private void salvarPlano() {
        String idPlano = idPlanoField.getText().replaceAll("[^0-9]", "");
        String nomePlano = nomePlanoField.getText();
        String dataInclusaoStr = dataInclusaoPlanoField.getText();
        String respInclusaoPlano = respInclusaoPlanoField.getText();

        Date dataInclusao = null;

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        try {
            dataInclusao = dateFormat.parse(dataInclusaoStr);
        } catch (ParseException e) {
            mensagemLabel.setText("Formato de data inválido.");
            return;
        }
        var planoSalvo = planoController.salvar(Plano.builder()
                        .id(Long.valueOf(idPlano))
                        .nome(nomePlano)
                        .data_inclusao(dataInclusao)
                        .responsavel_inclusao(respInclusaoPlano)
                .build());

        Plano novoPlano = new Plano(Long.valueOf(idPlano), nomePlano, dataInclusao, respInclusaoPlano);
        planos.add(novoPlano);

        idPlanoField.setValue("");
        nomePlanoField.setText("");
        dataInclusaoPlanoField.setValue("");
        respInclusaoPlanoField.setText("");

        mensagemLabel.setText("Plano id " + idPlano + " incluído com sucesso");
    }

    private void excluirPlano() {
        String idPlano = idPlanoField.getText().replaceAll("[^0-9]", "");

        planos.removeIf(plano -> false);

        mensagemLabel.setText("Plano excluído com sucesso");
    }

    private void listarPlanos() {
        resultadoArea.setText("");
        for (Plano plano : planos) {
            resultadoArea.append(plano.toString() + "\n");
        }
        mensagemLabel.setText("");
    }

    private void pesquisarPlano() {
        String idPlano = idPlanoField.getText().replaceAll("[^0-9]", "");
        resultadoArea.setText("");

        for (Plano plano : planos) {
            if (plano.getId().equals(idPlano)) {
                resultadoArea.append(plano.toString() + "\n");
                return;
            }
        }

        mensagemLabel.setText("Plano com o ID " + idPlano + " não encontrado.");
    }
}
