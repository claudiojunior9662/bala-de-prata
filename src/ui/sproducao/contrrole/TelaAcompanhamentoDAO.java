/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.sproducao.contrrole;

import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import entidades.OrdemProducao;
import java.util.Date;
import entidades.ProdOrcamento;
import model.bean.TelaAcompanhamentoBEAN;
import ui.cadastros.produtos.ProdutoDAO;

/**
 *
 * @author spd3
 */
public class TelaAcompanhamentoDAO {

    /**
     * Carrega as últimas 45 ordens de produção
     *
     * @return
     * @throws SQLException
     */
    public static List<TelaAcompanhamentoBEAN> refreshTabela(int limite) throws SQLException {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<TelaAcompanhamentoBEAN> retorno = new ArrayList();

        try {
            stmt = con.prepareStatement("SELECT cod, data_entrega, status, cod_produto, tipo_produto "
                    + "FROM tabela_ordens_producao "
                    + "ORDER BY tabela_ordens_producao.cod "
                    + "DESC LIMIT " + limite);
            rs = stmt.executeQuery();
            while (rs.next()) {
                TelaAcompanhamentoBEAN telaAcompanhamentoBEAN = new TelaAcompanhamentoBEAN();
                telaAcompanhamentoBEAN.setNumero(rs.getInt("cod"));
                telaAcompanhamentoBEAN.setDataEntrega(rs.getDate("data_entrega"));
                telaAcompanhamentoBEAN.setStatus(rs.getString("status"));
                telaAcompanhamentoBEAN.setDescricaoProduto(
                        ProdutoDAO.retornaDescricaoProduto(rs.getInt("cod_produto"), rs.getByte("tipo_produto")));
                retorno.add(telaAcompanhamentoBEAN);
            }
        } catch (SQLException ex) {
            throw new SQLException(ex);
        }
        return retorno;
    }

