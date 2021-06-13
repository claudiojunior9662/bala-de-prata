/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import connection.ConnectionFactory;
import entities.sisgrafex.Servicos;
import exception.ErroPesquisa;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import ui.cadastros.servicos.ServicoBEAN;

/**
 *
 * @author spd3
 */
public class ServicoDAO {
    
    /**
     * Cria um novo serviço
     * @param servico
     * @throws SQLException 
     */
    public static void cria(ServicoBEAN servico) throws SQLException{
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try{
            stmt = con.prepareStatement("INSERT INTO tabela_servicos_orcamento(descricao, valor_minimo, valor_unitario, servico_geral, tipo_servico) VALUES(?,?,?,?,?)");
            stmt.setString(1, servico.getDescricao());
            stmt.setFloat(2, servico.getValorMinimo());
            stmt.setFloat(3, servico.getValorUnitario());
            stmt.setInt(4, servico.getServicoGeral());
            stmt.setString(5, servico.getTipoServico());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            throw new SQLException(ex);
        } finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
    /**
     * Faz a pesquisa no banco de dados
     * @param tipo tipo da pesquisa
     * @param texto conteúdo da pesquisa
     * @return
     * @throws SQLException 
     */
    public static List<ServicoBEAN> pesquisaReg(String tipo, String texto) throws SQLException{
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<ServicoBEAN> cadastrosc = new ArrayList<>();
        
        try{
            if(tipo.equals("Código")){
                stmt = con.prepareStatement("SELECT * "
                        + "FROM tabela_servicos_orcamento "
                        + "WHERE cod = " + Integer.valueOf(texto));
            }else if(tipo.equals("Descrição")){
                stmt = con.prepareStatement("SELECT * "
                        + "FROM tabela_servicos_orcamento "
                        + "WHERE descricao LIKE " + "'%" + texto + "%'");
            }else if(tipo.equals("Tipo Serviço")){
                stmt = con.prepareStatement("SELECT * "
                        + "FROM tabela_servicos_orcamento WHERE descricao "
                        + "LIKE " + "'%" + texto + "%'");
            }
            
            rs = stmt.executeQuery();
            
            while(rs.next()){
                ServicoBEAN scBEAN = new ServicoBEAN();
                scBEAN.setCod(rs.getInt("cod"));
                scBEAN.setDescricao(rs.getString("descricao"));
                scBEAN.setValorMinimo(rs.getFloat("valor_minimo"));
                scBEAN.setValorUnitario(rs.getFloat("valor_unitario"));
                scBEAN.setServicoGeral(rs.getInt("servico_geral"));
                scBEAN.setTipoServico(rs.getString("tipo_servico"));
                cadastrosc.add(scBEAN);
            }
        } catch (SQLException ex) {
            throw new SQLException(ex);
        }catch(NumberFormatException ex){
            throw new ErroPesquisa();
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return cadastrosc;
    }
    
    /**
     * Mostra todos os serviços
     * @return
     * @throws SQLException 
     */
    public static List<ServicoBEAN> mostraTodos() throws SQLException{
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<ServicoBEAN> cadastrosc = new ArrayList<>();
        
        try{
            stmt = con.prepareStatement("SELECT * "
                    + "FROM tabela_servicos_orcamento "
                    + "WHERE excluido != 1 "
                    + "ORDER BY cod "
                    + "DESC");
            
            rs = stmt.executeQuery();
            
            while(rs.next()){
                ServicoBEAN scBEAN = new ServicoBEAN();
                scBEAN.setCod(rs.getInt("cod"));
                scBEAN.setDescricao(rs.getString("descricao"));
                scBEAN.setValorMinimo(rs.getFloat("valor_minimo"));
                scBEAN.setValorUnitario(rs.getFloat("valor_unitario"));
                scBEAN.setServicoGeral(rs.getInt("servico_geral"));
                scBEAN.setTipoServico(rs.getString("tipo_servico"));
                cadastrosc.add(scBEAN);
            }            
        } catch (SQLException ex) {
            throw new SQLException(ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return cadastrosc;
    }
    
    /**
     * Carrega a edição do serviço
     * @param codSv código do serviço
     * @return
     * @throws SQLException 
     */
    public static ServicoBEAN carregaEdicao(int codSv) throws SQLException{
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try{
            stmt = con.prepareStatement("SELECT * "
                    + "FROM tabela_servicos_orcamento "
                    + "WHERE cod = ?");
            stmt.setInt(1, codSv);
            rs = stmt.executeQuery();
            if (rs.next()){
                return new ServicoBEAN(
                        rs.getInt("cod"),
                        rs.getString("descricao"),
                        rs.getFloat("valor_minimo"),
                        rs.getFloat("valor_unitario"),
                        rs.getInt("servico_geral"),
                        rs.getString("tipo_servico")
                );
            }
            return null;
        } catch (SQLException ex) {
            throw new SQLException(ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
    }
    
    /**
     * Atualiza serviço
     * @param servico
     * @throws SQLException 
     */
    public static void atualizaSv(ServicoBEAN servico) throws SQLException{
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try{
            stmt = con.prepareStatement("UPDATE tabela_servicos_orcamento "
                    + "SET descricao = ?, valor_minimo = ?, valor_unitario = ?, servico_geral = ?, tipo_servico = ? "
                    + "WHERE cod = ?");
            stmt.setString(1, servico.getDescricao());
            stmt.setFloat(2, servico.getValorMinimo());
            stmt.setFloat(3, servico.getValorUnitario());
            stmt.setInt(4, servico.getServicoGeral());
            stmt.setString(5, servico.getTipoServico());
            stmt.setInt(6, servico.getCod());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            throw new SQLException(ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
    public static List<ServicoBEAN> carregaOrcamento(int cod) throws SQLException{
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<ServicoBEAN> cadastrosc = new ArrayList<>();
        
        try{
            stmt = con.prepareStatement("SELECT descricao, valor_unitario FROM tabela_servicos_orcamento WHERE cod = " + cod);
            rs = stmt.executeQuery();
            while(rs.next()){
                ServicoBEAN sBEAN = new ServicoBEAN();
                sBEAN.setCod(cod);
                sBEAN.setDescricao(rs.getString("descricao"));
                sBEAN.setValorUnitario(rs.getFloat("valor_unitario"));
                cadastrosc.add(sBEAN);
            }
        } catch (SQLException ex) {
            throw new SQLException(ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return cadastrosc;
    }
    
    public static void desativaServico(int codigoServico) throws SQLException{
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try{
            stmt = con.prepareStatement("UPDATE tabela_servicos_orcamento SET excluido = 1 WHERE cod = ?");
            stmt.setInt(1, codigoServico);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            throw new SQLException(ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
    public static String carregaDescricaoServicos(int codigoServico) throws SQLException{
        String retorno = null;
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try{
            stmt = con.prepareStatement("SELECT descricao FROM tabela_servicos_orcamento WHERE cod = ?");
            stmt.setInt(1, codigoServico);
            rs = stmt.executeQuery();
            while(rs.next()){
                retorno = rs.getString("descricao");
            }
        } catch (SQLException ex) {
            retorno = null;
            throw new SQLException(ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return retorno;
    }
    
    /*
    @param codigoOrcamento Código do orçamento
    @return Soma de todos os serviços do orçamento
    @see retornaVlrSvOrcExistente
    */
    public static Double retornaVlrSvOrcExistente(int codigoOrcamento) throws SQLException {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        double retorno = 0d;

        try {
            stmt = con.prepareStatement("SELECT tabela_servicos_orcamento.valor_unitario "
                    + "FROM tabela_servicos_orcamento "
                    + "INNER JOIN tabela_componentes_orcamentos ON tabela_componentes_orcamentos.cod_componente_1 = tabela_servicos_orcamento.cod "
                    + "WHERE tabela_componentes_orcamentos.cod_orcamento = ?");
            stmt.setInt(1, codigoOrcamento);
            rs = stmt.executeQuery();
            while (rs.next()) {
                retorno += rs.getDouble("tabela_servicos_orcamento.valor_unitario");
            }
        } catch (SQLException ex) {
            throw new SQLException(ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return retorno;
    }
    
    /*
    @param codigoServico Código do serviço
    @return Valores unitarios de cada serviço
    @see retornaVlrSrvOrcNExistente
    */
    public static float retornaVlrSrvOrcNExistente(int codigoServico) throws SQLException {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        float retorno = 0;

        try {
            stmt = con.prepareStatement("SELECT valor_unitario FROM tabela_servicos_orcamento WHERE cod = ?");
            stmt.setInt(1, codigoServico);
            rs = stmt.executeQuery();
            while (rs.next()) {
                retorno = rs.getFloat("valor_unitario");
            }
        } catch (SQLException ex) {
            throw new SQLException(ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return retorno;
    }
    
    /**
     * Retorna os serviços contidos no orçamento
    * @param codigoOrcamento Código do orçamento
    * @return Serviços associados ao orçamento
    * @see carregaServicosOrcamento
    */
    public static List<Servicos> carregaServicosOrcamento(int codigoOrcamento) throws SQLException {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Servicos> retorno = new ArrayList();

        try {
            stmt = con.prepareStatement("SELECT cod_componente_1, cod_componente FROM tabela_componentes_orcamentos WHERE cod_orcamento = ?");
            stmt.setInt(1, codigoOrcamento);
            rs = stmt.executeQuery();
            while (rs.next()) {
                retorno.add(new Servicos(rs.getInt("cod_componente_1"),
                        codigoOrcamento,
                        rs.getInt("cod_componente")));
            }
        } catch (SQLException ex) {
            throw new SQLException(ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return retorno;
    }
    
    /*
    @param codigoOrcamento
    @return void Desassocia os serviços do orçamento
    @see desassociaSvOrcamento
    */
    public static void desassociaSvOrcamento(int codigoOrcamento) throws SQLException {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("DELETE FROM tabela_componentes_orcamentos "
                    + "WHERE cod_orcamento = ?");
            stmt.setInt(1, codigoOrcamento);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            throw new SQLException(ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
    public static boolean verificaUsoSv(int codSv) throws SQLException{
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try{
            stmt = con.prepareStatement("SELECT cod_componente_1 "
                    + "FROM tabela_componentes_orcamentos "
                    + "WHERE cod_componente_1 = ? "
                    + "ORDER BY cod_componente_1 "
                    + "DESC "
                    + "LIMIT 1");
            stmt.setInt(1, codSv);
            rs = stmt.executeQuery();
            return rs.next();
        } catch (SQLException ex) {
            throw new SQLException(ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
    }
    
}
