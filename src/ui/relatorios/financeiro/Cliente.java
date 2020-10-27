/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.relatorios.financeiro;

/**
 *
 * @author 1113778771
 */
public class Cliente {

    private int codigo;
    private String nome;
    private Double credito;
    private Double debito;
    private Double emAberto;
    private Double saldoAcumuladoAtual;
    private Double saldoAcumuladoAnterior;

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getCredito() {
        return credito;
    }

    public void setCredito(Double credito) {
        this.credito = credito;
    }

    public Double getDebito() {
        return debito;
    }

    public void setDebito(Double debito) {
        this.debito = debito;
    }

    public Double getSaldoAcumuladoAtual() {
        return saldoAcumuladoAtual;
    }

    public void setSaldoAcumuladoAtual(Double saldoAcumuladoAtual) {
        this.saldoAcumuladoAtual = saldoAcumuladoAtual;
    }

    public Double getEmAberto() {
        return emAberto;
    }

    public void setEmAberto(Double emAberto) {
        this.emAberto = emAberto;
    }

    public Double getSaldoAcumuladoAnterior() {
        return saldoAcumuladoAnterior;
    }

    public void setSaldoAcumuladoAnterior(Double saldoAcumuladoAnterior) {
        this.saldoAcumuladoAnterior = saldoAcumuladoAnterior;
    }

    public Cliente(int codigo, String nome, Double credito, Double debito, Double emAberto, Double saldoAcumuladoAtual, Double saldoAcumuladoAnterior) {
        this.codigo = codigo;
        this.nome = nome;
        this.credito = credito;
        this.debito = debito;
        this.emAberto = 0d;
        this.saldoAcumuladoAtual = 0d;
        this.saldoAcumuladoAnterior = 0d;
    }

    public Cliente(int codigo, String nome, Double credito) {
        this.codigo = codigo;
        this.nome = nome;
        this.credito = credito;
    }

}