    /**
     * Atualiza os dados da ordem de produção
     * @param op ordem de produção
     * @throws SQLException 
     */
    public static void atualizaDadosOp(OrdemProducao op) throws SQLException {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = con.prepareStatement("UPDATE tabela_ordens_producao "
                    + "SET  ind_ent_prazo=?, ind_ent_erro=?,"
                    + "op_secao=?, status=?, data_entrega = ?, tipo_trabalho = ? "
                    + "WHERE cod = ?");
            stmt.setInt(1, op.getIndEntPrazo());
            stmt.setInt(2, op.getIndEntErro());
            stmt.setString(3, op.getOpSecao());
            stmt.setString(4, op.getStatus());
            stmt.setDate(5, new java.sql.Date(op.getDataEntrega().getTime()));
            stmt.setString(6, op.getTipoTrabalho());
            stmt.setInt(7, op.getCodigo());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            throw new SQLException(ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
    }

    /**
     * Retorna lista de ordem de produção ao aplicar o filtro
     * @param cod código da ordem de produção
     * @param data data aplicada
     * @param mesEmissao mês de emissão
     * @param anoEmissao ano de emissão
     * @param mesEntrega mês de entrega
     * @param anoEntrega ano de entrega
     * @param status status da op
     * @param descProduto descrição do produto
     * @return
     * @throws SQLException 
     */
    public static List<OrdemProducao> retornaFiltro(Long cod,
            Date data,
            String mesEmissao,
            String anoEmissao,
            String mesEntrega,
            String anoEntrega,
            String status,
            String descProduto) throws SQLException {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<OrdemProducao> retorno = new ArrayList();

        try {
            if (cod != 0 & data == null & status == null & descProduto == null & mesEmissao == null & anoEmissao == null & mesEntrega == null & anoEntrega == null) {
                stmt = con.prepareStatement("SELECT cod, data_entrega, status, cod_produto, tipo_produto "
                        + "FROM tabela_ordens_producao "
                        + "WHERE cod = ? "
                        + "ORDER BY cod "
                        + "DESC LIMIT 2");
                stmt.setLong(1, cod);
                rs = stmt.executeQuery();
                while (rs.next()) {
                    OrdemProducao op = new OrdemProducao();
                    op.setCodigo(rs.getInt("cod"));
                    op.setDataEntrega(rs.getDate("data_entrega"));
                    op.setStatus(rs.getString("status"));
                    op.setCodProduto(rs.getInt("cod_produto"));
                    op.setTipoProduto(rs.getByte("tipo_produto"));
                    retorno.add(op);
                }
            } else if (cod == 0 && data != null && status == null && descProduto == null & mesEmissao == null & anoEmissao == null & mesEntrega == null & anoEntrega == null) {
                stmt = con.prepareStatement("SELECT cod, data_entrega, status, cod_produto, tipo_produto "
                        + "FROM tabela_ordens_producao "
                        + "WHERE data_entrega = ?");
                stmt.setDate(1, new java.sql.Date((data).getTime()));
                rs = stmt.executeQuery();
                while (rs.next()) {
                    OrdemProducao op = new OrdemProducao();
                    op.setCodigo(rs.getInt("cod"));
                    op.setDataEntrega(rs.getDate("data_entrega"));
                    op.setStatus(rs.getString("status"));
                    op.setCodProduto(rs.getInt("cod_produto"));
                    op.setTipoProduto(rs.getByte("tipo_produto"));
                    retorno.add(op);
                }
            } else if (cod == 0 && data == null && status != null && descProduto == null & mesEmissao == null & anoEmissao == null & mesEntrega == null & anoEntrega == null) {
                stmt = con.prepareStatement("SELECT cod, data_entrega, status, cod_produto, tipo_produto "
                        + "FROM tabela_ordens_producao "
                        + "WHERE status = ?");
                stmt.setString(1, status);
                rs = stmt.executeQuery();
                while (rs.next()) {
                    OrdemProducao op = new OrdemProducao();
                    op.setCodigo(rs.getInt("cod"));
                    op.setDataEntrega(rs.getDate("data_entrega"));
                    op.setStatus(rs.getString("status"));
                    op.setCodProduto(rs.getInt("cod_produto"));
                    op.setTipoProduto(rs.getByte("tipo_produto"));
                    retorno.add(op);
                }
            } else if (cod == 0 && data == null && status == null && descProduto == null & mesEmissao == null & anoEmissao == null & mesEntrega == null & anoEntrega == null) {
                stmt = con.prepareStatement("SELECT cod, data_entrega, status, cod_produto, tipo_produto "
                        + "FROM tabela_ordens_producao");
                rs = stmt.executeQuery();
                while (rs.next()) {
                    OrdemProducao op = new OrdemProducao();
                    op.setCodigo(rs.getInt("cod"));
                    op.setDataEntrega(rs.getDate("data_entrega"));
                    op.setStatus(rs.getString("status"));
                    op.setCodProduto(rs.getInt("cod_produto"));
                    op.setTipoProduto(rs.getByte("tipo_produto"));
                    retorno.add(op);
                }
            } else if (cod == 0 && data == null && status == null && descProduto != null & mesEmissao == null & anoEmissao == null & mesEntrega == null & anoEntrega == null) {
                List aux = new ArrayList();
                aux = ProdutoDAO.retornaCodigosProdutos(descProduto);
                for (int i = 0; i < aux.size(); i++) {
                    stmt = con.prepareStatement("SELECT cod, data_entrega, status, cod_produto, tipo_produto "
                            + "FROM tabela_ordens_producao "
                            + "WHERE cod_produto = ? "
                            + "ORDER BY cod "
                            + "DESC");
                    stmt.setInt(1, Integer.parseInt(aux.get(i).toString()));
                    rs = stmt.executeQuery();
                    while (rs.next()) {
                        if (rs.getInt("cod") >= 364) {
                            OrdemProducao op = new OrdemProducao();
                            op.setCodigo(rs.getInt("cod"));
                            op.setDataEntrega(rs.getDate("data_entrega"));
                            op.setStatus(rs.getString("status"));
                            op.setCodProduto(rs.getInt("cod_produto"));
                            op.setTipoProduto(rs.getByte("tipo_produto"));
                            retorno.add(op);
                        }
                    }
                }
            } else if (cod == 0 & data == null & status == null & descProduto == null & mesEmissao != null & anoEmissao != null & mesEntrega == null & anoEntrega == null) {
                stmt = con.prepareStatement("SELECT cod, data_entrega, status, cod_produto, tipo_produto "
                        + "FROM tabela_ordens_producao "
                        + "WHERE MONTH(data_emissao) = ? "
                        + "AND YEAR(data_emissao) = ? "
                        + "ORDER BY cod "
                        + "ASC");
                stmt.setString(1, mesEmissao);
                stmt.setString(2, anoEmissao);
                rs = stmt.executeQuery();
                while (rs.next()) {
                    OrdemProducao op = new OrdemProducao();
                    op.setCodigo(rs.getInt("cod"));
                    op.setDataEntrega(rs.getDate("data_entrega"));
                    op.setStatus(rs.getString("status"));
                    op.setCodProduto(rs.getInt("cod_produto"));
                    op.setTipoProduto(rs.getByte("tipo_produto"));
                    retorno.add(op);
                }

            } else if (cod == 0 & data == null & status == null & descProduto == null & mesEmissao == null & anoEmissao == null & mesEntrega != null & anoEntrega != null) {
                stmt = con.prepareStatement("SELECT cod, data_entrega, status, cod_produto, tipo_produto "
                        + "FROM tabela_ordens_producao "
                        + "WHERE MONTH(data_entrega) = ? "
                        + "AND YEAR(data_entrega) = ? "
                        + "ORDER BY cod "
                        + "ASC");
                stmt.setString(1, mesEntrega);
                stmt.setString(2, anoEntrega);
                rs = stmt.executeQuery();
                while (rs.next()) {
                    OrdemProducao op = new OrdemProducao();
                    op.setCodigo(rs.getInt("cod"));
                    op.setDataEntrega(rs.getDate("data_entrega"));
                    op.setStatus(rs.getString("status"));
                    op.setCodProduto(rs.getInt("cod_produto"));
                    op.setTipoProduto(rs.getByte("tipo_produto"));
                    retorno.add(op);
                }

            }
            return retorno;
        } catch (SQLException ex) {
            throw new SQLException(ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        } 
    }

    public List<ProdOrcamento> retornaProdutos(int orcamentoBase) throws SQLException {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<ProdOrcamento> poBEAN = new ArrayList();

        try {
            stmt = con.prepareStatement("SELECT cod_produto, tipo_produto "
                    + "FROM tabela_produtos_orcamento "
                    + "WHERE cod_orcamento = ?");
            stmt.setInt(1, orcamentoBase);
            rs = stmt.executeQuery();
            while (rs.next()) {
                ProdOrcamento poAUX = new ProdOrcamento();
                poAUX.setCodProduto(rs.getInt("cod_produto"));
                poAUX.setTipoProduto(rs.getByte("tipo_produto"));
                poBEAN.add(poAUX);
            }
        } catch (SQLException ex) {
            throw new SQLException(ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return poBEAN;
    }

    public String tipoTrabalho(int orcamentoBase, int codProduto) throws SQLException {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String retorno = null;

        try {
            stmt = con.prepareStatement("SELECT tipo_trabalho FROM tabela_produtos_orcamento WHERE cod_orcamento = ? AND cod_produto = ?");
            stmt.setInt(1, orcamentoBase);
            stmt.setInt(2, codProduto);
            rs = stmt.executeQuery();
            while (rs.next()) {
                retorno = rs.getString("tipo_trabalho");
            }
        } catch (SQLException ex) {
            throw new SQLException();
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        System.out.println(retorno);
        return retorno;
    }

    /**
     * Define o tipo de trabalho
     * @param codOrc código do orçamento
     * @param codProd código do produto
     * @param tipoTrabalho tipo de trabalho
     * @return
     * @throws SQLException 
     */
    public static int setTipoTrabalho(int codOrc, 
            int codProd, 
            String tipoTrabalho) throws SQLException {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int retorno = 0;

        try {
            stmt = con.prepareStatement("UPDATE tabela_produtos_orcamento "
                    + "SET tipo_trabalho = ? "
                    + "WHERE cod_orcamento = ? "
                    + "AND cod_produto = ?");
            stmt.setString(1, tipoTrabalho);
            stmt.setInt(2, codOrc);
            stmt.setInt(3, codProd);
            stmt.executeUpdate();
            retorno = 1;
        } catch (SQLException ex) {
            throw new SQLException(ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return retorno;
    }

}
