package notif;

import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import api.Keys;
import gui.Assets;
import lang.Dictionary;
import task.Task;
import ui.Button;
import ui.UIConfig;

/**
 * Notification
 */
public class Notification extends JDialog implements Runnable {
 private static final long serialVersionUID = 42l;

 private JPanel panel;
 private Thread UIThread = null;
 
 private static int YPOS = 0;

 private static ArrayList<Notification> notifList = new ArrayList<Notification>();

 public Notification(Task task, boolean expired) {

  setSize(250, 60);
  setLocationRelativeTo(null);
  setLocation(0, -getHeight());

  setUndecorated(true);
  setAlwaysOnTop(true);

//  setLayout(null);
  
  panel = new JPanel(null) {
	  private static final long serialVersionUID = 1l;
	  public void paintComponent(Graphics g) {
		  super.paintComponent(g);
		  g.drawImage(Assets.app_icon.getImage(), 10, 10, getHeight()-20, getHeight()-20, null);
	  }
  };
  panel.setBackground(UIConfig.getThemeColor("table-bg"));
  panel.setBorder(BorderFactory.createLineBorder(UIConfig.getThemeColor("window-border"), 2, false));
  add(panel);
  
  JLabel notif = new JLabel(task.getTitle(), JLabel.LEFT);
  notif.setBounds(getHeight(), 10, getWidth()-getHeight(), 20);
  notif.setFont(Assets.notoFont_Bold);
  notif.setForeground(expired?UIConfig.getThemeColor("fg-error"):UIConfig.getThemeColor("fg-opposite"));
  panel.add(notif);
  
  JLabel description = new JLabel(expired?"[Date expired]":task.getTime(), JLabel.LEFT);
  description.setBounds(getHeight(), notif.getY()+notif.getHeight(), getWidth()-getHeight(), 20);
  description.setFont(Assets.notoFont);
  description.setForeground(UIConfig.getThemeColor("fg-opposite"));
  panel.add(description);

  Button closebtn = new Button("Close", event -> {
	  dispose();
//	  UIThread.interrupt();
  });
  closebtn.setBounds(getWidth()-60, getHeight()/2+5, 55, getHeight()/2-10);
  panel.add(closebtn);
  
  setVisible(true);

  if (task.notifMail() && Keys.loaded) {

   MailNotification mail = new MailNotification(Dictionary.get(Dictionary.notiftitle) + task.getTitle(), task.getTitle());
   mail.send();

  }
  
  if(task.notifWsp() && Keys.loaded) {
	  
	  WspNotification wsp = new WspNotification(Dictionary.get(Dictionary.notiftitle) + task.getTitle());
	  wsp.send();
	  
  }
  
  if(task.notifDesktop()) {
   UIThread = new Thread(this, "Notification UI");
   UIThread.start();
  }else {
   dispose();
  }

 }

 public void run() {

  // moving downwards

  while (getLocation().y < YPOS) {

   setLocation(0, getLocation().y + 1);

   try {
    Thread.sleep(1);
   } catch (Exception ex) {
    ex.printStackTrace();
   }

  }

  YPOS += getHeight();
  notifList.add(this);

  // waiting and letting the user read
  
  try {
   Thread.sleep(12000);
  } catch (Exception ex) {
   System.out.println(ex.getMessage());
  }

  // moving upwards

  while (getLocation().y > -getHeight()) {

   for (int i = 0; i < notifList.size(); i++) {

    notifList.get(i).setLocation(0, notifList.get(i).getLocation().y - 1);

   }

   YPOS--;

   try {
    Thread.sleep(1);
   } catch (Exception ex) {
    ex.printStackTrace();
   }

  }

  setVisible(false);

 }
 
}