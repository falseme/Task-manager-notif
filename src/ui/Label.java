package ui;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JLabel;

public class Label extends JLabel {
 private static final long serialVersionUID = 42l;

 public Label(String text, int align) {

  super(text, align);

 }

 public void paintComponent(Graphics g) {
  super.paintComponent(g);

  g.setColor(Color.GRAY);
  g.drawLine(5, getHeight() - 1, getWidth() - 5, getHeight() - 1);

 }

}
