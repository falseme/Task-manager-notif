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

import lang.Dictionary;

import main.App;

/**
 * TaskCreator
 */
public class TaskCreator extends JDialog {
 private static final long serialVersionUID = 42l;

 private JLabel windowTitle, dayLabel, titleLabel, timeLabel, timeLabel2, notifLabel;
 private JComboBox<String> days;
 private JComboBox<Integer> hours, minutes;
 private JTextField title;
 private JCheckBox wspCheck, mailCheck, repeatCheck;

 private Task modify = null;

 public TaskCreator(String day, int order) {

  setSize(300, 400);
  setLocationRelativeTo(null);
  setAlwaysOnTop(true);
  setResizable(false);

  setLayout(null);

  addComponents(day, order);

 }

 public TaskCreator(Task task) {

  // int day = task.getDate().get(Calendar.DAY_OF_WEEK) - 1;
  // this(Dictionary.get(day), day);
  this(Dictionary.get(task.getDate().get(Calendar.DAY_OF_WEEK) - 1), task.getDate().get(Calendar.DAY_OF_WEEK) - 1);

  title.setText(task.getTitle());
  wspCheck.setSelected(task.notifWsp() ? true : false);
  mailCheck.setSelected(task.notifMail() ? true : false);
  repeatCheck.setSelected(task.repeat() ? true : false);

  hours.setSelectedItem(task.getDate().get(Calendar.HOUR_OF_DAY));
  minutes.setSelectedItem(task.getDate().get(Calendar.MINUTE));

  modify = task;

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
  days.addItem(Dictionary.get(Dictionary.day1));
  days.addItem(Dictionary.get(Dictionary.day2));
  days.addItem(Dictionary.get(Dictionary.day3));
  days.addItem(Dictionary.get(Dictionary.day4));
  days.addItem(Dictionary.get(Dictionary.day5));
  days.addItem(Dictionary.get(Dictionary.day6));
  days.addItem(Dictionary.get(Dictionary.day7));
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
  wspCheck.setEnabled(false);

  mailCheck = new JCheckBox("Mail", false);
  mailCheck.setBounds(x, 270, w, 20);
  add(mailCheck);
  if (App.getConfig().getUserMail() == null || App.getConfig().getUserMail().isEmpty())
   mailCheck.setEnabled(false);

  repeatCheck = new JCheckBox("Repeat", true);
  repeatCheck.setBounds(x, 290, w, 20);
  add(repeatCheck);

  // sumbit

  JButton submit = new JButton("Create");
  submit.setBounds(x, 320, w, 30);
  add(submit);
  submit.addActionListener(submitListener());

 }

 private ActionListener submitListener() {

  ActionListener listener = new ActionListener() {

   public void actionPerformed(ActionEvent e) {

    Calendar calendar = Calendar.getInstance();

    int dayOrder = days.getSelectedIndex() + 1;
    int dayOfWeekOrder = calendar.get(Calendar.DAY_OF_WEEK);

    while (dayOfWeekOrder != dayOrder) {

     calendar.add(Calendar.DAY_OF_YEAR, 1);
     dayOfWeekOrder = calendar.get(Calendar.DAY_OF_WEEK);

    }

    calendar.set(Calendar.HOUR_OF_DAY, (int) hours.getSelectedItem());
    calendar.set(Calendar.MINUTE, (int) minutes.getSelectedItem());
    calendar.set(Calendar.SECOND, 0);

    Task task = new Task(title.getText(), calendar, wspCheck.isSelected(), mailCheck.isSelected(),
      repeatCheck.isSelected());
    TaskManager.addTask(task, (String) days.getSelectedItem());

    if (modify != null)
     TaskManager.removeTask(modify, modify.getDate().get(Calendar.DAY_OF_WEEK) - 1);

    // System.out.println(days.getSelectedItem());
    // System.out.println(title.getText());
    // System.out.println(hours.getSelectedItem());
    // System.out.println(minutes.getSelectedItem());

    dispose();

   }

  };

  return listener;

 }

}
