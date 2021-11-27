package event;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import task.TaskManager;

/**
 * UserWindowEvent
 */
public class UserWindowEvent extends WindowAdapter {

 public void windowClosing(WindowEvent e) {

  TaskManager.saveTasks();

 }

}
