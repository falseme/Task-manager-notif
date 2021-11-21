package task;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 * TaskCreator
 */
public class TaskCreator extends JDialog {
 private static final long serialVersionUID = 42l;

 private JLabel windowTitle, dayLabel, titleLabel, timeLabel, timeLabel2, notifLabel;
 private JComboBox<String> days;
 private JComboBox<Integer> hours, minutes;
 private JTextField title;
 private JCheckBox wspCheck, mailCheck;

 public TaskCreator(String day, int order) {

  setSize(300, 400);
  setLocationRelativeTo(null);
  setAlwaysOnTop(true);

  setLayout(null);

  addComponents(day, order);

  setVisible(true);

 }

 private void addComponents(String day, int order) {

  int w = (int) (getWidth() * 0.8);
  int x = getWidth() / 2 - w / 2;
  // int h = getHeight();

  windowTitle = new JLabel("New Task", JLabel.CENTER);
  windowTitle.setBounds(0, 0, w, 50);
  add(windowTitle);

  // day selection

  dayLabel = new JLabel("Day select");
  dayLabel.setBounds(x, 60, w, 20);
  add(dayLabel);

  days = new JComboBox<String>();
  days.addItem("-- Select a day --");
  days.addItem("Sunday");
  days.addItem("Monday");
  days.addItem("Tuesday");
  days.addItem("Wednesday");
  days.addItem("Thursday");
  days.addItem("Friday");
  days.addItem("Saturday");
  days.setBounds(x, 80, w, 25);
  if (day != null && !day.isEmpty())
   days.setSelectedItem(day);
  add(days);

  // title selection

  titleLabel = new JLabel("Title:");
  titleLabel.setBounds(x, 110, w, 20);
  add(titleLabel);

  title = new JTextField();
  title.setBounds(x, 130, w, 30);
  add(title);

  // time selection

  timeLabel = new JLabel("Time:");
  timeLabel.setBounds(x, 170, w, 20);
  add(timeLabel);

  // hours
  hours = new JComboBox<Integer>();
  for (int i = 0; i <= 23; i++) {
   hours.addItem(i);
  }
  hours.setBounds(x, 190, w / 3, 25);
  add(hours);

  // mid-label
  timeLabel2 = new JLabel(" : ", JLabel.CENTER);
  timeLabel2.setBounds(x + w / 3, 190, w / 3, 25);
  add(timeLabel2);

  // minutes
  minutes = new JComboBox<Integer>();
  for (int i = 0; i <= 59; i++) {
   minutes.addItem(i);
  }
  minutes.setBounds(x + w / 3 * 2, 190, w / 3, 25);
  add(minutes);

  // notifications selection

  notifLabel = new JLabel("Notifications");
  notifLabel.setBounds(x, 230, w, 20);
  add(notifLabel);

  wspCheck = new JCheckBox("Whats App", false);
  wspCheck.setBounds(x, 250, w, 20);
  add(wspCheck);

  mailCheck = new JCheckBox("Mail", false);
  mailCheck.setBounds(x, 270, w, 20);
  add(mailCheck);

  // sumbit

  JButton submit = new JButton("Create");
  submit.setBounds(x, 310, w, 30);
  add(submit);
  submit.addActionListener(submitListener(order));

 }

 private ActionListener submitListener(int order) {

  ActionListener listener = new ActionListener() {

   public void actionPerformed(ActionEvent e) {

    Calendar calendar = Calendar.getInstance();

    int dayOrder = order + 1;
    int dayOfWeekOrder = calendar.get(Calendar.DAY_OF_WEEK);

    while (dayOfWeekOrder != dayOrder) {

     calendar.add(Calendar.DAY_OF_YEAR, 1);
     dayOfWeekOrder = calendar.get(Calendar.DAY_OF_WEEK);

    }

    calendar.set(Calendar.HOUR_OF_DAY, (int) hours.getSelectedItem());
    calendar.set(Calendar.MINUTE, (int) hours.getSelectedItem());

    Task task = new Task(title.getText(), calendar, wspCheck.isSelected(), mailCheck.isSelected());
    TaskManager.addTask(task, (String) days.getSelectedItem());

    // System.out.println(days.getSelectedItem());
    // System.out.println(title.getText());
    // System.out.println(hours.getSelectedItem());
    // System.out.println(minutes.getSelectedItem());

    setVisible(false);

   }

  };

  return listener;

 }

}
