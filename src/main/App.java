package main;

import lang.Dictionary;

import ui.Window;

public class App {

 private static Window window;

 public static void main(String[] args) {

  Dictionary.init();

  window = new Window();
  window.setVisible(true);

  TaskManager.init(window);

 }

 public static Window getUserWindow(){
  return window;
 }

}
