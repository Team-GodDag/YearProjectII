package logic;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class Notify {public static void sendEmail () throws Exception {
    // Lavet af Lars

    final String emailId = "dawgen@gmail.com";
    final String salesManager = "lars_nielsen89@hotmail.com";
    final String password = "mhantewnehtwtopk";


    System.out.println("Sending Email from " + emailId + " to " + salesManager);

    Properties pr = new Properties();

    pr.put("mail.smtp.auth", "true");
    pr.put("mail.smtp.starttls.enable", "true");
    pr.put("mail.smtp.host", "smtp.gmail.com");
    pr.put("mail.smtp.port", "587");

    Session gs = Session.getInstance(pr, new Authenticator() {
        @Override
        protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(emailId, password);

        }
    });

    Message ms = messageContent(gs, emailId, salesManager);

    System.out.println("Message sent! ");

}

    private static Message messageContent(Session session, String emailId, String reciever) throws Exception {
        try {

            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(emailId));
            msg.setRecipient(Message.RecipientType.TO, new InternetAddress(reciever));
            msg.setSubject("Tilbud til godkendelse");
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