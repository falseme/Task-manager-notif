package notif;

import javax.swing.JDialog;
import javax.swing.JLabel;

import task.Task;

/**
 * Notification
 */
public class Notification extends JDialog implements Runnable {
 private static final long serialVersionUID = 42l;

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

  Thread thread = new Thread(this);
  thread.start();

 }

 public void run() {

  // moving downwards

  while (getLocation().y < 0) {

   setLocation(0, getLocation().y + 1);

   try {
    Thread.sleep(10);
   } catch (Exception ex) {
    ex.printStackTrace();
   }

  }

  // waiting and letting the user read

  try {
   Thread.sleep(7000);
  } catch (Exception ex) {
   ex.printStackTrace();
  }

  // moving upwards

  while (getLocation().y > -getHeight()) {

   setLocation(0, getLocation().y - 1);

   try {
    Thread.sleep(10);
   } catch (Exception ex) {
    ex.printStackTrace();
   }

  }

  setVisible(false);

 }

}
