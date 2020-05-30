/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.controle;

import com.itextpdf.text.BaseColor;
import connection.ConnectionFactory;
import entidades.StsOrcamento;
import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JTextPane;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
import javax.swing.border.Border;
import ui.login.TelaAutenticacao;
import ui.principal.GerenteJanelas;

/**
 *
 * @author 1113778771
 */
public class Controle {

    /**
     * Define o tipo de acessos de acordo com o estado da aplicação
     *
     * @param tipoVersao 1 - produção 2 - desenvolvimento rede 3 -
     * desenvolvimento local
     */
    private static byte tipoVersao = 3;

    public static byte getTipoVersao() {
        return tipoVersao;
    }

    public static void setTipoVersao(byte tipoVersao) {
        Controle.tipoVersao = tipoVersao;
    }

    /**
     * Define o tipo de login da tela de autenticação
     *
     * @param tipoLogin 1 - Primeiro, 2 - Alterar base
     */
    private static byte tipoLogin = 1;

    public static byte getTipoLogin() {
        return tipoLogin;
    }

    public static void setTipoLogin(byte tipoLogin) {
        Controle.tipoLogin = tipoLogin;
    }

    public static void sair(JFrame frame) {
        tipoLogin = 1;
        TelaAutenticacao ta = new TelaAutenticacao();
        ta.setDefaultCloseOperation(EXIT_ON_CLOSE);
        ta.setLocationRelativeTo(null);
        ta.setVisible(true);
        ta.campoUsuario.setText(TelaAutenticacao.getAtendenteLogado().getLoginAtendente());
        frame.dispose();
    }

    public static void defineStatus(JTextPane status) {
        status.setEditable(false);
        status.setText("GRÁFICA DO EXÉRCITO | USUÁRIO: "
                + TelaAutenticacao.getAtendenteLogado().getNomeAtendente()
                + " | BASE DE DADOS: "
                + Controle.getSelBanco()
                + " | " + Controle.dataPadrao.format(new Date()));
    }

    //ORÇAMENTOS E OP
    public static BaseColor fundoDestaque = new BaseColor(211, 211, 211);
    public static Border bordaLinhaVermelha = BorderFactory.createLineBorder(Color.red);
    public static Border bordaLinhaVerde = BorderFactory.createLineBorder(Color.green);
    public static SimpleDateFormat dataPadrao = new SimpleDateFormat("dd/MM/yyyy");
    public static SimpleDateFormat dataPadraoDiretorio = new SimpleDateFormat("dd-MM-yyyy");
    public static SimpleDateFormat horaPadrao = new SimpleDateFormat("HH:mm:ss");
    public static SimpleDateFormat horaPadraoDiretorio = new SimpleDateFormat("HH-mm-ss");
    public static String tipoDesign = "";
    public static String urlTempWindows = System.getProperty("java.io.tmpdir") + "\\";
    public static String urlTempUnix = System.getProperty("java.io.tmpdir") + "/";
    
    public static List<StsOrcamento> stsOrcamento;
    
    /**
     * Gerente de janelas
     */
    private static GerenteJanelas defaultGj = null;

    public static GerenteJanelas getDefaultGj() {
        return defaultGj;
    }

    public static void setDefaultGj(GerenteJanelas defaultGj) {
        Controle.defaultGj = defaultGj;
    }

    //CONEXÃO COM SERVIDOR ARQUIVOS
    private String host;
    private String usuario;
    private String senha;
    private int porta;

    //CONTROLE DE ARQUIVOS
    public static String ESTOQUE_NAME = "estoque";
    public static String TEMP_DIR = System.getProperty("java.io.tmpdir");
    public static String SENHA_ESTOQUE = "Estoque2566";

