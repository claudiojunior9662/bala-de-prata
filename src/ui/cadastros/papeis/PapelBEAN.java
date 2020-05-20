/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.cadastros.papeis;

/**
 *
 * @author claud
 */
public class PapelBEAN {

    private String codProduto;
    private int codPapel;
    private String tipo_papel;
    private int cor_frente;
    private int cor_verso;
    private String descricaoPapel;
    private float orelha;
    private int qtdGasta;
    private double precoUnit;
    private int gramatura;

    public PapelBEAN(int codPapel) {
        this.codPapel = codPapel;
    }

    public PapelBEAN() {
    }
    
    
    
    public int getQtdGasta() {
        return qtdGasta;
    }

    public void setQtdGasta(int qtdGasta) {
        this.qtdGasta = qtdGasta;
    }

    public double getPrecoUnit() {
        return precoUnit;
    }

    public void setPrecoUnit(double precoUnit) {
        this.precoUnit = precoUnit;
    }

    public int getGramatura() {
        return gramatura;
    }

    public void setGramatura(int gramatura) {
        this.gramatura = gramatura;
    }

    public float getOrelha() {
        return orelha;
    }

    public void setOrelha(float orelha) {
        this.orelha = orelha;
    }

    public String getDescricaoPapel() {
        return descricaoPapel;
    }

    public void setDescricaoPapel(String descricaoPapel) {
        this.descricaoPapel = descricaoPapel;
    }

    public String getCod_produto() {
        return codProduto;
    }

    public void setCod_produto(String cod_produto) {
        this.codProduto = cod_produto;
    }

    public int getCodigo() {
        return codPapel;
    }

    public void setCodPapel(int codPapel) {
        this.codPapel = codPapel;
    }

    public String getTipoPapel() {
        return tipo_papel;
    }

    public void setTipo_papel(String tipo_papel) {
        this.tipo_papel = tipo_papel;
    }

    public int getCorFrente() {
        return cor_frente;
    }

    public void setCor_frente(int cor_frente) {
        this.cor_frente = cor_frente;
    }

    public int getCorVerso() {
        return cor_verso;
    }

    public void setCor_verso(int cor_verso) {
        this.cor_verso = cor_verso;
    }

}
