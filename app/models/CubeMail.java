package models;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import play.libs.Mail;

public class CubeMail extends Mail{

    private static String subject;

    public static void feedback(String about, String subject, String userName, String userEmail, String message) {
        
        String emailSubject = "Reg. " + about;
        String emailBody = subject + ",\n\n" + message + "\n\nRegards,\n" + userName;
        SimpleEmail email = new SimpleEmail();
        try {
            email.setFrom(userEmail, userName);
            email.addTo("jasdeepm@gmail.com", "Jasdeep Madan");
            email.setSubject(emailSubject);
            email.setMsg(emailBody);
            Mail.send(email);
        } catch (EmailException e) {
            e.printStackTrace();
        }

    }

    public static void forgotPassword(User user) {
        String emailSubject = "your request for your Password ";
        String emailBody = " Your Password is as follows \n Password: "+ user.password + "\n\nRegards,\nCube-Talk.com";
        SimpleEmail email = new SimpleEmail();
        try {
            email.setFrom("admin@cube-talk.com");
            email.addTo(user.userEmail, user.userAlias);
            email.setSubject(emailSubject);
            email.setMsg(emailBody);
            Mail.send(email);
        } catch (EmailException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }
}