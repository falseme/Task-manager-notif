package gui;

import java.awt.image.BufferedImage;

/**
 * Assets
 */
public class Assets {

 public static BufferedImage ui_close;
 public static BufferedImage ui_min;

 public static void init() {

  ui_close = Loader.loadPng("/gui/cerrar.png");
  ui_min = Loader.loadPng("/gui/min.png");

 }

}
