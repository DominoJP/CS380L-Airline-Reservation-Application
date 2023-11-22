import java.net.Authenticator;
import java.net.InetAddress;
import java.net.PasswordAuthentication;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
//import javax.mail.Session;
//import javax.mail.Message;
//import javax.mail.Transport;
//import javax.mail.internet.InternetAddress;
//import javax.mail.internet.MimeMessage;

 /** 
  * a) Design Documentation: "Java Mail"
  * b) Date of creation: JavaMail is a program that facilitates 
  * c) Programmer's name: Sayra Reyes
  * d) Description: This class facilitates the use of sending emails using JavaMail API. 
       It allows configuration of sender and recipient email addresses, 
       sets up the properties for the email server, constructs an email message 
       and it sends it to the recipient’s email address.
  * e) Functions: sendMail() : sets up the necessary properties for the email server, 
  * 	creates a session usig the sender's credentials with a message and send's
  * 	to the recipient email address. 
  * f) Data Structures: N/A
  * g) Algorithm: N/A
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

