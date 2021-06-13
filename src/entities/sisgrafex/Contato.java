/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities.sisgrafex;

/**
 *
 * @author claudio
 */
public class Contato {

    private int cod;
    private String tabela;
    private int codCliente;
    private String nomeContato;
    private String email;
    private String telefone;
    private String ramal;
    private String telefone2;
    private String ramal2;
    private String departamento;
    private String tipoPessoa;

    @Override
    public String toString() {
        return "Contato{" + "cod=" + cod + ", tabela=" + tabela + ", codCliente=" + codCliente + ", nomeContato=" + nomeContato + ", email=" + email + ", telefone=" + telefone + ", ramal=" + ramal + ", telefone2=" + telefone2 + ", ramal2=" + ramal2 + ", departamento=" + departamento + ", tipoPessoa=" + tipoPessoa + '}';
    }

    public Contato(int cod,
            String nomeContato,
            String email,
            String telefone,
            String ramal,
            String telefone2,
            String ramal2,
            String departamento) {
        this.cod = cod;
        this.nomeContato = nomeContato;
        this.email = email;
        this.telefone = telefone;
        this.ramal = ramal;
        this.telefone2 = telefone2;
        this.ramal2 = ramal2;
        this.departamento = departamento;
    }

    public Contato(String nomeContato, String email, String telefone, String telefone2) {
        this.nomeContato = nomeContato;
        this.email = email;
        this.telefone = telefone;
        this.telefone2 = telefone2;
    }

    public Contato() {

    }

    public String getTelefone2() {
        return telefone2;
    }

    public void setTelefone2(String telefone2) {
        this.telefone2 = telefone2;
    }

    public String getRamal2() {
        return ramal2;
    }

    public void setRamal2(String ramal2) {
        this.ramal2 = ramal2;
    }

    public String getTipoPessoa() {
        return tipoPessoa;
    }

    public void setTipoPessoa(String tipoPessoa) {
        this.tipoPessoa = tipoPessoa;
    }

    public String getTabela() {
        return tabela;
    }

    public void setTabela(String tabela) {
        this.tabela = tabela;
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public int getCodCliente() {
        return codCliente;
    }

    public void setCodCliente(int codCliente) {
        this.codCliente = codCliente;
    }

    public String getNomeContato() {
        return nomeContato;
    }

    public void setNomeContato(String nomeContato) {
        this.nomeContato = nomeContato;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getRamal() {
        return ramal;
    }

    public void setRamal(String ramal) {
        this.ramal = ramal;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

}
