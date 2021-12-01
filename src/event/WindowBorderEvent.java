package event;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JComponent;
import javax.swing.JPopupMenu;

import ui.Window;

/**
 * PopupMenuEvent
 */
public class WindowBorderEvent extends MouseAdapter {

 private Window window;

 private boolean leftClick = false;

 private int X, Y; // window position
 private int x, y; // mouse position

 public WindowBorderEvent(Window parent) {
  window = parent;
 }

 public void mousePressed(MouseEvent e) {

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

  int x2 = e.getX();
  int y2 = e.getY();

  X += x2 - x;
  Y += y2 - y;

  window.setLocation(X, Y);

 }

}
