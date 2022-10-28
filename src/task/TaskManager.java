package task;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedList;

import javax.swing.Timer;

import list.TaskComparator;
import load.TaskReader;
import notif.Notification;
import ui.Window;

/**
 * TaskManager
 */
public class TaskManager {

 private static HashMap<Integer, LinkedList<Task>> taskList;

 private static Window window;

 public static void init(Window userWindow) {

  window = userWindow;

  taskList = new HashMap<Integer, LinkedList<Task>>();
  taskList.put(0, new LinkedList<Task>());
  taskList.put(1, new LinkedList<Task>());
  taskList.put(2, new LinkedList<Task>());
  taskList.put(3, new LinkedList<Task>());
  taskList.put(4, new LinkedList<Task>());
  taskList.put(5, new LinkedList<Task>());
  taskList.put(6, new LinkedList<Task>());

  // Loading tasks files
  for (int i = 0; i <= 6; i++) {
   addTasks(TaskReader.read(i), i);
   // day indexes go from 0 to 6 in the Dictionary Class, so its faster to simply use 'i'
  }
  window.repaint();

  Thread thread = new Thread(new Runnable() {

   public void run() {

    Timer timer = new Timer(5000, new ActionListener() {

     public void actionPerformed(ActionEvent e) {

      Calendar calendar = Calendar.getInstance();
      int today = calendar.get(Calendar.DAY_OF_WEEK) - 1;

      if (taskList.get(today).isEmpty())
       return;

      Task task = taskList.get(today).peek();
      if (task.getDate().before(calendar)) {

       if (task.repeat()) {

//        taskList.get(today).peek().passTime
        task.passTime();
        if(task.getAmount() > 0 && task.getAmount() < 7) {

        	int moveDay = task.getDate().get(Calendar.DAY_OF_WEEK) - 1;
        	taskList.get(moveDay).add(task);

        	sort(moveDay);
        	userWindow.updateTasks(moveDay);

        	userWindow.updateTasks(today, taskList.get(today).poll());

        }
        sort(today);
        userWindow.updateTasks(today);

       } else {

        userWindow.updateTasks(today, taskList.get(today).poll());

       }

       new Notification(task);

      }

     }

    });

    timer.start();

   }

  }, "Task Date Management");

  thread.start();

 }

 public static void addTask(Task task, int dayIndex) {

  // long init = System.nanoTime();

  taskList.get(dayIndex).add(task);

  window.updateTasks(dayIndex);

  sort(dayIndex);

  // long end = System.nanoTime();
  // double delta = end - init;
  // delta /= 1000000000;
  // System.out.println("TaskManager.addTask. segs:" + delta);

 }

 public static void addTasks(Task[] list, int dayIndex) {

  if (list == null)
   return;

  // long init = System.nanoTime();

  for (int i = 0; i < list.length; i++) {
   Task t = new Task(list[i]);
   taskList.get(dayIndex).add(t);
  }

  sort(dayIndex);
  window.updateTasks(dayIndex);

  // long end2 = System.nanoTime();
  // double delta = end2 - init;
  // delta /= 1000000000;
  // System.out.println("TaskManager.addTasks. segs:" + delta);

 }

 public static void removeTask(Task task, int dayOfWeek) {

  getTaskList(dayOfWeek).remove(task);
  window.updateTasks(dayOfWeek, task);

 }

 public static LinkedList<Task> getTaskList(int dayOfWeek) {

  return taskList.get(dayOfWeek);

 }

 public static void saveTasks() {

  // long init = System.nanoTime();

  for (int i = 0; i <= 6; i++)
   TaskReader.save(getTaskList(i), i);

  // long end3 = System.nanoTime();
  // double delta = end3 - init;
  // delta /= 1000000000;
  // System.out.println("TaskManager.saveTasks. segs:" + delta);

  System.exit(0);

 }

 private static void sort(int dayIndex) {

  if (taskList.get(dayIndex).isEmpty())
   return;

  taskList.get(dayIndex).sort(new TaskComparator<Task>());

 }

}
