/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities.sisgrafex;

/**
 *
 * @author spd3
 */
public class Acabamentos {
    private int codigo;
    private int codigoOrcamento;
    private int codigoProduto;

    public Acabamentos(Integer cod, 
            Integer codProduto, 
            Integer codOrcamento, 
            Integer codComponente){
        this.codigo = cod;
        this.codigoProduto = codProduto;
        this.codigoOrcamento = codOrcamento;
    }
    
    public int getCodProduto() {
        return codigoProduto;
    }

    public void setCodProduto(int codProduto) {
        this.codigoProduto = codProduto;
    }
    
    public int getCod_orcamento() {
        return codigoOrcamento;
    }

    public void setCod_orcamento(int cod_orcamento) {
        this.codigoOrcamento = cod_orcamento;
    }

    public int getCod() {
        return codigo;
    }

    public void setCod(int cod) {
        this.codigo = cod;
    }
    
    
}
