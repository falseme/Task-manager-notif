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

 private TaskListLayout layout;

 public TaskList(String dayName, boolean drawBorder, int order) {

  layout = new TaskListLayout();
  setLayout(layout);

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

  // long init = System.nanoTime();

  add(task);
  listLength++;

  layout.sort(this);

  layout.layoutContainer(this);
  task.getLayout().layoutContainer(task);

  // long end = System.nanoTime();
  // double delta = end - init;
  // delta /= 1000000000;
  // System.out.println("1. segs:" + delta);

 }

 public void removeTask(Task task) {

  remove(task);
  listLength--;
  layout.layoutContainer(this);

 }

 public int getListLength() {
  return listLength;
 }

}
