package ui.layout;

import java.awt.Container;
import java.io.Serializable;

/**
 * TaskLayout
 */
public class TaskLayout extends LayoutAdapter implements Serializable {
 private static final long serialVersionUID = 42l;

 public void layoutContainer(Container c) {

  int h = c.getHeight() / 2;

  c.getComponent(0).setBounds(0, 0, c.getWidth(), h); // title
  c.getComponent(1).setBounds(0, h, c.getWidth(), h); // hour

 }

}
