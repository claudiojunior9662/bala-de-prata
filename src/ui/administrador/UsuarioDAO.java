/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.administrador;

import connection.ConnectionFactory;
import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author spd3
 */
public class UsuarioDAO {

    public static void create(UsuarioBEAN cadastroFuncionariosBEAN) throws SQLException {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            if (cadastroFuncionariosBEAN.getSenhaAtendente() != "") {
                stmt = con.prepareStatement("INSERT INTO tabela_atendentes(codigo_atendente, "
                        + "login_atendente, "
                        + "nome_atendente, "
                        + "senha_atendente, "
                        + "tipo_atendente, "
                        + "acesso_prod, "
                        + "acesso_orc, "
                        + "acesso_exp, "
                        + "acesso_fin, "
                        + "acesso_estoque,"
                        + "mudanca_senha,"
                        + "ativo) "
                        + "VALUES(?,?,?,md5(?),?,?,?,?,?,?,?,1)");
                stmt.setString(1, cadastroFuncionariosBEAN.getCodigo());
                stmt.setString(2, cadastroFuncionariosBEAN.getLogin());
                stmt.setString(3, cadastroFuncionariosBEAN.getNome());
                stmt.setString(4, cadastroFuncionariosBEAN.getSenhaAtendente());
                stmt.setString(5, cadastroFuncionariosBEAN.getTipo());
                stmt.setByte(6, cadastroFuncionariosBEAN.getAcessoProd());
                stmt.setByte(7, cadastroFuncionariosBEAN.getAcessoOrc());
                stmt.setByte(8, cadastroFuncionariosBEAN.getAcessoExp());
                stmt.setByte(9, cadastroFuncionariosBEAN.getAcessoFin());
                stmt.setByte(10, cadastroFuncionariosBEAN.getAcessoEst());
                stmt.setDate(11, new java.sql.Date(new Date().getTime()));
            } else {
                stmt = con.prepareStatement("INSERT INTO tabela_atendentes(codigo_atendente, "
                        + "login_atendente, "
                        + "nome_atendente, "
                        + "senha_atendente, "
                        + "tipo_atendente, "
                        + "acesso_prod, "
                        + "acesso_orc, "
                        + "acesso_exp, "
                        + "acesso_fin, "
                        + "acesso_estoque,"
                        + "ativo) "
                        + "VALUES(?,?,?,?,?,?,?,?,?,?,1)");
                stmt.setString(1, cadastroFuncionariosBEAN.getCodigo());
                stmt.setString(2, cadastroFuncionariosBEAN.getLogin());
                stmt.setString(3, cadastroFuncionariosBEAN.getNome());
                stmt.setString(5, cadastroFuncionariosBEAN.getTipo());
                stmt.setByte(6, cadastroFuncionariosBEAN.getAcessoProd());
                stmt.setByte(7, cadastroFuncionariosBEAN.getAcessoOrc());
                stmt.setByte(8, cadastroFuncionariosBEAN.getAcessoExp());
                stmt.setByte(9, cadastroFuncionariosBEAN.getAcessoFin());
                stmt.setByte(10, cadastroFuncionariosBEAN.getAcessoEst());
                stmt.setDate(11, new java.sql.Date(new Date().getTime()));
            }
            stmt.executeUpdate();
        } catch (SQLException ex) {
            throw new SQLException(ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }

    public boolean verificaLogin(String login) throws SQLException {
        boolean retorno = false;
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        stmt = con.prepareStatement("SELECT * FROM tabela_atendentes WHERE login_atendente = ?");

        try {
            stmt.setString(1, login);
            rs = stmt.executeQuery();
            if (rs.next()) {
                retorno = true;
            }
        } catch (SQLException ex) {
            throw new SQLException();
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return retorno;
    }

    public static boolean verificaCodigo(String codigo) throws SQLException {
        boolean retorno = false;
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        stmt = con.prepareStatement("SELECT codigo_atendente FROM tabela_atendentes WHERE codigo_atendente = ?");

        try {
            stmt.setString(1, codigo);
            rs = stmt.executeQuery();
            if (rs.next()) {
                retorno = true;
            }
        } catch (SQLException ex) {
            throw new SQLException(ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return retorno;
    }

    public boolean verificaVersao(String versao) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        boolean retorno = false;
        try {
            stmt = con.prepareStatement("SELECT * FROM tabela_versoes WHERE n_versao = ?");
            stmt.setString(1, versao);
            rs = stmt.executeQuery();
            while (rs.next()) {
                retorno = true;
            }
            stmt.close();
        } catch (SQLException ex) {
            retorno = false;
            System.out.println("Erro na função verifica versao " + ex);
        }
        return retorno;
    }

    public static List<UsuarioBEAN> retornaAcessos(String login_atendente) throws SQLException {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<UsuarioBEAN> cadastrofc = new ArrayList();

        try {
            stmt = con.prepareStatement("SELECT acesso_orc, acesso_prod, acesso_exp, acesso_fin, acesso_estoque,"
                    + "acesso_ord FROM tabela_atendentes WHERE login_atendente = ?");
            stmt.setString(1, login_atendente);
            rs = stmt.executeQuery();
            while (rs.next()) {
                UsuarioBEAN cadastroFuncionariosBEAN = new UsuarioBEAN();
                cadastroFuncionariosBEAN.setAcessoOrc(rs.getByte("acesso_orc"));
                cadastroFuncionariosBEAN.setAcessoProd(rs.getByte("acesso_prod"));
                cadastroFuncionariosBEAN.setAcessoExp(rs.getByte("acesso_exp"));
                cadastroFuncionariosBEAN.setAcessoFin(rs.getByte("acesso_fin"));
                cadastroFuncionariosBEAN.setAcessoEst(rs.getByte("acesso_estoque"));
                cadastroFuncionariosBEAN.setAcessoOrd(rs.getByte("acesso_ord"));
                cadastrofc.add(cadastroFuncionariosBEAN);
            }
        } catch (SQLException ex) {
            throw new SQLException(ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return cadastrofc;
    }

    public static List<UsuarioBEAN> carregaLista() throws SQLException {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<UsuarioBEAN> retorno = new ArrayList();

        try {
            stmt = con.prepareStatement("SELECT * "
                    + "FROM tabela_atendentes "
                    + "INNER JOIN USUARIO_ACESSOS "
                    + "ON USUARIO_ACESSOS.CODIGO_USR = tabela_atendentes.codigo_atendente "
                    + "ORDER BY codigo_atendente ASC");
            rs = stmt.executeQuery();
            while (rs.next()) {
                retorno.add(new UsuarioBEAN(
                        rs.getString("nome_atendente"),
                        rs.getString("codigo_atendente"),
                        rs.getString("login_atendente"),
                        rs.getString("tipo_atendente"),
                        rs.getByte("USUARIO_ACESSOS.ORC"),
                        rs.getByte("USUARIO_ACESSOS.ORC_ADM"),
                        rs.getByte("USUARIO_ACESSOS.PROD"),
                        rs.getByte("USUARIO_ACESSOS.PROD_ADM"),
                        rs.getByte("USUARIO_ACESSOS.EXP"),
                        rs.getByte("USUARIO_ACESSOS.EXP_ADM"),
                        rs.getByte("USUARIO_ACESSOS.FIN"),
                        rs.getByte("USUARIO_ACESSOS.FIN_ADM"),
                        rs.getByte("USUARIO_ACESSOS.EST"),
                        rs.getByte("USUARIO_ACESSOS.ORD"),
                        rs.getByte("ativo")
                ));
            }
        } catch (SQLException ex) {
            throw new SQLException(ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return retorno;
    }

    public static void excluiFuncionario(String codigoAtendente) throws SQLException {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = con.prepareStatement("DELETE FROM tabela_atendentes WHERE codigo_atendente = ?");
            stmt.setString(1, codigoAtendente);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            throw new SQLException(ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
    }

    public static List<UsuarioBEAN> retornaPesquisa(String tipo, 
            String tipoAux, 
            String texto) throws SQLException {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<UsuarioBEAN> retorno = new ArrayList();

        try {
            if (tipo.equals("NOME ATENDENTE")) {
                stmt = con.prepareStatement("SELECT * "
                        + "FROM tabela_atendentes "
                        + "INNER JOIN USUARIO_ACESSOS ON USUARIO_ACESSOS.CODIGO_USR = tabela_atendentes.codigo_atendente "
                        + "WHERE nome_atendente = ? "
                        + "ORDER BY codigo_atendente "
                        + "ASC");
                stmt.setString(1, texto);
            }
            if (tipo.equals("CÓDIGO ATENDENTE")) {
                stmt = con.prepareStatement("SELECT * "
                        + "FROM tabela_atendentes "
                        + "INNER JOIN USUARIO_ACESSOS ON USUARIO_ACESSOS.CODIGO_USR = tabela_atendentes.codigo_atendente "
                        + "WHERE codigo_atendente = ?");
                stmt.setString(1, texto);
            }
            if (tipo.equals("LOGIN ATENDENTE")) {
                stmt = con.prepareStatement("SELECT * "
                        + "FROM tabela_atendentes "
                        + "INNER JOIN USUARIO_ACESSOS ON USUARIO_ACESSOS.CODIGO_USR = tabela_atendentes.codigo_atendente "
                        + "WHERE login_atendente = ?");
                stmt.setString(1, texto);
            }
            if (tipo.equals("TIPO ATENDENTE")) {
                if (tipoAux.equals("USUÁRIO")) {
                    stmt = con.prepareStatement("SELECT * "
                            + "FROM tabela_atendentes "
                            + "INNER JOIN USUARIO_ACESSOS ON USUARIO_ACESSOS.CODIGO_USR = tabela_atendentes.codigo_atendente "
                            + "WHERE tipo_atendente = 'USUÁRIO' "
                            + "ORDER BY codigo_atendente "
                            + "ASC");
                }
                if (tipoAux.equals("ADMINISTRADOR")) {
                    stmt = con.prepareStatement("SELECT * "
                            + "FROM tabela_atendentes "
                            + "INNER JOIN USUARIO_ACESSOS ON USUARIO_ACESSOS.CODIGO_USR = tabela_atendentes.codigo_atendente "
                            + "WHERE tipo_atendente = 'ADMINISTRADOR' "
                            + "ORDER BY codigo_atendente "
                            + "ASC");
                }
            }
            if (tipo.equals("ACESSO PRODUÇÃO")) {
                if (tipoAux.equals("SIM")) {
                    stmt = con.prepareStatement("SELECT * "
                            + "FROM tabela_atendentes "
                            + "INNER JOIN USUARIO_ACESSOS ON USUARIO_ACESSOS.CODIGO_USR = tabela_atendentes.codigo_atendente "
                            + "WHERE acesso_prod = 1 "
                            + "ORDER BY codigo_atendente "
                            + "ASC");
                } else {
                    stmt = con.prepareStatement("SELECT * "
                            + "FROM tabela_atendentes "
                            + "INNER JOIN USUARIO_ACESSOS ON USUARIO_ACESSOS.CODIGO_USR = tabela_atendentes.codigo_atendente "
                            + "WHERE acesso_prod = 0 "
                            + "ORDER BY codigo_atendente "
                            + "ASC");
                }
            }
            if (tipo.equals("ACESSO ORÇAMENTAÇÃO")) {
                if (tipoAux.equals("SIM")) {
                    stmt = con.prepareStatement("SELECT * "
                            + "FROM tabela_atendentes "
                            + "INNER JOIN USUARIO_ACESSOS ON USUARIO_ACESSOS.CODIGO_USR = tabela_atendentes.codigo_atendente "
                            + "WHERE acesso_orc = 1 "
                            + "ORDER BY codigo_atendente "
                            + "ASC");
                } else {
                    stmt = con.prepareStatement("SELECT * "
                            + "FROM tabela_atendentes "
                            + "INNER JOIN USUARIO_ACESSOS ON USUARIO_ACESSOS.CODIGO_USR = tabela_atendentes.codigo_atendente "
                            + "WHERE acesso_orc = 0 "
                            + "ORDER BY codigo_atendente "
                            + "ASC");
                }
            }
            rs = stmt.executeQuery();
            while (rs.next()) {
                retorno.add(new UsuarioBEAN(
                        rs.getString("nome_atendente"),
                        rs.getString("codigo_atendente"),
                        rs.getString("login_atendente"),
                        rs.getString("tipo_atendente"),
                        rs.getByte("USUARIO_ACESSOS.ORC"),
                        rs.getByte("USUARIO_ACESSOS.ORC_ADM"),
                        rs.getByte("USUARIO_ACESSOS.PROD"),
                        rs.getByte("USUARIO_ACESSOS.PROD_ADM"),
                        rs.getByte("USUARIO_ACESSOS.EXP"),
                        rs.getByte("USUARIO_ACESSOS.EXP_ADM"),
                        rs.getByte("USUARIO_ACESSOS.FIN"),
                        rs.getByte("USUARIO_ACESSOS.FIN_ADM"),
                        rs.getByte("USUARIO_ACESSOS.EST"),
                        rs.getByte("USUARIO_ACESSOS.ORD"),
                        rs.getByte("ativo")
                ));
            }
            return retorno;
        } catch (SQLException ex) {
            throw new SQLException(ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
    }

    public String retornaSenha(String codAtendente) throws SQLException {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String retorno = null;

        try {
            stmt = con.prepareStatement("SELECT senha_atendente FROM tabela_atendentes WHERE codigo_atendente = ?");
            stmt.setString(1, codAtendente);
            rs = stmt.executeQuery();
            while (rs.next()) {
                retorno = rs.getString("senha_atendente");
            }
            stmt.close();
        } catch (SQLException ex) {
            throw new SQLException(ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return retorno;
    }

    public static void desativaAtivaFuncionario(Integer estado, String codigoAtendente) throws SQLException {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("UPDATE tabela_atendentes SET ativo = ? WHERE codigo_atendente = ?");
            stmt.setInt(1, estado);
            stmt.setString(2, codigoAtendente);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            throw new SQLException();
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }

    }
    
    public static void resetaSenha(String loginAtendente) throws SQLException{
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try{
            stmt = con.prepareStatement("UPDATE tabela_atendentes SET senha_atendente = md5(?), mudanca_senha = '2018-01-01' "
                    + "WHERE login_atendente = ?");
            stmt.setString(1, loginAtendente);
            stmt.setString(2, loginAtendente);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            throw new SQLException(ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
    /** Retorna atendentes
     * @param acesso
    0 - TODOS
    1 - PRODUCAO
    2 - ORCAMENTO
    3 - EXPEDIÇÃO
    4 - FINANCEIRO
    5 - ESTOQUE
    */
    public static List<UsuarioBEAN> retornaAtendentes(byte acesso) throws SQLException {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<UsuarioBEAN> retorno = new ArrayList();

        try {
            switch(acesso){
                case 0:
                    stmt = con.prepareStatement("SELECT codigo_atendente, nome_atendente "
                            + "FROM tabela_atendentes");
                    break;
                case 1:
                    stmt = con.prepareStatement("SELECT codigo_atendente, nome_atendente "
                            + "FROM tabela_atendentes "
                            + "WHERE acesso_prod = 1");
                    break;
                case 2:
                    stmt = con.prepareStatement("SELECT codigo_atendente, nome_atendente "
                            + "FROM tabela_atendentes "
                            + "WHERE acesso_orc = 1");
                    break;
                case 3:
                    stmt = con.prepareStatement("SELECT codigo_atendente, nome_atendente "
                            + "FROM tabela_atendentes "
                            + "WHERE acesso_exp = 1");
                    break;
                case 4:
                    stmt = con.prepareStatement("SELECT codigo_atendente, nome_atendente "
                            + "FROM tabela_atendentes "
                            + "WHERE acesso_fin = 1");
                    break;
                case 5:
                    stmt = con.prepareStatement("SELECT codigo_atendente, nome_atendente "
                            + "FROM tabela_atendentes "
                            + "WHERE acesso_estoque = 1");
                    break;
            }
            rs = stmt.executeQuery();
            while (rs.next()) {
                retorno.add(new UsuarioBEAN(rs.getString("codigo_atendente"), 
                        rs.getString("nome_atendente")));
            }
        } catch (SQLException ex) {
            throw new SQLException(ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return retorno;
    }
    
    /**
     * Retorna o nome do atendente
     * @param codAtendente código do atendente
     * @return
     * @throws SQLException 
     */
    public static String retornaNomeAtendente(String codAtendente) throws SQLException{
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = con.prepareStatement("SELECT nome_atendente "
                    + "FROM tabela_atendentes "
                    + "WHERE codigo_atendente = ?");
            stmt.setString(1, codAtendente);
            rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getString("nome_atendente");
            }
            return null;
        } catch (SQLException ex) {
            throw new SQLException(ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
    }
    
    /**
     * Retorna as informações básicas do usuário
     * @param login login do usuário
     * @param senha senha do usuário
     * @return
     * @throws SQLException 
     */
    public static UsuarioBEAN retornaInfoUsr(String login, String senha) throws SQLException {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = con.prepareStatement("SELECT * "
                    + "FROM tabela_atendentes "
                    + "INNER JOIN USUARIO_ACESSOS "
                    + "ON USUARIO_ACESSOS.CODIGO_USR = tabela_atendentes.codigo_atendente "
                    + "WHERE login_atendente = ? AND senha_atendente = md5(?)");
            stmt.setString(1, login);
            stmt.setString(2, senha);
            rs = stmt.executeQuery();
            if (rs.next()) {
                return new UsuarioBEAN(
                        rs.getString("nome_atendente"),
                        rs.getString("codigo_atendente"),
                        rs.getString("login_atendente"),
                        rs.getString("tipo_atendente"),
                        rs.getByte("USUARIO_ACESSOS.ORC"),
                        rs.getByte("USUARIO_ACESSOS.ORC_ADM"),
                        rs.getByte("USUARIO_ACESSOS.PROD"),
                        rs.getByte("USUARIO_ACESSOS.PROD_ADM"),
                        rs.getByte("USUARIO_ACESSOS.EXP"),
                        rs.getByte("USUARIO_ACESSOS.EXP_ADM"),
                        rs.getByte("USUARIO_ACESSOS.FIN"),
                        rs.getByte("USUARIO_ACESSOS.FIN_ADM"),
                        rs.getByte("USUARIO_ACESSOS.EST"),
                        rs.getByte("USUARIO_ACESSOS.ORD"),
                        rs.getByte("ativo")
                );
            }
            return null;
        } catch (SQLException ex) {
            throw new SQLException(ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
    }
}
