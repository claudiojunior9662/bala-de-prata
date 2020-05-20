/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.erros;

/**
 *
 * @author 1113778771
 */
public class ErroBEAN {
    private Integer id;
    private String descricao;
    private String sts;
    private String usr;

    public ErroBEAN(Integer id, 
            String descricao, 
            String status,
            String usr) {
        this.id = id;
        this.descricao = descricao;
        this.sts = status;
        this.usr = usr;
    }
    
    public ErroBEAN( String descricao, 
            String status,
            String usr) {
        this.descricao = descricao;
        this.sts = status;
        this.usr = usr;
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getSts() {
        return sts;
    }

    public void setSts(String sts) {
        this.sts = sts;
    }

    public String getUsr() {
        return usr;
    }

    public void setUsr(String usr) {
        this.usr = usr;
    }
    
}
