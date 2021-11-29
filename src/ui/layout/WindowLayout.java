package ui.layout;

import java.awt.Container;

/**
 * WindowLayout
 */
public class WindowLayout extends LayoutAdapter {

 public void layoutContainer(Container c) {

  int borderH = 32;

  c.getComponent(0).setBounds(0, 0, c.getWidth(), borderH);
  c.getComponent(1).setBounds(0, borderH, c.getWidth(), c.getHeight() - borderH);

 }

}
