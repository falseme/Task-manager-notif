package main;

import lang.Dictionary;

import ui.Window;

import task.TaskManager;

public class App {

 private static Window window;
 private static Config config;

 public static void main(String[] args) {

  Dictionary.init();

  config = Config.readConfig();
  if (config == null) {
   config = new Config(Dictionary.spanishLang);
   config.serialize();
  }
  config.setConfiguration();

  window = new Window();
  window.setVisible(true);

  TaskManager.init(window);

 }

 public static Window getUserWindow() {
  return window;
 }

 public static Config getConfig() {
  return config;
 }

}
