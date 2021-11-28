package main;

import java.awt.Font;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import lang.Dictionary;

import ui.UIConfig;

/**
 * Config
 */
public class Config implements Serializable {
 private static final long serialVersionUID = 42l;

 private static final String path = "config.dat";

 private int langSelected;
 private int themeSelected;
 private Font fontSelected;

 public Config(int lang, int theme, Font font) {

  langSelected = lang;
  themeSelected = theme;
  fontSelected = font;

 }

 public void setConfiguration() {

  Dictionary.changeLang(langSelected);
  UIConfig.init(themeSelected, fontSelected);

 }

 public void serialize() {

  System.out.println("SERIALIZE");

  themeSelected = UIConfig.getTheme();
  fontSelected = UIConfig.getFont();

  try {

   ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(path));
   out.writeObject(this);

   out.close();

  } catch (IOException ex) {
   ex.printStackTrace();
  }

 }

 public static Config readConfig() {

  try {

   ObjectInputStream in = new ObjectInputStream(new FileInputStream(path));
   Config config = (Config) in.readObject();

   in.close();
   return config;

  } catch (IOException | ClassNotFoundException ex) {
   System.out.println("ERROR: COULD NOT READ CONFIG FILE (in: Config.java)");

   return null;

  }

 }

}
