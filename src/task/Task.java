package task;

import event.ComponentBackgroundMouseEvent;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Calendar;

import javax.swing.JComponent;
import javax.swing.JLabel;

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

 public Task(String title, Calendar date, boolean notifWsp, boolean notifMail, boolean repeat) {

  this.title = title;
  this.date = date;
  this.notifWsp = notifWsp;
  this.notifMail = notifMail;

  this.repeat = repeat;

  setLayout(new TaskLayout());

  JLabel titleLabel = new JLabel("  " + title, JLabel.LEFT);
  titleLabel.setForeground(UIConfig.getThemeColor("week-title"));
  add(titleLabel);
  JLabel dateLabel = new JLabel(date.get(Calendar.HOUR_OF_DAY) + ":" + date.get(Calendar.MINUTE) + "  ", JLabel.RIGHT);
  dateLabel.setForeground(UIConfig.getThemeColor("week-title"));
  add(dateLabel);

  addMouseListener(new ComponentBackgroundMouseEvent(this));

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
