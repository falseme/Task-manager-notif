package ui;

import java.awt.Font;

/**
 * UIConfig
 */
public class UIConfig {

 public static final int whiteTheme = 0;
 public static final int darkTheme = 1;

 public static final Font defaultFont = new Font("Dialog", Font.PLAIN, 12);

 private static int theme;

 public static void init(int initTheme) {

  theme = initTheme;

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
