package ui;

import java.awt.Cursor;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.JComponent;

import task.Task;
import ui.layout.TaskListLayout;

public class TaskList extends JComponent implements MouseWheelListener {
 private static final long serialVersionUID = 42l;

 private Label dayLabel;

 private int listLength = 0;

 private TaskListLayout layout;

 public TaskList(String dayName, int order) {

  layout = new TaskListLayout();
  setLayout(layout);

  dayLabel = new Label(dayName, Label.CENTER, order);
  dayLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
  add(dayLabel);

  addMouseWheelListener(this);

 }

 // public void paintComponent(Graphics g) {
 //
 // if (!drawBorder)
 // return;
 // if (drawBorder)
 // return;
 //
 // int gap = 10;
 //
 // g.setColor(Color.GRAY);
 // g.drawLine(0, gap, 0, getHeight() - gap);
 //
 // }

 public void addTask(Task task) {

  // long init = System.nanoTime();

  if (task != null) {
   add(task);
   listLength++;
  }

  layout.sort(this);
  layout.layoutContainer(this);

  if (task != null)
   task.getLayout().layoutContainer(task);
  
  repaint();

  // long end = System.nanoTime();
  // double delta = end - init;
  // delta /= 1000000000;
  // System.out.println("1. segs:" + delta);

 }

 public void removeTask(Task task) {

  remove(task);
  listLength--;
  layout.layoutContainer(this);
  repaint();

 }

 public void resetLang(String text) {

  dayLabel.setText(text);

 }

 public void repaintTheme() {

  for (int i = 0; i < getComponentCount(); i++) {

   getComponent(i).setBackground(UIConfig.getThemeColor("comp-bg"));
   getComponent(i).repaint();

  }

 }

 public int getListLength() {
  return listLength;
 }

 public void mouseWheelMoved(MouseWheelEvent e) {

  layout.scrollTasks(this, e.getUnitsToScroll() * -3);

 }

}
