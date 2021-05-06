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
import entities.sisgrafex.OrdemProducao;
import java.util.Date;
import entities.sisgrafex.ProdOrcamento;
import java.time.Instant;
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
            stmt = con.prepareStatement("SELECT cod, data_emissao, data_entrega, status, cod_produto, tipo_produto "
                    + "FROM tabela_ordens_producao "
                    + "ORDER BY tabela_ordens_producao.cod "
                    + "DESC LIMIT " + limite);
            rs = stmt.executeQuery();
            while (rs.next()) {
                TelaAcompanhamentoBEAN telaAcompanhamentoBEAN = new TelaAcompanhamentoBEAN();
                telaAcompanhamentoBEAN.setNumero(rs.getInt("cod"));
                telaAcompanhamentoBEAN.setDataEmissao(rs.getDate("data_emissao"));
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
     *
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
     *
     * @param param
     * @param tipoFiltro 0 - TUDO, 1 - CÓDIGO DA OP, 2 - CÓDIGO DO ORÇAMENTO, 3
     * - DATA DE ENTREGA, 4 - MÊS DE EMISSÃO, 5 - MÊS DE ENTREGA, 6 - STATUS
     * @return
     * @throws SQLException
     */
    public static List<OrdemProducao> retornaFiltro(
            Object param,
            byte tipoFiltro) throws SQLException {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<OrdemProducao> retorno = new ArrayList();
        java.util.Date dataFiltro = null;

        try {
            switch (tipoFiltro) {
                //TUDO
                case 0:
                    stmt = con.prepareStatement("SELECT cod, data_emissao, data_entrega, status, cod_produto, tipo_produto "
                            + "FROM tabela_ordens_producao");
                    rs = stmt.executeQuery();
                    while (rs.next()) {
                        OrdemProducao op = new OrdemProducao();
                        op.setCodigo(rs.getInt("cod"));
                        op.setDataEmissao(rs.getDate("data_emissao"));
                        op.setDataEntrega(rs.getDate("data_entrega"));
                        op.setStatus(rs.getString("status"));
                        op.setCodProduto(rs.getInt("cod_produto"));
                        op.setTipoProduto(rs.getByte("tipo_produto"));
                        retorno.add(op);
                    }
                    break;
                //POR CÓDIGO DA OP
                case 1:
                    stmt = con.prepareStatement("SELECT cod, data_emissao, data_entrega, status, cod_produto, tipo_produto "
                            + "FROM tabela_ordens_producao "
                            + "WHERE cod = ? "
                            + "ORDER BY cod "
                            + "DESC LIMIT 1");
                    stmt.setLong(1, (Long) param);
                    rs = stmt.executeQuery();
                    while (rs.next()) {
                        OrdemProducao op = new OrdemProducao();
                        op.setCodigo(rs.getInt("cod"));
                        op.setDataEmissao(rs.getDate("data_emissao"));
                        op.setDataEntrega(rs.getDate("data_entrega"));
                        op.setStatus(rs.getString("status"));
                        op.setCodProduto(rs.getInt("cod_produto"));
                        op.setTipoProduto(rs.getByte("tipo_produto"));
                        retorno.add(op);
                    }
                    break;
                //POR CÓDIGO DO ORÇAMENTO
                case 2:
                    stmt = con.prepareStatement("SELECT cod, data_emissao, data_entrega, status, cod_produto, tipo_produto "
                            + "FROM tabela_ordens_producao "
                            + "WHERE orcamento_base = ? "
                            + "ORDER BY cod "
                            + "DESC");
                    stmt.setLong(1, (Long) param);
                    rs = stmt.executeQuery();
                    while (rs.next()) {
                        OrdemProducao op = new OrdemProducao();
                        op.setCodigo(rs.getInt("cod"));
                        op.setDataEmissao(rs.getDate("data_emissao"));
                        op.setDataEntrega(rs.getDate("data_entrega"));
                        op.setStatus(rs.getString("status"));
                        op.setCodProduto(rs.getInt("cod_produto"));
                        op.setTipoProduto(rs.getByte("tipo_produto"));
                        retorno.add(op);
                    }
                    break;
                //POR DATA DE ENTREGA
                case 3:
                    stmt = con.prepareStatement("SELECT cod, data_emissao, data_entrega, status, cod_produto, tipo_produto "
                            + "FROM tabela_ordens_producao "
                            + "WHERE data_entrega = ?");
                    stmt.setDate(1, new java.sql.Date(new java.util.Date((Long) param).getTime()));
                    rs = stmt.executeQuery();
                    while (rs.next()) {
                        OrdemProducao op = new OrdemProducao();
                        op.setCodigo(rs.getInt("cod"));
                        op.setDataEmissao(rs.getDate("data_emissao"));
                        op.setDataEntrega(rs.getDate("data_entrega"));
                        op.setStatus(rs.getString("status"));
                        op.setCodProduto(rs.getInt("cod_produto"));
                        op.setTipoProduto(rs.getByte("tipo_produto"));
                        retorno.add(op);
                    }

                    break;
                //POR MÊS DE EMISSÃO
                case 4:
                    stmt = con.prepareStatement("SELECT cod, data_emissao, data_entrega, status, cod_produto, tipo_produto "
                            + "FROM tabela_ordens_producao "
                            + "WHERE MONTH(data_emissao) = ? "
                            + "AND YEAR(data_emissao) = ? "
                            + "ORDER BY cod "
                            + "ASC");
                    dataFiltro = (Date) param;
                    stmt.setInt(1, dataFiltro.getMonth() + 1);
                    stmt.setInt(2, dataFiltro.getYear());
                    rs = stmt.executeQuery();
                    while (rs.next()) {
                        OrdemProducao op = new OrdemProducao();
                        op.setCodigo(rs.getInt("cod"));
                        op.setDataEmissao(rs.getDate("data_emissao"));
                        op.setDataEntrega(rs.getDate("data_entrega"));
                        op.setStatus(rs.getString("status"));
                        op.setCodProduto(rs.getInt("cod_produto"));
                        op.setTipoProduto(rs.getByte("tipo_produto"));
                        retorno.add(op);
                    }
                    break;
                //POR MÊS DE ENTREGA
                case 5:
                    stmt = con.prepareStatement("SELECT cod, data_emissao, data_entrega, status, cod_produto, tipo_produto "
                            + "FROM tabela_ordens_producao "
                            + "WHERE MONTH(data_entrega) = ? "
                            + "AND YEAR(data_entrega) = ? "
                            + "ORDER BY cod "
                            + "ASC");
                    dataFiltro = (Date) param;
                    stmt.setInt(1, dataFiltro.getMonth() + 1);
                    stmt.setInt(2, dataFiltro.getYear());
                    rs = stmt.executeQuery();
                    while (rs.next()) {
                        OrdemProducao op = new OrdemProducao();
                        op.setCodigo(rs.getInt("cod"));
                        op.setDataEmissao(rs.getDate("data_emissao"));
                        op.setDataEntrega(rs.getDate("data_entrega"));
                        op.setStatus(rs.getString("status"));
                        op.setCodProduto(rs.getInt("cod_produto"));
                        op.setTipoProduto(rs.getByte("tipo_produto"));
                        retorno.add(op);
                    }
                    break;
                //POR STATUS
                case 6:
                    stmt = con.prepareStatement("SELECT cod, data_emissao, data_entrega, status, cod_produto, tipo_produto "
                            + "FROM tabela_ordens_producao "
                            + "WHERE status = ?");
                    stmt.setString(1, param.toString());
                    rs = stmt.executeQuery();
                    while (rs.next()) {
                        OrdemProducao op = new OrdemProducao();
                        op.setCodigo(rs.getInt("cod"));
                        op.setDataEmissao(rs.getDate("data_emissao"));
                        op.setDataEntrega(rs.getDate("data_entrega"));
                        op.setStatus(rs.getString("status"));
                        op.setCodProduto(rs.getInt("cod_produto"));
                        op.setTipoProduto(rs.getByte("tipo_produto"));
                        retorno.add(op);
                    }
                    break;
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
     *
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
