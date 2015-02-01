package com.unalsoft.elitefle.businesslogic.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Edward
 */
public class MailService {

    public boolean sendEmail(HashMap<String, String> recipients, String activityName, String activityURL) throws Exception {

        String fromEmail = "noreply.ELiteFLE2@gmail.com";
        String fromName = "ELite [FLE]2 Admin";
        String password = "jshYBV53Gts";
        String host = "smtp.gmail.com";
        String subject = "Vous êtes invité à faire une nouvelle activité";

        Properties props = new Properties();
        props.setProperty("mail.smtp.host", host);
        props.setProperty("mail.smtp.starttls.enable", "true");
        props.setProperty("mail.smtp.port", "587");
        props.setProperty("mail.smtp.auth", "true");
        
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    String username = "noreply.ELiteFLE2@gmail.com";
                    String password = "jshYBV53Gts";

                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        String msgBody = 
                "<p>Bonjour,</p>\n"
                + "\n      <p>Vous êtes invité à faire l'activité " + activityName + "</p>"
                + "\n      <p>Pour y acceder, veuillez cliquer sur le lien suivant: </p>\n"
                + "<a href=" + activityURL + ">" + activityURL + "</a>";

        try {
            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(fromEmail, fromName));

            for (Map.Entry<String, String> entry : recipients.entrySet()) {
                msg.addRecipient(Message.RecipientType.TO,
                        new InternetAddress(entry.getKey(), entry.getValue()));
            }

            msg.setSubject(subject);
            msg.setContent(msgBody, "text/html");
            
            Transport t = session.getTransport("smtp");
            t.connect(fromEmail, password);
            t.sendMessage(msg, msg.getAllRecipients());

            return true;

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

}
