package org.fasttrackit.dev.lesson1.numgenerator.servlet;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;


/**
 * Created by user376 on 22.08.2015.
 */
public class SendMail implements Runnable{

    private int nr;
    private int tentative;
    private double timp;

    public SendMail(int nr,int tentative,double timp) {
        this.nr=nr;
        this.tentative=tentative;
        this.timp=timp;
    }
    public void sendmail() {
        Thread t=new Thread();
        t.start();
    }
    public void run() {
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
                demoCreate();
                Transport.send(message);

                System.out.println("done, email sent ok");

            } catch (Exception e) {
                System.out.println("Email sending problems");
                e.printStackTrace();
            }}
    private static void demoCreate() throws ClassNotFoundException, SQLException {

        // 1. load driver
        Class.forName("org.postgresql.Driver");

        // 2. define connection params to db
        final String URL = "jdbc:postgresql://54.93.65.5:5432/Dan_Agenda";
        final String USERNAME = "fasttrackit_dev";
        final String PASSWORD = "fasttrackit_dev";

        // 3. obtain a connection
        Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

        // 4. create a query statement
        PreparedStatement pSt = conn.prepareStatement("INSERT INTO \"Numar\" (Number) VALUES (?)");
        pSt.setString(1, "10");

        // 5. execute a prepared statement
        int rowsInserted = pSt.executeUpdate();

        // 6. close the objects
        pSt.close();
        conn.close();
    }

    /*public static void main(String[] args){

        SendMail mail=new SendMail();
        mail.sendmail();

    }*/
}
