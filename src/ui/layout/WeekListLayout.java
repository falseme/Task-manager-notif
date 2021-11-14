package ui.layout;

import java.awt.Container;

public class WeekListLayout extends LayoutAdapter {

 public void layoutContainer(Container c) {

  int W = c.getWidth();
  int w = W / 7;
  int H = c.getHeight();
  int x = 0;
  int y = 0;

  int n = c.getComponentCount();
  for (int i = 0; i < n; i++) {

   c.getComponent(i).setBounds(x, y, w, H);
   x += w;

  }

 }

}
