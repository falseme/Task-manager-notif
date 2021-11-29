package ui;

import event.UserWindowEvent;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import task.Task;
import task.TaskManager;

import ui.layout.WindowLayout;

public class Window extends JFrame {
 private static final long serialVersionUID = 42l;

 private WeekPanel weekPanel;

 public Window() {

  setInitParams();

  // add(new WindowBorder(this));

  weekPanel = new WeekPanel();
  add(weekPanel);

  addWindowListener(new UserWindowEvent());

  setJMenuBar(createJMenuBar());

 }

 private void setInitParams() {

  setTitle("TaskManager");
  setSize(800, 600);
  setLocationRelativeTo(null);
  // setResizable(false);
  // setUndecorated(true);

  // setLayout(new WindowLayout());

  setDefaultCloseOperation(EXIT_ON_CLOSE);

 }

 public void updateTasks(int dayOfWeek) {

  weekPanel.updateTasks(dayOfWeek);

 }

 public void updateTask(int dayOfWeek, Task task) {

  weekPanel.updateTask(dayOfWeek, task);

 }

 public void repaintTheme() {

  getJMenuBar().setBackground(UIConfig.getThemeColor("window-border"));
  getJMenuBar().repaint();
  weekPanel.repaintTheme();

 }

 private JMenuBar createJMenuBar() {

  JMenuBar bar = new JMenuBar();

  JMenu options = new JMenu("Options");
  options.setFont(UIConfig.defaultFont);
  options.setForeground(UIConfig.getThemeColor("week-title"));
  bar.add(options);

  JMenu themes = new JMenu("Theme");
  options.setFont(UIConfig.defaultFont);
  options.add(themes);

  JMenuItem whiteTheme = new JMenuItem("White Theme");
  whiteTheme.addActionListener(new ActionListener() {
   public void actionPerformed(ActionEvent e) {

    UIConfig.setTheme(UIConfig.whiteTheme);

   }
  });
  whiteTheme.setFont(UIConfig.defaultFont);
  themes.add(whiteTheme);

  JMenuItem darkTheme = new JMenuItem("Dark Theme");
  darkTheme.addActionListener(new ActionListener() {
   public void actionPerformed(ActionEvent e) {

    UIConfig.setTheme(UIConfig.darkTheme);

   }
  });
  darkTheme.setFont(UIConfig.defaultFont);
  themes.add(darkTheme);

  bar.setBackground(UIConfig.getThemeColor("window-border"));
  bar.setBorder(null);

  return bar;

 }

}
