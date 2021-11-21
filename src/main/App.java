package main;

import ui.Window;
import task.TaskManager;

public class App {

 private static Window window;

 public static void main(String[] args) {

  window = new Window();
  window.setVisible(true);

  TaskManager.init(window);

 }

}
