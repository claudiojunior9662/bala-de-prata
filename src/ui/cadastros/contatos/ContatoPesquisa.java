/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.cadastros.contatos;

import exception.EnvioExcecao;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import ui.cadastros.clientes.ClienteDAO;
import javax.swing.table.DefaultTableModel;
import ui.cadastros.clientes.ClienteCadastro;
import ui.controle.Controle;

/**
 *
 * @author claudio
 */
public class ContatoPesquisa extends javax.swing.JFrame {
    public static boolean selecionarClientes = false;
    public static boolean selecionarTransportadoras = false;
    
    public static int CODIGO_CONTATO = 0;
    
    public ContatoPesquisa() {
        initComponents();
        botaoAdicionarTabela.setEnabled(false);
        botaoExcluir.setEnabled(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        p1 = new javax.swing.JComboBox<>();
        p2 = new javax.swing.JTextField();
        botaoPesquisar = new javax.swing.JButton();
        botaoMostrarTodos = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaPesquisa = new javax.swing.JTable();
        botaoAdicionarTabela = new javax.swing.JButton();
        botaoExcluir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1033, 540));

        jPanel1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        p1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "CÓDIGO", "NOME CONTATO", "EMAIL", "DEPARTAMENTO" }));
        p1.setBorder(javax.swing.BorderFactory.createTitledBorder("PESQUISA POR"));

        botaoPesquisar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/pesquisar.png"))); // NOI18N
        botaoPesquisar.setText("PESQUISAR");
        botaoPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoPesquisarActionPerformed(evt);
            }
        });

        botaoMostrarTodos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/ultimos.png"))); // NOI18N
        botaoMostrarTodos.setText("MOSTRAR TODOS");
        botaoMostrarTodos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoMostrarTodosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(p1, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(p2, javax.swing.GroupLayout.DEFAULT_SIZE, 419, Short.MAX_VALUE)
                .addGap(221, 221, 221)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(botaoPesquisar)
                    .addComponent(botaoMostrarTodos))
                .addContainerGap())
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {botaoMostrarTodos, botaoPesquisar});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(botaoPesquisar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(botaoMostrarTodos)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(p2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(p1, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {p1, p2});

        jPanel2.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        tabelaPesquisa.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CÓDIGO", "NOME CONTATO", "EMAIL", "TELEFONE", "RAMAL", "TELEFONE 2", "RAMAL 2", "DEPARTAMENTO"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabelaPesquisa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelaPesquisaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabelaPesquisa);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 339, Short.MAX_VALUE)
        );

        botaoAdicionarTabela.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/incluir.png"))); // NOI18N
        botaoAdicionarTabela.setText("ADICIONAR À TABELA DE CONTATOS");
        botaoAdicionarTabela.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoAdicionarTabelaActionPerformed(evt);
            }
        });

        botaoExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/desativar.png"))); // NOI18N
        botaoExcluir.setText("DESATIVAR");
        botaoExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoExcluirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(botaoExcluir)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botaoAdicionarTabela)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botaoAdicionarTabela)
                    .addComponent(botaoExcluir))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botaoMostrarTodosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoMostrarTodosActionPerformed
        try{
            DefaultTableModel modeloPesquisa = (DefaultTableModel) tabelaPesquisa.getModel();
            modeloPesquisa.setNumRows(0);
            for(ContatoBEAN auxBEAN : ClienteDAO.retornaPesquisaContatos("", "", true)){
                modeloPesquisa.addRow(new Object[]{
                    auxBEAN.getCod(),
                    auxBEAN.getNomeContato(),
                    auxBEAN.getEmail(),
                    auxBEAN.getTelefone(),
                    auxBEAN.getRamal(),
                    auxBEAN.getTelefone2(),
                    auxBEAN.getRamal2(),
                    auxBEAN.getDepartamento()
                });
            }
        }catch(SQLException ex){
            EnvioExcecao envioExcecao = new EnvioExcecao(Controle.getDefaultGj(), ex);
            EnvioExcecao.envio();
        }
    }//GEN-LAST:event_botaoMostrarTodosActionPerformed

    private void botaoPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoPesquisarActionPerformed
        try{
            ClienteDAO auxDAO = new ClienteDAO();
            DefaultTableModel modeloPesquisa = (DefaultTableModel) tabelaPesquisa.getModel();
            modeloPesquisa.setNumRows(0);
            for(ContatoBEAN auxBEAN : auxDAO.retornaPesquisaContatos(p1.getSelectedItem().toString(), p2.getText(), false)){
                modeloPesquisa.addRow(new Object[]{
                    auxBEAN.getCod(),
                    auxBEAN.getNomeContato(),
                    auxBEAN.getEmail(),
                    auxBEAN.getTelefone(),
                    auxBEAN.getRamal(),
                    auxBEAN.getTelefone2(),
                    auxBEAN.getRamal2(),
                    auxBEAN.getDepartamento()
                });
            }
        }catch(SQLException ex){
            EnvioExcecao envioExcecao = new EnvioExcecao(Controle.getDefaultGj(), ex);
            EnvioExcecao.envio();
        }
    }//GEN-LAST:event_botaoPesquisarActionPerformed

    private void botaoAdicionarTabelaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoAdicionarTabelaActionPerformed
        if(selecionarClientes == true){
            DefaultTableModel modeloContatos = (DefaultTableModel) ClienteCadastro.tabelaContatos.getModel();
            modeloContatos.addRow(new Object[]{
                tabelaPesquisa.getValueAt(tabelaPesquisa.getSelectedRow(), 0),
                tabelaPesquisa.getValueAt(tabelaPesquisa.getSelectedRow(), 1),
                tabelaPesquisa.getValueAt(tabelaPesquisa.getSelectedRow(), 2),
                tabelaPesquisa.getValueAt(tabelaPesquisa.getSelectedRow(), 3),
                tabelaPesquisa.getValueAt(tabelaPesquisa.getSelectedRow(), 4),
                tabelaPesquisa.getValueAt(tabelaPesquisa.getSelectedRow(), 5),
                tabelaPesquisa.getValueAt(tabelaPesquisa.getSelectedRow(), 6),
                tabelaPesquisa.getValueAt(tabelaPesquisa.getSelectedRow(), 7)
            });
            ClienteCadastro.tabelaContatos.setEnabled(true);
            this.dispose();
        }
    }//GEN-LAST:event_botaoAdicionarTabelaActionPerformed

    private void tabelaPesquisaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelaPesquisaMouseClicked
        botaoAdicionarTabela.setEnabled(true);
        botaoExcluir.setEnabled(true);
    }//GEN-LAST:event_tabelaPesquisaMouseClicked

    private void botaoExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoExcluirActionPerformed
        CODIGO_CONTATO = Integer.valueOf(tabelaPesquisa.getValueAt(tabelaPesquisa.getSelectedRow(), 0).toString());
        
        try{
            int dialogButton = JOptionPane.YES_NO_OPTION;
            int dialogResult = JOptionPane.showConfirmDialog(this, "ESTA AÇÃO PODERÁ AFETAR OUTROS CLIENTES RELACIONADOS A ESTE CONTATO.\nDESEJA CONTINUAR?", "ATENÇÃO!", dialogButton);
            if(dialogResult == 0){
                 ClienteDAO.desativaAtivaContatos(0, CODIGO_CONTATO, (byte) 0, (byte) 1, false);
            }
            botaoExcluir.setEnabled(false);

            DefaultTableModel modeloPesquisa = (DefaultTableModel) tabelaPesquisa.getModel();
            modeloPesquisa.setNumRows(0);
            for(ContatoBEAN auxBEAN : ClienteDAO.retornaPesquisaContatos("", "", true)){
                modeloPesquisa.addRow(new Object[]{
                    auxBEAN.getCod(),
                    auxBEAN.getNomeContato(),
                    auxBEAN.getEmail(),
                    auxBEAN.getTelefone(),
                    auxBEAN.getRamal(),
                    auxBEAN.getTelefone2(),
                    auxBEAN.getRamal2(),
                    auxBEAN.getDepartamento()
                });
            }
        }catch(SQLException ex){
            EnvioExcecao envioExcecao = new EnvioExcecao(Controle.getDefaultGj(), ex);
            EnvioExcecao.envio();
        }
    }//GEN-LAST:event_botaoExcluirActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ContatoPesquisa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ContatoPesquisa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ContatoPesquisa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ContatoPesquisa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ContatoPesquisa().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botaoAdicionarTabela;
    private javax.swing.JButton botaoExcluir;
    private javax.swing.JButton botaoMostrarTodos;
    private javax.swing.JButton botaoPesquisar;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox<String> p1;
    private javax.swing.JTextField p2;
    private javax.swing.JTable tabelaPesquisa;
    // End of variables declaration//GEN-END:variables
}
