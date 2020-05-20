/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

/**
 *
 * @author 1113778771
 */
public class Servicos {
    private int codigo;
    private int codigoOrcamento;
    private int codigoComponente;
    private int codigoProduto;

    public Servicos(Integer cod,
            Integer codOrcamento, 
            Integer codComponente){
        this.codigo = cod;
        this.codigoOrcamento = codOrcamento;
        this.codigoComponente = codComponente;
    }
    
    public Servicos(){
    
    }
    
    public Servicos(Integer codigo){
        this.codigo = codigo;
    }
    
    public int getCodProduto() {
        return codigoProduto;
    }

    public void setCodProduto(int codProduto) {
        this.codigoProduto = codProduto;
    }
    
    public int getCod_orcamento() {
        return codigoOrcamento;
    }

    public void setCod_orcamento(int cod_orcamento) {
        this.codigoOrcamento = cod_orcamento;
    }
    

    public int getCod_componente() {
        return codigoComponente;
    }

    public void setCod_componente(int cod_componente) {
        this.codigoComponente = cod_componente;
    }

    public int getCod() {
        return codigo;
    }

    public void setCod(int cod) {
        this.codigo = cod;
    }
}
