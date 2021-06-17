/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import connection.ConnectionFactory;
import entities.sisgrafex.Endereco;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author claudio
 */
public class EnderecoDAO {

    /**
     * Retorna o endereço formatado
     *
     * @param codEndereco código do endereço
     * @return
     */
    public synchronized static String retornaEndereco(int codEndereco) throws SQLException {
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

    /**
     * Verifica se existe o cep informado no BD
     *
     * @param cep cep
     * @return
     * @throws SQLException
     */
    public synchronized static Boolean verificaEndereco(String cep) throws SQLException {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = con.prepareStatement("SELECT tabela_enderecos.cod "
                    + "FROM tabela_enderecos "
                    + "WHERE tabela_enderecos.cep = ?");
            stmt.setString(1, cep);
            rs = stmt.executeQuery();
            if (rs.next()) {
                return true;
            }
            return false;
        } catch (SQLException ex) {
            throw new SQLException(ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
    }

    /**
     * Retorna o código do endereço a partir do cep informado
     * @param cep 
     * @return
     * @throws SQLException 
     */
    public synchronized static int retornaCodPorCep(String cep) throws SQLException {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = con.prepareStatement("SELECT tabela_enderecos.cod "
                    + "FROM tabela_enderecos "
                    + "WHERE tabela_enderecos.cep = ?");
            stmt.setString(1, cep);
            rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("tabela_enderecos.cod");
            }
            return 0;
        } catch (SQLException ex) {
            throw new SQLException(ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
    }

    /**
     * Grava o endereço no BD
     *
     * @param endereco
     * @throws SQLException
     */
    public static void gravarEnderecos(Endereco endereco) throws SQLException {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("INSERT INTO tabela_enderecos(cod, cep, tipo_endereco, logadouro, cidade, bairro, uf, complemento) "
                    + "VALUES(?,?,?,?,?,?,?,?)");
            stmt.setInt(1, endereco.getCodigo());
            stmt.setString(2, endereco.getCep());
            stmt.setString(3, endereco.getTipoEndereco());
            stmt.setString(4, endereco.getLogadouro());
            stmt.setString(5, endereco.getCidade());
            stmt.setString(6, endereco.getBairro());
            stmt.setString(7, endereco.getUf());
            stmt.setString(8, endereco.getComplemento());
            stmt.executeUpdate();
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }

    /**
     * Usada para o retorna de Endereço a partir do código
     *
     * @param codContato código do contato
     * @return Endereco(cep, tipo_endereco, logadouro, bairro, uf, complemento,
     * cidade) informações do contato do cliente
     * @see selInfoContatosNota#selInfoContato
     */
    public static synchronized Endereco selInfoEndereco(int codEndereco) throws SQLException {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = con.prepareStatement("SELECT logadouro, tipo_endereco, bairro, cidade, uf, complemento, cep " + "FROM tabela_enderecos " + "WHERE cod = ?");
            stmt.setInt(1, codEndereco);
            rs = stmt.executeQuery();
            while (rs.next()) {
                return new Endereco(rs.getString("cep"), rs.getString("tipo_endereco"), rs.getString("logadouro"), rs.getString("bairro"), rs.getString("uf"), rs.getString("complemento"), rs.getString("cidade"));
            }
            return null;
        } catch (SQLException ex) {
            throw new SQLException();
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
    }

    /**
     * Retorna o último registro de endereço no BD, para fins de inserção
     *
     * @return
     * @throws SQLException
     */
    public static int retornaUltimoRegistroEnderecos() throws SQLException {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int retorno = 0;
        try {
            stmt = con.prepareStatement("SELECT cod FROM tabela_enderecos ORDER BY cod DESC LIMIT 1");
            rs = stmt.executeQuery();
            while (rs.next()) {
                retorno = rs.getInt("cod");
            }
            stmt.close();
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return retorno;
    }

    /**
     * AtualizaCadastro de endereço no BD
     *
     * @param endereco
     * @throws SQLException
     */
    public static void atualizaEnderecos(Endereco endereco) throws SQLException {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("UPDATE tabela_enderecos SET cep = ?, tipo_endereco = ?, logadouro = ?," + "bairro = ?, uf = ?, cidade = ?, complemento = ? WHERE cod = ?");
            stmt.setString(1, endereco.getCep());
            stmt.setString(2, endereco.getTipoEndereco());
            stmt.setString(3, endereco.getLogadouro());
            stmt.setString(4, endereco.getBairro());
            stmt.setString(5, endereco.getUf());
            stmt.setString(6, endereco.getCidade());
            stmt.setString(7, endereco.getComplemento());
            stmt.setInt(8, endereco.getCodigo());
            stmt.executeUpdate();
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }

    /**
     * Retorna registros completos do BD a partir de uma lista de códigos
     *
     * @param codigos
     * @return
     * @throws SQLException
     */
    public static List<Endereco> retornaEnderecos(List codigos) throws SQLException {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Endereco> retorno = new ArrayList();
        try {
            for (int i = 0; i < codigos.size(); i++) {
                stmt = con.prepareStatement("SELECT * "
                        + "FROM tabela_enderecos "
                        + "WHERE tabela_enderecos.cod = ?");
                stmt.setInt(1, Integer.valueOf(codigos.get(i).toString()));
                rs = stmt.executeQuery();
                while (rs.next()) {
                    Endereco endereco = new Endereco();
                    endereco.setCodigo(rs.getInt("tabela_enderecos.cod"));
                    endereco.setCep(rs.getString("tabela_enderecos.cep"));
                    endereco.setLogadouro(rs.getString("tabela_enderecos.logadouro"));
                    endereco.setBairro(rs.getString("tabela_enderecos.bairro"));
                    endereco.setUf(rs.getString("tabela_enderecos.uf"));
                    endereco.setCidade(rs.getString("tabela_enderecos.cidade"));
                    endereco.setComplemento(rs.getString("tabela_enderecos.complemento"));
                    endereco.setTipoEndereco(rs.getString("tabela_enderecos.tipo_endereco"));
                    retorno.add(endereco);
                }
            }
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return retorno;
    }

    /**
     * Retorna os códigos de endereço associados ao cliente
     *
     * @param codigoCliente
     * @param tipoPessoa
     * @return
     * @throws SQLException
     */
    public static List<Integer> retornaEnderecosAssociados(int codigoCliente, byte tipoPessoa) throws SQLException {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Integer> retorno = new ArrayList();
        try {
            stmt = con.prepareStatement("SELECT cod_endereco "
                    + "FROM tabela_associacao_enderecos "
                    + "WHERE cod_cliente = ? "
                    + "AND "
                    + "tipo_cliente = ?");
            stmt.setInt(1, codigoCliente);
            stmt.setInt(2, tipoPessoa);
            while ((rs = stmt.executeQuery()).next()) {
                retorno.add(rs.getInt("cod_endereco"));
            }
            stmt.executeUpdate();
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return retorno;
    }

    public static void desativaAtivaEnderecos(int codigoCliente, int codigoEndereco, byte tipoPessoa, byte funcao, boolean associacao) throws SQLException {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            if (!associacao) {
                stmt = con.prepareStatement("UPDATE tabela_enderecos SET excluido = ? WHERE cod = ?");
                stmt.setInt(1, funcao);
                stmt.setInt(2, codigoEndereco);
                stmt.executeUpdate();
            }
            if (associacao) {
                for (int codigoEnderecoAux : retornaEnderecosAssociados(codigoCliente, tipoPessoa)) {
                    stmt = con.prepareStatement("UPDATE tabela_enderecos SET excluido = ? WHERE cod = ?");
                    stmt.setInt(1, funcao);
                    stmt.setInt(2, codigoEnderecoAux);
                    stmt.executeUpdate();
                }
            }
        } catch (SQLException ex) {
            throw new SQLException(ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }

    public static Boolean buscaCep(String cep) throws SQLException {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Boolean retorno = false;
        try {
            stmt = con.prepareStatement("SELECT cep FROM tabela_enderecos WHERE cep = ?");
            stmt.setString(1, cep);
            if ((rs = stmt.executeQuery()).next()) {
                retorno = true;
            }
        } catch (SQLException ex) {
            throw new SQLException(ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return retorno;
    }

    //ENDEREÇOS-----------------------------------------------------------------
    public static List selecionaEnderecos(byte tipoPessoa, Integer codigoCliente) throws SQLException {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List retorno = new ArrayList();
        try {
            if (tipoPessoa == 1) {
                stmt = con.prepareStatement("SELECT cod_endereco FROM tabela_associacao_enderecos WHERE cod_cliente = ? AND tipo_cliente = 1");
                stmt.setInt(1, codigoCliente);
            }
            if (tipoPessoa == 2) {
                stmt = con.prepareStatement("SELECT cod_endereco FROM tabela_associacao_enderecos WHERE cod_cliente = ? AND tipo_cliente = 2");
                stmt.setInt(1, codigoCliente);
            }
            rs = stmt.executeQuery();
            while (rs.next()) {
                retorno.add(rs.getInt("cod_endereco"));
            }
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return retorno;
    }

    public static List<Endereco> retornaPesquisaEnderecos(String p1, String p2, boolean todos) throws SQLException {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Endereco> retorno = new ArrayList();
        try {
            if (p1.equals("C\u00d3DIGO")) {
                stmt = con.prepareStatement("SELECT * FROM tabela_enderecos" + " WHERE cod = ? AND excluido != 1");
                stmt.setInt(1, Integer.valueOf(p2));
            }
            if (p1.equals("CEP (SOMENTE N\u00daMEROS)")) {
                stmt = con.prepareStatement("SELECT * FROM tabela_enderecos" + " WHERE cep = ? AND excluido != 1");
                stmt.setString(1, p2);
            }
            if (p1.equals("TIPO ENDERE\u00c7O (RESIDENCIAL)")) {
                stmt = con.prepareStatement("SELECT * FROM tabela_enderecos" + " WHERE tipo_endereco = 'RESIDENCIAL' AND excluido != 1");
            }
            if (p1.equals("TIPO ENDERE\u00c7O (COMERCIAL)")) {
                stmt = con.prepareStatement("SELECT * FROM tabela_enderecos" + " WHERE tipo_endereco = 'COMERCIAL' AND excluido != 1");
            }
            if (p1.equals("LOGADOURO")) {
                stmt = con.prepareStatement("SELECT * FROM tabela_enderecos" + " WHERE logadouro" + " LIKE " + "'%" + p2 + "%' AND excluido != 1");
            }
            if (p1.equals("CIDADE")) {
                stmt = con.prepareStatement("SELECT * FROM tabela_enderecos" + " WHERE cidade" + " LIKE " + "'%" + p2 + "%' AND excluido != 1");
            }
            if (p1.equals("BAIRRO")) {
                stmt = con.prepareStatement("SELECT * FROM tabela_enderecos" + " WHERE bairro" + " LIKE " + "'%" + p2 + "%' AND excluido != 1");
            }
            if (p1.equals("UF")) {
                stmt = con.prepareStatement("SELECT * FROM tabela_enderecos" + " WHERE uf" + " LIKE " + "'%" + p2 + "%' AND excluido != 1");
            }
            if (todos == true) {
                stmt = con.prepareStatement("SELECT * FROM tabela_enderecos WHERE excluido != 1");
            }
            rs = stmt.executeQuery();
            while (rs.next()) {
                Endereco cadastroClientes2EnderecosBEAN = new Endereco();
                cadastroClientes2EnderecosBEAN.setCodigo(rs.getInt("cod"));
                cadastroClientes2EnderecosBEAN.setCep(rs.getString("cep"));
                cadastroClientes2EnderecosBEAN.setTipoEndereco(rs.getString("tipo_endereco"));
                cadastroClientes2EnderecosBEAN.setLogadouro(rs.getString("logadouro"));
                cadastroClientes2EnderecosBEAN.setCidade(rs.getString("cidade"));
                cadastroClientes2EnderecosBEAN.setBairro(rs.getString("bairro"));
                cadastroClientes2EnderecosBEAN.setUf(rs.getString("uf"));
                cadastroClientes2EnderecosBEAN.setComplemento(rs.getString("complemento"));
                retorno.add(cadastroClientes2EnderecosBEAN);
            }
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return retorno;
    }

    public void excluirEnderecosCliente(int codigoCliente, byte tipoCliente) throws SQLException {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        List<Integer> listaCodigosEnderecos = retornaEnderecosAssociados(codigoCliente, tipoCliente);
        try {
            for (Integer codigoEndereco : listaCodigosEnderecos) {
                stmt = con.prepareStatement("DELETE FROM tabela_enderecos WHERE cod = ? AND excluido = 1");
                stmt.setInt(1, codigoEndereco);
                if (stmt.executeUpdate() == 1) {
                    stmt = con.prepareStatement("DELETE FROM tabela_associacao_enderecos WHERE cod_endereco = ?");
                    stmt.setInt(1, codigoEndereco);
                    stmt.executeUpdate();
                }
            }
        } catch (SQLException ex) {
            throw new SQLException(ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }

    public Boolean verificaAtrelamentoEnderecos(Integer codigoEndereco) throws SQLException {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Boolean retorno = false;
        try {
            //VERIFICA ORCAMENTOS
            stmt = con.prepareStatement("SELECT cod_endereco FROM tabela_orcamentos WHERE cod_endereco = ?");
            stmt.setInt(1, codigoEndereco);
            if ((rs = stmt.executeQuery()).next()) {
                retorno = true;
            }
            //VERIFICA ORDENS DE PRODUCAO
            stmt = con.prepareStatement("SELECT cod_endereco FROM tabela_ordens_producao WHERE cod_endereco = ?");
            stmt.setInt(1, codigoEndereco);
            if ((rs = stmt.executeQuery()).next()) {
                retorno = true;
            }
            //VERIFICA NOTAS
            stmt = con.prepareStatement("SELECT cod_endereco FROM tabela_notas WHERE cod_endereco = ?");
            stmt.setInt(1, codigoEndereco);
            if ((rs = stmt.executeQuery()).next()) {
                retorno = true;
            }
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return retorno;
    }

}
