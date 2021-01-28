/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.principal;

import com.jcraft.jsch.SftpException;
import connection.ConnectionFactory;
import exception.EnvioExcecao;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import ui.controle.Controle;
import ui.estoque.EnviarEstoque;
import ui.login.TelaAutenticacao;

/**
 *
 * @author 1113778771
 */
public class Estoque extends javax.swing.JFrame {

    /**
     * Creates new form Estoque
     */
    public Estoque() {
        initComponents();
        
        atualizacao.setVisible(false);

        new Thread("Atualizações estoque") {
            @Override
            public void run() {
                while (true) {
                    try {
                        try {
                            if(Controle.verificaVersao(TelaAutenticacao.getCodVersao(), 
                                    TelaAutenticacao.getUpdate())){
                                atualizacao.setVisible(true);
                            }
                            Thread.sleep(600000);
                        } catch (SQLException ex) {
                            Thread.sleep(600000);
                            Logger.getLogger(Estoque.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } catch (InterruptedException ex) {
                        Thread.interrupted();
                        Logger.getLogger(Estoque.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }.start();
        
        Controle.setDefaultGj(new GerenteJanelas(areaDeTrabalho));
        Controle.defineStatus(statusPane);
        loadingHide();

//        URL url = this.getClass().getResource("/ui/login/logo.png");
//        Image imagemLogo = Toolkit.getDefaultToolkit().getImage(url);
//        this.setIconImage(imagemLogo);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        statusPane = new javax.swing.JTextPane();
        loading = new javax.swing.JLabel();
        //ImageIcon icon = new ImageIcon(getClass().getResource("/ui/principal/brasaoGraficaBala-475x288.png"));
        //Image image = icon.getImage();
        areaDeTrabalho = new javax.swing.JDesktopPane(){
            //
            //    public void paintComponent(Graphics g){
                //        Dimension d = areaDeTrabalho.getSize();
                //        g.drawImage(image,(d.width - 475) / 2,(d.height - 288) / 2,475,288,null);
                //    }
            //
        };
        atualizacao = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        menuPrincipal = new javax.swing.JMenu();
        uploadEstoque = new javax.swing.JMenuItem();
        downloadEstoque = new javax.swing.JMenuItem();
        menuModulos = new javax.swing.JMenu();
        menuSair = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setExtendedState(6);

        jScrollPane1.setViewportView(statusPane);

        loading.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        loading.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ui/orcamentos/operacoes/carregando.gif"))); // NOI18N

        areaDeTrabalho.setBackground(java.awt.Color.lightGray);

        javax.swing.GroupLayout areaDeTrabalhoLayout = new javax.swing.GroupLayout(areaDeTrabalho);
        areaDeTrabalho.setLayout(areaDeTrabalhoLayout);
        areaDeTrabalhoLayout.setHorizontalGroup(
            areaDeTrabalhoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        areaDeTrabalhoLayout.setVerticalGroup(
            areaDeTrabalhoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 474, Short.MAX_VALUE)
        );

        atualizacao.setBackground(new java.awt.Color(255, 102, 102));
        atualizacao.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        atualizacao.setText("Uma atualização do sistema está disponível. Quando finalizar as operações, feche esta instância e abra novamente pela INTRANET.");
        atualizacao.setOpaque(true);

        menuPrincipal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/menu.png"))); // NOI18N
        menuPrincipal.setText("MENU");

        uploadEstoque.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/upload.png"))); // NOI18N
        uploadEstoque.setText("UPLOAD ESTOQUE");
        uploadEstoque.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                uploadEstoqueMouseClicked(evt);
            }
        });
        uploadEstoque.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                uploadEstoqueActionPerformed(evt);
            }
        });
        menuPrincipal.add(uploadEstoque);

        downloadEstoque.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/download.png"))); // NOI18N
        downloadEstoque.setText("DOWNLOAD ESTOQUE");
        downloadEstoque.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                downloadEstoqueActionPerformed(evt);
            }
        });
        menuPrincipal.add(downloadEstoque);

        jMenuBar1.add(menuPrincipal);

        menuModulos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/modulos.png"))); // NOI18N
        menuModulos.setText("MÓDULOS");
        menuModulos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuModulosMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                menuModulosMousePressed(evt);
            }
        });
        menuModulos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuModulosActionPerformed(evt);
            }
        });
        jMenuBar1.add(menuModulos);

        menuSair.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/sair.png"))); // NOI18N
        menuSair.setText("SAIR");
        menuSair.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuSairMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                menuSairMousePressed(evt);
            }
        });
        jMenuBar1.add(menuSair);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1030, Short.MAX_VALUE)
            .addComponent(loading, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(areaDeTrabalho)
            .addComponent(atualizacao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(areaDeTrabalho)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(atualizacao)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(loading, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void uploadEstoqueMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_uploadEstoqueMouseClicked
        Controle.getDefaultGj().abrirJanelas(EnviarEstoque.getInstancia(), "UPLOAD DE ESTOQUE");
    }//GEN-LAST:event_uploadEstoqueMouseClicked

    private void uploadEstoqueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_uploadEstoqueActionPerformed
        Controle.getDefaultGj().abrirJanelas(EnviarEstoque.getInstancia(), "UPLOAD DE ESTOQUE");
    }//GEN-LAST:event_uploadEstoqueActionPerformed

    private void downloadEstoqueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_downloadEstoqueActionPerformed
        downloadEstoqueFunction((byte) 1);
    }//GEN-LAST:event_downloadEstoqueActionPerformed

    private void menuModulosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuModulosMouseClicked

    }//GEN-LAST:event_menuModulosMouseClicked

    private void menuModulosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuModulosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_menuModulosActionPerformed

    private void menuSairMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuSairMouseClicked

    }//GEN-LAST:event_menuSairMouseClicked

    private void menuModulosMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuModulosMousePressed
        ModulosInt m = new ModulosInt();
        m.setLocationRelativeTo(null);
        m.setDefaultCloseOperation(EXIT_ON_CLOSE);
        m.setTitle("MÓDULOS");
        m.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_menuModulosMousePressed

    private void menuSairMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuSairMousePressed
        Controle.sair(this);
    }//GEN-LAST:event_menuSairMousePressed

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
            java.util.logging.Logger.getLogger(Estoque.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Estoque.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Estoque.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Estoque.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Estoque().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDesktopPane areaDeTrabalho;
    public static javax.swing.JLabel atualizacao;
    private javax.swing.JMenuItem downloadEstoque;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JLabel loading;
    private javax.swing.JMenu menuModulos;
    private javax.swing.JMenu menuPrincipal;
    private javax.swing.JMenu menuSair;
    private javax.swing.JTextPane statusPane;
    private javax.swing.JMenuItem uploadEstoque;
    // End of variables declaration//GEN-END:variables
public static void loadingVisible(String texto) {
        loading.setVisible(true);
        loading.setText(texto);
    }

    public static void loadingHide() {
        loading.setVisible(false);
        loading.setText("");
    }

    /*
    @param className
    1 - estoque
    2 - orcamento
     */
    public static void downloadEstoqueFunction(byte className) {
        new Thread() {
            @Override
            public void run() {
                try {
                    if (className == 1) {
                        Estoque.loadingVisible("CONECTANDO AO SERVIDOR...");
                    } else {
                        OrcamentoFrame.loadingVisible("CONECTANDO AO SERVIDOR...");
                    }

                    if (ConnectionFactory.downloadSSH(Controle.TEMP_DIR, className)) {
                        if (className == 1) {
                            Estoque.loadingVisible("ABRINDO O ARQUIVO...");
                        } else {
                            OrcamentoFrame.loadingVisible("ABRINDO O ARQUIVO...");
                        }
                        File file = new File(Controle.TEMP_DIR);
                        if (file.isDirectory()) {
                            File[] files = file.listFiles();
                            for (int i = 0; i < files.length; ++i) {
                                if (files[i].getName().contains(Controle.ESTOQUE_NAME)) {
                                    try {
                                        java.awt.Desktop.getDesktop().open(files[i]);
                                        if (className == 1) {
                                            Estoque.loadingHide();
                                        } else {
                                            OrcamentoFrame.loadingHide();
                                        }
                                        return;
                                    } catch (IOException ex) {
                                        EnvioExcecao envioExcecao = new EnvioExcecao(Controle.getDefaultGj(), ex);
                                        EnvioExcecao.envio();
                                        if (className == 1) {
                                            Estoque.loadingHide();
                                        } else {
                                            OrcamentoFrame.loadingHide();
                                        }
                                        return;
                                    }
                                }
                            }
                        }
                    }
                } catch (Exception ex) {
                    EnvioExcecao envioExcecao = new EnvioExcecao(Controle.getDefaultGj(), ex);
                    EnvioExcecao.envio();
                }
            }
        }.start();
    }
}
