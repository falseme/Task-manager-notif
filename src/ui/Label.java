package ui;

import event.ComponentBackgroundMouseEvent;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;

import task.TaskCreator;

public class Label extends JLabel {
 private static final long serialVersionUID = 42l;

 // private static final Color bgcolor = new Color(0, 0, 0, 0);
 // private static final Color inbgcolor = new Color(1f, 1f, 1f, 0.025f);
 // private static final Color clickbgcolor = new Color(1f, 1f, 1f, 0.035f);

 public Label(String text, int align, int order) {
  super(text, align);

  setFont(UIConfig.defaultFont);
  setForeground(UIConfig.getThemeColor("week-title"));

  addMouseListener(new MouseAdapter() {

   public void mouseClicked(MouseEvent e) {

    new TaskCreator(text, order);

   }

  });

  addMouseListener(new ComponentBackgroundMouseEvent(this));

 }

 public void paintComponent(Graphics g) {

  g.setColor(getBackground());
  g.fillRect(0, 0, getWidth(), getHeight());

  super.paintComponent(g);

 }

}
