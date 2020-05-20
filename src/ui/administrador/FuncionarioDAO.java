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
public class FuncionarioDAO {

    public static void create(FuncionarioBEAN cadastroFuncionariosBEAN) throws SQLException {
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
                stmt.setString(1, cadastroFuncionariosBEAN.getCodigoAtendente());
                stmt.setString(2, cadastroFuncionariosBEAN.getLoginAtendente());
                stmt.setString(3, cadastroFuncionariosBEAN.getNomeAtendente());
                stmt.setString(4, cadastroFuncionariosBEAN.getSenhaAtendente());
                stmt.setString(5, cadastroFuncionariosBEAN.getTipoAtendente());
                stmt.setByte(6, cadastroFuncionariosBEAN.getAcessoProducao());
                stmt.setByte(7, cadastroFuncionariosBEAN.getAcessoOrcamentacao());
                stmt.setByte(8, cadastroFuncionariosBEAN.getAcessoExpedicao());
                stmt.setByte(9, cadastroFuncionariosBEAN.getAcessoFinanceiro());
                stmt.setByte(10, cadastroFuncionariosBEAN.getAcessoEstoque());
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
                stmt.setString(1, cadastroFuncionariosBEAN.getCodigoAtendente());
                stmt.setString(2, cadastroFuncionariosBEAN.getLoginAtendente());
                stmt.setString(3, cadastroFuncionariosBEAN.getNomeAtendente());
                stmt.setString(5, cadastroFuncionariosBEAN.getTipoAtendente());
                stmt.setByte(6, cadastroFuncionariosBEAN.getAcessoProducao());
                stmt.setByte(7, cadastroFuncionariosBEAN.getAcessoOrcamentacao());
                stmt.setByte(8, cadastroFuncionariosBEAN.getAcessoExpedicao());
                stmt.setByte(9, cadastroFuncionariosBEAN.getAcessoFinanceiro());
                stmt.setByte(10, cadastroFuncionariosBEAN.getAcessoEstoque());
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

    public static List<FuncionarioBEAN> retornaAcessos(String login_atendente) throws SQLException {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<FuncionarioBEAN> cadastrofc = new ArrayList();

        try {
            stmt = con.prepareStatement("SELECT acesso_orc, acesso_prod, acesso_exp, acesso_fin, acesso_estoque,"
                    + "acesso_ord FROM tabela_atendentes WHERE login_atendente = ?");
            stmt.setString(1, login_atendente);
            rs = stmt.executeQuery();
            while (rs.next()) {
                FuncionarioBEAN cadastroFuncionariosBEAN = new FuncionarioBEAN();
                cadastroFuncionariosBEAN.setAcessoOrcamentacao(rs.getByte("acesso_orc"));
                cadastroFuncionariosBEAN.setAcessoProducao(rs.getByte("acesso_prod"));
                cadastroFuncionariosBEAN.setAcessoExpedicao(rs.getByte("acesso_exp"));
                cadastroFuncionariosBEAN.setAcessoFinanceiro(rs.getByte("acesso_fin"));
                cadastroFuncionariosBEAN.setAcessoEstoque(rs.getByte("acesso_estoque"));
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

    public static List<FuncionarioBEAN> carregaLista() throws SQLException {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<FuncionarioBEAN> retorno = new ArrayList();

        try {
            stmt = con.prepareStatement("SELECT * FROM tabela_atendentes ORDER BY codigo_atendente ASC");
            rs = stmt.executeQuery();
            while (rs.next()) {
                retorno.add(new FuncionarioBEAN(rs.getString("nome_atendente"),
                        rs.getString("codigo_atendente"),
                        rs.getString("login_atendente"),
                        rs.getString("tipo_atendente"),
                        rs.getByte("acesso_orc"),
                        rs.getByte("acesso_prod"),
                        rs.getByte("acesso_exp"),
                        rs.getByte("acesso_fin"),
                        rs.getByte("acesso_estoque"),
                        rs.getInt("ativo")));
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

    public static List<FuncionarioBEAN> retornaPesquisa(String tipo, String tipoAux, String texto) throws SQLException {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<FuncionarioBEAN> cadastroFunc = new ArrayList();

        try {
            if (tipo.equals("NOME ATENDENTE")) {
                stmt = con.prepareStatement("SELECT * FROM tabela_atendentes WHERE nome_atendente = ? ORDER BY codigo_atendente ASC");
                stmt.setString(1, texto);
            }
            if (tipo.equals("CÓDIGO ATENDENTE")) {
                stmt = con.prepareStatement("SELECT * FROM tabela_atendentes WHERE codigo_atendente = ?");
                stmt.setString(1, texto);
            }
            if (tipo.equals("LOGIN ATENDENTE")) {
                stmt = con.prepareStatement("SELECT * FROM tabela_atendentes WHERE login_atendente = ?");
                stmt.setString(1, texto);
            }
            if (tipo.equals("TIPO ATENDENTE")) {
                if (tipoAux.equals("USUÁRIO")) {
                    stmt = con.prepareStatement("SELECT * FROM tabela_atendentes WHERE tipo_atendente = 'USUÁRIO' ORDER BY codigo_atendente ASC");
                }
                if (tipoAux.equals("ADMINISTRADOR")) {
                    stmt = con.prepareStatement("SELECT * FROM tabela_atendentes WHERE tipo_atendente = 'ADMINISTRADOR' ORDER BY codigo_atendente ASC");
                }
            }
            if (tipo.equals("ACESSO PRODUÇÃO")) {
                if (tipoAux.equals("SIM")) {
                    stmt = con.prepareStatement("SELECT * FROM tabela_atendentes WHERE acesso_prod = 1 ORDER BY codigo_atendente ASC");
                } else {
                    stmt = con.prepareStatement("SELECT * FROM tabela_atendentes WHERE acesso_prod = 0 ORDER BY codigo_atendente ASC");
                }
            }
            if (tipo.equals("ACESSO ORÇAMENTAÇÃO")) {
                if (tipoAux.equals("SIM")) {
                    stmt = con.prepareStatement("SELECT * FROM tabela_atendentes WHERE acesso_orc = 1 ORDER BY codigo_atendente ASC");
                } else {
                    stmt = con.prepareStatement("SELECT * FROM tabela_atendentes WHERE acesso_orc = 0 ORDER BY codigo_atendente ASC");
                }
            }
            rs = stmt.executeQuery();
            while (rs.next()) {
                FuncionarioBEAN cadastroFuncionariosBEAN = new FuncionarioBEAN();
                cadastroFuncionariosBEAN.setNomeAtendente(rs.getString("nome_atendente"));
                cadastroFuncionariosBEAN.setCodigoAtendente(rs.getString("codigo_atendente"));
                cadastroFuncionariosBEAN.setLogin_atendente(rs.getString("login_atendente"));
                cadastroFuncionariosBEAN.setTipoAtendente(rs.getString("tipo_atendente"));
                cadastroFuncionariosBEAN.setAcessoOrcamentacao(rs.getByte("acesso_orc"));
                cadastroFuncionariosBEAN.setAcessoProducao(rs.getByte("acesso_prod"));
                cadastroFuncionariosBEAN.setAcessoExpedicao(rs.getByte("acesso_exp"));
                cadastroFuncionariosBEAN.setAcessoFinanceiro(rs.getByte("acesso_fin"));
                cadastroFuncionariosBEAN.setAcessoEstoque(rs.getByte("acesso_estoque"));
                cadastroFunc.add(cadastroFuncionariosBEAN);
            }
        } catch (SQLException ex) {
            throw new SQLException(ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return cadastroFunc;
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
    public static List<FuncionarioBEAN> retornaAtendentes(byte acesso) throws SQLException {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<FuncionarioBEAN> retorno = new ArrayList();

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
                retorno.add(new FuncionarioBEAN(rs.getString("codigo_atendente"), 
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
}
