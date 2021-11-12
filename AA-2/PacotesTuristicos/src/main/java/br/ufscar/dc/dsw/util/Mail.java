package br.ufscar.dc.dsw.util;

import java.util.*;  
import javax.mail.*;  
import javax.mail.internet.*; 

public class Mail {
    
    public void sendMail(String destinatario) {

        String remetente = "dsw1.agencia.turismo@gmail.com";
        String senha = "agenciaturismo";
        String host = "smtp.gmail.com";
        
        String subject = "Compra de Pacote Turistico Realizada";
        String mensagem = "Olá,\n\nSua compra de pacote foi realizada com sucesso.\n\n";
        mensagem = mensagem + "Para ajustarmos os últimos detalhes, entre na reunião às 13:00 de amanhã: https://meet.google.com/kse-kcvm-ztr\n\n";
        mensagem = mensagem + "Atensiosamente,\nEquipe Pacote Turistico Digital";

        Properties properties = System.getProperties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");
        
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication(){
                return new PasswordAuthentication(remetente, senha);
            }
        });

        session.setDebug(true);

        try {

            MimeMessage message = new MimeMessage(session);

            message.setFrom(new InternetAddress(destinatario));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(destinatario));
            message.setSubject(subject);
            message.setText(mensagem);

            Transport.send(message);

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}