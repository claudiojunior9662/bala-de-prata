/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.cadastros.acabamentos;

/**
 *
 * @author spd3
 */
public class AcabamentoBEAN {
    private int codigo;
    private String nomeMaquina;
    private int maquinaAtiva;
    private float custoHora;

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNomeMaquina() {
        return nomeMaquina;
    }

    public void setNomeMaquina(String nomeMaquina) {
        this.nomeMaquina = nomeMaquina;
    }

    public int getMaquinaAtiva() {
        return maquinaAtiva;
    }

    public void setMaquinaAtiva(int maquinaAtiva) {
        this.maquinaAtiva = maquinaAtiva;
    }

    public float getCustoHora() {
        return custoHora;
    }

    public void setCustoHora(float custoHora) {
        this.custoHora = custoHora;
    }
    
    
}
