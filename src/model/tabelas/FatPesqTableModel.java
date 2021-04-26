/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.tabelas;

import entities.sisgrafex.Faturamento;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author claud
 */
public class FatPesqTableModel extends AbstractTableModel{

    private List<Faturamento> dados = new ArrayList<>();
    private String[] colunas = {"FATURAMENTO", "ORÇAMENTO", "OP", "DATA DE LANÇAMENTO", "EMISSOR", "VALOR"};
    
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
                return dados.get(linha).getCod();
            case 1:
                return dados.get(linha).getCodOrc();
            case 2: 
                return dados.get(linha).getCodOp();
            case 3:
                return dados.get(linha).getDtFat();
            case 4:
                return dados.get(linha).getEmissor();
            case 5:
                return dados.get(linha).getVlrFat();
        }
        return null;
    }
    
    public Faturamento getValueAt(int linha){
        return dados.get(linha);
    }
    
    public void addRow(Faturamento fat){
        this.dados.add(fat);
        this.fireTableDataChanged();
    }
    
    public void setNumRows(int nRows){
        this.dados.clear();
        this.fireTableDataChanged();
    }
    
}
