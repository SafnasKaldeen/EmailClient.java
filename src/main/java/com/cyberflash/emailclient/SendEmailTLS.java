package com.cyberflash.emailclient;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

//creating an Class named SendEmailTLS to send an email by creating a new E_Mail object
public class SendEmailTLS {

    //Constructor for the SendEmailTLS class
    public static void SendCustomMail(E_Mail email) {
        String Email = email.getTarget_Email();
        String Subject = email.getSubject();
        String Content = email.getMessage();

        //Source EMail and Decrypted pseudo Password.
        final String username = "safnastemp2000@gmail.com";
        final String password = "zbmmonpphdwjzhwp";

        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true"); //TLS

        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("safnastemp2000@gmail.com"));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(Email)
            );
            message.setSubject(Subject);
            message.setText(Content);

            Transport.send(message);

            System.out.println("E-Mail sent successfully \n");
            Encryption_Class.Serialize_Email(email);

        //Exception handling
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}

