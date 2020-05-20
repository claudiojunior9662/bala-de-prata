/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.cadastros.chapas;

import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import entidades.Chapa;

/**
 *
 * @author spd3
 */
public class ChapaDAO {
    
    public void create(ChapaBEAN cadastroChapasBEAN) throws SQLException{
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try{
            stmt = con.prepareStatement("INSERT INTO tabela_chapas(descricao, largura, altura, durabilidade, tipo,"
                                        + "valor_unitario) VALUES(?,?,?,?,?,?)");
            stmt.setString(1,cadastroChapasBEAN.getDescricao());
            stmt.setFloat(2,cadastroChapasBEAN.getLargura());
            stmt.setFloat(3,cadastroChapasBEAN.getAltura());
            stmt.setInt(4, cadastroChapasBEAN.getDurabilidade());
            stmt.setString(5, cadastroChapasBEAN.getTipo());
            stmt.setFloat(6, cadastroChapasBEAN.getValor_unitario());
            stmt.executeUpdate();
        }catch(SQLException ex){
            throw new SQLException(ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
   public List<Chapa> consulta(String tipo, String pesquisa) throws SQLException{
       Connection con = ConnectionFactory.getConnection();
       PreparedStatement stmt = null;
       ResultSet rs = null;
       
       List<Chapa> retorno = new ArrayList<>();
       
       try{
                
            if(tipo == "Código"){
                stmt = con.prepareStatement("SELECT * FROM tabela_chapas WHERE cod = " + pesquisa);
            }else if(tipo == "Descrição"){
                stmt = con.prepareStatement("SELECT * FROM tabela_chapas WHERE descricao LIKE " + "'%" +  pesquisa + "%'");
            }else if(tipo == "Largura"){
                stmt = con.prepareStatement("SELECT * FROM tabela_chapas WHERE largura LIKE " + "'%" +  pesquisa + "%'");
            }else if(tipo == "Altura"){
                stmt = con.prepareStatement("SELECT * FROM tabela_chapas WHERE altura LIKE " + "'%" +  pesquisa + "%'");
            }else if(tipo == "Durabilidade"){
                stmt = con.prepareStatement("SELECT * FROM tabela_chapas WHERE durabilidade LIKE " + "'%" +  pesquisa + "%'");
            }else if(tipo == "Tipo"){
                stmt = con.prepareStatement("SELECT * FROM tabela_chapas WHERE tipo LIKE " + "'%" +  pesquisa + "%'");
            }else if(tipo == "Valor Unitário"){
                stmt = con.prepareStatement("SELECT * FROM tabela_chapas WHERE valor_unitario LIKE " + "'%" +  pesquisa + "%'");
            }
            
            rs = stmt.executeQuery();

            while(rs.next()){
                Chapa cp = new Chapa();
                cp.setCod(rs.getInt("cod"));
                cp.setDescricao(rs.getString("descricao"));
                cp.setLargura(rs.getFloat("largura"));
                cp.setAltura(rs.getFloat("altura"));
                cp.setDurabilidade(rs.getInt("durabilidade"));
                cp.setTipo(rs.getString("tipo"));
                cp.setValor_unitario(rs.getFloat("valor_unitario"));
                retorno.add(cp);
            }
        }catch(SQLException ex){
            throw new SQLException(ex);
        }finally{
            ConnectionFactory.closeConnection(con,stmt,rs);
        }
        return retorno;
   }
   
   public List<Chapa> mostra_todos() throws SQLException{
       Connection con = ConnectionFactory.getConnection();
       PreparedStatement stmt = null;
       ResultSet rs = null;
       
       List<Chapa> cadastrocp = new ArrayList<>();
       
       try{
           stmt = con.prepareStatement("SELECT * FROM tabela_chapas");
           rs = stmt.executeQuery();
           while(rs.next()){
               Chapa cp = new Chapa();
                cp.setCod(rs.getInt("cod"));
                cp.setDescricao(rs.getString("descricao"));
                cp.setLargura(rs.getFloat("largura"));
                cp.setAltura(rs.getFloat("altura"));
                cp.setDurabilidade(rs.getInt("durabilidade"));
                cp.setTipo(rs.getString("tipo"));
                cp.setValor_unitario(rs.getFloat("valor_unitario"));
                cadastrocp.add(cp);
           }
       }catch(SQLException ex){
            throw new SQLException(ex);
       }finally{
            ConnectionFactory.closeConnection(con,stmt,rs);
       }
       return cadastrocp;
   }
   
   public void exclui_registro(int cod) throws SQLException{
       Connection con = ConnectionFactory.getConnection();
       PreparedStatement stmt = null;
       
       try{
           stmt = con.prepareStatement("DELETE FROM tabela_chapas WHERE cod = ?");
           stmt.setInt(1, cod);
           stmt.executeUpdate();
       } catch (SQLException ex) {
            throw new SQLException(ex);
       }finally{
           ConnectionFactory.closeConnection(con,stmt);
       }
   }
   
   public List<Chapa> carregar_edicao(int cod) throws SQLException{
       Connection con = ConnectionFactory.getConnection();
       PreparedStatement stmt = null;
       ResultSet rs = null;
       
       List<Chapa> cadastrocp = new ArrayList<>();
       
       try{
           stmt = con.prepareStatement("SELECT * FROM tabela_chapas WHERE cod = ?");
           stmt.setInt(1,cod);
           rs = stmt.executeQuery();
           while(rs.next()){
               Chapa cp = new Chapa();
                cp.setCod(rs.getInt("cod"));
                cp.setDescricao(rs.getString("descricao"));
                cp.setLargura(rs.getFloat("largura"));
                cp.setAltura(rs.getFloat("altura"));
                cp.setDurabilidade(rs.getInt("durabilidade"));
                cp.setTipo(rs.getString("tipo"));
                cp.setValor_unitario(rs.getFloat("valor_unitario"));
                cadastrocp.add(cp);
           }
       }catch(SQLException ex){
            throw new SQLException(ex);
       }finally{
            ConnectionFactory.closeConnection(con,stmt,rs);
       }
       return cadastrocp;
   }
   
   public void editarRegistro(int cod, Chapa cp) throws SQLException{
       Connection con = ConnectionFactory.getConnection();
       PreparedStatement stmt = null;
       
        try {
            stmt = con.prepareStatement("UPDATE tabela_chapas SET descricao = ?, largura = ?, altura = ?, durabilidade = ?, tipo = ?, valor_unitario = ? WHERE cod = ?");
            stmt.setString(1, cp.getDescricao());
            stmt.setFloat(2, cp.getLargura());
            stmt.setFloat(3, cp.getAltura());
            stmt.setInt(4, cp.getDurabilidade());
            stmt.setString(5, cp.getTipo());
            stmt.setFloat(6, cp.getValor_unitario());
            stmt.setInt(7, cod);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            throw new SQLException(ex);
        }finally{
            ConnectionFactory.closeConnection(con,stmt);
        }
   }
}
