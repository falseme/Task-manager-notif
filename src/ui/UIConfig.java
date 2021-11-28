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
 private static Font font;

 public static void init(int initTheme, Font initFont) {

  theme = initTheme;
  font = initFont;

 }

 public static void setFont(Font newFont) {
  font = newFont;
 }

 public static Font getFont() {
  return font;
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
