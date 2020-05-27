/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.relatorios.financeiro;

import connection.ConnectionFactory;
import entidades.Cliente;
import ui.cadastros.notas.NotaBEAN;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import static model.dao.OrdemProducaoDAO.retornaNomeCliente;
import ui.cadastros.clientes.ClienteDAO;
import ui.cadastros.produtos.ProdutoDAO;

/**
 *
 * @author claudio
 */
public class RelatoriosFinanceiroDAO {

    //NOTAS DE CRÉDITO
    public List<NotaBEAN> retornaConteudoRelatorioCredito(boolean codigo,
            boolean formaPagamento,
            boolean emissor,
            boolean codigoCliente,
            boolean nomeCliente,
            boolean tipoPessoa,
            boolean valor,
            boolean data,
            String tipoCondicaoCliente,
            String tipoCondicaoEmissor,
            String tipoCondicaoPeriodo,
            String tipoCondicaoFormaPagamento,
            String tipoCondicaoOrdenar,
            int tipoCliente,
            String textoCliente,
            String textoEmissor,
            String periodoInicio,
            String periodoFim,
            String textoFormaPagamento) throws SQLException {

        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<NotaBEAN> retorno = new ArrayList();
        String comando = null;

        try {
            comando = retornaComandoCredito(codigo, formaPagamento, emissor, codigoCliente,
                    tipoPessoa, valor, data, tipoCondicaoCliente,
                    tipoCondicaoEmissor, tipoCondicaoPeriodo,
                    tipoCondicaoFormaPagamento, tipoCondicaoOrdenar,
                    tipoCliente, textoCliente, textoEmissor,
                    periodoInicio, periodoFim, textoFormaPagamento);
            stmt = con.prepareStatement(comando);
            rs = stmt.executeQuery();
            retorno = retornaResultadoQueryCredito(rs, codigo, formaPagamento, emissor,
                    codigoCliente, nomeCliente, tipoPessoa, valor, data,
                    tipoCliente, textoCliente);
            stmt.close();
        } catch (SQLException ex) {
            throw new SQLException(ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return retorno;
    }

    //FORMATACAO DE COMANDO SQL
    public String retornaComandoCredito(boolean codigo,
            boolean formaPagamento,
            boolean emissor,
            boolean codigoCliente,
            boolean tipoPessoa,
            boolean valor,
            boolean data,
            String tipoCondicaoCliente,
            String tipoCondicaoEmissor,
            String tipoCondicaoPeriodo,
            String tipoCondicaoFormaPagamento,
            String tipoCondicaoOrdenar,
            int tipoCliente,
            String textoCliente,
            String textoEmissor,
            String periodoInicio,
            String periodoFim,
            String textoFormaPagamento) {

        String comando = "SELECT";
        int primeiro = 0;

        if (codigo == true) {
            comando = comando + " cod";
            primeiro += 1;
        }
        if (formaPagamento == true) {
            if (primeiro == 0) {
                comando = comando + " forma_pagamento";
                primeiro += 1;
            } else {
                comando = comando + ", forma_pagamento";
            }
        }
        if (emissor == true) {
            if (primeiro == 0) {
                comando = comando + " cod_emissor";
                primeiro += 1;
            } else {
                comando = comando + ", cod_emissor";
            }
        }

        if (primeiro == 0) {
            comando = comando + " cod_cliente";
            primeiro += 1;
        } else {
            comando = comando + ", cod_cliente";
        }

        if (tipoPessoa == true) {
            if (primeiro == 0) {
                comando = comando + " tipo_pessoa";
                primeiro += 1;
            } else {
                comando = comando + ", tipo_pessoa";
            }
        }
        if (valor == true) {
            if (primeiro == 0) {
                comando = comando + " valor";
                primeiro += 1;
            } else {
                comando = comando + ", valor";
            }
        }
        if (data == true) {
            if (primeiro == 0) {
                comando = comando + " data";
                primeiro += 1;
            } else {
                comando = comando + ", data";
            }
        }

        comando = comando + " FROM tabela_notas";
        primeiro = 0;

        if (tipoCondicaoPeriodo == "POR DIA") {
            comando = comando + " WHERE data = '" + periodoInicio + "'";
            primeiro += 1;
        } else if (tipoCondicaoPeriodo == "POR PERIODO") {
            comando = comando + " WHERE DATE_FORMAT(STR_TO_DATE(data, '%d/%m/%Y'), '%Y-%m-%d') "
                    + "BETWEEN DATE_FORMAT(STR_TO_DATE('" + periodoInicio + "', '%d/%m/%Y'), '%Y-%m-%d') "
                    + "AND DATE_FORMAT(STR_TO_DATE('" + periodoFim + "','%d/%m/%Y'), '%Y-%m-%d')";
            primeiro += 1;
        }

        if (primeiro == 0) {
            comando = comando + " WHERE serie = 2";
            primeiro += 1;
        } else {
            comando = comando + " AND serie = 2";
        }

        if (tipoCondicaoCliente == "POR CÓDIGO" || tipoCondicaoCliente == "POR NOME") {
            if (primeiro == 0) {
                comando = comando + " WHERE cod_cliente = " + Integer.valueOf(textoCliente) + " AND tipo_pessoa = " + tipoCliente;
                primeiro += 1;
            } else {
                comando = comando + " AND cod_cliente = " + Integer.valueOf(textoCliente) + " AND tipo_pessoa = " + tipoCliente;
                primeiro += 1;
            }
        } else if (tipoCondicaoCliente == "POR TIPO PESSOA") {
            if (primeiro == 0) {
                comando = comando + " WHERE tipo_pessoa = " + tipoCliente;
                primeiro += 1;
            } else {
                comando = comando + " AND tipo_pessoa = " + tipoCliente;
                primeiro += 1;
            }
        }

        if (tipoCondicaoEmissor == "POR EMISSOR") {
            if (primeiro == 0) {
                comando = comando + " WHERE cod_emissor = '" + textoEmissor + "'";
                primeiro += 1;
            } else {
                comando = comando + " AND cod_emissor = '" + textoEmissor + "'";
            }
        }

        if (tipoCondicaoFormaPagamento == "POR FORMA DE PAGAMENTO") {
            if (primeiro == 0) {
                comando = comando + " WHERE forma_pagamento = " + Integer.valueOf(textoFormaPagamento);
                primeiro += 1;
            } else {
                comando = comando + " AND forma_pagamento = " + Integer.valueOf(textoFormaPagamento);
            }
        }

        if (tipoCondicaoOrdenar == "POR CÓDIGO CRESCENTE") {
            comando = comando + " ORDER BY cod ASC";
        } else if (tipoCondicaoOrdenar == "POR CÓDIGO DECRESCENTE") {
            comando = comando + " ORDER BY cod DESC";
        } else if (tipoCondicaoOrdenar == "POR FORMA DE PAGAMENTO") {
            comando = comando + " ORDER BY forma_pagamento ASC";
        } else if (tipoCondicaoOrdenar == "POR EMISSOR") {
            comando = comando + " ORDER BY cod_emissor ASC";
        } else if (tipoCondicaoOrdenar == "POR TIPO DE PESSOA") {
            comando = comando + " ORDER BY tipo_pessoa ASC";
        } else if (tipoCondicaoOrdenar == "POR VALOR CRESCENTE") {
            comando = comando + " ORDER BY valor ASC";
        } else if (tipoCondicaoOrdenar == "POR VALOR DECRESCENTE") {
            comando = comando + " ORDER BY valor DESC";
        } else if (tipoCondicaoOrdenar == "POR DATA MAIS ATUAL") {
            comando = comando + " ORDER BY data DESC";
        } else if (tipoCondicaoOrdenar == "POR DATA MAIS ANTIGA") {
            comando = comando + " ORDER BY data ASC";
        }

        System.out.println(comando);
        return comando;
    }

    public List<NotaBEAN> retornaResultadoQueryCredito(ResultSet rs,
            boolean codigo,
            boolean formaPagamento,
            boolean emissor,
            boolean codigoCliente,
            boolean nomeCliente,
            boolean tipoPessoa,
            boolean valor,
            boolean data,
            int tipoCliente,
            String textoCliente) throws SQLException {

        List<NotaBEAN> retorno = new ArrayList();

        try {
            while (rs.next()) {
                NotaBEAN aux = new NotaBEAN();

                if (codigo == true) {
                    aux.setCod(rs.getInt("cod"));
                }
                if (formaPagamento == true) {
                    aux.setFormaPagamento(rs.getInt("forma_pagamento"));
                }
                if (emissor == true) {
                    aux.setCodEmissor(rs.getString("cod_emissor"));
                }
                if (codigoCliente == true) {
                    aux.setCodigoCliente(rs.getInt("cod_cliente"));
                }
                if (nomeCliente == true) {
                    aux.setNomeCliente(retornaNomeCliente(rs.getInt("cod_cliente"), tipoCliente));
                }
                if (tipoPessoa == true) {
                    aux.setTipoPessoa(rs.getInt("tipo_pessoa"));
                }
                if (valor == true) {
                    aux.setValor(rs.getFloat("valor"));
                }
                if (data == true) {
                    aux.setData(rs.getString("data"));
                }

                retorno.add(aux);
            }
        } catch (SQLException ex) {
            throw new SQLException(ex);
        }

        return retorno;
    }

    //FATURAMENTOS
    /**-------------------------------------------------------------------------
     * Retorna relatório faturamento
     * @param codigo
     * @param codigoOp
     * @param codigoOrcamento
     * @param emissor
     * @param codigoCliente
     * @param nomeCliente
     * @param tipoPessoa
     * @param quantidadeEntregue
     * @param valor
     * @param data
     * @param nomeTransportador
     * @param modalidadeFrete
     * @param produto
     * @param tipoCondicaoCliente
     * @param tipoCondicaoOpOrcamento
     * @param tipoCondicaoEmissor
     * @param tipoCondicaoPeriodo
     * @param tipoCondicaoTransporte
     * @param tipoCondicaoOrdenar
     * @param cliente
     * @param textoOpOrcamento
     * @param textoEmissor
     * @param periodoInicio
     * @param periodoFim
     * @param textoTransporte
     * @return
     * @throws SQLException 
     */
    public List<RelatoriosFatBEAN> retornaContRelFat(boolean codigo,
            boolean codigoOp,
            boolean codigoOrcamento,
            boolean emissor,
            boolean codigoCliente,
            boolean nomeCliente,
            boolean tipoPessoa,
            boolean quantidadeEntregue,
            boolean valor,
            boolean data,
            boolean nomeTransportador,
            boolean modalidadeFrete,
            boolean produto,
            String tipoCondicaoCliente,
            String tipoCondicaoOpOrcamento,
            String tipoCondicaoEmissor,
            String tipoCondicaoPeriodo,
            String tipoCondicaoTransporte,
            String tipoCondicaoOrdenar,
            Cliente cliente,
            String textoOpOrcamento,
            String textoEmissor,
            java.sql.Date periodoInicio,
            java.sql.Date periodoFim,
            String textoTransporte) throws SQLException {

        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<RelatoriosFatBEAN> retorno = new ArrayList();
        String comando = null;

        try {
            comando = retornaCmdFat(codigo, codigoOp, codigoOrcamento, emissor,
                    codigoCliente, tipoPessoa, quantidadeEntregue,
                    valor, data, nomeTransportador, modalidadeFrete, produto,
                    tipoCondicaoCliente, tipoCondicaoOpOrcamento,
                    tipoCondicaoEmissor, tipoCondicaoPeriodo,
                    tipoCondicaoTransporte, tipoCondicaoOrdenar,
                    cliente, textoOpOrcamento,
                    textoEmissor, periodoInicio, periodoFim,
                    textoTransporte);
            stmt = con.prepareStatement(comando);
            rs = stmt.executeQuery();
            retorno = retornaResultQueryFat(rs, codigo, codigoOp, codigoOrcamento, emissor,
                    codigoCliente, nomeCliente, tipoPessoa, quantidadeEntregue,
                    valor, data, nomeTransportador, modalidadeFrete, produto, cliente,
                    tipoCondicaoCliente);
            stmt.close();
        } catch (SQLException ex) {
            throw new SQLException(ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return retorno;
    }

    //FORMATACAO DE COMANDO SQL
    public String retornaCmdFat(boolean codigo,
            boolean codigoOp,
            boolean codigoOrcamento,
            boolean emissor,
            boolean codigoCliente,
            boolean tipoPessoa,
            boolean quantidadeEntregue,
            boolean valor,
            boolean data,
            boolean nomeTransportador,
            boolean modalidadeFrete,
            boolean produto,
            String tipoCondicaoCliente,
            String tipoCondicaoOpOrcamento,
            String tipoCondicaoEmissor,
            String tipoCondicaoPeriodo,
            String tipoCondicaoTransporte,
            String tipoCondicaoOrdenar,
            Cliente cliente,
            String textoOpOrcamento,
            String textoEmissor,
            java.sql.Date periodoInicio,
            java.sql.Date periodoFim,
            String textoTransporte) {

        String comando = "SELECT";
        int primeiro = 0;

        if (tipoCondicaoTransporte != "POR TODOS") {

            comando = comando + " FATURAMENTOS.CODIGO";
            primeiro += 1;

            if (primeiro == 0) {
                comando = comando + " FATURAMENTOS.CODIGO_OP";
                primeiro += 1;
            } else {
                comando = comando + ", FATURAMENTOS.CODIGO_OP";
            }
            
            if(produto){
                 if (primeiro == 0) {
                    comando = comando + " tabela_ordens_producao.cod_produto";
                    primeiro += 1;
                } else {
                    comando = comando + ", tabela_ordens_producao.cod_produto";
                }
            }

            if (codigoOrcamento == true) {
                if (primeiro == 0) {
                    comando = comando + " FATURAMENTOS.CODIGO_ORC";
                    primeiro += 1;
                } else {
                    comando = comando + ", FATURAMENTOS.CODIGO_ORC";
                }
            }
            if (emissor == true) {
                if (primeiro == 0) {
                    comando = comando + " FATURAMENTOS.EMISSOR";
                    primeiro += 1;
                } else {
                    comando = comando + ", FATURAMENTOS.EMISSOR";
                }
            }

            if (primeiro == 0) {
                comando = comando + " tabela_ordens_producao.cod_cliente";
                primeiro += 1;
            } else {
                comando = comando + ", tabela_ordens_producao.cod_cliente";
            }

            if (primeiro == 0) {
                comando = comando + " tabela_ordens_producao.tipo_cliente";
                primeiro += 1;
            } else {
                comando = comando + ", tabela_ordens_producao.tipo_cliente";
            }

            if (quantidadeEntregue == true) {
                if (primeiro == 0) {
                    comando = comando + " FATURAMENTOS.QTD_ENTREGUE";
                    primeiro += 1;
                } else {
                    comando = comando + ", FATURAMENTOS.QTD_ENTREGUE";
                }
            }
            if (valor == true) {
                if (primeiro == 0) {
                    comando = comando + " FATURAMENTOS.VLR_FAT";
                    primeiro += 1;
                } else {
                    comando = comando + ", FATURAMENTOS.VLR_FAT";
                }
            }
            if (data == true) {
                if (primeiro == 0) {
                    comando = comando + " FATURAMENTOS.DT_FAT";
                    primeiro += 1;
                } else {
                    comando = comando + ", FATURAMENTOS.DT_FAT";
                }
            }

            if (primeiro == 0) {
                comando = comando + " tabela_notas_transporte.cod_nota, "
                        + "tabela_notas_transporte.modalidade_frete, "
                        + "tabela_notas_transporte.nome_transportador";
                primeiro += 1;
            } else {
                comando = comando + ", tabela_notas_transporte.cod_nota, "
                        + "tabela_notas_transporte.modalidade_frete, "
                        + "tabela_notas_transporte.nome_transportador";
            }

            comando = comando + " FROM FATURAMENTOS";
            comando = comando + " INNER JOIN tabela_notas_transporte "
                    + "ON tabela_notas_transporte.cod_nota = FATURAMENTOS.CODIGO";
            comando = comando + " INNER JOIN tabela_ordens_producao "
                    + "ON tabela_ordens_producao.cod = FATURAMENTOS.CODIGO_OP";
            primeiro = 0;

            if (tipoCondicaoTransporte == "POR MODALIDADE DE FRETE") {
                if (primeiro == 0) {
                    comando = comando + " WHERE tabela_notas_transporte.modalidade_frete = '"
                            + textoTransporte + "'";
                    primeiro += 1;
                } else {
                    comando = comando + " AND tabela_notas_transporte.modalidade_frete = '"
                            + textoTransporte + "'";
                }
            } else if (tipoCondicaoTransporte == "POR NOME DO TRANSPORTADOR") {
                if (primeiro == 0) {
                    comando = comando + " WHERE tabela_notas_transporte.nome_transportador LIKE '%"
                            + textoTransporte + "%'";
                    primeiro += 1;
                } else {
                    comando = comando + " AND tabela_notas_transporte.nome_transportador LIKE '%"
                            + textoTransporte + "%'";
                }
            }

            if (primeiro == 0) {
                comando = comando + " WHERE tabela_notas_transporte.cod_nota = FATURAMENTOS.CODIGO";
                primeiro += 1;
            } else {
                comando = comando + " AND tabela_notas_transporte.cod_nota = FATURAMENTOS.CODIGO";
            }

            if (tipoCondicaoOpOrcamento == "ORDEM DE PRODUÇÃO (CÓDIGO)") {
                if (primeiro == 0) {
                    comando = comando + " WHERE FATURAMENTOS.CODIGO_OP = " + Integer.valueOf(textoOpOrcamento);
                    primeiro += 1;
                } else {
                    comando = comando + " AND FATURAMENTOS.CODIGO_OP = " + Integer.valueOf(textoOpOrcamento);
                }
            } else if (tipoCondicaoOpOrcamento == "ORÇAMENTO (CÓDIGO)") {
                if (primeiro == 0) {
                    comando = comando + " WHERE FATURAMENTOS.CODIGO_ORC = " + Integer.valueOf(textoOpOrcamento);
                    primeiro += 1;
                } else {
                    comando = comando + " AND FATURAMENTOS.CODIGO_ORC = " + Integer.valueOf(textoOpOrcamento);
                }
            }

            if (tipoCondicaoEmissor == "POR EMISSOR") {
                if (primeiro == 0) {
                    comando = comando + " WHERE FATURAMENTOS.EMISSOR = '" + textoEmissor + "'";
                    primeiro += 1;
                } else {
                    comando = comando + " AND FATURAMENTOS.EMISSOR = '" + textoEmissor + "'";
                }
            }

            if (tipoCondicaoPeriodo == "POR DIA") {
                if (primeiro == 0) {
                    comando = comando + " WHERE FATURAMENTOS.DT_FAT = '" + periodoInicio + "'";
                    primeiro += 1;
                } else {
                    comando = comando + " AND FATURAMENTOS.DT_FAT = '" + periodoInicio + "'";
                }
            } else if (tipoCondicaoPeriodo == "POR PERIODO") {
                if (primeiro == 0) {
                    comando = comando + " WHERE FATURAMENTOS.DT_FAT "
                            + "BETWEEN '" + periodoInicio + "' "
                            + "AND '" + periodoFim + "'";
                    primeiro += 1;
                } else {
                    comando = comando + " AND FATURAMENTOS.DT_FAT "
                            + "BETWEEN '" + periodoInicio + "' "
                            + "AND '" + periodoFim + "'";
                }

            }

            if (tipoCondicaoCliente == "POR CÓDIGO" || tipoCondicaoCliente == "POR NOME") {
                if (primeiro == 0) {
                    comando = comando + " WHERE tabela_ordens_producao.cod_cliente = " + cliente.getCodigo()
                            + " AND tabela_ordens_producao.tipo_cliente = " + cliente.getTipoPessoa();
                    primeiro += 1;
                } else {
                    comando = comando + " AND tabela_ordens_producao.cod_cliente = " + cliente.getCodigo()
                            + " AND tabela_ordens_producao.tipo_cliente = " + cliente.getTipoPessoa();
                    primeiro += 1;
                }
            } else if (tipoCondicaoCliente == "POR TIPO PESSOA") {
                if (primeiro == 0) {
                    comando = comando + " WHERE tabela_ordens_producao.tipo_cliente = " + cliente.getTipoPessoa();
                    primeiro += 1;
                } else {
                    comando = comando + " AND tabela_ordens_producao.tipo_cliente = " + cliente.getTipoPessoa();
                    primeiro += 1;
                }
            }

            if (tipoCondicaoOrdenar == "POR CÓDIGO CRESCENTE") {
                comando = comando + " ORDER BY FATURAMENTOS.CODIGO ASC";
            } else if (tipoCondicaoOrdenar == "POR CÓDIGO DECRESCENTE") {
                comando = comando + " ORDER BY FATURAMENTOS.CODIGO DESC";
            } else if (tipoCondicaoOrdenar == "POR CÓDIGO OP CRESCENTE") {
                comando = comando + " ORDER BY FATURAMENTOS.CODIGO_OP ASC";
            } else if (tipoCondicaoOrdenar == "POR CÓDIGO OP DECRESCENTE") {
                comando = comando + " ORDER BY FATURAMENTOS.CODIGO_OP DESC";
            } else if (tipoCondicaoOrdenar == "POR QUANTIDADE CRESCENTE") {
                comando = comando + " ORDER BY FATURAMENTOS.QTD_ENTREGUE ASC";
            } else if (tipoCondicaoOrdenar == "POR QUANTIDADE DECRESCENTE") {
                comando = comando + " ORDER BY FATURAMENTOS.QTD_ENTREGUE DESC";
            } else if (tipoCondicaoOrdenar == "POR EMISSOR") {
                comando = comando + " ORDER BY FATURAMENTOS.EMISSOR ASC";
            } else if (tipoCondicaoOrdenar == "POR TIPO DE PESSOA") {
                comando = comando + " ORDER BY tabela_ordens_producao.tipo_cliente ASC";
            } else if (tipoCondicaoOrdenar == "POR VALOR CRESCENTE") {
                comando = comando + " ORDER BY FATURAMENTOS.VLR_FAT ASC";
            } else if (tipoCondicaoOrdenar == "POR VALOR DECRESCENTE") {
                comando = comando + " ORDER BY FATURAMENTOS.VLR_FAT DESC";
            } else if (tipoCondicaoOrdenar == "POR DATA MAIS ATUAL") {
                comando = comando + " ORDER BY FATURAMENTOS.DT_FAT DESC";
            } else if (tipoCondicaoOrdenar == "POR DATA MAIS ANTIGA") {
                comando = comando + " ORDER BY FATURAMENTOS.DT_FAT ASC";
            }
        } else {

            comando = comando + " FATURAMENTOS.CODIGO";
            primeiro += 1;

            if (primeiro == 0) {
                comando = comando + " FATURAMENTOS.CODIGO_OP";
                primeiro += 1;
            } else {
                comando = comando + ", FATURAMENTOS.CODIGO_OP";
            }
            
            if(produto){
                 if (primeiro == 0) {
                    comando = comando + " tabela_ordens_producao.cod_produto";
                    primeiro += 1;
                } else {
                    comando = comando + ", tabela_ordens_producao.cod_produto";
                }
            }

            if (codigoOrcamento == true) {
                if (primeiro == 0) {
                    comando = comando + " FATURAMENTOS.CODIGO_ORC";
                    primeiro += 1;
                } else {
                    comando = comando + ", FATURAMENTOS.CODIGO_ORC";
                }
            }
            if (emissor == true) {
                if (primeiro == 0) {
                    comando = comando + " FATURAMENTOS.EMISSOR";
                    primeiro += 1;
                } else {
                    comando = comando + ", FATURAMENTOS.EMISSOR";
                }
            }

            if (primeiro == 0) {
                comando = comando + " tabela_ordens_producao.cod_cliente";
                primeiro += 1;
            } else {
                comando = comando + ", tabela_ordens_producao.cod_cliente";
            }

            if (primeiro == 0) {
                comando = comando + " tabela_ordens_producao.tipo_cliente";
                primeiro += 1;
            } else {
                comando = comando + ", tabela_ordens_producao.tipo_cliente";
            }

            if (quantidadeEntregue == true) {
                if (primeiro == 0) {
                    comando = comando + " FATURAMENTOS.QTD_ENTREGUE";
                    primeiro += 1;
                } else {
                    comando = comando + ", FATURAMENTOS.QTD_ENTREGUE";
                }
            }
            if (valor == true) {
                if (primeiro == 0) {
                    comando = comando + " FATURAMENTOS.VLR_FAT";
                    primeiro += 1;
                } else {
                    comando = comando + ", FATURAMENTOS.VLR_FAT";
                }
            }
            if (data == true) {
                if (primeiro == 0) {
                    comando = comando + " FATURAMENTOS.DT_FAT";
                    primeiro += 1;
                } else {
                    comando = comando + ", FATURAMENTOS.DT_FAT";
                }
            }

            if (primeiro == 0) {
                comando = comando + " tabela_notas_transporte.cod_nota, "
                        + "tabela_notas_transporte.modalidade_frete, "
                        + "tabela_notas_transporte.nome_transportador";
                primeiro += 1;
            } else {
                comando = comando + ", tabela_notas_transporte.cod_nota, "
                        + "tabela_notas_transporte.modalidade_frete, "
                        + "tabela_notas_transporte.nome_transportador";
            }

            comando = comando + " FROM FATURAMENTOS";
            comando = comando + " INNER JOIN tabela_notas_transporte "
                    + "ON tabela_notas_transporte.cod_nota = FATURAMENTOS.CODIGO";
            comando = comando + " INNER JOIN tabela_ordens_producao "
                    + "ON tabela_ordens_producao.cod = FATURAMENTOS.CODIGO_OP";
            primeiro = 0;

            if (primeiro == 0) {
                comando = comando + " WHERE tabela_notas_transporte.cod_nota = FATURAMENTOS.CODIGO";
                primeiro += 1;
            } else {
                comando = comando + " AND tabela_notas_transporte.cod_nota = FATURAMENTOS.CODIGO";
            }

            if (tipoCondicaoOpOrcamento == "ORDEM DE PRODUÇÃO (CÓDIGO)") {
                System.out.println("entrou");
                if (primeiro == 0) {
                    comando = comando + " WHERE FATURAMENTOS.CODIGO_OP = " + Integer.valueOf(textoOpOrcamento);
                    primeiro += 1;
                } else {
                    comando = comando + " AND FATURAMENTOS.CODIGO_OP = " + Integer.valueOf(textoOpOrcamento);
                }
            } else if (tipoCondicaoOpOrcamento == "ORÇAMENTO (CÓDIGO)") {
                if (primeiro == 0) {
                    comando = comando + " WHERE FATURAMENTOS.CODIGO_ORC = " + Integer.valueOf(textoOpOrcamento);
                    primeiro += 1;
                } else {
                    comando = comando + " AND FATURAMENTOS.CODIGO_ORC = " + Integer.valueOf(textoOpOrcamento);
                }
            }

            if (tipoCondicaoEmissor == "POR EMISSOR") {
                if (primeiro == 0) {
                    comando = comando + " WHERE FATURAMENTOS.EMISSOR = '" + textoEmissor + "'";
                    primeiro += 1;
                } else {
                    comando = comando + " AND FATURAMENTOS.EMISSOR = '" + textoEmissor + "'";
                }
            }

            if (tipoCondicaoPeriodo == "POR DIA") {
                if (primeiro == 0) {
                    comando = comando + " WHERE FATURAMENTOS.DT_FAT = '" + periodoInicio + "'";
                    primeiro += 1;
                } else {
                    comando = comando + " AND FATURAMENTOS.DT_FAT = '" + periodoInicio + "'";
                }
            } else if (tipoCondicaoPeriodo == "POR PERIODO") {
                if (primeiro == 0) {
                    comando = comando + " WHERE FATURAMENTOS.DT_FAT "
                            + "BETWEEN '" + periodoInicio + "' "
                            + "AND '" + periodoFim + "'";
                    primeiro += 1;
                } else {
                    comando = comando + " AND FATURAMENTOS.DT_FAT "
                            + "BETWEEN '" + periodoInicio + "' "
                            + "AND '" + periodoFim + "'";
                }

            }

            if (tipoCondicaoCliente == "POR CÓDIGO" || tipoCondicaoCliente == "POR NOME") {
                if (primeiro == 0) {
                    comando = comando + " WHERE tabela_ordens_producao.cod_cliente = " + cliente.getCodigo()
                            + " AND tabela_ordens_producao.tipo_cliente = " + cliente.getTipoPessoa();
                    primeiro += 1;
                } else {
                    comando = comando + " AND tabela_ordens_producao.cod_cliente = " + cliente.getCodigo()
                            + " AND tabela_ordens_producao.tipo_cliente = " + cliente.getTipoPessoa();
                    primeiro += 1;
                }
            } else if (tipoCondicaoCliente == "POR TIPO PESSOA") {
                if (primeiro == 0) {
                    comando = comando + " WHERE tabela_ordens_producao.tipo_cliente = " + cliente.getTipoPessoa();
                    primeiro += 1;
                } else {
                    comando = comando + " AND tabela_ordens_producao.tipo_cliente = " + cliente.getTipoPessoa();
                    primeiro += 1;
                }
            }

            if (tipoCondicaoOrdenar == "POR CÓDIGO CRESCENTE") {
                comando = comando + " ORDER BY FATURAMENTOS.CODIGO ASC";
            } else if (tipoCondicaoOrdenar == "POR CÓDIGO DECRESCENTE") {
                comando = comando + " ORDER BY FATURAMENTOS.CODIGO DESC";
            } else if (tipoCondicaoOrdenar == "POR CÓDIGO OP CRESCENTE") {
                comando = comando + " ORDER BY FATURAMENTOS.CODIGO_OP ASC";
            } else if (tipoCondicaoOrdenar == "POR CÓDIGO OP DECRESCENTE") {
                comando = comando + " ORDER BY FATURAMENTOS.CODIGO_OP DESC";
            } else if (tipoCondicaoOrdenar == "POR QUANTIDADE CRESCENTE") {
                comando = comando + " ORDER BY FATURAMENTOS.QTD_ENTREGUE ASC";
            } else if (tipoCondicaoOrdenar == "POR QUANTIDADE DECRESCENTE") {
                comando = comando + " ORDER BY FATURAMENTOS.QTD_ENTREGUE DESC";
            } else if (tipoCondicaoOrdenar == "POR EMISSOR") {
                comando = comando + " ORDER BY FATURAMENTOS.EMISSOR ASC";
            } else if (tipoCondicaoOrdenar == "POR TIPO DE PESSOA") {
                comando = comando + " ORDER BY tabela_ordens_producao.tipo_cliente ASC";
            } else if (tipoCondicaoOrdenar == "POR VALOR CRESCENTE") {
                comando = comando + " ORDER BY FATURAMENTOS.VLR_FAT ASC";
            } else if (tipoCondicaoOrdenar == "POR VALOR DECRESCENTE") {
                comando = comando + " ORDER BY FATURAMENTOS.VLR_FAT DESC";
            } else if (tipoCondicaoOrdenar == "POR DATA MAIS ATUAL") {
                comando = comando + " ORDER BY FATURAMENTOS.DT_FAT DESC";
            } else if (tipoCondicaoOrdenar == "POR DATA MAIS ANTIGA") {
                comando = comando + " ORDER BY FATURAMENTOS.DT_FAT ASC";
            }
        }
        System.out.println(comando);
        return comando;
    }

    public List<RelatoriosFatBEAN> retornaResultQueryFat(ResultSet rs,
            boolean codigo,
            boolean codigoOp,
            boolean codigoOrcamento,
            boolean emissor,
            boolean codigoCliente,
            boolean nomeCliente,
            boolean tipoPessoa,
            boolean quantidadeEntregue,
            boolean valor,
            boolean data,
            boolean nomeTransportador,
            boolean modalidadeFrete,
            boolean produto,
            Cliente cliente,
            String tipoCondicaoCliente) throws SQLException {

        List<RelatoriosFatBEAN> retorno = new ArrayList();

        try {
            while (rs.next()) {
                RelatoriosFatBEAN fat = new RelatoriosFatBEAN();

                if (codigo == true) {
                    fat.setCod(rs.getInt("FATURAMENTOS.CODIGO"));
                }
                if (codigoOp == true) {
                    fat.setCodOp(rs.getInt("FATURAMENTOS.CODIGO_OP"));
                }
                if (codigoOrcamento == true) {
                    fat.setCodOrcamento(rs.getInt("FATURAMENTOS.CODIGO_ORC"));
                }
                if (emissor == true) {
                    fat.setCodEmissor(rs.getString("FATURAMENTOS.EMISSOR"));
                }
                if (codigoCliente == true) {
                    fat.setCodCliente(rs.getInt("tabela_ordens_producao.cod_cliente"));
                }

                if (nomeCliente == true) {
                    if (tipoCondicaoCliente.equals("TODOS")) {
                        fat.setNomeCliente(ClienteDAO.retornaNomeCliente(rs.getInt("tabela_ordens_producao.cod_cliente"), 
                                (byte) rs.getInt("tabela_ordens_producao.tipo_cliente")));
                    } else {
                        fat.setNomeCliente(ClienteDAO.retornaNomeCliente(rs.getInt("tabela_ordens_producao.cod_cliente"), 
                                cliente.getTipoPessoa()));
                    }
                }

                if (tipoPessoa == true) {
                    fat.setTipo_pessoa(rs.getInt("tabela_ordens_producao.tipo_cliente"));
                }
                if (quantidadeEntregue == true) {
                    fat.setQuantidadeEntregue(rs.getInt("FATURAMENTOS.QTD_ENTREGUE"));
                }
                if (valor == true) {
                    fat.setValor(rs.getFloat("FATURAMENTOS.VLR_FAT"));
                }
                if (data == true) {
                    fat.setData(rs.getString("FATURAMENTOS.DT_FAT"));
                }
                if (nomeTransportador == true) {
                    fat.setNomeTransportador(rs.getString("tabela_notas_transporte.nome_transportador"));
                }
                if (modalidadeFrete == true) {
                    fat.setModalidadeFrete(rs.getString("tabela_notas_transporte.modalidade_frete"));
                }

                if (produto == true) {
                    fat.setDescricaoProduto(ProdutoDAO.retornaDescricaoProduto(
                            rs.getString("tabela_ordens_producao.cod_produto")));
                }

                retorno.add(fat);
            }
        } catch (SQLException ex) {
            throw new SQLException(ex);
        }

        return retorno;
    }

}
