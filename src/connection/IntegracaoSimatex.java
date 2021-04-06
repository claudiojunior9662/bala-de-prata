/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connection;

import exception.EnvioExcecao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import ui.controle.Controle;

/**
 *
 * @author claud
 */
public class IntegracaoSimatex {

    //VARIÁVEIS PARA CONEXÃO COM O SERVIDOR FIREBIRD
    final static private String DRIVER_FIREBIRD = "org.firebirdsql.jdbc.FBDriver";
    private static String CHARSET = "?encoding=ISO8859_1";
    private static String SERVIDOR = null;
    private static String PORTA = null;
    private static String USUARIO = null;
    private static String SENHA = null;
    private static String ENDERECO_BD = null;
    
    /**
     * Preenche os dados para a conexão com o banco de dados do Simatex, com parâmetros do banco de dados
     */
    public static void preencheDadosConexaoSimatex() {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = con.prepareStatement("SELECT SRV_SIMATEX, " + "PORTA_SIMATEX, " + "USR_SIMATEX, " + "SENHA_SIMATEX, " + "END_BD_SIMATEX " + "FROM tabela_controle");
            rs = stmt.executeQuery();
            if (rs.next()) {
                SERVIDOR = rs.getString("SRV_SIMATEX");
                PORTA = String.valueOf(rs.getInt("PORTA_SIMATEX"));
                USUARIO = rs.getString("USR_SIMATEX");
                SENHA = rs.getString("SENHA_SIMATEX");
                ENDERECO_BD = rs.getString("END_BD_SIMATEX");
            }
        } catch (SQLException ex) {
            EnvioExcecao envioExcecao = new EnvioExcecao(Controle.getDefaultGj(), ex);
            EnvioExcecao.envio();
        }
    }

    /**
     * Função que realiza a conexão com o banco de dados do simatex
     * @return 
     */
    public static Connection conectaSimatex() {
        preencheDadosConexaoSimatex();
        Connection connection = null;
        try {
            Class.forName(DRIVER_FIREBIRD);
            connection = DriverManager.getConnection("jdbc:firebirdsql:" + SERVIDOR + "/" + PORTA + ":" + ENDERECO_BD + CHARSET, USUARIO, SENHA);
        } catch (ClassNotFoundException | SQLException ex) {
            EnvioExcecao envioExcecao = new EnvioExcecao(Controle.getDefaultGj(), ex);
            EnvioExcecao.envio();
        }
        return connection;
    }
    
}
