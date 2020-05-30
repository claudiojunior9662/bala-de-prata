/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.tabelas;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import ui.administrador.UsuarioBEAN;

/**
 *
 * @author claud
 */
public class UsuarioTableModel extends AbstractTableModel{

    private List<UsuarioBEAN> dados = new ArrayList<>();
    private String[] colunas = {"NOME", "COD", "LOGIN", "TIPO", "ORC", "PROD", "EXP", "FIN", "EST", "ATIVO"};
    
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
    
    public UsuarioBEAN getValueAt(int linha){
        return dados.get(linha);
    }
    
    public void addRow(UsuarioBEAN usr){
        this.dados.add(usr);
        this.fireTableDataChanged();
    }
    
    public void setNumRows(int nRows){
        this.dados.clear();
        this.fireTableDataChanged();
    }

    @Override
    public Object getValueAt(int linha, int coluna) {
        switch(coluna){
            case 0:
                return dados.get(linha).getNome();
            case 1:
                return dados.get(linha).getCodigo();
            case 2: 
                return dados.get(linha).getLogin();
            case 3:
                return dados.get(linha).getTipo();
            case 4:
                return dados.get(linha).getAcessoOrc();
            case 5:
                return dados.get(linha).getAcessoProd();
            case 6:
                return dados.get(linha).getAcessoExp();
            case 7:
                return dados.get(linha).getAcessoFin();
            case 8:
                return dados.get(linha).getAcessoEst();
            case 9:
                return dados.get(linha).getAtivo();
        }
        return null;
    }
    
}
