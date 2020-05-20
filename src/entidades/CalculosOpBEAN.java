/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

/**
 *
 * @author claud
 */
public class CalculosOpBEAN {

    private int codOp;
    private String codProduto;
    private String tipoPapel;
    private int qtdFolhas;
    private int qtdFolhasTotal;
    private int montagem;
    private int formato;
    private int qtdChapas;
    private float perca;
    private int codigoProposta;
    private int codigoPapel;

    public int getCodigoPapel() {
        return codigoPapel;
    }

    public void setCodigoPapel(int codigoPapel) {
        this.codigoPapel = codigoPapel;
    }

    public int getCodigoProposta() {
        return codigoProposta;
    }

    public void setCodigoProposta(int codigoProposta) {
        this.codigoProposta = codigoProposta;
    }

    public float getPerca() {
        return perca;
    }

    public void setPerca(float perca) {
        this.perca = perca;
    }

    public int getQtdChapas() {
        return qtdChapas;
    }

    public void setQtdChapas(int qtdChapas) {
        this.qtdChapas = qtdChapas;
    }

    public String getTipoPapel() {
        return tipoPapel;
    }

    public void setTipoPapel(String tipoPapel) {
        this.tipoPapel = tipoPapel;
    }

    public int getCodOp() {
        return codOp;
    }

    public void setCodOp(int codOp) {
        this.codOp = codOp;
    }

    public String getCodProduto() {
        return codProduto;
    }

    public void setCodProduto(String codProduto) {
        this.codProduto = codProduto;
    }

    public int getQtdFolhas() {
        return qtdFolhas;
    }

    public void setQtdFolhas(int qtdFolhas) {
        this.qtdFolhas = qtdFolhas;
    }

    public int getQtdFolhasTotal() {
        return qtdFolhasTotal;
    }

    public void setQtdFolhasTotal(int qtdFolhasTotal) {
        this.qtdFolhasTotal = qtdFolhasTotal;
    }

    public int getMontagem() {
        return montagem;
    }

    public void setMontagem(int montagem) {
        this.montagem = montagem;
    }

    public int getFormato() {
        return formato;
    }

    public void setFormato(int formato) {
        this.formato = formato;
    }

}
