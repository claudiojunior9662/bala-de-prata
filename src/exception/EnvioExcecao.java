/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exception;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import ui.erros.ErroBEAN;
import ui.erros.ErroDAO;
import ui.login.TelaAutenticacao;
import ui.principal.GerenteJanelas;

/**
 *
 * @author claud
 */
public class EnvioExcecao {

    private static Exception ex = null;
    private static GerenteJanelas gj;

    private static EnvioExcecao envioExcecao;

    public static EnvioExcecao getInstancia(GerenteJanelas gj, Exception ex) {
        return new EnvioExcecao(gj, ex);
    }

    public EnvioExcecao(GerenteJanelas gj, Exception ex) {
        EnvioExcecao.gj = gj;
        this.ex = ex;
    }

    public static void envio() {
        try {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            ex.printStackTrace(pw);
            ErroBEAN bean = new ErroBEAN(ex.toString() + "\n\n" + sw.toString(),
                    "EM ANÁLISE",
                    TelaAutenticacao.getAtendenteLogado().getCodigoAtendente());
            ErroDAO.insereErro(bean);
            JOptionPane.showMessageDialog(null,
                    "ORCORREU UM ERRO AO REALIZAR ESTA AÇÃO.\n"
                    + "A EXCEÇÃO FOI ENVIADA PARA A ANÁLISE.",
                    "ATENÇÃO!",
                    JOptionPane.ERROR_MESSAGE);
        } catch (SQLException ex) {
            Logger.getLogger(EnvioExcecao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
