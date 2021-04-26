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
import javax.swing.table.AbstractTableModel;
import ui.cadastros.clientes.ClienteDAO;
import ui.controle.Controle;

/**
 *
 * @author 1113778771
 */
public class OpIntTableModel extends AbstractTableModel {

    private List<OrdemProducao> dados = new ArrayList<>();
    private String[] colunas = {"OP", "ORÇAMENTO", "CLIENTE", "TIPO DE CLIENTE", "DATA DE EMISSÃO", "DATA DE ENTREGA", "STATUS"};
    
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
        switch (coluna) {
            case 0:
                return dados.get(linha).getCodigo();
            case 1:
                return dados.get(linha).getOrcBase();
            case 2: {
                try {
                    return ClienteDAO.retornaNomeCliente(
                            dados.get(linha).getCodCliente(),
                            dados.get(linha).getTipoPessoa()
                    );
                } catch (SQLException ex) {
                    EnvioExcecao envioExcecao = new EnvioExcecao(Controle.getDefaultGj(), ex);
                    EnvioExcecao.envio();
                }
            }
            case 3:
                return Controle.retornaTipoCliente(dados.get(linha).getTipoPessoa());
            case 4:
                return Controle.dataPadrao.format(dados.get(linha).getDataEmissao());
            case 5:
                return Controle.dataPadrao.format(dados.get(linha).getDataEntrega());
            case 6:
                return dados.get(linha).getStatus();
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
