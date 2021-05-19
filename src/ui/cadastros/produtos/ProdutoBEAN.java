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
    private int codigo;
    private int codigoOrcamento;
    private int codigoProduto;
    private String descricao;
    //DIMENSÕES
    private float largura;
    private float altura;
    private float espessura;
    private float peso;
    //CARACTERÍSTICAS
    private boolean usoEcommerce;
    private boolean precoPromocional;
    private int qtdPaginas;
    private int quantidadeProduto;
    private String tipoProduto;
    private String descricaoProduto;
    private int quantidadeProduzida;
    private boolean ativo;
    //PREÇOS
    private float valorCusto;
    private float valorPromocional;

    public ProdutoBEAN() {
    }

    public ProdutoBEAN(float largura, float altura) {
        this.largura = largura;
        this.altura = altura;
    }

    public ProdutoBEAN(int codigo,
            String descricao,
            float largura,
            float altura,
            float espessura,
            float peso,
            int qtdPaginas,
            String tipoProduto,
            boolean ativo,
            float valorCusto,
            float valorPromocional,
            boolean usoEcommerce,
            boolean precoPromocional) {
        this.codigo = codigo;
        this.descricao = descricao;
        this.largura = largura;
        this.altura = altura;
        this.espessura = espessura;
        this.peso = peso;
        this.qtdPaginas = qtdPaginas;
        this.tipoProduto = tipoProduto;
        this.ativo = ativo;
        this.valorCusto = valorCusto;
        this.valorPromocional = valorPromocional;
        this.usoEcommerce = usoEcommerce;
        this.precoPromocional = precoPromocional;
    }

    public boolean isUsoEcommerce() {
        return usoEcommerce;
    }

    public void setUsoEcommerce(boolean usoEcommerce) {
        this.usoEcommerce = usoEcommerce;
    }

    public boolean isPrecoPromocional() {
        return precoPromocional;
    }

    public void setPrecoPromocional(boolean precoPromocional) {
        this.precoPromocional = precoPromocional;
    }

    public float getValorCusto() {
        return valorCusto;
    }

    public void setValorCusto(float valorCusto) {
        this.valorCusto = valorCusto;
    }

    public float getValorPromocional() {
        return valorPromocional;
    }

    public void setValorPromocional(float valorPromocional) {
        this.valorPromocional = valorPromocional;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public float getEspessura() {
        return espessura;
    }

    public void setEspessura(float espessura) {
        this.espessura = espessura;
    }

    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
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

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
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
