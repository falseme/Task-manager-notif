package task;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedList;

import javax.swing.Timer;

import list.TaskComparator;

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

  dayList = new String[] { "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday" };

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

  Thread thread = new Thread(new Runnable() {

   public void run() {

    Timer timer = new Timer(10000, new ActionListener() {

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

      }

     }

    });

    timer.start();

   }

  }, "Task Management");

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
  // System.out.println("2. segs:" + delta);

 }

 public static LinkedList<Task> getTaskList(int dayOfWeek) {

  return taskList.get(dayList[dayOfWeek]);

 }

 private static void sort(String dayName) {

  taskList.get(dayName).sort(new TaskComparator<Task>());

 }

}
