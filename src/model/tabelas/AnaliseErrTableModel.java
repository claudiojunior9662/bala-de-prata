/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.tabelas;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import ui.erros.ErroBEAN;

/**
 *
 * @author claud
 */
public class AnaliseErrTableModel extends AbstractTableModel{

    private List<ErroBEAN> dados = new ArrayList<>();
    private String[] colunas = {"ID", "USR", "DESC", "STS"};
    
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
                return dados.get(linha).getId();
            case 1:
                return dados.get(linha).getUsr();
            case 2: 
                return dados.get(linha).getDescricao();
            case 3:
                return dados.get(linha).getSts();
        }
        return null;
    }
    
    public ErroBEAN getValueAt(int linha){
        return dados.get(linha);
    }
    
    public void addRow(ErroBEAN err){
        this.dados.add(err);
        this.fireTableDataChanged();
    }
    
    public void setNumRows(int nRows){
        this.dados.clear();
        this.fireTableDataChanged();
    }
    
}
