package main;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import lang.Dictionary;

/**
 * Config
 */
public class Config implements Serializable {
 private static final long serialVersionUID = 42l;

 private static final String path = "config.dat";

 private int langSelected;

 public Config(int lang) {

  langSelected = lang;

 }

 public void setConfiguration() {

  Dictionary.changeLang(langSelected);

 }

 public void serialize() {

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
   ex.printStackTrace();

   return null;

  }

 }

}
