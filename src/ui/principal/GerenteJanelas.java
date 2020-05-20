/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.principal;

import java.beans.PropertyVetoException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;

/**
 *
 * @author 1113778771
 */
public class GerenteJanelas {

    private static JDesktopPane jDesktopPane;

    public GerenteJanelas(JDesktopPane jDesktopPane) {
        GerenteJanelas.jDesktopPane = jDesktopPane;
    }

    public void abrirJanelas(JInternalFrame jInternalFrame, String title) {
        if (jInternalFrame.isVisible()) {
            jInternalFrame.toFront();
            jInternalFrame.requestFocus();
        } else if (jInternalFrame.isIconifiable()) {
            jDesktopPane.add(jInternalFrame);
            jInternalFrame.setVisible(true);
            jInternalFrame.requestFocus();
        } else {
            jDesktopPane.add(jInternalFrame);
            jInternalFrame.setVisible(true);
            jInternalFrame.setMaximizable(true);
            jInternalFrame.setClosable(true);
            jInternalFrame.setIconifiable(true);
            jInternalFrame.setTitle(title);
            jInternalFrame.setResizable(true);
            jInternalFrame.setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
        }
    }

    public void fecharJanelas(Object fechar) {
        try {
            for(JInternalFrame frame : jDesktopPane.getAllFrames()){
                if(frame.getClass().equals(fechar)){
                    frame.setClosed(true);
                }
            }
        } catch (PropertyVetoException ex) {
            Logger.getLogger(GerenteJanelas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static JDesktopPane getjDesktopPane() {
        return jDesktopPane;
    }

    public static void setjDesktopPane(JDesktopPane jDesktopPane) {
        GerenteJanelas.jDesktopPane = jDesktopPane;
    }
}
