package notif;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import main.App;

/**
 * MailNotification
 */
public class MailNotification implements Runnable {

 public static Session session;

 private String user, subject, msg;

 public static void init() {

  Properties props = new Properties();
  
  props.put("mail.smtp.host", "smtp.office365.com");
  props.setProperty("mail.smtp.starttls.enable", "true");
  props.put("mail.smtp.ssl.trust", "smtp.office365.com");
  props.setProperty("mail.smtp.port", "587");
  props.setProperty("mail.smtp.user", api.Keys.get("NO_REPLY_USER"));
  props.setProperty("mail.smtp.auth", "true");

  session = Session.getDefaultInstance(props);

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

  try {

   MimeMessage message = new MimeMessage(session);
   message.setFrom(new InternetAddress(api.Keys.get("NO_REPLY_USER")));
   message.addRecipient(Message.RecipientType.TO, new InternetAddress(user));
   message.setSubject(subject + msg);
   message.setText(msg);

   Transport t = session.getTransport("smtp");
   t.connect(api.Keys.get("NO_REPLY_USER"), api.Keys.get("NO_REPLY_PASS"));
   t.sendMessage(message, message.getAllRecipients());
   t.close();

  } catch (Exception e) {
   e.printStackTrace();
  }
   
 }

}
