/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities.lojaIntegrada;

/**
 *
 * @author claud
 */
public class Category {

    private int id;
    private String idExterno;
    private String nome;
    private String descricao;
    private Category pai;

    //Constructors--------------------------------------------------------------
    public Category(String idExterno, String nome, String descricao, Category pai) {
        this.idExterno = idExterno;
        this.nome = nome;
        this.descricao = descricao;
        this.pai = pai;
    }

    //Getters and setters-------------------------------------------------------
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIdExterno() {
        return idExterno;
    }

    public void setIdExterno(String idExterno) {
        this.idExterno = idExterno;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Category getPai() {
        return pai;
    }

    public void setPai(Category pai) {
        this.pai = pai;
    }

}
