package ui;

import event.UserWindowEvent;

import gui.Assets;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import lang.Dictionary;

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

  setTitle(Dictionary.get(Dictionary.title));
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

 public void resetLang() {

  setTitle(Dictionary.get(Dictionary.title));

  menubar.resetLang();
  // menubar.repaint();
  border.resetLang();
  // border.repaint();
  weekPanel.resetLang();

 }

 private class MenuBar extends JMenuBar {
  private static final long serialVersionUID = 42l;

  private JMenu options;
  private JMenu themes;
  private JMenuItem whiteTheme;
  private JMenuItem darkTheme;

  private JMenu langs;
  private JMenuItem enlang;
  private JMenuItem eslang;

  public MenuBar() {

   options = new JMenu(Dictionary.get(Dictionary.options));
   options.setFont(Assets.notoFont);
   options.setBorder(null);
   add(options);

   // THEME

   themes = new JMenu(Dictionary.get(Dictionary.theme));
   themes.setFont(Assets.notoFont);
   themes.setBorder(null);
   options.add(themes);

   whiteTheme = new JMenuItem(Dictionary.get(Dictionary.whiteTheme));
   whiteTheme.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {

     UIConfig.setTheme(UIConfig.whiteTheme);

    }
   });
   whiteTheme.setFont(Assets.notoFont);
   whiteTheme.setBorder(null);
   themes.add(whiteTheme);

   darkTheme = new JMenuItem(Dictionary.get(Dictionary.darkTheme));
   darkTheme.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {

     UIConfig.setTheme(UIConfig.darkTheme);

    }
   });
   darkTheme.setFont(Assets.notoFont);
   darkTheme.setBorder(null);
   themes.add(darkTheme);

   // LANG

   langs = new JMenu(Dictionary.get(Dictionary.languaje));
   langs.setFont(Assets.notoFont);
   langs.setBorder(null);
   options.add(langs);

   eslang = new JMenuItem(Dictionary.get(Dictionary.spanish));
   eslang.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {

     Dictionary.changeLang(Dictionary.spanishLang);
     Window.this.resetLang();

    }
   });
   eslang.setFont(Assets.notoFont);
   eslang.setBorder(null);
   langs.add(eslang);

   enlang = new JMenuItem(Dictionary.get(Dictionary.english));
   enlang.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {

     Dictionary.changeLang(Dictionary.englishLang);
     Window.this.resetLang();

    }
   });
   enlang.setFont(Assets.notoFont);
   enlang.setBorder(null);
   langs.add(enlang);

   setBorder(null);

   repaintTheme();

  }

  public void resetLang() {

   options.setText(Dictionary.get(Dictionary.options));
   themes.setText(Dictionary.get(Dictionary.theme));
   whiteTheme.setText(Dictionary.get(Dictionary.whiteTheme));
   darkTheme.setText(Dictionary.get(Dictionary.darkTheme));
   langs.setText(Dictionary.get(Dictionary.languaje));
   eslang.setText(Dictionary.get(Dictionary.spanish));
   enlang.setText(Dictionary.get(Dictionary.english));

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

   langs.setForeground(UIConfig.getThemeColor("week-title"));
   langs.setBackground(UIConfig.getThemeColor("window-border"));
   eslang.setForeground(UIConfig.getThemeColor("week-title"));
   eslang.setBackground(UIConfig.getThemeColor("window-border"));
   enlang.setForeground(UIConfig.getThemeColor("week-title"));
   enlang.setBackground(UIConfig.getThemeColor("window-border"));

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
