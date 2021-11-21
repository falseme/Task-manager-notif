package task;

import java.awt.Graphics;
import java.util.Calendar;
import java.awt.Color;

import javax.swing.JComponent;

/**
 * Task
 */
public class Task extends JComponent {
 private static final long serialVersionUID = 42l;

 private String title;
 private Calendar date;
 private boolean notifWsp;
 private boolean notifMail;

 public Task(String title, Calendar date, boolean notifWsp, boolean notifMail) {

  this.title = title;
  this.date = date;
  this.notifWsp = notifWsp;
  this.notifMail = notifMail;

 }

 public void paintComponent(Graphics g) {

  g.setColor(Color.CYAN);
  int arc = 10;
  g.drawRoundRect(0, 0, getWidth(), getHeight(), arc, arc);

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

}
