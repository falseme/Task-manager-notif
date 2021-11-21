package ui;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JComponent;

import task.Task;

import ui.layout.TaskListLayout;

public class TaskList extends JComponent {
 private static final long serialVersionUID = 42l;

 private boolean drawBorder;

 private Label dayLabel;

 private int listLength = 0;

 public TaskList(String dayName, boolean drawBorder, int order) {

  setLayout(new TaskListLayout());

  dayLabel = new Label(dayName, Label.CENTER, order);
  add(dayLabel);

  this.drawBorder = drawBorder;

 }

 public void paintComponent(Graphics g) {

  if (!drawBorder)
   return;

  int gap = 10;

  g.setColor(Color.GRAY);
  g.drawLine(0, gap, 0, getHeight() - gap);

 }

 public void addTask(Task task) {

  add(task);
  listLength++;
  getLayout().layoutContainer(this);
  task.getLayout().layoutContainer(task);

 }

 public void removeTask(Task task) {

  remove(task);
  listLength--;
  getLayout().layoutContainer(this);

 }

 public int getListLength() {
  return listLength;
 }

}
