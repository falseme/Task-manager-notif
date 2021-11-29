package event;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JComponent;

import ui.UIConfig;

/**
 * TaskMouseEvent
 */
public class ComponentBackgroundMouseEvent implements MouseListener {

 private JComponent owner;

 private boolean in = false;

 public ComponentBackgroundMouseEvent(JComponent owner) {

  this.owner = owner;

  owner.setBackground(UIConfig.getThemeColor("comp-bg"));

 }

 public void mouseClicked(MouseEvent e) {
 }

 public void mouseEntered(MouseEvent e) {

  in = true;
  owner.setBackground(UIConfig.getThemeColor("comp-bg-in"));
  owner.repaint();

 }

 public void mouseExited(MouseEvent e) {

  in = false;
  owner.setBackground(UIConfig.getThemeColor("comp-bg"));
  owner.repaint();

 }

 public void mousePressed(MouseEvent e) {

  owner.setBackground(UIConfig.getThemeColor("comp-bg-click"));
  owner.repaint();

 }

 public void mouseReleased(MouseEvent e) {

  if (in)
   owner.setBackground(UIConfig.getThemeColor("comp-bg-in"));
  else
   owner.setBackground(UIConfig.getThemeColor("comp-bg"));
  owner.repaint();

 }

}
