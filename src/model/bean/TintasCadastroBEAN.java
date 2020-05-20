/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.bean;

/**
 *
 * @author spd3
 */
public class TintasCadastroBEAN {
    private int cod;
    private String descricao;
    private float fator_absorcao;
    private float valor_kg;

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

    public float getFator_absorcao() {
        return fator_absorcao;
    }

    public void setFator_absorcao(float fator_absorcao) {
        this.fator_absorcao = fator_absorcao;
    }

    public float getValor_kg() {
        return valor_kg;
    }

    public void setValor_kg(float valor_kg) {
        this.valor_kg = valor_kg;
    }
    
    
}
