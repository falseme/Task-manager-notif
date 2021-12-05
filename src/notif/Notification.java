package notif;

import java.util.ArrayList;

import javax.swing.JDialog;
import javax.swing.JLabel;

import lang.Dictionary;

import task.Task;

/**
 * Notification
 */
public class Notification extends JDialog implements Runnable {
 private static final long serialVersionUID = 42l;

 private static int YPOS = 0;

 private static ArrayList<Notification> notifList = new ArrayList<Notification>();

 public Notification(Task task) {

  setSize(200, 50);
  setLocationRelativeTo(null);
  setLocation(0, -getHeight());

  setUndecorated(true);
  setAlwaysOnTop(true);

  setLayout(null);

  JLabel notif = new JLabel("Notification:", JLabel.LEFT);
  notif.setBounds(0, 0, 200, 20);
  add(notif);

  JLabel title = new JLabel(task.getTitle(), JLabel.CENTER);
  title.setBounds(0, 20, 200, 20);
  add(title);

  setVisible(true);

  Thread thread = new Thread(this, "Notification UI");
  thread.start();

  if (task.notifMail()) {

   MailNotification mail = new MailNotification(Dictionary.get(Dictionary.notiftitle), task.getTitle());
   mail.send();

  }

 }

 public void run() {

  // moving downwards

  while (getLocation().y < YPOS) {

   setLocation(0, getLocation().y + 1);

   try {
    Thread.sleep(10);
   } catch (Exception ex) {
    ex.printStackTrace();
   }

  }

  YPOS += getHeight();
  notifList.add(this);

  // waiting and letting the user read

  try {
   Thread.sleep(7000);
  } catch (Exception ex) {
   ex.printStackTrace();
  }

  // moving upwards

  while (getLocation().y > -getHeight()) {

   for (int i = 0; i < notifList.size(); i++) {

    notifList.get(i).setLocation(0, notifList.get(i).getLocation().y - 1);

   }

   YPOS--;

   try {
    Thread.sleep(10);
   } catch (Exception ex) {
    ex.printStackTrace();
   }

  }

  setVisible(false);

 }

}
