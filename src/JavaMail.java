import java.net.Authenticator;
import java.net.InetAddress;
import java.net.PasswordAuthentication;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Session;
import javax.mail.Message;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


 /** Java program to send email with message. 
   * TO DO: Need to download Java Mail : after add that library to the project . 
   * @author Sayra Reyes
   * @version 1.0
 **/

public class JavaMail {
	public static void main (String[] args) {
		sendMail();
	}
	
	public static void sendMail() {
		//Sender's email address and password
		String senderEmail = "sender@gmail.com";
		String senderPassword = "password";
		
		//Recipient's email address
		String recipientEmail = "recipient@gmail.com";
		
		//JavaMail properties configuration
		Properties properties = new Properties();
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.port", "587");
		
		//Create a session 
		Session sessionobj = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmail, senderPassword);
            }
        });
		
		try {
				MimeMessage message = new MimeMessage(sessionobj);
				message.setFrom(new InternetAddress(senderEmail));
				message.addRecipient(message.RecipientType.TO, new InternetAddress(recipientEmail));
			
				message.setSubject("Ticket Information");
				message.setText("Thank you for choosing to fly with us! We wish a pleasant and comfortable journey! ");
				
				Transport.send(message);
				
				System.out.println("Email sent successfully!");
			
		}catch(MessagingException e) {
			
		}
	}
	
}