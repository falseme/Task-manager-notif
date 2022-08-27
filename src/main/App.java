package main;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Calendar;

import api.Keys;
import gui.Assets;
import lang.Dictionary;
import notif.MailNotification;
import task.TaskManager;
import ui.UIConfig;
import ui.Window;
import util.MessagePane;

public class App {

 private static Window window;
 private static Config config;

 public static void main(String[] args) {

  try {
   init();
  }catch(Exception ex) {
   File file = new File("error-log" + Calendar.getInstance().toString() + ".txt");
   try {
	file.createNewFile();
	ex.printStackTrace(new PrintStream(file));
   }catch (IOException e) {
	e.printStackTrace();
   }

  }

 }
 
 private static void init() {

  Assets.init();
  Dictionary.init();

  config = Config.readConfig();
  if (config == null)
   config = new Config(Dictionary.spanishLang, UIConfig.whiteTheme);
  config.setConfiguration();

  if(!Keys.init()) {
   new MessagePane(Dictionary.get(Dictionary.keys_load_title), Dictionary.get(Dictionary.keys_load_desc));
  }else {
   MailNotification.init();
  }

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
