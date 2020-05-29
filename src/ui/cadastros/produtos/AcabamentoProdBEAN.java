/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.cadastros.produtos;

/**
 *
 * @author claud
 */
public class AcabamentoProdBEAN {

    private byte tipoProduto;
    private int codigoProduto;
    private int codigoAcabamento;

    public byte getTipoProduto() {
        return tipoProduto;
    }

    public void setTipoProduto(byte tipoProduto) {
        this.tipoProduto = tipoProduto;
    }

    public int getCodigoProduto() {
        return codigoProduto;
    }

    public void setCodigoProduto(int codigoProduto) {
        this.codigoProduto = codigoProduto;
    }

    public int getCodigoAcabamento() {
        return codigoAcabamento;
    }

    public void setCodigoAcabamento(int codigoAcabamento) {
        this.codigoAcabamento = codigoAcabamento;
    }

}
