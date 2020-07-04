/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.cadastros.notas;

import connection.ConnectionFactory;
import entidades.Faturamento;
import ui.cadastros.clientes.ClienteBEAN;
import ui.cadastros.contatos.ContatoBEAN;
import ui.cadastros.enderecos.EnderecoBEAN;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import ui.cadastros.clientes.ClienteDAO;
import static ui.cadastros.notas.NotaDAO.verificaQuantidadeEntregue;

/**
 *
 * @author spd3
 */
public class NotaDAO {

    //PESQUISA------------------------------------------------------------------
    public static List<NotaBEAN> pesquisaNota(String p1, String p2, String p3) throws SQLException {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        //VARIÁVEIS AUXILIARES--------------------------------------------------
        int aux = 0;
        boolean cliente = false;
        List<Integer> retornoCliente = new ArrayList();

        //RETORNO DA TABELA ORDENS PRODUCAO-------------------------------------
        List codOps = new ArrayList();
        PreparedStatement stmt2 = null;
        ResultSet rs2 = null;

        List<NotaBEAN> retorno = new ArrayList();

        try {
            if (p1.equals("SÉRIE")) {
                aux = Integer.valueOf(p3);
                stmt = con.prepareStatement("SELECT * FROM tabela_notas WHERE serie = ?");
                stmt.setInt(1, aux);
            } else if (p1.equals("OP")) {
                aux = Integer.valueOf(p3);
                stmt = con.prepareStatement("SELECT * FROM tabela_notas WHERE cod_op = ?");
                stmt.setInt(1, aux);
            } else if (p1.equals("DATA LANÇAMENTO")) {
                stmt = con.prepareStatement("SELECT * FROM tabela_notas WHERE data = ?");
                stmt.setString(1, p3);
            } else if (p1.equals("CLIENTE")) {
                if (p2.contains("CÓDIGO")) {
                    stmt = con.prepareStatement("SELECT * FROM tabela_notas WHERE cod_cliente = ?");
                    stmt.setInt(1, Integer.valueOf(p3));
                } else {
                    retornoCliente = retornaCodigoCliente(p2, p3);
                    stmt = con.prepareStatement("SELECT * FROM tabela_notas WHERE cod_cliente = ?");
                    stmt.setInt(1, Integer.valueOf(retornoCliente.get(0).toString()));
                }
            } else if (p1.equals("EMISSOR")) {
                stmt = con.prepareStatement("SELECT * FROM tabela_notas WHERE cod_emissor = ?");
                stmt.setString(1, p3);
            } else if (p1.equals("CÓDIGO")) {
                stmt = con.prepareStatement("SELECT * FROM tabela_notas WHERE cod = ?");
                stmt.setInt(1, Integer.valueOf(p3));
            }

            rs = stmt.executeQuery();
            while (rs.next()) {
                NotaBEAN aux2 = new NotaBEAN();
                aux2.setCod(rs.getInt("cod"));
                aux2.setSerie(rs.getInt("serie"));
                aux2.setCodOp(rs.getInt("cod_op"));
                //CLIENTE-------------------------------------------
                retornoCliente = retornaCodigoCliente(String.valueOf(rs.getInt("tipo_pessoa")), String.valueOf(rs.getInt("cod_cliente")));
                aux2.setCodigoCliente(Integer.valueOf(retornoCliente.get(0).toString()));
                aux2.setNomeCliente(String.valueOf(retornoCliente.get(1)));
                //--------------------------------------------------
                aux2.setCodOrc(rs.getInt("cod_orcamento"));
                aux2.setCodEmissor(rs.getString("cod_emissor"));
                aux2.setQuantidadeEntregue(rs.getInt("quantidade_entregue"));
                aux2.setValor(rs.getFloat("valor"));
                aux2.setData(rs.getString("data"));
                retorno.add(aux2);
            }
        } catch (SQLException ex) {
            throw new SQLException();
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return retorno;
    }

    public static List retornaCodigoCliente(String p1, String texto) throws SQLException {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List retorno = new ArrayList();

        try {
            if (p1.equals("PESSOA FÍSICA - NOME")) {
                stmt = con.prepareStatement("SELECT cod,nome FROM tabela_clientes_fisicos WHERE nome LIKE " + "'%" + texto + "%' ORDER BY cod ASC");
            } else if (p1.equals("PESSOA FÍSICA - CPF (SOMENTE NÚMEROS)")) {
                stmt = con.prepareStatement("SELECT cod,nome FROM tabela_clientes_fisicos WHERE cpf = ? ORDER BY cod ASC");
                stmt.setString(1, texto);
            } else if (p1.equals("PESSOA JURÍDICA - NOME")) {
                stmt = con.prepareStatement("SELECT cod,nome FROM tabela_clientes_juridicos WHERE nome LIKE " + "'%" + texto + "%' ORDER BY cod ASC");
            } else if (p1.equals("PESSOA JURÍDICA - NOME FANTASIA")) {
                stmt = con.prepareStatement("SELECT cod,nome FROM tabela_clientes_juridicos WHERE nome_fantasia LIKE " + "'%" + texto + "%' ORDER BY cod ASC");
            } else if (p1.equals("PESSOA JURÍDICA - CNPJ (SOMENTE NÚMEROS)")) {
                stmt = con.prepareStatement("SELECT cod,nome FROM tabela_clientes_juridicos WHERE cnpj = ? ORDER BY cod ASC");
                stmt.setString(1, texto);
            } else {
                if (Integer.valueOf(p1) == 1) {
                    stmt = con.prepareStatement("SELECT cod, nome FROM tabela_clientes_fisicos WHERE cod = ?");
                    stmt.setInt(1, Integer.valueOf(texto));
                } else if (Integer.valueOf(p1) == 2) {
                    stmt = con.prepareStatement("SELECT cod, nome FROM tabela_clientes_juridicos WHERE cod = ?");
                    stmt.setInt(1, Integer.valueOf(texto));
                }
            }
            rs = stmt.executeQuery();
            while (rs.next()) {
                retorno.add(rs.getInt("cod"));
                retorno.add(rs.getString("nome"));
            }
        } catch (SQLException ex) {
            throw new SQLException();
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return retorno;
    }

    public static List<NotaBEAN> mostraUltimas() throws SQLException {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        //VARIÁVEIS AUXILIARES--------------------------------------------------
        List retornoCliente = new ArrayList();

        List<NotaBEAN> retorno = new ArrayList();

        try {
            stmt = con.prepareStatement("SELECT * FROM tabela_notas ORDER BY cod DESC LIMIT 45");
            rs = stmt.executeQuery();
            while (rs.next()) {
                NotaBEAN aux2 = new NotaBEAN();
                aux2.setCod(rs.getInt("cod"));
                aux2.setSerie(rs.getInt("serie"));
                aux2.setCodOp(rs.getInt("cod_op"));
                //CLIENTE-------------------------------------------
                retornoCliente.clear();
                retornoCliente = retornaCodigoCliente(String.valueOf(rs.getInt("tipo_pessoa")), String.valueOf(rs.getInt("cod_cliente")));
                aux2.setCodigoCliente(Integer.valueOf(retornoCliente.get(0).toString()));
                aux2.setNomeCliente(String.valueOf(retornoCliente.get(1)));
                //--------------------------------------------------
                aux2.setCodOrc(rs.getInt("cod_orcamento"));
                aux2.setCodEmissor(rs.getString("cod_emissor"));
                aux2.setQuantidadeEntregue(rs.getInt("quantidade_entregue"));
                aux2.setValor(rs.getFloat("valor"));
                aux2.setData(rs.getString("data"));
                aux2.setTipo(rs.getInt("tipo"));
                retorno.add(aux2);
            }
        } catch (SQLException ex) {
            throw new SQLException();
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return retorno;
    }

    /**
     * Retorna atendentes com acesso ao módulo Expedição
     * @return
     * @throws SQLException 
     */
    public static List retornaEmissores() throws SQLException {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List retorno = new ArrayList();

        try {
            stmt = con.prepareStatement("SELECT codigo_atendente "
                    + "FROM tabela_atendentes "
                    + "WHERE acesso_exp = 1");
            rs = stmt.executeQuery();
            while (rs.next()) {
                retorno.add(rs.getString("codigo_atendente"));
            }
        } catch (SQLException ex) {
            throw new SQLException(ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return retorno;
    }

    /*
    @param codNota código da nota de venda a ser selecionada
    @return NotaBEAN nota de venda selecionada
    @see selNotaVenda
     */
    public synchronized static NotaBEAN selNotaVenda(int codNota) throws SQLException {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = con.prepareStatement("SELECT serie, cod_op, cod_orcamento,"
                    + "cod_emissor, quantidade_entregue, valor, data,"
                    + "cod_cliente, tipo_pessoa, tipo, cod_endereco, cod_contato,"
                    + "observacoes, FAT_FRETE, FAT_SERVICOS, COD_PRODUTO "
                    + "FROM tabela_notas "
                    + "WHERE cod = ?");
            stmt.setInt(1, codNota);
            rs = stmt.executeQuery();
            if (rs.next()) {
                return new NotaBEAN(
                        codNota,
                        rs.getInt("serie"),
                        rs.getInt("cod_op"),
                        rs.getInt("cod_orcamento"),
                        rs.getString("cod_emissor"),
                        rs.getInt("quantidade_entregue"),
                        rs.getFloat("valor"),
                        rs.getString("data"),
                        rs.getInt("cod_cliente"),
                        rs.getInt("tipo_pessoa"),
                        rs.getInt("tipo"),
                        rs.getInt("cod_endereco"),
                        rs.getInt("cod_contato"),
                        rs.getString("observacoes"),
                        (byte) rs.getInt("FAT_FRETE"),
                        (byte) rs.getInt("FAT_SERVICOS"),
                        ClienteDAO.retornaNomeCliente(rs.getInt("cod_cliente"), (byte) rs.getInt("tipo_pessoa")),
                        rs.getInt("COD_PRODUTO")
                );
            }
            return null;
        } catch (SQLException ex) {
            throw new SQLException();
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
    }

    public static List<NotaBEAN> selecionaNotaCredito(int codNota) throws SQLException {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<NotaBEAN> retorno = new ArrayList();

        try {
            stmt = con.prepareStatement("SELECT * FROM tabela_notas WHERE cod = ?");
            stmt.setInt(1, codNota);
            rs = stmt.executeQuery();
            while (rs.next()) {
                NotaBEAN aux = new NotaBEAN();
                aux.setCod(codNota);
                aux.setSerie(rs.getInt("serie"));
                aux.setTipo(rs.getInt("tipo"));
                aux.setFormaPagamento(rs.getInt("forma_pagamento"));
                aux.setCodEmissor(rs.getString("cod_emissor"));
                aux.setCodigoCliente(rs.getInt("cod_cliente"));
                aux.setCodEndereco(rs.getInt("cod_endereco"));
                aux.setCodContato(rs.getInt("cod_contato"));
                aux.setTipoPessoa(rs.getInt("tipo_pessoa"));
                aux.setValor(rs.getFloat("valor"));
                aux.setData(rs.getString("data"));
                aux.setObservacoes(rs.getString("observacoes"));
                retorno.add(aux);
            }
        } catch (SQLException ex) {
            throw new SQLException();
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return retorno;
    }

    public static List<ClienteBEAN> carregaClientes(int codCliente, int tipoCliente) throws SQLException {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<ClienteBEAN> retorno = new ArrayList();

        try {
            if (tipoCliente == 1) {
                stmt = con.prepareStatement("SELECT * FROM tabela_clientes_fisicos WHERE cod = ?");
                stmt.setInt(1, codCliente);
                rs = stmt.executeQuery();
                while (rs.next()) {
                    ClienteBEAN aux = new ClienteBEAN();
                    aux.setNome(rs.getString("nome"));
                    aux.setCpf(rs.getString("cpf"));
                    retorno.add(aux);
                }
            } else if (tipoCliente == 2) {
                stmt = con.prepareStatement("SELECT * FROM tabela_clientes_juridicos WHERE cod = ?");
                stmt.setInt(1, codCliente);
                rs = stmt.executeQuery();
                while (rs.next()) {
                    ClienteBEAN aux = new ClienteBEAN();
                    aux.setNome(rs.getString("nome"));
                    aux.setCnpj(rs.getString("cnpj"));
                    retorno.add(aux);
                }
            }
        } catch (SQLException ex) {
            throw new SQLException();
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return retorno;
    }

    /*
    @param codNota código da nota a ser selecionado o transporte
    @return TransporteBEAN transporte
    @see TransporteBEAN
     */
    public synchronized static TransporteBEAN selTransporte(int codNota) throws SQLException {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = con.prepareStatement("SELECT * FROM tabela_notas_transporte WHERE cod_nota = ?");
            stmt.setInt(1, codNota);
            rs = stmt.executeQuery();
            if (rs.next()) {
                return new TransporteBEAN(
                        rs.getInt("cod"),
                        rs.getInt("cod_nota"),
                        rs.getString("modalidade_frete"),
                        rs.getString("nome_transportador"),
                        rs.getFloat("espessura_produto"),
                        rs.getFloat("peso_produto")
                );
            }
            return null;
        } catch (SQLException ex) {
            throw new SQLException();
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
    }

    public synchronized static List<VolumeBEAN> selecionaVolumes(int codTransporte) throws SQLException {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<VolumeBEAN> retorno = new ArrayList();

        try {
            stmt = con.prepareStatement("SELECT * FROM tabela_notas_transporte_volumes WHERE cod_transporte = ?");
            stmt.setInt(1, codTransporte);
            rs = stmt.executeQuery();
            while (rs.next()) {
                VolumeBEAN aux = new VolumeBEAN();
                aux.setCod(rs.getInt("cod"));
                aux.setCodTransporte(rs.getInt("cod_transporte"));
                aux.setNumeroVolume(rs.getInt("numero_volume"));
                aux.setAlturaVolume(rs.getFloat("altura_volume"));
                aux.setLarguraVolume(rs.getFloat("largura_volume"));
                aux.setPesoVolume(rs.getFloat("peso_volume"));
                retorno.add(aux);
            }
        } catch (SQLException ex) {
            throw new SQLException();
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return retorno;
    }

    //FUNÇÕES AUXILIARES--------------------------------------------------------
    /**
     * Verifica qual foi a quantidade entregue do produto
     *
     * @param codOp código da ordem de producao ou do orçamento a ser verificado
     * @return int quantidade entregue
     * @throws java.sql.SQLException
     * @see verificaQuantidadeEntregue
     */
    public synchronized static int verificaQuantidadeEntregue(int codOp) throws SQLException {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int retorno = 0;

        try {
            stmt = con.prepareStatement("SELECT QTD_ENTREGUE "
                    + "FROM faturamentos "
                    + "WHERE CODIGO_OP = ?");
            stmt.setInt(1, codOp);
            rs = stmt.executeQuery();
            while (rs.next()) {
                retorno += rs.getInt("QTD_ENTREGUE");
            }
            return retorno;
        } catch (SQLException ex) {
            throw new SQLException(ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
    }

    //GRAVAR NOTA---------------------------------------------------------------
    public static int retornaUltimaNota() throws SQLException {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int retorno = 0;

        try {
            stmt = con.prepareStatement("SELECT cod FROM tabela_notas ORDER BY cod DESC LIMIT 1");
            rs = stmt.executeQuery();
            while (rs.next()) {
                retorno = rs.getInt("cod");
            }
        } catch (SQLException ex) {
            throw new SQLException();
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return retorno;
    }

    public static void gravaNotaVenda(NotaBEAN notaBEAN, int codigoNota) throws SQLException {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = con.prepareStatement("INSERT INTO tabela_notas(cod, serie, cod_op, cod_orcamento, "
                    + "cod_emissor, cod_cliente,"
                    + "tipo_pessoa, quantidade_entregue, "
                    + "valor, data,tipo, observacoes, FAT_FRETE, "
                    + "FAT_SERVICOS, COD_PRODUTO, cod_endereco, cod_contato) "
                    + "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            stmt.setInt(1, codigoNota);
            stmt.setInt(2, notaBEAN.getSerie());
            stmt.setInt(3, notaBEAN.getCodOp());
            stmt.setInt(4, notaBEAN.getCodOrc());
            stmt.setString(5, notaBEAN.getCodEmissor());
            stmt.setInt(6, notaBEAN.getCodCliente());
            stmt.setInt(7, notaBEAN.getTipoPessoa());
            stmt.setInt(8, notaBEAN.getQuantidadeEntregue());
            stmt.setFloat(9, notaBEAN.getValor());
            stmt.setString(10, notaBEAN.getData());
            stmt.setInt(11, notaBEAN.getTipo());
            stmt.setString(12, notaBEAN.getObservacoes());
            stmt.setByte(13, notaBEAN.getFatFrete());
            stmt.setByte(14, notaBEAN.getFatServicos());
            stmt.setInt(15, notaBEAN.getCodProduto());
            stmt.setInt(16, notaBEAN.getCodEndereco());
            stmt.setInt(17, notaBEAN.getCodContato());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            throw new SQLException(ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }

    public static void gravaNotaCredito(NotaBEAN nota) throws SQLException {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = con.prepareStatement("INSERT INTO tabela_notas(cod, serie, forma_pagamento, cod_emissor, cod_cliente, tipo_pessoa,"
                    + "valor, data, tipo, cod_endereco, cod_contato, observacoes) VALUES(?,?,?,?,?,?,?,?,?,?,?,?)");
            stmt.setInt(1, nota.getCod());
            stmt.setInt(2, nota.getSerie());
            stmt.setInt(3, nota.getFormaPagamento());
            stmt.setString(4, nota.getCodEmissor());
            stmt.setInt(5, nota.getCodCliente());
            stmt.setInt(6, nota.getTipoPessoa());
            stmt.setFloat(7, nota.getValor());
            stmt.setString(8, nota.getData());
            stmt.setInt(9, nota.getTipo());
            stmt.setInt(10, nota.getCodEndereco());
            stmt.setInt(11, nota.getCodContato());
            stmt.setString(12, nota.getObservacoes());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            throw new SQLException(ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
    }

    public static int retornaUltimoTransporte() throws SQLException {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = con.prepareStatement("SELECT cod "
                    + "FROM tabela_notas_transporte "
                    + "ORDER BY cod "
                    + "DESC "
                    + "LIMIT 1");
            rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("cod");
            }
            return 0;
        } catch (SQLException ex) {
            throw new SQLException(ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
    }

    public static void gravaTransportes(TransporteBEAN transporte) throws SQLException {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = con.prepareStatement("INSERT INTO tabela_notas_transporte(cod, cod_nota, "
                    + "modalidade_frete, nome_transportador,"
                    + "espessura_produto, peso_produto) VALUES(?,?,?,?,?,?)");
            stmt.setInt(1, transporte.getCod());
            stmt.setInt(2, transporte.getCodNota());
            stmt.setString(3, transporte.getModalidadeFrete());
            stmt.setString(4, transporte.getNomeTransportador());
            stmt.setFloat(5, transporte.getEspessuraProduto());
            stmt.setFloat(6, transporte.getPesoProduto());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            throw new SQLException(ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }

    public static int retornaUltimoVolume() throws SQLException {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int retorno = 0;

        try {
            stmt = con.prepareStatement("SELECT cod FROM tabela_notas_transporte_volumes ORDER BY cod DESC LIMIT 1");
            rs = stmt.executeQuery();
            if (rs.next()) {
                retorno = rs.getInt("cod");
            }
        } catch (SQLException ex) {
            throw new SQLException();
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return retorno;
    }

    public static void gravaVolumes(VolumeBEAN aux, int cod) throws SQLException {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = con.prepareStatement("INSERT INTO tabela_notas_transporte_volumes(cod, cod_transporte, numero_volume,"
                    + "altura_volume, largura_volume, peso_volume) VALUES(?,?,?,?,?,?)");
            stmt.setInt(1, cod);
            stmt.setInt(2, aux.getCodTransporte());
            stmt.setInt(3, aux.getNumeroVolume());
            stmt.setFloat(4, aux.getAlturaVolume());
            stmt.setFloat(5, aux.getLarguraVolume());
            stmt.setFloat(6, aux.getPesoVolume());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            throw new SQLException();
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }

    //EXCLUIR NOTA--------------------------------------------------------------
    public static void excluiVolumes(int codNota) throws SQLException {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        int codTransporte = 0;

        try {
            stmt = con.prepareStatement("SELECT cod FROM tabela_notas_transporte WHERE cod_nota = ?");
            stmt.setInt(1, codNota);
            rs = stmt.executeQuery();
            while (rs.next()) {
                codTransporte = rs.getInt("cod");
            }

            stmt = con.prepareStatement("DELETE FROM tabela_notas_transporte_volumes WHERE cod_transporte = ?");
            stmt.setInt(1, codTransporte);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            throw new SQLException();
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
    }

    public static void excluiTransportes(int codNota) throws SQLException {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = con.prepareStatement("DELETE FROM tabela_notas_transporte "
                    + "WHERE cod_nota = ?");
            stmt.setInt(1, codNota);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            throw new SQLException(ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
    }

    public static void excluiNota(int codNota) throws SQLException {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = con.prepareStatement("DELETE FROM tabela_notas WHERE cod = ?");
            stmt.setInt(1, codNota);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            throw new SQLException(ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
    }

    //RETORNA DADOS NOTA CRÉDITO------------------------------------------------
    public static List<EnderecoBEAN> retornaEndereco(int codEndereco) throws SQLException {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<EnderecoBEAN> retorno = new ArrayList();

        try {
            stmt = con.prepareStatement("SELECT tipo_endereco, bairro, cidade, uf, complemento, cep, logadouro"
                    + " FROM tabela_enderecos WHERE cod = ?");
            stmt.setInt(1, codEndereco);
            rs = stmt.executeQuery();
            while (rs.next()) {
                EnderecoBEAN aux = new EnderecoBEAN();
                aux.setTipoEndereco(rs.getString("tipo_endereco"));
                aux.setBairro(rs.getString("bairro"));
                aux.setCidade(rs.getString("cidade"));
                aux.setUf(rs.getString("uf"));
                aux.setComplemento(rs.getString("complemento"));
                aux.setCep(rs.getString("cep"));
                aux.setLogadouro(rs.getString("logadouro"));
                retorno.add(aux);
            }
        } catch (SQLException ex) {
            throw new SQLException();
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return retorno;
    }

    public static List<ContatoBEAN> retornaContato(int codContato) throws SQLException {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<ContatoBEAN> retorno = new ArrayList();

        try {
            stmt = con.prepareStatement("SELECT nome_contato, email, telefone, telefone2"
                    + " FROM tabela_contatos WHERE cod = ?");
            stmt.setInt(1, codContato);
            rs = stmt.executeQuery();
            while (rs.next()) {
                ContatoBEAN aux = new ContatoBEAN();
                aux.setNomeContato(rs.getString("nome_contato"));
                aux.setEmail(rs.getString("email"));
                aux.setTelefone(rs.getString("telefone"));
                aux.setTelefone2(rs.getString("telefone2"));
                retorno.add(aux);
            }
        } catch (SQLException ex) {
            throw new SQLException();
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return retorno;
    }

    //ATUALIZA NOTA DE CRÉDITO--------------------------------------------------
    /**
     * Atualiza a nota de crédito
     *
     * @param nota
     * @throws SQLException
     */
    public static void atualizaNC(NotaBEAN nota) throws SQLException {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("UPDATE tabela_notas "
                    + "SET forma_pagamento = ?, cod_emissor = ?, cod_cliente = ?,"
                    + "cod_endereco = ?, cod_contato = ?, tipo_pessoa = ?,"
                    + "valor = ?, data = ?, observacoes = ? "
                    + "WHERE cod = ?");
            stmt.setInt(1, nota.getFormaPagamento());
            stmt.setString(2, nota.getCodEmissor());
            stmt.setInt(3, nota.getCodCliente());
            stmt.setInt(4, nota.getCodEndereco());
            stmt.setInt(5, nota.getCodContato());
            stmt.setInt(6, nota.getTipoPessoa());
            stmt.setFloat(7, nota.getValor());
            stmt.setString(8, nota.getData());
            stmt.setString(9, nota.getObservacoes());
            stmt.setInt(10, nota.getCod());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            throw new SQLException(ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }

    //FATURAMENTO---------------------------------------------------------------
    /**
     * Insere registro na tabela faturamentos
     *
     * @param fat entidade faturamento
     * @throws SQLException
     */
    public static void insereFat(Faturamento fat) throws SQLException {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("INSERT INTO faturamentos "
                    + "VALUES(?,?,?,?,?,?,?,?,?,?)");
            stmt.setInt(1, fat.getCod());
            stmt.setInt(2, fat.getCodOrc());
            stmt.setInt(3, fat.getCodOp());
            stmt.setString(4, fat.getEmissor());
            stmt.setInt(5, fat.getQtdEntregue());
            stmt.setDouble(6, fat.getVlrFat());
            stmt.setDate(7, new java.sql.Date(fat.getDtFat().getTime()));
            stmt.setByte(8, fat.getFreteFat());
            stmt.setByte(9, fat.getServicosFat());
            stmt.setString(10, fat.getObservacoes());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            throw new SQLException(ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }

    /**
     * Remove o faturamento
     *
     * @param codFat código do faturamento
     * @throws SQLException
     */
    public static void removeFat(int codFat) throws SQLException {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("DELETE FROM faturamentos "
                    + "WHERE CODIGO = ?");
            stmt.setInt(1, codFat);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            throw new SQLException(ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }

    /**
     * Atualiza faturamento
     *
     * @param fat faturamento
     * @throws SQLException
     */
    public static void atualizaFat(Faturamento fat) throws SQLException {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("UPDATE faturamentos "
                    + "SET EMISSOR = ?, QTD_ENTREGUE = ?, VLR_FAT = ?, DT_FAT = ?,"
                    + "FRETE_FAT = ?, SERVICOS_FAT = ?, OBSERVACOES = ? "
                    + "WHERE CODIGO = ?");
            stmt.setString(1, fat.getEmissor());
            stmt.setInt(2, fat.getQtdEntregue());
            stmt.setDouble(3, fat.getVlrFat());
            stmt.setDate(4, new java.sql.Date(fat.getDtFat().getTime()));
            stmt.setByte(5, fat.getFreteFat());
            stmt.setByte(6, fat.getServicosFat());
            stmt.setString(7, fat.getObservacoes());
            stmt.setInt(8, fat.getCod());
        } catch (SQLException ex) {
            throw new SQLException(ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }

    /**
     * Seleciona o faturamento
     *
     * @param codFat código do faturamento
     * @return
     * @throws SQLException
     */
    public static Faturamento selFat(int codFat) throws SQLException {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = con.prepareStatement("SELECT * "
                    + "FROM faturamentos "
                    + "WHERE CODIGO = ?");
            stmt.setInt(1, codFat);
            rs = stmt.executeQuery();
            if (rs.next()) {
                return new Faturamento(
                        rs.getInt("CODIGO"),
                        rs.getInt("CODIGO_ORC"),
                        rs.getInt("CODIGO_OP"),
                        rs.getString("EMISSOR"),
                        rs.getInt("QTD_ENTREGUE"),
                        rs.getDouble("VLR_FAT"),
                        rs.getDate("DT_FAT"),
                        rs.getByte("FRETE_FAT"),
                        rs.getByte("SERVICOS_FAT"),
                        rs.getString("OBSERVACOES")
                );
            }
            return null;
        } catch (SQLException ex) {
            throw new SQLException(ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
    }

    /**
     * Retorna último código faturamentos
     *
     * @return
     * @throws SQLException
     */
    public static int retornaUltFat() throws SQLException {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = con.prepareStatement("SELECT CODIGO "
                    + "FROM faturamentos "
                    + "ORDER BY CODIGO "
                    + "DESC "
                    + "LIMIT 1");
            rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("CODIGO");
            }
            return 0;
        } catch (SQLException ex) {
            throw new SQLException(ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
    }

    /**
     * Pesquisa o faturamento
     *
     * @param p1 tipo da pesquisa 1 - CÓDIGO 2 - ORÇAMENTO 3 - OP 4 - DATA
     * LANÇAMENTO 5 - CLIENTE 6 - EMISSOR 7 - MOSTRAR ÚLTIMOS
     * @param vlrTxt valor da pesquisa em texto
     * @param vlrDate valor da pesquisa em Date
     * @return
     * @throws SQLException
     */
    public static List<Faturamento> pesqFat(byte p1, String vlrTxt, Date vlrDate) throws SQLException {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Faturamento> retorno = new ArrayList();

        try {
            switch (p1) {
                case 1:
                    stmt = con.prepareStatement("SELECT * "
                            + "FROM faturamentos "
                            + "WHERE CODIGO = ?");
                    stmt.setInt(1, Integer.valueOf(vlrTxt.toString()));
                    break;
                case 2:
                    stmt = con.prepareStatement("SELECT * "
                            + "FROM faturamentos "
                            + "WHERE CODIGO_ORC = ?");
                    stmt.setInt(1, Integer.valueOf(vlrTxt.toString()));
                    break;
                case 3:
                case 5:
                    stmt = con.prepareStatement("SELECT * "
                            + "FROM faturamentos "
                            + "WHERE CODIGO_OP = ?");
                    stmt.setInt(1, Integer.valueOf(vlrTxt.toString()));
                    break;
                case 4:
                    stmt = con.prepareStatement("SELECT * "
                            + "FROM faturamentos "
                            + "WHERE DT_FAT = ?");
                    stmt.setDate(1, new java.sql.Date(vlrDate.getTime()));
                    break;
                case 6:
                    stmt = con.prepareStatement("SELECT * "
                            + "FROM faturamentos "
                            + "WHERE EMISSOR = ?");
                    stmt.setString(1, vlrTxt);
                    break;
                case 7:
                    stmt = con.prepareStatement("SELECT * "
                            + "FROM faturamentos "
                            + "ORDER BY CODIGO DESC LIMIT 45");
                    break;
            }
            rs = stmt.executeQuery();
            while (rs.next()) {
                retorno.add(new Faturamento(
                        rs.getInt("CODIGO"),
                        rs.getInt("CODIGO_ORC"),
                        rs.getInt("CODIGO_OP"),
                        rs.getString("EMISSOR"),
                        rs.getInt("QTD_ENTREGUE"),
                        rs.getDouble("VLR_FAT"),
                        rs.getDate("DT_FAT"),
                        rs.getByte("FRETE_FAT"),
                        rs.getByte("SERVICOS_FAT"),
                        rs.getString("OBSERVACOES")
                ));
            }
        } catch (SQLException ex) {
            throw new SQLException(ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return retorno;
    }

    /**
     * Retorna recibos de entregas parciais, se houver
     *
     * @param codFat código do faturamento
     * @param codOp código da ordem de produção
     * @param codOrc código do orçamento
     * @return
     * @throws SQLException
     */
    public static List<Faturamento> retornaRecibos(int codFat, int codOrc, int codOp) throws SQLException {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Faturamento> retorno = new ArrayList();

        try {
            stmt = con.prepareStatement("SELECT * "
                    + "FROM faturamentos "
                    + "WHERE CODIGO != ? "
                    + "AND CODIGO_ORC = ? "
                    + "AND CODIGO_OP = ? "
                    + "ORDER BY CODIGO "
                    + "DESC");
            stmt.setInt(1, codFat);
            stmt.setInt(2, codOrc);
            stmt.setInt(3, codOp);
            rs = stmt.executeQuery();

            if (rs.wasNull()) {
                return null;
            }

            while (rs.next()) {
                retorno.add(new Faturamento(
                        rs.getInt("CODIGO"),
                        rs.getInt("CODIGO_ORC"),
                        rs.getInt("CODIGO_OP"),
                        rs.getString("EMISSOR"),
                        rs.getInt("QTD_ENTREGUE"),
                        rs.getDouble("VLR_FAT"),
                        rs.getDate("DT_FAT"),
                        rs.getByte("FRETE_FAT"),
                        rs.getByte("SERVICOS_FAT"),
                        rs.getString("OBSERVACOES")
                ));
            }
            return retorno;
        } catch (SQLException ex) {
            throw new SQLException(ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
    }

    //LANÇAMENTO SIGA/SIAFI-----------------------------------------------------
    
    /**
     * Exclui lançamento siga/siafi
     *
     * @param codNota código da nota
     * @throws SQLException
     */
    public static void excluiLanc(int codNota) throws SQLException {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("DELETE FROM nt_credito_lanc_siafi "
                    + "WHERE NT_CREDITO_CODIGO = ?");
            stmt.setInt(1, codNota);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            throw new SQLException(ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }

    /**
     * Cria lançamento siga/siafi
     *
     * @param lanc lançamento
     * @throws SQLException
     */
    public static void criaLanc(LancSigaSiafi lanc) throws SQLException {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("INSERT INTO nt_credito_lanc_siafi(NT_CREDITO_CODIGO,"
                    + "CPF_USR, NOME_USR, UG, DATA_HORA) "
                    + "VALUES(?,?,?,?,?)");
            stmt.setInt(1, lanc.getCodNota());
            stmt.setString(2, lanc.getCpfUsr());
            stmt.setString(3, lanc.getNomeUsr());
            stmt.setInt(4, lanc.getUg());
            stmt.setTimestamp(5, lanc.getDataHora());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            throw new SQLException(ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }

    /**
     * Atualiza lançamento siga/siafi
     *
     * @param lanc lançamento
     * @throws SQLException
     */
    public static void atualizaLanc(LancSigaSiafi lanc) throws SQLException {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("UPDATE nt_credito_lanc_siafi "
                    + "SET CPF_USR = ?, NOME_USR = ?, UG = ?, DATA_HORA = ? "
                    + "WHERE NT_CREDITO_CODIGO = ?");
            stmt.setString(1, lanc.getCpfUsr());
            stmt.setString(2, lanc.getNomeUsr());
            stmt.setInt(3, lanc.getUg());
            stmt.setTimestamp(4, lanc.getDataHora());
            stmt.setInt(5, lanc.getCodNota());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            throw new SQLException(ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }

    /**
     * Retorna lançamento
     *
     * @param codNota código da nota
     * @return
     * @throws SQLException
     */
    public static LancSigaSiafi retornaLanc(int codNota) throws SQLException {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = con.prepareStatement("SELECT * "
                    + "FROM nt_credito_lanc_siafi "
                    + "WHERE NT_CREDITO_CODIGO = ?");
            stmt.setInt(1, codNota);
            rs = stmt.executeQuery();
            if (rs.next()) {
                return new LancSigaSiafi(rs.getInt("NT_CREDITO_CODIGO"),
                        rs.getString("CPF_USR"),
                        rs.getString("NOME_USR"),
                        rs.getInt("UG"),
                        rs.getTimestamp("DATA_HORA"));
            }
            return null;
        } catch (SQLException ex) {
            throw new SQLException(ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
    }

    /**
     * Verifica se exite algum lançamento para a nota enviada
     *
     * @param codNota código da nota
     * @return
     * @throws SQLException
     */
    public static boolean verificaLanc(int codNota) throws SQLException {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = con.prepareStatement("SELECT NT_CREDITO_CODIGO "
                    + "FROM nt_credito_lanc_siafi "
                    + "WHERE NT_CREDITO_CODIGO = ?");
            stmt.setInt(1, codNota);
            rs = stmt.executeQuery();
            return rs.next();
        } catch (SQLException ex) {
            throw new SQLException(ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
    }

    /**
     * Verifica se existem lançamentos repetidos
     *
     * @param lanc lançamento
     * @return
     * @throws SQLException
     */
    public static int verificaLancRep(LancSigaSiafi lanc) throws SQLException {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = con.prepareStatement("SELECT NT_CREDITO_CODIGO "
                    + "FROM nt_credito_lanc_siafi "
                    + "WHERE CPF_USR = ? AND NOME_USR = ? AND UG = ? AND DATA_HORA = ?");
            stmt.setString(1, lanc.getCpfUsr());
            stmt.setString(2, lanc.getNomeUsr());
            stmt.setInt(3, lanc.getUg());
            stmt.setTimestamp(4, lanc.getDataHora());
            rs = stmt.executeQuery();
            if(rs.next()){
                return rs.getInt("NT_CREDITO_CODIGO");
            }
            return 0;
        } catch (SQLException ex) {
            throw new SQLException(ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
    }
    
    //LANÇAMENTO GRU------------------------------------------------------------
    
    /**
     * Exclui lançamento siga/siafi
     *
     * @param codNota código da nota
     * @throws SQLException
     */
    public static void excluiLancGru(int codNota) throws SQLException {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("DELETE FROM nt_credito_lanc_gru "
                    + "WHERE NT_CREDITO_CODIGO = ?");
            stmt.setInt(1, codNota);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            throw new SQLException(ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }

    /**
     * Cria lançamento siga/siafi
     *
     * @param lanc lançamento
     * @throws SQLException
     */
    public static void criaLancGru(LancGru lanc) throws SQLException {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("INSERT INTO nt_credito_lanc_gru(NT_CREDITO_CODIGO,"
                    + "CPF_USR, NOME_USR, CODIGO_REC, DATA_HORA) "
                    + "VALUES(?,?,?,?,?)");
            stmt.setInt(1, lanc.getCodNota());
            stmt.setString(2, lanc.getCpfUsr());
            stmt.setString(3, lanc.getNomeUsr());
            stmt.setInt(4, lanc.getCodRec());
            stmt.setTimestamp(5, lanc.getDataHora());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            throw new SQLException(ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }

    /**
     * Atualiza lançamento siga/siafi
     *
     * @param lanc lançamento
     * @throws SQLException
     */
    public static void atualizaLancGru(LancGru lanc) throws SQLException {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("UPDATE nt_credito_lanc_gru "
                    + "SET CPF_USR = ?, NOME_USR = ?, CODIGO_REC = ?, DATA_HORA = ? "
                    + "WHERE NT_CREDITO_CODIGO = ?");
            stmt.setString(1, lanc.getCpfUsr());
            stmt.setString(2, lanc.getNomeUsr());
            stmt.setInt(3, lanc.getCodRec());
            stmt.setTimestamp(4, lanc.getDataHora());
            stmt.setInt(5, lanc.getCodNota());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            throw new SQLException(ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }

    /**
     * Retorna lançamento
     *
     * @param codNota código da nota
     * @return
     * @throws SQLException
     */
    public static LancGru retornaLancGru(int codNota) throws SQLException {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = con.prepareStatement("SELECT * "
                    + "FROM nt_credito_lanc_gru "
                    + "WHERE NT_CREDITO_CODIGO = ?");
            stmt.setInt(1, codNota);
            rs = stmt.executeQuery();
            if (rs.next()) {
                return new LancGru(rs.getInt("NT_CREDITO_CODIGO"),
                        rs.getString("CPF_USR"),
                        rs.getString("NOME_USR"),
                        rs.getInt("CODIGO_REC"),
                        rs.getTimestamp("DATA_HORA"));
            }
            return null;
        } catch (SQLException ex) {
            throw new SQLException(ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
    }

    /**
     * Verifica se exite algum lançamento para a nota enviada
     *
     * @param codNota código da nota
     * @return
     * @throws SQLException
     */
    public static boolean verificaLancGru(int codNota) throws SQLException {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = con.prepareStatement("SELECT NT_CREDITO_CODIGO "
                    + "FROM nt_credito_lanc_gru "
                    + "WHERE NT_CREDITO_CODIGO = ?");
            stmt.setInt(1, codNota);
            rs = stmt.executeQuery();
            return rs.next();
        } catch (SQLException ex) {
            throw new SQLException(ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
    }

    /**
     * Verifica se existem lançamentos repetidos
     *
     * @param lanc lançamento
     * @return
     * @throws SQLException
     */
    public static int verificaLancRepGru(LancGru lanc) throws SQLException {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = con.prepareStatement("SELECT NT_CREDITO_CODIGO "
                    + "FROM nt_credito_lanc_gru "
                    + "WHERE CPF_USR = ? AND NOME_USR = ? AND CODIGO_REC = ? AND DATA_HORA = ?");
            stmt.setString(1, lanc.getCpfUsr());
            stmt.setString(2, lanc.getNomeUsr());
            stmt.setInt(3, lanc.getCodRec());
            stmt.setTimestamp(4, lanc.getDataHora());
            rs = stmt.executeQuery();
            if(rs.next()){
                return rs.getInt("NT_CREDITO_CODIGO");
            }
            return 0;
        } catch (SQLException ex) {
            throw new SQLException(ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
    }

}
