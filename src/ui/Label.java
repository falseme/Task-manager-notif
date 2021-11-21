package ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Calendar;

import javax.swing.JLabel;

import task.*;

public class Label extends JLabel {
 private static final long serialVersionUID = 42l;

 public Label(String text, int align, int order) {

  super(text, align);

  addMouseListener(new MouseAdapter() {

   public void mouseClicked(MouseEvent e) {

    Task task = new Task("Tarea", Calendar.getInstance(), false, false);
    TaskManager.addTask(task, TaskManager.dayList[order]);

   }

  });

 }

 public void paintComponent(Graphics g) {
  super.paintComponent(g);

  g.setColor(Color.GRAY);
  g.drawLine(5, getHeight() - 1, getWidth() - 5, getHeight() - 1);

 }

}
