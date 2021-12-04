package gui;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

/**
 * Loader
 */
public class Loader {

 public static BufferedImage loadPng(String path) {

  try {

   return ImageIO.read(Loader.class.getResource(path));

  } catch (IOException ex) {
   System.out.println("ERROR: COULD NOT LOAD IMAGE (name: " + path + ") (in: Loader.java)");
   return null;
  }

 }

 public static Font loadFont(String path,  int style, int size) {

  Font font;

  try {

   InputStream is = Loader.class.getResourceAsStream(path);
   font = Font.createFont(Font.TRUETYPE_FONT, is);

  } catch (FontFormatException | IOException ex) {
   System.out.println("ERROR: Could not load font: " + path + " (in: Loader.java)");
   font = new Font("Console", Font.PLAIN, 12);
  }

  return font.deriveFont(style, size);

 }

}
