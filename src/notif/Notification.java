package notif;

import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import api.Keys;
import gui.Assets;
import lang.Dictionary;
import task.Task;
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

 public Notification(Task task) {

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
  
  JLabel notif = new JLabel("Notification:", JLabel.LEFT);
  notif.setBounds(getHeight(), 5, getWidth()-getHeight(), 20);
  notif.setFont(Assets.notoFont_Bold);
  notif.setForeground(UIConfig.getThemeColor("fg-opposite"));
  panel.add(notif);

  JLabel title = new JLabel(task.getTitle(), JLabel.LEFT);
  title.setBounds(getHeight(), 25, getWidth()-getHeight(), 20);
  title.setFont(Assets.notoFont_Task);
  title.setForeground(UIConfig.getThemeColor("fg-notif"));
  if(title.getPreferredSize().width > getWidth())
	  title.setHorizontalAlignment(JLabel.LEFT);
  panel.add(title);

  setVisible(true);

  if (task.notifMail() && Keys.loaded) {

   MailNotification mail = new MailNotification(Dictionary.get(Dictionary.notiftitle), task.getTitle());
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
  
  panel.addMouseListener(new MouseAdapter() {
	  public void mouseReleased(MouseEvent e) {
		  dispose();
//		  UIThread.interrupt();
	  }
  });

 }

 public void run() {

  // moving downwards

  while (getLocation().y < YPOS) {

   setLocation(0, getLocation().y + 1);

   try {
    Thread.sleep(5);
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
   System.out.println(ex.getMessage());
  }

  // moving upwards

  while (getLocation().y > -getHeight()) {

   for (int i = 0; i < notifList.size(); i++) {

    notifList.get(i).setLocation(0, notifList.get(i).getLocation().y - 1);

   }

   YPOS--;

   try {
    Thread.sleep(5);
   } catch (Exception ex) {
    ex.printStackTrace();
   }

  }

  setVisible(false);

 }
 
}