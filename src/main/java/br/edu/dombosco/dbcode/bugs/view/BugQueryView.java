package br.edu.dombosco.dbcode.bugs.view;

import br.edu.dombosco.dbcode.accessManagment.model.User;
import br.edu.dombosco.dbcode.bugs.controller.BugController;

import javax.swing.*;

import static br.edu.dombosco.dbcode.accessManagment.model.Profile.DEV;
import static br.edu.dombosco.dbcode.accessManagment.model.Profile.TEST;

public class BugQueryView extends JPanel {
    private JLabel lbl_id_bug;
    private JLabel lbl_titulo_bug;
    private JLabel lbl_status_bug;
    private JLabel lbl_descricao_bug;
    private JLabel lbl_passos_reproducao;
    private JLabel lbl_classificacao_bug;
    private JButton btn_consultar_bug;
    private JTextField txt_id_bug;
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
    private JLabel lbl_data;
    private JTextField txt_data;
    private JButton btn_deletar_bug;
    private JButton btn_editar_bug;

    private BugController bugController;
    private User user;

    public BugQueryView(BugController bugController, User user) {
        this.bugController = bugController;
        this.user = user;
        //setLocationRelativeTo(null);
        initComponents();
        setupListeners();

       // setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    private void setupListeners(){
        lbl_id_bug.setBounds(10, 10, 100, 25);
        txt_id_bug.setBounds(120, 10, 250, 25);
        lbl_titulo_bug.setBounds(10, 50, 100, 25);
        txt_titulo_bug.setBounds(120, 50, 250, 25);
        lbl_status_bug.setBounds(10, 90, 100, 25);
        txt_status_bug.setBounds(120, 90, 250, 25);
        lbl_descricao_bug.setBounds(10, 130, 100, 25);
        txt_descricao_bug.setBounds(120, 130, 420, 70);
        lbl_passos_reproducao.setBounds(10, 220, 150, 25);
        txt_passos_reproducao.setBounds(170, 220, 370, 70);
        lbl_classificacao_bug.setBounds(10, 320, 100, 25);
        txt_classificacao_bug.setBounds(120, 320, 250, 25);
        txt_filename.setBounds(120, 350, 250, 25);
        lbl_anexar_arquivo.setBounds(10, 350, 100, 25);
        lbl_prioridade.setBounds(10, 420, 100, 25);
        txt_prioridade.setBounds(120, 420, 250, 25);
        lbl_data.setBounds(10, 390, 100, 25);
        txt_data.setBounds(120, 390, 250, 25);
        btn_deletar_bug.setBounds(230, 460, 100, 25);
        btn_limpar_campos_bug.setBounds(120, 460, 100, 25);
        btn_consultar_bug.setBounds(230, 460, 100, 25);
        btn_editar_bug.setBounds(330, 460, 100, 25);

        add(lbl_id_bug);
        add(txt_id_bug);
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
        add(lbl_anexar_arquivo);
        add(lbl_prioridade);
        add(txt_prioridade);
        add(lbl_data);
        add(txt_data);
        add(btn_consultar_bug);
        add(btn_limpar_campos_bug);
        add(btn_deletar_bug);
        add(btn_editar_bug);

        // Limpar campos
        btn_limpar_campos_bug.addActionListener(e -> {
            txt_id_bug.setText(null);
            txt_titulo_bug.setText(null);
            txt_passos_reproducao.setText(null);
            txt_descricao_bug.setText(null);
            txt_filename.setText(null);
            txt_classificacao_bug.setSelectedItem(null);
            txt_data.setText(null);
            txt_status_bug.setSelectedItem("Aberto");
            txt_classificacao_bug.setSelectedItem("Não definido");
            txt_prioridade.setSelectedItem("Baixa");
        });

        // Consultar bug
        btn_consultar_bug.addActionListener(e -> {
            Long id = Long.parseLong(txt_id_bug.getText());
            var bugQuery = bugController.query(id);

            if(bugQuery != null){
                txt_titulo_bug.setText(bugQuery.getTitulo());
                txt_status_bug.setSelectedItem(bugQuery.getStatus());
                txt_descricao_bug.setText(bugQuery.getDescricao());
                txt_passos_reproducao.setText(bugQuery.getReproducao());
                txt_filename.setText(bugQuery.getFile());
                txt_data.setText(String.valueOf(bugQuery.getData_cadastro()));
                txt_prioridade.setSelectedItem(bugQuery.getPrioridade());
                txt_classificacao_bug.setSelectedItem(bugQuery.getClassificacao());
            }
            else{
                JOptionPane.showMessageDialog(this, "Bug não encontrado!");
            }
        });

        // Deletar
        btn_deletar_bug.addActionListener(e -> {

            if (txt_id_bug.getText().isEmpty()){
                JOptionPane.showMessageDialog(this, "Informe o ID do bug!");
            }
            else{
                Long id = Long.parseLong(txt_id_bug.getText());
                var bugQuery = bugController.query(id);

                if(bugQuery != null){
                    txt_titulo_bug.setText(bugQuery.getTitulo());
                    txt_status_bug.setSelectedItem(bugQuery.getStatus());
                    txt_descricao_bug.setText(bugQuery.getDescricao());
                    txt_passos_reproducao.setText(bugQuery.getReproducao());
                    txt_filename.setText(bugQuery.getFile());
                    txt_data.setText(String.valueOf(bugQuery.getData_cadastro()));
                    txt_prioridade.setSelectedItem(bugQuery.getPrioridade());
                    txt_classificacao_bug.setSelectedItem(bugQuery.getClassificacao());

                    int ans = JOptionPane.showConfirmDialog(this, "Deseja excluir o Bug?");
                    if (ans == 0){
                        bugController.delete(id);
                        JOptionPane.showMessageDialog(this, "Bug excluído com sucesso!");
                        btn_limpar_campos_bug.doClick();
                    }
                    else{
                        JOptionPane.showMessageDialog(this, "Operação cancelada!");
                    }
                }
                else{
                    JOptionPane.showMessageDialog(this, "Bug não encontrado!");
                }
            }
        });

        // Editar bug
        btn_editar_bug.addActionListener(e -> {
            if (txt_id_bug.getText().isEmpty()){
                JOptionPane.showMessageDialog(this, "Informe o ID do bug!");
            }
            else{
                Long id = Long.parseLong(txt_id_bug.getText());
                var bugQuery = bugController.query(id);

                if(bugQuery != null){

                    if (bugQuery.getStatus().equals("Aberto") && user.getProfile() == TEST){
                        String titulo = txt_titulo_bug.getText();
                        String status = (String) txt_status_bug.getSelectedItem();
                        String descricao = txt_descricao_bug.getText();
                        String reproducao = txt_passos_reproducao.getText();
                        String file = txt_filename.getText();
                        String classificacao = (String) txt_classificacao_bug.getSelectedItem();
                        String prioridade = (String) txt_prioridade.getSelectedItem();

                        bugController.editBugAberto(
                                id, titulo, status, descricao,
                                reproducao, file, classificacao, prioridade);

                        JOptionPane.showMessageDialog(this, "Bug editado com sucesso!");
                        btn_limpar_campos_bug.doClick();
                    }
                    else if (!bugQuery.getStatus().equals("Aberto") &&
                            (user.getProfile() == TEST || user.getProfile() == DEV)){
                        JOptionPane.showMessageDialog(this, "Você pode editar somente os campos 'status' e 'classificacao'");
                        String status = (String) txt_status_bug.getSelectedItem();
                        String classificacao = (String) txt_classificacao_bug.getSelectedItem();
                        bugController.editStatusClassificacao(id, status, classificacao);

                        JOptionPane.showMessageDialog(this, "Bug editado com sucesso!");
                        btn_limpar_campos_bug.doClick();
                    }
                    else{
                        JOptionPane.showMessageDialog(this, "Você não tem permissão para editar esse bug!");
                    }
                }
                else{
                    JOptionPane.showMessageDialog(this, "Bug não encontrado!");
                }
            }
        });
    }

