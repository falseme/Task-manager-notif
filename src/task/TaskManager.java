package task;

import java.util.HashMap;
import java.util.LinkedList;

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

 }

 public static void addTask(Task task, String dayName) {

  // long init = System.nanoTime();

  taskList.get(dayName).add(task);

  window.updateTasks(dayToInt.get(dayName));

  taskList.get(dayName).sort(new TaskComparator<Task>());

  // long end = System.nanoTime();
  // double delta = end - init;
  // delta /= 1000000000;
  // System.out.println("2. segs:" + delta);

 }

 public static LinkedList<Task> getTaskList(int dayOfWeek) {

  return taskList.get(dayList[dayOfWeek]);

 }

}
