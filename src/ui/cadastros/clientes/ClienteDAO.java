/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.cadastros.clientes;

import ui.cadastros.enderecos.EnderecoBEAN;
import ui.cadastros.contatos.ContatoBEAN;
import connection.ConnectionFactory;
import entities.sisgrafex.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import entities.sisgrafex.Orcamento;
import exception.ClienteNaoEncontradoException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author claudio
 */
public class ClienteDAO {

    //INFORMAÇÕES CLIENTE-------------------------------------------------------
    public static List<ClienteBEAN> retornaPesquisa(String p1, String p2, String p3) throws SQLException {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<ClienteBEAN> retorno = new ArrayList();

        try {
            if (p1.equals("PESSOA FÍSICA")) {
                if (p2.equals("CÓDIGO")) {
                    stmt = con.prepareStatement("SELECT * "
                            + "FROM tabela_clientes_fisicos "
                            + "WHERE cod = ? AND excluido != 1 "
                            + "ORDER BY cod DESC LIMIT 45");
                    stmt.setInt(1, Integer.valueOf(p3));
                }
                if (p2.equals("NOME")) {
                    stmt = con.prepareStatement("SELECT * "
                            + "FROM tabela_clientes_fisicos "
                            + "WHERE nome "
                            + "LIKE " + "'%" + p3 + "%' "
                                    + "AND excluido != 1 "
                                    + "ORDER BY cod "
                                    + "DESC "
                                    + "LIMIT 45");
                }
                if (p2.equals("CPF")) {
                    stmt = con.prepareStatement("SELECT * "
                            + "FROM tabela_clientes_fisicos "
                            + "WHERE cpf "
                            + "LIKE " + "'%" + p3 + "%' "
                                    + "AND excluido != 1 "
                                    + "ORDER BY cod "
                                    + "DESC "
                                    + "LIMIT 45");
                }
                if (p2.equals("ATIVIDADE")) {
                    stmt = con.prepareStatement("SELECT * "
                            + "FROM tabela_clientes_fisicos "
                            + "WHERE atividade "
                            + "LIKE " + "'%" + p3 + "%' "
                                    + "AND excluido != 1 "
                                    + "ORDER BY cod "
                                    + "DESC "
                                    + "LIMIT 45");
                }
            }
            if (p1.equals("PESSOA JURÍDICA")) {
                if (p2.equals("CÓDIGO")) {
                    stmt = con.prepareStatement("SELECT * "
                            + "FROM tabela_clientes_juridicos "
                            + "WHERE cod = ? "
                            + "AND excluido != 1 "
                            + "ORDER BY cod "
                            + "DESC "
                            + "LIMIT 45");
                    stmt.setInt(1, Integer.valueOf(p3));
                }
                if (p2.equals("NOME")) {
                    stmt = con.prepareStatement("SELECT * "
                            + "FROM tabela_clientes_juridicos "
                            + "WHERE nome "
                            + "LIKE " + "'%" + p3 + "%' AND excluido != 1 ORDER BY cod DESC LIMIT 45");
                }
                if (p2.equals("NOME FANTASIA")) {
                    stmt = con.prepareStatement("SELECT * FROM tabela_clientes_juridicos WHERE nome_fantasia LIKE " + "'%" + p3 + "%' AND excluido != 1 ORDER BY cod DESC LIMIT 45");
                }
                if (p2.equals("CNPJ")) {
                    stmt = con.prepareStatement("SELECT * FROM tabela_clientes_juridicos WHERE cnpj LIKE " + "'%" + p3 + "%' AND excluido != 1 ORDER BY cod DESC LIMIT 45");
                }
                if (p2.equals("ATIVIDADE")) {
                    stmt = con.prepareStatement("SELECT * FROM tabela_clientes_juridicos WHERE atividade LIKE " + "'%" + p3 + "%' AND excluido != 1 ORDER BY cod DESC LIMIT 45");
                }
            }
            rs = stmt.executeQuery();
            while (rs.next()) {
                ClienteBEAN cadastroClientes2BEAN = new ClienteBEAN();
                if (p1.equals("PESSOA FÍSICA")) {
                    cadastroClientes2BEAN.setCod(rs.getInt("cod"));
                    cadastroClientes2BEAN.setNome(rs.getString("nome"));
                    cadastroClientes2BEAN.setCpf(rs.getString("cpf"));
                    cadastroClientes2BEAN.setAtividade(rs.getString("atividade"));
                    cadastroClientes2BEAN.setCodAtendente(rs.getString("cod_atendente"));
                    cadastroClientes2BEAN.setCredito(rs.getFloat("credito"));
                    //----------------------------------------------------------
                    cadastroClientes2BEAN.setCnpj("");
                    cadastroClientes2BEAN.setFilialColigada("");
                    cadastroClientes2BEAN.setNomeFantasia("");
                }
                if (p1.equals("PESSOA JURÍDICA")) {
                    cadastroClientes2BEAN.setCod(rs.getInt("cod"));
                    cadastroClientes2BEAN.setNome(rs.getString("nome"));
                    cadastroClientes2BEAN.setNomeFantasia(rs.getString("nome_fantasia"));
                    cadastroClientes2BEAN.setCnpj(rs.getString("cnpj"));
                    cadastroClientes2BEAN.setAtividade(rs.getString("atividade"));
                    cadastroClientes2BEAN.setFilialColigada(rs.getString("filial_coligada"));
                    cadastroClientes2BEAN.setCodAtendente(rs.getString("cod_atendente"));
                    cadastroClientes2BEAN.setCredito(rs.getFloat("credito"));
                    //----------------------------------------------------------
                    cadastroClientes2BEAN.setCpf("");
                }
                retorno.add(cadastroClientes2BEAN);
            }
        } catch (SQLException ex) {
            retorno = null;
            throw new SQLException(ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return retorno;
    }

    public static List<ClienteBEAN> mostraUltimos(String p1) throws SQLException {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<ClienteBEAN> retorno = new ArrayList();

        try {
            if (p1.equals("PESSOA FÍSICA")) {
                stmt = con.prepareStatement("SELECT * FROM tabela_clientes_fisicos WHERE excluido != 1 ORDER BY cod DESC LIMIT 45");
            } else {
                stmt = con.prepareStatement("SELECT * FROM tabela_clientes_juridicos WHERE excluido != 1 ORDER BY cod DESC LIMIT 45");
            }
            rs = stmt.executeQuery();
            while (rs.next()) {
                ClienteBEAN aux = new ClienteBEAN();
                if (p1.equals("PESSOA FÍSICA")) {
                    aux.setCod(rs.getInt("cod"));
                    aux.setNome(rs.getString("nome"));
                    aux.setCpf(rs.getString("cpf"));
                    aux.setAtividade(rs.getString("atividade"));
                    aux.setCodAtendente(rs.getString("cod_atendente"));
                    aux.setCredito(rs.getFloat("credito"));
                    //----------------------------------------------------------
                    aux.setCnpj(null);
                    aux.setFilialColigada(null);
                    aux.setNomeFantasia(null);
                }
                if (p1.equals("PESSOA JURÍDICA")) {
                    aux.setCod(rs.getInt("cod"));
                    aux.setNome(rs.getString("nome"));
                    aux.setNomeFantasia(rs.getString("nome_fantasia"));
                    aux.setCnpj(rs.getString("cnpj"));
                    aux.setAtividade(rs.getString("atividade"));
                    aux.setFilialColigada(rs.getString("filial_coligada"));
                    aux.setCodAtendente(rs.getString("cod_atendente"));
                    aux.setCredito(rs.getFloat("credito"));
                    //----------------------------------------------------------
                    aux.setCpf(null);
                }
                retorno.add(aux);
            }
             return retorno;
        } catch (SQLException ex) {
            throw new SQLException(ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
       
    }

    public static ClienteBEAN selecionaInformacoes(byte tipoPessoa, Integer codigoCliente) throws SQLException {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            //INFORMAÇÕES CLIENTE-----------------------------------------------
            if (tipoPessoa == 1) {
                stmt = con.prepareStatement("SELECT * "
                        + "FROM tabela_clientes_fisicos "
                        + "WHERE cod = ? AND excluido != 1");
                stmt.setInt(1, codigoCliente);
            }
            if (tipoPessoa == 2) {
                stmt = con.prepareStatement("SELECT * FROM tabela_clientes_juridicos WHERE cod = ? AND excluido != 1");
                stmt.setInt(1, codigoCliente);
            }
            rs = stmt.executeQuery();
            if (rs.next()) {
                ClienteBEAN aux = new ClienteBEAN();
                if (tipoPessoa == 1) {
                    return new ClienteBEAN(
                            rs.getString("nome"),
                            rs.getString("cpf"),
                            "",
                            rs.getString("atividade"),
                            "",
                            "",
                            rs.getString("cod_atendente"),
                            rs.getString("nome_atendente"),
                            rs.getString("observacoes"),
                            rs.getFloat("credito"),
                            (byte) 1
                    );

                } else if (tipoPessoa == 2) {
                    return new ClienteBEAN(
                            rs.getString("nome"),
                            "",
                            rs.getString("cnpj"),
                            rs.getString("atividade"),
                            rs.getString("nome_fantasia"),
                            rs.getString("filial_coligada"),
                            rs.getString("cod_atendente"),
                            rs.getString("nome_atendente"),
                            rs.getString("observacao"),
                            rs.getFloat("credito"),
                            (byte) 2
                    );
                }
            }
            return null;
        } catch (SQLException ex) {
            throw new SQLException(ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
    }

    public static void desativaAtivaCliente(int codigoCliente, byte tipoPessoa, byte funcao) throws SQLException {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            if (tipoPessoa == 1) {
                stmt = con.prepareStatement("UPDATE tabela_clientes_fisicos SET excluido = ? WHERE cod = ?");
                stmt.setInt(1, funcao);
                stmt.setInt(2, codigoCliente);
            }
            if (tipoPessoa == 2) {
                stmt = con.prepareStatement("UPDATE tabela_clientes_juridicos SET excluido = ? WHERE cod = ?");
                stmt.setInt(1, funcao);
                stmt.setInt(2, codigoCliente);
            }
            stmt.executeUpdate();
        } catch (SQLException ex) {
            throw new SQLException(ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }

    public static Integer retornaCliente(String cpfCnpj, byte tipoPessoa) throws SQLException {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Integer retorno = null;

        try {
            if (tipoPessoa == 1) {
                stmt = con.prepareStatement("SELECT cod FROM tabela_clientes_fisicos WHERE cpf = ?");
                stmt.setString(1, cpfCnpj);
            } else if (tipoPessoa == 2) {
                stmt = con.prepareStatement("SELECT cod FROM tabela_clientes_juridicos WHERE cnpj = ?");
                stmt.setString(1, cpfCnpj);
            }

            if ((rs = stmt.executeQuery()).next()) {
                retorno = rs.getInt("cod");
            } else {
                retorno = 0;
                throw new ClienteNaoEncontradoException();
            }
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return retorno;
    }

    public static void gravarClientes(ClienteBEAN cadastroClientes2BEAN, byte tipoPessoa) throws SQLException {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            if (tipoPessoa == 1) {
                stmt = con.prepareStatement("INSERT INTO tabela_clientes_fisicos(cod, nome, cpf,"
                        + "atividade, cod_atendente, nome_atendente, observacoes, excluido)"
                        + "VALUES(?,?,?,?,?,?,?,?)");
                stmt.setInt(1, cadastroClientes2BEAN.getCod());
                stmt.setString(2, cadastroClientes2BEAN.getNome());
                stmt.setString(3, cadastroClientes2BEAN.getCpf());
                stmt.setString(4, cadastroClientes2BEAN.getAtividade());
                stmt.setString(5, cadastroClientes2BEAN.getCodAtendente());
                stmt.setString(6, cadastroClientes2BEAN.getNomeAtendente());
                stmt.setString(7, cadastroClientes2BEAN.getObservacoes());
                stmt.setInt(8, 0);
            }
            if (tipoPessoa == 2) {
                stmt = con.prepareStatement("INSERT INTO tabela_clientes_juridicos(cod, nome, nome_fantasia,"
                        + "cnpj,atividade,filial_coligada, cod_atendente, nome_atendente, observacao, excluido)"
                        + "VALUES(?,?,?,?,?,?,?,?,?,?)");
                stmt.setInt(1, cadastroClientes2BEAN.getCod());
                stmt.setString(2, cadastroClientes2BEAN.getNome());
                stmt.setString(3, cadastroClientes2BEAN.getNomeFantasia());
                stmt.setString(4, cadastroClientes2BEAN.getCnpj());
                stmt.setString(5, cadastroClientes2BEAN.getAtividade());
                stmt.setString(6, cadastroClientes2BEAN.getFilialColigada());
                stmt.setString(7, cadastroClientes2BEAN.getCodAtendente());
                stmt.setString(8, cadastroClientes2BEAN.getNomeAtendente());
                stmt.setString(9, cadastroClientes2BEAN.getObservacoes());
                stmt.setInt(10, 0);
            }
            stmt.executeUpdate();
        } catch (SQLException ex) {
            throw new SQLException(ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }

    public static void atualizaClientes(ClienteBEAN cadastroClientes2BEAN, byte tipoPessoa) throws SQLException {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            if (tipoPessoa == 1) {
                stmt = con.prepareStatement("UPDATE tabela_clientes_fisicos SET  nome = ?, cpf = ?,"
                        + "atividade = ?, cod_atendente = ?, nome_atendente = ?, observacoes = ? WHERE cod = ?");
                stmt.setString(1, cadastroClientes2BEAN.getNome());
                stmt.setString(2, cadastroClientes2BEAN.getCpf());
                stmt.setString(3, cadastroClientes2BEAN.getAtividade());
                stmt.setString(4, cadastroClientes2BEAN.getCodAtendente());
                stmt.setString(5, cadastroClientes2BEAN.getNomeAtendente());
                stmt.setString(6, cadastroClientes2BEAN.getObservacoes());
                stmt.setInt(7, cadastroClientes2BEAN.getCod());
            }
            if (tipoPessoa == 2) {
                stmt = con.prepareStatement("UPDATE tabela_clientes_juridicos SET  nome = ?, nome_fantasia = ?,"
                        + "cnpj = ?,atividade = ?,filial_coligada = ?, cod_atendente = ?, nome_atendente = ?, observacao = ? WHERE cod = ?");
                stmt.setString(1, cadastroClientes2BEAN.getNome());
                stmt.setString(2, cadastroClientes2BEAN.getNomeFantasia());
                stmt.setString(3, cadastroClientes2BEAN.getCnpj());
                stmt.setString(4, cadastroClientes2BEAN.getAtividade());
                stmt.setString(5, cadastroClientes2BEAN.getFilialColigada());
                stmt.setString(6, cadastroClientes2BEAN.getCodAtendente());
                stmt.setString(7, cadastroClientes2BEAN.getNomeAtendente());
                stmt.setString(8, cadastroClientes2BEAN.getObservacoes());
                stmt.setInt(9, cadastroClientes2BEAN.getCod());
            }
            stmt.executeUpdate();
        } catch (SQLException ex) {
            throw new SQLException(ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }

    public static void associacaoClientes(List codigosEnderecos, List codigosContatos, int codCliente, byte tipoPessoa) throws SQLException {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        PreparedStatement stmt2 = null;
        int tipo = 0;

        if (tipoPessoa == 1) {
            tipo = 1;
        }
        if (tipoPessoa == 2) {
            tipo = 2;
        }

        try {
            for (int i = 0; i < codigosEnderecos.size(); i++) {
                stmt = con.prepareStatement("INSERT INTO tabela_associacao_enderecos(cod_endereco,"
                        + "cod_cliente, tipo_cliente) VALUES(?,?,?)");
                stmt.setInt(1, Integer.valueOf(codigosEnderecos.get(i).toString()));
                stmt.setInt(2, codCliente);
                stmt.setInt(3, tipo);
                stmt.executeUpdate();
            }
            for (int i = 0; i < codigosContatos.size(); i++) {
                stmt2 = con.prepareStatement("INSERT INTO tabela_associacao_contatos(cod_contato,"
                        + "cod_cliente, tipo_cliente) VALUES(?,?,?)");
                stmt2.setInt(1, Integer.valueOf(codigosContatos.get(i).toString()));
                stmt2.setInt(2, codCliente);
                stmt2.setInt(3, tipo);
                stmt2.executeUpdate();
            }
        } catch (SQLException ex) {
            throw new SQLException(ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
            ConnectionFactory.closeConnection(con, stmt2);
        }
    }

    public static void excluirAssociacaoClientes(int codigoCliente, byte tipoPessoa) throws SQLException {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("DELETE FROM tabela_associacao_contatos WHERE cod_cliente = ? AND tipo_cliente = ?");
            stmt.setInt(1, codigoCliente);
            stmt.setInt(2, tipoPessoa);
            stmt.executeUpdate();

            stmt = con.prepareStatement("DELETE FROM tabela_associacao_enderecos WHERE cod_cliente = ? AND tipo_cliente = ?");
            stmt.setInt(1, codigoCliente);
            stmt.setInt(2, tipoPessoa);
            stmt.executeUpdate();
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }

    public static int retornaUltimoRegistroClientes(byte tipoPessoa) throws SQLException {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int retorno = 0;

        try {
            if (tipoPessoa == 1) {
                stmt = con.prepareStatement("SELECT cod FROM tabela_clientes_fisicos ORDER BY cod DESC LIMIT 1");
            }
            if (tipoPessoa == 2) {
                stmt = con.prepareStatement("SELECT cod FROM tabela_clientes_juridicos ORDER BY cod DESC LIMIT 1");
            }
            rs = stmt.executeQuery();
            while (rs.next()) {
                retorno = rs.getInt("cod");
            }
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return retorno;
    }

    public static Boolean verificaCpfCnpj(byte tipoPessoa, String valor, Boolean excluido) throws SQLException {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Boolean retorno = false;

        try {
            if (tipoPessoa == 1) {
                if (!excluido) {
                    stmt = con.prepareStatement("SELECT cpf FROM tabela_clientes_fisicos WHERE excluido = 0");
                } else if (excluido) {
                    stmt = con.prepareStatement("SELECT cpf FROM tabela_clientes_fisicos WHERE excluido = 1");
                }
                rs = stmt.executeQuery();
                while (rs.next()) {
                    if (valor.equals(rs.getString("cpf"))) {
                        retorno = true;
                    }
                }
            } else {
                if (!excluido) {
                    stmt = con.prepareStatement("SELECT cnpj FROM tabela_clientes_juridicos WHERE excluido = 0");
                } else if (excluido) {
                    stmt = con.prepareStatement("SELECT cnpj FROM tabela_clientes_juridicos WHERE excluido = 1");
                }
                rs = stmt.executeQuery();
                while (rs.next()) {
                    if (valor.equals(rs.getString("cnpj"))) {
                        retorno = true;
                    }
                }
            }
        } catch (Exception ex) {
            throw new SQLException(ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return retorno;
    }

    public static Boolean verificaAtrelamentoCliente(Integer codigoCliente, byte tipoPessoa) throws SQLException {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Boolean retorno = false;

        try {
            //VERIFICA ORCAMENTOS
            stmt = con.prepareStatement("SELECT cod_cliente FROM tabela_orcamentos WHERE cod_cliente = ? AND tipo_cliente = ?");
            stmt.setInt(1, codigoCliente);
            stmt.setInt(2, tipoPessoa);
            if ((rs = stmt.executeQuery()).next()) {
                retorno = true;
            }
            //VERIFICA ORDENS DE PRODUCAO
            stmt = con.prepareStatement("SELECT cod_cliente FROM tabela_ordens_producao WHERE cod_cliente = ? AND tipo_cliente = ?");
            stmt.setInt(1, codigoCliente);
            stmt.setInt(2, tipoPessoa);
            if ((rs = stmt.executeQuery()).next()) {
                retorno = true;
            }
            //VERIFICA NOTAS
            stmt = con.prepareStatement("SELECT cod_cliente FROM tabela_notas WHERE cod_cliente = ? AND tipo_pessoa = ?");
            stmt.setInt(1, codigoCliente);
            stmt.setInt(2, tipoPessoa);
            if ((rs = stmt.executeQuery()).next()) {
                retorno = true;
            }
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return retorno;
    }

    public static Cliente retornaTipoCodClienteRelatorio(String nome) throws SQLException {
        Connection con = ConnectionFactory.getConnection();
        ResultSet rs = null;
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("SELECT cod, nome FROM tabela_clientes_fisicos WHERE nome = ?");
            stmt.setString(1, nome);
            rs = stmt.executeQuery();
            if (rs.next()) {
                return new Cliente(rs.getInt("cod"),
                        (byte) 1);
            } else {
                stmt = con.prepareStatement("SELECT cod, nome FROM tabela_clientes_juridicos WHERE nome = ?");
                stmt.setString(1, nome);
                rs = stmt.executeQuery();
                if (rs.next()) {
                    return new Cliente(rs.getInt("cod"),
                            (byte) 2);
                }
            }
            return null;
        } catch (SQLException ex) {
            throw new SQLException(ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
    }

    public static List<String> retornaPesquisaClientesAproximada(String texto) throws SQLException {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<String> retorno = new ArrayList();
        retorno.clear();

        try {
            stmt = con.prepareStatement("SELECT nome FROM tabela_clientes_fisicos WHERE nome LIKE '%"
                    + texto + "%' ORDER BY nome DESC LIMIT 5");
            rs = stmt.executeQuery();
            while (rs.next()) {
                retorno.add(rs.getString("nome"));
            }

            stmt = con.prepareStatement("SELECT cod,nome FROM tabela_clientes_juridicos WHERE nome LIKE '%"
                    + texto + "%' ORDER BY nome DESC LIMIT 5");
            rs = stmt.executeQuery();
            while (rs.next()) {
                retorno.add(rs.getString("nome"));
            }
        } catch (SQLException ex) {
            throw new SQLException(ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return retorno;
    }

    /**
     * Retorna o nome do cliente por extenso
     * @param codCliente código do cliente
     * @param tipoCliente 1 - Pessoa física, 2 - Pessoa jurídica
     * @return
     * @throws SQLException 
     */
    public static String retornaNomeCliente(int codCliente, byte tipoCliente) throws SQLException {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String retorno = null;

        try {
            if (tipoCliente == 1) {
                stmt = con.prepareStatement("SELECT nome "
                        + "FROM tabela_clientes_fisicos "
                        + "WHERE cod = ?");
                stmt.setInt(1, codCliente);
            } else {
                stmt = con.prepareStatement("SELECT nome "
                        + "FROM tabela_clientes_juridicos "
                        + "WHERE cod = ?");
                stmt.setInt(1, codCliente);
            }
            rs = stmt.executeQuery();
            if (rs.next()) {
                retorno = rs.getString("nome");
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

    public static List<EnderecoBEAN> retornaDescricaoEnderecos(List codigos) throws SQLException {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<EnderecoBEAN> retorno = new ArrayList();

        try {
            for (int i = 0; i < codigos.size(); i++) {
                stmt = con.prepareStatement("SELECT * FROM tabela_enderecos WHERE cod = ?");
                stmt.setInt(1, Integer.valueOf(codigos.get(i).toString()));
                rs = stmt.executeQuery();
                while (rs.next()) {
                    EnderecoBEAN cadastroClientes2EnderecosBEAN = new EnderecoBEAN();
                    cadastroClientes2EnderecosBEAN.setCodigo(rs.getInt("cod"));
                    cadastroClientes2EnderecosBEAN.setCep(rs.getString("cep"));
                    cadastroClientes2EnderecosBEAN.setLogadouro(rs.getString("logadouro"));
                    cadastroClientes2EnderecosBEAN.setBairro(rs.getString("bairro"));
                    cadastroClientes2EnderecosBEAN.setUf(rs.getString("uf"));
                    cadastroClientes2EnderecosBEAN.setCidade(rs.getString("cidade"));
                    cadastroClientes2EnderecosBEAN.setComplemento(rs.getString("complemento"));
                    cadastroClientes2EnderecosBEAN.setTipoEndereco(rs.getString("tipo_endereco"));
                    retorno.add(cadastroClientes2EnderecosBEAN);
                }
            }
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
                for (int codigoEnderecoAux : retornaCodigosEnderecos(codigoCliente, tipoPessoa)) {
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

    public static List<Integer> retornaCodigosEnderecos(int codigoCliente, byte tipoPessoa) throws SQLException {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Integer> retorno = new ArrayList();

        try {
            stmt = con.prepareStatement("SELECT cod_endereco FROM tabela_associacao_enderecos WHERE cod_cliente = ? AND tipo_cliente = ?");
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

    public void excluirEnderecosCliente(int codigoCliente, byte tipoCliente) throws SQLException {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        List<Integer> listaCodigosEnderecos = retornaCodigosEnderecos(codigoCliente, tipoCliente);

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

    public static List<EnderecoBEAN> retornaPesquisaEnderecos(String p1, String p2, boolean todos) throws SQLException {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<EnderecoBEAN> retorno = new ArrayList();

        try {
            if (p1.equals("CÓDIGO")) {
                stmt = con.prepareStatement("SELECT * FROM tabela_enderecos"
                        + " WHERE cod = ? AND excluido != 1");
                stmt.setInt(1, Integer.valueOf(p2));
            }
            if (p1.equals("CEP (SOMENTE NÚMEROS)")) {
                stmt = con.prepareStatement("SELECT * FROM tabela_enderecos"
                        + " WHERE cep = ? AND excluido != 1");
                stmt.setString(1, p2);
            }
            if (p1.equals("TIPO ENDEREÇO (RESIDENCIAL)")) {
                stmt = con.prepareStatement("SELECT * FROM tabela_enderecos"
                        + " WHERE tipo_endereco = 'RESIDENCIAL' AND excluido != 1");
            }
            if (p1.equals("TIPO ENDEREÇO (COMERCIAL)")) {
                stmt = con.prepareStatement("SELECT * FROM tabela_enderecos"
                        + " WHERE tipo_endereco = 'COMERCIAL' AND excluido != 1");
            }
            if (p1.equals("LOGADOURO")) {
                stmt = con.prepareStatement("SELECT * FROM tabela_enderecos"
                        + " WHERE logadouro"
                        + " LIKE " + "'%" + p2 + "%' AND excluido != 1");
            }
            if (p1.equals("CIDADE")) {
                stmt = con.prepareStatement("SELECT * FROM tabela_enderecos"
                        + " WHERE cidade"
                        + " LIKE " + "'%" + p2 + "%' AND excluido != 1");
            }
            if (p1.equals("BAIRRO")) {
                stmt = con.prepareStatement("SELECT * FROM tabela_enderecos"
                        + " WHERE bairro"
                        + " LIKE " + "'%" + p2 + "%' AND excluido != 1");
            }
            if (p1.equals("UF")) {
                stmt = con.prepareStatement("SELECT * FROM tabela_enderecos"
                        + " WHERE uf"
                        + " LIKE " + "'%" + p2 + "%' AND excluido != 1");
            }
            if (todos == true) {
                stmt = con.prepareStatement("SELECT * FROM tabela_enderecos WHERE excluido != 1");
            }
            rs = stmt.executeQuery();
            while (rs.next()) {
                EnderecoBEAN cadastroClientes2EnderecosBEAN = new EnderecoBEAN();
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

    public static void gravarEnderecos(EnderecoBEAN auxBEAN) throws SQLException {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("INSERT INTO tabela_enderecos(cod, cep,"
                    + "tipo_endereco, logadouro, cidade, bairro, uf,"
                    + "complemento) VALUES(?,?,?,?,?,?,?,?)");
            stmt.setInt(1, auxBEAN.getCodigo());
            stmt.setString(2, auxBEAN.getCep());
            stmt.setString(3, auxBEAN.getTipoEndereco());
            stmt.setString(4, auxBEAN.getLogadouro());
            stmt.setString(5, auxBEAN.getCidade());
            stmt.setString(6, auxBEAN.getBairro());
            stmt.setString(7, auxBEAN.getUf());
            stmt.setString(8, auxBEAN.getComplemento());
            stmt.executeUpdate();
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }

    public static void atualizaEnderecos(EnderecoBEAN cadastroClientes2EnderecosBEAN) throws SQLException {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("UPDATE tabela_enderecos SET cep = ?, tipo_endereco = ?, logadouro = ?,"
                    + "bairro = ?, uf = ?, cidade = ?, complemento = ? WHERE cod = ?");
            stmt.setString(1, cadastroClientes2EnderecosBEAN.getCep());
            stmt.setString(2, cadastroClientes2EnderecosBEAN.getTipoEndereco());
            stmt.setString(3, cadastroClientes2EnderecosBEAN.getLogadouro());
            stmt.setString(4, cadastroClientes2EnderecosBEAN.getBairro());
            stmt.setString(5, cadastroClientes2EnderecosBEAN.getUf());
            stmt.setString(6, cadastroClientes2EnderecosBEAN.getCidade());
            stmt.setString(7, cadastroClientes2EnderecosBEAN.getComplemento());
            stmt.setInt(8, cadastroClientes2EnderecosBEAN.getCodigo());
            stmt.executeUpdate();
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }

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

    //CONTATOS------------------------------------------------------------------
    public static List<Integer> selecionaContatos(byte tipoPessoa, Integer codigoCliente) throws SQLException {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Integer> retorno = new ArrayList();

        try {
            if (tipoPessoa == 1) {
                stmt = con.prepareStatement("SELECT cod_contato FROM tabela_associacao_contatos WHERE cod_cliente = ? AND tipo_cliente = 1");
                stmt.setInt(1, codigoCliente);
            }
            if (tipoPessoa == 2) {
                stmt = con.prepareStatement("SELECT cod_contato FROM tabela_associacao_contatos WHERE cod_cliente = ? AND tipo_cliente = 2");
                stmt.setInt(1, codigoCliente);
            }
            rs = stmt.executeQuery();
            while (rs.next()) {
                retorno.add(rs.getInt("cod_contato"));
            }
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return retorno;
    }

    public static List<ContatoBEAN> retornaDescricaoContatos(List codigos) throws SQLException {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<ContatoBEAN> retorno = new ArrayList();

        try {
            for (int i = 0; i < codigos.size(); i++) {
                stmt = con.prepareStatement("SELECT * FROM tabela_contatos WHERE cod = ?");
                stmt.setInt(1, Integer.valueOf(codigos.get(i).toString()));
                rs = stmt.executeQuery();
                while (rs.next()) {
                    retorno.add(new ContatoBEAN(rs.getInt("cod"),
                            rs.getString("nome_contato"),
                            rs.getString("email"),
                            rs.getString("telefone"),
                            rs.getString("ramal"),
                            rs.getString("telefone2"),
                            rs.getString("ramal2"),
                            rs.getString("departamento")));
                }
            }
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return retorno;
    }

    public static void desativaAtivaContatos(int codigoCliente, int codigoContato, byte tipoPessoa, byte funcao, boolean associacao) throws SQLException {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            if (!associacao) {
                stmt = con.prepareStatement("UPDATE tabela_contatos SET excluido = ? WHERE cod = ?");
                stmt.setInt(1, funcao);
                stmt.setInt(2, codigoContato);
                stmt.executeUpdate();
            }
            if (associacao) {
                for (int codigoContatoAux : retornaCodigosEnderecos(codigoCliente, tipoPessoa)) {
                    stmt = con.prepareStatement("UPDATE tabela_contatos SET excluido = ? WHERE cod = ?");
                    stmt.setInt(1, funcao);
                    stmt.setInt(2, codigoContatoAux);
                    stmt.executeUpdate();
                }
            }
        } catch (SQLException ex) {
            throw new SQLException(ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }

    public static List<Integer> retornaCodigosContatos(int codigoCliente, byte tipoPessoa) throws SQLException {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Integer> retorno = new ArrayList();

        try {
            stmt = con.prepareStatement("SELECT cod_contato FROM tabela_associacao_contatos WHERE cod_cliente = ? AND tipo_cliente = ?");
            stmt.setInt(1, codigoCliente);
            stmt.setInt(2, tipoPessoa);
            while ((rs = stmt.executeQuery()).next()) {
                retorno.add(rs.getInt("cod_contato"));
            }
            stmt.executeUpdate();
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return retorno;
    }

    public static List<ContatoBEAN> retornaPesquisaContatos(String p1, String p2, boolean todos) throws SQLException {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<ContatoBEAN> retorno = new ArrayList();

        try {
            if (p1.equals("CÓDIGO")) {
                stmt = con.prepareStatement("SELECT * FROM tabela_contatos"
                        + " WHERE cod = ? AND excluido != 1");
                stmt.setInt(1, Integer.valueOf(p2));
            }
            if (p1.equals("NOME CONTATO")) {
                stmt = con.prepareStatement("SELECT * FROM tabela_contatos"
                        + " WHERE nome_contato"
                        + " LIKE " + "'%" + p2 + "%' AND excluido != 1");
            }
            if (p1.equals("EMAIL")) {
                stmt = con.prepareStatement("SELECT * FROM tabela_contatos"
                        + " WHERE email"
                        + " LIKE " + "'%" + p2 + "%' AND excluido != 1");
            }
            if (p1.equals("DEPARTAMENTO")) {
                stmt = con.prepareStatement("SELECT * FROM tabela_contatos"
                        + " WHERE departamento"
                        + " LIKE " + "'%" + p2 + "%' AND excluido != 1");
            }
            if (todos == true) {
                stmt = con.prepareStatement("SELECT * FROM tabela_contatos WHERE excluido != 1");
            }
            rs = stmt.executeQuery();
            while (rs.next()) {
                retorno.add(new ContatoBEAN(rs.getInt("cod"),
                        rs.getString("nome_contato"),
                        rs.getString("email"),
                        rs.getString("telefone"),
                        rs.getString("ramal"),
                        rs.getString("telefone2"),
                        rs.getString("ramal2"),
                        rs.getString("departamento")));
            }
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return retorno;
    }

    public void gravarContatos(ContatoBEAN cadastroClientes2ContatosBEAN) throws SQLException {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("INSERT INTO tabela_contatos(cod, nome_contato, email,"
                    + "telefone, ramal, telefone2, ramal2, departamento"
                    + ") VALUES(?,?,?,?,?,?,?,?)");
            stmt.setInt(1, cadastroClientes2ContatosBEAN.getCod());
            stmt.setString(2, cadastroClientes2ContatosBEAN.getNomeContato());
            stmt.setString(3, cadastroClientes2ContatosBEAN.getEmail());
            stmt.setString(4, cadastroClientes2ContatosBEAN.getTelefone());
            stmt.setString(5, cadastroClientes2ContatosBEAN.getRamal());
            stmt.setString(6, cadastroClientes2ContatosBEAN.getTelefone2());
            stmt.setString(7, cadastroClientes2ContatosBEAN.getRamal2());
            stmt.setString(8, cadastroClientes2ContatosBEAN.getDepartamento());
            stmt.executeUpdate();
            stmt.close();
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }

    public void atualizaContatos(ContatoBEAN cadastroClientes2ContatosBEAN, int codigoContato) throws SQLException {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("UPDATE tabela_contatos SET  nome_contato = ?,"
                    + "email = ?, telefone = ?, ramal = ?, telefone2 = ?,"
                    + "ramal2 = ?, departamento = ? WHERE cod = ?");
            stmt.setString(1, cadastroClientes2ContatosBEAN.getNomeContato());
            stmt.setString(2, cadastroClientes2ContatosBEAN.getEmail());
            stmt.setString(3, cadastroClientes2ContatosBEAN.getTelefone());
            stmt.setString(4, cadastroClientes2ContatosBEAN.getRamal());
            stmt.setString(5, cadastroClientes2ContatosBEAN.getTelefone2());
            stmt.setString(6, cadastroClientes2ContatosBEAN.getRamal2());
            stmt.setString(7, cadastroClientes2ContatosBEAN.getDepartamento());
            stmt.setInt(8, codigoContato);
            stmt.executeUpdate();
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }

    public int retornaUltimoRegistroContatos() throws SQLException {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int retorno = 0;

        try {
            stmt = con.prepareStatement("SELECT cod FROM tabela_contatos ORDER BY cod DESC LIMIT 1");
            rs = stmt.executeQuery();
            while (rs.next()) {
                retorno = rs.getInt("cod");
            }
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return retorno;
    }

    public List<Integer> retornaCodigoContato(Integer codigoCliente, byte tipoPessoa) throws SQLException {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Integer> retorno = new ArrayList();

        try {
            stmt = con.prepareStatement("SELECT cod_contato FROM tabela_associacao_contatos WHERE cod_cliente = ? AND tipo_cliente = ?");
            stmt.setInt(1, codigoCliente);
            stmt.setInt(2, tipoPessoa);
            rs = stmt.executeQuery();
            while (rs.next()) {
                retorno.add(rs.getInt("cod_contato"));
            }
        } catch (SQLException ex) {
            retorno = null;
            throw new SQLException(ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return retorno;
    }

    public Boolean verificaAtrelamentoContatos(Integer codigoContato) throws SQLException {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Boolean retorno = false;

        try {
            //VERIFICA ORCAMENTOS
            stmt = con.prepareStatement("SELECT cod_contato FROM tabela_orcamentos WHERE cod_contato = ?");
            stmt.setInt(1, codigoContato);
            if ((rs = stmt.executeQuery()).next()) {
                retorno = true;
            }
            //VERIFICA ORDENS DE PRODUCAO
            stmt = con.prepareStatement("SELECT cod_contato FROM tabela_ordens_producao WHERE cod_contato = ?");
            stmt.setInt(1, codigoContato);
            if ((rs = stmt.executeQuery()).next()) {
                retorno = true;
            }
            //VERIFICA NOTAS
            stmt = con.prepareStatement("SELECT cod_contato FROM tabela_notas WHERE cod_contato = ?");
            stmt.setInt(1, codigoContato);
            if ((rs = stmt.executeQuery()).next()) {
                retorno = true;
            }
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return retorno;
    }

    //NOTA FISCAL - INFORMAÇÕES CLIENTE-----------------------------------------
    /**
     * Usada para retornar informações do cliente para a pesquisa de notas
     *
     * @param tipoPessoa tipo de cliente
     * @param codCliente código do cliente
     * @return Cliente(cod, nome, doc - cpf ou cnpj) cliente da nota
     * @see selecionaInformacoesNota#selInfoNota
     */
    public static Cliente selInfoNota(byte tipoPessoa, int codCliente) throws SQLException {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            //INFORMAÇÕES CLIENTE-----------------------------------------------
            switch (tipoPessoa) {
                case 1:
                    stmt = con.prepareStatement("SELECT cod, nome, cpf "
                            + "FROM tabela_clientes_fisicos "
                            + "WHERE cod = ?");
                    stmt.setInt(1, codCliente);
                    break;
                case 2:
                    stmt = con.prepareStatement("SELECT cod, nome, cnpj "
                            + "FROM tabela_clientes_juridicos "
                            + "WHERE cod = ?");
                    stmt.setInt(1, codCliente);
                    break;
            }
            rs = stmt.executeQuery();
            if (rs.next()) {
                switch (tipoPessoa) {
                    case 1:
                        return new Cliente(
                                rs.getInt("cod"),
                                rs.getString("nome"),
                                rs.getString("cpf"),
                                (byte) 1
                        );
                    case 2:
                        return new Cliente(
                                rs.getInt("cod"),
                                rs.getString("nome"),
                                rs.getString("cnpj"),
                                (byte) 2
                        );
                }
            }
            return null;
        } catch (SQLException ex) {
            throw new SQLException(ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
    }

    //CARREGA ORÇAMENTOS CLIENTE------------------------------------------------
    public List<Orcamento> carregaOrcamentos(int codigoCliente, int tipoPessoa) throws SQLException {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Orcamento> retorno = new ArrayList();

        try {
            stmt = con.prepareStatement("SELECT cod, data_emissao, data_validade, valor_total, status, cod_emissor FROM tabela_orcamentos WHERE cod_cliente = ? AND tipo_cliente = ?");
            stmt.setInt(1, codigoCliente);
            stmt.setInt(2, tipoPessoa);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Orcamento orcamentoBEAN = new Orcamento();
                orcamentoBEAN.setCod(rs.getInt("cod"));
                orcamentoBEAN.setDataEmissao(rs.getDate("data_emissao"));
                orcamentoBEAN.setDataValidade(rs.getDate("data_validade"));
                orcamentoBEAN.setValorTotal(rs.getFloat("valor_total"));
                orcamentoBEAN.setStatus(rs.getByte("status"));
                orcamentoBEAN.setCodEmissor(rs.getString("cod_emissor"));
                retorno.add(orcamentoBEAN);
            }
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return retorno;
    }

    public List<Orcamento> carregaOrcamentosPersonalizado(int codigoCliente, int tipoPessoa, String p1, String p2) throws SQLException {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Orcamento> retorno = new ArrayList();

        try {
            if (p1.equals("MÊS/ANO") || p1.equals("ANO")) {
                stmt = con.prepareStatement("SELECT cod, data_emissao, data_validade, valor_total, status, cod_emissor "
                        + "FROM tabela_orcamentos WHERE data_emissao LIKE '%" + p2 + "%' AND "
                        + "cod_cliente = ? AND tipo_cliente = ?");
                stmt.setInt(1, codigoCliente);
                stmt.setInt(2, tipoPessoa);
            } else if (p1.equals("EMISSOR")) {
                stmt = con.prepareStatement("SELECT cod, data_emissao, data_validade, valor_total, status, cod_emissor "
                        + "FROM tabela_orcamentos WHERE cod_emissor LIKE '%" + p2 + "%' AND "
                        + "cod_cliente = ? AND tipo_cliente = ?");
                stmt.setInt(1, codigoCliente);
                stmt.setInt(2, tipoPessoa);
            }
            rs = stmt.executeQuery();
            while (rs.next()) {
                Orcamento aux = new Orcamento();
                aux.setCod(rs.getInt("cod"));
                aux.setDataEmissao(rs.getDate("data_emissao"));
                aux.setDataValidade(rs.getDate("data_validade"));
                aux.setValorTotal(rs.getFloat("valor_total"));
                aux.setStatus(rs.getByte("status"));
                aux.setCodEmissor(rs.getString("cod_emissor"));
                retorno.add(aux);
            }
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return retorno;
    }

    /**
     * Usada para retornar o crédito do cliente
     *
     * @param codCliente Código do cliente
     * @param tipoPessoa Tipo de pessoa
     * @return double crédito do cliente
     * @see retornaCredCliente
     */
    public static float retornaCredCliente(int codCliente, int tipoPessoa) throws SQLException {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            switch (tipoPessoa) {
                case 1:
                    stmt = con.prepareStatement("SELECT credito FROM tabela_clientes_fisicos WHERE cod = ?");
                    break;
                case 2:
                    stmt = con.prepareStatement("SELECT credito FROM tabela_clientes_juridicos WHERE cod = ?");
                    break;
            }
            stmt.setInt(1, codCliente);
            rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getFloat("credito");
            }
            return 0f;
        } catch (SQLException ex) {
            throw new SQLException(ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
    }

    /**
     * Usada para o retorna de Endereço a partir do código
     *
     * @param codContato código do contato
     * @return ContatoBEAN(nome_contato, email, telefone, telefone2) informações
     * do contato do cliente
     * @see selInfoContatosNota#selInfoContato
     */
    public synchronized static ContatoBEAN selInfoContato(int codContato) throws SQLException {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = con.prepareStatement("SELECT "
                    + "nome_contato, "
                    + "telefone, "
                    + "telefone2, "
                    + "email "
                    + "FROM tabela_contatos "
                    + "WHERE cod = ?");
            stmt.setInt(1, codContato);
            rs = stmt.executeQuery();
            if (rs.next()) {
                return new ContatoBEAN(rs.getString("nome_contato"),
                        rs.getString("email"),
                        rs.getString("telefone"),
                        rs.getString("telefone2")
                );
            }
            return null;
        } catch (SQLException ex) {
            throw new SQLException();
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
    }

    /**
     * Usada para o retorna de Endereço a partir do código
     *
     * @param codContato código do contato
     * @return EnderecoBEAN(cep, tipo_endereco, logadouro, bairro, uf,
     * complemento, cidade) informações do contato do cliente
     * @see selInfoContatosNota#selInfoContato
     */
    public synchronized static EnderecoBEAN selInfoEndereco(int codEndereco) throws SQLException {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = con.prepareStatement("SELECT logadouro, tipo_endereco, bairro, cidade, uf, complemento, cep "
                    + "FROM tabela_enderecos "
                    + "WHERE cod = ?");
            stmt.setInt(1, codEndereco);
            rs = stmt.executeQuery();
            while (rs.next()) {
                return new EnderecoBEAN(rs.getString("cep"),
                        rs.getString("tipo_endereco"),
                        rs.getString("logadouro"),
                        rs.getString("bairro"),
                        rs.getString("uf"),
                        rs.getString("complemento"),
                        rs.getString("cidade"));
            }
            return null;
        } catch (SQLException ex) {
            throw new SQLException();
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
    }

    /**
     * Usada para o retorno de lista de código de cliente para a pesquisa de
     * notas
     *
     * @param condicao condição da pesquisa
     * @param pesquisa tipo de pesquisa
     * @return List<Integer> lista de códigos de clientes
     * @see retornaCodCliente
     */
    public static List<Integer> retornaCodCliente(String condicao, String pesquisa) throws SQLException {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List retorno = new ArrayList();

        try {
            if (condicao.equals("PESSOA FÍSICA - NOME")) {
                stmt = con.prepareStatement("SELECT cod "
                        + "FROM tabela_clientes_fisicos "
                        + "WHERE nome "
                        + "LIKE " + "'%" + pesquisa + "%' ORDER BY cod ASC");
            } else if (condicao.equals("PESSOA FÍSICA - CPF (SOMENTE NÚMEROS)")) {
                stmt = con.prepareStatement("SELECT cod, nome "
                        + "FROM tabela_clientes_fisicos "
                        + "WHERE cpf = ? O"
                        + "RDER BY cod "
                        + "ASC");
                stmt.setString(1, pesquisa);
            } else if (condicao.equals("PESSOA JURÍDICA - NOME")) {
                stmt = con.prepareStatement("SELECT cod "
                        + "FROM tabela_clientes_juridicos "
                        + "WHERE nome "
                        + "LIKE " + "'%" + pesquisa + "%' "
                        + "ORDER BY cod "
                        + "ASC");
            } else if (condicao.equals("PESSOA JURÍDICA - NOME FANTASIA")) {
                stmt = con.prepareStatement("SELECT cod "
                        + "FROM tabela_clientes_juridicos "
                        + "WHERE nome_fantasia "
                        + "LIKE " + "'%" + pesquisa + "%' "
                        + "ORDER BY cod "
                        + "ASC");
            } else if (condicao.equals("PESSOA JURÍDICA - CNPJ (SOMENTE NÚMEROS)")) {
                stmt = con.prepareStatement("SELECT cod "
                        + "FROM tabela_clientes_juridicos "
                        + "WHERE cnpj = ? "
                        + "ORDER BY cod "
                        + "ASC");
                stmt.setString(1, pesquisa);
            }
            rs = stmt.executeQuery();
            while (rs.next()) {
                retorno.add(rs.getInt("cod"));
            }
            return retorno;
        } catch (SQLException ex) {
            throw new SQLException();
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
    }

    /**
     * Retorna pesquisa com condição cliente (List<ClienteBEAN> (cod,
     * tipoCliente))
     *
     * @param condicao
     * @param pesquisa
     * @return
     * @throws SQLException
     */
    public static List<ClienteBEAN> retornaCliente(String condicao, String pesquisa) throws SQLException {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<ClienteBEAN> retorno = new ArrayList();
        byte tipoCliente = 0;

        try {
            if (condicao.equals("PESSOA FÍSICA - NOME")) {
                stmt = con.prepareStatement("SELECT cod "
                        + "FROM tabela_clientes_fisicos "
                        + "WHERE nome "
                        + "LIKE " + "'%" + pesquisa + "%' ORDER BY cod ASC");
                tipoCliente = 1;
            } else if (condicao.equals("PESSOA FÍSICA - CPF (SOMENTE NÚMEROS)")) {
                stmt = con.prepareStatement("SELECT cod, nome "
                        + "FROM tabela_clientes_fisicos "
                        + "WHERE cpf = ? "
                        + "ORDER BY cod "
                        + "ASC");
                stmt.setString(1, pesquisa);
                tipoCliente = 1;
            } else if (condicao.equals("PESSOA JURÍDICA - NOME")) {
                stmt = con.prepareStatement("SELECT cod "
                        + "FROM tabela_clientes_juridicos "
                        + "WHERE nome "
                        + "LIKE " + "'%" + pesquisa + "%' "
                        + "ORDER BY cod "
                        + "ASC");
                tipoCliente = 2;
            } else if (condicao.equals("PESSOA JURÍDICA - NOME FANTASIA")) {
                stmt = con.prepareStatement("SELECT cod "
                        + "FROM tabela_clientes_juridicos "
                        + "WHERE nome_fantasia "
                        + "LIKE " + "'%" + pesquisa + "%' "
                        + "ORDER BY cod "
                        + "ASC");
                tipoCliente = 2;
            } else if (condicao.equals("PESSOA JURÍDICA - CNPJ (SOMENTE NÚMEROS)")) {
                stmt = con.prepareStatement("SELECT cod "
                        + "FROM tabela_clientes_juridicos "
                        + "WHERE cnpj = ? "
                        + "ORDER BY cod "
                        + "ASC");
                stmt.setString(1, pesquisa);
                tipoCliente = 1;
            } else if(condicao.contains("FÍSICA") & condicao.contains("CÓDIGO")){
                stmt = con.prepareStatement("SELECT cod, nome "
                        + "FROM tabela_clientes_fisicos "
                        + "WHERE cod = ? "
                        + "ORDER BY cod "
                        + "ASC");
                stmt.setInt(1, Integer.valueOf(pesquisa));
                tipoCliente = 1;
            }else if(condicao.contains("JURÍDICA") & condicao.contains("CÓDIGO")){
                stmt = con.prepareStatement("SELECT cod, nome "
                        + "FROM tabela_clientes_juridicos "
                        + "WHERE cod = ? "
                        + "ORDER BY cod "
                        + "ASC");
                stmt.setInt(1, Integer.valueOf(pesquisa));
                tipoCliente = 2;
            }
            rs = stmt.executeQuery();
            while (rs.next()) {
                retorno.add(new ClienteBEAN(
                        rs.getInt("cod"),
                        tipoCliente
                ));
            }
            return retorno;
        } catch (SQLException ex) {
            throw new SQLException(ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
    }

    /**
     * Corrige o crédito do cliente
     *
     * @param codCliente código do cliente
     * @param tipoPessoa 1 - PF, 2 - PJ
     * @param valor valor para ser corrigido
     * @param operacao 1 - CRED, 2 - DEB
     * @throws SQLException
     */
    public static void corrigeCredito(int codCliente,
            byte tipoPessoa,
            double valor,
            byte operacao) throws SQLException {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        double credito = 0d;

        try {

            credito = retornaCredCliente(codCliente, tipoPessoa);

            switch (operacao) {
                case 1:
                    credito = credito + valor;
                    break;
                case 2:
                    credito = credito - valor;
                    break;
            }

            switch (tipoPessoa) {
                case 1:
                    stmt = con.prepareStatement("UPDATE tabela_clientes_fisicos "
                            + "SET credito = ? "
                            + "WHERE cod = ?");
                    stmt.setDouble(1, credito);
                    stmt.setInt(2, codCliente);
                    break;
                case 2:
                    stmt = con.prepareStatement("UPDATE tabela_clientes_juridicos "
                            + "SET credito = ? "
                            + "WHERE cod = ?");
                    stmt.setDouble(1, credito);
                    stmt.setInt(2, codCliente);
                    break;
            }
            stmt.executeUpdate();
        } catch (SQLException ex) {
            throw new SQLException(ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
    }

    /**
     * Seleciona as informações do cliente para a ordem de produção
     *
     * @param tipoCliente tipo do cliente
     * @param codCliente código do cliente
     * @return
     * @throws SQLException
     */
    public static ClienteBEAN selInfoOp(int tipoCliente, int codCliente) throws SQLException {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            switch (tipoCliente) {
                case 1:
                    stmt = con.prepareStatement("SELECT nome, cpf, observacoes "
                            + "FROM tabela_clientes_fisicos "
                            + "WHERE cod = ?");
                    stmt.setInt(1, codCliente);
                    rs = stmt.executeQuery();
                    if (rs.next()) {
                        return new ClienteBEAN(
                                rs.getString("nome"),
                                "",
                                rs.getString("cpf"),
                                rs.getString("observacoes"),
                                (byte) 1
                        );
                    }
                    break;
                case 2:
                    stmt = con.prepareStatement("SELECT nome, cnpj, nome_fantasia, observacao "
                            + "FROM tabela_clientes_juridicos "
                            + "WHERE cod = ?");
                    stmt.setInt(1, codCliente);
                    rs = stmt.executeQuery();
                    if (rs.next()) {
                        return new ClienteBEAN(
                                rs.getString("nome"),
                                rs.getString("nome_fantasia"),
                                rs.getString("cnpj"),
                                rs.getString("observacao"),
                                (byte) 2
                        );
                    }
                    break;
            }
            return null;
        } catch (SQLException ex) {
            throw new SQLException(ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
    }
    
    /**
     * Retorna conteúdo do relatório de contabilidade (crédito do cliente)
     * @param tipoCondicaoCliente 1 - PF, 2 - PJ
     * @param tipoCondicaoOrdenar 1 - CODIGO ASC, 2 - CODIGO DESC, 2 - NOME ASC, NOME DESC
     * @return 
     */
    public static List<ClienteBEAN> retornaContRelContabilidade(
            byte tipoCondicaoCliente,
            byte tipoCondicaoOrdenar) throws SQLException{
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        StringBuilder sb = null;
        List<ClienteBEAN> retorno = new ArrayList();

        try {
            switch(tipoCondicaoCliente){
                case 1:
                    sb = new StringBuilder().append("SELECT cod, nome, cpf, credito "
                            + "FROM tabela_clientes_fisicos ");
                    switch(tipoCondicaoOrdenar){
                        case 1:
                            sb.append("ORDER BY cod ASC");
                            break;
                        case 2:
                            sb.append("ORDER BY cod DESC");
                            break;
                        case 3:
                            sb.append("ORDER BY nome ASC");
                            break;
                        case 4:
                            sb.append("ORDER BY nome DESC");
                            break;
                    }
                    break;
                case 2:
                    sb = new StringBuilder().append("SELECT cod, nome, nome_fantasia, cnpj, credito "
                            + "FROM tabela_clientes_juridicos ");
                    switch(tipoCondicaoOrdenar){
                        case 1:
                            sb.append("ORDER BY cod ASC");
                            break;
                        case 2:
                            sb.append("ORDER BY cod DESC");
                            break;
                        case 3:
                            sb.append("ORDER BY nome ASC");
                            break;
                        case 4:
                            sb.append("ORDER BY nome DESC");
                            break;
                    }
                    break;
            }
            stmt = con.prepareStatement(sb.toString());
            rs = stmt.executeQuery();
            while (rs.next()) {
                switch(tipoCondicaoCliente){
                    case 1:
                        retorno.add(new ClienteBEAN(
                                rs.getInt("cod"),
                                rs.getString("nome"),
                                null,
                                rs.getString("cpf"),
                                (byte) 1,
                                rs.getFloat("credito")
                        ));
                        break;
                    case 2:
                        retorno.add(new ClienteBEAN(
                                rs.getInt("cod"),
                                rs.getString("nome"),
                                rs.getString("nome_fantasia"),
                                rs.getString("cnpj"),
                                (byte) 2,
                                rs.getFloat("credito")
                        ));
                        break;
                }
            }
            return retorno;
        } catch (SQLException ex) {
            throw new SQLException(ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
    }
    
    /**
     * Faz a autenticação da senha do cliente
     * @param cod código do cliente
     * @param senha
     * @return 
     */
    public static boolean autenticaCliente(int cod,String senha) throws SQLException{
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try{
            stmt = con.prepareStatement("SELECT senha "
                    + "FROM tabela_clientes_juridicos "
                    + "WHERE senha = md5(?)");
            stmt.setString(1, senha);
            rs = stmt.executeQuery();
            return !rs.wasNull();
        } catch (SQLException ex) {
            throw new SQLException(ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
    }
    
    /**
     * Retorna clientes com créditos negativos para apreciação do financeiro
     * @return
     * @throws SQLException 
     */
    public static List<ClienteBEAN> retornaCredNeg() throws SQLException{
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<ClienteBEAN> creditos = new ArrayList();
        
        try{
            stmt = con.prepareStatement("SELECT cod, nome, credito "
                    + "FROM tabela_clientes_fisicos "
                    + "WHERE credito < 0");
            rs = stmt.executeQuery();
            while(rs.next()){
                creditos.add(new ClienteBEAN(
                        rs.getInt("cod"),
                        rs.getString("nome"),
                        null,
                        (byte) 1,
                        rs.getFloat("credito")
                ));
            }
            stmt = con.prepareStatement("SELECT cod, nome, nome_fantasia, credito "
                    + "FROM tabela_clientes_juridicos "
                    + "WHERE credito < 0");
            rs = stmt.executeQuery();
            while(rs.next()){
                creditos.add(new ClienteBEAN(
                        rs.getInt("cod"),
                        rs.getString("nome"),
                        rs.getString("nome_fantasia"),
                        (byte) 2,
                        rs.getFloat("credito")
                ));
            }
            return creditos;
        } catch (SQLException ex) {
            throw new SQLException(ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
    }
    
    /**
     * Atualiza os dados de acesso (contator e último acesso) dos clientes
     * @param cod - código do cliente
     * @throws SQLException 
     */
    public static void atualizaClientes(int cod) throws SQLException{
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        int acessos = 0;
        Timestamp data = new Timestamp(new Date().getTime());
        
        try{
            stmt = con.prepareStatement("SELECT "
                    + "tabela_clientes_juridicos.QTD_ACESSOS "
                    + "FROM tabela_clientes_juridicos "
                    + "WHERE tabela_clientes_juridicos.cod = ?");
            stmt.setInt(1, cod);
            rs = stmt.executeQuery();
            if(rs.next()){
                acessos = rs.getInt("tabela_clientes_juridicos.QTD_ACESSOS");
            }
            
            acessos++;
            
            stmt = con.prepareStatement("UPDATE tabela_clientes_juridicos "
                    + "SET tabela_clientes_juridicos.ULTIMO_ACESSO = ?, "
                    + "tabela_clientes_juridicos.QTD_ACESSOS = ? "
                    + "WHERE tabela_clientes_juridicos.cod = ?");
            stmt.setTimestamp(1, data);
            stmt.setInt(2, acessos);
            stmt.setInt(3, cod);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            throw new SQLException(ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
    }
}
