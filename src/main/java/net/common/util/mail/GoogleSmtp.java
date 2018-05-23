package net.common.util.mail;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class GoogleSmtp implements Mail {

	final String username = "issuebreakers@gmail.com";
	final String password = "issue123";

	public void send(String toAddress, String subject, String msg) {

		Properties props = new Properties();
		props.put("mail.smtp.user",username); 
		props.put("mail.smtp.password",password);
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "25");
		props.put("mail.debug", "true");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.EnableSSL.enable", "true");
		props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.setProperty("mail.smtp.socketFactory.fallback", "false");
		props.setProperty("mail.smtp.port", "465");
		props.setProperty("mail.smtp.socketFactory.port", "465");

		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});
		
		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(username));//
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toAddress));
			message.setSubject(subject);
			message.setText(msg);// 내용
			message.setContent(msg,"text/html; charset=utf-8");//글내용을 html타입
			
			// charset설정
			System.out.println("send!!!");
			Transport.send(message);
			System.out.println("SEND");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
