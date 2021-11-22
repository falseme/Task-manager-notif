package task;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Calendar;

import javax.swing.JComponent;
import javax.swing.JLabel;

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

  add(new JLabel("  " + title, JLabel.LEFT));
  add(new JLabel(date.get(Calendar.HOUR_OF_DAY) + ":" + date.get(Calendar.MINUTE) + "  ", JLabel.RIGHT));

 }

 public void paintComponent(Graphics g) {

  int arc = 10;

  g.setColor(Color.GRAY);
  g.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, arc, arc);

  g.setColor(Color.CYAN);
  g.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, arc, arc);

 }

 public void passWeek() {

  date.add(Calendar.DAY_OF_MONTH, 7);
  System.out.println(date);

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
