package task;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedList;

import javax.swing.Timer;

import lang.Dictionary;

import list.TaskComparator;

import load.LoadWindow;
import load.TaskReader;

import notif.Notification;

import ui.Window;

/**
 * TaskManager
 */
public class TaskManager {

 private static HashMap<String, LinkedList<Task>> taskList;
 public static String[] dayList;

 private static HashMap<String, Integer> dayToInt; // used to get the order of the day in the table by the name.

 private static Window window;

 public static void init(Window userWindow) {

  window = userWindow;

  dayList = new String[] { Dictionary.get(Dictionary.day1), Dictionary.get(Dictionary.day2),
    Dictionary.get(Dictionary.day3), Dictionary.get(Dictionary.day4), Dictionary.get(Dictionary.day5),
    Dictionary.get(Dictionary.day6), Dictionary.get(Dictionary.day7) };

  dayToInt = new HashMap<String, Integer>();
  dayToInt.put(dayList[0], 0);
  dayToInt.put(dayList[1], 1);
  dayToInt.put(dayList[2], 2);
  dayToInt.put(dayList[3], 3);
  dayToInt.put(dayList[4], 4);
  dayToInt.put(dayList[5], 5);
  dayToInt.put(dayList[6], 6);

  taskList = new HashMap<String, LinkedList<Task>>();
  taskList.put(dayList[0], new LinkedList<Task>());
  taskList.put(dayList[1], new LinkedList<Task>());
  taskList.put(dayList[2], new LinkedList<Task>());
  taskList.put(dayList[3], new LinkedList<Task>());
  taskList.put(dayList[4], new LinkedList<Task>());
  taskList.put(dayList[5], new LinkedList<Task>());
  taskList.put(dayList[6], new LinkedList<Task>());

  // Loading tasks files
  LoadWindow loadWindow = new LoadWindow("LOADING...");
  for (int i = 0; i <= 6; i++) {
   addTasks(TaskReader.read(i), dayList[i]);
  }
  loadWindow.dispose();
  window.repaint();

  Thread thread = new Thread(new Runnable() {

   public void run() {

    Timer timer = new Timer(5000, new ActionListener() {

     public void actionPerformed(ActionEvent e) {

      Calendar calendar = Calendar.getInstance();
      String today = dayList[calendar.get(Calendar.DAY_OF_WEEK) - 1];

      if (taskList.get(today).isEmpty())
       return;

      Task task = taskList.get(today).peek();
      if (task.getDate().before(calendar)) {

       if (task.repeat()) {

        taskList.get(today).peek().passWeek();
        sort(today);
        userWindow.updateTasks(calendar.get(Calendar.DAY_OF_WEEK) - 1);

       } else {

        taskList.get(today).poll();
        userWindow.updateTask(calendar.get(Calendar.DAY_OF_WEEK) - 1, task);

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

 public static void addTask(Task task, String dayName) {

  // long init = System.nanoTime();

  taskList.get(dayName).add(task);

  window.updateTasks(dayToInt.get(dayName));

  sort(dayName);

  // long end = System.nanoTime();
  // double delta = end - init;
  // delta /= 1000000000;
  // System.out.println("TaskManager.addTask. segs:" + delta);

 }

 public static void addTasks(Task[] list, String dayName) {

  if (list == null)
   return;

  // long init = System.nanoTime();

  for (int i = 0; i < list.length; i++) {
   taskList.get(dayName).add(list[i]);
  }

  sort(dayName);
  window.updateTasks(dayToInt.get(dayName));

  // long end2 = System.nanoTime();
  // double delta = end2 - init;
  // delta /= 1000000000;
  // System.out.println("TaskManager.addTasks. segs:" + delta);

 }

 public static LinkedList<Task> getTaskList(int dayOfWeek) {

  return taskList.get(dayList[dayOfWeek]);

 }

 public static void saveTasks() {

  // long init = System.nanoTime();

  new LoadWindow("SAVING...");

  for (int i = 0; i <= 6; i++)
   TaskReader.save(getTaskList(i), i);

   // long end3 = System.nanoTime();
   // double delta = end3 - init;
   // delta /= 1000000000;
   // System.out.println("TaskManager.saveTasks. segs:" + delta);

  System.exit(0);

 }

 private static void sort(String dayName) {

  if (taskList.get(dayName).isEmpty())
   return;

  taskList.get(dayName).sort(new TaskComparator<Task>());

 }

}
