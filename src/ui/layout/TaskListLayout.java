package ui.layout;

import java.awt.Container;
import java.util.LinkedList;

import list.TaskComparator;

import task.Task;

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

 public void sort(Container c) {

  LinkedList<Task> tasks = new LinkedList<Task>();

  for (int i = 1; i < c.getComponentCount(); i++) {

   Task task = (Task) c.getComponent(i);
   tasks.add(task);

  }

  for (int i=0; i<c.getComponentCount(); i++) {

   c.remove(c.getComponent(1));

  }

  tasks.sort(new TaskComparator<Task>());

  while (!tasks.isEmpty()) {

   c.add(tasks.poll());

  }

 }

}
