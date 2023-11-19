package br.edu.dombosco.dbcode.bugs.view;

import br.edu.dombosco.dbcode.bugs.controller.BugController;
import br.edu.dombosco.dbcode.bugs.model.Bug;

import javax.swing.*;
import java.time.LocalDate;

public class BugRegisterView extends JFrame {
    private JLabel lbl_titulo_bug;
    private JLabel lbl_status_bug;
    private JLabel lbl_descricao_bug;
    private JLabel lbl_passos_reproducao;
    private JLabel lbl_classificacao_bug;
    private JButton bt_cadastrar_bug;
    private JTextField txt_titulo_bug;
    private JButton btn_limpar_campos_bug;
    private JComboBox<String> txt_status_bug;
    private JTextArea txt_descricao_bug;
    private JComboBox<String> txt_classificacao_bug;
    private JTextArea txt_passos_reproducao;
    private JButton txt_filename;
    private JLabel lbl_anexar_arquivo;
    private JLabel lbl_prioridade;
    private JComboBox<String> txt_prioridade;

    private BugController bugController;

    public BugRegisterView(BugController bugController) {
        this.bugController = bugController;
        setLocationRelativeTo(null);
        initComponents();
        setupListeners();

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }


    private void setupListeners() {
        lbl_titulo_bug.setBounds(10, 10, 100, 25);
        txt_titulo_bug.setBounds(120, 10, 250, 25);
        lbl_status_bug.setBounds(10, 40, 100, 25);
        txt_status_bug.setBounds(120, 40, 250, 25);
        lbl_descricao_bug.setBounds(10, 70, 100, 25);
        txt_descricao_bug.setBounds(120, 70, 420, 70);
        lbl_passos_reproducao.setBounds(10, 150, 150, 25);
        txt_passos_reproducao.setBounds(170, 150, 370, 70);
        lbl_classificacao_bug.setBounds(10, 290, 100, 25);
        txt_classificacao_bug.setBounds(120, 290, 250, 25);
        txt_filename.setBounds(120, 260, 250, 25);
        lbl_anexar_arquivo.setBounds(10, 260, 100, 25);
        lbl_prioridade.setBounds(10, 230, 100, 25);
        txt_prioridade.setBounds(120, 230, 250, 25);
        btn_limpar_campos_bug.setBounds(120, 350, 100, 25);
        bt_cadastrar_bug.setBounds(230, 350, 110, 25);

        add(lbl_titulo_bug);
        add(txt_titulo_bug);
        add(lbl_status_bug);
        add(txt_status_bug);
        add(lbl_descricao_bug);
        add(txt_descricao_bug);
        add(lbl_passos_reproducao);
        add(txt_passos_reproducao);
        add(lbl_classificacao_bug);
        add(txt_classificacao_bug);
        add(txt_filename);
        add(lbl_prioridade);
        add(txt_prioridade);
        add(btn_limpar_campos_bug);
        add(bt_cadastrar_bug);
        add(lbl_anexar_arquivo);

        // Cadastrar Bug
        bt_cadastrar_bug.addActionListener(e -> {
            String titulo = txt_titulo_bug.getText();
            String status = (String) txt_status_bug.getSelectedItem();
            String descricao = txt_descricao_bug.getText();
            String reproducao = txt_passos_reproducao.getText();
            String classificacao = (String) txt_classificacao_bug.getSelectedItem();
            String prioridade = (String) txt_prioridade.getSelectedItem();
            String filename;
            if (txt_filename.getText().equals("Escolher arquivo...")){
                filename = "";
            }
            else{
                filename = txt_filename.getText();
            }

            var bugCreated = bugController.create(Bug.builder()
                    .titulo(titulo)
                    .status(status)
                    .descricao(descricao)
                    .reproducao(reproducao)
                    .file(filename)
                    .classificacao(classificacao)
                    .prioridade(prioridade)
                    .data_cadastro(LocalDate.now())
                    .build());
            if(bugCreated != null){
                JOptionPane.showMessageDialog(this, "Bug cadastrado com sucesso!");
                btn_limpar_campos_bug.doClick();
            }
            else{
                JOptionPane.showMessageDialog(this, "Preencha todos os campos!");
            }
        });

        // Limpar campos
        btn_limpar_campos_bug.addActionListener(e -> {
            txt_titulo_bug.setText(null);
            txt_passos_reproducao.setText(null);
            txt_descricao_bug.setText(null);
            txt_filename.setText(null);
            txt_classificacao_bug.setSelectedItem(null);
            txt_status_bug.setSelectedItem("Aberto");
            txt_classificacao_bug.setSelectedItem("Não definido");
            txt_prioridade.setSelectedItem("Baixa");
        });

        // Anexar arquivo
        txt_filename.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            int returnValue = fileChooser.showOpenDialog(this);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                java.io.File selectedFile = fileChooser.getSelectedFile();
                txt_filename.setText(selectedFile.getPath());
            }
        });
    }

    private void initComponents() {
        setTitle("Cadastro de Bug");
        setSize(700, 600);
        setLocationRelativeTo(null);
        setLayout(null);

        lbl_titulo_bug = new JLabel("Título do Bug:");
        txt_titulo_bug = new JTextField();

        lbl_status_bug = new JLabel("Status:");
        txt_status_bug = new JComboBox<>(
                new String[]{"Aberto", "Em andamento", "Concluído", "Em teste", "Fechado", "Reaberto"}
        );

        lbl_descricao_bug = new JLabel("Descrição:");
        txt_descricao_bug = new JTextArea();

        lbl_passos_reproducao = new JLabel("Passos de Reprodução:");
        txt_passos_reproducao = new JTextArea();

        lbl_classificacao_bug = new JLabel("Classificação:");
        txt_classificacao_bug = new JComboBox<>(
                new String[]{"Não definido", "Desenvolvimento", "Ambiente", "Especificação",
                        "Usabilidade", "Teste", "Melhoria"}
        );

        lbl_anexar_arquivo = new JLabel("Anexar arquivo:");
        txt_filename = new JButton("Escolher arquivo...");

        lbl_prioridade = new JLabel("Prioridade:");
        txt_prioridade = new JComboBox<>(
                new String[]{"Baixa", "Média", "Alta"}
        );

        bt_cadastrar_bug = new JButton("Cadastrar");

        btn_limpar_campos_bug = new JButton("Limpar");

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }

}
