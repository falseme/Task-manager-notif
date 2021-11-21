package ui.layout;

import java.awt.Container;

/**
 * TaskLayout
 */
public class TaskLayout extends LayoutAdapter {

 public void layoutContainer(Container c) {

  int h = c.getHeight() / 2;

  c.getComponent(0).setBounds(0, 0, c.getWidth(), h); // title
  c.getComponent(1).setBounds(0, h, c.getWidth(), h); // hour

 }

}
