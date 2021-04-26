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
public class Papel {

    private int cod;
    private int codOrcamento;
    private String descricao;
    private String medida;
    private int gramatura;
    private String formato;
    private int umaFace;
    private double unitario;
    private String tipo;
    private int CoresFrente;
    private int CoresVerso;
    private int codProduto;
    private int qtdGasta;

    public Papel() {

    }

    public Papel(Integer codPapel,
            String tipo,
            String descricao) {
        this.cod = codPapel;
        this.tipo = tipo;
        this.descricao = descricao;
    }

    public Papel(int cod, String descricao) {
        this.cod = cod;
        this.descricao = descricao;
    }

    public Papel(int cod) {
        this.cod = cod;
    }

    public int getQtdGasta() {
        return qtdGasta;
    }

    public void setQtdGasta(int qtdGasta) {
        this.qtdGasta = qtdGasta;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getGramatura() {
        return gramatura;
    }

    public void setGramatura(int gramatura) {
        this.gramatura = gramatura;
    }

    public double getUnitario() {
        return unitario;
    }

    public void setUnitario(double unitario) {
        this.unitario = unitario;
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getMedida() {
        return medida;
    }

    public void setMedida(String medida) {
        this.medida = medida;
    }

    public String getFormato() {
        return formato;
    }

    public void setFormato(String formato) {
        this.formato = formato;
    }

    public int getUma_face() {
        return umaFace;
    }

    public void setUma_face(int uma_face) {
        this.umaFace = uma_face;
    }

}
