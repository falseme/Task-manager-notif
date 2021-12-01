package main;

import gui.Assets;

import lang.Dictionary;

import task.TaskManager;

import ui.UIConfig;
import ui.Window;

public class App {

 private static Window window;
 private static Config config;

 public static void main(String[] args) {

  Assets.init();
  Dictionary.init();

  config = Config.readConfig();
  if (config == null)
   config = new Config(Dictionary.spanishLang, UIConfig.whiteTheme, UIConfig.defaultFont);
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
