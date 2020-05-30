/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.erros;

import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author 1113778771
 */
public class ErroDAO {

    /**
     * Insere erro
     *
     * @param erro erro
     * @throws SQLException
     */
    public static void insereErro(ErroBEAN erro) throws SQLException {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("INSERT INTO erros(DESCRICAO, STATUS, USUARIO) "
                    + "VALUES(?,?,?)");
            stmt.setString(1, erro.getDescricao());
            stmt.setString(2, erro.getSts());
            stmt.setString(3, erro.getUsr());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            throw new SQLException(ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }

    /**
     * Retorna erros enviados
     *
     * @return
     * @throws SQLException
     */
    public static List<ErroBEAN> retornaErros() throws SQLException {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<ErroBEAN> retorno = new ArrayList();

        try {
            stmt = con.prepareStatement("SELECT * "
                    + "FROM erros "
                    + "ORDER BY ID "
                    + "DESC");
            rs = stmt.executeQuery();
            while (rs.next()) {
                retorno.add(new ErroBEAN(rs.getInt("ID"),
                        rs.getString("DESCRICAO"),
                        rs.getString("STATUS"),
                        rs.getString("USUARIO")));
            }
            return retorno;
        } catch (SQLException ex) {
            throw new SQLException(ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
    }

    /**
     * Exclui erro
     *
     * @param codErr código do erro
     * @throws SQLException
     */
    public static void excluiErr(int codErr) throws SQLException {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("DELETE FROM erros "
                    + "WHERE ID = ?");
            stmt.setInt(1, codErr);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            throw new SQLException(ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }

    /**
     * Atualiza o status do erro
     * @param erro
     * @throws SQLException 
     */
    public static void atualizaStsErr(ErroBEAN erro) throws SQLException {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("UPDATE erros "
                    + "SET STATUS = ? "
                    + "WHERE ID = ?");
            stmt.setString(1, erro.getSts());
            stmt.setInt(2, erro.getId());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            throw new SQLException(ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
}
