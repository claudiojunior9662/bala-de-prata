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
import model.bean.AtividadeBEAN;

/**
 *
 * @author spd3-linux
 */
public class AtividadeDAO {
    public void create(AtividadeBEAN aBEAN) throws SQLException{
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try{
            stmt = con.prepareStatement("INSERT INTO tabela_atividades(cod, descricao) VALUES(?,?)");
            stmt.setInt(1, aBEAN.getCodigo());
            stmt.setString(2, aBEAN.getDescricao());
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException ex) {
            throw new SQLException(ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
    public List<AtividadeBEAN> pesquisa(String tipo,String texto) throws SQLException{
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<AtividadeBEAN> aBEAN = new ArrayList();
        
        try{
            if(tipo.equals("CÓDIGO")){
                stmt = con.prepareStatement("SELECT * FROM tabela_atividades WHERE cod = ?");
                stmt.setString(1, texto); 
            }if(tipo.equals("DESCRIÇÃO")){
                stmt = con.prepareStatement("SELECT * FROM tabela_atividades WHERE descricao = ?");
                stmt.setString(1, texto); 
            }
            rs = stmt.executeQuery();
            while(rs.next()){
                AtividadeBEAN aAUX = new AtividadeBEAN();
                aAUX.setCodigo(rs.getInt("cod"));
                aAUX.setDescricao(rs.getString("descricao"));
                aBEAN.add(aAUX);
            }
        } catch (SQLException ex) {
            throw new SQLException(ex);
        }finally{
            ConnectionFactory.closeConnection(con,stmt,rs);
        }
        return aBEAN;
    }
    
    public List<AtividadeBEAN> mostraUltimos() throws SQLException{
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<AtividadeBEAN> aBEAN = new ArrayList();
        
        try{
            stmt = con.prepareStatement("SELECT * FROM tabela_atividades ORDER BY cod DESC LIMIT 45");
            rs = stmt.executeQuery();
            while(rs.next()){
                AtividadeBEAN aAUX = new AtividadeBEAN();
                aAUX.setCodigo(rs.getInt("cod"));
                aAUX.setDescricao(rs.getString("descricao"));
                aBEAN.add(aAUX);
            }
        } catch (SQLException ex) {
            throw new SQLException(ex);
        }finally{
            ConnectionFactory.closeConnection(con,stmt,rs);
        }
        return aBEAN;
    }
    
    public void excluir(int cod) throws SQLException{
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try{
            stmt = con.prepareStatement("DELETE FROM tabela_atividades WHERE cod = ?");
            stmt.setInt(1, cod);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            throw new SQLException(ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
    public int retornaProximo() throws SQLException{
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int retorno = 0;
        
        try{
            stmt = con.prepareStatement("SELECT cod FROM tabela_atividades ORDER BY cod DESC LIMIT 1");
            rs = stmt.executeQuery();
            while(rs.next()){
                retorno = rs.getInt("cod");
            }
        } catch (SQLException ex) {
            throw new SQLException(ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return retorno + 1;
    }
}
