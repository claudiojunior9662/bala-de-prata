/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.administrador;

/**
 *
 * @author spd3
 */
public class FuncionarioBEAN {

    private String codigoAtendente;
    private String loginAtendente;
    private String nomeAtendente;
    private String senhaAtendente;
    private String tipoAtendente;
    private byte acessoOrcamentacao;
    private byte acessoProducao;
    private byte acessoExpedicao;
    private byte acessoFinanceiro;
    private byte acessoEstoque;
    private byte acessoOrd;
    private int ativo;

    public FuncionarioBEAN(String codigoAtendente,
            String loginAtendente,
            String nomeAtendente,
            String tipoAtendente) {
        this.codigoAtendente = codigoAtendente;
        this.loginAtendente = loginAtendente;
        this.nomeAtendente = nomeAtendente;
        this.tipoAtendente = tipoAtendente;
    }

    public FuncionarioBEAN(String nomeAtendente,
            String codigoAtendente,
            String loginAtendente,
            String tipoAtendente,
            byte acessoOrcamentacao,
            byte acessoProducao,
            byte acessoExpedicao,
            byte acessoFinanceiro,
            byte acessoEstoque,
            Integer ativo) {
        this.nomeAtendente = nomeAtendente;
        this.codigoAtendente = codigoAtendente;
        this.loginAtendente = loginAtendente;
        this.tipoAtendente = tipoAtendente;
        this.acessoOrcamentacao = acessoOrcamentacao;
        this.acessoProducao = acessoProducao;
        this.acessoExpedicao = acessoExpedicao;
        this.acessoFinanceiro = acessoFinanceiro;
        this.acessoEstoque = acessoEstoque;
        this.ativo = ativo;
    }

    public FuncionarioBEAN(String codigoAtendente, String nomeAtendente) {
        this.codigoAtendente = codigoAtendente;
        this.nomeAtendente = nomeAtendente;
    }

    public byte getAcessoOrd() {
        return acessoOrd;
    }

    public void setAcessoOrd(byte acessoOrd) {
        this.acessoOrd = acessoOrd;
    }

    public FuncionarioBEAN() {

    }

    public byte getAcessoOrcamentacao() {
        return acessoOrcamentacao;
    }

    public void setAcessoOrcamentacao(byte acessoOrcamentacao) {
        this.acessoOrcamentacao = acessoOrcamentacao;
    }

    public byte getAcessoProducao() {
        return acessoProducao;
    }

    public void setAcessoProducao(byte acessoProducao) {
        this.acessoProducao = acessoProducao;
    }

    public byte getAcessoExpedicao() {
        return acessoExpedicao;
    }

    public void setAcessoExpedicao(byte acessoExpedicao) {
        this.acessoExpedicao = acessoExpedicao;
    }

    public byte getAcessoFinanceiro() {
        return acessoFinanceiro;
    }

    public void setAcessoFinanceiro(byte acessoFinanceiro) {
        this.acessoFinanceiro = acessoFinanceiro;
    }

    public byte getAcessoEstoque() {
        return acessoEstoque;
    }

    public void setAcessoEstoque(byte acessoEstoque) {
        this.acessoEstoque = acessoEstoque;
    }

    public int getAtivo() {
        return ativo;
    }

    public void setAtivo(int ativo) {
        this.ativo = ativo;
    }

    public String getCodigoAtendente() {
        return codigoAtendente;
    }

    public void setCodigoAtendente(String codigo_atendente) {
        this.codigoAtendente = codigo_atendente;
    }

    public String getLoginAtendente() {
        return loginAtendente;
    }

    public void setLogin_atendente(String login_atendente) {
        this.loginAtendente = login_atendente;
    }

    public String getNomeAtendente() {
        return nomeAtendente;
    }

    public void setNomeAtendente(String nome_atendente) {
        this.nomeAtendente = nome_atendente;
    }

    public String getSenhaAtendente() {
        return senhaAtendente;
    }

    public void setSenha_atendente(String senha_atendente) {
        this.senhaAtendente = senha_atendente;
    }

    public String getTipoAtendente() {
        return tipoAtendente;
    }

    public void setTipoAtendente(String tipo_atendente) {
        this.tipoAtendente = tipo_atendente;
    }

}
