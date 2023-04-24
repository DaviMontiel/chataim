package com.david.chataim.controller;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.david.chataim.view.login.components.Message;

public class Mail {

	final String username = "davidmontielnieto5@gmail.com";
    final String password = "vylfmqgqxodidbuh";
    
    final static String senderEmail = "davidmontielnieto5@gmail.com"; //change email address
	final static String senderPassword = "vylfmqgqxodidbuh"; //change password
	final static String emailSMTPserver = "smtp.gmail.com";
	final static String emailServerPort = "465";
	
	
	public static boolean send(String receiverEmail, String subject, String body) {
		Properties props = new Properties();
		props.put("mail.smtp.user",senderEmail);
		props.put("mail.smtp.host", emailSMTPserver);
		props.put("mail.smtp.port", emailServerPort);
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.socketFactory.port", emailServerPort);
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.socketFactory.fallback", "false");
		
		try {
			Authenticator auth = new SMTPAuthenticator();
			Session session = Session.getInstance(props, auth);
			MimeMessage msg = new MimeMessage(session);
			
			msg.setContent(body, "text/html");
			msg.setSubject(subject);
			msg.setFrom(new InternetAddress(senderEmail));
			msg.addRecipient(javax.mail.Message.RecipientType.TO, new InternetAddress(receiverEmail));
			Transport.send(msg);
			
			Controller.s().showMessage(Message.MessageType.SUCCESS, LanguageController.getWord(25));
			
			return true;
		}//TRY
		catch (Exception e) { Controller.s().showMessage(Message.MessageType.ERROR, LanguageController.getWord(24)); }//CATCH
		
		return false;
	}//Constructor
	
	public static class SMTPAuthenticator extends javax.mail.Authenticator {
		public PasswordAuthentication getPasswordAuthentication() {
			return new PasswordAuthentication(senderEmail, senderPassword);
		}//FUN
	}//CLASS
}//CLASS