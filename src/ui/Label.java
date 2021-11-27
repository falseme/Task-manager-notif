package ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;

import task.TaskCreator;

public class Label extends JLabel {
 private static final long serialVersionUID = 42l;

 public Label(String text, int align, int order) {

  super(text, align);

  addMouseListener(new MouseAdapter() {

   public void mouseClicked(MouseEvent e) {

    new TaskCreator(text, order);

   }

  });

 }

 public void paintComponent(Graphics g) {
  super.paintComponent(g);

  g.setColor(Color.GRAY);
  g.drawLine(5, getHeight() - 1, getWidth() - 5, getHeight() - 1);

 }

}
