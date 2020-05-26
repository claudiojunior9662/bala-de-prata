
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.dao.OrcamentoDAO;
import ui.cadastros.clientes.ClienteDAO;

/**
 *
 * @author claudio
 */
public class NewMain {

    final static private String driver = "org.firebirdsql.jdbc.FBDriver";
    static String CHARSET = "?encoding=ISO8859_1";
    static String SERVIDOR = "10.67.32.111";
    static String PORTA = "3050";
    static String USUARIO = "sysdba";
    static String SENHA = "masterkey";

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        mostraValorProducao();
    }

    //--------------------------------------------------------------------------
    private static void mostraCredito() {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ResultSet rs2 = null;
        double credito;
        DecimalFormat df = new DecimalFormat("###,##0.00");

        try {
            stmt = con.prepareStatement("SELECT cod, nome "
                    + "FROM tabela_clientes_juridicos ");
            rs = stmt.executeQuery();
            while (rs.next()) {
                credito = 0d;
//                stmt = con.prepareStatement("SELECT valor "
//                        + "FROM tabela_notas "
//                        + "WHERE DATE_FORMAT(STR_TO_DATE(`data`, '%d/%m/%Y'), '%Y-%m-%d') BETWEEN "
//                        + "DATE_FORMAT(STR_TO_DATE('01/05/2020', '%d/%m/%Y'), '%Y-%m-%d') AND "
//                        + "DATE_FORMAT(STR_TO_DATE('26/05/2020', '%d/%m/%Y'), '%Y-%m-%d') AND "
//                        + "cod_cliente = ? AND tipo_pessoa = 2");
//                stmt.setInt(1, rs.getInt("cod"));
//                rs2 = stmt.executeQuery();
//                while (rs2.next()) {
//                    credito += rs2.getFloat("valor");
//                }

                stmt = con.prepareStatement("SELECT FATURAMENTOS.VLR_FAT "
                        + "FROM FATURAMENTOS "
                        + "INNER JOIN tabela_ordens_producao ON tabela_ordens_producao.cod = FATURAMENTOS.CODIGO_OP "
                        + "WHERE FATURAMENTOS.DT_FAT BETWEEN '2020-05-01' AND '2020-05-26' AND "
                        + "tabela_ordens_producao.cod_cliente = ? AND tabela_ordens_producao.tipo_cliente = 2");
                stmt.setInt(1, rs.getInt("cod"));
                rs2 = stmt.executeQuery();
                while (rs2.next()) {
                    credito -= rs2.getFloat("FATURAMENTOS.VLR_FAT");
                }

//                if(credito != 0d){
                    System.out.println(df.format(credito));
//                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(NewMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static void mostraValorProducao() {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ResultSet rs2 = null;
        ResultSet rs3 = null;
        double valor;
        DecimalFormat df = new DecimalFormat("###,##0.00");
        List<Integer> codigosProcessados = new ArrayList();

        try {
            stmt = con.prepareStatement("SELECT tabela_ordens_producao.cod_cliente, "
                    + "tabela_ordens_producao.orcamento_base, "
                    + "tabela_ordens_producao.cod_produto "
                    + "FROM tabela_ordens_producao "
                    + "INNER JOIN tabela_orcamentos ON tabela_orcamentos.cod = tabela_ordens_producao.orcamento_base "
                    + "WHERE tabela_ordens_producao.status != 'ENTREGUE' "
                    + "AND tabela_ordens_producao.status != 'ENTREGUE PARCIALMENTE' "
                    + "AND tabela_ordens_producao.status != 'CANCELADA'"
                    + "AND tabela_ordens_producao.tipo_cliente = 2 "
                    + "AND tabela_ordens_producao.data_emissao BETWEEN '2018-01-01' AND '2020-05-26' "
                    + "ORDER BY tabela_ordens_producao.cod_cliente ASC");
            rs = stmt.executeQuery();
            while (rs.next()) {
                if (!codigosProcessados.contains(rs.getInt("tabela_ordens_producao.cod_cliente"))) {
                    valor = 0d;
                    stmt = con.prepareStatement("SELECT tabela_ordens_producao.orcamento_base, "
                            + "tabela_ordens_producao.cod_produto "
                            + "FROM tabela_ordens_producao "
                            + "INNER JOIN tabela_orcamentos ON tabela_orcamentos.cod = tabela_ordens_producao.orcamento_base "
                            + "WHERE tabela_ordens_producao.status != 'ENTREGUE' "
                            + "AND tabela_ordens_producao.status != 'ENTREGUE PARCIALMENTE' "
                            + "AND tabela_ordens_producao.status != 'CANCELADA'"
                            + "AND tabela_ordens_producao.tipo_cliente = 2 "
                            + "AND tabela_ordens_producao.cod_cliente = ? "
                            + "AND tabela_ordens_producao.data_emissao BETWEEN '2018-01-01' AND '2020-05-26' "
                            + "ORDER BY tabela_ordens_producao.cod_cliente ASC");
                    stmt.setInt(1, rs.getInt("tabela_ordens_producao.cod_cliente"));
                    rs2 = stmt.executeQuery();
                    while (rs2.next()) {
                        stmt = con.prepareStatement("SELECT (quantidade * preco_unitario) AS valor "
                                + "FROM tabela_produtos_orcamento "
                                + "WHERE cod_orcamento = ? AND cod_produto = ?");
                        stmt.setInt(1, rs2.getInt("tabela_ordens_producao.orcamento_base"));
                        stmt.setInt(2, rs2.getInt("tabela_ordens_producao.cod_produto"));
                        rs3 = stmt.executeQuery();
                        if (rs3.next()) {
                            valor += rs3.getDouble("valor");
                        }
                    }
                    codigosProcessados.add(rs.getInt("tabela_ordens_producao.cod_cliente"));
                    System.out.println(rs.getInt("tabela_ordens_producao.cod_cliente") + ";" + df.format(valor));
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(NewMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static void descontaOrdemProducao() {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ResultSet rs2 = null;

        try {
            stmt = con.prepareStatement("SELECT orcamento_base, cod_cliente, tipo_cliente, cod_produto  "
                    + "FROM tabela_ordens_producao "
                    + "WHERE status != 'ENTREGUE' AND status != 'CANCELADA'");
            rs = stmt.executeQuery();
            while (rs.next()) {
                stmt = con.prepareStatement("SELECT FAT_TOTALMENTE "
                        + "FROM tabela_orcamentos "
                        + "WHERE cod = ? AND FAT_TOTALMENTE = 0");
                stmt.setInt(1, rs.getInt("orcamento_base"));
                rs2 = stmt.executeQuery();
                if (rs2.next()) {
                    double vlrParc = OrcamentoDAO.retornaVlrParcProd(rs.getInt("orcamento_base"),
                            rs.getString("cod_produto"));
                    ClienteDAO.corrigeCredito(rs.getInt("cod_cliente"), (byte) rs.getInt("tipo_cliente"),
                            vlrParc, (byte) 2);
                    OrcamentoDAO.alteraStatus(rs.getInt("orcamento_base"), 1);
                    System.out.println("Cliente: " + rs.getInt("cod_cliente") + " Tipo: " + rs.getInt("tipo_cliente")
                            + " Orcamento: " + rs.getInt("orcamento_base") + " Valor descontado: " + vlrParc);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(NewMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static void ranking() {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ResultSet rs2 = null;
        ResultSet rs3 = null;
        List<Integer> opsLidas = new ArrayList();
        int qtdProdutos = 0;
        List<String> ranking = new ArrayList();

        try {
            stmt = con.prepareStatement("SELECT tabela_ordens_producao.cod, "
                    + "tabela_ordens_producao.cod_cliente, "
                    + "tabela_ordens_producao.orcamento_base,"
                    + "tabela_ordens_producao.cod_produto,"
                    + "PRODUTOS.DESCRICAO "
                    + "FROM tabela_ordens_producao "
                    + "INNER JOIN tabela_orcamentos ON tabela_orcamentos.cod = tabela_ordens_producao.orcamento_base "
                    + "INNER JOIN PRODUTOS ON PRODUTOS.CODIGO = tabela_ordens_producao.cod_produto "
                    + "WHERE tabela_ordens_producao.status != 'CANCELADA' "
                    + "AND tabela_ordens_producao.data_emissao BETWEEN '2018-01-01' AND '2020-05-22' "
                    + "AND tabela_ordens_producao.tipo_cliente = 2 "
                    + "AND tabela_orcamentos.FAT_TOTALMENTE > 1");
            rs = stmt.executeQuery();
            while (rs.next()) {
                
                if (!opsLidas.contains(rs.getInt("tabela_ordens_producao.cod"))) {
                    qtdProdutos = 0;
                    stmt = con.prepareStatement("SELECT tabela_ordens_producao.cod, "
                            + "tabela_ordens_producao.orcamento_base,"
                            + "tabela_ordens_producao.cod_produto,"
                            + "PRODUTOS.DESCRICAO  "
                            + "FROM tabela_ordens_producao "
                            + "INNER JOIN tabela_orcamentos ON tabela_orcamentos.cod = tabela_ordens_producao.orcamento_base "
                            + "INNER JOIN PRODUTOS ON PRODUTOS.CODIGO = tabela_ordens_producao.cod_produto "
                            + "WHERE tabela_ordens_producao.status != 'CANCELADA' "
                            + "AND tabela_ordens_producao.cod_cliente = ? "
                            + "AND tabela_ordens_producao.tipo_cliente = 2 "
                            + "AND tabela_ordens_producao.data_emissao BETWEEN '2018-01-01' AND '2020-05-22'"
                            + "AND tabela_orcamentos.FAT_TOTALMENTE > 1");
                    stmt.setInt(1, rs.getInt("cod_cliente"));
                    rs2 = stmt.executeQuery();
                    while(rs2.next()){
                        opsLidas.add(rs2.getInt("tabela_ordens_producao.cod"));
                        stmt = con.prepareStatement("SELECT quantidade "
                                + "FROM tabela_produtos_orcamento "
                                + "WHERE cod_orcamento = ? AND cod_produto = ?");
                        stmt.setInt(1, rs2.getInt("tabela_ordens_producao.orcamento_base"));
                        stmt.setInt(2, rs2.getInt("tabela_ordens_producao.cod_produto"));
                        rs3 = stmt.executeQuery();
                        if(rs3.next()){qtdProdutos += rs3.getInt("quantidade");}
                        System.out.println(rs2.getInt("tabela_ordens_producao.cod_produto") + "#" 
                                + rs2.getString("PRODUTOS.DESCRICAO") + "#" +
                                qtdProdutos);
                    } 
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(NewMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
