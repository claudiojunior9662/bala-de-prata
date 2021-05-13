/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.tabelas;

import entities.sisgrafex.OrdemProducao;
import exception.EnvioExcecao;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;
import ui.cadastros.clientes.ClienteDAO;
import ui.cadastros.produtos.ProdutoDAO;
import ui.controle.Controle;

/**
 *
 * @author 1113778771
 */
public class OpIntTableModel extends AbstractTableModel {

    private List<OrdemProducao> dados = new ArrayList<>();
    private String[] colunas = {"OP", "ORÇAMENTO", "PRODUTO", "CLIENTE", "TIPO DE CLIENTE", "DATA DE EMISSÃO", "DATA DE ENTREGA", "STATUS"};
    
    @Override
    public String getColumnName(int col) {
        return colunas[col];
    }

    @Override
    public int getRowCount() {
        return dados.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public Object getValueAt(int linha, int coluna) {
        try {
            switch (coluna) {
                case 0:
                    return dados.get(linha).getCodigo();
                case 1:
                    return dados.get(linha).getOrcBase();
                case 2:
                    return ProdutoDAO.retornaDescricaoProduto(dados.get(linha).getCodProduto(), dados.get(linha).getTipoProduto());
                case 3:
                    try {
                        return ClienteDAO.retornaNomeCliente(
                                dados.get(linha).getCodCliente(),
                                dados.get(linha).getTipoPessoa()
                        );
                    } catch (SQLException ex) {
                        EnvioExcecao envioExcecao = new EnvioExcecao(Controle.getDefaultGj(), ex);
                        EnvioExcecao.envio();
                    }
                case 4:
                    return Controle.retornaTipoCliente(dados.get(linha).getTipoPessoa());
                case 5:
                    return Controle.dataPadrao.format(dados.get(linha).getDataEmissao());
                case 6:
                    return Controle.dataPadrao.format(dados.get(linha).getDataEntrega());
                case 7:
                    return dados.get(linha).getStatus();
            }
        } catch (SQLException ex) {
            Logger.getLogger(OpIntTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public OrdemProducao getValueAt(int linha) {
        return dados.get(linha);
    }

    public void addRow(OrdemProducao op) {
        this.dados.add(op);
        this.fireTableDataChanged();
    }

    public void setNumRows(int nRows) {
        this.dados.clear();
        this.fireTableDataChanged();
    }
}
