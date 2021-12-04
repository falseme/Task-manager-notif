package main;

import gui.Assets;

import lang.Dictionary;

import notif.MailNotification;

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
   config = new Config(Dictionary.spanishLang, UIConfig.whiteTheme);
  config.setConfiguration();

  MailNotification.init();

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
