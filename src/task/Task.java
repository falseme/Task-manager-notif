package task;

import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import event.ComponentBackgroundMouseEvent;
import event.TaskPopupTextEvent;
import gui.Assets;
import ui.UIConfig;
import ui.layout.TaskLayout;

/**
 * Task
 */
public class Task extends JComponent {
 private static final long serialVersionUID = 42L;

 private String title;
 private Calendar date;
 private boolean desktop = false;
 private boolean notifWsp;
 private boolean notifMail;

 private boolean repeat;
 private int dayAmount = 0;

 private JLabel dateLabel, titleLabel;

 public Task(String title, Calendar date, boolean desktop, boolean notifWsp, boolean notifMail, boolean repeat, int dayAmount) {

  this.title = title;
  this.date = date;
  this.desktop = desktop;
  this.notifWsp = notifWsp;
  this.notifMail = notifMail;

  this.repeat = repeat;
  this.dayAmount = dayAmount;

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
  TaskPopupTextEvent ev = new TaskPopupTextEvent(this);
  addMouseListener(ev);
  addMouseMotionListener(ev);

  setComponentPopupMenu(createPopupMenu());
  
  setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

 }

 public Task(Task otherTask) {

  this(otherTask.title, otherTask.date, otherTask.desktop, otherTask.notifWsp, otherTask.notifMail, otherTask.repeat, otherTask.dayAmount);		 
  
  //COMPATIBILITY CHECK
  
  //v2.1
  if(otherTask.repeat && otherTask.dayAmount == 0) {
   this.dayAmount = 7;
   this.desktop = true;
  }
  
 }

 public void paintComponent(Graphics g) {

  int arc = 20;

  g.setColor(getBackground());
  g.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, arc, arc);

  g.setColor(UIConfig.getThemeColor("task-border"));
  g.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, arc, arc);

  if(repeat) {
   int h = dateLabel.getHeight() / 3 * 2;
   int x = 0;
   int y = dateLabel.getY() + h / 2;
   g.drawImage(Assets.repeat, x, y, h, h, null);
  }
  
  if (notifMail) {
   int h = dateLabel.getHeight() / 3 * 2;
   int x = repeat ? h : 6;
   int y = dateLabel.getY() + h / 2;
   g.drawImage(Assets.gmail, x, y, h, h, null);
  }
  
  if(notifWsp) {
   int h = dateLabel.getHeight() / 3 * 2;
   int x = repeat ? (notifMail ? 2 + h*2 : h) : 6;
   int y = dateLabel.getY() + h / 2;
   g.drawImage(Assets.wsp, x, y, h, h, null);
  }

 }

 private JPopupMenu createPopupMenu() {

  JPopupMenu pop = new JPopupMenu();

  JMenuItem remove = new JMenuItem("Remove Task");
  remove.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
  remove.addActionListener(new ActionListener() {
   public void actionPerformed(ActionEvent e) {

    TaskManager.removeTask(Task.this, date.get(Calendar.DAY_OF_WEEK) - 1);

   }
  });

  JMenuItem modify = new JMenuItem("Modify Task");
  modify.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
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

 public void passTime() {

  date.add(Calendar.DAY_OF_MONTH, dayAmount);

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
 
 public boolean notifDesktop() {
  return desktop;
 }
 
 public int getAmount() {
	 return dayAmount;
 }

 public String toString() {

  return "Title: " + title + " | Day: " + date.get(Calendar.DAY_OF_MONTH) + " | Month: " + date.get(Calendar.MONTH)
    + " | Year: " + date.get(Calendar.YEAR) + " | Time: " + date.get(Calendar.HOUR_OF_DAY) + ":"
    + date.get(Calendar.MINUTE) + " | Frequency: " + repeat + " | Day frequency: " + dayAmount
    + " | Desktop: " + desktop + " | WSP: " + notifWsp + " | Mail: " + notifMail;

 }

}
