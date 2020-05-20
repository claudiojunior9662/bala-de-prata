/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.cadastros.notas;

/**
 *
 * @author claudio
 */
public class TransporteBEAN {

    private int cod;
    private int codNota;
    private String modalidadeFrete;
    private String nomeTransportador;
    private float espessuraProduto;
    private float pesoProduto;

    public TransporteBEAN(int cod, int codNota, String modalidadeFrete, String nomeTransportador, float espessuraProduto, float pesoProduto) {
        this.cod = cod;
        this.codNota = codNota;
        this.modalidadeFrete = modalidadeFrete;
        this.nomeTransportador = nomeTransportador;
        this.espessuraProduto = espessuraProduto;
        this.pesoProduto = pesoProduto;
    }

    public TransporteBEAN() {
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public int getCodNota() {
        return codNota;
    }

    public void setCodNota(int codNota) {
        this.codNota = codNota;
    }

    public String getModalidadeFrete() {
        return modalidadeFrete;
    }

    public void setModalidadeFrete(String modalidadeFrete) {
        this.modalidadeFrete = modalidadeFrete;
    }

    public String getNomeTransportador() {
        return nomeTransportador;
    }

    public void setNomeTransportador(String nomeTransportador) {
        this.nomeTransportador = nomeTransportador;
    }

    public float getEspessuraProduto() {
        return espessuraProduto;
    }

    public void setEspessuraProduto(float espessuraProduto) {
        this.espessuraProduto = espessuraProduto;
    }

    public float getPesoProduto() {
        return pesoProduto;
    }

    public void setPesoProduto(float pesoProduto) {
        this.pesoProduto = pesoProduto;
    }

}
