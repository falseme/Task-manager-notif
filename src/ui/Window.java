package ui;

import event.UserWindowEvent;

import java.awt.Color;
import java.awt.Graphics;
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
 private WindowBorder border;
 private MenuBar menubar;

 public Window() {

  setInitParams();

  weekPanel = new WeekPanel();
  add(weekPanel);

  addWindowListener(new UserWindowEvent());

  // setJMenuBar(createJMenuBar());

 }

 private void setInitParams() {

  setTitle("TaskManager");
  setSize(800, 600);
  setLocationRelativeTo(null);
  // setResizable(false);
  setUndecorated(true);

  setLayout(new WindowLayout());

  border = new WindowBorder(this);
  add(border);

  menubar = new MenuBar();
  add(menubar);

  setDefaultCloseOperation(EXIT_ON_CLOSE);

 }

 public void updateTasks(int dayOfWeek) {

  weekPanel.updateTasks(dayOfWeek);

 }

 public void updateTask(int dayOfWeek, Task task) {

  weekPanel.updateTask(dayOfWeek, task);

 }

 public void repaintTheme() {

  menubar.repaintTheme();
  weekPanel.repaintTheme();
  border.repaintTheme();

 }

 private class MenuBar extends JMenuBar {
  private static final long serialVersionUID = 42l;

  private JMenu options;
  private JMenu themes;
  private JMenuItem whiteTheme;
  private JMenuItem darkTheme;

  public MenuBar() {

   options = new JMenu("Options");
   options.setFont(UIConfig.defaultFont);
   options.setBorder(null);
   add(options);

   themes = new JMenu("Theme");
   themes.setFont(UIConfig.defaultFont);
   themes.setBorder(null);
   options.add(themes);

   whiteTheme = new JMenuItem("White Theme");
   whiteTheme.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {

     UIConfig.setTheme(UIConfig.whiteTheme);

    }
   });
   whiteTheme.setFont(UIConfig.defaultFont);
   whiteTheme.setBorder(null);
   themes.add(whiteTheme);

   darkTheme = new JMenuItem("Dark Theme");
   darkTheme.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {

     UIConfig.setTheme(UIConfig.darkTheme);

    }
   });
   darkTheme.setFont(UIConfig.defaultFont);
   darkTheme.setBorder(null);
   themes.add(darkTheme);

   setBorder(null);

   repaintTheme();

  }

  public void repaintTheme() {

   options.setForeground(UIConfig.getThemeColor("week-title"));
   options.setBackground(UIConfig.getThemeColor("window-border"));
   themes.setForeground(UIConfig.getThemeColor("week-title"));
   themes.setBackground(UIConfig.getThemeColor("window-border"));
   whiteTheme.setForeground(UIConfig.getThemeColor("week-title"));
   whiteTheme.setBackground(UIConfig.getThemeColor("window-border"));
   darkTheme.setForeground(UIConfig.getThemeColor("week-title"));
   darkTheme.setBackground(UIConfig.getThemeColor("window-border"));

   setForeground(UIConfig.getThemeColor("week-title"));
   setBackground(UIConfig.getThemeColor("window-border"));

  }

  public void paintComponent(Graphics g) {
   super.paintComponent(g);

   g.setColor(UIConfig.getThemeColor("task-border"));
   g.fillRect(0, 0, getWidth(), 2);

  }

 }

}
