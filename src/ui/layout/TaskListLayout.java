package ui.layout;

import java.awt.Container;

public class TaskListLayout extends LayoutAdapter {

 public void layoutContainer(Container c) {

  int size = c.getWidth() * 2 / 5;
  int y = 0;
  int gap = 5;

  c.getComponent(0).setBounds(0, y, c.getWidth(), size);
  y += size + gap;

  int w = (int) (c.getWidth() * 0.9);
  int x = c.getWidth() / 2 - w / 2;

  for (int i = 1; i < c.getComponentCount(); i++) {

   c.getComponent(i).setBounds(x, y, w, size);
   y += size + gap;

  }

 }

}
