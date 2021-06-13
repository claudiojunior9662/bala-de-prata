/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities.sisgrafex;

/**
 *
 * @author claud
 */
public class Cliente {

    //IDENTIFICADORES
    private int codigo;
    private int codigoLI;

    private String nome;
    private String nomeFantasia;
    private String cpf;
    private String cnpj;
    private byte tipoPessoa;
    private String atividade;
    private String filialColigada;
    private String observacoes;
    private double credito;
    private String codigoAtendente;
    private String nomeAtendente;

    @Override
    public String toString() {
        return "Cliente{" + "codigo=" + codigo + ", codigoLI=" + codigoLI + ", nome=" + nome + ", nomeFantasia=" + nomeFantasia + ", cpf=" + cpf + ", cnpj=" + cnpj + ", tipoPessoa=" + tipoPessoa + ", atividade=" + atividade + ", filialColigada=" + filialColigada + ", observacoes=" + observacoes + ", credito=" + credito + ", codigoAtendente=" + codigoAtendente + ", nomeAtendente=" + nomeAtendente + '}';
    }

    public Cliente(int codigo, String nome, String nomeFantasia, String cpf, String cnpj, byte tipoPessoa, String atividade, String filialColigada, double credito, String codigoAtendente, String nomeAtendente) {
        this.codigo = codigo;
        this.nome = nome;
        this.nomeFantasia = nomeFantasia;
        this.cpf = cpf;
        this.cnpj = cnpj;
        this.tipoPessoa = tipoPessoa;
        this.atividade = atividade;
        this.filialColigada = filialColigada;
        this.credito = credito;
        this.codigoAtendente = codigoAtendente;
        this.nomeAtendente = nomeAtendente;
    }

    public Cliente(String nome,
            String cpf,
            String cnpj,
            String atividade,
            String nomeFantasia,
            String filialColigada,
            String codigoAtendente,
            String nomeAtendente,
            String observacoes,
            float credito,
            byte tipoCliente) {
        switch (tipoCliente) {
            case 1:
                this.nome = nome;
                this.cpf = cpf;
                this.codigoAtendente = codigoAtendente;
                this.nomeAtendente = nomeAtendente;
                this.observacoes = observacoes;
                this.credito = credito;
                break;
            case 2:
                this.nome = nome;
                this.cnpj = cnpj;
                this.atividade = atividade;
                this.nomeFantasia = nomeFantasia;
                this.filialColigada = filialColigada;
                this.codigoAtendente = codigoAtendente;
                this.nomeAtendente = nomeAtendente;
                this.observacoes = observacoes;
                this.credito = credito;
                break;
        }

    }

    public Cliente(int codigo,
            int codigoLI,
            String nome,
            String nomeFantasia,
            String cpf,
            String cnpj,
            byte tipoPessoa) {
        this.codigo = codigo;
        this.codigoLI = codigoLI;
        this.nome = nome;
        this.nomeFantasia = nomeFantasia;
        this.cpf = cpf;
        this.cnpj = cnpj;
        this.tipoPessoa = tipoPessoa;
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

    public Cliente(int codigo, byte tipoPessoa, String nome) {
        this.codigo = codigo;
        this.tipoPessoa = tipoPessoa;
        this.nome = nome;
    }

    public Cliente(int codigo, byte tipoPessoa) {
        this.codigo = codigo;
        this.tipoPessoa = tipoPessoa;
    }

    public Cliente(int codigo, String nome, String doc, byte tipoPessoa) {
        this.codigo = codigo;
        this.nome = nome;
        switch (tipoPessoa) {
            case 1:
                this.cpf = doc;
                break;
            case 2:
                this.cnpj = doc;
                break;
        }

    }

    public Cliente(int codigo, String nome) {
        this.codigo = codigo;
        this.nome = nome;
    }

    public Cliente() {

    }

    public String getCodigoAtendente() {
        return codigoAtendente;
    }

    public void setCodigoAtendente(String codigoAtendente) {
        this.codigoAtendente = codigoAtendente;
    }

    public String getNomeAtendente() {
        return nomeAtendente;
    }

    public void setNomeAtendente(String nomeAtendente) {
        this.nomeAtendente = nomeAtendente;
    }

    public int getCodigoLI() {
        return codigoLI;
    }

    public void setCodigoLI(int codigoLI) {
        this.codigoLI = codigoLI;
    }

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

}
