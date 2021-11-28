package ui;

import event.UserWindowEvent;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import task.Task;
import task.TaskManager;

public class Window extends JFrame {
 private static final long serialVersionUID = 42l;

 private WeekPanel weekPanel;

 public Window() {

  setInitParams();

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

  setDefaultCloseOperation(EXIT_ON_CLOSE);

 }

 public void updateTasks(int dayOfWeek) {

  weekPanel.updateTasks(dayOfWeek);

 }

 public void updateTask(int dayOfWeek, Task task) {

  weekPanel.updateTask(dayOfWeek, task);

 }

 private JMenuBar createJMenuBar() {

  JMenuBar bar = new JMenuBar();

  JMenu options = new JMenu("Options");
  bar.add(options);

  JMenu themes = new JMenu("Theme");
  options.add(themes);

  JMenuItem whiteTheme = new JMenuItem("White Theme");
  whiteTheme.addActionListener(new ActionListener() {
   public void actionPerformed(ActionEvent e) {

    UIConfig.setTheme(UIConfig.whiteTheme);

   }
  });
  themes.add(whiteTheme);

  JMenuItem darkTheme = new JMenuItem("Dark Theme");
  darkTheme.addActionListener(new ActionListener() {
   public void actionPerformed(ActionEvent e) {

    UIConfig.setTheme(UIConfig.darkTheme);

   }
  });
  themes.add(darkTheme);

  return bar;

 }

}
