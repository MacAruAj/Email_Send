package com.emailSend;

import java.io.File;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.sql.rowset.spi.TransactionalWriter;

public class App 
{
    public static void main( String[] args )
    {
      System.out.println("Preparing to send msg");
        
        String message="Hello., Dear this is for security checvk";
        String subject="This is for checkin purpose.";
        String to="ajay.jaysing96@gmail.com";
         String from="ajay.jaying@gmail.com";
         
         sendEmail(message,subject,to,from);
         
         sendAttach(message,subject,to,from);
         
         
    }
    
    //This method is responsible for msg snd with attachment
    private static void sendAttach(String message, String subject, String to, String from) {
		
    	//main step
    			// variable fvor Gmail host
    			String host="smtp.gmail.com";
    			
    			//get the system properties
    			Properties properties=System.getProperties();
    			System.out.println("Properties :"+ properties);
    			
    			//setting important inf. to properties object
    			
    			properties.put("mail.smtp.host", host);
    			properties.put("mail.smtp.port", "465");
    			properties.put("mail.smtp.ssl.enable", "true");
    			properties.put("mail.smtp.auth", "true");
    			
    			//step:1 to get session obj
    			
    			Session session=Session.getInstance(properties, new Authenticator() {
    				
    				//source-> override implemented method
    				@Override
    				protected PasswordAuthentication getPasswordAuthentication() {
    					
    					return new PasswordAuthentication("ajay.jaysing96@gmail.com", "££££password");
    				}
    	 			
    				
    			});
    			
    			session.setDebug(true);
    			
    			//step:2 compose the message
    			MimeMessage msg=new MimeMessage(session);
    			
    			try {
    			//from email
    			msg.setFrom(from);
    			
    			//adding recepient
    			msg.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
    			
    			//adding subject to msg
    			msg.setSubject(subject);
    			
    			
    			//Attachment
    			
    			//filepath
    			String path="C:\\Users\\Gagan\\Desktop\\stop-wishing-start-doing-motivational-quotes-9mgqjzbpglqk5232.png";
    			
    			MimeMultipart mimeMultipart=new MimeMultipart();
    			
    			//text
    			MimeBodyPart textmime=new MimeBodyPart();
    			
    			
    			//file
    			MimeBodyPart fileMime=new MimeBodyPart();
    			
    			
    			try{
    				textmime.setText(message);
    				
    				File file=new File(path);
    				fileMime.attachFile(file);
    				
    				
    				mimeMultipart.addBodyPart(textmime);
    				mimeMultipart.addBodyPart(fileMime);
    				
    			}catch(Exception e) {
    			e.printStackTrace();	
    			}
    			
    			msg.setContent(mimeMultipart);
    			
    			
    			//step:3 sent the msg through transport class
    			Transport.send(msg);
    			
    			System.out.println("sent sucess........................");
    			
    			}catch(Exception e) {
    				e.printStackTrace();
    			}
   
    			
		
	}
	//This method is responsible for sending mail
	private static void sendEmail(String message, String subject, String to, String from) {
		
		//main step
		// variable fvor Gmail host
		String host="smtp.gmail.com";
		
		//get the system properties
		Properties properties=System.getProperties();
		System.out.println("Properties :"+ properties);
		
		//setting important inf. to properties object
		
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port", "465");
		properties.put("mail.smtp.ssl.enable", "true");
		properties.put("mail.smtp.auth", "true");
		
		//step:1 to get session obj
		
		Session session=Session.getInstance(properties, new Authenticator() {
			
			//source-> override implemented method
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				
				return new PasswordAuthentication("ajay.jaysing96@gmail.com", "££££password");
			}
 			
			
		});
		
		session.setDebug(true);
		
		//step:2 compose the message
		MimeMessage msg=new MimeMessage(session);
		
		try {
		//from email
		msg.setFrom(from);
		
		//adding recepient
		msg.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
		
		//adding subject to msg
		msg.setSubject(subject);
		
		//adding set to subject
		msg.setText(message);
		
		//send
		
		//step:3 sent the msg through transport class
		Transport.send(msg);
		
		System.out.println("sent sucess........................");
		
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
