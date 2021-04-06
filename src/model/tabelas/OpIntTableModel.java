/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.tabelas;

import entidades.OrdemProducao;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author 1113778771
 */
public class OpIntTableModel extends AbstractTableModel{
    private List<OrdemProducao> dados = new ArrayList<>();
    private String[] colunas = {"OP", "ORÇAMENTO", "CLIENTE", "TIPO DE CLIENTE","DATA DE EMISSÃO", "DATA DE VALIDADE", "STATUS"};
    
    @Override
    public String getColumnName(int col){
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
        switch(coluna){
            case 0:
                return dados.get(linha).getCodigo();
            case 1:
                return dados.get(linha).getOrcBase();
            case 2: 
                return dados.get(linha).getDataEmissao();
            case 3:
                return dados.get(linha).getDataEntrega();
            case 4:
                return dados.get(linha).getStatus();
        }
        return null;
    }
    
    public OrdemProducao getValueAt(int linha){
        return dados.get(linha);
    }
    
    public void addRow(OrdemProducao op){
        this.dados.add(op);
        this.fireTableDataChanged();
    }
    
    public void setNumRows(int nRows){
        this.dados.clear();
        this.fireTableDataChanged();
    }
}
