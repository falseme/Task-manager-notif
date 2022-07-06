package task;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;

import event.MovableComponentListener;
import gui.Assets;
import lang.Dictionary;
import main.App;
import ui.Button;
import ui.UIConfig;

/**
 * TaskCreator
 */
public class TaskCreator extends JDialog {
 private static final long serialVersionUID = 42l;

 private JPanel panel;

 private JLabel windowTitle, dayLabel, titleLabel, timeLabel, timeLabel2, notifLabel;
 private JComboBox<String> days;
 private JComboBox<Integer> hours, minutes;
 private JTextField title;
 private JCheckBox wspCheck, mailCheck, repeatCheck;

 private Task modify = null;

 public TaskCreator(String day, int order) {

  setSize(300, 420);
  setLocationRelativeTo(null);
  setAlwaysOnTop(true);
  setResizable(false);
  setUndecorated(true);

  panel = new JPanel(null);
  add(panel);
  panel.setBackground(UIConfig.getThemeColor("table-bg"));
  panel.setBorder(BorderFactory.createLineBorder(UIConfig.getThemeColor("week-title"), 2, true));

  MovableComponentListener moveListener = new MovableComponentListener(this);
  addMouseListener(moveListener);
  addMouseMotionListener(moveListener);
  
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

 @Override
 public void setVisible(boolean b) {
	 super.setVisible(b);
	 title.grabFocus();
 }
 
 private void addComponents(String day, int order) {

  int W = getWidth();
  int w = (int) (W * 0.8);
  int x = (W-w)/2;
  // int h = getHeight();

  windowTitle = new JLabel("New Task", JLabel.CENTER);
  windowTitle.setBounds(x, 0, w, 50);
  panel.add(windowTitle);
  windowTitle.setFont(Assets.oswaldFont);
  windowTitle.setForeground(UIConfig.getThemeColor("week-title"));

  // day selection

  dayLabel = new JLabel("Day select");
  dayLabel.setBounds(x, 60, w, 20);
  panel.add(dayLabel);
  dayLabel.setFont(Assets.notoFont_Task);
  dayLabel.setForeground(UIConfig.getThemeColor("week-title"));

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
  panel.add(days);
  days.setFont(Assets.notoFont);
  days.setForeground(UIConfig.getThemeColor("week-title"));
  days.setBackground(UIConfig.getThemeColor("comp-bg-in"));

  // title selection

  titleLabel = new JLabel("Title:");
  titleLabel.setBounds(x, 110, w, 20);
  panel.add(titleLabel);
  titleLabel.setFont(Assets.notoFont_Task);
  titleLabel.setForeground(UIConfig.getThemeColor("week-title"));

  title = new JTextField();
  title.setBounds(x, 130, w, 30);
  title.setBorder(null);
  panel.add(title);
  title.setFont(Assets.notoFont);
  title.setForeground(UIConfig.getThemeColor("week-title"));
  title.setBackground(UIConfig.getThemeColor("comp.bg-in"));
  
  JSeparator separator = new JSeparator();
  separator.setBounds(x, 160, w, 2);
  separator.setBackground(UIConfig.getThemeColor("week-title"));
  separator.setForeground(UIConfig.getThemeColor("week-title"));
  panel.add(separator);

  // time selection

  timeLabel = new JLabel("Time:");
  timeLabel.setBounds(x, 170, w, 20);
  panel.add(timeLabel);
  timeLabel.setFont(Assets.notoFont_Task);
  timeLabel.setForeground(UIConfig.getThemeColor("week-title"));

  // hours
  hours = new JComboBox<Integer>();
  for (int i = 0; i <= 23; i++) {
   hours.addItem(i);
  }
  hours.setBounds(x, 190, w / 3, 25);
  panel.add(hours);
  hours.setFont(Assets.notoFont);
  hours.setForeground(UIConfig.getThemeColor("week-title"));
  hours.setBackground(UIConfig.getThemeColor("comp.bg-in"));

  // mid-label
  timeLabel2 = new JLabel(" : ", JLabel.CENTER);
  timeLabel2.setBounds(x + w / 3, 190, w / 3, 25);
  panel.add(timeLabel2);
  timeLabel2.setFont(Assets.notoFont_Task);
  timeLabel2.setForeground(UIConfig.getThemeColor("week-title"));

  // minutes
  minutes = new JComboBox<Integer>();
  for (int i = 0; i <= 59; i++) {
   minutes.addItem(i);
  }
  minutes.setBounds(x + w / 3 * 2, 190, w / 3, 25);
  panel.add(minutes);
  minutes.setFont(Assets.notoFont);
  minutes.setForeground(UIConfig.getThemeColor("week-title"));
  minutes.setBackground(UIConfig.getThemeColor("comp.bg-in"));

  // notifications selection

  notifLabel = new JLabel("Notifications");
  notifLabel.setBounds(x, 230, w, 20);
  panel.add(notifLabel);
  notifLabel.setFont(Assets.notoFont_Task);
  notifLabel.setForeground(UIConfig.getThemeColor("week-title"));

  wspCheck = new JCheckBox("Whats App", false);
  wspCheck.setFont(Assets.notoFont);
  wspCheck.setBounds(x, 250, wspCheck.getPreferredSize().width, 20);
  panel.add(wspCheck);
  wspCheck.setEnabled(false);
  wspCheck.setForeground(UIConfig.getThemeColor("week-title"));
  wspCheck.setBackground(UIConfig.getThemeColor("table-bg"));

  mailCheck = new JCheckBox("Mail", false);
  mailCheck.setFont(Assets.notoFont);
  mailCheck.setBounds(x, 270, mailCheck.getPreferredSize().width, 20);
  panel.add(mailCheck);
  if (App.getConfig().getUserMail() == null || App.getConfig().getUserMail().isEmpty())
   mailCheck.setEnabled(false);
  mailCheck.setForeground(UIConfig.getThemeColor("week-title"));
  mailCheck.setBackground(UIConfig.getThemeColor("table-bg"));

  repeatCheck = new JCheckBox("Repeat", true);
  repeatCheck.setFont(Assets.notoFont);
  repeatCheck.setBounds(x, 290, repeatCheck.getPreferredSize().width, 20);
  panel.add(repeatCheck);
  repeatCheck.setForeground(UIConfig.getThemeColor("week-title"));
  repeatCheck.setBackground(UIConfig.getThemeColor("table-bg"));

  // sumbit

//  JButton submit = new JButton("Create");
//  submit.setBounds(x, 320, w, 30);
//  submit.setBackground(UIConfig.getThemeColor("comp-bg"));
//  panel.add(submit);
//  submit.addActionListener(submitListener());
//  submit.setFont(Assets.notoFont);
  
  Button submit = new Button("Create", submitListener());
  submit.setBounds(x, 320, w, 30);
  panel.add(submit);
  
  //cancel
  
  Button cancel = new Button("Cancel", event -> {
	  setVisible(false);
  });
  cancel.setBounds(x, 360, w, 30);
  panel.add(cancel);

 }

 private ActionListener submitListener() {

  ActionListener listener = new ActionListener() {

   public void actionPerformed(ActionEvent e) {

    if (title.getText().trim().isEmpty()) {
     titleLabel.setForeground(UIConfig.getThemeColor("fg-error"));
     title.grabFocus();
     return;
    }

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
