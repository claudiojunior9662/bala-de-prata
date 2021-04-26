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
public class Grid {
    private int id;
    private int idExterno;
    private int nome;
    private int nomeVisivel;
    private int enderecoUri;

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

    public int getNome() {
        return nome;
    }

    public void setNome(int nome) {
        this.nome = nome;
    }

    public int getNomeVisivel() {
        return nomeVisivel;
    }

    public void setNomeVisivel(int nomeVisivel) {
        this.nomeVisivel = nomeVisivel;
    }

    public int getEnderecoUri() {
        return enderecoUri;
    }

    public void setEnderecoUri(int enderecoUri) {
        this.enderecoUri = enderecoUri;
    }
    
    
}
