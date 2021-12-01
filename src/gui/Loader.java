package gui;

import java.awt.image.BufferedImage;
import java.io.IOException;

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

}
