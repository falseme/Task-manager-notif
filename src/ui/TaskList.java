package ui;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JComponent;

import ui.layout.TaskListLayout;

public class TaskList extends JComponent {
 private static final long serialVersionUID = 42l;

 private boolean drawBorder;

 private Label dayLabel;

 public TaskList(String dayName, boolean drawBorder) {

  setLayout(new TaskListLayout());

  dayLabel = new Label(dayName, Label.CENTER);
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

}
