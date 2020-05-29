/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.relatorios.financeiro;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfWriter;
import entidades.Cliente;
import exception.EnvioExcecao;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import ui.controle.Controle;
import ui.login.TelaAutenticacao;

/**
 *
 * @author spd3
 */
public class RelatorioFin extends javax.swing.JInternalFrame {

    DefaultListModel model = new DefaultListModel();
    int qtdSelecionada = 0;
    public static Cliente cliente;

    JLabel loading;

    private static RelatorioFin relatoriosNotaVenda;

    public static RelatorioFin getInstancia(JLabel loading) {
        return new RelatorioFin(loading);
    }

    public RelatorioFin(JLabel loading) {
            initComponents();
            
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        grupoCliente = new javax.swing.ButtonGroup();
        grupoOpOrcamento = new javax.swing.ButtonGroup();
        grupoEmissor = new javax.swing.ButtonGroup();
        grupoPeriodo = new javax.swing.ButtonGroup();
        grupoTransporte = new javax.swing.ButtonGroup();
        grupoOrdenar = new javax.swing.ButtonGroup();
        grupoTipoPessoa = new javax.swing.ButtonGroup();
        grupoOrientacao = new javax.swing.ButtonGroup();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        porTipoPessoa = new javax.swing.JRadioButton();
        pessoaJuridica = new javax.swing.JRadioButton();
        pessoaFisica = new javax.swing.JRadioButton();
        porTodosClientes = new javax.swing.JRadioButton();
        jPanel3 = new javax.swing.JPanel();
        jdcFimPeriodo = new com.toedter.calendar.JDateChooser();
        jrbTodos = new javax.swing.JRadioButton();
        jrbPorPeriodo = new javax.swing.JRadioButton();
        jLabel1 = new javax.swing.JLabel();
        jdcInicioPeriodo = new com.toedter.calendar.JDateChooser();
        jLabel2 = new javax.swing.JLabel();
        jrbPorAno = new javax.swing.JRadioButton();
        jycAno = new com.toedter.calendar.JYearChooser();
        jrbPorMes = new javax.swing.JRadioButton();
        jmcMes = new com.toedter.calendar.JMonthChooser();
        jycAnoMes = new com.toedter.calendar.JYearChooser();
        jPanel5 = new javax.swing.JPanel();
        porCodigoCrescente = new javax.swing.JRadioButton();
        porCodigoDecrescente = new javax.swing.JRadioButton();
        porCodigoOpCrescente = new javax.swing.JRadioButton();
        porCodigoOpDecrescente = new javax.swing.JRadioButton();
        porQuantidadeCrescente = new javax.swing.JRadioButton();
        porQuantidadeDecrescente = new javax.swing.JRadioButton();
        porEmissorOrdenar = new javax.swing.JRadioButton();
        porTipoPessoaOrdenar = new javax.swing.JRadioButton();
        porValorCrescente = new javax.swing.JRadioButton();
        porValorDecrescente = new javax.swing.JRadioButton();
        porDataMaisAtual = new javax.swing.JRadioButton();
        porDataMaisAntiga = new javax.swing.JRadioButton();
        semOrdenacao = new javax.swing.JRadioButton();
        botaoGeraRelatorio1 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        rBtnRetrato = new javax.swing.JRadioButton();
        rBtnPaisagem = new javax.swing.JRadioButton();

        setTitle("RELATÓRIO DE FATURAMENTOS");
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/faturamento.png"))); // NOI18N
        setPreferredSize(new java.awt.Dimension(900, 340));

        jTabbedPane1.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);

