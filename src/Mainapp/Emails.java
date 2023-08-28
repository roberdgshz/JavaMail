/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Mainapp;

import java.awt.Color;
import java.awt.Font;
import java.io.File;
import java.util.Date;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author lalo_
 */
public class Emails {
    public static void addFontStyle(JTextField txtF)
    {
        Font f1 = txtF.getFont();
        f1 = f1.deriveFont(Font.ITALIC);
        txtF.setFont(f1);
        txtF.setForeground(Color.gray);
    }
    
    public static void removeFontStyle(JTextField txtF)
    {
        Font f1 = txtF.getFont();
        f1 = f1.deriveFont(Font.PLAIN|Font.BOLD);
        txtF.setFont(f1);
        txtF.setForeground(Color.black);
    }
    
    public static void GmailSender(String EmailS,String Password,String Subject,String MessG,String EmailR,File[] fileF) throws AddressException, MessagingException
    {
        
        Properties Prop = new Properties();
        Prop.put("mail.smtp.auth",true);
        Prop.put("mail.smtp.starttls.enable", true);
        Prop.put("mail.smtp.host", "smtp.gmail.com");
        Prop.put("mail.smtp.port", "587");
        Prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        Prop.put("mail.smtp.ssl.protocols", "TLSv1.2");
        
        Session sess = Session.getDefaultInstance(Prop, 
                new javax.mail.Authenticator(){
            @Override
            protected PasswordAuthentication getPasswordAuthentication(){
                return new PasswordAuthentication(EmailS,Password);
            }
        });

        MimeMessage Mess = new MimeMessage(sess);
        Mess.setSubject(Subject);
        //Mess.setContent(MessG, "text/plain");
        Mess.setFrom(new InternetAddress(EmailS));
        Mess.setRecipient(RecipientType.TO,new InternetAddress(EmailR));
        Mess.setSentDate(new Date()); 
        
        //Files
        Multipart multiP = new MimeMultipart();
        
        BodyPart BP = new MimeBodyPart();
        BP.setText(MessG);
        multiP.addBodyPart(BP);
        
        for(File file:fileF)
        {
            DataSource Ds = new FileDataSource(file);
            MimeBodyPart Mbp = new MimeBodyPart();
            Mbp.setDataHandler(new DataHandler(Ds));
            Mbp.setFileName(file.getName());
            multiP.addBodyPart(Mbp);
        }
        
        Mess.setContent(multiP);
        Transport.send(Mess);
        JOptionPane.showMessageDialog(null, "Message sent to:"+EmailR+"!");
    }
    
    public static void GmailSender(String EmailS,String Password,String Subject,String MessG,String EmailR) throws AddressException, MessagingException
    {
        Properties Prop = new Properties();
        Prop.put("mail.smtp.auth",true);
        Prop.put("mail.smtp.starttls.enable", true);
        Prop.put("mail.smtp.host", "smtp.gmail.com");
        Prop.put("mail.smtp.port", "587");
        Prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        Prop.put("mail.smtp.ssl.protocols", "TLSv1.2");
        
        Session sess = Session.getDefaultInstance(Prop, 
                new javax.mail.Authenticator(){
            @Override
            protected PasswordAuthentication getPasswordAuthentication(){
                return new PasswordAuthentication(EmailS,Password);
            }
        });

        MimeMessage Mess = new MimeMessage(sess);
        Mess.setSubject(Subject);
        Mess.setContent(MessG, "text/plain");
        Mess.setFrom(new InternetAddress(EmailS));
        Mess.setRecipient(RecipientType.TO,new InternetAddress(EmailR));
        Mess.setSentDate(new Date()); 
        
        Transport.send(Mess);
        JOptionPane.showMessageDialog(null, "Message sent to:"+EmailR+"!");
    }
}
