/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.consulta.simatex;

import connection.ConnectionFactory;
import connection.IntegracaoSimatex;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author claud
 */
public class ConsultaSimatexDAO {

    /*
    1 - nome material
    2 - codigo material
     */
    public static List<ConsultaSimatexBEAN> consultaMaterialSimatex(String textoPesquisa, byte tipoPesquisa) throws SQLException {
        Connection con = IntegracaoSimatex.conectaSimatex();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<ConsultaSimatexBEAN> retorno = new ArrayList();
        retorno.clear();

        try {
            switch (tipoPesquisa) {
                case 1:
                    stmt = con.prepareStatement("SELECT m.CODIGO AS CODIGO, "
                            + "m.NOME_PADRAO AS NOME_PADRAO, "
                            + "m.CARACTERISTICAS_TECNICAS AS CARACTERISTICAS_TECNICAS,"
                            + "s.QTDE_DISPONIVEL AS QTDE_DISPONIVEL, "
                            + "s.VALOR_UNITARIO AS VALOR_UNITARIO, "
                            + "s.DT_OPER AS DT_OPER "
                            + "FROM MATERIAIS m "
                            + "INNER JOIN SALDOS_MAT_CONS_DEP s ON s.COD_MAT = CODIGO "
                            + "WHERE m.NOME_PADRAO LIKE '%" + textoPesquisa + "%'");
                    break;
                case 2:
                    stmt = con.prepareStatement("SELECT m.CODIGO AS CODIGO, "
                            + "m.NOME_PADRAO AS NOME_PADRAO, "
                            + "m.CARACTERISTICAS_TECNICAS AS CARACTERISTICAS_TECNICAS,"
                            + "s.QTDE_DISPONIVEL AS QTDE_DISPONIVEL, "
                            + "s.VALOR_UNITARIO AS VALOR_UNITARIO, "
                            + "s.DT_OPER AS DT_OPER "
                            + "FROM MATERIAIS m "
                            + "INNER JOIN SALDOS_MAT_CONS_DEP s ON s.COD_MAT = CODIGO "
                            + "WHERE m.CODIGO = ?");
                    stmt.setString(1, textoPesquisa);
                    break;
            }
            rs = stmt.executeQuery();
            while (rs.next()) {
                retorno.add(new ConsultaSimatexBEAN(rs.getString("CODIGO"),
                        rs.getString("NOME_PADRAO"),
                        rs.getString("CARACTERISTICAS_TECNICAS"),
                        rs.getInt("QTDE_DISPONIVEL"),
                        rs.getFloat("VALOR_UNITARIO"),
                        rs.getDate("DT_OPER")));
            }
        } catch (SQLException ex) {
            throw new SQLException(ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return retorno;
    }
}
