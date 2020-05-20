/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.cadastros.enderecos;

import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.text.MaskFormatter;

/**
 *
 * @author claudio
 */
public class EnderecoBEAN {
    private int codigo;
    private String cep;
    private int codCliente;
    private String tipoEndereco;
    private String logradouro;
    private String bairro;
    private String uf;
    private String complemento;
    private String cidade;
    
    public EnderecoBEAN(String logradouro,
                                            String complemento,
                                            String bairro,
                                            String cidade,
                                            String uf){
        this.logradouro = logradouro;
        this.complemento = complemento;
        this.bairro = bairro;
        this.cidade = cidade;
        this.uf = uf;
    }

    public EnderecoBEAN(int codigo, 
            String cep, 
            String tipoEndereco, 
            String logradouro, 
            String bairro, 
            String uf, 
            String complemento, 
            String cidade) {
        this.codigo = codigo;
        this.cep = cep;
        this.tipoEndereco = tipoEndereco;
        this.logradouro = logradouro;
        this.bairro = bairro;
        this.uf = uf;
        this.complemento = complemento;
        this.cidade = cidade;
    }
    
    public EnderecoBEAN(String cep, 
            String tipoEndereco, 
            String logradouro, 
            String bairro, 
            String uf, 
            String complemento, 
            String cidade) {
        this.cep = cep;
        this.tipoEndereco = tipoEndereco;
        this.logradouro = logradouro;
        this.bairro = bairro;
        this.uf = uf;
        this.complemento = complemento;
        this.cidade = cidade;
    }
    
    
    
    public EnderecoBEAN(){
    
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
    
    

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public int getCodCliente() {
        return codCliente;
    }

    public void setCodCliente(int codCliente) {
        this.codCliente = codCliente;
    }

    public String getTipoEndereco() {
        return tipoEndereco;
    }

    public void setTipoEndereco(String tipoEndereco) {
        this.tipoEndereco = tipoEndereco;
    }

    public String getLogadouro() {
        return logradouro;
    }

    public void setLogadouro(String logadouro) {
        this.logradouro = logadouro;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }
    
    public synchronized static String retornaCepFormatado(String cep) {
        String formatar = cep.replace(".", "-").replace("-", "");
        try {
            MaskFormatter formatoTelefone = new MaskFormatter("##.###-###");
            formatoTelefone.setValueContainsLiteralCharacters(false);
            formatar = formatoTelefone.valueToString(formatar);
        } catch (ParseException ex) {
            Logger.getLogger(EnderecoBEAN.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return formatar;
    }
    
}
