/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.orcamentos.email;

import entities.sisgrafex.Orcamento;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import ui.principal.OrcamentoFrame;

/**
 *
 * @author claud
 */
public class Email {

    public static void main(String[] args) {
            enviarEmail(Orcamento.geraPdf(2997,
                    true,
                    Boolean.FALSE,
                    1,
                    OrcamentoFrame.loading,
                    Boolean.TRUE), "Teste");
            System.out.println("email enviado");
    }
    
    public static void enviarEmail(String caminhoPdf,
            String mensagem){
        try {
            // Create the email message
            SimpleEmail email = new SimpleEmail();
            email.setDebug(true);
            email.setStartTLSEnabled(true);
            email.setHostName("SMTP.office365.com");
            email.setSmtpPort(587);
            email.setAuthentication("claudiojunior966@hotmail.com.br", 
                    "01031999");
            email.setSSL(false);
            email.setTLS(true);
            email.addTo("claudiojunior966@hotmail.com.br");
            email.setFrom("claudiojunior966@hotmail.com.br");
            email.setSubject("orçamento");
            email.setMsg(mensagem);
            email.send();
            
            // Create the attachment
//            EmailAttachment attachment = new EmailAttachment();
//            attachment.setPath(caminhoPdf);
//            attachment.setName("Gráfica");
            
            
            
            
            
            
            // add the attachment
            //email.attach(attachment);
            
            // send the email
            
        } catch (EmailException ex) {
            System.out.println("erro " + ex);
            ex.printStackTrace();
        }
    }
}
