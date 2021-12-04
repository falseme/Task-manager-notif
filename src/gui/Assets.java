package gui;

import java.awt.Font;
import java.awt.image.BufferedImage;

/**
 * Assets
 */
public class Assets {

 public static BufferedImage ui_close;
 public static BufferedImage ui_min;

 public static Font notoFont;
 public static Font oswaldFont;
 public static Font notoFont_Task;

 public static void init() {

  ui_close = Loader.loadPng("/gui/cerrar.png");
  ui_min = Loader.loadPng("/gui/min.png");

  notoFont = Loader.loadFont("/fonts/NotoSans-Regular.ttf", Font.PLAIN, 13);
  oswaldFont = Loader.loadFont("/fonts/Oswald-Regular.ttf", Font.PLAIN, 20);
  notoFont_Task = Loader.loadFont("/fonts/NotoSans-Regular.ttf", Font.BOLD, 12);

 }

}
