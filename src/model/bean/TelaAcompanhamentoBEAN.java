/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.bean;

import java.util.Date;

/**
 *
 * @author spd3
 */
public class TelaAcompanhamentoBEAN {

    int numero;
    Date dataEmissao;
    Date dataEntrega;
    byte status;
    String descricaoProduto;

    @Override
    public String toString() {
        return "TelaAcompanhamentoBEAN{" + "numero=" + numero + ", dataEntrega=" + dataEntrega + ", status=" + status + ", descricaoProduto=" + descricaoProduto + '}';
    }

    public TelaAcompanhamentoBEAN(int numero, Date dataEmissao, Date dataEntrega, byte status, String descricaoProduto) {
        this.numero = numero;
        this.dataEmissao = dataEmissao;
        this.dataEntrega = dataEntrega;
        this.status = status;
        this.descricaoProduto = descricaoProduto;
    }

    public String getDescricaoProduto() {
        return descricaoProduto;
    }

    public void setDescricaoProduto(String descricaoProduto) {
        this.descricaoProduto = descricaoProduto;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public byte getStatus() {
        return status;
    }

    public void setStatus(byte status) {
        this.status = status;
    }

    public Date getDataEntrega() {
        return dataEntrega;
    }

    public void setDataEntrega(Date dataEntrega) {
        this.dataEntrega = dataEntrega;
    }

    public Date getDataEmissao() {
        return dataEmissao;
    }

    public void setDataEmissao(Date dataEmissao) {
        this.dataEmissao = dataEmissao;
    }

}
