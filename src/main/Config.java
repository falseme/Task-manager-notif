package main;

import java.io.File;
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

 public static final String PATH = System.getProperty("user.home") + File.separator + ".Tasky";
 private static final String path = PATH + File.separator + "config.dat";

 private String usermail;
 private String userwspnumber;

 private int langSelected;
 private int themeSelected;

 public Config(int lang, int theme) {

  langSelected = lang;
  themeSelected = theme;

  usermail = null;
  userwspnumber = null;

 }

 public void setConfiguration() {

  Dictionary.changeLang(langSelected);
  UIConfig.init(themeSelected);

 }

 public void serialize() {

  System.out.println("SERIALIZE Config.java -> " + PATH);

  themeSelected = UIConfig.getTheme();
  langSelected = Dictionary.getLangSelected();

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

 public String getUserMail() {
  return usermail;
 }

 public void setUserMail(String mail) {
  usermail = mail;
 }

 public String getUserwspnumber() {
	return userwspnumber;
 }

 public void setUserwspnumber(String userwspnumber) {
	this.userwspnumber = userwspnumber;
 }

}
