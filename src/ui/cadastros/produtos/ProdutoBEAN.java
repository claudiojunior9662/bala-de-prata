/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.cadastros.produtos;

/**
 *
 * @author spd3
 */
public class ProdutoBEAN {

    //IDENTIFICADORES
    private String codigo;
    private int codigoOrcamento;
    private int codigoProduto;
    private String descricao;
    private int disponivelVendas;
    //DIMENSÕES
    private float largura;
    private float altura;
    //CARACTERÍSTICAS
    private int qtdPaginas;
    private int quantidadeProduto;
    private String tipoProduto;
    private String descricaoProduto;
    private int quantidadeProduzida;
    
    
    public ProdutoBEAN() {
    }

    public ProdutoBEAN(float largura, float altura) {
        this.largura = largura;
        this.altura = altura;
    }

    public ProdutoBEAN(String codigo, 
            String descricao, 
            float largura, 
            float altura, 
            int qtdPaginas, 
            String tipoProduto, 
            int disponivelVendas) {
        this.codigo = codigo;
        this.descricao = descricao;
        this.largura = largura;
        this.altura = altura;
        this.qtdPaginas = qtdPaginas;
        this.tipoProduto = tipoProduto;
        this.disponivelVendas = disponivelVendas;
    }

    public int getDisponivelVendas() {
        return disponivelVendas;
    }

    public void setDisponivelVendas(int disponivelVendas) {
        this.disponivelVendas = disponivelVendas;
    }

    public int getQuantidade() {
        return quantidadeProduzida;
    }

    public void setQuantidade(int quantidade) {
        this.quantidadeProduzida = quantidade;
    }

    public int getCodigoOrcamento() {
        return codigoOrcamento;
    }

    public void setCodigoOrcamento(int codigoOrcamento) {
        this.codigoOrcamento = codigoOrcamento;
    }

    public int getCodigoProduto() {
        return codigoProduto;
    }

    public void setCodigoProduto(int codigoProduto) {
        this.codigoProduto = codigoProduto;
    }

    public String getDescricaoProduto() {
        return descricaoProduto;
    }

    public void setDescricaoProduto(String descricaoProduto) {
        this.descricaoProduto = descricaoProduto;
    }

    public String getTipoProduto() {
        return tipoProduto;
    }

    public void setTipoProduto(String tipoProduto) {
        this.tipoProduto = tipoProduto;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public float getLargura() {
        return largura;
    }

    public void setLargura(float largura) {
        this.largura = largura;
    }

    public float getAltura() {
        return altura;
    }

    public void setAltura(float altura) {
        this.altura = altura;
    }

    public int getQuantidadeFolhas() {
        return qtdPaginas;
    }

    public void setQuantidadePaginas(int quantidadePaginas) {
        this.qtdPaginas = quantidadePaginas;
    }

    public int getQuantidadeProduto() {
        return quantidadeProduto;
    }

    public void setQuantidadeProduto(int quantidadeProduto) {
        this.quantidadeProduto = quantidadeProduto;
    }

}