        grupoCliente.add(porTipoPessoa);
        porTipoPessoa.setText("POR TIPO DE PESSOA");
        porTipoPessoa.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                porTipoPessoaItemStateChanged(evt);
            }
        });

        grupoTipoPessoa.add(pessoaJuridica);
        pessoaJuridica.setText("PESSOA JURÍDICA (2/PJ)");

        grupoTipoPessoa.add(pessoaFisica);
        pessoaFisica.setText("PESSOA FÍSICA (1/PF)");

        grupoCliente.add(porTodosClientes);
        porTodosClientes.setText("TODOS");
        porTodosClientes.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                porTodosClientesItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(290, 290, 290)
                        .addComponent(pessoaJuridica))
                    .addComponent(porTodosClientes)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(150, 150, 150)
                        .addComponent(pessoaFisica))
                    .addComponent(porTipoPessoa))
                .addContainerGap(422, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(porTipoPessoa)
                    .addComponent(pessoaFisica)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(porTodosClientes))
                    .addComponent(pessoaJuridica))
                .addContainerGap(205, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("CLIENTE", new javax.swing.ImageIcon(getClass().getResource("/icones/cliente.png")), jPanel1); // NOI18N

        grupoPeriodo.add(jrbTodos);
        jrbTodos.setText("TODOS");
        jrbTodos.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jrbTodosItemStateChanged(evt);
            }
        });

        grupoPeriodo.add(jrbPorPeriodo);
        jrbPorPeriodo.setText("POR PERÍODO");
        jrbPorPeriodo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jrbPorPeriodoItemStateChanged(evt);
            }
        });

        jLabel1.setText("INICÍO");

        jLabel2.setText("FIM");

        grupoPeriodo.add(jrbPorAno);
        jrbPorAno.setText("POR ANO");
        jrbPorAno.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jrbPorAnoItemStateChanged(evt);
            }
        });

        grupoPeriodo.add(jrbPorMes);
        jrbPorMes.setText("POR MÊS");
        jrbPorMes.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jrbPorMesItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jrbPorMes)
                    .addComponent(jrbPorPeriodo)
                    .addComponent(jrbTodos)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jdcInicioPeriodo, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jycAno, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jdcFimPeriodo, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jrbPorAno)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jmcMes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jycAnoMes, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(63, 63, 63)))
                .addContainerGap(503, Short.MAX_VALUE))
        );

        jPanel3Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jdcFimPeriodo, jdcInicioPeriodo});

        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jrbPorPeriodo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jdcInicioPeriodo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jdcFimPeriodo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jrbPorMes)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jmcMes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jycAnoMes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jrbPorAno)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jycAno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jrbTodos)
                .addContainerGap(48, Short.MAX_VALUE))
        );

        jPanel3Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jLabel1, jLabel2, jdcFimPeriodo, jdcInicioPeriodo, jmcMes, jrbPorPeriodo, jycAno, jycAnoMes});

        jTabbedPane1.addTab("PERÍODO", new javax.swing.ImageIcon(getClass().getResource("/icones/periodo.png")), jPanel3); // NOI18N

        grupoOrdenar.add(porCodigoCrescente);
        porCodigoCrescente.setText("POR CÓDIGO CRESCENTE");

        grupoOrdenar.add(porCodigoDecrescente);
        porCodigoDecrescente.setText("POR CÓDIGO DECRESCENTE");

        grupoOrdenar.add(porCodigoOpCrescente);
        porCodigoOpCrescente.setText("POR CÓDIGO OP CRESCENTE");

        grupoOrdenar.add(porCodigoOpDecrescente);
        porCodigoOpDecrescente.setText("POR CÓDIGO OP DECRESCENTE");

        grupoOrdenar.add(porQuantidadeCrescente);
        porQuantidadeCrescente.setText("POR QUANTIDADE CRESCENTE");
        porQuantidadeCrescente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                porQuantidadeCrescenteActionPerformed(evt);
            }
        });

        grupoOrdenar.add(porQuantidadeDecrescente);
        porQuantidadeDecrescente.setText("POR QUANTIDADE DECRESCENTE");
        porQuantidadeDecrescente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                porQuantidadeDecrescenteActionPerformed(evt);
            }
        });

        grupoOrdenar.add(porEmissorOrdenar);
        porEmissorOrdenar.setText("POR EMISSOR");

        grupoOrdenar.add(porTipoPessoaOrdenar);
        porTipoPessoaOrdenar.setText("POR TIPO DE PESSOA");

        grupoOrdenar.add(porValorCrescente);
        porValorCrescente.setText("POR VALOR CRESCENTE");

        grupoOrdenar.add(porValorDecrescente);
        porValorDecrescente.setText("POR VALOR DECRESCENTE");

        grupoOrdenar.add(porDataMaisAtual);
        porDataMaisAtual.setText("POR DATA MAIS ATUAL");

        grupoOrdenar.add(porDataMaisAntiga);
        porDataMaisAntiga.setText("POR DATA MAIS ANTIGA");

        grupoOrdenar.add(semOrdenacao);
        semOrdenacao.setText("SEM ORDENAÇÃO");

        botaoGeraRelatorio1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/confirma.png"))); // NOI18N
        botaoGeraRelatorio1.setText("GERAR RELATÓRIO");
        botaoGeraRelatorio1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoGeraRelatorio1ActionPerformed(evt);
            }
        });

        jLabel3.setText("ORIENTAÇÃO:");

        grupoOrientacao.add(rBtnRetrato);
        rBtnRetrato.setText("RETRATO");

        grupoOrientacao.add(rBtnPaisagem);
        rBtnPaisagem.setText("PAISAGEM");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(botaoGeraRelatorio1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(porCodigoOpDecrescente)
                                    .addComponent(porQuantidadeCrescente)
                                    .addComponent(porQuantidadeDecrescente)
                                    .addComponent(porCodigoCrescente)
                                    .addComponent(porCodigoDecrescente)
                                    .addComponent(porCodigoOpCrescente))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(semOrdenacao)
                                    .addComponent(porDataMaisAntiga)
                                    .addComponent(porDataMaisAtual)
                                    .addComponent(porEmissorOrdenar)
                                    .addComponent(porTipoPessoaOrdenar)
                                    .addComponent(porValorCrescente)
                                    .addComponent(porValorDecrescente)))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(rBtnRetrato)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(rBtnPaisagem)))
                        .addGap(0, 352, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(porCodigoCrescente)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(porCodigoDecrescente)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(porCodigoOpCrescente)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(porCodigoOpDecrescente)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(porQuantidadeCrescente)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(porQuantidadeDecrescente))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addComponent(porEmissorOrdenar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(porTipoPessoaOrdenar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(porValorCrescente)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(porValorDecrescente)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(porDataMaisAtual)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(porDataMaisAntiga)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(semOrdenacao)))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(rBtnRetrato)
                    .addComponent(rBtnPaisagem))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(botaoGeraRelatorio1)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        jPanel5Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jLabel3, rBtnPaisagem, rBtnRetrato});

        jTabbedPane1.addTab("ORDENAR", new javax.swing.ImageIcon(getClass().getResource("/icones/ordenar.png")), jPanel5); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 310, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void porTipoPessoaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_porTipoPessoaItemStateChanged
        
    }//GEN-LAST:event_porTipoPessoaItemStateChanged

    private void porTodosClientesItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_porTodosClientesItemStateChanged
        
    }//GEN-LAST:event_porTodosClientesItemStateChanged

    private void jrbPorPeriodoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jrbPorPeriodoItemStateChanged
        
    }//GEN-LAST:event_jrbPorPeriodoItemStateChanged

    private void porQuantidadeCrescenteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_porQuantidadeCrescenteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_porQuantidadeCrescenteActionPerformed

    private void porQuantidadeDecrescenteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_porQuantidadeDecrescenteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_porQuantidadeDecrescenteActionPerformed

    private void jrbTodosItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jrbTodosItemStateChanged
        
    }//GEN-LAST:event_jrbTodosItemStateChanged

    private void botaoGeraRelatorio1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoGeraRelatorio1ActionPerformed
        geraRelatorio();
    }//GEN-LAST:event_botaoGeraRelatorio1ActionPerformed

    private void jrbPorAnoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jrbPorAnoItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_jrbPorAnoItemStateChanged

    private void jrbPorMesItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jrbPorMesItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_jrbPorMesItemStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botaoGeraRelatorio1;
    private javax.swing.ButtonGroup grupoCliente;
    private javax.swing.ButtonGroup grupoEmissor;
    private javax.swing.ButtonGroup grupoOpOrcamento;
    private javax.swing.ButtonGroup grupoOrdenar;
    private javax.swing.ButtonGroup grupoOrientacao;
    private javax.swing.ButtonGroup grupoPeriodo;
    private javax.swing.ButtonGroup grupoTipoPessoa;
    private javax.swing.ButtonGroup grupoTransporte;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JTabbedPane jTabbedPane1;
    private com.toedter.calendar.JDateChooser jdcFimPeriodo;
    private com.toedter.calendar.JDateChooser jdcInicioPeriodo;
    private com.toedter.calendar.JMonthChooser jmcMes;
    private javax.swing.JRadioButton jrbPorAno;
    private javax.swing.JRadioButton jrbPorMes;
    private javax.swing.JRadioButton jrbPorPeriodo;
    private javax.swing.JRadioButton jrbTodos;
    private com.toedter.calendar.JYearChooser jycAno;
    private com.toedter.calendar.JYearChooser jycAnoMes;
    private javax.swing.JRadioButton pessoaFisica;
    private javax.swing.JRadioButton pessoaJuridica;
    private javax.swing.JRadioButton porCodigoCrescente;
    private javax.swing.JRadioButton porCodigoDecrescente;
    private javax.swing.JRadioButton porCodigoOpCrescente;
    private javax.swing.JRadioButton porCodigoOpDecrescente;
    private javax.swing.JRadioButton porDataMaisAntiga;
    private javax.swing.JRadioButton porDataMaisAtual;
    private javax.swing.JRadioButton porEmissorOrdenar;
    private javax.swing.JRadioButton porQuantidadeCrescente;
    private javax.swing.JRadioButton porQuantidadeDecrescente;
    private javax.swing.JRadioButton porTipoPessoa;
    private javax.swing.JRadioButton porTipoPessoaOrdenar;
    private javax.swing.JRadioButton porTodosClientes;
    private javax.swing.JRadioButton porValorCrescente;
    private javax.swing.JRadioButton porValorDecrescente;
    private javax.swing.JRadioButton rBtnPaisagem;
    private javax.swing.JRadioButton rBtnRetrato;
    private javax.swing.JRadioButton semOrdenacao;
    // End of variables declaration//GEN-END:variables
    //ESTADOS DA UI-------------------------------------------------------------
    public void estadoInicial() {
        
    }

    //GERA RELATÓRIO------------------------------------------------------------
    public void geraRelatorio() {

        com.itextpdf.text.Document document = new com.itextpdf.text.Document(PageSize.A4, 30, 20, 20, 30);

        String valor = null;

        new Thread() {
            @Override
            public void run() {

                loading.setVisible(true);
                loading.setText("GERANDO RELATÓRIO...");

                String hora = Controle.horaPadraoDiretorio.format(new Date());
                String data = Controle.dataPadraoDiretorio.format(new Date());

                try {
                    if (System.getProperty("os.name").toLowerCase().contains("windows")) {
                        PdfWriter.getInstance(document, new FileOutputStream(Controle.urlTempWindows + data + hora + ".pdf"));
                    } else {
                        PdfWriter.getInstance(document, new FileOutputStream(Controle.urlTempUnix + data + hora + ".pdf"));
                    }

                    document.setMargins(20, 20, 20, 20);

                    if (rBtnPaisagem.isSelected()) {
                        document.setPageSize(PageSize.A4.rotate());
                    } else {
                        document.setPageSize(PageSize.A4);
                    }

                    document.open();

                    document.addAuthor(TelaAutenticacao.nomeAtendente);
                    document.addCreator(TelaAutenticacao.nomeAtendente);

                    document.add(new Paragraph(new Phrase("RELATÓRIO DE ORDEM DE PRODUÇÃO - "
                            + "DATA E HORA DE EMISSÃO: "
                            + data
                            + " "
                            + hora
                            + " - SISTEMA BALA DE PRATA\n\n", FontFactory.getFont("arial.ttf", 9))));
                    

                    document.close();
                } catch (FileNotFoundException ex) {
                    EnvioExcecao envioExcecao = new EnvioExcecao(Controle.getDefaultGj(), ex);
                    EnvioExcecao.envio();
                } catch (DocumentException | IOException ex) {
                    EnvioExcecao envioExcecao = new EnvioExcecao(Controle.getDefaultGj(), ex);
                    EnvioExcecao.envio();
                }

                try {
                    if (System.getProperty("os.name").toLowerCase().contains("windows")) {
                        java.awt.Desktop.getDesktop().open(new File(Controle.urlTempWindows + data + hora + ".pdf"));
                    } else {
                        java.awt.Desktop.getDesktop().open(new File(Controle.urlTempUnix + data + hora + ".pdf"));
                    }
                } catch (IOException ex) {
                    EnvioExcecao envioExcecao = new EnvioExcecao(Controle.getDefaultGj(), ex);
                    EnvioExcecao.envio();
                }

                loading.setVisible(false);
            }
        }.start();
    }
}
