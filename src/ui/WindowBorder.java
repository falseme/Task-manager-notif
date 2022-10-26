package ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JComponent;
import javax.swing.JLabel;

import event.WindowBorderEvent;
import gui.Assets;

/**
 * WindowBorder
 */
public class WindowBorder extends JComponent {
 private static final long serialVersionUID = 42l;

 private static final Color exitColor = new Color(1f, 0f, 0f, 0.5f);
 private static final Color minColor = new Color(0f, 0.5f, 1f, 0.5f);

 private JLabel title;

 private Button exitBtn;
 private Button minBtn;

 private Window parent;

 public WindowBorder(Window window) {

  parent = window;

  title = new JLabel(window.getTitle(), JLabel.LEFT);
  title.setFont(Assets.oswaldFont);

  exitBtn = new Button(Button.EXIT);
  minBtn = new Button(Button.MIN);

  add(title);
  add(exitBtn);
  add(minBtn);

  WindowBorderEvent listener = new WindowBorderEvent(window);
  addMouseListener(listener);
  addMouseMotionListener(listener);

  repaintTheme();

 }

 public void setBounds(int x, int y, int w, int h) {
  super.setBounds(x, y, w, h);

  title.setLocation(10, 0);
  title.setSize(title.getPreferredSize().width, h);
  exitBtn.setBounds(w - h, 0, h, h);
  minBtn.setBounds(w - h * 2, 0, h, h);

 }

 public void paintComponent(Graphics g) {

  g.setColor(UIConfig.getThemeColor("window-border"));
  g.fillRect(0, 0, getWidth(), getHeight());

 }

 public void resetLang() {

  title.setText(parent.getTitle());

 }

 public void repaintTheme() {

  title.setForeground(UIConfig.getThemeColor("week-title"));

  repaint();

 }

 private class Button extends JComponent {
  private static final long serialVersionUID = 42l;

  public static final int EXIT = 0;
  public static final int MIN = 1;

  private int type;

  public Button(int type) {

   this.type = type;

   setBackground(new Color(0, 0, 0, 0));

   addMouseListener(new MouseListener() {

    public void mouseClicked(MouseEvent e) {

     if (type == EXIT) {
      parent.dispose();
     } else {
      parent.setState(1);
     }

    }

    public void mouseEntered(MouseEvent e) {

     if (type == EXIT) {
      setBackground(exitColor);
     } else {
      setBackground(minColor);
     }

     repaint();

    }

    public void mouseExited(MouseEvent e) {

     setBackground(new Color(0, 0, 0, 0));
     repaint();

    }

    public void mousePressed(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
    }

   });

  }

  protected void paintComponent(Graphics g) {

   g.setColor(getBackground());
   g.fillRect(0, 0, getWidth(), getHeight());

   if (type == EXIT)
    g.drawImage(Assets.ui_close, 0, 0, getWidth(), getHeight(), null);
   else
    g.drawImage(Assets.ui_min, 0, 0, getWidth(), getHeight(), null);

  }

 }

}
