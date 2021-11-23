package ui;

import java.util.List;

import javax.swing.JPanel;

import lang.Dictionary;

import task.*;

import ui.layout.WeekListLayout;

public class WeekPanel extends JPanel {
 private static final long serialVersionUID = 42l;

 private TaskList[] weekList;
 private String[] days;

 public WeekPanel() {

  days = new String[] { Dictionary.get(Dictionary.day1), Dictionary.get(Dictionary.day2),
    Dictionary.get(Dictionary.day3), Dictionary.get(Dictionary.day4), Dictionary.get(Dictionary.day5),
    Dictionary.get(Dictionary.day6), Dictionary.get(Dictionary.day7) };

  setLayout(new WeekListLayout());

  boolean[] bs = new boolean[] { false, true, true, true, true, true, true };
  weekList = new TaskList[7];
  for (int i = 0; i < weekList.length; i++) {

   weekList[i] = new TaskList(days[i], bs[i], i);
   add(weekList[i]);

  }

 }

 public void updateTasks(int dayOfWeek) {

  List<Task> tasks = TaskManager.getTaskList(dayOfWeek);
  // int n = tasks.size() - weekList[dayOfWeek].getListLength();
  int n = weekList[dayOfWeek].getListLength();

  if (n >= tasks.size()) {

   weekList[dayOfWeek].addTask(null);
   return;

  }

  for (int i = n; i < tasks.size(); i++) {

   weekList[dayOfWeek].addTask(tasks.get(i));

  }

 }

 public void updateTask(int dayOfWeek, Task task) {

  weekList[dayOfWeek].removeTask(task);

 }

}
