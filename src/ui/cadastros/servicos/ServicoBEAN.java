/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.cadastros.servicos;

/**
 *
 * @author spd3
 */
public class ServicoBEAN {
    private int cod;
    private String descricao;
    private float valor_minimo;
    private float valor_unitario;
    private int servico_geral;
    private String tipo_servico;

    public ServicoBEAN() {
        
    }

    public ServicoBEAN(int cod, 
            String descricao, 
            float valor_minimo, 
            float valor_unitario, 
            int servico_geral, 
            String tipo_servico) {
        this.cod = cod;
        this.descricao = descricao;
        this.valor_minimo = valor_minimo;
        this.valor_unitario = valor_unitario;
        this.servico_geral = servico_geral;
        this.tipo_servico = tipo_servico;
    }
    
    

    
    
    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public float getValorMinimo() {
        return valor_minimo;
    }

    public void setValorMinimo(float valor_minimo) {
        this.valor_minimo = valor_minimo;
    }

    public float getValorUnitario() {
        return valor_unitario;
    }

    public void setValorUnitario(float valor_unitario) {
        this.valor_unitario = valor_unitario;
    }

    public int getServicoGeral() {
        return servico_geral;
    }

    public void setServicoGeral(int servico_geral) {
        this.servico_geral = servico_geral;
    }

    public String getTipoServico() {
        return tipo_servico;
    }

    public void setTipoServico(String tipo_servico) {
        this.tipo_servico = tipo_servico;
    }
    
    
}
