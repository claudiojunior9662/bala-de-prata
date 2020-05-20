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
import model.bean.MateriaisCadastroBEAN;

/**
 *
 * @author spd3
 */
public class MateriaisCadastroDAO {
    public static void create(MateriaisCadastroBEAN mcBEAN) throws SQLException{
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        
        try{
            stmt = con.prepareStatement("INSERT INTO tabela_materiais(descricao, estoque_seg, preco_custo, preco_venda, data_alteracao) VALUES(?,?,?,?,?)");
            stmt.setString(1, mcBEAN.getDescricao());
            stmt.setInt(2, mcBEAN.getEstoque_seg());
            stmt.setFloat(3, mcBEAN.getPreco_custo());
            stmt.setFloat(4, mcBEAN.getPreco_venda());
            stmt.setString(5, mcBEAN.getData_alteracao());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            throw new SQLException(ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
    public static List<MateriaisCadastroBEAN> consulta(String tipo, String texto) throws SQLException{
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
       
        List<MateriaisCadastroBEAN> cadastromc = new ArrayList<>();
        
        try{
            
            if(tipo == "Código"){
                stmt = con.prepareStatement("SELECT * FROM tabela_materiais WHERE cod = " +  texto);
            }else if(tipo == "Descrição"){
                stmt = con.prepareStatement("SELECT * FROM tabela_materiais WHERE descricao LIKE " + "'%" + texto + "%'");
            }else if(tipo == "Estoque de Segurança"){
                stmt = con.prepareStatement("SELECT * FROM tabela_materiais WHERE estoque_seg LIKE " + "'%" + texto + "%'");
            }else if(tipo == "Preço Custo"){
                stmt = con.prepareStatement("SELECT * FROM tabela_materiais WHERE preco_custo LIKE " + "'%" + texto + "%'");
            }else if(tipo == "Preço Venda"){
                stmt = con.prepareStatement("SELECT * FROM tabela_materiais WHERE data_alteracao LIKE " + "'%" + texto + "%'");
            }
            
            rs = stmt.executeQuery();
            
            while(rs.next()){
                MateriaisCadastroBEAN mcBEAN = new MateriaisCadastroBEAN();
                mcBEAN.setCod(rs.getInt("cod"));
                mcBEAN.setDescricao(rs.getString("descricao"));
                mcBEAN.setEstoque_seg(rs.getInt("estoque_seg"));
                mcBEAN.setPreco_custo(rs.getFloat("preco_custo"));
                mcBEAN.setPreco_venda(rs.getFloat("preco_venda"));
                mcBEAN.setData_alteracao(rs.getString("data_alteracao"));
                cadastromc.add(mcBEAN);
            }
        } catch (SQLException ex) {
            throw new SQLException(ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return cadastromc;
    }
    
    public static List<MateriaisCadastroBEAN> mostraTodos() throws SQLException{
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<MateriaisCadastroBEAN> cadastromc = new ArrayList<>();
        
        try{
            stmt = con.prepareStatement("SELECT * FROM tabela_materiais");
            rs = stmt.executeQuery();
            
            while(rs.next()){
                MateriaisCadastroBEAN mcBEAN = new MateriaisCadastroBEAN();
                mcBEAN.setCod(rs.getInt("cod"));
                mcBEAN.setDescricao(rs.getString("descricao"));
                mcBEAN.setEstoque_seg(rs.getInt("estoque_seg"));
                mcBEAN.setPreco_custo(rs.getFloat("preco_custo"));
                mcBEAN.setPreco_venda(rs.getFloat("preco_venda"));
                mcBEAN.setData_alteracao(rs.getString("data_alteracao"));
                cadastromc.add(mcBEAN);
            }
        } catch (SQLException ex) {
            throw new SQLException(ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return cadastromc;
    }
    
    public void excluir_registro(int cod) throws SQLException{
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try{
            stmt = con.prepareStatement("DELETE FROM tabela_materiais WHERE cod = ?");
            stmt.setInt(1,cod);
            stmt.executeUpdate();
        }catch(SQLException ex){
            throw new SQLException(ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
    public static List<MateriaisCadastroBEAN> carregaEdicao(int cod) throws SQLException{
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<MateriaisCadastroBEAN> cadastromc = new ArrayList(); 
        
        try{
            stmt = con.prepareStatement("SELECT * FROM tabela_materiais WHERE cod = ?");
            stmt.setInt(1, cod);
            rs = stmt.executeQuery();
            
            while(rs.next()){
                MateriaisCadastroBEAN mcBEAN = new MateriaisCadastroBEAN();
                mcBEAN.setCod(rs.getInt("cod"));
                mcBEAN.setDescricao(rs.getString("descricao"));
                mcBEAN.setEstoque_seg(rs.getInt("estoque_seg"));
                mcBEAN.setPreco_custo(rs.getFloat("preco_custo"));
                mcBEAN.setPreco_venda(rs.getFloat("preco_venda"));
                mcBEAN.setData_alteracao(rs.getString("data_alteracao"));
                cadastromc.add(mcBEAN);
            }
        } catch (SQLException ex) {
            throw new SQLException(ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return cadastromc;
    }
    
    public static void alteraRegistro(int cod, MateriaisCadastroBEAN mcBEAN) throws SQLException{
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try{
            stmt = con.prepareStatement("UPDATE tabela_materiais SET descricao = ?, estoque_seg = ?, preco_custo = ?, preco_venda = ?, data_alteracao = ? WHERE cod = ?");
            stmt.setString(1, mcBEAN.getDescricao());
            stmt.setInt(2, mcBEAN.getEstoque_seg());
            stmt.setFloat(3, mcBEAN.getPreco_custo());
            stmt.setFloat(4, mcBEAN.getPreco_venda());
            stmt.setString(5, mcBEAN.getData_alteracao());
            stmt.setInt(6,cod);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            throw new SQLException(ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
}
