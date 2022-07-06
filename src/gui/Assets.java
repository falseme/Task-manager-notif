package gui;

import java.awt.Font;
import java.awt.font.TextAttribute;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

/**
 * Assets
 */
public class Assets {

 public static BufferedImage ui_close;
 public static BufferedImage ui_min;
 public static BufferedImage gmail;

 public static Font notoFont;
 public static Font oswaldFont;
 public static Font notoFont_Task;
 public static Font notoFont_Underlined;

 public static void init() {

  ui_close = Loader.loadPng("/gui/cerrar.png");
  ui_min = Loader.loadPng("/gui/min.png");
  gmail = Loader.loadPng("/gui/gmail16.png");

  notoFont = Loader.loadFont("/fonts/NotoSans-Regular.ttf", Font.PLAIN, 13);
  oswaldFont = Loader.loadFont("/fonts/Oswald-Regular.ttf", Font.PLAIN, 20);
  notoFont_Task = Loader.loadFont("/fonts/NotoSans-Regular.ttf", Font.BOLD, 12);
  
  Map<TextAttribute, Integer> fontAttributes = new HashMap<TextAttribute, Integer>();
  fontAttributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
  notoFont_Underlined = notoFont.deriveFont(fontAttributes);
  

 }

}
