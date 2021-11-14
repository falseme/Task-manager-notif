package ui;

import javax.swing.JFrame;

public class Window extends JFrame {
 private static final long serialVersionUID = 42l;

 public Window() {

  setInitParams();

  WeekPanel panel = new WeekPanel();
  add(panel);

 }

 private void setInitParams() {

  setTitle("TaskManager");
  setSize(800, 600);
  setLocationRelativeTo(null);
  // setResizable(false);

  setDefaultCloseOperation(EXIT_ON_CLOSE);

 }

}
