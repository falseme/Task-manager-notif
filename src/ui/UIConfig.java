package ui;

import java.awt.Color;
import java.util.HashMap;

import main.App;

/**
 * UIConfig
 */
public class UIConfig {

 public static final int whiteTheme = 0;
 public static final int darkTheme = 1;

 public static HashMap<Integer, HashMap<String, Color>> themeColors;

 private static int theme;

 public static void init(int initTheme) {

  theme = initTheme;

  themeColors = new HashMap<Integer, HashMap<String, Color>>();
  themeColors.put(whiteTheme, new HashMap<String, Color>());
  themeColors.put(darkTheme, new HashMap<String, Color>());

  themeColors.get(whiteTheme).put("window-border", new Color(170, 170, 170));
  themeColors.get(whiteTheme).put("table-bg", new Color(190, 190, 190));
  themeColors.get(whiteTheme).put("week-title", new Color(100, 100, 100));
  themeColors.get(whiteTheme).put("task-border", new Color(165, 165, 165));
  themeColors.get(whiteTheme).put("comp-bg", new Color(185, 185, 185)); // comp => component
  themeColors.get(whiteTheme).put("comp-bg-in", new Color(190, 190, 190));
  themeColors.get(whiteTheme).put("comp-bg-click", new Color(175, 175, 175));

  themeColors.get(darkTheme).put("window-border", new Color(44, 44, 44));
  themeColors.get(darkTheme).put("table-bg", new Color(32, 32, 32));
  themeColors.get(darkTheme).put("week-title", new Color(100, 100, 100));
  themeColors.get(darkTheme).put("task-border", new Color(42, 42, 42));
  themeColors.get(darkTheme).put("comp-bg", new Color(34, 34, 34)); // comp => component
  themeColors.get(darkTheme).put("comp-bg-in", new Color(38, 38, 38));
  themeColors.get(darkTheme).put("comp-bg-click", new Color(30, 30, 30));

 }

 public static Color getThemeColor(String key) {

  return themeColors.get(theme).get(key);

 }

 public static void setTheme(int newTheme) {

  theme = newTheme;

 }

 public static boolean isWhiteTheme() {

  if (theme == whiteTheme)
   return true;
  return false;

 }

 public static boolean isDarkTheme() {

  if (theme == darkTheme)
   return true;
  return false;

 }

 public static int getTheme() {
  return theme;
 }

}
