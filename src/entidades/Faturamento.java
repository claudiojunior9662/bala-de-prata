/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.util.Date;

/**
 *
 * @author claud
 */
public class Faturamento {

    private int cod;
    private int codOrc;
    private int codOp;
    private String emissor;
    private int qtdEntregue;
    private double vlrFat;
    private Date dtFat;
    private byte freteFat;
    private byte servicosFat;
    private String observacoes;

    public Faturamento() {
    }

    public Faturamento(int cod,
            int codOrc,
            int codOp,
            String emissor,
            int qtdEntregue,
            double vlrFat,
            Date dtFat,
            byte freteFat,
            byte servicosFat,
            String observacoes) {
        this.cod = cod;
        this.codOrc = codOrc;
        this.codOp = codOp;
        this.emissor = emissor;
        this.qtdEntregue = qtdEntregue;
        this.vlrFat = vlrFat;
        this.dtFat = dtFat;
        this.freteFat = freteFat;
        this.servicosFat = servicosFat;
        this.observacoes = observacoes;
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public int getCodOrc() {
        return codOrc;
    }

    public void setCodOrc(int codOrc) {
        this.codOrc = codOrc;
    }

    public int getCodOp() {
        return codOp;
    }

    public void setCodOp(int codOp) {
        this.codOp = codOp;
    }

    public String getEmissor() {
        return emissor;
    }

    public void setEmissor(String emissor) {
        this.emissor = emissor;
    }

    public int getQtdEntregue() {
        return qtdEntregue;
    }

    public void setQtdEntregue(int qtdEntregue) {
        this.qtdEntregue = qtdEntregue;
    }

    public double getVlrFat() {
        return vlrFat;
    }

    public void setVlrFat(double vlrFat) {
        this.vlrFat = vlrFat;
    }

    public Date getDtFat() {
        return dtFat;
    }

    public void setDtFat(Date dtFat) {
        this.dtFat = dtFat;
    }

    public byte getFreteFat() {
        return freteFat;
    }

    public void setFreteFat(byte freteFat) {
        this.freteFat = freteFat;
    }

    public byte getServicosFat() {
        return servicosFat;
    }

    public void setServicosFat(byte servicosFat) {
        this.servicosFat = servicosFat;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

}
