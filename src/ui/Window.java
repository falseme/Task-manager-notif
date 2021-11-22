package ui;

import javax.swing.JFrame;

import task.TaskManager;
import task.Task;

public class Window extends JFrame {
 private static final long serialVersionUID = 42l;

 private WeekPanel weekPanel;

 public Window() {

  setInitParams();

  weekPanel = new WeekPanel();
  add(weekPanel);

 }

 private void setInitParams() {

  setTitle("TaskManager");
  setSize(800, 600);
  setLocationRelativeTo(null);
  // setResizable(false);

  setDefaultCloseOperation(EXIT_ON_CLOSE);

 }

 public void updateTasks(int dayOfWeek) {

  weekPanel.updateTasks(dayOfWeek);

 }

 public void updateTask(int dayOfWeek, Task task) {

  weekPanel.updateTask(dayOfWeek, task);

 }

}
