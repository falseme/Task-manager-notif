package ui;

import java.util.List;

import javax.swing.JPanel;

import task.*;

import ui.layout.WeekListLayout;

public class WeekPanel extends JPanel {
 private static final long serialVersionUID = 42l;

 private TaskList[] weekList;
 private String[] days;

 public WeekPanel() {

  days = new String[] { "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday" };

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

  for(int i=n; i<tasks.size(); i++){

   weekList[dayOfWeek].addTask(tasks.get(i));

  }

 }

}
