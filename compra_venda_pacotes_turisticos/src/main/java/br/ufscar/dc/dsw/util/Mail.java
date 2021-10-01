package br.ufscar.dc.dsw.util;

import java.util.*;  
import javax.mail.*;  
import javax.mail.internet.*; 

public class Mail {

    public void sendMail(String destinatario) {

        String remetente = "pacote_turistico@noreply.com";
        String host = "localhost";
        String port = "465";

        String mensagem = "Olá,\n\nSua compra de pacote foi realizada com sucesso.\n\n";
        mensagem = mensagem + "Para ajustarmos os últimos detalhes na reunião às 13:00 de amanhã: https://meet.google.com/kse-kcvm-ztr\n\n";
        mensagem = mensagem + "Atensiosamente,\nEquipe Pacote Turistico Digital";

        Properties properties = new Properties();
        properties.setProperty("mail.smtp.host", host);
        properties.setProperty("mail.smtp.port", port);

        Session session = Session.getDefaultInstance(properties);

        try {
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(destinatario));

        message.addRecipient(Message.RecipientType.TO,new InternetAddress(remetente));  

        message.setSubject("Compra de Pacote Turistico Realizada");
        message.setText(mensagem);
        Transport.send(message);

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
