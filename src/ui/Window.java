package ui;

import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import api.Keys;
import event.UserWindowEvent;
import gui.Assets;
import lang.Dictionary;
import task.Task;
import task.TaskCreator;
import ui.layout.WindowLayout;
import util.MailLoginPane;
import util.WspLoginPane;

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
  setSize(860, 640);
  setPreferredSize(getSize());
  setLocationRelativeTo(null);
  // setResizable(false);
  setIconImage(Assets.app_icon.getImage());
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

 // update tasks layout and delete the input task
 public void updateTasks(int dayOfWeek, Task task) {

  weekPanel.updateTask(dayOfWeek, task);

 }

 public WindowBorder getWindowBorder() {
  return border;
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

  // options
  private JMenu options;
  private JMenu themes;
  private JMenuItem whiteTheme;
  private JMenuItem darkTheme;

  private JMenu langs;
  private JMenuItem enlang;
  private JMenuItem eslang;

  // notifications
  JMenu user;
  JMenuItem mail;
  JMenuItem wsp;
  
  //fast task creation
  JMenu tasks;
  JMenuItem fastTask;

  public MenuBar() {

   options = new JMenu(Dictionary.get(Dictionary.options));
   options.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
   options.setFont(Assets.notoFont);
   options.setBorder(null);
   add(options);

   // THEME

   themes = new JMenu(Dictionary.get(Dictionary.theme));
   themes.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
   themes.setFont(Assets.notoFont);
   themes.setBorder(null);
   options.add(themes);

   whiteTheme = new JMenuItem(Dictionary.get(Dictionary.whiteTheme));
   whiteTheme.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
   whiteTheme.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {

     UIConfig.setTheme(UIConfig.whiteTheme);
     Window.this.repaintTheme();

    }
   });
   whiteTheme.setFont(Assets.notoFont);
   whiteTheme.setBorder(null);
   themes.add(whiteTheme);

   darkTheme = new JMenuItem(Dictionary.get(Dictionary.darkTheme));
   darkTheme.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
   darkTheme.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {

     UIConfig.setTheme(UIConfig.darkTheme);
     Window.this.repaintTheme();

    }
   });
   darkTheme.setFont(Assets.notoFont);
   darkTheme.setBorder(null);
   themes.add(darkTheme);

   // LANG

   langs = new JMenu(Dictionary.get(Dictionary.languaje));
   langs.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
   langs.setFont(Assets.notoFont);
   langs.setBorder(null);
   options.add(langs);

   eslang = new JMenuItem(Dictionary.get(Dictionary.spanish));
   eslang.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
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
   enlang.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
   enlang.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {

     Dictionary.changeLang(Dictionary.englishLang);
     Window.this.resetLang();

    }
   });
   enlang.setFont(Assets.notoFont);
   enlang.setBorder(null);
   langs.add(enlang);

   // ----------------
   // NOTIFICATIONS

   user = new JMenu(Dictionary.get(Dictionary.user));
   user.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
   user.setFont(Assets.notoFont);
   user.setBorder(null);
   add(user);

   mail = new JMenuItem(Dictionary.get(Dictionary.mailsettings));
   mail.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
   mail.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {

    	new MailLoginPane();

    }
   });
   mail.setFont(Assets.notoFont);
   mail.setBorder(null);
   user.add(mail);
   mail.setEnabled(Keys.loaded);
   
   wsp = new JMenuItem(Dictionary.get(Dictionary.wspsettings));
   wsp.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
   wsp.addActionListener(new ActionListener() {
	public void actionPerformed(ActionEvent e) {
		new WspLoginPane();
	}
   });
   wsp.setFont(Assets.notoFont);
   wsp.setBorder(null);
   user.add(wsp);
   wsp.setEnabled(Keys.loaded);
   
   //FAST TASK CREATION
   
   tasks = new JMenu(Dictionary.get(Dictionary.tasks));
   tasks.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
   tasks.setFont(Assets.notoFont);
   tasks.setBorder(null);
   add(tasks);
   
   fastTask = new JMenuItem(Dictionary.get(Dictionary.fastTask));
   fastTask.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
   fastTask.addActionListener(event -> new TaskCreator());
   fastTask.setFont(Assets.notoFont);
   fastTask.setBorder(null);
   tasks.add(fastTask);

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

   user.setText(Dictionary.get(Dictionary.user));
   mail.setText(Dictionary.get(Dictionary.mailsettings));
   wsp.setText(Dictionary.get(Dictionary.wspsettings));
   
   tasks.setText(Dictionary.get(Dictionary.tasks));
   fastTask.setText(Dictionary.get(Dictionary.fastTask));

  }

  public void repaintTheme() {

   options.setForeground(UIConfig.getThemeColor("week-title"));
   options.setBackground(UIConfig.getThemeColor("window-border"));

   themes.setForeground(UIConfig.getThemeColor("week-title"));
//   themes.setBackground(UIConfig.getThemeColor("window-border"));
   whiteTheme.setForeground(UIConfig.getThemeColor("week-title"));
//   whiteTheme.setBackground(UIConfig.getThemeColor("window-border"));
   darkTheme.setForeground(UIConfig.getThemeColor("week-title"));
//   darkTheme.setBackground(UIConfig.getThemeColor("window-border"));

   langs.setForeground(UIConfig.getThemeColor("week-title"));
//   langs.setBackground(UIConfig.getThemeColor("window-border"));
   eslang.setForeground(UIConfig.getThemeColor("week-title"));
//   eslang.setBackground(UIConfig.getThemeColor("window-border"));
   enlang.setForeground(UIConfig.getThemeColor("week-title"));
//   enlang.setBackground(UIConfig.getThemeColor("window-border"));

   user.setForeground(UIConfig.getThemeColor("week-title"));
//   user.setBackground(UIConfig.getThemeColor("window-border"));
   mail.setForeground(UIConfig.getThemeColor("week-title"));
//   mail.setBackground(UIConfig.getThemeColor("window-border"));
   wsp.setForeground(UIConfig.getThemeColor("week-title"));
   
   tasks.setForeground(UIConfig.getThemeColor("week-title"));
   tasks.setBackground(UIConfig.getThemeColor("window-border"));
   
   fastTask.setForeground(UIConfig.getThemeColor("week-title"));
//   fastTask.setBackground(UIConfig.getThemeColor("window-border"));

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
