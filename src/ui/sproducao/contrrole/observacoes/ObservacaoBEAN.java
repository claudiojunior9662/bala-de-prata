/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.sproducao.contrrole.observacoes;

import java.util.Date;

/**
 *
 * @author claud
 */
public class ObservacaoBEAN {

    private int codigoOp;
    private Date data;
    private String observacao;

    public ObservacaoBEAN() {
    }

    public ObservacaoBEAN(int codigoOp, Date data, String observacao) {
        this.codigoOp = codigoOp;
        this.data = data;
        this.observacao = observacao;
    }

    public int getCodigoOp() {
        return codigoOp;
    }

    public void setCodigoOp(int codigoOp) {
        this.codigoOp = codigoOp;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

}
