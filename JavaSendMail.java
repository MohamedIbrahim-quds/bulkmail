package bulkmail;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class JavaSendMail {
	public static void sendmail(String recepient) {
		System.out.println("preparing to send email...");

		Properties properties = new Properties();
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.port", "587");

		String username = "xxxxxxxxxx@gmail.com";
		String password = "xxxxxxxxx";

		Session session = Session.getInstance(properties, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		Message message = prepareMessage(session, username, recepient);

		try {
			Transport.send(message);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		System.out.println("email sent succesfully to " + recepient);
	}

	public static Message prepareMessage(Session session, String username, String recepient) {

		String[] splitted = recepient.split("@");
		String name = splitted[0];

		Message message = new MimeMessage(session);
		try {
			message.setFrom(new InternetAddress(username));
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
			message.setSubject("[....testing mail....]");
			message.setText("Dear " + name + ","
							+ "\nWelcome to my Github Account-  https://github.com/MohamedIbrahim-quds");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return message;
	}
}
