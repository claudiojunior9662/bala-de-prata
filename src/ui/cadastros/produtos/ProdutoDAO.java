/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.cadastros.produtos;

import ui.cadastros.papeis.PapelBEAN;
import connection.ConnectionFactory;
import entidades.Produto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import entidades.ProdOrcamento;

/**
 *
 * @author spd3
 */
public class ProdutoDAO {

    //PRODUTOS PRODUÇÃO---------------------------------------------------------
    /**
     * Cria produtos para produção
     *
     * @param produto produto a ser cadastrado
     * @throws SQLException
     */
    public static void cria(ProdutoBEAN produto) throws SQLException {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("INSERT INTO produtos(CODIGO, DESCRICAO, LARGURA, ALTURA, QTD_PAGINAS, TIPO, VENDAS) "
                    + "VALUES(?,?,?,?,?,?,?)");
            stmt.setInt(1, produto.getCodigo());
            stmt.setString(2, produto.getDescricao());
            stmt.setFloat(3, produto.getLargura());
            stmt.setFloat(4, produto.getAltura());
            stmt.setInt(5, produto.getQuantidadeFolhas());
            stmt.setString(6, produto.getTipoProduto());
            stmt.setInt(7, produto.getDisponivelVendas());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            throw new SQLException(ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }

    /**
     * Atualiza produtos para produção
     *
     * @param produto produto a ser atualizado
     * @throws SQLException
     */
    public static void atualiza(ProdutoBEAN produto) throws SQLException {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("UPDATE produtos SET "
                    + "DESCRICAO = ?,"
                    + "LARGURA = ?,"
                    + "ALTURA = ?,"
                    + "QTD_PAGINAS = ?,"
                    + "TIPO = ?,"
                    + "VENDAS = ? WHERE CODIGO = ?");
            stmt.setString(1, produto.getDescricao());
            stmt.setFloat(2, produto.getLargura());
            stmt.setFloat(3, produto.getAltura());
            stmt.setInt(4, produto.getQuantidadeFolhas());
            stmt.setString(5, produto.getTipoProduto());
            stmt.setInt(6, produto.getDisponivelVendas());
            stmt.setInt(7, Integer.valueOf(produto.getCodigo()));
            stmt.executeUpdate();
        } catch (SQLException ex) {
            throw new SQLException(ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }

    public static boolean verificaDescricao(String descricao) throws SQLException {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = con.prepareStatement("SELECT DESCRICAO FROM produtos WHERE DESCRICAO = ?");
            stmt.setString(1, descricao);
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

    public static int retornaUltimoRegistro() throws SQLException {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = con.prepareStatement("SELECT CODIGO FROM produtos ORDER BY CODIGO DESC LIMIT 1");
            rs = stmt.executeQuery();
            while (rs.next()) {
                return rs.getInt("CODIGO");
            }
            return 0;
        } catch (SQLException ex) {
            throw new SQLException(ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
    }

    public static List<ProdutoBEAN> pesquisaRegistro(String tipo, String texto) throws SQLException {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<ProdutoBEAN> cadastrolc = new ArrayList();

        try {
            if (tipo.equals("CÓDIGO")) {
                stmt = con.prepareStatement("SELECT CODIGO, DESCRICAO FROM produtos WHERE CODIGO = ?");
                stmt.setInt(1, Integer.valueOf(texto));
            } else if (tipo.equals("DESCRIÇÃO")) {
                stmt = con.prepareStatement("SELECT CODIGO, DESCRICAO FROM "
                        + "produtos WHERE DESCRICAO LIKE " + "'%" + texto + "%' ORDER BY CODIGO DESC LIMIT 45");
            }

            rs = stmt.executeQuery();

            while (rs.next()) {
                ProdutoBEAN lpBEAN = new ProdutoBEAN();
                lpBEAN.setCodigo(rs.getInt("CODIGO"));
                lpBEAN.setDescricao(rs.getString("DESCRICAO"));
                cadastrolc.add(lpBEAN);
            }
        } catch (SQLException ex) {
            throw new SQLException(ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return cadastrolc;
    }

    public static List<ProdutoBEAN> mostraTodos() throws SQLException {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<ProdutoBEAN> cadastrolc = new ArrayList();

        try {
            stmt = con.prepareStatement("SELECT CODIGO, DESCRICAO FROM produtos ORDER BY CODIGO DESC LIMIT 45");
            rs = stmt.executeQuery();
            while (rs.next()) {
                ProdutoBEAN lpBEAN = new ProdutoBEAN();
                lpBEAN.setCodigo(rs.getInt("CODIGO"));
                lpBEAN.setDescricao(rs.getString("DESCRICAO"));
                cadastrolc.add(lpBEAN);
            }
        } catch (SQLException ex) {
            throw new SQLException(ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return cadastrolc;
    }

    public void excluiRegistro(int cod) throws SQLException {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("DELETE FROM produtos WHERE CODIGO = ?");
            stmt.setInt(1, cod);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            throw new SQLException(ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }

    /**
     * Carrega os produtos do orçamento
     *
     * @param codOrcamento código do orçamento
     * @return
     * @throws SQLException
     */
    public static List<ProdOrcamento> carregaProdutosOrcamento(int codOrcamento) throws SQLException {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<ProdOrcamento> retorno = new ArrayList();

        try {
            stmt = con.prepareStatement("SELECT * "
                    + "FROM tabela_produtos_orcamento "
                    + "WHERE cod_orcamento = ?");
            stmt.setInt(1, codOrcamento);
            rs = stmt.executeQuery();
            while (rs.next()) {
                ProdOrcamento produtosOrcamentoBEAN = new ProdOrcamento();
                produtosOrcamentoBEAN.setCodProduto(rs.getInt("cod_produto"));
                produtosOrcamentoBEAN.setTipoProduto(rs.getByte("tipo_produto"));
                produtosOrcamentoBEAN.setDescricaoProduto(rs.getString("descricao_produto"));
                produtosOrcamentoBEAN.setQuantidade(rs.getInt("quantidade"));
                produtosOrcamentoBEAN.setObservacaoProduto(rs.getString("observacao_produto"));
                produtosOrcamentoBEAN.setPrecoUnitario(rs.getFloat("preco_unitario"));
                produtosOrcamentoBEAN.setValorDigital(rs.getDouble("valor_digital"));
                produtosOrcamentoBEAN.setMaquina(rs.getInt("maquina"));
                retorno.add(produtosOrcamentoBEAN);
            }
        } catch (SQLException ex) {
            throw new SQLException(ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return retorno;
    }

    /**
     * Retorna informações sobre o produto para produção
     *
     * @param codProduto código do produto
     * @param tipoProduto 1 - PERSONALIZADO (PP), 2 - PRONTA ENTREGA (PE), 3 -
     * INTERNET (PI)
     * @return
     * @throws SQLException
     */
    public static ProdutoBEAN retornaInfoProd(int codProduto, byte tipoProduto) throws SQLException {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            switch (tipoProduto) {
                case 1:
                    stmt = con.prepareStatement("SELECT * "
                            + "FROM produtos "
                            + "WHERE CODIGO = ?");
                    stmt.setInt(1, codProduto);
                    break;
                case 2:
                    stmt = con.prepareStatement("SELECT * "
                            + "FROM produtos_pr_ent "
                            + "WHERE CODIGO = ?");
                    stmt.setInt(1, codProduto);
                    break;
                case 3:
                    break;
            }

            rs = stmt.executeQuery();
            if (rs.next()) {
                return new ProdutoBEAN(
                        codProduto,
                        rs.getString("DESCRICAO"),
                        rs.getFloat("LARGURA"),
                        rs.getFloat("ALTURA"),
                        rs.getInt("QTD_PAGINAS"),
                        rs.getString("TIPO"),
                        rs.getInt("VENDAS")
                );
            }
            return null;
        } catch (SQLException ex) {
            throw new SQLException(ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
    }

    public Integer gramaturaPapel(int cod_papel) throws SQLException {
        int gramatura = 0;
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = con.prepareStatement("SELECT gramatura FROM tabela_papeis WHERE cod = ?");
            stmt.setInt(1, cod_papel);
            rs = stmt.executeQuery();
            while (rs.next()) {
                gramatura = rs.getInt("gramatura");
            }
        } catch (SQLException ex) {
            throw new SQLException(ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return gramatura;
    }

    public static Double retornaPrecoPapel(int codPapel) throws SQLException {
        Double preco = 0d;
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = con.prepareStatement("SELECT unitario FROM tabela_papeis WHERE cod = ?");
            stmt.setInt(1, codPapel);
            rs = stmt.executeQuery();
            while (rs.next()) {
                preco = rs.getDouble("unitario");
            }
            stmt.close();
            rs.close();
        } catch (SQLException ex) {
            throw new SQLException(ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return preco;
    }

    public static List<PapelBEAN> retornaInformacoesPapel(int codigoPapeis) throws SQLException {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<PapelBEAN> cadastrolp = new ArrayList();

        try {
            stmt = con.prepareStatement("SELECT * FROM tabela_papeis_produto WHERE cod_produto = ?");
            stmt.setInt(1, codigoPapeis);
            rs = stmt.executeQuery();
            while (rs.next()) {
                PapelBEAN lpBEAN = new PapelBEAN();
                lpBEAN.setCodPapel(rs.getInt("cod_papel"));
                lpBEAN.setTipo_papel(rs.getString("tipo_papel"));
                lpBEAN.setCor_frente(rs.getInt("cor_frente"));
                lpBEAN.setCor_verso(rs.getInt("cor_verso"));
                lpBEAN.setDescricaoPapel(rs.getString("descricao"));
                cadastrolp.add(lpBEAN);
            }
        } catch (SQLException ex) {
            throw new SQLException(ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return cadastrolp;
    }

    public static String retornaDescricaoPapel(int codPapel) throws SQLException {
        String retorno = null;
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = con.prepareStatement("SELECT descricao FROM tabela_papeis WHERE cod = ?");
            stmt.setInt(1, codPapel);
            rs = stmt.executeQuery();
            while (rs.next()) {
                retorno = rs.getString("descricao");
            }
        } catch (SQLException ex) {
            throw new SQLException(ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return retorno;
    }

    public float retornaOrelhaCapa(int codProduto, int codPapel) throws SQLException {
        float retorno = 0;
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = con.prepareStatement("SELECT orelha, tipo_papel FROM tabela_papeis_produto WHERE cod_produto = ? AND cod_papel = ?");
            stmt.setInt(1, codProduto);
            stmt.setInt(2, codPapel);
            rs = stmt.executeQuery();
            while (rs.next()) {
                retorno = rs.getFloat("orelha");
            }
        } catch (SQLException ex) {
            throw new SQLException(ex);
        }
        return retorno;
    }

    public int retornaQuantidade(int codProduto, int codOrcamento) throws SQLException {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int retorno = 0;

        try {
            stmt = con.prepareStatement("SELECT quantidade FROM tabela_produtos_orcamento WHERE cod_orcamento = ? AND cod_produto = ?");
            stmt.setInt(1, codOrcamento);
            stmt.setInt(2, codProduto);
            rs = stmt.executeQuery();
            while (rs.next()) {
                retorno = rs.getInt("quantidade");
            }
            stmt.close();
        } catch (SQLException ex) {
            throw new SQLException(ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return retorno;
    }

    public static String retornaTipoProduto(int codProduto) throws SQLException {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = con.prepareStatement("SELECT TIPO FROM produtos WHERE CODIGO = ?");
            stmt.setInt(1, codProduto);
            rs = stmt.executeQuery();
            while (rs.next()) {
                return rs.getString("TIPO");
            }
            return null;
        } catch (SQLException ex) {
            throw new SQLException(ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
    }

    public static float retornaPrecoChapa(int codChapa) throws SQLException {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        float preco = 0;

        try {
            stmt = con.prepareStatement("SELECT valor_unitario FROM tabela_chapas WHERE cod = ?");
            stmt.setInt(1, codChapa);
            rs = stmt.executeQuery();
            while (rs.next()) {
                preco = rs.getFloat("valor_unitario");
            }
        } catch (SQLException ex) {
            throw new SQLException(ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return preco;
    }

    public int retornaTipoMaquina(int codProduto, int codOrcamento) throws SQLException {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int retorno = 0;

        try {
            stmt = con.prepareStatement("SELECT maquina FROM tabela_produtos_orcamento WHERE cod_produto = ? AND cod_orcamento = ?");
            stmt.setInt(1, codProduto);
            stmt.setInt(2, codOrcamento);
            rs = stmt.executeQuery();
            while (rs.next()) {
                retorno = rs.getInt("maquina");
            }
            stmt.close();
        } catch (SQLException ex) {
            throw new SQLException(ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return retorno;
    }

    public static int retornaUsoProduto(int codProduto) throws SQLException {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int usoProduto = 0;

        try {
            stmt = con.prepareStatement("SELECT cod_produto FROM tabela_ordens_producao WHERE cod_produto = ?");
            stmt.setInt(1, codProduto);
            rs = stmt.executeQuery();
            while (rs.next()) {
                usoProduto = 1;
            }
            stmt.close();
        } catch (SQLException ex) {
            throw new SQLException(ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return usoProduto;
    }

    /*
    @param codProduto código do produto
    @return string descrição do produto
    @see retornaDescricaoProduto
     */
    public static String retornaDescricaoProduto(int codProduto, byte tipoProduto) throws SQLException {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            switch (tipoProduto) {
                case 1:
                    stmt = con.prepareStatement("SELECT DESCRICAO "
                            + "FROM produtos "
                            + "WHERE CODIGO = ?");
                    stmt.setInt(1, codProduto);
                    break;
                case 2:
                    stmt = con.prepareStatement("SELECT DESCRICAO "
                            + "FROM produtos_pr_ent "
                            + "WHERE CODIGO = ?");
                    stmt.setInt(1, codProduto);
                    break;
                case 3:

                    break;
            }
            rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getString("DESCRICAO");
            }
            return null;
        } catch (SQLException ex) {
            throw new SQLException(ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
    }

    public static List<String> retornaPesquisaProdutosAproximada(String descricaoProduto) throws SQLException {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List retorno = new ArrayList();
        retorno.clear();

        try {
            stmt = con.prepareStatement("SELECT DESCRICAO FROM produtos WHERE DESCRICAO LIKE '%"
                    + descricaoProduto + "%' ORDER BY DESCRICAO DESC LIMIT 5");
            rs = stmt.executeQuery();
            while (rs.next()) {
                retorno.add(rs.getString("DESCRICAO"));
            }
        } catch (SQLException ex) {
            throw new SQLException(ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return retorno;
    }

    public static Produto retornaProdutoRelatorio(String descricaoProduto) throws SQLException {
        Connection con = ConnectionFactory.getConnection();
        ResultSet rs = null;
        PreparedStatement stmt = null;

        Produto retorno = null;

        try {
            stmt = con.prepareStatement("SELECT CODIGO, DESCRICAO, TIPO FROM produtos WHERE DESCRICAO = ?");
            stmt.setString(1, descricaoProduto);
            rs = stmt.executeQuery();
            if (rs.next()) {
                retorno = new Produto();
                retorno.setCodigo(rs.getInt("CODIGO"));
                retorno.setDescricao(rs.getString("DESCRICAO"));
                retorno.setTipo(rs.getString("TIPO"));
                return retorno;
            }
        } catch (SQLException ex) {
            throw new SQLException(ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return retorno;
    }

    /*
    @param descricaoProduto descrição do produto
    @return List<String> lista de códigos de produtos aproximada com a descrição informada
    @see retornaCodigosProdutos
     */
    public static List<String> retornaCodigosProdutos(String descricaoProduto) throws SQLException {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List retorno = new ArrayList();

        try {
            stmt = con.prepareStatement("SELECT DISTINCT CODIGO FROM produtos WHERE DESCRICAO LIKE '%" + descricaoProduto + "%'");
            rs = stmt.executeQuery();
            while (rs.next()) {
                retorno.add(rs.getInt("CODIGO"));
            }
        } catch (SQLException ex) {
            throw new SQLException();
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return retorno;
    }

    /*
    @param codProduto Código do produto
    @return float altura do produto
    @see carregaLarguraProduto
     */
    public static float carregaLarguraProduto(int codProduto, byte tipoProduto) throws SQLException {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            switch (tipoProduto) {
                case 1:
                    stmt = con.prepareStatement("SELECT LARGURA "
                            + "FROM produtos "
                            + "WHERE CODIGO = ?");
                    stmt.setInt(1, Integer.valueOf(codProduto));
                    break;
                case 2:
                    stmt = con.prepareStatement("SELECT LARGURA "
                            + "FROM produtos_pr_ent "
                            + "WHERE CODIGO = ?");
                    stmt.setInt(1, codProduto);
                    break;
                case 3:
                    break;
            }

            rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getFloat("LARGURA");
            }
            return 0f;
        } catch (SQLException ex) {
            throw new SQLException(ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
    }

    /*
    @param codProduto Código do produto
    @return float altura do produto
    @see carregaAlturaProduto
     */
    public static float carregaAlturaProduto(int codProduto, byte tipoProduto) throws SQLException {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            switch (tipoProduto) {
                case 1:
                    stmt = con.prepareStatement("SELECT ALTURA "
                            + "FROM produtos "
                            + "WHERE CODIGO = ?");
                    stmt.setInt(1, Integer.valueOf(codProduto));
                    break;
                case 2:
                    stmt = con.prepareStatement("SELECT ALTURA "
                            + "FROM produtos_pr_ent "
                            + "WHERE CODIGO = ?");
                    stmt.setInt(1, codProduto);
                    break;
                case 3:
                    break;
            }

            rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getFloat("ALTURA");
            }
            return 0f;
        } catch (SQLException ex) {
            throw new SQLException(ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
    }

    /*
    @param codProduto Código do produto
    @return int quantidade de páginas
    @see retornaQuantidadePaginas
     */
    public static int retornaQuantidadePaginas(int codProduto, byte tipoProduto) throws SQLException {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            switch (tipoProduto) {
                case 1:
                    stmt = con.prepareStatement("SELECT QTD_PAGINAS "
                            + "FROM produtos "
                            + "WHERE CODIGO = ?");
                    stmt.setInt(1, Integer.valueOf(codProduto));
                    break;
                case 2:
                    stmt = con.prepareStatement("SELECT QTD_PAGINAS "
                            + "FROM produtos_pr_ent "
                            + "WHERE CODIGO = ?");
                    stmt.setInt(1, codProduto);
                    break;
                case 3:

                    break;
            }

            rs = stmt.executeQuery();
            while (rs.next()) {
                return rs.getInt("QTD_PAGINAS");
            }
            return 0;
        } catch (SQLException ex) {
            throw new SQLException(ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
    }

    /**
     * @param codProduto Código do produto
     * @return List altura e largura do produto
     * @throws java.sql.SQLException
     * @see selecionaDimensoesProduto
     */
    public synchronized static ProdutoBEAN selecionaDimensoesProduto(int codProduto) throws SQLException {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = con.prepareStatement("SELECT ALTURA, LARGURA "
                    + "FROM produtos "
                    + "WHERE CODIGO = ?");
            stmt.setInt(1, codProduto);
            rs = stmt.executeQuery();
            if (rs.next()) {
                return new ProdutoBEAN(rs.getFloat("LARGURA"), rs.getFloat("ALTURA"));
            }
            return null;
        } catch (SQLException ex) {
            throw new SQLException();
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
    }
    //-----------------------------------------------------------------------------------------------------------

    //produtos PRONTA ENTREGA------------------------------------------------------------------------------------
    /**
     * Insere produto pr ent
     *
     * @param prodPrEnt produto para pronta entrega
     * @throws java.sql.SQLException
     * @see inserePe
     */
    public static void inserePe(ProdutoPrEntBEAN prodPrEnt) throws SQLException {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("INSERT INTO produtos_pr_ent(CODIGO, DESCRICAO, LARGURA,"
                    + "ALTURA, ESPESSURA, PESO, VENDAS, PRE_VENDA, PROM, VLR_PROM, INICIO_PROM,"
                    + "FIM_PROM, QTD_PAGINAS, ESTOQUE, AVISO_ESTOQUE, AVISO_ESTOQUE_UN, TIPO,"
                    + "VLR_UNIT, ULT_MOV, PD_QTD_MIN, PD_MAX, PD_QTD_MAX) "
                    + "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            stmt.setInt(1, prodPrEnt.getCodigo());
            stmt.setString(2, prodPrEnt.getDescricao());
            stmt.setFloat(3, prodPrEnt.getLargura());
            stmt.setFloat(4, prodPrEnt.getAltura());
            stmt.setFloat(5, prodPrEnt.getEspessura());
            stmt.setFloat(6, prodPrEnt.getPeso());
            stmt.setByte(7, prodPrEnt.getVendas());
            stmt.setByte(8, prodPrEnt.getPreVenda());
            stmt.setByte(9, prodPrEnt.getPromocao());
            stmt.setDouble(10, prodPrEnt.getVlrPromocao());
            stmt.setDate(11, prodPrEnt.getPromocao() == 1
                    ? new java.sql.Date(prodPrEnt.getInicioPromocao().getTime())
                    : null);
            stmt.setDate(12, prodPrEnt.getPromocao() == 1
                    ? new java.sql.Date(prodPrEnt.getFimPromocao().getTime())
                    : null);
            stmt.setInt(13, prodPrEnt.getQtdPaginas());
            stmt.setInt(14, prodPrEnt.getEstoque());
            stmt.setByte(15, prodPrEnt.getAvisoEstoque());
            stmt.setInt(16, prodPrEnt.getAvisoEstoqueUn());
            stmt.setString(17, prodPrEnt.getTipo());
            stmt.setDouble(18, prodPrEnt.getVlrUnit());
            stmt.setTimestamp(19, prodPrEnt.getUltMov());
            stmt.setInt(20, prodPrEnt.getPdQtdMin());
            stmt.setByte(21, prodPrEnt.getPdMax());
            stmt.setInt(22, prodPrEnt.getPdQtdMax());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            throw new SQLException(ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }

    /**
     * Edita produto pr ent
     *
     * @param prodPrEnt produto pronta entrega para edição
     * @throws SQLException
     */
    public static void editaPe(ProdutoPrEntBEAN prodPrEnt) throws SQLException {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("UPDATE produtos_pr_ent SET DESCRICAO = ?, LARGURA = ?,"
                    + "ALTURA = ?, ESPESSURA = ?, PESO = ?, VENDAS = ?, PRE_VENDA = ?, PROM = ?,"
                    + "VLR_PROM = ?, INICIO_PROM = ?, FIM_PROM = ?, QTD_PAGINAS = ?, ESTOQUE = ?,"
                    + "AVISO_ESTOQUE = ?, AVISO_ESTOQUE_UN = ?, TIPO = ?, VLR_UNIT = ?, ULT_MOV = ?,"
                    + "PD_QTD_MIN = ?, PD_MAX = ?, PD_QTD_MAX = ? "
                    + "WHERE CODIGO = ?");
            stmt.setString(1, prodPrEnt.getDescricao());
            stmt.setFloat(2, prodPrEnt.getLargura());
            stmt.setFloat(3, prodPrEnt.getAltura());
            stmt.setFloat(4, prodPrEnt.getEspessura());
            stmt.setFloat(5, prodPrEnt.getPeso());
            stmt.setByte(6, prodPrEnt.getVendas());
            stmt.setByte(7, prodPrEnt.getPreVenda());
            stmt.setByte(8, prodPrEnt.getPromocao());
            stmt.setDouble(9, prodPrEnt.getVlrPromocao());
            stmt.setDate(10, prodPrEnt.getPromocao() == 1
                    ? new java.sql.Date(prodPrEnt.getInicioPromocao().getTime())
                    : null);
            stmt.setDate(11, prodPrEnt.getPromocao() == 1
                    ? new java.sql.Date(prodPrEnt.getFimPromocao().getTime())
                    : null);
            stmt.setInt(12, prodPrEnt.getQtdPaginas());
            stmt.setInt(13, prodPrEnt.getEstoque());
            stmt.setByte(14, prodPrEnt.getAvisoEstoque());
            stmt.setInt(15, prodPrEnt.getAvisoEstoqueUn());
            stmt.setString(16, prodPrEnt.getTipo());
            stmt.setDouble(17, prodPrEnt.getVlrUnit());
            stmt.setTimestamp(18, prodPrEnt.getUltMov());
            stmt.setInt(19, prodPrEnt.getPdQtdMin());
            stmt.setByte(20, prodPrEnt.getPdMax());
            stmt.setInt(21, prodPrEnt.getPdQtdMax());
            stmt.setInt(22, prodPrEnt.getCodigo());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            throw new SQLException(ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }

    /**
     * Pesquisa produto a pr ent
     *
     * @param tipo tipo de pesquisa em byte
     * @param pesquisa texto de pesquisa, em descricao ou código
     * @return List lista de produtos
     * @throws java.sql.SQLException
     * @see pesquisaPe
     */
    public static List<ProdutoPrEntBEAN> pesquisaPe(byte tipo, String pesquisa) throws SQLException {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<ProdutoPrEntBEAN> retorno = new ArrayList();

        try {
            switch (tipo) {
                case 1:
                    stmt = con.prepareStatement("SELECT CODIGO, DESCRICAO, VLR_UNIT, ESTOQUE, PRE_VENDA, PROM,"
                            + "AVISO_ESTOQUE, INICIO_PROM, FIM_PROM, VLR_PROM "
                            + " FROM produtos_pr_ent"
                            + " WHERE CODIGO = ? "
                            + "ORDER BY CODIGO DESC");
                    stmt.setString(1, pesquisa);
                    break;
                case 2:
                    stmt = con.prepareStatement("SELECT CODIGO, DESCRICAO, VLR_UNIT, ESTOQUE, PRE_VENDA, PROM,"
                            + "AVISO_ESTOQUE, INICIO_PROM, FIM_PROM, VLR_PROM "
                            + " FROM produtos_pr_ent"
                            + " WHERE DESCRICAO LIKE '%" + pesquisa + "%' "
                            + "ORDER BY CODIGO DESC");
                    break;
                case 3:
                    stmt = con.prepareStatement("SELECT CODIGO, DESCRICAO, VLR_UNIT, ESTOQUE, PRE_VENDA, PROM,"
                            + "AVISO_ESTOQUE, INICIO_PROM, FIM_PROM, VLR_PROM "
                            + "FROM produtos_pr_ent "
                            + "ORDER BY CODIGO DESC");
                    break;
                default:
                    break;
            }
            rs = stmt.executeQuery();
            while (rs.next()) {
                Double vlrUnit = 0d;
                if (rs.getByte("PROM") == 1) {
                    Date hoje = new Date();
                    if (hoje.after(rs.getDate("INICIO_PROM")) & hoje.before(rs.getDate("FIM_PROM"))) {
                        vlrUnit = rs.getDouble("VLR_PROM");
                    } else {
                        vlrUnit = rs.getDouble("VLR_UNIT");
                    }
                } else {
                    vlrUnit = rs.getDouble("VLR_UNIT");
                }

                retorno.add(new ProdutoPrEntBEAN(rs.getInt("CODIGO"),
                        rs.getString("DESCRICAO"),
                        rs.getByte("PRE_VENDA"),
                        rs.getByte("PROM"),
                        rs.getInt("ESTOQUE"),
                        rs.getByte("AVISO_ESTOQUE"),
                        vlrUnit
                ));
            }
            return retorno;
        } catch (SQLException ex) {
            throw new SQLException(ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }

    /**
     * Retorna o código do útlimo produto pr ent
     *
     * @return String codigo do último produto
     * @see retornaCodPe
     */
    public static int retornaCodPe() throws SQLException {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = con.prepareStatement("SELECT CODIGO "
                    + "FROM produtos_pr_ent "
                    + "ORDER BY CODIGO DESC "
                    + "LIMIT 1");
            rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("CODIGO") + 1;
            }
            return 0;
        } catch (SQLException ex) {
            throw new SQLException(ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
    }

    /**
     * Carrega edição pr ent
     *
     * @param codPe código do produto a pronta entrega
     * @return ProdutoBEAN produto a ser editado
     * @see retornaPeEdicao
     */
    public static ProdutoPrEntBEAN retornaPeEdicao(int codPe) throws SQLException {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = con.prepareStatement("SELECT * "
                    + "FROM produtos_pr_ent "
                    + "WHERE CODIGO = ?");
            stmt.setInt(1, codPe);
            rs = stmt.executeQuery();
            if (rs.next()) {
                return new ProdutoPrEntBEAN(
                        rs.getInt("CODIGO"),
                        rs.getString("DESCRICAO"),
                        rs.getFloat("LARGURA"),
                        rs.getFloat("ALTURA"),
                        rs.getFloat("ESPESSURA"),
                        rs.getFloat("PESO"),
                        rs.getByte("VENDAS"),
                        rs.getByte("PRE_VENDA"),
                        rs.getByte("PROM"),
                        rs.getDouble("VLR_PROM"),
                        rs.getDate("INICIO_PROM"),
                        rs.getDate("FIM_PROM"),
                        rs.getInt("QTD_PAGINAS"),
                        rs.getInt("ESTOQUE"),
                        rs.getByte("AVISO_ESTOQUE"),
                        rs.getInt("AVISO_ESTOQUE_UN"),
                        rs.getString("TIPO"),
                        rs.getDouble("VLR_UNIT"),
                        rs.getTimestamp("ULT_MOV"),
                        rs.getInt("PD_QTD_MIN"),
                        rs.getByte("PD_MAX"),
                        rs.getInt("PD_QTD_MAX")
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
     * Carrega produto pr ent para o orçamento
     *
     * @param codPe
     * @return ProdutoBEAN produto a ser carregado para o orçamento
     * @throws java.sql.SQLException
     * @see retornaInfoPe
     */
    public static ProdutoPrEntBEAN retornaInfoPe(int codPe) throws SQLException {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = con.prepareStatement("SELECT CODIGO, DESCRICAO, ALTURA, LARGURA, QTD_PAGINAS,"
                    + "ESTOQUE, VLR_UNIT, PROM, VLR_PROM, INICIO_PROM, FIM_PROM "
                    + "FROM produtos_pr_ent "
                    + "WHERE CODIGO = ?");
            stmt.setInt(1, codPe);
            rs = stmt.executeQuery();
            if (rs.next()) {
                Double vlrUnit = 0d;
                if (rs.getByte("PROM") == 1) {
                    Date hoje = new Date();
                    if (hoje.after(rs.getDate("INICIO_PROM")) & hoje.before(rs.getDate("FIM_PROM"))) {
                        vlrUnit = rs.getDouble("VLR_PROM");
                    } else {
                        vlrUnit = rs.getDouble("VLR_UNIT");
                    }
                } else {
                    vlrUnit = rs.getDouble("VLR_UNIT");
                }

                return new ProdutoPrEntBEAN(rs.getString("DESCRICAO"),
                        rs.getFloat("LARGURA"),
                        rs.getFloat("ALTURA"),
                        rs.getInt("QTD_PAGINAS"),
                        rs.getInt("ESTOQUE"),
                        vlrUnit);
            }
            return null;
        } catch (SQLException ex) {
            throw new SQLException(ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
    }

    /**
     * Retorna o valor do produto a pronta entrega
     *
     * @param codProd
     * @return
     * @throws SQLException
     */
    public synchronized static double retornaVlrPe(int codProd) throws SQLException {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = con.prepareStatement("SELECT VLR_UNIT, PROM, VLR_PROM, INICIO_PROM, FIM_PROM "
                    + "FROM produtos_pr_ent "
                    + "WHERE CODIGO = ?");
            stmt.setInt(1, codProd);
            rs = stmt.executeQuery();
            if (rs.next()) {
                if (rs.getByte("PROM") == 1) {
                    Date hoje = new Date();
                    if (hoje.after(rs.getDate("INICIO_PROM")) & hoje.before(rs.getDate("FIM_PROM"))) {
                        return rs.getDouble("VLR_PROM");
                    }
                }
                return rs.getDouble("VLR_UNIT");
            }
            return 0d;
        } catch (SQLException ex) {
            throw new SQLException(ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
    }

    /**
     * Verifica o estoque para orçamento produtos pr ent
     *
     * @param codProdPe código produto pronta entrega
     * @param qtdSol quantidade solicitada pelo orçamento
     * @return
     * @throws SQLException
     */
    public synchronized static boolean verificaEstoque(int codProdPe, int qtdSol) throws SQLException {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = con.prepareStatement("SELECT ESTOQUE "
                    + "FROM produtos_pr_ent "
                    + "WHERE CODIGO = ?");
            stmt.setInt(1, codProdPe);
            rs = stmt.executeQuery();
            if (rs.next()) {
                if (qtdSol > rs.getInt("ESTOQUE")) {
                    return true;
                } else {
                    return false;
                }
            }
            return false;
        } catch (SQLException ex) {
            throw new SQLException(ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
    }

    /**
     * Movimenta o estoque de produtos pr ent
     *
     * @param codProdPe código produto pr ent
     * @param qtdMov quantidade movimentada
     * @param operacao 1 - RETIRADA, 2 - CHEGADA
     * @throws SQLException
     */
    public synchronized static void movEst(int codProdPe, int qtdMov, byte operacao) throws SQLException {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            switch (operacao) {
                case 1:
                    stmt = con.prepareStatement("UPDATE produtos_pr_ent "
                            + "SET ESTOQUE = ESTOQUE - ?, ULT_MOV = ? "
                            + "WHERE CODIGO = ?");
                    stmt.setInt(1, qtdMov);
                    stmt.setTimestamp(2, new java.sql.Timestamp(new Date().getTime()));
                    stmt.setInt(3, codProdPe);
                    break;
                case 2:
                    stmt = con.prepareStatement("UPDATE produtos_pr_ent "
                            + "SET ESTOQUE = ESTOQUE + ?, ULT_MOV = ? "
                            + "WHERE CODIGO = ?");
                    stmt.setInt(1, qtdMov);
                    stmt.setTimestamp(2, new java.sql.Timestamp(new Date().getTime()));
                    stmt.setInt(3, codProdPe);
                    break;
            }
            stmt.executeUpdate();
        } catch (SQLException ex) {
            throw new SQLException(ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }

    }

    /**
     * Verifica se existe algum produto com estoque abaixo do limite e retorna
     * uma lista
     *
     * @return
     * @throws SQLException
     */
    public synchronized static List<ProdutoPrEntBEAN> retornaAvisoEstoquePe() throws SQLException {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<ProdutoPrEntBEAN> retorno = new ArrayList();

        try {
            stmt = con.prepareStatement("SELECT CODIGO, DESCRICAO, ESTOQUE "
                    + "FROM produtos_pr_ent "
                    + "WHERE AVISO_ESTOQUE = 1 AND ESTOQUE <= AVISO_ESTOQUE_UN");
            rs = stmt.executeQuery();
            while (rs.next()) {
                retorno.add(new ProdutoPrEntBEAN(
                        rs.getInt("CODIGO"),
                        rs.getString("DESCRICAO"),
                        rs.getInt("ESTOQUE")
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
     * Verifica o uso de produtos em orçamentos para habilitar ou não sua edição
     *
     * @param codProd código do produto
     * @return
     * @throws SQLException
     */
    public synchronized static boolean verificaUsoProdPe(int codProd) throws SQLException {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = con.prepareStatement("SELECT cod_produto "
                    + "FROM tabela_produtos_orcamento "
                    + "WHERE cod_produto = ? "
                    + "ORDER BY cod_produto "
                    + "DESC LIMIT 1");
            stmt.setInt(1, codProd);
            rs = stmt.executeQuery();
            return rs.next();
        } catch (SQLException ex) {
            throw new SQLException(ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
    }

    /**
     * Verifica se existe um produto com descrição igual já cadastrado
     *
     * @param descricaoProd descrição do produto
     * @return
     * @throws SQLException
     */
    public synchronized static boolean verificaDescPe(String descricaoProd) throws SQLException {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = con.prepareStatement("SELECT DESCRICAO "
                    + "FROM produtos_pr_ent "
                    + "WHERE DESCRICAO = ?");
            stmt.setString(1, descricaoProd);
            rs = stmt.executeQuery();
            return rs.next();
        } catch (SQLException ex) {
            throw new SQLException(ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
    }

    /**
     * Retorna a descrição do produto
     *
     * @param codProd código do produto
     * @return
     * @throws SQLException
     */
    public synchronized static String retornaDescPe(String codProd) throws SQLException {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = con.prepareStatement("SELECT DESCRICAO "
                    + "FROM produtos_pr_ent "
                    + "WHERE CODIGO = ?");
            stmt.setString(1, codProd);
            rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getString("DESCRICAO");
            }
            return null;
        } catch (SQLException ex) {
            throw new SQLException(ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
    }

    public synchronized static void atualizaEstPe(ProdutoPrEntBEAN produto) throws SQLException {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = con.prepareStatement("UPDATE produtos_pr_ent "
                    + "SET ESTOQUE = ?, AVISO_ESTOQUE = ?, AVISO_ESTOQUE_UN = ?, ULT_MOV = ? "
                    + "WHERE CODIGO = ?");
            stmt.setInt(1, produto.getEstoque());
            stmt.setByte(2, produto.getAvisoEstoque());
            stmt.setInt(3, produto.getAvisoEstoqueUn());
            stmt.setTimestamp(4, new java.sql.Timestamp(new Date().getTime()));
            stmt.setInt(5, produto.getCodigo());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            throw new SQLException(ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
    }

    /**
     * Seleciona as dimensões do produto
     *
     * @param codProduto Código do produto
     * @return List altura e largura do produto
     * @throws java.sql.SQLException
     * @see selecionaDimensoesProduto
     */
    public synchronized static ProdutoPrEntBEAN selDimProdPrEnt(int codProduto) throws SQLException {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = con.prepareStatement("SELECT ALTURA, LARGURA, ESPESSURA, PESO "
                    + "FROM produtos_pr_ent "
                    + "WHERE CODIGO = ?");
            stmt.setInt(1, codProduto);
            rs = stmt.executeQuery();
            if (rs.next()) {
                return new ProdutoPrEntBEAN(rs.getFloat("ALTURA"),
                        rs.getFloat("LARGURA"),
                        rs.getFloat("ESPESSURA"),
                        rs.getFloat("PESO"));
            }
            return null;
        } catch (SQLException ex) {
            throw new SQLException();
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
    }
    //--------------------------------------------------------------------------

    public synchronized static String traduzCodProd(int codProd, byte tipoProd) {
        switch (tipoProd) {
            case 1:
                return "PP" + codProd;
            case 2:
                return "PE" + codProd;
            case 3:
                return "PI" + codProd;
            default:
                return null;
        }
    }
}
