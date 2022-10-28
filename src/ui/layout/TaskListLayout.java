package ui.layout;

import java.awt.Component;
import java.awt.Container;
import java.util.Calendar;
import java.util.LinkedList;

import list.TaskComparator;
import task.Task;
import ui.util.TaskSeparator;

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

   if(c.getComponent(i).getClass().getName().equals("ui.util.TaskSeparator")) {
	 c.getComponent(i).setBounds(x, y, w, 1);
	 y += 1+gap;
	 continue;
   }

   c.getComponent(i).setBounds(x, y, w, size);
   y += size + gap;

  }

 }

 protected void layoutUpsideDown(Container c) {

  int y = c.getHeight();
  int w = (int) (c.getWidth() * 0.9);
  int x = c.getWidth() / 2 - w / 2;

  for (int i = c.getComponentCount() - 1; i > 0; i--) {

   if(c.getComponent(i).getClass().getName().equals("ui.util.TaskSeparator")) {
	 y -= (1+gap);
	 c.getComponent(i).setBounds(x, y, w, 1);
	 continue;
   }

   y -= (size + gap);
   c.getComponent(i).setBounds(x, y, w, size);

  }

 }

 public void sort(Container c) {

  // true if a separator between today tasks and done tasks was added
  boolean separated = false;
	 
  LinkedList<Task> tasks = new LinkedList<Task>();

  for (int i = 1; i < c.getComponentCount(); i++) {
   if(!c.getComponent(i).getClass().getName().equals("task.Task"))
	   continue;

   Task task = (Task) c.getComponent(i);
   tasks.add(task);

  }

  int count = c.getComponentCount()-1;
  for (int i = 0; i < count; i++)
   c.remove(c.getComponent(1));

  tasks.sort(new TaskComparator<Task>());

  Calendar instance = null;
  if(tasks.peek() != null) {
	instance = (Calendar) tasks.peek().getDate().clone();
	instance.add(Calendar.DAY_OF_YEAR, 1);
  }

  while (!tasks.isEmpty()) {

   c.add(tasks.poll());

   if(!separated && tasks.peek() != null && tasks.peek().getDate().after(instance)) {
	c.add(new TaskSeparator());
    separated = true;
   }

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
  else if (c.getComponent(c.getComponentCount() - 1).getY() < endY) {
   if ((c.getComponentCount() - 1) * (size + gap) >= c.getHeight())
    layoutUpsideDown(c);
   else
    layoutContainer(c);
  }

 }

}
