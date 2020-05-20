/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

/**
 *
 * @author claud
 */
public class StsOrcamento {

    private int cod;
    private String status;

    public StsOrcamento(int cod, String status) {
        this.cod = cod;
        this.status = status;
    }

    @Override
    public String toString() {
        return cod + " - " + status;
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