    private void initComponents() {
        //setTitle("Consulta de Bug");
        setSize(800, 650);
        //setLocationRelativeTo(null);
        setLayout(null);

        lbl_id_bug = new JLabel("Id do Bug:");
        txt_id_bug = new JTextField();

        lbl_titulo_bug = new JLabel("Título do Bug:");
        txt_titulo_bug = new JTextField();

        lbl_status_bug = new JLabel("Status:");
        txt_status_bug = new JComboBox<>(new String[]{
                "Aberto", "Em andamento", "Concluído", "Em teste", "Fechado", "Reaberto"
        });

        lbl_descricao_bug = new JLabel("Descrição:");
        txt_descricao_bug = new JTextArea();

        lbl_passos_reproducao = new JLabel("Passos de Reprodução:");
        txt_passos_reproducao = new JTextArea();

        lbl_classificacao_bug = new JLabel("Classificação:");
        txt_classificacao_bug = new JComboBox<>(new String[]{
                "Não definido", "Desenvolvimento", "Ambiente", "Especificação", "Usabilidade", "Teste", "Melhoria"
        });

        lbl_anexar_arquivo = new JLabel("Anexar arquivo:");
        txt_filename = new JButton("Escolher arquivo...");

        lbl_prioridade = new JLabel("Prioridade:");
        txt_prioridade = new JComboBox<>(new String[]{
                "Baixa", "Média", "Alta"
        });

        lbl_data = new JLabel("Data cadastro:");
        txt_data = new JTextField();

        btn_consultar_bug = new JButton("Consultar");
        btn_limpar_campos_bug = new JButton("Limpar");
        btn_deletar_bug = new JButton("Excluir");
        btn_editar_bug = new JButton("Editar");

        //setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }
}
