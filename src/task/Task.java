package task;

import event.ComponentBackgroundMouseEvent;

import gui.Assets;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import ui.UIConfig;
import ui.layout.TaskLayout;

/**
 * Task
 */
public class Task extends JComponent {
 private static final long serialVersionUID = 42l;

 private String title;
 private Calendar date;
 private boolean notifWsp;
 private boolean notifMail;

 private boolean repeat;

 private JLabel dateLabel, titleLabel;

 public Task(String title, Calendar date, boolean notifWsp, boolean notifMail, boolean repeat) {

  this.title = title;
  this.date = date;
  this.notifWsp = notifWsp;
  this.notifMail = notifMail;

  this.repeat = repeat;

  setLayout(new TaskLayout());

  titleLabel = new JLabel("  " + title, JLabel.LEFT);
  titleLabel.setFont(Assets.notoFont_Task);
  titleLabel.setForeground(UIConfig.getThemeColor("week-title"));
  add(titleLabel);

  String hour = "" + date.get(Calendar.HOUR_OF_DAY);
  if (hour.length() <= 1)
   hour = "0" + hour;

  String mins = "" + date.get(Calendar.MINUTE);
  if (mins.length() <= 1)
   mins = "0" + mins;

  dateLabel = new JLabel(hour + ":" + mins + "  ", JLabel.RIGHT);
  dateLabel.setFont(Assets.notoFont_Task);
  dateLabel.setForeground(UIConfig.getThemeColor("week-title"));
  add(dateLabel);

  addMouseListener(new ComponentBackgroundMouseEvent(this));

  setComponentPopupMenu(createPopupMenu());

 }

 public Task(Task otherTask) {

  this(otherTask.title, otherTask.date, otherTask.notifWsp, otherTask.notifMail, otherTask.repeat);

 }

 public void paintComponent(Graphics g) {

  int arc = 20;

  g.setColor(getBackground());
  g.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, arc, arc);

  g.setColor(UIConfig.getThemeColor("task-border"));
  g.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, arc, arc);

  if (notifMail) {
   int h = dateLabel.getHeight() / 3 * 2;
   int x = 10;
   int y = dateLabel.getY() + h / 2;
   g.drawImage(Assets.gmail, x, y, h, h, null);
  }

 }

 private JPopupMenu createPopupMenu() {

  JPopupMenu pop = new JPopupMenu();

  JMenuItem remove = new JMenuItem("Remove Task");
  remove.addActionListener(new ActionListener() {
   public void actionPerformed(ActionEvent e) {

    TaskManager.removeTask(Task.this, date.get(Calendar.DAY_OF_WEEK) - 1);

   }
  });

  JMenuItem modify = new JMenuItem("Modify Task");
  modify.addActionListener(new ActionListener() {
   public void actionPerformed(ActionEvent e) {

    TaskCreator creator = new TaskCreator(Task.this);
    creator.setVisible(true);

   }
  });

  pop.add(modify);
  pop.add(remove);

  return pop;

 }

 public void passWeek() {

  date.add(Calendar.DAY_OF_MONTH, 7);

 }

 public String getTitle() {
  return title;
 }

 public Calendar getDate() {
  return date;
 }

 public boolean notifWsp() {
  return notifWsp;
 }

 public boolean notifMail() {
  return notifMail;
 }

 public boolean repeat() {
  return repeat;
 }

 public String toString() {

  return "Title: " + title + " | Day: " + date.get(Calendar.DAY_OF_MONTH) + " | Month: " + date.get(Calendar.MONTH)
    + " | Year: " + date.get(Calendar.YEAR) + " | Time: " + date.get(Calendar.HOUR_OF_DAY) + ":"
    + date.get(Calendar.MINUTE) + " | WSP: " + notifWsp + " | Mail: " + notifMail;

 }

}
