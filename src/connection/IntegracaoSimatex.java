/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connection;

import exception.EnvioExcecao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import ui.controle.Controle;

/**
 *
 * @author claud
 */
public class IntegracaoSimatex {
    
    /**
     * Função que realiza a conexão com o banco de dados do simatex
     * @return 
     */
    public static Connection conectaSimatex() {
        Connection connection = null;
        try {
            Class.forName(Controle.DRIVER_FIREBIRD);
            connection = DriverManager.getConnection("jdbc:firebirdsql:" + Controle.SERVIDOR + "/" + Controle.PORTA + ":" + Controle.ENDERECO_BD + Controle.CHARSET, Controle.USUARIO, Controle.SENHA);
        } catch (ClassNotFoundException | SQLException ex) {
            EnvioExcecao envioExcecao = new EnvioExcecao(Controle.getDefaultGj(), ex);
            EnvioExcecao.envio(null);
        }
        return connection;
    }
    
}
