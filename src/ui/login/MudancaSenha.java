/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.login;

import ui.controle.Controle;
import exception.AtualizaSenhaException;
import exception.EnvioExcecao;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author 1113778771
 */
public class MudancaSenha extends javax.swing.JFrame {

    public static String SENHA = null;

    /**
     * Creates new form MudançaSenha
     */
    public MudancaSenha() {
        initComponents();

        avisos.setVisible(true);
        avisos.setEditable(false);
        avisos.setText("PARA AUMENTAR A SEGURANÇA, UTILIZE UMA SENHA QUE CONTENHA LETRAS E NÚMEROS.\n\nSUA SENHA É PESSOAL E INTRANSFERÍVEL.");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        senhaAnterior = new javax.swing.JPasswordField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        confirmarSenha = new javax.swing.JPasswordField();
        novaSenha = new javax.swing.JPasswordField();
        atualizarSenha = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        avisos = new javax.swing.JTextPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel1.setText("SENHA ANTERIOR:");

        senhaAnterior.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                senhaAnteriorFocusLost(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel2.setText("NOVA SENHA:");

        jLabel3.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel3.setText("CONFIRME A NOVA SENHA:");

        confirmarSenha.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                confirmarSenhaFocusLost(evt);
            }
        });

        novaSenha.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                novaSenhaFocusLost(evt);
            }
        });

        atualizarSenha.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/atualizar.png"))); // NOI18N
        atualizarSenha.setText("ATUALIZAR");
        atualizarSenha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                atualizarSenhaActionPerformed(evt);
            }
        });

        avisos.setFont(new java.awt.Font("Ubuntu", 0, 10)); // NOI18N
        jScrollPane1.setViewportView(avisos);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(67, 67, 67)
                                    .addComponent(jLabel1)
                                    .addGap(12, 12, 12)
                                    .addComponent(senhaAnterior, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(97, 97, 97)
                                    .addComponent(jLabel2)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(novaSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel3)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(confirmarSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(atualizarSenha, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {confirmarSenha, novaSenha, senhaAnterior});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(senhaAnterior, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(novaSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(confirmarSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(atualizarSenha)
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jLabel2, novaSenha});

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void atualizarSenhaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_atualizarSenhaActionPerformed
        if (!senhaAnterior.equals("") && !novaSenha.equals("") && !confirmarSenha.equals("")) {
            try {
                LoginDAO.atualizaSenha(confirmarSenha.getText(), TelaAutenticacao.getUsrLogado().getCodigo());
                JOptionPane.showMessageDialog(null, "FAÇA O LOGIN UTILIZANDO A NOVA SENHA", "SENHA ATUALIZADA", JOptionPane.PLAIN_MESSAGE);
                TelaAutenticacao.campoUsuario.setText(TelaAutenticacao.getUsrLogado().getLogin());
                TelaAutenticacao.campoSenha.setText("");
                this.dispose();
            } catch (AtualizaSenhaException ex) {
                EnvioExcecao envioExcecao = new EnvioExcecao(Controle.getDefaultGj(), ex);
                EnvioExcecao.envio();
            }
        }
    }//GEN-LAST:event_atualizarSenhaActionPerformed

    private void senhaAnteriorFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_senhaAnteriorFocusLost
        try {
            if (!LoginDAO.verificaSenhaAntiga(senhaAnterior.getText(), TelaAutenticacao.getUsrLogado().getCodigo())) {
                avisos.setText("SENHA ANTIGA NÃO CONFERE");
                senhaAnterior.setText("");
                senhaAnterior.setBorder(Controle.bordaLinhaVermelha);
            } else {
                avisos.setText("");
                senhaAnterior.setBorder(Controle.bordaLinhaVerde);
            }
        } catch (SQLException ex) {
            EnvioExcecao envioExcecao = new EnvioExcecao(Controle.getDefaultGj(), ex);
            EnvioExcecao.envio();
        }
    }//GEN-LAST:event_senhaAnteriorFocusLost

    private void novaSenhaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_novaSenhaFocusLost
        String novaSenhaAux = novaSenha.getText();
        if (!senhaAnterior.getText().equals("")) {
            if (novaSenha.getText().equals(senhaAnterior.getText())) {
                avisos.setText("A NOVA SENHA NÃO PODE SER IGUAL A SENHA ANTERIOR.");
                novaSenha.setText("");
                novaSenha.setBorder(Controle.bordaLinhaVermelha);
            } else if (novaSenhaAux.length() < 8) {
                avisos.setText("A NOVA SENHA DEVE CONTER MAIS DE 8 CARACTERES.");
                novaSenha.setText("");
                novaSenha.setBorder(Controle.bordaLinhaVermelha);
            } else if (!verificaCaracteres(novaSenhaAux.toCharArray())) {
                avisos.setText("A NOVA SENHA DEVE TER AO MENOS 1 LETRA MAIÚSCULA E 1 NÚMERO.");
                novaSenha.setText("");
                novaSenha.setBorder(Controle.bordaLinhaVermelha);
            } else {
                avisos.setText("");
                novaSenha.setBorder(Controle.bordaLinhaVerde);
            }
        }
    }//GEN-LAST:event_novaSenhaFocusLost

    private void confirmarSenhaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_confirmarSenhaFocusLost
        String confirmarSenhaAux = confirmarSenha.getText();
        if (!novaSenha.getText().equals("")) {
            if (!novaSenha.getText().equals(confirmarSenhaAux)) {
                avisos.setText("AS SENHAS NÃO CONFEREM!");
                confirmarSenha.setText("");
            } else {
                avisos.setText("");
                avisos.setText("");
                confirmarSenha.setBorder(Controle.bordaLinhaVerde);
            }
        }
    }//GEN-LAST:event_confirmarSenhaFocusLost

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
            java.util.logging.Logger.getLogger(MudancaSenha.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MudancaSenha.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MudancaSenha.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MudancaSenha.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MudancaSenha().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton atualizarSenha;
    private javax.swing.JTextPane avisos;
    private javax.swing.JPasswordField confirmarSenha;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPasswordField novaSenha;
    private javax.swing.JPasswordField senhaAnterior;
    // End of variables declaration//GEN-END:variables
    public Boolean verificaCaracteres(char[] array) {
        Integer letrasMaiusculas = 0;
        Integer numeros = 0;
        for (char c : array) {
            if (Character.isUpperCase(c)) {
                letrasMaiusculas++;
            }
            if (Character.isDigit(c)) {
                numeros++;
            }
        }
        if (letrasMaiusculas > 0 && numeros > 0) {
            return true;
        } else {
            return false;
        }
    }
}
