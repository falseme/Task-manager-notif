package ui.layout;

import java.awt.Container;

public class TaskListLayout extends LayoutAdapter {

 public void layoutContainer(Container c) {

  int size = c.getWidth() * 2 / 5;
  int y = 0;
  int gap = 10;

  for (int i = 0; i < c.getComponentCount(); i++) {

   c.getComponent(i).setBounds(0, y, c.getWidth(), size);
   y += size + gap;

  }

 }

}
