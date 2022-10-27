package task;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.JTextField;

import api.Keys;
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

 private JLabel windowTitle, dayLabel, titleLabel, timeLabel, timeLabel2, notifLabel, freqLabel;
 private JComboBox<String> days;
 private JComboBox<Integer> hours, minutes, dayAmount;
 private JTextField title;
 private JCheckBox wspCheck, mailCheck;
 private JRadioButton repeatWeek, repeatDays, notRepeat;

 private Task modify = null;
 private boolean fast = false;
 
 public TaskCreator(String day, int order, boolean fast) {

  setSize(300, 500);
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
  
  this.fast = fast;
  addComponents(day, order);

 }

 public TaskCreator(Task task) {

  this(Dictionary.get(task.getDate().get(Calendar.DAY_OF_WEEK) - 1), task.getDate().get(Calendar.DAY_OF_WEEK) - 1, false);

  title.setText(task.getTitle());
  wspCheck.setSelected(task.notifWsp() ? true : false);
  mailCheck.setSelected(task.notifMail() ? true : false);
  repeatWeek.setSelected(task.repeat() ? true : false);

  hours.setSelectedItem(task.getDate().get(Calendar.HOUR_OF_DAY));
  minutes.setSelectedItem(task.getDate().get(Calendar.MINUTE));
  
  if(task.getAmount() == 0)
   notRepeat.setSelected(true);
  else if(task.getAmount() < 7) {
   repeatDays.setSelected(true);
   dayAmount.setSelectedIndex(task.getAmount()-1);
  }else
   repeatWeek.setSelected(true);

  modify = task;

 }
 
 public TaskCreator() {
  
  this(Dictionary.get(Calendar.getInstance().get(Calendar.DAY_OF_WEEK) - 1), Calendar.getInstance().get(Calendar.DAY_OF_WEEK) - 1, true);
  
  setLocationRelativeTo(null);
  setVisible(true);
  
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
  int y = 0;

  // TITLE
  windowTitle = new JLabel(Dictionary.get(Dictionary.create), JLabel.CENTER);
  windowTitle.setBounds(x, y, w, 50);
  y += 50;
  panel.add(windowTitle);
  windowTitle.setFont(Assets.oswaldFont);
  windowTitle.setForeground(UIConfig.getThemeColor("week-title"));

  // DAY SELECTION
  y = componentDaySection(x, y, w, day);

  // TITLE INPUT
  y = componentTaskTitleSection(x, y, w);

  // TIME INPUT
  y = componentTimeSection(x, y, w);

  // NOTIFICATIONS
  y = componentNotificationSection(x, y, w);
  
  // FREQUENCY
  if(!fast)
   y = componentFrequencySection(x, y, w);

  // SUBMIT & CANCEL
  Button submit = new Button(Dictionary.get(Dictionary.create), submitListener());
  submit.setBounds(x, y, w, 30);
  y += 40;
  panel.add(submit);
  
  Button cancel = new Button(Dictionary.get(Dictionary.cancel), event -> setVisible(false));
  cancel.setBounds(x, y, w, 30);
  panel.add(cancel);
  
  setSize(W, y + 45);

 }
 
 private int componentDaySection(int x, int y, int w, String day) {

  dayLabel = new JLabel(Dictionary.get(Dictionary.creator_select));
  dayLabel.setBounds(x+1, y, w, 20);
  y += 22;
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
  days.setBounds(x, y, w, 25);
  y += 30;
  if (day != null && !day.isEmpty())
   days.setSelectedItem(day);
  panel.add(days);
  days.setFont(Assets.notoFont);
  days.setForeground(UIConfig.getThemeColor("week-title"));
  days.setBackground(UIConfig.getThemeColor("comp-bg-in"));
//  if(fast)
//   days.setEnabled(false);

  return y;
  
 }


 private int componentTaskTitleSection(int x, int y, int w) {

  titleLabel = new JLabel(Dictionary.get(Dictionary.creator_title));
  titleLabel.setBounds(x, y, w, 20);
  y += 18;
  panel.add(titleLabel);
  titleLabel.setFont(Assets.notoFont_Task);
  titleLabel.setForeground(UIConfig.getThemeColor("week-title"));

  title = new JTextField();
  title.setBounds(x, y, w, 30);
  y += 30;
  title.setBorder(null);
  panel.add(title);
  title.setFont(Assets.notoFont);
  title.setForeground(UIConfig.getThemeColor("week-title"));
  title.setBackground(UIConfig.getThemeColor("comp.bg-in"));

  JSeparator separator = new JSeparator();
  separator.setBounds(x, y, w, 2);
  y += 12;
  separator.setBackground(UIConfig.getThemeColor("week-title"));
  separator.setForeground(UIConfig.getThemeColor("week-title"));
  panel.add(separator);

  return y;

 }
 
 private int componentTimeSection(int x, int y, int w) {

  timeLabel = new JLabel(Dictionary.get(Dictionary.creator_time));
  timeLabel.setBounds(x+1, y, w, 20);
  y += 22;
  panel.add(timeLabel);
  timeLabel.setFont(Assets.notoFont_Task);
  timeLabel.setForeground(UIConfig.getThemeColor("week-title"));

  // hours
  hours = new JComboBox<Integer>();
  for (int i = 0; i <= 23; i++) {
   hours.addItem(i);
  }
  hours.setBounds(x, y, w / 3, 25);
  panel.add(hours);
  hours.setFont(Assets.notoFont);
  hours.setForeground(UIConfig.getThemeColor("week-title"));
  hours.setBackground(UIConfig.getThemeColor("comp.bg-in"));

  // mid-label
  timeLabel2 = new JLabel(" : ", JLabel.CENTER);
  timeLabel2.setBounds(x + w / 3, y, w / 3, 25);
  panel.add(timeLabel2);
  timeLabel2.setFont(Assets.notoFont_Task);
  timeLabel2.setForeground(UIConfig.getThemeColor("week-title"));

  // minutes
  minutes = new JComboBox<Integer>();
  for (int i = 0; i <= 59; i++) {
   minutes.addItem(i);
  }
  minutes.setBounds(x + w / 3 * 2, y, w / 3, 25);
  y += 30;
  panel.add(minutes);
  minutes.setFont(Assets.notoFont);
  minutes.setForeground(UIConfig.getThemeColor("week-title"));
  minutes.setBackground(UIConfig.getThemeColor("comp.bg-in"));

  return y;
  
 }
 
 private int componentNotificationSection(int x, int y, int w) {

  notifLabel = new JLabel(Dictionary.get(Dictionary.creator_notif));
  notifLabel.setBounds(x+1, y, w, 20);
  y += 22;
  panel.add(notifLabel);
  notifLabel.setFont(Assets.notoFont_Task);
  notifLabel.setForeground(UIConfig.getThemeColor("week-title"));

  wspCheck = new JCheckBox("Whats App", false);
  wspCheck.setFont(Assets.notoFont);
  wspCheck.setBounds(x, y, wspCheck.getPreferredSize().width, 20);
  y += 20;
  panel.add(wspCheck);
  if(App.getConfig().getUserwspnumber() == null || App.getConfig().getUserwspnumber().isEmpty() || !Keys.loaded)
	  wspCheck.setEnabled(false);
  wspCheck.setForeground(UIConfig.getThemeColor("week-title"));
  wspCheck.setBackground(UIConfig.getThemeColor("table-bg"));

  mailCheck = new JCheckBox("Mail", false);
  mailCheck.setFont(Assets.notoFont);
  mailCheck.setBounds(x, y, mailCheck.getPreferredSize().width, 20);
  y += 25;
  panel.add(mailCheck);
  if (App.getConfig().getUserMail() == null || App.getConfig().getUserMail().isEmpty() || !Keys.loaded)
   mailCheck.setEnabled(false);
  mailCheck.setForeground(UIConfig.getThemeColor("week-title"));
  mailCheck.setBackground(UIConfig.getThemeColor("table-bg"));

  return y;
  
 }
 
 private int componentFrequencySection(int x, int y, int w) {

  freqLabel = new JLabel(Dictionary.get(Dictionary.frequency));
  freqLabel.setBounds(x+1, y, w, 20);
  y += 22;
  panel.add(freqLabel);
  freqLabel.setFont(Assets.notoFont_Task);
  freqLabel.setForeground(UIConfig.getThemeColor("week-title"));

  ButtonGroup freqGroup = new ButtonGroup();

  repeatWeek = new JRadioButton(Dictionary.get(Dictionary.repeatWeek), true);
  repeatWeek.setFont(Assets.notoFont);
  repeatWeek.setBounds(x, y, repeatWeek.getPreferredSize().width, 20);
  y += 20;
  panel.add(repeatWeek);
  repeatWeek.setForeground(UIConfig.getThemeColor("week-title"));
  repeatWeek.setBackground(UIConfig.getThemeColor("table-bg"));

  repeatDays = new JRadioButton(Dictionary.get(Dictionary.repeatDays), false);
  repeatDays.setFont(Assets.notoFont);
  repeatDays.setBounds(x, y+3, repeatDays.getPreferredSize().width, 20);
  panel.add(repeatDays);
  repeatDays.setForeground(UIConfig.getThemeColor("week-title"));
  repeatDays.setBackground(UIConfig.getThemeColor("table-bg"));
  
  dayAmount = new JComboBox<Integer>(new Integer[] {1,2,3,4,5,6});
  dayAmount.setBounds(x + w - dayAmount.getPreferredSize().width - 10, y, dayAmount.getPreferredSize().width + 10, 25);
  y += 25;
  panel.add(dayAmount);
  dayAmount.setFont(Assets.notoFont);
  dayAmount.setForeground(UIConfig.getThemeColor("week-title"));
  dayAmount.setBackground(UIConfig.getThemeColor("comp.bg-in"));

  notRepeat = new JRadioButton(Dictionary.get(Dictionary.notRepeat), false);
  notRepeat.setFont(Assets.notoFont);
  notRepeat.setBounds(x, y, notRepeat.getPreferredSize().width, 20);
  y += 30;
  panel.add(notRepeat);
  notRepeat.setForeground(UIConfig.getThemeColor("week-title"));
  notRepeat.setBackground(UIConfig.getThemeColor("table-bg"));

  freqGroup.add(repeatWeek);
  freqGroup.add(repeatDays);
  freqGroup.add(notRepeat);

//  dayAmount.setEnabled(false);
//  repeatWeek.addActionListener(event -> dayAmount.setEnabled(repeatDays.isSelected()));
//  repeatDays.addActionListener(event -> dayAmount.setEnabled(repeatDays.isSelected()));
  
  return y;
  
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
    
    if(calendar.before(Calendar.getInstance()))
    	calendar.add(Calendar.DAY_OF_YEAR, 7);

    int amount = 0;
    boolean repeat = false;
    if(!fast) {
   	 amount = notRepeat.isSelected() ? 0 : repeatDays.isSelected() ? dayAmount.getSelectedIndex()+1 : 7;
   	 repeat = !notRepeat.isSelected();
    }
    Task task = new Task(title.getText(), calendar, wspCheck.isSelected(), mailCheck.isSelected(), repeat, amount);
    TaskManager.addTask(task, Dictionary.getKey((String) days.getSelectedItem()));

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
