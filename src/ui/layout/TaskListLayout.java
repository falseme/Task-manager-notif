package ui.layout;

import java.awt.Component;
import java.awt.Container;
import java.util.LinkedList;

import list.TaskComparator;

import task.Task;

public class TaskListLayout extends LayoutAdapter {

 int size = 50;
 int gap = 5;

 public void layoutContainer(Container c) {

  int y = 0;

  c.getComponent(0).setBounds(0, y, c.getWidth(), size);
  y += size + gap;

  int w = (int) (c.getWidth() * 0.9);
  int x = c.getWidth() / 2 - w / 2;

  for (int i = 1; i < c.getComponentCount(); i++) {

   c.getComponent(i).setBounds(x, y, w, size);
   y += size + gap;

  }

 }

 protected void layoutUpsideDown(Container c) {

  int y = c.getHeight() - gap - size;
  int w = (int) (c.getWidth() * 0.9);
  int x = c.getWidth() / 2 - w / 2;

  for (int i = c.getComponentCount() - 1; i > 0; i--) {

   c.getComponent(i).setBounds(x, y, w, size);
   y -= (size + gap);

  }

 }

 public void sort(Container c) {

  LinkedList<Task> tasks = new LinkedList<Task>();

  for (int i = 1; i < c.getComponentCount(); i++) {

   Task task = (Task) c.getComponent(i);
   tasks.add(task);

  }

  for (int i = 0; i < c.getComponentCount(); i++) {

   c.remove(c.getComponent(1));

  }

  tasks.sort(new TaskComparator<Task>());

  while (!tasks.isEmpty()) {

   c.add(tasks.poll());

  }

 }

 public void scrollTasks(Container c, int amount) {

  if (c.getComponentCount() <= 1)
   return;

  int endY = c.getHeight() - gap - size;

  for (int i = 1; i < c.getComponentCount(); i++) {
   Component com = c.getComponent(i);
   com.setLocation(com.getX(), com.getY() + amount);
  }

  if (c.getComponent(1).getY() > size)
   layoutContainer(c);
  else if (c.getComponent(c.getComponentCount() - 1).getY() < endY)
   layoutUpsideDown(c);

 }

}
