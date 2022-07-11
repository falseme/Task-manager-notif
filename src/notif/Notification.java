package notif;

import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import gui.Assets;
import lang.Dictionary;
import task.Task;
import ui.UIConfig;

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

//  setLayout(null);
  
  JPanel panel = new JPanel(null);
  panel.setBackground(UIConfig.getThemeColor("table-bg"));
  panel.setBorder(BorderFactory.createLineBorder(UIConfig.getThemeColor("window-border"), 2, true));
  add(panel);
  
  JLabel notif = new JLabel("Notification:", JLabel.LEFT);
  notif.setBounds(10, 0, 200, 20);
  notif.setFont(Assets.notoFont_Underlined);
  notif.setForeground(UIConfig.getThemeColor("fg-notif"));
  panel.add(notif);

  JLabel title = new JLabel(" " + task.getTitle(), JLabel.CENTER);
  title.setBounds(0, 20, 200, 20);
  title.setFont(Assets.notoFont_Task);
  title.setForeground(UIConfig.getThemeColor("fg-opposite"));
  if(title.getPreferredSize().width > getWidth())
	  title.setHorizontalAlignment(JLabel.LEFT);
  panel.add(title);

  setVisible(true);

  Thread thread = new Thread(this, "Notification UI");
  thread.start();

  if (task.notifMail()) {

   MailNotification mail = new MailNotification(Dictionary.get(Dictionary.notiftitle), task.getTitle());
   mail.send();

  }
  
  if(task.notifWsp()) {
	  
	  WspNotification wsp = new WspNotification(Dictionary.get(Dictionary.notiftitle) + task.getTitle());
	  wsp.send();
	  
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