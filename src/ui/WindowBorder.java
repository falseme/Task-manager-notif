package ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JComponent;

/**
 * WindowBorder
 */
public class WindowBorder extends JComponent {
 private static final long serialVersionUID = 42l;

 public final Color darkThemeColor = new Color(42, 42, 42);

 private static final Color exitColor = Color.RED;
 private static final Color minColor = Color.BLUE;

 private Button exitBtn;
 private Button minBtn;

 private Window parent;

 public WindowBorder(Window window) {

  parent = window;

  exitBtn = new Button(Button.EXIT);
  minBtn = new Button(Button.MIN);

  add(exitBtn);
  add(minBtn);

 }

 public void setBounds(int x, int y, int w, int h) {
  super.setBounds(x, y, w, h);

  exitBtn.setBounds(w - h, 0, h, h);
  minBtn.setBounds(w - h * 2, 0, h, h);

 }

 public void paintComponent(Graphics g) {

  g.setColor(darkThemeColor);
  g.fillRect(0, 0, getWidth(), getHeight());

 }

 private class Button extends JComponent {
  private static final long serialVersionUID = 42l;

  public static final int EXIT = 0;
  public static final int MIN = 1;

  private int type;

  public Button(int type) {

   this.type = type;

   addMouseListener(new MouseListener() {

    public void mouseClicked(MouseEvent e) {

     if (type == EXIT) {
      parent.dispose();
     } else {
      parent.setState(0);
     }

    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void mousePressed(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
    }

   });

  }

  protected void paintComponent(Graphics g) {

   g.setColor(type == EXIT ? exitColor : minColor);
   g.fillRect(0, 0, getWidth(), getHeight());

  }

 }

}
