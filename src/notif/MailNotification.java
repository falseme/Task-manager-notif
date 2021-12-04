package notif;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import main.App;

/**
 * MailNotification
 */
public class MailNotification implements Runnable {

 public static Session session;

 private static String noreply;
 private static String pass;

 private String user, subject, msg;

 public static void init() {

  Properties props = new Properties();
  props.setProperty("mail.smtp.host", "smtp.gmail.com");
  props.setProperty("mail.smtp.starttls.enable", "true");
  props.setProperty("mail.smtp.port", "587");
  props.setProperty("mail.smtp.auth", "true");

  session = Session.getDefaultInstance(props);

  noreply = App.getConfig().getNoreplyMail();
  pass = App.getConfig().getNoreplyMailPass();

 }

 public MailNotification(String subject, String msg) {

  this.subject = subject;
  this.msg = msg;

  user = App.getConfig().getUserMail();

 }

 public void send() {

  if (user == null || user.isEmpty())
   return;

  Thread thr = new Thread(this);
  thr.start();

 }

 public void run() {

  MimeMessage mail = new MimeMessage(session);

  try {

   mail.setFrom(new InternetAddress(noreply));
   mail.setRecipient(Message.RecipientType.TO, new InternetAddress(user));
   mail.setSubject(subject);
   mail.setText(msg);

   Transport transport = session.getTransport("smtp");
   transport.connect(noreply, pass);
   transport.sendMessage(mail, mail.getRecipients(Message.RecipientType.TO));

   transport.close();

  } catch (AddressException e) {
   e.printStackTrace();
  } catch (MessagingException e) {
   e.printStackTrace();
  }

 }

}
