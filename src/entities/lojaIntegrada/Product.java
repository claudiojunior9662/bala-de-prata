/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities.lojaIntegrada;

/**
 *
 * @author claud
 */
public class Product {

    private String id;
    private String idExterno;
    private String sku;
    private String mpn;
    private String ncm;
    private String nome;
    private String descricaoCompleta;
    private boolean ativo;
    private boolean destaque;
    private float peso;
    private float altura;
    private float largura;
    private float profundidade;
    private String tipo;
    private boolean usado;
    private Grid grid;
    private Category category;
    private float valorCusto;
    private float valorPromocional;
    private int estoque;

    //Contructors---------------------------------------------------------------
    public Product(String id, String sku, String nome, String descricaoCompleta, boolean ativo, float peso, float altura, float largura, float profundidade, String tipo) {
        this.id = id;
        this.sku = sku;
        this.nome = nome;
        this.descricaoCompleta = descricaoCompleta;
        this.ativo = ativo;
        this.peso = peso;
        this.altura = altura;
        this.largura = largura;
        this.profundidade = profundidade;
        this.tipo = tipo;
    }

    public Product(String id, String sku, float valorCusto, float valorPromocional) {
        this.id = id;
        this.sku = sku;
        this.valorCusto = valorCusto;
        this.valorPromocional = valorPromocional;
    }
    
    public Product(String id, String sku, int estoque) {
        this.id = id;
        this.sku = sku;
        this.estoque = estoque;
    }

    //Getters and setters-------------------------------------------------------
    public int getEstoque() {
        return estoque;
    }

    public void setEstoque(int estoque) {
        this.estoque = estoque;
    }
    
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getIdExterno() {
        return idExterno;
    }

    public void setIdExterno(String idExterno) {
        this.idExterno = idExterno;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getMpn() {
        return mpn;
    }

    public void setMpn(String mpn) {
        this.mpn = mpn;
    }

    public String getNcm() {
        return ncm;
    }

    public void setNcm(String ncm) {
        this.ncm = ncm;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricaoCompleta() {
        return descricaoCompleta;
    }

    public void setDescricaoCompleta(String descricaoCompleta) {
        this.descricaoCompleta = descricaoCompleta;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public boolean isDestaque() {
        return destaque;
    }

    public void setDestaque(boolean destaque) {
        this.destaque = destaque;
    }

    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    public float getAltura() {
        return altura;
    }

    public void setAltura(float altura) {
        this.altura = altura;
    }

    public float getProfundidade() {
        return profundidade;
    }

    public void setProfundidade(float profundidade) {
        this.profundidade = profundidade;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public boolean isUsado() {
        return usado;
    }

    public void setUsado(boolean usado) {
        this.usado = usado;
    }

    public Grid getGrid() {
        return grid;
    }

    public void setGrid(Grid grid) {
        this.grid = grid;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public float getLargura() {
        return largura;
    }

    public void setLargura(float largura) {
        this.largura = largura;
    }

}
