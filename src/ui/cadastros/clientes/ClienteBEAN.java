/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.cadastros.clientes;

/**
 *
 * @author claudio
 */
public class ClienteBEAN {

    private Integer codigo;
    private String nome;
    private String cpf;
    private String cnpj;
    private String atividade;
    private String nomeFantasia;
    private String filialColigada;
    private String codigoAtendente;
    private byte tipoCliente;
    private String nomeAtendente;
    private String observacoes;
    private Float credito;

    public ClienteBEAN() {

    }

    public ClienteBEAN(String nome,
            String doc,
            byte tipo) {
        switch (tipo) {
            case 1:
                this.nome = nome;
                this.cpf = doc;
                break;
            case 2:
                this.nome = nome;
                this.cnpj = doc;
                break;
        }
    }

    public ClienteBEAN(String nome,
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

    public ClienteBEAN(Integer codigo,
            byte tipoCliente) {
        this.codigo = codigo;
        this.tipoCliente = tipoCliente;
    }
    
    public ClienteBEAN(String nome, 
            String nomeFantasia, 
            String doc, 
            String observacao, 
            byte tipoPessoa){
        switch(tipoPessoa){
            case 1:
                this.nome = nome;
                this.cpf = doc;
                this.observacoes = observacao;
                break;
            case 2:
                this.nome = nome;
                this.nomeFantasia = nomeFantasia;
                this.cnpj = doc;
                this.observacoes = observacao;
                break;
        }
    }
    
    public ClienteBEAN(int cod,
            String nome,
            String nomeFantasia,
            String doc,
            byte tipo,
            float credito){
        this.codigo = cod;
        this.nome = nome;
        this.tipoCliente = tipo;
        if(tipo == 1){
            this.cpf = doc;
        }else{
            this.cnpj = doc;
            this.nomeFantasia = nomeFantasia;
        }
        this.credito = credito;
    }
    
    public ClienteBEAN(int cod,
            String nome,
            String nomeFantasia,
            byte tipo,
            float credito){
        this.codigo = cod;
        this.nome = nome;
        this.tipoCliente = tipo;
        if(tipo == 2){
            this.nomeFantasia = nomeFantasia;
        }
        this.credito = credito;
    }

    public Float getCredito() {
        return credito;
    }

    public void setCredito(Float credito) {
        this.credito = credito;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public String getNomeAtendente() {
        return nomeAtendente;
    }

    public void setNomeAtendente(String nomeAtendente) {
        this.nomeAtendente = nomeAtendente;
    }

    public byte getTipoCliente() {
        return tipoCliente;
    }

    public void setTipoCliente(byte tipoCliente) {
        this.tipoCliente = tipoCliente;
    }

    public String getCodAtendente() {
        return codigoAtendente;
    }

    public void setCodAtendente(String codAtendente) {
        this.codigoAtendente = codAtendente;
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getFilialColigada() {
        return filialColigada;
    }

    public void setFilialColigada(String filialColigada) {
        this.filialColigada = filialColigada;
    }

    public int getCod() {
        return codigo;
    }

    public void setCod(int cod) {
        this.codigo = cod;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getAtividade() {
        return atividade;
    }

    public void setAtividade(String atividade) {
        this.atividade = atividade;
    }

}
