package ui;

import javax.swing.JPanel;

import ui.layout.WeekListLayout;

public class WeekPanel extends JPanel {
 private static final long serialVersionUID = 42l;

 private TaskList[] weekList;
 private String[] days;

 public WeekPanel() {

  days = new String[] { "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday" };

  setLayout(new WeekListLayout());

  boolean[] bs = new boolean[] { false, true, true, true, true, true, true };
  weekList = new TaskList[7];
  for (int i = 0; i < weekList.length; i++) {

   weekList[i] = new TaskList(days[i], bs[i]);
   add(weekList[i]);

  }

 }

}
