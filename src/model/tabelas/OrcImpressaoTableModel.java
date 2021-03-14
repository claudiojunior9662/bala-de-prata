/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.tabelas;

import javax.swing.JComboBox;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author claud
 */
public class OrcImpressaoTableModel extends AbstractTableModel{

    @Override
    public int getRowCount() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getColumnCount() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private class ModoImpressao extends JComboBox{
        ModoImpressao(){
            addItem("SELECIONE..");
            addItem("OFFSET");
            addItem("DIGITAL");
        }
    }
    
    private class TipoImpressao extends JComboBox{
        TipoImpressao(){
            addItem("SELECIONE..");
            addItem("IMPRESSÃO CHAPADA");
            addItem("RETÍCULA 70%");
            addItem("RETÍCULA 40%");
            addItem("RETÍCULA 30%");
            addItem("TEXTO");
        }
    }
    
    private class PesoEspTinta extends JComboBox{
        PesoEspTinta(){
            addItem("SELECIONE..");
            addItem("CORES DE ESCALA");
            addItem("CORES TRANSPARENTES");
            addItem("CORES OPACAS");
            addItem("BANCO");
        }
    }
    
    private String[] colunas = {"PRODUTO", "PAPEL", "PROC. DE IMPRESSÃO", "TIPO DE IMPRESSÃO", "PESO ESPECÍFICO", "VLR IMP. DIGITAL", "FORMATO IMP.", "% PERCA"};
    
    @Override
    public String getColumnName(int col){
        return colunas[col];
    }
    
    
    
}
