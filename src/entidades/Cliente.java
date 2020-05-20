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
public class Cliente {

    private int codigo;
    private String nome;
    private String nomeFantasia;
    private String cpf;
    private String cnpj;
    private byte tipoPessoa;
    private String atividade;
    private String filialColigada;
    private String observacoes;
    private double credito;

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

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public byte getTipoPessoa() {
        return tipoPessoa;
    }

    public void setTipoPessoa(byte tipoPessoa) {
        this.tipoPessoa = tipoPessoa;
    }

    public String getAtividade() {
        return atividade;
    }

    public void setAtividade(String atividade) {
        this.atividade = atividade;
    }

    public String getFilialColigada() {
        return filialColigada;
    }

    public void setFilialColigada(String filialColigada) {
        this.filialColigada = filialColigada;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public double getCredito() {
        return credito;
    }

    public void setCredito(double credito) {
        this.credito = credito;
    }

    public Cliente(int codigo, String nome, String cpf, String atividade, String observacoes, double credito) {
        this.codigo = codigo;
        this.nome = nome;
        this.cpf = cpf;
        this.atividade = atividade;
        this.observacoes = observacoes;
        this.credito = credito;
        this.tipoPessoa = 1;
    }

    public Cliente(int codigo, String nome, String nomeFantasia, String cnpj, String atividade, String filialColigada, String observacoes, double credito) {
        this.codigo = codigo;
        this.nome = nome;
        this.nomeFantasia = nomeFantasia;
        this.cnpj = cnpj;
        this.atividade = atividade;
        this.filialColigada = filialColigada;
        this.observacoes = observacoes;
        this.credito = credito;
        this.tipoPessoa = 2;
    }

    public Cliente(int codigo, String nome, byte tipoPessoa) {
        this.codigo = codigo;
        this.nome = nome;
        this.tipoPessoa = tipoPessoa;
    }

    public Cliente(int codigo, byte tipoPessoa) {
        this.codigo = codigo;
        this.tipoPessoa = tipoPessoa;
    }

    public Cliente(int codigo, String nome, String doc, byte tipoPessoa) {
        this.codigo = codigo;
        this.nome = nome;
        switch(tipoPessoa){
            case 1:
                this.cpf = doc;
                break;
            case 2:
                this.cnpj = doc;
                break;
        }
        
    }
    
    

    public Cliente() {

    }

    @Override
    public String toString() {
        return "Cliente{" + "codigo=" + codigo + ", nome=" + nome + ", nomeFantasia=" + nomeFantasia + ", cpf=" + cpf + ", cnpj=" + cnpj + ", tipoPessoa=" + tipoPessoa + ", atividade=" + atividade + ", filialColigada=" + filialColigada + ", observacoes=" + observacoes + ", credito=" + credito + '}';
    }
    
    

}
