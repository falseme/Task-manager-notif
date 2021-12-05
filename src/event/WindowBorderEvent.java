package event;

import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import ui.Window;

/**
 * PopupMenuEvent
 */
public class WindowBorderEvent extends MouseAdapter {

 private Window window;
 private boolean staticPos; // true when the window is on the left, right or extended on top

 private boolean leftClick = false;

 private int X, Y; // window position
 private int x, y; // mouse position

 private Point pressedPoint; // coords in window screen when click pressed

 public WindowBorderEvent(Window parent) {
  window = parent;
 }

 public void mousePressed(MouseEvent e) {

  pressedPoint = new Point(e.getXOnScreen(), e.getYOnScreen());

  if (e.getButton() != MouseEvent.BUTTON1) {
   leftClick = false;
   return;
  } else {
   leftClick = true;
  }

  X = window.getX();
  Y = window.getY();

  x = e.getX();
  y = e.getY();

 }

 public void mouseDragged(MouseEvent e) {

  if (!leftClick)
   return;

  if (staticPos) {

   double W = window.getWidth() - window.getWindowBorder().getHeight() * 2;
   double percentWidth = e.getX() / W; // *100
   window.setSize(window.getPreferredSize());
   W = window.getWidth() - window.getWindowBorder().getHeight() * 2;
   int wp = (int) (W * percentWidth); // /100

   X = e.getXOnScreen() - wp;
   x = wp;
   window.setLocation(X, Y);

   staticPos = false;
   return;

  }

  int x2 = e.getX();
  int y2 = e.getY();

  X += x2 - x;
  Y += y2 - y;

  window.setLocation(X, Y);

 }

 public void mouseReleased(MouseEvent e) {

  Point p = new Point(e.getXOnScreen(), e.getYOnScreen());

  if (p.getX() == pressedPoint.getX() && p.getY() == pressedPoint.getY())
   return;

  Toolkit toolkit = Toolkit.getDefaultToolkit();
  int w = toolkit.getScreenSize().width;
  int h = toolkit.getScreenSize().height;

  if (p.getX() <= 0) { // left

   window.setBounds(0, 0, w / 2, h);
   staticPos = true;

  } else if (p.getX() >= w - 1) { // right

   window.setBounds(w / 2, 0, w / 2, h);
   staticPos = true;

  } else if (p.getY() <= 0) { // up

   window.setBounds(0, 0, w, h);
   staticPos = true;

  }

 }

}
