/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.consulta.simatex;

import java.util.Date;

/**
 *
 * @author claud
 */
public class ConsultaSimatexBEAN {

    private String codigoMaterial;
    private String nomeMaterial;
    private String descricao;
    private int quantidade;
    private double valorUnitario;
    private Date dataMovimentacao;

    public String getCodigoMaterial() {
        return codigoMaterial;
    }

    public void setCodigoMaterial(String codigoMaterial) {
        this.codigoMaterial = codigoMaterial;
    }

    public Date getDataMovimentacao() {
        return dataMovimentacao;
    }

    public void setDataMovimentacao(Date dataMovimentacao) {
        this.dataMovimentacao = dataMovimentacao;
    }

    public String getNomeMaterial() {
        return nomeMaterial;
    }

    public void setNomeMaterial(String nomeMaterial) {
        this.nomeMaterial = nomeMaterial;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(double valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public ConsultaSimatexBEAN(String codigoMaterial, String nomeMaterial, String descricao, int quantidade, double valorUnitario, Date dataInclusao) {
        this.codigoMaterial = codigoMaterial;
        this.nomeMaterial = nomeMaterial;
        this.descricao = descricao;
        this.quantidade = quantidade;
        this.valorUnitario = valorUnitario;
        this.dataMovimentacao = dataInclusao;
    }

    public ConsultaSimatexBEAN() {
    }
    
    

}
