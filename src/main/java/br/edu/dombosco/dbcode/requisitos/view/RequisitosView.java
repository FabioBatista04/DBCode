package br.edu.dombosco.dbcode.requisitos.view;


import br.edu.dombosco.dbcode.requisitos.controller.RequisitosController;
import br.edu.dombosco.dbcode.requisitos.model.Requisito;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class RequisitosView extends JFrame {
    int num;
    
    public RequisitosView() {
        initComponents();
        num = 0;
        
        
        DefaultTableModel modelo = (DefaultTableModel) jTable1.getModel();
        jTable1.setRowSorter(new TableRowSorter(modelo));
    }


    @SuppressWarnings("unchecked")
    private void initComponents() {

        bg_qualificacao = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txt_nome = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        txt_descricao = new javax.swing.JTextArea();
        rb_func = new javax.swing.JRadioButton();
        rb_naofunc = new javax.swing.JRadioButton();
        bt_file_especificacao = new javax.swing.JButton();
        bt_file_desenho = new javax.swing.JButton();
        txt_file_especificacao = new javax.swing.JTextField();
        txt_file_desenho = new javax.swing.JTextField();
        bt_excluir = new javax.swing.JButton();
        bt_salvar = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        txt_buscar = new javax.swing.JTextField();
        bt_buscar = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        bt_editar = new javax.swing.JButton();
        bt_imprimir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 51, 51));
        jLabel1.setText("CADASTRO DE REQUISITO");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(51, 51, 51));
        jLabel2.setText("NOME/TITULO");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(51, 51, 51));
        jLabel6.setText("ANEXAR DESENHO UC");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(51, 51, 51));
        jLabel4.setText("QUALIFICAÇÃO");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(51, 51, 51));
        jLabel3.setText("DESCRIÇÃO");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(51, 51, 51));
        jLabel5.setText("ANEXAR ESPECIFICAÇÃO");

        txt_nome.setBackground(new java.awt.Color(204, 204, 204));
        txt_nome.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        txt_descricao.setBackground(new java.awt.Color(204, 204, 204));
        txt_descricao.setColumns(20);
        txt_descricao.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txt_descricao.setRows(5);
        jScrollPane1.setViewportView(txt_descricao);

        rb_func.setBackground(new java.awt.Color(255, 255, 255));
        bg_qualificacao.add(rb_func);
        rb_func.setForeground(new java.awt.Color(51, 51, 51));
        rb_func.setText("Funcional");

        bg_qualificacao.add(rb_naofunc);
        rb_naofunc.setForeground(new java.awt.Color(51, 51, 51));
        rb_naofunc.setText("Não funcional");

        bt_file_especificacao.setBackground(new java.awt.Color(204, 204, 204));
        bt_file_especificacao.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        bt_file_especificacao.setText("ARQUIVO");
        bt_file_especificacao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_file_especificacaoActionPerformed(evt);
            }
        });

        bt_file_desenho.setBackground(new java.awt.Color(204, 204, 204));
        bt_file_desenho.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        bt_file_desenho.setText("ARQUIVO");
        bt_file_desenho.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_file_desenhoActionPerformed(evt);
            }
        });

        txt_file_especificacao.setBackground(new java.awt.Color(204, 204, 204));

        txt_file_desenho.setBackground(new java.awt.Color(204, 204, 204));

        bt_excluir.setBackground(new java.awt.Color(204, 204, 204));
        bt_excluir.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        bt_excluir.setText("EXCLUIR");
        bt_excluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_excluirActionPerformed(evt);
            }
        });

        bt_salvar.setBackground(new java.awt.Color(204, 204, 204));
        bt_salvar.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        bt_salvar.setText("SALVAR");
        bt_salvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_salvarActionPerformed(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(204, 204, 204));

        bt_buscar.setBackground(new java.awt.Color(204, 204, 204));
        bt_buscar.setText("Buscar");
        bt_buscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_buscarActionPerformed(evt);
            }
        });

        jTable1.setBackground(new java.awt.Color(204, 204, 204));
        jTable1.setModel(new DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Num", "Nome do Requisito", "Qualificação", "Descrição", "Anexos Especificação", "Anexo desenho UC"
            }
        ) {
            Class[] types = new Class [] {
                Integer.class, String.class, String.class, String.class, String.class, String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(txt_buscar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bt_buscar)
                .addGap(62, 62, 62))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_buscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bt_buscar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 228, Short.MAX_VALUE)
                .addContainerGap())
        );

        bt_editar.setBackground(new java.awt.Color(204, 204, 204));
        bt_editar.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        bt_editar.setText("EDITAR");
        bt_editar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_editarActionPerformed(evt);
            }
        });

        bt_imprimir.setBackground(new java.awt.Color(204, 204, 204));
        bt_imprimir.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        bt_imprimir.setText("IMPRIMIR");
        bt_imprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_imprimirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(204, 204, 204)
                                .addComponent(bt_editar)
                                .addGap(124, 124, 124)
                                .addComponent(bt_excluir)
                                .addGap(75, 75, 75)
                                .addComponent(bt_imprimir))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(230, 230, 230)
                                .addComponent(jLabel1)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(rb_func)
                                        .addGap(18, 18, 18)
                                        .addComponent(rb_naofunc))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(1, 1, 1)
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(23, 23, 23)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(bt_file_especificacao)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txt_file_especificacao))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(bt_file_desenho)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txt_file_desenho))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel5)
                                            .addComponent(jLabel6))
                                        .addGap(0, 206, Short.MAX_VALUE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(bt_salvar))))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_nome)
                                .addGap(12, 12, 12)))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txt_nome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(bt_file_especificacao)
                                    .addComponent(txt_file_especificacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(bt_file_desenho)
                                    .addComponent(txt_file_desenho, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(bt_salvar)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(rb_naofunc)
                                .addComponent(rb_func)
                                .addComponent(jLabel4))))
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bt_excluir)
                    .addComponent(bt_editar)
                    .addComponent(bt_imprimir))
                .addGap(12, 12, 12))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bt_file_especificacaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_file_especificacaoActionPerformed
        // TODO add your handling code here:
        
        JFileChooser especificacao = new JFileChooser();
        especificacao.setDialogTitle("Anexar arquivo de especificações:");
        int retorno = especificacao.showOpenDialog(this);// mudar this para local de mostra do arquivo
        
        if(retorno == JFileChooser.APPROVE_OPTION){
            File file = especificacao.getSelectedFile();
            txt_file_especificacao.setText(file.getPath());
        }
        
    }//GEN-LAST:event_bt_file_especificacaoActionPerformed

    private void bt_file_desenhoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_file_desenhoActionPerformed
        // TODO add your handling code here:
        JFileChooser desenho = new JFileChooser();
        desenho.setDialogTitle("Anexar arquivo de Desenhu UC:");
        int retorno = desenho.showOpenDialog(this);// mudar this para local de mostra do arquivo
        
        if(retorno == JFileChooser.APPROVE_OPTION){
            File file = desenho.getSelectedFile();
            txt_file_desenho.setText(file.getPath());
        }
    }//GEN-LAST:event_bt_file_desenhoActionPerformed

    private void bt_excluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_excluirActionPerformed
        // TODO add your handling code here:
        DefaultTableModel remover = (DefaultTableModel) jTable1.getModel();
        remover.removeRow(jTable1.getSelectedRow());
        System.out.println("linha "+jTable1.getSelectedRow()+" foi excluida.");
    }//GEN-LAST:event_bt_excluirActionPerformed

    private void bt_salvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_salvarActionPerformed
        // TODO add your handling code here:
        try {
            String qualificacao = "";
            
            DefaultTableModel salvar = (DefaultTableModel) jTable1.getModel();
            
            RequisitosController db = new RequisitosController();

            var requisito = Requisito.builder()
                    .nome(txt_nome.getText())
                    .descricao(txt_descricao.getText())
                    .file_especificacao(txt_file_especificacao.getText())
                    .file_desenho(txt_file_desenho.getText())
                    .build();

            String nome = txt_nome.getText();

            if(rb_naofunc.isSelected() == true){
                qualificacao = "Não Funcional";
            }
            if(rb_func.isSelected() == true){
                qualificacao = "Funcional";
            }

            String descricao = txt_descricao.getText();
            String especificacao = txt_file_especificacao.getText();
            String desenho = txt_file_desenho.getText();

            Object[] dados = {num,txt_nome.getText(), qualificacao, txt_descricao.getText(), txt_file_especificacao.getText(), txt_file_desenho.getText()};
            salvar.addRow(dados);

            db.salvar(requisito);
            num++;
//        } catch (ErroInvalidIndexException e) {
//            System.out.println("ErroInvalid index");
            
        } catch (Exception e) {
            System.out.println("Erro" + e.getMessage());
        }
        

        
    }//GEN-LAST:event_bt_salvarActionPerformed

    private void bt_editarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_editarActionPerformed
        // TODO add your handling code here:
        try {
            String qualificacao = "";
            DefaultTableModel editar = (DefaultTableModel) jTable1.getModel();
            RequisitosController db = new RequisitosController();
            
            int id = (int) jTable1.getValueAt(jTable1.getSelectedRow(), 0);
            String nome = txt_nome.getText();

            if(rb_naofunc.isSelected() == true){
                qualificacao = "Não Funcional";
            }
            if(rb_func.isSelected() == true){
                qualificacao = "Funcional";
            }
            
            String descricao = txt_descricao.getText();
            String especificacao = txt_file_especificacao.getText();
            String desenho = txt_file_desenho.getText();

            Object[] dados = {id,nome, qualificacao, descricao, especificacao, desenho};
            for (int i = 0; i < dados.length; i++) {
                editar.setValueAt(dados[i], jTable1.getSelectedRow(), i);
            }
            
            db.editar(null);
        } catch (Exception e) {
            System.out.println("Erro" + e.getMessage());
        }
    }//GEN-LAST:event_bt_editarActionPerformed

    private void bt_imprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_imprimirActionPerformed
        // TODO add your handling code here:
        try {
            // Obter o modelo da JTable
            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();

            RequisitosController db = new RequisitosController();

            // Obter a linha selecionada
            int selectedRow = jTable1.getSelectedRow();

            // Verificar se uma linha está selecionada
            if (selectedRow != -1) {
                // Obter os dados da linha selecionada
            String id = model.getValueAt(selectedRow, 0).toString();// Assumindo que a coluna 0 contém o ID
            String nome = model.getValueAt(selectedRow, 1).toString();  // Assumindo que a coluna 1 contém o nome
            String qualificacao = model.getValueAt(selectedRow, 2).toString();  // Assumindo que a coluna 2 contém a qualificação
            String descricao = model.getValueAt(selectedRow, 3).toString();  // Assumindo que a coluna 3 contém a descrição
            String especificacao = model.getValueAt(selectedRow, 4).toString();  // Assumindo que a coluna 4 contém o arquivo de especificação
            String desenho = model.getValueAt(selectedRow, 5).toString();  // Assumindo que a coluna 5 contém o arquivo de desenho

            // Chamar o método para imprimir os dados
            FileWriter arquivo = new FileWriter("C:\\Users\\user\\Documents\\" + nome +".txt");// para abrir o arquivo
            PrintWriter grava_arquivo = new PrintWriter(arquivo);  // Processar Arquivo
            grava_arquivo.printf("Id: "+id+"\nNome: " +nome+"\nQualificação: "+qualificacao+"\nEspecificação: " +especificacao+"\nDesenho: " +desenho+"\nDescrição: " +descricao); // Processar Aquivo
            grava_arquivo.close();// Fechar Arquivo
            
                System.out.println("Impressão "+ nome +" concluida em C:\\Users\\user\\Documents\\" + nome +".txt");
            

            } else {
                JOptionPane.showMessageDialog(this, "Selecione uma linha para imprimir os dados.");
            }
        } catch (Exception e) {
            System.out.println("Erro" + e.getMessage());
        }
        
        
    }//GEN-LAST:event_bt_imprimirActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
        
        //fazer selecionar linha, modificar, atualizar no banco
        
        if(jTable1.getSelectedRow() != -1){
            txt_nome.setText(jTable1.getValueAt(jTable1.getSelectedRow(), 1).toString());
            txt_descricao.setText(jTable1.getValueAt(jTable1.getSelectedRow(), 3).toString());
            txt_file_especificacao.setText(jTable1.getValueAt(jTable1.getSelectedRow(), 4).toString());
            txt_file_desenho.setText(jTable1.getValueAt(jTable1.getSelectedRow(), 5).toString());
            
            // falta capturar seleção qualificação
                
        }
            
        
    }//GEN-LAST:event_jTable1MouseClicked

    private void bt_buscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_buscarActionPerformed
        // TODO add your handling code here:
        try {
            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
            jTable1.setRowSorter(sorter);

            String text = txt_buscar.getText(); // O texto a ser pesquisado
            if (text.trim().length() == 0) {
                sorter.setRowFilter(null); // Se o campo de pesquisa estiver vazio, remova o filtro
            } else {
                sorter.setRowFilter(RowFilter.regexFilter("(?i)" + text)); // Pesquisa sem diferenciar maiúsculas de minúsculas
            }
            
            
            
        } catch (Exception e) {
            System.out.println("Erro" + e.getMessage());
        }
        
    }//GEN-LAST:event_bt_buscarActionPerformed

    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(RequisitosController.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(RequisitosController.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(RequisitosController.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(RequisitosController.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new RequisitosController().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup bg_qualificacao;
    private javax.swing.JButton bt_buscar;
    private javax.swing.JButton bt_editar;
    private javax.swing.JButton bt_excluir;
    private javax.swing.JButton bt_file_desenho;
    private javax.swing.JButton bt_file_especificacao;
    private javax.swing.JButton bt_imprimir;
    private javax.swing.JButton bt_salvar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JRadioButton rb_func;
    private javax.swing.JRadioButton rb_naofunc;
    private javax.swing.JTextField txt_buscar;
    private javax.swing.JTextArea txt_descricao;
    private javax.swing.JTextField txt_file_desenho;
    private javax.swing.JTextField txt_file_especificacao;
    private javax.swing.JTextField txt_nome;
    // End of variables declaration//GEN-END:variables
}
