package org.fasttrackit.dev.lesson1.numgenerator.servlet;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;


/**
 * Created by user376 on 22.08.2015.
 */
public class SendMail {
    public void sendmail(int nr,int tentative,double timp) {
        final String username = "lemonteamcode@gmail.com";
        final String password = "fastrackit";
    Properties props = new Properties();
    props.put("mail.smtp.auth", "true");
    props.put("mail.smtp.starttls.enable", "true");
    props.put("mail.smtp.host", "smtp.gmail.com");
    props.put("mail.smtp.port", "587");

    Session session = Session.getInstance(props,
            new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                }
            });
    try {

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress("lemonteamcode@gmail.com"));
        message.setRecipients(Message.RecipientType.TO,
                InternetAddress.parse("lemonteamcode@gmail.com"));
        message.setSubject("Num-guess");
        message.setText("Congratulation !"+"nr ghicit: "+nr+" ,tentative: "+tentative+" ,timp: "+timp);

        Transport.send(message);

        System.out.println("done, email sent ok");

    } catch (Exception e) {
        System.out.println("Email sending problems");
        e.printStackTrace();
    }}
    /*public static void main(String[] args){

        SendMail mail=new SendMail();
        mail.sendmail();

    }*/
}
