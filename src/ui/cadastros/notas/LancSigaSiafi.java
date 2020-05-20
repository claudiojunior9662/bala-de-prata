/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.cadastros.notas;

import java.sql.Timestamp;

/**
 *
 * @author claud
 */
public class LancSigaSiafi {
    private int codNota;
    private String cpfUsr;
    private String nomeUsr;
    private int ug;
    private Timestamp dataHora;

    public LancSigaSiafi(int codNota, 
            String cpfUsr, 
            String nomeUsr, 
            int ug, 
            Timestamp dataHora) {
        this.codNota = codNota;
        this.cpfUsr = cpfUsr;
        this.nomeUsr = nomeUsr;
        this.ug = ug;
        this.dataHora = dataHora;
    }

    public int getCodNota() {
        return codNota;
    }

    public void setCodNota(int codNota) {
        this.codNota = codNota;
    }

    public String getCpfUsr() {
        return cpfUsr;
    }

    public void setCpfUsr(String cpfUsr) {
        this.cpfUsr = cpfUsr;
    }

    public String getNomeUsr() {
        return nomeUsr;
    }

    public void setNomeUsr(String nomeUsr) {
        this.nomeUsr = nomeUsr;
    }

    public int getUg() {
        return ug;
    }

    public void setUg(int ug) {
        this.ug = ug;
    }

    public Timestamp getDataHora() {
        return dataHora;
    }

    public void setDataHora(Timestamp dataHora) {
        this.dataHora = dataHora;
    }
    
    
}
