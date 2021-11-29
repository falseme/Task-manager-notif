package event;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import task.TaskManager;

import main.App;

/**
 * UserWindowEvent
 */
public class UserWindowEvent extends WindowAdapter {

 public void windowClosing(WindowEvent e) {

  App.getConfig().serialize();

  TaskManager.saveTasks(); // this method calls 'System.exit(0);'

 }

 public void windowClosed(WindowEvent e) {
  windowClosing(e);
 }

}
