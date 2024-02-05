package utility;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailSender {
	
	private static final String HOST = "smtp.gmail.com";
	private static final int PORT = 587;
	private static final String USERNAME = "";
	private static final String PASSWORD = "";
	
	public static void sendEmail(String recipient, String subject, String content)
			throws MessagingException {
		Properties properties = new Properties();
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.host", HOST);
		properties.put("mail.smtp.port", PORT);
		
		Session session = Session.getInstance(properties, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(USERNAME, PASSWORD);
			}
		});
		
		Message message = new MimeMessage(session);
		message.setFrom(new InternetAddress(USERNAME));
		message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
		message.setSubject(subject);
		message.setText(content);
		
		Transport.send(message);
	}

}
