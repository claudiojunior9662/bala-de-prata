/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.administrador;

import exception.EnvioExcecao;
import ui.login.TelaAutenticacao;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.tabelas.UsuarioTableModel;
import ui.controle.Controle;

/**
 *
 * @author claud
 */
public class UsuarioCadastro extends javax.swing.JInternalFrame {

    private static UsuarioCadastro usuarioCadastro;
    UsuarioTableModel model = new UsuarioTableModel();

    public static UsuarioCadastro getInstancia() {
        return new UsuarioCadastro();
    }

    /**
     * Creates new form CadastroFuncionariosNovo
     */
    public UsuarioCadastro() {
        initComponents();
        excluir.setEnabled(false);
        pesquisaTexto.setText("");
        pesquisaComboAux.setEnabled(false);
        resetarSenha.setEnabled(false);
        editar.setEnabled(false);
        ativar.setEnabled(false);
        desativar.setEnabled(false);
        
        
        tabelaAtendentes.setModel(model);
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
        jLabel1 = new javax.swing.JLabel();
        nomeAtendente = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        novoLogin = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        tipoAtendente = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        codigoAtendente = new javax.swing.JFormattedTextField();
        cadastrar = new javax.swing.JButton();
        novaSenha = new javax.swing.JPasswordField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaAtendentes = new javax.swing.JTable();
        excluir = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        pesquisaCombo = new javax.swing.JComboBox<>();
        pesquisaComboAux = new javax.swing.JComboBox<>();
        pesquisaTexto = new javax.swing.JTextField();
        pesquisar = new javax.swing.JButton();
        editar = new javax.swing.JButton();
        desativar = new javax.swing.JButton();
        ativar = new javax.swing.JButton();
        resetarSenha = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblAcessos = new javax.swing.JTable();

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "CADASTRO DE ATENDENTES", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N

        jLabel1.setText("NOME:");

        jLabel2.setText("CÓDIGO:");

        jLabel3.setText("NOVO LOGIN:");

        jLabel4.setText("NOVA SENHA:");

        tipoAtendente.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SELECIONE...", "ADMINISTRADOR", "USUÁRIO" }));
        tipoAtendente.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                tipoAtendenteItemStateChanged(evt);
            }
        });
        tipoAtendente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tipoAtendenteActionPerformed(evt);
            }
        });

        jLabel5.setText("TIPO:");

        try {
            codigoAtendente.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("???")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        cadastrar.setText("CADASTRAR");
        cadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cadastrarActionPerformed(evt);
            }
        });

        tabelaAtendentes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "NOME", "CÓDIGO", "LOGIN", "TIPO", "ORC", "PROD", "EXP", "FIN", "EST", "ATIVO"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabelaAtendentes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelaAtendentesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabelaAtendentes);

        excluir.setText("EXCLUIR");
        excluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                excluirActionPerformed(evt);
            }
        });

        jPanel2.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel6.setText("PESQUISA POR:");

        pesquisaCombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SELECIONE...", "NOME ATENDENTE", "CÓDIGO ATENDENTE", "LOGIN ATENDENTE", "TIPO ATENDENTE", "ACESSO PRODUÇÃO", "ACESSO ORÇAMENTAÇÃO" }));
        pesquisaCombo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                pesquisaComboItemStateChanged(evt);
            }
        });

        pesquisaComboAux.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SELECIONE..." }));

        pesquisar.setText("PESQUISAR");
        pesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pesquisarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pesquisaCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pesquisaComboAux, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pesquisaTexto, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pesquisar)
                .addGap(140, 140, 140))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel6)
                    .addComponent(pesquisaCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pesquisaComboAux, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pesquisaTexto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pesquisar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        editar.setText("EDITAR");
        editar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editarActionPerformed(evt);
            }
        });

        desativar.setText("DESATIVAR");
        desativar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                desativarActionPerformed(evt);
            }
        });

        ativar.setText("ATIVAR");
        ativar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ativarActionPerformed(evt);
            }
        });

        resetarSenha.setText("RESETAR SENHA");
        resetarSenha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetarSenhaActionPerformed(evt);
            }
        });

        tblAcessos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"USUÁRIO", null, null, null, null, null, null},
                {"ADMIN", null, null, null, null,  new Boolean(false), null}
            },
            new String [] {
                "TIPO ACESSO", "ORÇAMENTO", "PRODUÇÃO", "EXPEDIÇÃO", "FINANCEIRO", "ESTOQUE", "ORD DESPESAS"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tblAcessos);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(nomeAtendente, javax.swing.GroupLayout.PREFERRED_SIZE, 339, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(codigoAtendente, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(novoLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(novaSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(tipoAtendente, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(resetarSenha)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ativar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(desativar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(excluir)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(editar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cadastrar))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nomeAtendente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(novoLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tipoAtendente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(codigoAtendente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(novaSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cadastrar)
                    .addComponent(excluir)
                    .addComponent(editar)
                    .addComponent(desativar)
                    .addComponent(ativar)
                    .addComponent(resetarSenha))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tipoAtendenteItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_tipoAtendenteItemStateChanged

    }//GEN-LAST:event_tipoAtendenteItemStateChanged

    private void tipoAtendenteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tipoAtendenteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tipoAtendenteActionPerformed

    private void cadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cadastrarActionPerformed

    }//GEN-LAST:event_cadastrarActionPerformed

    private void tabelaAtendentesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelaAtendentesMouseClicked
        UsuarioBEAN usuarioSel = model.getValueAt(tabelaAtendentes.getSelectedRow());
        
        nomeAtendente.setText(usuarioSel.getNome());
        codigoAtendente.setText(usuarioSel.getCodigo());
        novoLogin.setText(usuarioSel.getLogin());
        tipoAtendente.setSelectedItem(usuarioSel.getTipo());
        
        resetarSenha.setEnabled(true);
        editar.setEnabled(true);
        ativar.setEnabled(true);
        desativar.setEnabled(true);
        
        if(usuarioSel.getAcessoOrc() == 1){
            if(usuarioSel.getAcessoOrcAdm() == 1){
                tblAcessos.setValueAt(true, 1, 1);
            }else{
                tblAcessos.setValueAt(true, 0, 1);
            }
        }if(usuarioSel.getAcessoProd() == 1){
            if(usuarioSel.getAcessoProdAdm() == 1){
                tblAcessos.setValueAt(true, 1, 2);
            }else{
                tblAcessos.setValueAt(true, 0, 2);
            }
        }if(usuarioSel.getAcessoExp() == 1){
            if(usuarioSel.getAcessoExpAdm() == 1){
                tblAcessos.setValueAt(true, 1, 3);
            }else{
                tblAcessos.setValueAt(true, 0, 3);
            }
        }if(usuarioSel.getAcessoFin() == 1){
            if(usuarioSel.getAcessoFinAdm() == 1){
                tblAcessos.setValueAt(true, 1, 4);
            }else{
                tblAcessos.setValueAt(true, 0, 4);
            }
        }if(usuarioSel.getAcessoEst() == 1){
            tblAcessos.setValueAt(true, 0, 5);
        }if(usuarioSel.getAcessoOrd() == 1){
            tblAcessos.setValueAt(true, 0, 6);
        }
    }//GEN-LAST:event_tabelaAtendentesMouseClicked

    private void excluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_excluirActionPerformed
        
    }//GEN-LAST:event_excluirActionPerformed

    private void pesquisaComboItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_pesquisaComboItemStateChanged
        
    }//GEN-LAST:event_pesquisaComboItemStateChanged

    private void pesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pesquisarActionPerformed
        
    }//GEN-LAST:event_pesquisarActionPerformed

    private void editarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editarActionPerformed

    }//GEN-LAST:event_editarActionPerformed

    private void desativarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_desativarActionPerformed
        try {
            UsuarioDAO.desativaAtivaFuncionario(0, (String) tabelaAtendentes.getValueAt(tabelaAtendentes.getSelectedRow(), 1));
            JOptionPane.showMessageDialog(null, "ATENDENTE DESATIVADO COM SUCESSO", "CONFIRMAÇÃO", JOptionPane.INFORMATION_MESSAGE);
            carregaLista(null, null, null);
        } catch (SQLException ex) {
            EnvioExcecao envioExcecao = new EnvioExcecao(Controle.getDefaultGj(), ex);
            EnvioExcecao.envio();
        }
    }//GEN-LAST:event_desativarActionPerformed

    private void ativarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ativarActionPerformed
        try {
            UsuarioDAO.desativaAtivaFuncionario(1, (String) tabelaAtendentes.getValueAt(tabelaAtendentes.getSelectedRow(), 1));
            JOptionPane.showMessageDialog(null, "ATENDENTE ATIVADO COM SUCESSO", "CONFIRMAÇÃO", JOptionPane.INFORMATION_MESSAGE);
            carregaLista(null, null, null);
        } catch (SQLException ex) {
            EnvioExcecao envioExcecao = new EnvioExcecao(Controle.getDefaultGj(), ex);
            EnvioExcecao.envio();
        }
    }//GEN-LAST:event_ativarActionPerformed

    private void resetarSenhaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetarSenhaActionPerformed
        int dialogButton = JOptionPane.YES_NO_OPTION;
        int dialogResult = JOptionPane.showConfirmDialog(this, "A SENHA SERÁ O LOGIN DO ATENDENTE COM LETRAS MINÚSCULAS", "ATENÇÃO!", dialogButton);
        if (dialogResult != 0) {
            return;
        } else {
            if (tabelaAtendentes.getValueAt(tabelaAtendentes.getSelectedRow(), 2).toString().equals("")
                    | tabelaAtendentes.getValueAt(tabelaAtendentes.getSelectedRow(), 2) == null) {
                JOptionPane.showMessageDialog(null, "SELECIONE UM ATENDENTE PARA CONTINUAR", "ERRO", JOptionPane.ERROR_MESSAGE);
                return;
            }
            try {
                UsuarioDAO.resetaSenha(tabelaAtendentes.getValueAt(tabelaAtendentes.getSelectedRow(), 2).toString().toLowerCase());
                JOptionPane.showMessageDialog(null, "SENHA RESETADA COM SUCESSO", "CONFIRMAÇÃO", JOptionPane.INFORMATION_MESSAGE);
            } catch (SQLException ex) {
                EnvioExcecao envioExcecao = new EnvioExcecao(Controle.getDefaultGj(), ex);
                EnvioExcecao.envio();
            }
        }
    }//GEN-LAST:event_resetarSenhaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ativar;
    private javax.swing.JButton cadastrar;
    public static javax.swing.JFormattedTextField codigoAtendente;
    private javax.swing.JButton desativar;
    private javax.swing.JButton editar;
    private javax.swing.JButton excluir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    public static javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    public static javax.swing.JTextField nomeAtendente;
    public static javax.swing.JPasswordField novaSenha;
    public static javax.swing.JTextField novoLogin;
    private javax.swing.JComboBox<String> pesquisaCombo;
    private javax.swing.JComboBox<String> pesquisaComboAux;
    private javax.swing.JTextField pesquisaTexto;
    private javax.swing.JButton pesquisar;
    private javax.swing.JButton resetarSenha;
    private javax.swing.JTable tabelaAtendentes;
    private javax.swing.JTable tblAcessos;
    public static javax.swing.JComboBox<String> tipoAtendente;
    // End of variables declaration//GEN-END:variables
 public void limpa() {
        nomeAtendente.setText("");
        novaSenha.setText("");
        codigoAtendente.setText("");
        novoLogin.setText("");
        tipoAtendente.setSelectedIndex(0);
    }

    public void carregaLista(String tipo, String tipoAux, String texto) {
        try {
            DefaultTableModel modeloAtendentes = (DefaultTableModel) tabelaAtendentes.getModel();
            modeloAtendentes.setNumRows(0);

            if (tipo == null && tipoAux == null && texto == null) {
                for (UsuarioBEAN usuario : UsuarioDAO.carregaLista()) {
                    model.addRow(usuario);
                }
            } else {
                for (UsuarioBEAN cadastroFuncionariosBEAN : 
                        UsuarioDAO.retornaPesquisa(tipo, tipoAux, texto)) {

                    modeloAtendentes.addRow(new Object[]{
                        cadastroFuncionariosBEAN.getNome(),
                        cadastroFuncionariosBEAN.getCodigo(),
                        cadastroFuncionariosBEAN.getLogin(),
                        cadastroFuncionariosBEAN.getTipo(),
                        cadastroFuncionariosBEAN.getAcessoOrc() == 1 ? "SIM" : "NÃO",
                        cadastroFuncionariosBEAN.getAcessoProd() == 1 ? "SIM" : "NÃO",
                        cadastroFuncionariosBEAN.getAcessoExp() == 1 ? "SIM" : "NÃO",
                        cadastroFuncionariosBEAN.getAcessoFin() == 1 ? "SIM" : "NÃO",
                        cadastroFuncionariosBEAN.getAcessoEst() == 1 ? "SIM" : "NÃO",
                        cadastroFuncionariosBEAN.getAtivo() == 1 ? "SIM" : "NÃO"

                    });
                }
            }
        } catch (SQLException ex) {
            EnvioExcecao envioExcecao = new EnvioExcecao(Controle.getDefaultGj(), ex);
            EnvioExcecao.envio();
        }
    }

}
