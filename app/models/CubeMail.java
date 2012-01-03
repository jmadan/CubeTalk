package models;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import play.libs.Mail;

public class CubeMail extends Mail{

    private static String subject;

    public static void feedback(String about, String subject, String userName, String userEmail, String message) {
        SimpleEmail email = new SimpleEmail();
        try {
            email.setFrom(userEmail, userName);
            email.addTo("jasdeepm@gmail.com", "Jasdeep Madan");
            email.setMsg(message);
            Mail.send(email);
        } catch (EmailException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

    }

}