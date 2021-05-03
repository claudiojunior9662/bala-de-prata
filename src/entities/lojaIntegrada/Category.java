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
    private int idExterno;
    private String nome;
    private String descricao;
    private Category pai;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdExterno() {
        return idExterno;
    }

    public void setIdExterno(int idExterno) {
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
