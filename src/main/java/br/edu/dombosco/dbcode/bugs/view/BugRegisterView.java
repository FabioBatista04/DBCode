package br.edu.dombosco.dbcode.bugs.view;

import br.edu.dombosco.dbcode.accessManagment.model.Profile;
import br.edu.dombosco.dbcode.accessManagment.view.HomeView;
import br.edu.dombosco.dbcode.bugs.controller.BugController;
import br.edu.dombosco.dbcode.bugs.model.Bug;

import javax.swing.*;
import java.time.LocalDate;

public class BugRegisterView extends JPanel {
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
    private HomeView homeView;

    public BugRegisterView(HomeView homeView) {
        this.homeView = homeView;
        this.bugController = homeView.getBugController();

        initComponents();
        setupListeners();
    }


    private void setupListeners() {
        lbl_titulo_bug.setBounds(10, 10, 100, 25);
        txt_titulo_bug.setBounds(240, 10, 250, 25);
        lbl_status_bug.setBounds(10, 40, 100, 25);
        txt_status_bug.setBounds(240, 40, 250, 25);
        lbl_descricao_bug.setBounds(10, 70, 100, 25);
        txt_descricao_bug.setBounds(240, 70, 420, 70);
        lbl_passos_reproducao.setBounds(10, 150, 210, 25);
        txt_passos_reproducao.setBounds(240, 150, 370, 70);
        lbl_classificacao_bug.setBounds(10, 290, 100, 25);
        txt_classificacao_bug.setBounds(240, 290, 250, 25);
        txt_filename.setBounds(240, 260, 250, 25);
        lbl_anexar_arquivo.setBounds(10, 260, 180, 25);
        lbl_prioridade.setBounds(10, 230, 180, 25);
        txt_prioridade.setBounds(240, 230, 250, 25);
        btn_limpar_campos_bug.setBounds(240, 350, 100, 25);
        btn_limpar_campos_bug.setBounds(240, 350, 100, 25);
        bt_cadastrar_bug.setBounds(380, 350, 110, 25);

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
            if(isNotAutorized(homeView.getUser().getProfile())){
                JOptionPane.showMessageDialog(this, "Você não tem permissão para cadastrar bugs!");
                return;
            }
            String titulo = txt_titulo_bug.getText();
            String status = (String) txt_status_bug.getSelectedItem();
            String descricao = txt_descricao_bug.getText();
            String reproducao = txt_passos_reproducao.getText();
            String classificacao = (String) txt_classificacao_bug.getSelectedItem();
            String prioridade = (String) txt_prioridade.getSelectedItem();
            String filename;
            if (txt_filename.getText() == null || txt_filename.getText().isEmpty() || txt_filename.getText().equals("Escolher arquivo...")){
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
                    .projeto(homeView.getProjeto())
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

    private boolean isNotAutorized(Profile profile) {
        System.out.println(profile);
        return profile != Profile.ADMIN && profile != Profile.DEV && profile != Profile.TEST;
    }

    private void initComponents() {
        setSize(800, 650);
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

    }

}
