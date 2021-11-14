package ui.layout;

import java.awt.Container;

public class TaskListLayout extends LayoutAdapter {

 public void layoutContainer(Container c) {

  int size = c.getWidth() * 2 / 5;

  c.getComponent(0).setBounds(0, 0, c.getWidth(), size);

 }

}
