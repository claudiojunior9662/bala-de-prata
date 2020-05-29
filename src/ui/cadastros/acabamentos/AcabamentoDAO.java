/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.cadastros.acabamentos;

import connection.ConnectionFactory;
import exception.SemAcabamentoException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import ui.cadastros.produtos.AcabamentoProdBEAN;

/**
 *
 * @author spd3
 */
public class AcabamentoDAO {

    public static void cria(AcabamentoBEAN acabamentosBEAN) throws SQLException {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("INSERT INTO ACABAMENTOS(MAQUINA, ATIVA, CUSTO_HORA) "
                    + "VALUES(?,?,?)");
            stmt.setString(1, acabamentosBEAN.getNomeMaquina());
            stmt.setInt(2, acabamentosBEAN.getMaquinaAtiva());
            stmt.setFloat(3, acabamentosBEAN.getCustoHora());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            throw new SQLException(ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }

    public static void atualiza(AcabamentoBEAN acabamentosBEAN) throws SQLException {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("UPDATE ACABAMENTOS SET MAQUINA = ?, CUSTO_HORA = ? "
                    + "WHERE CODIGO = ?");
            stmt.setString(1, acabamentosBEAN.getNomeMaquina());
            stmt.setFloat(2, acabamentosBEAN.getCustoHora());
            stmt.setInt(3, acabamentosBEAN.getCodigo());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            throw new SQLException(ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }

    public static List<AcabamentoBEAN> mostraTodos() throws SQLException {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<AcabamentoBEAN> retorno = new ArrayList();

        try {
            stmt = con.prepareStatement("SELECT * "
                    + "FROM ACABAMENTOS "
                    + "ORDER BY CODIGO "
                    + "DESC");

            rs = stmt.executeQuery();

            while (rs.next()) {
                AcabamentoBEAN ab = new AcabamentoBEAN();
                ab.setCodigo(rs.getInt("CODIGO"));
                ab.setNomeMaquina(rs.getString("MAQUINA"));
                ab.setMaquinaAtiva(rs.getInt("ATIVA"));
                ab.setCustoHora(rs.getFloat("CUSTO_HORA"));
                retorno.add(ab);
            }
        } catch (SQLException ex) {
            throw new SQLException(ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return retorno;
    }

    /*
     @param tipo
     1 - CÓDIGO
     2 - NOME MÁQUINA
     3 - MÁQUINA ATIVA
     */
    public static List<AcabamentoBEAN> pesquisaRegistro(byte tipo, String texto) throws SQLException {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<AcabamentoBEAN> retorno = new ArrayList();

        try {

            switch (tipo) {
                case 1:
                    stmt = con.prepareStatement("SELECT * FROM ACABAMENTOS WHERE CODIGO = ?");
                    stmt.setInt(1, Integer.valueOf(texto));
                    break;
                case 2:
                    stmt = con.prepareStatement("SELECT * FROM ACABAMENTOS WHERE MAQUINA LIKE '%" + texto + "%'");
                    break;
                case 3:
                    stmt = con.prepareStatement("SELECT * FROM ACABAMENTOS WHERE ATIVA = ?");
                    stmt.setByte(1, Byte.valueOf(texto));
                    break;
            }

            rs = stmt.executeQuery();

            while (rs.next()) {
                AcabamentoBEAN ab = new AcabamentoBEAN();
                ab.setCodigo(rs.getInt("CODIGO"));
                ab.setNomeMaquina(rs.getString("MAQUINA"));
                ab.setMaquinaAtiva(rs.getInt("ATIVA"));
                ab.setCustoHora(rs.getInt("CUSTO_HORA"));
                retorno.add(ab);
            }
        } catch (SQLException ex) {
            throw new SQLException(ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return retorno;
    }

    public static List<AcabamentoBEAN> carregaEdicao(int CODIGO) throws SQLException {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<AcabamentoBEAN> cadastroab = new ArrayList();

        try {
            stmt = con.prepareStatement("SELECT * FROM ACABAMENTOS WHERE CODIGO = ?");
            stmt.setInt(1, CODIGO);

            rs = stmt.executeQuery();

            while (rs.next()) {
                AcabamentoBEAN ab = new AcabamentoBEAN();
                ab.setCodigo(rs.getInt("CODIGO"));
                ab.setNomeMaquina(rs.getString("MAQUINA"));
                ab.setMaquinaAtiva(rs.getInt("ATIVA"));
                ab.setCustoHora(rs.getFloat("CUSTO_HORA"));
                cadastroab.add(ab);
            }
        } catch (SQLException ex) {
            throw new SQLException(ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return cadastroab;
    }

    public static List<AcabamentoBEAN> carregaOrcamento(int CODIGO) throws SQLException {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<AcabamentoBEAN> cadastroab = new ArrayList();

        try {
            stmt = con.prepareStatement("SELECT * FROM ACABAMENTOS WHERE CODIGO = " + CODIGO);
            rs = stmt.executeQuery();
            while (rs.next()) {
                AcabamentoBEAN aBEAN = new AcabamentoBEAN();
                aBEAN.setCodigo(CODIGO);
                aBEAN.setNomeMaquina(rs.getString("MAQUINA"));
                cadastroab.add(aBEAN);
            }
        } catch (SQLException ex) {
            throw new SQLException(ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return cadastroab;
    }

    public static void desativaMaquina(int CODIGO) throws SQLException {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("UPDATE ACABAMENTOS SET ATIVA = 0 WHERE CODIGO = ?");
            stmt.setInt(1, CODIGO);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            throw new SQLException(ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }

    public static void ativaMaquina(int CODIGO) throws SQLException {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("UPDATE ACABAMENTOS SET ATIVA = 1 WHERE CODIGO = ?");
            stmt.setInt(1, CODIGO);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            throw new SQLException(ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }

    public static float retornaValorAcabamento(int CODIGOigoAcabamento) throws SQLException {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        float retorno = 0;

        try {
            stmt = con.prepareStatement("SELECT CUSTO_HORA FROM ACABAMENTOS WHERE CODIGO = ?");
            stmt.setInt(1, CODIGOigoAcabamento);
            rs = stmt.executeQuery();
            if (rs.next()) {
                retorno = rs.getFloat("CUSTO_HORA");
            }
        } catch (SQLException ex) {
            throw new SQLException();
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return retorno;
    }

    public static String retornaDescricaoAcabamentos(int CODIGO) throws SQLException {
        String retorno = null;
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = con.prepareStatement("SELECT MAQUINA FROM ACABAMENTOS WHERE CODIGO = ?");
            stmt.setInt(1, CODIGO);
            rs = stmt.executeQuery();
            while (rs.next()) {
                retorno = rs.getString("MAQUINA");
            }
        } catch (SQLException ex) {
            retorno = null;
            throw new SQLException(ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return retorno;
    }

    /**
     * Associa os acabamentos do produto
     * @param cadastroProd acabamentos do produto
     * @throws SQLException 
     */
    public static void criaAcabamentosProduto(AcabamentoProdBEAN cadastroProd) throws SQLException {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("INSERT INTO tabela_componentes_produto(cod_produto, tipo_produto, cod_acabamento) "
                    + "VALUES(?,?,?)");
            stmt.setInt(1, cadastroProd.getCodigoProduto());
            stmt.setByte(2, cadastroProd.getTipoProduto());
            stmt.setInt(3, cadastroProd.getCodigoAcabamento());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            throw new SQLException(ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
    /**
     * Exclui os acabamentos do produto
     * @param codProd
     * @throws SQLException 
     */
    public static void excluiAcabamentosProduto(int codProd, byte tipoProd) throws SQLException{
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try{
            stmt = con.prepareStatement("DELETE "
                    + "FROM tabela_componentes_produto "
                    + "WHERE cod_produto = ? AND tipo_produto = ?");
            stmt.setInt(1, codProd);
            stmt.setByte(2, tipoProd);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            throw new SQLException(ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
    /**
     * Retorna os acabamentos atrelados ao produto
     * @param codProd código do produto
     * @return
     * @throws SQLException 
     */
    public static List<AcabamentoProdBEAN> retornaAcabamentosProduto(int codProd) throws SQLException{
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<AcabamentoProdBEAN> retorno = new ArrayList();
        
        try{
            stmt = con.prepareStatement("SELECT cod_acabamento "
                    + "FROM tabela_componentes_produto "
                    + "WHERE cod_produto = ?");
            stmt.setInt(1, codProd);
            rs = stmt.executeQuery();
            if(rs.wasNull()){
                throw new SemAcabamentoException();
            }
            while(rs.next()){
                AcabamentoProdBEAN lcBEAN = new AcabamentoProdBEAN();
                lcBEAN.setCodigoAcabamento(rs.getInt("cod_acabamento"));
                retorno.add(lcBEAN);
            }
            return retorno;
        } catch (SQLException ex) {
            throw new SQLException(ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
    }
    
    public static double retornaPrecoUnitarioProduto(int codigoProduto) throws SQLException{
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        double precoUnitario = 0;
        int codigoAcabamento = 0;
        
        try{
            stmt = con.prepareStatement("SELECT cod_acabamento FROM tabela_componentes_produto WHERE cod_produto = ?");
            stmt.setInt(1, codigoProduto);
            rs = stmt.executeQuery();
            while(rs.next()){
                codigoAcabamento = rs.getInt("cod_acabamento");
                precoUnitario += retornaValorAcabamento(codigoAcabamento);
            }
        } catch (SQLException ex) {
            throw new SQLException(ex);
        }
        return precoUnitario;
    }
    
    public static List<Integer> usandoProduto(int codigoAcabamento) throws SQLException{
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Integer> retorno = new ArrayList();
        
        try{
            stmt = con.prepareStatement("SELECT cod_produto FROM tabela_componentes_produto "
                    + "WHERE cod_acabamento = ?");
            stmt.setInt(1, codigoAcabamento);
            rs = stmt.executeQuery();
            while(rs.next()){
                retorno.add(rs.getInt("cod_produto"));
            }
            return retorno;
        } catch (SQLException ex) {
            throw new SQLException(ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
    }
}
