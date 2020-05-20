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
public class CalculoPapelBEAN {
    private  int qtd_papeis_folha_capa;
    private  int qtd_papeis_miolo;
    private  int formato_capa_folha;
    private  int montagem_capa_folha;
    private  int formato_miolo;
    private  int montagem_miolo; 
    private  int cod_papel_folha_capa;
    private  int cod_papel_miolo;

    public int getCod_papel_folha_capa() {
        return cod_papel_folha_capa;
    }

    public void setCod_papel_folha_capa(int cod_papel_folha_capa) {
        this.cod_papel_folha_capa = cod_papel_folha_capa;
    }

    public int getCod_papel_miolo() {
        return cod_papel_miolo;
    }

    public void setCod_papel_miolo(int cod_papel_miolo) {
        this.cod_papel_miolo = cod_papel_miolo;
    }

    
    public int getQtd_papeis_folha_capa() {
        return qtd_papeis_folha_capa;
    }

    public void setQtd_papeis_folha_capa(int qtd_papeis_folha_capa) {
        this.qtd_papeis_folha_capa = qtd_papeis_folha_capa;
    }

    public int getQtd_papeis_miolo() {
        return qtd_papeis_miolo;
    }

    public void setQtd_papeis_miolo(int qtd_papeis_miolo) {
        this.qtd_papeis_miolo = qtd_papeis_miolo;
    }

    public int getFormato_capa_folha() {
        return formato_capa_folha;
    }

    public void setFormato_capa_folha(int formato_capa_folha) {
        this.formato_capa_folha = formato_capa_folha;
    }

    public int getMontagem_capa_folha() {
        return montagem_capa_folha;
    }

    public void setMontagem_capa_folha(int montagem_capa_folha) {
        this.montagem_capa_folha = montagem_capa_folha;
    }

    public int getFormato_miolo() {
        return formato_miolo;
    }

    public void setFormato_miolo(int formato_miolo) {
        this.formato_miolo = formato_miolo;
    }

    public int getMontagem_miolo() {
        return montagem_miolo;
    }

    public void setMontagem_miolo(int montagem_miolo) {
        this.montagem_miolo = montagem_miolo;
    }

    
}
