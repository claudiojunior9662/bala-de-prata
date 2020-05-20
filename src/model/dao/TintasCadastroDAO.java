/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.bean.TintasCadastroBEAN;

/**
 *
 * @author spd3
 */
public class TintasCadastroDAO {
    public static void create(TintasCadastroBEAN tcBEAN) throws SQLException{
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try{
            stmt = con.prepareStatement("INSERT INTO tabela_tintas(descricao, fator_absorcao, valor_kg) VALUES(?,?,?)");
            stmt.setString(1, tcBEAN.getDescricao());
            stmt.setFloat(2, tcBEAN.getFator_absorcao());
            stmt.setFloat(3, tcBEAN.getValor_kg());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            throw new SQLException();
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
    public static List<TintasCadastroBEAN> pesquisaRegistro(String tipo, String texto) throws SQLException{
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<TintasCadastroBEAN> cadastrotc = new ArrayList<>();
        
        try{
            
            if(tipo == "Código"){
                stmt = con.prepareStatement("SELECT * FROM tabela_tintas WHERE cod = " + texto);
            }else if(tipo == "Descrição"){
                stmt = con.prepareStatement("SELECT * FROM tabela_tintas WHERE descricao LIKE " + "'%" + texto + "%'");
            }else if(tipo == "Fator de Absorção"){
                stmt = con.prepareStatement("SELECT * FROM tabela_tintas WHERE fator_absorcao LIKE " + "'%" + texto + "%'");
            }else if(tipo == "Valor por Kg"){
                stmt = con.prepareStatement("SELECT * FROM tabela_tintas WHERE valor_kg LIKE " + "'%" + texto + "%'");
            }
            
            rs = stmt.executeQuery();
            
            while(rs.next()){
                TintasCadastroBEAN tcBEAN = new TintasCadastroBEAN();
                tcBEAN.setCod(rs.getInt("cod"));
                tcBEAN.setDescricao(rs.getString("descricao"));
                tcBEAN.setFator_absorcao(rs.getFloat("fator_absorcao"));
                tcBEAN.setValor_kg(rs.getFloat("valor_kg"));
                cadastrotc.add(tcBEAN);
            }
        } catch (SQLException ex) {
            throw new SQLException();
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return cadastrotc;
    }
    
   public static List<TintasCadastroBEAN> mostraTodos() throws SQLException{
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<TintasCadastroBEAN> cadastrotc = new ArrayList<>();
        
        try{
            
            stmt = con.prepareStatement("SELECT * FROM tabela_tintas");
            
            rs = stmt.executeQuery();
            
            while(rs.next()){
                TintasCadastroBEAN tcBEAN = new TintasCadastroBEAN();
                tcBEAN.setCod(rs.getInt("cod"));
                tcBEAN.setDescricao(rs.getString("descricao"));
                tcBEAN.setFator_absorcao(rs.getFloat("fator_absorcao"));
                tcBEAN.setValor_kg(rs.getFloat("valor_kg"));
                cadastrotc.add(tcBEAN);
            }
        } catch (SQLException ex) {
            throw new SQLException();
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return cadastrotc;
    }
   
   public void exclui_registro(int cod) throws SQLException{
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try{
            stmt = con.prepareStatement("DELETE FROM tabela_tintas WHERE cod = ?");
            stmt.setInt(1, cod);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            throw new SQLException();
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
   }
   
   public static void alteraRegistro(int cod, TintasCadastroBEAN tcBEAN) throws SQLException{
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try{
            stmt = con.prepareStatement("UPDATE tabela_tintas SET descricao = ?, fator_absorcao = ?, valor_kg = ? WHERE cod = ?");
            stmt.setString(1, tcBEAN.getDescricao());
            stmt.setFloat(2, tcBEAN.getFator_absorcao());
            stmt.setFloat(3, tcBEAN.getValor_kg());
            stmt.setInt(4, cod);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            throw new SQLException();
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
   }
   
   public static List<TintasCadastroBEAN> carregaEdicao(int cod) throws SQLException{
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<TintasCadastroBEAN> cadastrotc = new ArrayList<>();
        
        try{
            stmt = con.prepareStatement("SELECT * FROM tabela_tintas WHERE cod = ?");
            stmt.setInt(1,cod);
            
            rs = stmt.executeQuery();
            
            while(rs.next()){
                TintasCadastroBEAN tcBEAN = new TintasCadastroBEAN();
                tcBEAN.setCod(cod);
                tcBEAN.setDescricao(rs.getString("descricao"));
                tcBEAN.setFator_absorcao(rs.getFloat("fator_absorcao"));
                tcBEAN.setValor_kg(rs.getFloat("valor_kg"));
                cadastrotc.add(tcBEAN);
            }
        } catch (SQLException ex) {
            throw new SQLException();
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return cadastrotc;
   }
   
   
}
