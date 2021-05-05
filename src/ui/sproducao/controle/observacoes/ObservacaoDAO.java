/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.sproducao.controle.observacoes;

import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author claud
 */
public class ObservacaoDAO {
    public static void insereObs(ObservacaoBEAN obs) throws SQLException{
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = con.prepareStatement("INSERT INTO obs_ordem_producao "
                    + "VALUES(?,?,?)");
            stmt.setInt(1, obs.getCodigoOp());
            stmt.setDate(2, new java.sql.Date(obs.getData().getTime()));
            stmt.setString(3, obs.getObservacao());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            throw new SQLException(ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
    }
    
    public static List<ObservacaoBEAN> carregaObs(int codigoOp) throws SQLException{
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<ObservacaoBEAN> retorno = new ArrayList();

        try {
            stmt = con.prepareStatement("SELECT * "
                    + "FROM obs_ordem_producao "
                    + "WHERE CODIGO_OP = ?");
            stmt.setInt(1, codigoOp);
            rs = stmt.executeQuery();
            while(rs.next()){
                retorno.add(new ObservacaoBEAN(
                        codigoOp,
                        rs.getDate("DATA"),
                        rs.getString("OBSERVACAO")
                ));
            }
            return retorno;
        } catch (SQLException ex) {
            throw new SQLException(ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
    }
    
    public static void editaObs(ObservacaoBEAN obs, String obsAntiga) throws SQLException{
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = con.prepareStatement("UPDATE obs_ordem_producao "
                    + "SET DATA = ?, OBSERVACAO = ? "
                    + "WHERE CODIGO_OP = ? AND OBSERVACAO = ?");
            stmt.setDate(1, new java.sql.Date(obs.getData().getTime()));
            stmt.setString(2, obs.getObservacao());
            stmt.setInt(3, obs.getCodigoOp());
            stmt.setString(4, obsAntiga);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            throw new SQLException(ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
    }
    
    public static void excluiObs(int codigoOp, String obs) throws SQLException{
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = con.prepareStatement("DELETE FROM obs_ordem_producao "
                    + "WHERE CODIGO_OP = ? AND OBSERVACAO = ?");
            stmt.setInt(1, codigoOp);
            stmt.setString(2, obs);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            throw new SQLException(ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
    }
}
