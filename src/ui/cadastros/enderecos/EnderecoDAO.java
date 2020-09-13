/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.cadastros.enderecos;

import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author claudio
 */
public class EnderecoDAO {
    
    /**
     * Retorna o endereço formatado
     * @param codEndereco código do endereço
     * @return 
     */
    public synchronized static String retornaEndereço(int codEndereco) throws SQLException{
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            stmt = con.prepareStatement("SELECT "
                    + "cep,"
                    + "tipo_endereco,"
                    + "logadouro,"
                    + "bairro,"
                    + "uf,"
                    + "cidade,"
                    + "complemento "
                    + "FROM tabela_enderecos "
                    + "WHERE cod = ?");
            stmt.setInt(1, codEndereco);
            rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getString("logadouro")
                        + ", "
                        + rs.getString("bairro")
                        + ", "
                        + rs.getString("cidade") 
                        + ", "
                        + rs.getString("uf")
                        + " - "
                        + rs.getString("cep")
                        + " ( "
                        + rs.getString("complemento")
                        + " )";
            }
            return null;
        } catch (SQLException ex) {
            throw new SQLException(ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
    }
    
}
