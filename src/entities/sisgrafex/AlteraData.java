/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities.sisgrafex;

import java.sql.Timestamp;
import java.util.Date;

/**
 *
 * @author 1113778771
 */
public class AlteraData {
    private int codigo;
    private int codigoOp;
    private Timestamp alteracao;
    private Date dataAnterior;
    private String usuario;
    private String motivo;

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getCodigoOp() {
        return codigoOp;
    }

    public void setCodigoOp(int codigoOp) {
        this.codigoOp = codigoOp;
    }

    public Timestamp getAlteracao() {
        return alteracao;
    }

    public void setAlteracao(Timestamp alteracao) {
        this.alteracao = alteracao;
    }

    public Date getDataAnterior() {
        return dataAnterior;
    }

    public void setDataAnterior(Date dataAnterior) {
        this.dataAnterior = dataAnterior;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }
    
    

    public AlteraData(int codigoOp, Timestamp alteracao, Date dataAnterior, String usuario, String motivo) {
        this.codigoOp = codigoOp;
        this.alteracao = alteracao;
        this.dataAnterior = dataAnterior;
        this.usuario = usuario;
        this.motivo = motivo;
    }
    
    
}