    public Controle(String host, String usuario, String senha, int porta) {
        this.host = host;
        this.usuario = usuario;
        this.senha = senha;
        this.porta = porta;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public int getPorta() {
        return porta;
    }

    public void setPorta(int porta) {
        this.porta = porta;
    }

    //DAO
    public static Controle retornaDadosConexao(String senha) throws SQLException {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        Controle retorno = null;
//Estoque2566
        try {
            stmt = con.prepareStatement("SELECT host, usuario, senha, porta FROM tabela_controle WHERE senha = md5(?)");
            stmt.setString(1, senha);
            rs = stmt.executeQuery();
            if (rs.next()) {
                retorno = new Controle(
                        rs.getString("host"),
                        rs.getString("usuario"),
                        senha,
                        rs.getInt("porta")
                );
            } else {
                retorno = null;
            }
        } catch (SQLException ex) {
            throw new SQLException(ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return retorno;
    }

    public static String retornaDirEstoque() throws SQLException {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = con.prepareStatement("SELECT dirEstoque FROM tabela_controle");
            rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getString("dirEstoque");
            }
        } catch (SQLException ex) {
            throw new SQLException(ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return "";
    }
    
    /**
     * Retorna os avisos definidos pelos administradores para edição
     * @return
     * @throws SQLException 
     */
    public static List<String> retornaAvisos() throws SQLException{
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<String> retorno = new ArrayList();
        
        try {
            stmt = con.prepareStatement("SELECT AVISOS_ORC, AVISOS_PROD, AVISOS_EXP, AVISOS_FIN, AVISOS_ORD "
                    + "FROM tabela_controle");
            rs = stmt.executeQuery();
            if (rs.next()) {
                retorno.add(rs.getString("AVISOS_ORC"));
                retorno.add(rs.getString("AVISOS_PROD"));
                retorno.add(rs.getString("AVISOS_EXP"));
                retorno.add(rs.getString("AVISOS_FIN"));
                retorno.add(rs.getString("AVISOS_ORD"));
            }
            return retorno;
        } catch (SQLException ex) {
            throw new SQLException(ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
    }
    
    /**
     * Retorna avisos definidos pelos administradores
     * @return
     * @throws SQLException 
     */
    public static String retornaAvOrc() throws SQLException{
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try{
            stmt = con.prepareStatement("SELECT AVISOS_ORC "
                    + "FROM tabela_controle");
            rs = stmt.executeQuery();
            if(rs.next()){
                return rs.getString("AVISOS_ORC");
            }
            return null;
        } catch (SQLException ex) {
            throw new SQLException(ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
    }
    
    /**
     * Retorna avisos definidos pelos administradores
     * @return
     * @throws SQLException 
     */
    public static String retornaAvExp() throws SQLException{
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try{
            stmt = con.prepareStatement("SELECT AVISOS_EXP "
                    + "FROM tabela_controle");
            rs = stmt.executeQuery();
            if(rs.next()){
                return rs.getString("AVISOS_EXP");
            }
            return null;
        } catch (SQLException ex) {
            throw new SQLException(ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
    }
    
    /**
     * Retorna avisos definidos pelos administradores
     * @return
     * @throws SQLException 
     */
    public static String retornaAvProd() throws SQLException{
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try{
            stmt = con.prepareStatement("SELECT AVISOS_PROD "
                    + "FROM tabela_controle");
            rs = stmt.executeQuery();
            if(rs.next()){
                return rs.getString("AVISOS_PROD");
            }
            return null;
        } catch (SQLException ex) {
            throw new SQLException(ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
    }
    
    /**
     * Retorna avisos definidos pelos administradores
     * @return
     * @throws SQLException 
     */
    public static String retornaAvFin() throws SQLException{
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try{
            stmt = con.prepareStatement("SELECT AVISOS_FIN "
                    + "FROM tabela_controle");
            rs = stmt.executeQuery();
            if(rs.next()){
                return rs.getString("AVISOS_fin");
            }
            return null;
        } catch (SQLException ex) {
            throw new SQLException(ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
    }
    
    /**
     * Retorna avisos definidos pelos administradores
     * @return
     * @throws SQLException 
     */
    public static String retornaAvOrd() throws SQLException{
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try{
            stmt = con.prepareStatement("SELECT AVISOS_ORD "
                    + "FROM tabela_controle");
            rs = stmt.executeQuery();
            if(rs.next()){
                return rs.getString("AVISOS_ORD");
            }
            return null;
        } catch (SQLException ex) {
            throw new SQLException(ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
    }
    
    public static void atualizaAvisos(List<String> avisos) throws SQLException{
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try{
            stmt = con.prepareStatement("UPDATE tabela_controle SET AVISOS_ORC = ?, AVISOS_PROD = ?,"
                    + "AVISOS_EXP = ?, AVISOS_FIN = ?, AVISOS_ORD = ?");
            stmt.setString(1, avisos.get(0));
            stmt.setString(2, avisos.get(1));
            stmt.setString(3, avisos.get(2));
            stmt.setString(4, avisos.get(3));
            stmt.setString(5, avisos.get(4));
            stmt.executeUpdate();
        } catch (SQLException ex) {
            throw new SQLException(ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
    public static boolean verificaVersao(String codigo, String atualizacao) throws SQLException{
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try{
            stmt = con.prepareStatement("SELECT CODIGO, ATUALIZACAO "
                    + "FROM VERSAO "
                    + "WHERE CODIGO != ? OR ATUALIZACAO != ?");
            stmt.setString(1, codigo);
            stmt.setString(2, atualizacao);
            rs = stmt.executeQuery();
            return rs.next();
        } catch (SQLException ex) {
            throw new SQLException(ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
    }

    //STATUS
    public static String getStatusByCod(int codStatus) {
        for (StsOrcamento status : stsOrcamento) {
            if (status.getCod() == codStatus) {
                return status.getStatus();
            }
        }
        return null;
    }

    /**
     * Banco de dados
     *
     * @return
     */
    public static String getSelBanco() {
        switch (tipoVersao) {
            case 1:
                return "PRODUÇÃO";
            case 2:
                return "DEV REDE";
            case 3:
                return "DEV LOCAL";
            default:
                return null;
        }
    }

}
