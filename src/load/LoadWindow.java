package load;

import javax.swing.JDialog;
import javax.swing.JLabel;

/**
 * LoadWindow
 */
public class LoadWindow extends JDialog {
 private static final long serialVersionUID = 42l;

 public LoadWindow(String message) {

  System.out.println(message);

  setSize(200, 100);
  setLocationRelativeTo(null);
  setUndecorated(true);
  setAlwaysOnTop(true);

  add(new JLabel(message, JLabel.CENTER));

  setVisible(true);

 }

}
