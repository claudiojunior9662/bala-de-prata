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
public class ProdOrcamento {

    private int codOrcamento;
    private int codProduto;
    private byte tipoProduto;
    private String descricaoProduto;
    private int quantidade;
    private String observacaoProduto;
    private float precoUnitario;
    private String tipoTrabalho;
    private int maquina;
    private Double valorDigital;

    @Override
    public String toString() {
        return "ProdOrcamento{" + "codOrcamento=" + codOrcamento + ", codProduto=" + codProduto + ", tipoProduto=" + tipoProduto + ", descricaoProduto=" + descricaoProduto + ", quantidade=" + quantidade + ", observacaoProduto=" + observacaoProduto + ", precoUnitario=" + precoUnitario + ", tipoTrabalho=" + tipoTrabalho + ", maquina=" + maquina + ", valorDigital=" + valorDigital + '}';
    }

    public ProdOrcamento(String descricaoProduto,
            int quantidade,
            float precoUnitario,
            int codProduto,
            byte tipoProduto,
            String observacaoProduto) {
        this.descricaoProduto = descricaoProduto;
        this.quantidade = quantidade;
        this.codProduto = codProduto;
        this.tipoProduto = tipoProduto;
        this.precoUnitario = precoUnitario;
    }

    public ProdOrcamento() {
    }

    public ProdOrcamento(int codProduto,
            byte tipoProduto,
            String descricaoProduto,
            int quantidade,
            float precoUnitario) {
        this.codProduto = codProduto;
        this.tipoProduto = tipoProduto;
        this.descricaoProduto = descricaoProduto;
        this.quantidade = quantidade;
        this.precoUnitario = precoUnitario;
    }

    public ProdOrcamento(int codProduto, String descricaoProduto, int quantidade, float precoUnitario) {
        this.codProduto = codProduto;
        this.descricaoProduto = descricaoProduto;
        this.quantidade = quantidade;
        this.precoUnitario = precoUnitario;
    }

    public Double getValorDigital() {
        return valorDigital;
    }

    public void setValorDigital(Double valorDigital) {
        this.valorDigital = valorDigital;
    }

    public int getMaquina() {
        return maquina;
    }

    public void setMaquina(int maquina) {
        this.maquina = maquina;
    }

    public String getTipo_trabalho() {
        return tipoTrabalho;
    }

    public void setTipo_trabalho(String tipo_trabalho) {
        this.tipoTrabalho = tipo_trabalho;
    }

    public float getPrecoUnitario() {
        return precoUnitario;
    }

    public void setPrecoUnitario(float precoUnitario) {
        this.precoUnitario = precoUnitario;
    }

    public String getObservacaoProduto() {
        return observacaoProduto;
    }

    public void setObservacaoProduto(String observacaoProduto) {
        this.observacaoProduto = observacaoProduto;
    }

    public int getCodOrcamento() {
        return codOrcamento;
    }

    public void setCodOrcamento(int cod_orcamento) {
        this.codOrcamento = cod_orcamento;
    }

    public String getDescricaoProduto() {
        return descricaoProduto;
    }

    public void setDescricaoProduto(String descricao_produto) {
        this.descricaoProduto = descricao_produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public int getCodProduto() {
        return codProduto;
    }

    public void setCodProduto(int codProduto) {
        this.codProduto = codProduto;
    }

    public byte getTipoProduto() {
        return tipoProduto;
    }

    public void setTipoProduto(byte tipoProduto) {
        this.tipoProduto = tipoProduto;
    }

}
