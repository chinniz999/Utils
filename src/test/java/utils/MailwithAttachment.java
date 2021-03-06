package utils;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

import org.testng.annotations.Test;

import javax.activation.*;

public class MailwithAttachment {

	@Test
	public void sendMailwithAttachmnet() {

		String to = "chinniz999@gmail.com";// change accordingly
		final String user = "chinniz999@gmail.com";// change accordingly
		final String password = "chowdary@9669";// change accordingly

		// 1) get the session object
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");

		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(user, password);
			}
		});

		// 2) compose message
		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(user));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			message.setSubject("Message Aleart");

			// 3) create MimeBodyPart object and set your message text
			BodyPart messageBodyPart1 = new MimeBodyPart();
			messageBodyPart1.setText("C:\\Users\\Narayana\\Desktop\\Config\\XSLT_Reports\\output\\index.html");

			// 4) create new MimeBodyPart object and set DataHandler object to
			// this object
			MimeBodyPart messageBodyPart2 = new MimeBodyPart();

			String filename = "C:\\Users\\Narayana\\Desktop\\Config\\test-output\\getCurrentURL.csv";// change
																															// accordingly
			DataSource source = new FileDataSource(filename);
			messageBodyPart2.setDataHandler(new DataHandler(source));
			messageBodyPart2.setFileName(filename);

			// 5) create Multipart object and add MimeBodyPart objects to this
			// object
			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(messageBodyPart1);
			multipart.addBodyPart(messageBodyPart2);

			// 6) set the multiplart object to the message object
			message.setContent(multipart);

			// 7) send message
			Transport.send(message);

			System.out.println("message sent....");
		} catch (MessagingException ex) {
			ex.printStackTrace();
		}
	}

}
