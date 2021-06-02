package logic;

import entities.SalesPerson;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;
import java.util.Properties;

public class Notify {public static void sendEmail () throws Exception {

    final SalesPerson salesPerson = new SalesPerson();
    final String emailId = "dawgen@gmail.com"; //sender's email address
    final String salesManager = "lars_nielsen89@hotmail.com"; //salesManager's email address
    final String password = "mhantewnehtwtopk";      //provide your password here


    System.out.println("Sending Email from " + emailId + " to " + salesManager);

    Properties pr = new Properties();

    pr.put("mail.smtp.auth", "true");    //for username and password authentication
    pr.put("mail.smtp.starttls.enable", "true");
    pr.put("mail.smtp.host", "smtp.gmail.com");  //here host is gmail.com
    pr.put("mail.smtp.port", "587");             //port no.

    Session gs = Session.getInstance(pr, new Authenticator() {
        @Override
        protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(emailId, password);  //pass your email id and password here

        }
    });

    Message ms = messageContent(gs, emailId, salesManager);

    System.out.println("Message sent! ");

}

    private static Message messageContent(Session session, String emailId, String reciever) throws Exception {
        try {
            String csv = "Lars;Nielsen;500";

            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(emailId));
            msg.setRecipient(Message.RecipientType.TO, new InternetAddress(reciever));
            msg.setSubject("Tilbud til godkendelse"); //to set the subject (not mandatory)
            msg.setText("Der er et tilbud der venter på godkendelse, kontakt venligst kontoret. " + "\n" +
            "Med venlig hilsen Ferrari Autoforhandler.");

            Transport.send(msg);
            return msg;
        } catch (MessagingException e) {
            System.out.println(e);
        }

        return null;

    }
}